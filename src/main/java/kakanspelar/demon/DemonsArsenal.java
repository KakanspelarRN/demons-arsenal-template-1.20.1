package kakanspelar.demon;

import kakanspelar.demon.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemonsArsenal implements ModInitializer {
	public static final String MOD_ID = "demonsarsenal";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
	}
}