package lum.boundless_realms.item;

import lum.boundless_realms.entity.LunchTicketProjectileEntity;
import lum.boundless_realms.entity.ModEntities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class LunchTicketItem extends Item {

    public LunchTicketItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return ActionResult.CONSUME;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (!(user instanceof PlayerEntity player)) {
            return false;
        }

        int useTicks = this.getMaxUseTime(stack, user) - remainingUseTicks;
        float power = getPullProgress(useTicks);

        if (power < 0.1F) {
            return false;
        }

        if (!world.isClient()) {
            int cost = 44;

            int money = countMoney(player);
            if (money < cost) {
                player.sendMessage(Text.translatable("not_enough_money"), true);
                return false;
            }

            removeMoney(player, cost);

            LunchTicketProjectileEntity projectile =
                    new LunchTicketProjectileEntity(ModEntities.LUNCH_TICKET_PROJECTILE, world);

            projectile.setOwner(player);
            projectile.setPosition(player.getX(), player.getEyeY() - 0.1, player.getZ());
            projectile.setVelocity(
                    player,
                    player.getPitch(),
                    player.getYaw(),
                    0.0F,
                    power * 3.0F,
                    1.0F
            );

            world.spawnEntity(projectile);

            player.sendMessage(Text.translatable("spent_money", cost), true);
        }

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                SoundEvents.ENTITY_SNOWBALL_THROW,
                SoundCategory.PLAYERS,
                0.5F,
                1.0F
        );

        return true;
    }

    private static float getPullProgress(int useTicks) {
        float f = (float) useTicks / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;

        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    private int countMoney(PlayerEntity player) {
        int total = 0;

        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);

            if (stack.getItem() == ModItems.MONEY) {
                total += stack.getCount();
            }
        }

        return total;
    }

    private void removeMoney(PlayerEntity player, int amount) {
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);

            if (stack.getItem() == ModItems.MONEY) {
                int remove = Math.min(amount, stack.getCount());
                stack.decrement(remove);
                amount -= remove;

                if (amount <= 0) {
                    break;
                }
            }
        }
    }
}