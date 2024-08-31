package net.siman.improvedfishingmod;

import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.commands.arguments.item.ItemParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.NoteBlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.items.wrapper.EntityEquipmentInvWrapper;
import net.siman.improvedfishingmod.data.DataGenerators;
import net.siman.improvedfishingmod.init.LootModifierInit;
import net.siman.improvedfishingmod.item.ModCreativeModeTabs;
import net.siman.improvedfishingmod.item.ModItems;
import net.siman.improvedfishingmod.item.custom.FishingRod;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

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
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Random;
import java.util.logging.ErrorManager;
import java.util.random.RandomGenerator;

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

        LootModifierInit.LOOT_MODIFIERS.register(modEventBus);
        modEventBus.addListener(DataGenerators::gatherData);

        // Register the item to a creative tab
        //modEventBus.addListener(this::addCreative);
        NeoForge.EVENT_BUS.addListener(this::onPlayerPreTick);
        //NeoForge.EVENT_BUS.addListener(this::checkCasting);
        //NeoForge.EVENT_BUS.addListener(this::onClientSetup);

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

        }
    }


    //private boolean f = false
    private void onPlayerPreTick(PlayerTickEvent.Pre event){
        Player ply = event.getEntity();
        /*
        for(int i=0;i<1200;i++) {
            if(MinecartItem.byId(i).getDefaultInstance().getItem().toString().equals("minecraft:fishing_rod")) System.out.println(i);
            //ply.addItem(MinecartItem.byId(i).getDefaultInstance());
        }
        fishing rod 931
        string 850
         */
        //System.out.println(ply.getOffhandItem().getItem().toString().equals("minecraft:fishing_rod"));
        if(ply.getItemInHand(ply.getUsedItemHand()).getItem().toString().equals("minecraft:fishing_rod")){
            ply.setItemInHand(ply.getUsedItemHand(),ModItems.FISHINGROD.toStack());
            /* to find what ID String is
            for(int i=0;i<1200;i++) {
                if(MinecartItem.byId(i).getDefaultInstance().getItem().toString().equals("minecraft:string")) System.out.println(i);
                //ply.addItem(MinecartItem.byId(i).getDefaultInstance());
            }
            */
            ply.addItem(MinecartItem.byId(850).getDefaultInstance());
            ply.addItem(MinecartItem.byId(850).getDefaultInstance());
            ply.playSound(SoundEvents.CRAFTER_CRAFT);
        }
        else if(ply.getOffhandItem().getItem().toString().equals("minecraft:fishing_rod")){
            for(int i=99;i<100;i++){
                ply.getSlot(i).set(ModItems.FISHINGROD.toStack());
            }
            ply.addItem(MinecartItem.byId(850).getDefaultInstance());
            ply.addItem(MinecartItem.byId(850).getDefaultInstance());
            ply.playSound(SoundEvents.CRAFTER_CRAFT);

        }


    }
}
