package net.siman.improvedfishingmod.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;
import net.neoforged.neoforge.event.EventHooks;

import java.util.Random;
import java.util.function.Predicate;

public class FishingRod extends FishingRodItem {

    public FishingRod(Properties properties) {
        super(properties);
    }

    Player player1;
    int time = 0;

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        player1 = player;
        ItemStack itemstack = player.getItemInHand(hand);
        if(time == 2) {
            System.out.println("Stopped");
            time=-1;
            for(int i=0;i<101;i++){
                //System.out.println(player1.getInventory().getItem(i).getItem().toString());
                if(player1.getSlot(i).get().getItem().toString().equals("improvedfishingmod:worm")){
                    Random rand = new Random();
                    int n = rand.nextInt(2);
                    if(n==1) {
                        player1.getSlot(i).get().setCount(player1.getSlot(i).get().getCount() - 1);
                        //player1.getSlot(i).set(player1.getSlot(i).get().setCount(player1.getSlot(i).get().getCount()-1));
                        System.out.println("Removed worm");
                    }
                }
            }
        } else time++;
        return super.use(level, player, hand);
    }
    @Override
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        //System.out.println(event.getEntity().toString());
        if (player1.getInventory().hasAnyMatching(new Predicate<ItemStack>() {
            @Override
            public boolean test(ItemStack itemStack) {
                if (itemStack.getItem().toString().equals("improvedfishingmod:fishingline")) return true;
                return false;
            }
        })) if (player1.getInventory().hasAnyMatching(new Predicate<ItemStack>() {
            @Override
            public boolean test(ItemStack itemStack) {
                if (itemStack.getItem().toString().equals("improvedfishingmod:fishinghook")) return true;
                return false;
            }
        })) if (player1.getInventory().hasAnyMatching(new Predicate<ItemStack>() {
            @Override
            public boolean test(ItemStack itemStack) {
                //check if it has bait
                if (itemStack.getItem().toString().equals("improvedfishingmod:worm")) return true;
                return false;
            }
        })) {
            //System.out.println("Can Fish");
            return ItemAbilities.DEFAULT_FISHING_ROD_ACTIONS.contains(itemAbility);

            //return super.canPerformAction(stack, itemAbility);
                }

        return false;
    }


    /*
    Player player1;
    int time = 0;

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
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

        player1 = player;
        if(time == 2) {
            System.out.println("Stopped");
            time=-1;
            for(int i=0;i<101;i++){
                //System.out.println(player1.getInventory().getItem(i).getItem().toString());
                if(player1.getSlot(i).get().getItem().toString().equals("improvedfishingmod:worm")){
                    Random rand = new Random();
                    int n = rand.nextInt(2);
                    if(n==1) {
                        player1.getSlot(i).get().setCount(player1.getSlot(i).get().getCount() - 1);
                        //player1.getSlot(i).set(player1.getSlot(i).get().setCount(player1.getSlot(i).get().getCount()-1));
                        System.out.println("Removed worm");
                    }
                }
            }
        } else time++;

        return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
    }

    public int getEnchantmentValue() {
        return 1;
    }
    /*
    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        return ItemAbilities.DEFAULT_FISHING_ROD_ACTIONS.contains(itemAbility);
    }



    public boolean canPerformAction(ItemStack stack, ItemAbility itemAbility) {
        //System.out.println(event.getEntity().toString());
        if (player1.getInventory().hasAnyMatching(new Predicate<ItemStack>() {
            @Override
            public boolean test(ItemStack itemStack) {
                if (itemStack.getItem().toString().equals("improvedfishingmod:fishingline")) return true;
                return false;
            }
        })) if (player1.getInventory().hasAnyMatching(new Predicate<ItemStack>() {
            @Override
            public boolean test(ItemStack itemStack) {
                if (itemStack.getItem().toString().equals("improvedfishingmod:fishinghook")) return true;
                return false;
            }
        })) if (player1.getInventory().hasAnyMatching(new Predicate<ItemStack>() {
            @Override
            public boolean test(ItemStack itemStack) {
                //check if it has bait
                if (itemStack.getItem().toString().equals("improvedfishingmod:worm")) return true;
                return false;
            }
        })) {
            //System.out.println("Can Fish");
            return ItemAbilities.DEFAULT_FISHING_ROD_ACTIONS.contains(itemAbility);

            //return super.canPerformAction(stack, itemAbility);
        }

        return false;
    }


     */
}
