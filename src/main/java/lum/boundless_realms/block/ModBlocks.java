package lum.boundless_realms.block;

import lum.boundless_realms.BoundlessRealmsMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import java.util.function.Function;

import static net.minecraft.block.Blocks.createLightLevelFromLitBlockState;

public class ModBlocks {

    public static final Block A_BLOCK = registerBlock("a_block",
            Block::new,
            AbstractBlock.Settings.create());

    public static final Block NETHER_FURNACE = registerBlock("nether_furnace",
            NetherFurnaceBlock::new,
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.BRIGHT_RED)
                    .requiresTool()
                    .strength(3.5F)
                    .luminance(createLightLevelFromLitBlockState(15)));

    private static <T extends Block> T registerBlock(String name, Function<AbstractBlock.Settings, T> factory, AbstractBlock.Settings settings) {
        RegistryKey<Block> blockKey = RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(BoundlessRealmsMod.MOD_ID, name));

        T block = factory.apply(settings.registryKey(blockKey));

        registerBlockItem(name, block);

        return Registry.register(Registries.BLOCK, blockKey, block);
    }

    private static void registerBlockItem(String name, Block block) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, name));
        Registry.register(Registries.ITEM, itemKey, new BlockItem(block, new Item.Settings().registryKey(itemKey)));
    }

    public static void registerModBlocks() {
        net.minecraft.block.entity.BlockEntityType.FURNACE.addSupportedBlock(ModBlocks.NETHER_FURNACE);
        BoundlessRealmsMod.LOGGER.info("Registering Mod Blocks for " + BoundlessRealmsMod.MOD_ID);
    }
}