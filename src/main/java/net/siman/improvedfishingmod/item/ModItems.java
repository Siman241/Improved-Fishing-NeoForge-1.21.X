package net.siman.improvedfishingmod.item;

import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.siman.improvedfishingmod.ImprovedFishing;
import net.siman.improvedfishingmod.item.custom.BambooRod;
import net.siman.improvedfishingmod.item.custom.WoodRod;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ImprovedFishing.MOD_ID);
    /*
    public static final DeferredItem<FishingRodItem> POLEROD = ITEMS.register("polerod",
            ()-> new FishingRodItem(
                    new Item.Properties()
            ));
    */
    //Items and Tools
    public static final DeferredItem<Item> FISHINGLINE = ITEMS.register("fishingline",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> WOODROD = ITEMS.register("woodrod",
            ()-> new WoodRod(new Item.Properties().stacksTo(1).durability(384)));

    public static final DeferredItem<Item> WOODPOLE = ITEMS.register("woodpole",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> FISHINGHOOK = ITEMS.register("fishinghook",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> FISHINGREEL = ITEMS.register("fishingreel",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    //Improved Items
    public static final DeferredItem<Item> BAMBOO_ROD = ITEMS.register("bamboo_rod",
            ()-> new BambooRod(new Item.Properties().stacksTo(1).durability(768)));

    public static final DeferredItem<Item> BAMBOO_POLE = ITEMS.register("bamboo_pole",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> DIAMOND_TIPPED_FISHING_HOOK = ITEMS.register("diamond_tipped_fishing_hook",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> AUTOMATIC_FISHING_REEL = ITEMS.register("automatic_fishing_reel",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> STRENGTHENED_FISHING_LINE = ITEMS.register("strengthened_fishing_line",
            ()-> new Item(new Item.Properties().stacksTo(1)));


    //Land Fish
    public static final DeferredItem<Item> RAWCARP = ITEMS.register("raw_carp",
            ()-> new Item(new Item.Properties().food(ModFoods.RAWCARP)));

    public static final DeferredItem<Item> RAWCATFISH = ITEMS.register("raw_catfish",
            ()-> new Item(new Item.Properties().food(ModFoods.RAWCATFISH)));

    public static final DeferredItem<Item> RAWPIKE = ITEMS.register("raw_pike",
            ()-> new Item(new Item.Properties().food(ModFoods.RAWPIKE)));

    public static final DeferredItem<Item> COOKEDCARP = ITEMS.register("cooked_carp",
            ()-> new Item(new Item.Properties().food(ModFoods.COOKEDCARP)));

    public static final DeferredItem<Item> COOKEDCATFISH = ITEMS.register("cooked_catfish",
            ()-> new Item(new Item.Properties().food(ModFoods.COOKEDCATFISH)));

    public static final DeferredItem<Item> COOKEDPIKE = ITEMS.register("cooked_pike",
            ()-> new Item(new Item.Properties().food(ModFoods.COOKEDPIKE)));

    //Jungle Fish
    public static final DeferredItem<Item> RAW_ARAPAIMA = ITEMS.register("raw_arapaima",
            ()-> new Item(new Item.Properties().food(ModFoods.RAWCARP)));

    public static final DeferredItem<Item> RAW_PIRANHA = ITEMS.register("raw_piranha",
            ()-> new Item(new Item.Properties().food(ModFoods.RAWCATFISH)));

    public static final DeferredItem<Item> RAW_EEL = ITEMS.register("raw_eel",
            ()-> new Item(new Item.Properties().food(ModFoods.RAWPIKE)));

    public static final DeferredItem<Item> COOKED_ARAPAIMA = ITEMS.register("cooked_arapaima",
            ()-> new Item(new Item.Properties().food(ModFoods.COOKEDCARP)));

    public static final DeferredItem<Item> COOKED_PIRANHA = ITEMS.register("cooked_piranha",
            ()-> new Item(new Item.Properties().food(ModFoods.COOKEDCATFISH)));

    public static final DeferredItem<Item> COOKED_EEL = ITEMS.register("cooked_eel",
            ()-> new Item(new Item.Properties().food(ModFoods.COOKEDPIKE)));

    //Sea Fish
    public static final DeferredItem<Item> RAW_OCTOPUS = ITEMS.register("raw_octopus",
            ()-> new Item(new Item.Properties().food(ModFoods.RAWCARP)));

    public static final DeferredItem<Item> RAW_TUNA = ITEMS.register("raw_tuna",
            ()-> new Item(new Item.Properties().food(ModFoods.RAWCATFISH)));

    public static final DeferredItem<Item> COOKED_OCTOPUS = ITEMS.register("cooked_octopus",
            ()-> new Item(new Item.Properties().food(ModFoods.COOKEDCARP)));

    public static final DeferredItem<Item> COOKED_TUNA = ITEMS.register("cooked_tuna",
            ()-> new Item(new Item.Properties().food(ModFoods.COOKEDCATFISH)));


    //Baits
    public static final DeferredItem<Item> CORN_SEEDS = ITEMS.register("corn_seeds",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CORN = ITEMS.register("corn",
            ()-> new Item(new Item.Properties().food(ModFoods.CORN)));

    public static final DeferredItem<Item> WORM = ITEMS.register("worm",
            ()-> new Item(new Item.Properties()));

    public static final DeferredItem<Item> SEA_SNAIL = ITEMS.register("sea_snail",
            ()-> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
