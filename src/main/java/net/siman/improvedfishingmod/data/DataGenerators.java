package net.siman.improvedfishingmod.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.siman.improvedfishingmod.ImprovedFishing;
import net.siman.improvedfishingmod.data.loot.ModGlobalLootModifiersProvider;



//@EventBusSubscriber(modid = ImprovedFishing.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    //@SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        try{
            DataGenerator generator = event.getGenerator();
            PackOutput output = generator.getPackOutput();
            ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
            //generator.addProvider(true, new ModEnLangProvider(output, event.getLookupProvider()));
            generator.addProvider(true, new ModGlobalLootModifiersProvider(output, event.getLookupProvider()));

        } catch (RuntimeException e){
            //System.out.println("Failed to gather data");
            ImprovedFishing.logger.error("Failed to gather data",e);
        }
    }
}
