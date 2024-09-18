package net.siman.improvedfishingmod.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Random;
import java.util.function.Supplier;

public class SetCodModifier extends LootModifier {

    public static Supplier<MapCodec<SetCodModifier>> CODEC_SUPPLIER = Suppliers.memoize(() -> RecordCodecBuilder
            .mapCodec(instance -> SetCodModifier.codecStart(instance)
                    .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item1")
                            .forGetter(setCornModifierInstance -> setCornModifierInstance.item1))
                    .and(BuiltInRegistries.ITEM.byNameCodec().fieldOf("item2")
                            .forGetter(setCornModifierInstance -> setCornModifierInstance.item2))
                    .apply(instance, SetCodModifier::new)));

    private final Item item1;
    private final Item item2;


    public SetCodModifier(LootItemCondition[] conditionsIn, Item item1, Item item2) {
        super(conditionsIn);
        this.item1 = item1;
        this.item2 = item2;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        for (LootItemCondition condition : this.conditions) {
            if (!condition.test(context)) {
                return generatedLoot;
            }
        }
        Random rand = new Random();
        if(rand.nextInt(8)==1) return new ObjectArrayList<ItemStack>(Collections.singleton(new ItemStack(this.item1)));
        else if(rand.nextInt(3)==2) return new ObjectArrayList<ItemStack>(Collections.singleton(new ItemStack(this.item2)));
        return new ObjectArrayList<ItemStack>(Collections.singleton(new ItemStack(Blocks.AIR)));
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC_SUPPLIER.get();
    }
}