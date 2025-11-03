package kakanspelar.demon.item.custom;

import kakanspelar.demon.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.text.MutableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();

            int minY = context.getWorld().getBottomY();  // usually -64
            int maxY = context.getWorld().getTopY();     // usually 319

            List<Text> foundOres = new ArrayList<>();

            // Scan full world height
            for (int y = maxY; y >= minY; y--) {
                BlockPos checkPos = new BlockPos(positionClicked.getX(), y, positionClicked.getZ());
                BlockState state = context.getWorld().getBlockState(checkPos);

                if (isValuableBlock(state)) {
                    Block block = state.getBlock();
                    String blockName = block.asItem().getName().getString();

                    // Get hex color for this ore
                    int colorHex = getOreColor(block);

                    // Build styled text: colored ore name + white coords
                    MutableText message = Text.literal("")
                            .append(Text.literal(blockName).styled(style -> style.withColor(TextColor.fromRgb(colorHex))))
                            .append(Text.literal(" at (" + checkPos.getX() + ", " + checkPos.getY() + ", " + checkPos.getZ() + ")"));

                    foundOres.add(message);
                }
            }

            if (foundOres.isEmpty()) {
                player.sendMessage(Text.literal("No ores found in this column."), false);
            } else {
                for (Text ore : foundOres) {
                    player.sendMessage(ore, false);
                }
            }
        }

        // Damage the item
        context.getStack().damage(1, context.getPlayer(),
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));

        return ActionResult.SUCCESS;
    }

    private boolean isValuableBlock(BlockState state) {
        return state.isOf(Blocks.IRON_ORE)
                || state.isOf(Blocks.DIAMOND_ORE)
                || state.isOf(Blocks.COAL_ORE)
                || state.isOf(Blocks.GOLD_ORE)
                || state.isOf(Blocks.COPPER_ORE)
                || state.isOf(Blocks.EMERALD_ORE)
                || state.isOf(Blocks.LAPIS_ORE)
                || state.isOf(Blocks.REDSTONE_ORE)
                || state.isOf(Blocks.DEEPSLATE_COAL_ORE)
                || state.isOf(Blocks.DEEPSLATE_IRON_ORE)
                || state.isOf(Blocks.DEEPSLATE_GOLD_ORE)
                || state.isOf(Blocks.DEEPSLATE_COPPER_ORE)
                || state.isOf(Blocks.DEEPSLATE_EMERALD_ORE)
                || state.isOf(Blocks.DEEPSLATE_REDSTONE_ORE)
                || state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)
                || state.isOf(Blocks.DEEPSLATE_LAPIS_ORE)
                || state.isOf(Blocks.ANCIENT_DEBRIS)
                || state.isOf(Blocks.NETHER_QUARTZ_ORE)
                || state.isOf(Blocks.NETHER_GOLD_ORE)
                || state.isOf(ModBlocks.NETHER_INFERNAL_ORE);
    }

    private int getOreColor(Block block) {
        // Normal ores
        if (block == Blocks.GOLD_ORE) return 0xFFD700;       // gold yellow
        if (block == Blocks.DIAMOND_ORE) return 0x00FFFF;    // aqua blue
        if (block == Blocks.COPPER_ORE) return 0xFF7F00;     // orange
        if (block == Blocks.LAPIS_ORE) return 0x0000CD;      // medium blue
        if (block == Blocks.EMERALD_ORE) return 0x00FF00;    // bright green
        if (block == Blocks.REDSTONE_ORE) return 0xFF0000;   // red
        if (block == Blocks.IRON_ORE) return 0xC0C0C0;       // silver/gray
        if (block == Blocks.COAL_ORE) return 0x2E2E2E;       // dark gray

        // Deepslate ores (darker variants)
        if (block == Blocks.DEEPSLATE_GOLD_ORE) return 0xB8860B;    // dark gold
        if (block == Blocks.DEEPSLATE_DIAMOND_ORE) return 0x008B8B; // dark aqua
        if (block == Blocks.DEEPSLATE_COPPER_ORE) return 0xCC5500;  // darker orange
        if (block == Blocks.DEEPSLATE_LAPIS_ORE) return 0x00008B;   // dark blue
        if (block == Blocks.DEEPSLATE_EMERALD_ORE) return 0x006400; // dark green
        if (block == Blocks.DEEPSLATE_REDSTONE_ORE) return 0x8B0000;// dark red
        if (block == Blocks.DEEPSLATE_IRON_ORE) return 0x808080;    // darker gray
        if (block == Blocks.DEEPSLATE_COAL_ORE) return 0x1C1C1C;    // near black

        // nether ore
        if (block == ModBlocks.NETHER_INFERNAL_ORE) return 0xC2946D;
        if (block == Blocks.ANCIENT_DEBRIS) return 0x4B3621;
        if (block == Blocks.NETHER_QUARTZ_ORE) return 0xF0F0F0;
        if (block == Blocks.NETHER_GOLD_ORE) return 0xFFD720;

        // Default white
        return 0xFFFFFF;
    }
}
