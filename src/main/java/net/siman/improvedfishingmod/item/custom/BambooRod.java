package net.siman.improvedfishingmod.item.custom;

import net.minecraft.core.Holder;
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
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.event.EventHooks;
import net.siman.improvedfishingmod.item.ModItems;

import java.util.Random;
import java.util.function.Consumer;

public class BambooRod extends FishingRodItem {

    public BambooRod(Properties properties) {
        super(properties);
    }
    int damage = 0;
    Player player1;
    int time = 0;
    boolean fishing = false;

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count){
        System.out.println("Stopped using Bamboo Rod");
        time = 0;
        fishing = false;

        //super.onStopUsing(stack, entity, count);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player1 = player;
        ItemStack itemstack = player.getItemInHand(hand);
        if(time == 1) {
            fishing = true;
        }
        if(time == 2) {
            /*
            if(itemstack.getItem().equals(ModItems.BAMBOO_ROD.get())) {
                System.out.println("Damaged");
                itemstack.setDamageValue(damage+1);
            }

             */
            fishing = false;
            time=-1;

            if((player1.getSlot(99).get().getItem().equals(ModItems.WORM.get()) ||
                    player1.getSlot(99).get().getItem().equals(ModItems.CORN.get()) ||
                    player1.getSlot(99).get().getItem().equals(Items.COD) ||
                    player1.getSlot(99).get().getItem().equals(ModItems.SEA_SNAIL.get()) ||
                    player1.getSlot(99).get().getItem().equals(Items.KELP))) {
                Random rand = new Random();
                int n = rand.nextInt(2);
                if(n==1) {
                    player.getSlot(99).get().setCount(player.getSlot(99).get().getCount() - 1);
                    System.out.println("Removed bait");
                }
            }




        } else time++;

        if (player.fishing != null) {
            if (!level.isClientSide) {
                int i = player.fishing.retrieve(itemstack);
                ItemStack original = itemstack.copy();
                itemstack.hurtAndBreak(i, player, LivingEntity.getSlotForHand(hand));
                if (itemstack.isEmpty()) {
                    EventHooks.onPlayerDestroyItem(player, original, hand);
                }
            }

            level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_RETRIEVE, SoundSource.NEUTRAL, 1.0F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.gameEvent(GameEvent.ITEM_INTERACT_FINISH);
        } else {
            level.playSound((Player)null, player.getX(), player.getY(), player.getZ(), SoundEvents.FISHING_BOBBER_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (level instanceof ServerLevel) {
                ServerLevel serverlevel = (ServerLevel)level;
                int j = (int)(EnchantmentHelper.getFishingTimeReduction(serverlevel, itemstack, player) * 20.0F);
                int k = EnchantmentHelper.getFishingLuckBonus(serverlevel, itemstack, player);
                level.addFreshEntity(new FishingHook(player, level, k, j));
            }

            player.awardStat(Stats.ITEM_USED.get(this));
            player.gameEvent(GameEvent.ITEM_INTERACT_START);
        }

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
        //System.out.println("Fishing -> " + fishing);
        //return super.use(level, player, hand);
    }
    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        if(player1 == null){
            time = 0;
            fishing = false;
            return false;
        }
        if(player1.getOffhandItem().getItem().toString().equals("improvedfishingmod:worm") ||
                player1.getOffhandItem().getItem().toString().equals("improvedfishingmod:corn") ||
                        player1.getOffhandItem().getItem().toString().equals("minecraft:cod")||
                player1.getOffhandItem().getItem().toString().equals("minecraft:kelp")||
                player1.getOffhandItem().getItem().toString().equals("improvedfishingmod:sea_snail")){
            return ItemAbilities.DEFAULT_FISHING_ROD_ACTIONS.contains(itemAbility);
        }
        time = 0;
        fishing = false;
        return false;

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

    public int getEnchantmentValue() {
        if(fishing) return 2;
        return 1;
    }
}
