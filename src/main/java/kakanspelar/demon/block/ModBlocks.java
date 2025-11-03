package kakanspelar.demon.block;

import kakanspelar.demon.DemonsArsenal;
import kakanspelar.demon.block.custom.SoundBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

public class ModBlocks {
    public static final Block INFERNAL_BLOCK = registerBlock("infernal_block",
            new Block(FabricBlockSettings.create().strength(5.0f, 6.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.METAL)));
    public static final Block RAW_INFERNAL_BLOCK = registerBlock("raw_infernal_block",
            new Block(FabricBlockSettings.create().strength(4.0f, 50.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)));
    public static final Block NETHER_INFERNAL_ORE = registerBlock("infernal_ore",
            new ExperienceDroppingBlock(FabricBlockSettings.create().strength(5.0f, 50.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.NETHER_ORE)
                    .mapColor(MapColor.DARK_RED),
                    UniformIntProvider.create(2, 5)));
    public static Block DEBUG_A = registerBlock("___",
            new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.NETHER_GOLD_ORE)));
    public static final Block SOUND_BLOCK = registerBlock("sound_block",
            new SoundBlock(FabricBlockSettings.create().strength(1.0f, 0.0f)
                    .sounds(BlockSoundGroup.AMETHYST_CLUSTER)));






    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(DemonsArsenal.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(DemonsArsenal.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        DemonsArsenal.LOGGER.info("Registering ModBlocks for " + DemonsArsenal.MOD_ID);
    }
}