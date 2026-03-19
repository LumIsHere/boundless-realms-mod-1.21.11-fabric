package lum.boundless_realms.item;

import lum.boundless_realms.BoundlessRealmsMod;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;

public class ModItems {
    public static final Item AN_ITEM = registerItem("an_item", new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID,"an_item")))));

    public static final Item MONEY = registerItem("money",
            new Item(new Item.Settings().maxCount(99).registryKey(
                    RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, "money"))
            )));

    public static final Item LUNCH_TICKET = registerItem("lunch_ticket",
            new LunchTicketItem(new Item.Settings().maxCount(1).registryKey(
                    RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, "lunch_ticket"))
            )));

    public static final Item BACKSTAB_TOTEM = registerItem("backstab_totem", new BackstabTotemItem(new Item.Settings().maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, "backstab_totem")))));
    public static final Item WITHER_FURY = registerItem("wither_fury",
            new WitherFuryItem(new Item.Settings()
                    .maxCount(1)
                    .attributeModifiers(createSwordAttributes(8.0f, -2.4f))
                    .component(DataComponentTypes.TOOL, createSwordToolComponent())
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, "wither_fury")))
            )
    );
    public static final Item TAIGA_ONE_SURVIVAL_MACHETE = registerItem("taiga_1_survival_machete",
            new Item(new Item.Settings().sword(ToolMaterial.NETHERITE, 6.0F, -3.0F).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID,"taiga_1_survival_machete")))));

    public static final Item ANGLERFISH_MASK = registerItem("anglerfish_mask",
            new AnglerfishMaskItem(new Item.Settings()
                    .maxCount(1).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.HEAD).equipSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER).build())
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, "anglerfish_mask")))
            ));

    public static final Item PORTABLE_CRAFTING_TABLE = registerItem("portable_crafting_table",
            new PortableCraftingTableItem(new Item.Settings().maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, "portable_crafting_table")))));

    public static final Item WALLET = registerItem("wallet",
            new WalletItem(new Item.Settings().maxCount(1).registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID,"wallet")))));

    public static final Item NETHER_FURNACE_UPGRADE_TEMPLATE = registerItem("nether_furnace_upgrade_template", new Item(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID,"nether_furnace_upgrade_template")))));


    private static AttributeModifiersComponent createSwordAttributes(double damage, double speed) {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.ATTACK_DAMAGE,
                        new EntityAttributeModifier(Identifier.of("base_attack_damage"), damage - 1.0, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND)
                .add(EntityAttributes.ATTACK_SPEED,
                        new EntityAttributeModifier(Identifier.of("base_attack_speed"), speed, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND)
                .build();
    }

    private static ToolComponent createSwordToolComponent() {
        return new ToolComponent(
                List.of(
                        ToolComponent.Rule.of(
                                RegistryEntryList.of(Blocks.COBWEB.getRegistryEntry()),
                                15.0f
                        )
                ),
                1.0f,
                1,
                false
        );
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        BoundlessRealmsMod.LOGGER.info("Registering items for " + BoundlessRealmsMod.MOD_ID);
    }
}
