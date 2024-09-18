package net.siman.improvedfishingmod.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.siman.improvedfishingmod.ImprovedFishing;
import net.siman.improvedfishingmod.block.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> CORN_KEY = registerKey("corn_key");

    public static final ResourceKey<ConfiguredFeature<?, ?>> FARM_KEY = registerKey("farm_key");


    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        RuleTest grassReplaceable = new TagMatchTest(BlockTags.DIRT);
        RuleTest farmReplaceable = new TagMatchTest(BlockTags.MAINTAINS_FARMLAND);

        List<OreConfiguration.TargetBlockState> overworldFarmland = List.of(OreConfiguration.target(grassReplaceable, Blocks.FARMLAND.defaultBlockState()));

        register(context, CORN_KEY, Feature.FLOWER, new RandomPatchConfiguration(32, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(ModBlocks.CORN_CROP.get())))));

        register(context, FARM_KEY, Feature.ORE, new OreConfiguration(overworldFarmland, 9));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(ImprovedFishing.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration){
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
