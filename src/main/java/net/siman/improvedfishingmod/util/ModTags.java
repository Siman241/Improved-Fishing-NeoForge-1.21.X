package net.siman.improvedfishingmod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.siman.improvedfishingmod.ImprovedFishing;

public class ModTags {
    public static class Blocks {

        private static TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(ImprovedFishing.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> FISHING_EQUIPMENT = createTag("fishing_equipment");

        public static final TagKey<Item> IMPROVED_FISHING_EQUIPMENT = createTag("improved_fishing_equipment");

        public static final TagKey<Item> BAIT = createTag("bait");

        private static TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(ImprovedFishing.MOD_ID, name));
        }
    }
}
