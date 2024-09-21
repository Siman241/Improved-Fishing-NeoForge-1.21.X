package net.siman.improvedfishingmod;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.siman.improvedfishingmod.block.ModBlocks;

import net.siman.improvedfishingmod.component.ModDataComponents;
import net.siman.improvedfishingmod.item.ModCreativeModeTabs;
import net.siman.improvedfishingmod.item.ModItems;
import net.siman.improvedfishingmod.loot.ModLootModifiers;
import org.slf4j.Logger;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.LoggerFactory;


@Mod(ImprovedFishing.MOD_ID)
public class ImprovedFishing {

    public static final String MOD_ID = "improvedfishingmod";

    public static final Logger logger = LoggerFactory.getLogger(ImprovedFishing.class);

    public ImprovedFishing(IEventBus modEventBus, ModContainer modContainer) {

        NeoForge.EVENT_BUS.register(this);
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModDataComponents.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIERS.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(this::onPlayerPreTick);
        NeoForge.EVENT_BUS.addListener(this::onPlayerRightClickBlock);
        NeoForge.EVENT_BUS.addListener(this::onPlayerRightClickItem);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> {
                //Model Texture Override Wood Rod
                ItemProperties.register(

                        ModItems.WOODROD.get(),

                        ResourceLocation.fromNamespaceAndPath("improvedfishingmod", "wood_cast"),

                        (stack, level, player, seed) -> {
                            if(player != null  && level != null  && player.isHolding(ModItems.WOODROD.get()) && Boolean.TRUE.equals(stack.get(ModDataComponents.CASTED))){
                                return 1;
                            }
                            return 0;
                        }
                );
                //Model Texture Override Bamboo Rod
                ItemProperties.register(

                        ModItems.BAMBOO_ROD.get(),

                        ResourceLocation.fromNamespaceAndPath("improvedfishingmod", "bamboo_cast"),

                        (stack, level, player, seed) -> {
                            if(player != null  && level != null  && player.isHolding(ModItems.BAMBOO_ROD.get()) && Boolean.TRUE.equals(stack.get(ModDataComponents.CASTED))){
                                return 1;
                            }
                            return 0;
                        }
                );
            });

        }
    }
    private void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event){
        //Planting Corn Seeds
        if(event.getItemStack().getItem().toString().equals("improvedfishingmod:corn_seeds")){
            if(event.getLevel().getBlockState(event.getPos()).getBlock().equals(Blocks.FARMLAND)){
                event.getEntity().swing(event.getHand());
                event.getEntity().playSound(SoundEvents.CROP_PLANTED);
                event.getItemStack().setCount(event.getItemStack().getCount()-1);
                event.getEntity().setItemInHand(event.getHand(),event.getItemStack());
                event.getLevel().setBlock(event.getPos().above(1), ModBlocks.CORN_CROP.get().defaultBlockState(), 4);
            }
        }
    }

    private void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event){
        //Damaging Fishing Rods
        if(event.getItemStack().getItem().equals(ModItems.BAMBOO_ROD.get()) || event.getItemStack().getItem().equals(ModItems.WOODROD.get())) {
            if(event.getEntity().getSlot(99).get().getItem().equals(ModItems.CORN.get()) ||
                    event.getEntity().getSlot(99).get().getItem().equals(ModItems.WORM.get()) ||
                    event.getEntity().getSlot(99).get().getItem().equals(ModItems.SEA_SNAIL.get()) ||
                    event.getEntity().getSlot(99).get().getItem().equals(Items.COD) ||
                    event.getEntity().getSlot(99).get().getItem().equals(Items.KELP)) {
                event.getItemStack().setDamageValue(event.getItemStack().getItem().getDamage(event.getItemStack())+1);
            }
        }

    }

    boolean holdingWood = false;
    boolean holdingBamboo = false;
    //private boolean f = false
    private void onPlayerPreTick(PlayerTickEvent.Pre event){
        Player ply = event.getEntity();

        //Removal of Vanilla Fishing Rod
        for(int i=0;i<100;i++){
            if(ply.getSlot(i).get().getItem().toString().equals("minecraft:fishing_rod")){
                ply.getSlot(i).set(ModItems.WOODPOLE.toStack());
                ply.playSound(SoundEvents.CRAFTER_CRAFT);
                ply.addItem(Items.STRING.getDefaultInstance());
                ply.addItem(Items.STRING.getDefaultInstance());
            }

        }

        //if player is holding or stopped holding Bamboo Rod
        //Used for texture model
        if(ply.getItemInHand(ply.getUsedItemHand()).getItem().equals(ModItems.BAMBOO_ROD.get())) {
            holdingBamboo = true;
        }
        else if((!ply.getItemInHand(ply.getUsedItemHand()).getItem().equals(ModItems.BAMBOO_ROD.get())) && holdingBamboo){
            System.out.println("Stopped holding Bamboo Rod");
            for(int i=0;i<100;i++){
                if(ply.getSlot(i).get().getItem().equals(ModItems.WOODROD.get())){
                    ply.getSlot(i).get().set(ModDataComponents.CASTED, false);
                }
                if(ply.getSlot(i).get().getItem().equals(ModItems.BAMBOO_ROD.get())){
                    ply.getSlot(i).get().set(ModDataComponents.CASTED, false);
                }
            }
            holdingBamboo = false;
        }

        //if player is holding or stopped holding Wood Rod
        //Used for texture model
        if(ply.getItemInHand(ply.getUsedItemHand()).getItem().equals(ModItems.WOODROD.get())) {
            holdingWood = true;
        }
        else if(!ply.getItemInHand(ply.getUsedItemHand()).getItem().equals(ModItems.WOODROD.get()) && holdingWood){
            System.out.println("Stopped holding Wood Rod");
            for(int i=0;i<100;i++){
                if(ply.getSlot(i).get().getItem().equals(ModItems.WOODROD.get())){
                    ply.getSlot(i).get().set(ModDataComponents.CASTED, false);
                }
                if(ply.getSlot(i).get().getItem().equals(ModItems.BAMBOO_ROD.get())){
                    ply.getSlot(i).get().set(ModDataComponents.CASTED, false);
                }
            }
            holdingWood = false;
        }

        //Checking if player can cast rod
        if(ply.getItemInHand(ply.getUsedItemHand()).getItem().equals(ModItems.WOODROD.get()) ||
                ply.getItemInHand(ply.getUsedItemHand()).getItem().equals(ModItems.BAMBOO_ROD.get())) {
            if((ply.getSlot(99).get().getItem().equals(ModItems.WORM.get()) ||
                    ply.getSlot(99).get().getItem().equals(ModItems.CORN.get()) ||
                    ply.getSlot(99).get().getItem().equals(Items.COD) ||
                    ply.getSlot(99).get().getItem().equals(ModItems.SEA_SNAIL.get()) ||
                    ply.getSlot(99).get().getItem().equals(Items.KELP))) {
                ply.getItemInHand(ply.getUsedItemHand()).set(ModDataComponents.CANCAST, true);
            }
            else {
                ply.getItemInHand(ply.getUsedItemHand()).set(ModDataComponents.CANCAST, false);
            }
        }
        else ply.getItemInHand(ply.getUsedItemHand()).set(ModDataComponents.CANCAST, false);



    }
}
