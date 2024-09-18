package net.siman.improvedfishingmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.siman.improvedfishingmod.ImprovedFishing;
import net.siman.improvedfishingmod.item.ModItems;
import net.siman.improvedfishingmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, ImprovedFishing.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ItemTags.FISHES)
                .add(ModItems.COOKEDCARP.get())
                .add(ModItems.COOKEDCATFISH.get())
                .add(ModItems.COOKEDPIKE.get())
                .add(ModItems.RAWPIKE.get())
                .add(ModItems.RAWCARP.get())
                .add(ModItems.RAWCATFISH.get())
                .add(ModItems.RAW_ARAPAIMA.get())
                .add(ModItems.RAW_PIRANHA.get())
                .add(ModItems.RAW_EEL.get())
                .add(ModItems.COOKED_ARAPAIMA.get())
                .add(ModItems.COOKED_PIRANHA.get())
                .add(ModItems.COOKED_EEL.get())
                .add(ModItems.RAW_OCTOPUS.get())
                .add(ModItems.COOKED_OCTOPUS.get())
                .add(ModItems.COOKED_TUNA.get())
                .add(ModItems.RAW_TUNA.get());

        tag(ModTags.Items.FISHING_EQUIPMENT)
                .add(ModItems.WOODPOLE.get())
                .add(ModItems.WOODROD.get())
                .add(ModItems.FISHINGREEL.get())
                .add(ModItems.FISHINGLINE.get())
                .add(ModItems.FISHINGHOOK.get());

        tag(ModTags.Items.IMPROVED_FISHING_EQUIPMENT)
                .add(ModItems.BAMBOO_ROD.get())
                .add(ModItems.BAMBOO_POLE.get())
                .add(ModItems.STRENGTHENED_FISHING_LINE.get())
                .add(ModItems.AUTOMATIC_FISHING_REEL.get())
                .add(ModItems.DIAMOND_TIPPED_FISHING_HOOK.get());

        tag(ModTags.Items.BAIT)
                .add(ModItems.SEA_SNAIL.get())
                .add(ModItems.CORN.get())
                .add(ModItems.WORM.get())
                .add(Items.KELP)
                .add(Items.COD);


    }
}
