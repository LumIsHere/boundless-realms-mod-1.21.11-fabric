package lum.boundless_realms.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class WitherFuryItem extends Item {
    private static final double MAX_DISTANCE = 1200.0;
    private static final double SKULL_HEIGHT_OFFSET = 10.0;

    public WitherFuryItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            // Raycast where player is looking
            BlockHitResult hitResult = raycast(world, user, RaycastContext.FluidHandling.NONE, MAX_DISTANCE);

            Vec3d targetPos;
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                targetPos = hitResult.getPos();
            } else {
                targetPos = user.getEyePos().add(user.getRotationVector().multiply(MAX_DISTANCE));
            }

            Vec3d spawnPos = new Vec3d(targetPos.x, targetPos.y + SKULL_HEIGHT_OFFSET, targetPos.z);

            // Create the wither skull entity
            WitherSkullEntity witherSkull = new WitherSkullEntity(EntityType.WITHER_SKULL, world);
            witherSkull.setOwner(user); // link skull to player
            witherSkull.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

            // Set velocity from spawn to target
            Vec3d velocity = targetPos.subtract(spawnPos).normalize().multiply(2.5); // faster speed
            witherSkull.setVelocity(velocity);

            witherSkull.setCharged(true);
            world.spawnEntity(witherSkull);

            world.playSound(null, user.getX(), user.getY(), user.getZ(),
                    SoundEvents.ENTITY_WITHER_SHOOT, SoundCategory.PLAYERS,
                    1.0f, 1.0f);
        }

        return ActionResult.SUCCESS;
    }

    private BlockHitResult raycast(World world, PlayerEntity player, RaycastContext.FluidHandling fluidHandling, double maxDistance) {
        Vec3d startPos = player.getEyePos();
        Vec3d endPos = startPos.add(player.getRotationVector().multiply(maxDistance));

        RaycastContext context = new RaycastContext(
                startPos,
                endPos,
                RaycastContext.ShapeType.OUTLINE,
                fluidHandling,
                player
        );

        return world.raycast(context);
    }
}