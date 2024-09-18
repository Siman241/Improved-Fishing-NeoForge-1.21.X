package net.siman.improvedfishingmod.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.siman.improvedfishingmod.ImprovedFishing;
import net.siman.improvedfishingmod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ImprovedFishing.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.FISHINGHOOK.get());
        basicItem(ModItems.FISHINGLINE.get());
        basicItem(ModItems.FISHINGREEL.get());
        basicItem(ModItems.WOODPOLE.get());

        basicItem(ModItems.DIAMOND_TIPPED_FISHING_HOOK.get());
        basicItem(ModItems.STRENGTHENED_FISHING_LINE.get());
        basicItem(ModItems.AUTOMATIC_FISHING_REEL.get());
        basicItem(ModItems.BAMBOO_POLE.get());

        basicItem(ModItems.WORM.get());
        basicItem(ModItems.CORN.get());
        basicItem(ModItems.CORN_SEEDS.get());
        basicItem(ModItems.SEA_SNAIL.get());

        basicItem(ModItems.RAWCARP.get());
        basicItem(ModItems.RAWPIKE.get());
        basicItem(ModItems.RAWCATFISH.get());
        basicItem(ModItems.COOKEDCATFISH.get());
        basicItem(ModItems.COOKEDCARP.get());
        basicItem(ModItems.COOKEDPIKE.get());

        basicItem(ModItems.COOKED_ARAPAIMA.get());
        basicItem(ModItems.COOKED_PIRANHA.get());
        basicItem(ModItems.COOKED_EEL.get());
        basicItem(ModItems.RAW_ARAPAIMA.get());
        basicItem(ModItems.RAW_PIRANHA.get());
        basicItem(ModItems.RAW_EEL.get());

        basicItem(ModItems.RAW_OCTOPUS.get());
        basicItem(ModItems.RAW_TUNA.get());
        basicItem(ModItems.COOKED_TUNA.get());
        basicItem(ModItems.COOKED_OCTOPUS.get());

    }
}
