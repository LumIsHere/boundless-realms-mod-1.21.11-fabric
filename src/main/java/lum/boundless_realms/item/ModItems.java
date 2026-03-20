package lum.boundless_realms.item;

import lum.boundless_realms.BoundlessRealmsMod;
import lum.boundless_realms.entity.ModEntities;
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
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Function;

public class ModItems {

    public static final Item AN_ITEM = registerItem("an_item", Item::new, new Item.Settings());

    public static final Item FAKE_LUNCH_TICKET = registerItem("fake_lunch_ticket", Item::new, new Item.Settings().maxCount(1));

    public static final Item MONEY = registerItem("money", Item::new, new Item.Settings().maxCount(99));

    public static final Item LUNCH_TICKET = registerItem("lunch_ticket", LunchTicketItem::new,
            new Item.Settings().maxCount(1));

    public static final Item BACKSTAB_TOTEM = registerItem("backstab_totem", BackstabTotemItem::new,
            new Item.Settings().maxCount(1));

    public static final Item WITHER_FURY = registerItem("wither_fury", WitherFuryItem::new,
            new Item.Settings()
                    .maxCount(1)
                    .attributeModifiers(createSwordAttributes(8.0f, -2.4f))
                    .component(DataComponentTypes.TOOL, createSwordToolComponent()));

    public static final Item TAIGA_ONE_SURVIVAL_MACHETE = registerItem("taiga_1_survival_machete", Item::new,
            new Item.Settings().sword(ToolMaterial.NETHERITE, 6.0F, -3.0F));

    public static final Item ANGLERFISH_MASK = registerItem("anglerfish_mask", AnglerfishMaskItem::new,
            new Item.Settings()
                    .maxCount(1)
                    .component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.HEAD)
                            .equipSound(SoundEvents.ITEM_ARMOR_EQUIP_LEATHER).build()));

    public static final Item PORTABLE_CRAFTING_TABLE = registerItem("portable_crafting_table", PortableCraftingTableItem::new,
            new Item.Settings().maxCount(1));

    public static final Item WALLET = registerItem("wallet", WalletItem::new,
            new Item.Settings().maxCount(1));

    public static final Item NETHER_FURNACE_UPGRADE_TEMPLATE = registerItem("nether_furnace_upgrade_template", Item::new,
            new Item.Settings());

    public static final Item TICKET_INSPECTOR_SPAWN_EGG = registerItem("ticket_inspector_spawn_egg", SpawnEggItem::new,
            new Item.Settings().spawnEgg(ModEntities.TICKET_INSPECTOR));


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
                                RegistryEntryList.of(Registries.BLOCK.getEntry(Blocks.COBWEB)),
                                15.0f
                        )
                ),
                1.0f,
                1,
                false
        );
    }

    private static <T extends Item> T registerItem(String name, Function<Item.Settings, T> factory, Item.Settings settings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(BoundlessRealmsMod.MOD_ID, name));
        T item = factory.apply(settings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }

    public static void registerModItems() {
        BoundlessRealmsMod.LOGGER.info("Registering items for " + BoundlessRealmsMod.MOD_ID);
    }
}
