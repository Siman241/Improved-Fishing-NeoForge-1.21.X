package net.siman.improvedfishingmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.neoforged.neoforge.common.util.TriState;
import net.siman.improvedfishingmod.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class CornCropBlock extends CropBlock {
    public static final int FIRST_STAGE_MAX_AGE = 8;
    public static final int SECOND_STAGE_MAX_AGE = 2;

    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.box(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 10.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 12.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 14.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0),
            Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0)
    };

    public static final IntegerProperty AGE = IntegerProperty.create("age",0,10);

    public CornCropBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE_BY_AGE[this.getAge(state)];
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {

         if(!level.isAreaLoaded(pos,1)) return;
         if(level.getRawBrightness(pos,1) >= 9) {
             int currentAge = this.getAge(state);

             if(currentAge < this.getMaxAge()){
                 float growthSpeed = getGrowthSpeed(this.defaultBlockState(),level,pos);
                 //System.out.println(currentAge);
                 //System.out.println(level.getBlockState(pos.above(1)));
                 if(net.neoforged.neoforge.common.CommonHooks.canCropGrow(level, pos, state, random.nextInt((int)(25.0f / growthSpeed) + 1) == 0)){
                    if(currentAge == 7) {
                        if (level.getBlockState(pos.above(1)).is(Blocks.AIR)) {
                            level.setBlock(pos.above(1), this.getStateForAge(8), 2);
                        } else if(level.getBlockState(pos.above(1)).is(this)) {
                            level.setBlock(pos, this.getStateForAge(9), 2);
                            level.setBlock(pos.above(1), this.getStateForAge(10), 2);
                        }
                    }
                    else if(currentAge < 8){
                        level.setBlock(pos, this.getStateForAge(currentAge + 1),2);
                    }

                    net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos, state);
                 }
             }
         }


    }

    @Override
    public @NotNull TriState canSustainPlant(BlockState state, BlockGetter level, BlockPos soilPosition, Direction facing, BlockState plant) {
        if(super.mayPlaceOn(state, level, soilPosition)) return TriState.TRUE;
        else return TriState.FALSE;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return (super.canSurvive(state, level, pos) || (level.getBlockState(pos.below(1)).is(this) && ((
                level.getBlockState(pos.below(0)).getValue(AGE)==7) ||  level.getBlockState(pos.below(0)).getValue(AGE)==10)));


    }
    private boolean once=true;
    @Override
    public void growCrops(Level level, BlockPos pos, BlockState state) {
        int nextAge = this.getAge(state)+ this.getBonemealAgeIncrease(level);
        int maxAge = this.getMaxAge();
        if(nextAge > maxAge){
            nextAge = maxAge;
        }
        //System.out.println("Current Age is: " + this.getAge(state));
        //System.out.println("Next Age is: " + nextAge);
        if(this.getAge(state) == 7 && level.getBlockState(pos.above(1)).is(Blocks.AIR)){
            //level.setBlock(pos, this.getStateForAge(7), 2);
            //System.out.println("Happened First");
            level.setBlock(pos.above(1), this.getStateForAge(8), 2);
        } else if(this.getAge(state) == 7 && level.getBlockState(pos.above(1)).is(this)){
            //System.out.println("Happened Second");
            level.setBlock(pos, this.getStateForAge(9), 2);
            level.setBlock(pos.above(1), this.getStateForAge(10), 2);
        }
        else {
            //System.out.println("Happened Third");
            if(nextAge - SECOND_STAGE_MAX_AGE < 7) level.setBlock(pos, this.getStateForAge(nextAge - SECOND_STAGE_MAX_AGE), 2);
            else if(once || nextAge - SECOND_STAGE_MAX_AGE < 8) {
                level.setBlock(pos, this.getStateForAge(this.getAge(state)+1), 2);
                once=false;
            }
        }
    }

    @Override
    public int getMaxAge() {
        return  FIRST_STAGE_MAX_AGE + SECOND_STAGE_MAX_AGE;
    }

    @Override
    protected @NotNull ItemLike getBaseSeedId() {
        return ModItems.CORN_SEEDS.get();
    }

    @Override
    public @NotNull IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AGE);
    }
}
