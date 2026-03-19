package lum.boundless_realms.block;

import lum.boundless_realms.BoundlessRealmsMod;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<NetherFurnaceBlockEntity> NETHER_FURNACE_ENTITY =
            Registry.register(
                    Registries.BLOCK_ENTITY_TYPE,
                    Identifier.of(BoundlessRealmsMod.MOD_ID, "nether_furnace_be"),
                    FabricBlockEntityTypeBuilder.create(NetherFurnaceBlockEntity::new, ModBlocks.NETHER_FURNACE).build()
            );

    public static void registerBlockEntities() {
        BoundlessRealmsMod.LOGGER.info("Registering Block Entities for " + BoundlessRealmsMod.MOD_ID);
    }
}