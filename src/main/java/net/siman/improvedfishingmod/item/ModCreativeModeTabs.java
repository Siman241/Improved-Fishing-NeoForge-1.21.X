package net.siman.improvedfishingmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.siman.improvedfishingmod.ImprovedFishing;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ImprovedFishing.MOD_ID);

    public static final Supplier<CreativeModeTab> IMPROVED_FISHING_ITEMS_TAB = CREATIVE_MODE_TAB.register("improved_fishing_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.WOODROD.get()))
            .title(Component.translatable("creativetab.improvedfishingmod.fishing_items"))
            .displayItems(((itemDisplayParameters, output) -> {
                output.accept(ModItems.WOODROD);
                output.accept(ModItems.WOODPOLE);
                output.accept(ModItems.FISHINGLINE);
                output.accept(ModItems.FISHINGHOOK);
                output.accept(ModItems.FISHINGREEL);
                output.accept(ModItems.BAMBOO_POLE);
                output.accept(ModItems.BAMBOO_ROD);
                output.accept(ModItems.STRENGTHENED_FISHING_LINE);
                output.accept(ModItems.DIAMOND_TIPPED_FISHING_HOOK);
                output.accept(ModItems.AUTOMATIC_FISHING_REEL);
            })).build());

    public static final Supplier<CreativeModeTab> IMPROVED_FISHING_FISH_TAB = CREATIVE_MODE_TAB.register("improved_fishing_fish_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAWPIKE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ImprovedFishing.MOD_ID, "improved_fishing_items_tab"))
                    .title(Component.translatable("creativetab.improvedfishingmod.fish"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAWCARP);
                        output.accept(ModItems.RAWCATFISH);
                        output.accept(ModItems.RAWPIKE);
                        output.accept(ModItems.COOKEDCARP);
                        output.accept(ModItems.COOKEDCATFISH);
                        output.accept(ModItems.COOKEDPIKE);
                        output.accept(ModItems.RAW_ARAPAIMA);
                        output.accept(ModItems.RAW_PIRANHA);
                        output.accept(ModItems.RAW_EEL);
                        output.accept(ModItems.COOKED_ARAPAIMA);
                        output.accept(ModItems.COOKED_PIRANHA);
                        output.accept(ModItems.COOKED_EEL);
                        output.accept(ModItems.RAW_TUNA);
                        output.accept(ModItems.COOKED_TUNA);
                        output.accept(ModItems.COOKED_OCTOPUS);
                        output.accept(ModItems.RAW_OCTOPUS);
                    })).build());

    public static final Supplier<CreativeModeTab> IMPROVED_FISHING_FISH_AND_BAIT_TAB = CREATIVE_MODE_TAB.register("improved_fishing_bait_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.WORM.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ImprovedFishing.MOD_ID, "improved_fishing_items_tab"))
                    .title(Component.translatable("creativetab.improvedfishingmod.bait"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.WORM);
                        output.accept(ModItems.CORN_SEEDS);
                        output.accept(ModItems.CORN);
                        output.accept(ModItems.SEA_SNAIL);
                    })).build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
