package kakanspelar.demon.item;

import kakanspelar.demon.DemonsArsenal;
import kakanspelar.demon.DemonsArsenalClient;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item INFERNAL_STEEL = registerItem("infernal_steel", new Item(new FabricItemSettings()));

    private static void addItemToIngredientTabGroup(FabricItemGroupEntries entries) {
        entries.add(INFERNAL_STEEL);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(DemonsArsenal.MOD_ID, name), item);
    }


    public static void registerModItems() {
        DemonsArsenal.LOGGER.info("Registering Mod Items For " + DemonsArsenal.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemToIngredientTabGroup);
    }
}
