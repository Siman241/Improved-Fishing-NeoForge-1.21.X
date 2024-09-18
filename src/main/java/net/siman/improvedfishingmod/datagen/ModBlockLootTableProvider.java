package net.siman.improvedfishingmod.datagen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.siman.improvedfishingmod.ImprovedFishing;
import net.siman.improvedfishingmod.block.ModBlocks;
import net.minecraft.core.Holder;
import net.siman.improvedfishingmod.item.ModItems;
import org.jetbrains.annotations.NotNull;

import java.util.Set;
import java.util.function.Predicate;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        /*
        LootItemCondition.Builder dropGrownCropCondition = () -> new LootItemCondition() {
            @Override
            public LootItemConditionType getType() {
                return new LootItemConditionType((MapCodec<? extends LootItemCondition>) LootItemCondition.CODEC);
            }

            @Override
            public boolean test(LootContext lootContext) {
                System.out.println(lootContext.getLevel().getEntities().toString());
                return false;
            }
        };
        add(ModBlocks.CORN_CROP.get(),
                block -> createCropDrops(ModBlocks.CORN_CROP.get(), ModItems.CORN.get(), ModItems.CORN_SEEDS.get(), dropGrownCropCondition));

         */
    }


/*

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        /*
        return ModBlocks.BLOCKS.getEntries().stream().filter(new Predicate<DeferredHolder<Block, ? extends Block>>() {
            @Override
            public boolean test(DeferredHolder<Block, ? extends Block> blockDeferredHolder) {
                System.out.println(blockDeferredHolder.getId());
                return !blockDeferredHolder.getId().toString().equals("improvedfishingmod:corn_crop");
            }
        }).map(Holder::value)::iterator;


        //Sytem.out.println(ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator);
        //return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
    */
}
