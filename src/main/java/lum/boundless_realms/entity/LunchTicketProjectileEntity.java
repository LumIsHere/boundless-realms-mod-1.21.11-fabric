package lum.boundless_realms.entity;

import lum.boundless_realms.item.ModItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;

public class LunchTicketProjectileEntity extends ThrownItemEntity {

    public LunchTicketProjectileEntity(EntityType<? extends LunchTicketProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.LUNCH_TICKET;
    }

    @Override
    protected void onEntityHit(EntityHitResult hitResult) {
        super.onEntityHit(hitResult);

        World world = this.getEntityWorld();
        if (!(world instanceof ServerWorld serverWorld)) {
            return;
        }

        Entity target = hitResult.getEntity();

        if (target instanceof LivingEntity living) {
            living.damage(serverWorld, this.getDamageSources().thrown(this, this.getOwner()), 88.0F);
        }

        this.discard();
    }
}