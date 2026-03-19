package lum.boundless_realms.entity;

import lum.boundless_realms.BoundlessRealmsMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
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

    public static final Identifier FAKE_TICKET_INSPECTOR_ID =
            Identifier.of(BoundlessRealmsMod.MOD_ID, "fake_ticket_inspector");

    public static final RegistryKey<EntityType<?>> FAKE_TICKET_INSPECTOR_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, FAKE_TICKET_INSPECTOR_ID);

    public static final EntityType<FakeTicketInspectorEntity> FAKE_TICKET_INSPECTOR =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    FAKE_TICKET_INSPECTOR_ID,
                    EntityType.Builder.<FakeTicketInspectorEntity>create(
                                    FakeTicketInspectorEntity::new,
                                    SpawnGroup.CREATURE
                            )
                            .dimensions(0.6F, 1.95F)
                            .maxTrackingRange(8)
                            .trackingTickInterval(2)
                            .build(FAKE_TICKET_INSPECTOR_KEY)
            );

    public static void registerModEntities() {
        BoundlessRealmsMod.LOGGER.info("Registering entities for " + BoundlessRealmsMod.MOD_ID);

        FabricDefaultAttributeRegistry.register(
                FAKE_TICKET_INSPECTOR,
                FakeTicketInspectorEntity.createAttributes()
        );
    }
}