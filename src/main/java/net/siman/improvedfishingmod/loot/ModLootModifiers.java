package net.siman.improvedfishingmod.loot;

import com.mojang.serialization.MapCodec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.siman.improvedfishingmod.ImprovedFishing;

public class ModLootModifiers {

    public static DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ImprovedFishing.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<AddItemModifier>> WORM_GLM = LOOT_MODIFIERS.register("worm_glm", AddItemModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<AddItemModifier>> SEA_SNAIL_GLM = LOOT_MODIFIERS.register("sea_snail_glm", AddItemModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<AddItemModifier>> CORN_SEEDS_CHEST = LOOT_MODIFIERS.register("corn_seeds_chest", AddItemModifier.CODEC_SUPPLIER);

    //Wood Modifiers

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetCornModifier>> LAND_CORN = LOOT_MODIFIERS.register("land_corn", SetCornModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetCodModifier>> LAND_COD = LOOT_MODIFIERS.register("land_cod", SetCodModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetWormModifier>> LAND_WORM = LOOT_MODIFIERS.register("land_worm", SetWormModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetWormModifier>> JUNGLE_WORM = LOOT_MODIFIERS.register("jungle_worm", SetWormModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetCornModifier>> JUNGLE_CORN = LOOT_MODIFIERS.register("jungle_corn", SetCornModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetCodModifier>> JUNGLE_COD = LOOT_MODIFIERS.register("jungle_cod", SetCodModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetWormModifier>> SEA_SEA_SNAIL = LOOT_MODIFIERS.register("sea_sea_snail", SetWormModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetCornModifier>> SEA_KELP = LOOT_MODIFIERS.register("sea_kelp", SetCornModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetCodModifier>> SEA_COD = LOOT_MODIFIERS.register("sea_cod", SetCodModifier.CODEC_SUPPLIER);

    //Bamboo Modifiers

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetBambooCornModifier>> BAMBOO_LAND_CORN = LOOT_MODIFIERS.register("bamboo_land_corn", SetBambooCornModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetBambooCodModifier>> BAMBOO_LAND_COD = LOOT_MODIFIERS.register("bamboo_land_cod", SetBambooCodModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetBambooWormModifier>> BAMBOO_LAND_WORM = LOOT_MODIFIERS.register("bamboo_land_worm", SetBambooWormModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetBambooWormModifier>> BAMBOO_JUNGLE_WORM = LOOT_MODIFIERS.register("bamboo_jungle_worm", SetBambooWormModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetBambooCornModifier>> BAMBOO_JUNGLE_CORN = LOOT_MODIFIERS.register("bamboo_jungle_corn", SetBambooCornModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetBambooCodModifier>> BAMBOO_JUNGLE_COD = LOOT_MODIFIERS.register("bamboo_jungle_cod", SetBambooCodModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetBambooWormModifier>> BAMBOO_SEA_SEA_SNAIL = LOOT_MODIFIERS.register("bamboo_sea_sea_snail", SetBambooWormModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetBambooCornModifier>> BAMBOO_SEA_KELP = LOOT_MODIFIERS.register("bamboo_sea_kelp", SetBambooCornModifier.CODEC_SUPPLIER);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<SetBambooCodModifier>> BAMBOO_SEA_COD = LOOT_MODIFIERS.register("bamboo_sea_cod", SetBambooCodModifier.CODEC_SUPPLIER);
}
