package lum.boundless_realms.item;

import lum.boundless_realms.entity.LunchTicketEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.consume.UseAction;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;

public class LunchTicketItem extends Item {
    public LunchTicketItem(Settings settings) {
        super(settings);
    }

    // Make it behave like a bow (pulling animation)
    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 72000; // Standard bow duration
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return ActionResult.CONSUME;
    }

    @Override
    public boolean onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof PlayerEntity player) {
            int i = this.getMaxUseTime(stack, user) - remainingUseTicks;
            float pullProgress = getPullProgress(i);

            if (pullProgress >= 0.1f) {
                if (!world.isClient()) {
                    LunchTicketEntity ticket = new LunchTicketEntity(world, player);
                    ticket.setItem(stack);
                    ticket.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, pullProgress * 3.0F, 1.0F);
                    world.spawnEntity(ticket);
                }

                if (!player.getAbilities().creativeMode) {
                    stack.decrement(1);
                }
            }
        }

        return true; // ✅ REQUIRED in 1.21+
    }

    public static float getPullProgress(int useTicks) {
        float f = (float)useTicks / 20.0F; // 20 ticks (1 second) for full power
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }
}