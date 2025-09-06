package kakanspelar.demon.block;

import kakanspelar.demon.DemonsArsenal;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block INFERNAL_BLOCK = registerBlock("infernal_block",
            new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK)));
    public static final Block INFERNAL_ORE = registerBlock("infernal_ore",
            new Block(FabricBlockSettings
                    .of()                            // No Material class needed
                    .strength(4.0f, 100.0f)            // Hardness & blast resistance
                    .requiresTool()                  // Requires the correct tool
                    .sounds(BlockSoundGroup.STONE)   // Sound when mined/placed
            ));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(DemonsArsenal.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(DemonsArsenal.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        DemonsArsenal.LOGGER.info("Registering ModBlocks for" + DemonsArsenal.MOD_ID);
    }
}


