package net.siman.improvedfishingmod;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.siman.improvedfishingmod.block.ModBlocks;

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

import java.util.function.Predicate;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ImprovedFishing.MOD_ID)
public class ImprovedFishing {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "improvedfishingmod";
    // Directly reference a slf4j logger
    public static final Logger logger = LoggerFactory.getLogger(ImprovedFishing.class);
    //public static ErrorManager LOGGER;

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public ImprovedFishing(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        //modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIERS.register(modEventBus);
        //ModLootModifiers.register(modEventBus);
        // Register the item to a creative tab
        //modEventBus.addListener(this::addCreative);
        NeoForge.EVENT_BUS.addListener(this::onPlayerPreTick);
        NeoForge.EVENT_BUS.addListener(this::onPlayerRightClickBlock);
        NeoForge.EVENT_BUS.addListener(this::onPlayerRightClickItem);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {


    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }
    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            event.enqueueWork(() -> { // ItemProperties#register is not threadsafe, so we need to call it on the main thread
                ItemProperties.register(
                        // The item to apply the property to.
                        ModItems.WOODROD.get(),
                        // The id of the property.
                        ResourceLocation.fromNamespaceAndPath("improvedfishingmod", "wood_cast"),
                        // A reference to a method that calculates the override value.
                        // Parameters are the used item stack, the level context, the player using the item,
                        // and a random seed you can use.
                        (stack, level, player, seed) -> {
                            if(player != null  && level != null  && player.isHolding(ModItems.WOODROD.get()) && stack.getEnchantmentValue() == 2){
                                return 1;
                            }
                            return 0;
                            //return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;
                        }
                );
                ItemProperties.register(
                        // The item to apply the property to.
                        ModItems.BAMBOO_ROD.get(),
                        // The id of the property.
                        ResourceLocation.fromNamespaceAndPath("improvedfishingmod", "bamboo_cast"),
                        // A reference to a method that calculates the override value.
                        // Parameters are the used item stack, the level context, the player using the item,
                        // and a random seed you can use.
                        (stack, level, player, seed) -> {
                            if(player != null  && level != null  && player.isHolding(ModItems.BAMBOO_ROD.get()) && stack.getEnchantmentValue() == 2){
                                return 1;
                            }
                            return 0;
                            //return player != null && player.isUsingItem() && player.getUseItem() == stack ? 1.0F : 0.0F;
                        }
                );
            });

        }
    }
    private void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event){
        //System.out.println(event.getItemStack().getItem());
        if(event.getItemStack().getItem().toString().equals("improvedfishingmod:corn_seeds")){
            if(event.getLevel().getBlockState(event.getPos()).getBlock().equals(Blocks.FARMLAND)){
                event.getEntity().swing(event.getHand());
                event.getEntity().playSound(SoundEvents.CROP_PLANTED);
                event.getItemStack().setCount(event.getItemStack().getCount()-1);
                event.getEntity().setItemInHand(event.getHand(),event.getItemStack());
                event.getLevel().setBlock(event.getPos().above(1), ModBlocks.CORN_CROP.get().defaultBlockState(), 4);
            }
        }
        //event.getItemStack().onStopUsing(event.getEntity(), event.getItemStack().getItem().getDamage(event.getItemStack()));
    }
    int damage = 0;
    int time = 0;
    private void onPlayerRightClickItem(PlayerInteractEvent.RightClickItem event){
        //System.out.println(event.getItemStack().getItem());
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

    boolean holding = false;
    //private boolean f = false
    private void onPlayerPreTick(PlayerTickEvent.Pre event){
        Player ply = event.getEntity();

        for(int i=0;i<100;i++){
            if(ply.getSlot(i).get().getItem().toString().equals("minecraft:fishing_rod")){
                ply.getSlot(i).set(ModItems.WOODPOLE.toStack());
                ply.playSound(SoundEvents.CRAFTER_CRAFT);
                ply.addItem(Items.STRING.getDefaultInstance());
                ply.addItem(Items.STRING.getDefaultInstance());
            }

        }
        if(ply.getItemInHand(ply.getUsedItemHand()).getItem().equals(ModItems.WOODROD.get())
                || ply.getItemInHand(ply.getUsedItemHand()).getItem().equals(ModItems.BAMBOO_ROD.get())) {
            holding = true;
        }
        else if((!ply.getItemInHand(ply.getUsedItemHand()).getItem().equals(ModItems.WOODROD.get()) ||
                !ply.getItemInHand(ply.getUsedItemHand()).getItem().equals(ModItems.BAMBOO_ROD.get())) && holding){
            System.out.println("is not holding");
            for(int i=0;i<100;i++){
                if(ply.getSlot(i).get().getItem().equals(ModItems.WOODROD.get())){
                    ply.getSlot(i).get().getItem().onStopUsing(ply.getSlot(i).get(), ply, 1);
                }
                if(ply.getSlot(i).get().getItem().equals(ModItems.BAMBOO_ROD.get())){
                    ply.getSlot(i).get().getItem().onStopUsing(ply.getSlot(i).get(), ply, 1);
                }

            }
            holding = false;
        }



    }
}
