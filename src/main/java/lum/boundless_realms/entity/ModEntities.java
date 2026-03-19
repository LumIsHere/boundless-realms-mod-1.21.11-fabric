package lum.boundless_realms.entity;

import lum.boundless_realms.BoundlessRealmsMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final Identifier LUNCH_TICKET_PROJECTILE_ID =
            Identifier.of(BoundlessRealmsMod.MOD_ID, "lunch_ticket_projectile");

    public static final RegistryKey<EntityType<?>> LUNCH_TICKET_PROJECTILE_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, LUNCH_TICKET_PROJECTILE_ID);

    public static final EntityType<LunchTicketProjectileEntity> LUNCH_TICKET_PROJECTILE =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    LUNCH_TICKET_PROJECTILE_ID,
                    EntityType.Builder.<LunchTicketProjectileEntity>create(
                                    LunchTicketProjectileEntity::new,
                                    SpawnGroup.MISC
                            )
                            .dimensions(0.25F, 0.25F)
                            .maxTrackingRange(4)
                            .trackingTickInterval(10)
                            .build(LUNCH_TICKET_PROJECTILE_KEY)
            );

    public static void registerModEntities() {
        BoundlessRealmsMod.LOGGER.info("Registering entities for " + BoundlessRealmsMod.MOD_ID);
    }
}