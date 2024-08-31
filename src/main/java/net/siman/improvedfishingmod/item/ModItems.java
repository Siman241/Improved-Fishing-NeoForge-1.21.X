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
    public static final DeferredItem<Item> FISHINGLINE = ITEMS.register("fishingline",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> FISHINGROD = ITEMS.register("fishingrod",
            ()-> new FishingRod(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> FISHINGHOOK = ITEMS.register("fishinghook",
            ()-> new Item(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> COMMONCARP = ITEMS.register("commoncarp",
            ()-> new Item(new Item.Properties().stacksTo(16).food(ModFoods.COMMONCARP)));

    public static final DeferredItem<Item> WORM = ITEMS.register("worm",
            ()-> new Item(new Item.Properties()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
