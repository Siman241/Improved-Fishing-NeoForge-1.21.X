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
            })).build());

    public static final Supplier<CreativeModeTab> IMPROVED_FISHING_FISH_AND_BAIT_TAB = CREATIVE_MODE_TAB.register("improved_fishing_fish_and_bait_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.RAWPIKE.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(ImprovedFishing.MOD_ID, "improved_fishing_items_tab"))
                    .title(Component.translatable("creativetab.improvedfishingmod.fish_and_bait"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.RAWCARP);
                        output.accept(ModItems.RAWCATFISH);
                        output.accept(ModItems.RAWPIKE);
                        output.accept(ModItems.COOKEDCARP);
                        output.accept(ModItems.COOKEDCATFISH);
                        output.accept(ModItems.COOKEDPIKE);
                        output.accept(ModItems.WORM);
                    })).build());

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
