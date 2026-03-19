package lum.boundless_realms.entity;

import lum.boundless_realms.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.entity.damage.DamageSource;

public class LunchTicketEntity extends ThrownItemEntity {

    public LunchTicketEntity(EntityType<? extends LunchTicketEntity> entityType, World world) {
        super(entityType, world);
    }

    public LunchTicketEntity(World world, LivingEntity owner) {
        super(EntityType.SNOWBALL, world); // Using snowball as base
        this.setOwner(owner);
        this.setPosition(owner.getX(), owner.getEyeY() - 0.1, owner.getZ());
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.LUNCH_TICKET;
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity target = entityHitResult.getEntity();

        DamageSource source = this.getDamageSources().thrown(this, this.getOwner());
        if (!this.getEntityWorld().isClient()) {
            target.damage(
                    (net.minecraft.server.world.ServerWorld) this.getEntityWorld(),
                    source,
                    44.0f
            );
        }
    }
}
