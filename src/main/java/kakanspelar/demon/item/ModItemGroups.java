package kakanspelar.demon.item;

import kakanspelar.demon.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import kakanspelar.demon.DemonsArsenal;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup DEMONSARSENAL = Registry.register(
            Registries.ITEM_GROUP,
            new Identifier(DemonsArsenal.MOD_ID, "infernal_steel"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.demons_arsenal_item_group"))
                    .icon(() -> new ItemStack(ModItems.INFERNAL_STEEL))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.INFERNAL_STEEL);
                        entries.add(ModItems.RAW_INFERNAL);
                        entries.add(ModBlocks.INFERNAL_BLOCK);
                        entries.add(ModBlocks.INFERNAL_ORE);
                    })
                    .build()
    );

    public static void registerItemGroups() {
        DemonsArsenal.LOGGER.info("Registering Item Groups for " + DemonsArsenal.MOD_ID);
    }
}
