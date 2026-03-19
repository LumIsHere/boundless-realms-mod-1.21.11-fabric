package lum.boundless_realms;

import lum.boundless_realms.entity.ModEntities;
import lum.boundless_realms.item.ModItemGroups;
import lum.boundless_realms.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BoundlessRealmsMod implements ModInitializer {
	public static final String MOD_ID = "boundless_realms";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModEntities.registerModEntities();
		ModItems.registerModItems();
		ModItemGroups.registerItemGroups();
	}
}