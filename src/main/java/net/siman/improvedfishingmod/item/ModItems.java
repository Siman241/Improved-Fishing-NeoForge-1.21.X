package net.siman.improvedfishingmod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.siman.improvedfishingmod.ImprovedFishing;
import net.siman.improvedfishingmod.item.custom.FishingRod;

import java.util.function.Predicate;

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
            ()-> new FishingRod(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> WOODPOLE = ITEMS.register("woodpole",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> FISHINGHOOK = ITEMS.register("fishinghook",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> FISHINGREEL = ITEMS.register("fishingreel",
            ()-> new Item(new Item.Properties().stacksTo(1)));


    //Food and Baits
    public static final DeferredItem<Item> RAWCARP = ITEMS.register("raw_carp",
            ()-> new Item(new Item.Properties().food(ModFoods.RAWCARP)));

    public static final DeferredItem<Item> RAWCATFISH = ITEMS.register("raw_catfish",
            ()-> new Item(new Item.Properties().food(ModFoods.RAWCATFISH)));

    public static final DeferredItem<Item> RAWPIKE = ITEMS.register("raw_pike",
            ()-> new Item(new Item.Properties().food(ModFoods.RAWPIKE)));

    public static final DeferredItem<Item> COOKEDCARP = ITEMS.register("cooked_carp",
            ()-> new Item(new Item.Properties().food(ModFoods.COOKEDCARP)));

    public static final DeferredItem<Item> COOKEDCATFISH = ITEMS.register("cooked_catfish",
            ()-> new Item(new Item.Properties().food(ModFoods.COOKEDATFISH)));

    public static final DeferredItem<Item> COOKEDPIKE = ITEMS.register("cooked_pike",
            ()-> new Item(new Item.Properties().food(ModFoods.COOKEDPIKE)));

    public static final DeferredItem<Item> WORM = ITEMS.register("worm",
            ()-> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
