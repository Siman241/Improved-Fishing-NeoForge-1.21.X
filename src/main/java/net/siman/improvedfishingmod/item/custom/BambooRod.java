package net.siman.improvedfishingmod.item.custom;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.event.EventHooks;
import net.siman.improvedfishingmod.component.ModDataComponents;
import net.siman.improvedfishingmod.item.ModItems;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

public class BambooRod extends FishingRodItem {

    public BambooRod(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if(Boolean.TRUE.equals(itemstack.get(ModDataComponents.CANCAST))) {
            if (player.fishing != null) {
                if (!level.isClientSide) {
                    itemstack.set(ModDataComponents.CASTED, false);
                    Random rand = new Random();
                    int n = rand.nextInt(5);
                    if (n == 1) {
                        player.getSlot(99).get().setCount(player.getSlot(99).get().getCount() - 1);
                        System.out.println("Removed bait");
                    }
                    int i = player.fishing.retrieve(itemstack);
                    ItemStack original = itemstack.copy();
                    itemstack.hurtAndBreak(i, player, LivingEntity.getSlotForHand(hand));
                    if (itemstack.isEmpty()) {
                        EventHooks.onPlayerDestroyItem(player, original, hand);
                    }
                }
                level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
                player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
            } else {
                level.playSound((Player) null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
                if (level instanceof ServerLevel) {
                    itemstack.set(ModDataComponents.CASTED, true);
                    ServerLevel serverlevel = (ServerLevel) level;
                    int j = (int) (EnchantmentHelper.getFishingTimeReduction(serverlevel, itemstack, player) * 20.0F);
                    int k = EnchantmentHelper.getFishingLuckBonus(serverlevel, itemstack, player);
                    level.addFreshEntity(new FishingHook(player, level, k, j));
                }
                player.awardStat(Stats.ITEM_USED.get(this));
                player.gameEvent(GameEvent.ITEM_INTERACT_START);
            }
            return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        }
        return InteractionResultHolder.fail(itemstack);

    }

    @Override
    public boolean supportsEnchantment(ItemStack stack, Holder<Enchantment> enchantment) {
        System.out.println("Checking");
        System.out.println(stack.getItem());
        if(stack.getItem().equals(this)){
            if(enchantment.getRegisteredName().equals("minecraft:unbreaking")) return true;
            if(enchantment.getRegisteredName().equals("minecraft:mending")) return true;
            if(enchantment.getRegisteredName().equals("minecraft:lure")) return true;
            if(enchantment.getRegisteredName().equals("minecraft:luck_of_the_sea")) return true;
            if(enchantment.getRegisteredName().equals("minecraft:vanishing_curse")) return true;
        }
        return super.supportsEnchantment(stack, enchantment);
    }
    //Adding Tooltip Text
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if(Boolean.FALSE.equals(stack.get(ModDataComponents.CANCAST))) {
            tooltipComponents.add(Component.literal("Required bait"));
            tooltipComponents.add(Component.literal("Place bait in you offhand"));
        }
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
