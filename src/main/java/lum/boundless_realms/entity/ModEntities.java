package lum.boundless_realms.entity;

import lum.boundless_realms.BoundlessRealmsMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static final Identifier LUNCH_TICKET_PROJECTILE_ID = entityId("lunch_ticket_projectile");

    public static final RegistryKey<EntityType<LunchTicketProjectileEntity>> LUNCH_TICKET_PROJECTILE_KEY = entityKey(LUNCH_TICKET_PROJECTILE_ID);

    public static final EntityType<LunchTicketProjectileEntity> LUNCH_TICKET_PROJECTILE = registerEntity(
            LUNCH_TICKET_PROJECTILE_ID,
            LUNCH_TICKET_PROJECTILE_KEY,
            EntityType.Builder.<LunchTicketProjectileEntity>create(
                            LunchTicketProjectileEntity::new,
                            SpawnGroup.MISC
                    )
                    .dimensions(0.25F, 0.25F)
                    .maxTrackingRange(4)
                    .trackingTickInterval(10)
    );

    public static final Identifier TICKET_INSPECTOR_ID = entityId("ticket_inspector");

    public static final RegistryKey<EntityType<TicketInspectorEntity>> TICKET_INSPECTOR_KEY = entityKey(TICKET_INSPECTOR_ID);

    public static final EntityType<TicketInspectorEntity> TICKET_INSPECTOR = registerEntity(
            TICKET_INSPECTOR_ID,
            TICKET_INSPECTOR_KEY,
            EntityType.Builder.<TicketInspectorEntity>create(
                            TicketInspectorEntity::new,
                            SpawnGroup.CREATURE
                    )
                    .dimensions(0.6F, 1.95F)
                    .maxTrackingRange(8)
                    .trackingTickInterval(2)
    );

    public static void registerModEntities() {
        BoundlessRealmsMod.LOGGER.info("Registering entities for " + BoundlessRealmsMod.MOD_ID);

        FabricDefaultAttributeRegistry.register(
                TICKET_INSPECTOR,
                TicketInspectorEntity.createAttributes()
        );
    }

    private static Identifier entityId(String name) {
        return Identifier.of(BoundlessRealmsMod.MOD_ID, name);
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> RegistryKey<EntityType<T>> entityKey(Identifier id) {
        return (RegistryKey<EntityType<T>>) (RegistryKey<?>) RegistryKey.of(RegistryKeys.ENTITY_TYPE, id);
    }

    private static <T extends Entity> EntityType<T> registerEntity(
            Identifier id,
            RegistryKey<EntityType<T>> key,
            EntityType.Builder<T> builder
    ) {
        return Registry.register(Registries.ENTITY_TYPE, id, builder.build(asWildcardKey(key)));
    }

    @SuppressWarnings("unchecked")
    private static <T extends Entity> RegistryKey<EntityType<?>> asWildcardKey(RegistryKey<EntityType<T>> key) {
        return (RegistryKey<EntityType<?>>) (RegistryKey<?>) key;
    }
}
