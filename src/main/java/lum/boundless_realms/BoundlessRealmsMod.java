package lum.boundless_realms;

import lum.boundless_realms.block.ModBlockEntities;
import lum.boundless_realms.block.ModBlocks;
import lum.boundless_realms.command.InspectorAnswerCommand;
import lum.boundless_realms.entity.ModEntities;
import lum.boundless_realms.item.ModItemGroups;
import lum.boundless_realms.item.ModItems;
import lum.boundless_realms.recipe.ModRecipeTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
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
        ModBlocks.registerModBlocks();
        ModBlockEntities.registerBlockEntities();
        ModRecipeTypes.registerRecipes();

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) ->
				InspectorAnswerCommand.register(dispatcher)
		);
	}
}
