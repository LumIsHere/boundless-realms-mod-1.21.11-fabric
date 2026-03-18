package lum.boundless_realms.item;

import lum.boundless_realms.BoundlessRealmsMod;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item AN_ITEM = registerItem("an_item", new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID,"an_item")))));
    public static final Item BACKSTAB_TOTEM = registerItem("backstab_totem", new BackstabTotemItem(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, "backstab_totem")))));
    public static final Item WITHER_FURY = registerItem("wither_fury", new WitherFuryItem(new Item.Settings().maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, "wither_fury")))));
    public static final Item TAIGA_ONE_SURVIVAL_MACHETE = registerItem("taiga_1_survival_machete", new Item(new Item.Settings().sword(ToolMaterial.NETHERITE, 6.0F, -3.0F).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID,"taiga_1_survival_machete")))));

    public static final Item ANGLERFISH_MASK = registerItem("anglerfish_mask",
            new AnglerfishMaskItem(new Item.Settings()
                    .maxCount(1)
                    .component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.HEAD)
                            .equipSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER) // Optional sound
                            .build())
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, "anglerfish_mask")))
            ));
    // Register items above

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BoundlessRealmsMod.LOGGER.info("Registering items for " + BoundlessRealmsMod.MOD_ID);
    }
}
