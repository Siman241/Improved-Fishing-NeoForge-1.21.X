package net.siman.improvedfishingmod.data.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.siman.improvedfishingmod.ImprovedFishing;
import net.siman.improvedfishingmod.item.ModItems;
import net.siman.improvedfishingmod.loot.AddItemModifier;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, ImprovedFishing.MOD_ID);
    }

    @Override
    protected void start() {
        add("custom_fish", new AddItemModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(ResourceLocation.parse("gameplay/fishing/fish")).build(),
                LootItemRandomChanceCondition.randomChance(0.8f).build()},
                ModItems.COMMONCARP.get()));

        add("test", new AddItemModifier(new LootItemCondition[]{
                LootTableIdCondition.builder(ResourceLocation.parse("entities/iron_golem")).build(),
                /*
                LootItemRandomChanceCondition.randomChance(0.8f).build()
                */
                },
                ModItems.COMMONCARP.get()));
    }
}
