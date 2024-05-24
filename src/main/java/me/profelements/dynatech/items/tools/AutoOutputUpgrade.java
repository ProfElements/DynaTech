package me.profelements.dynatech.items.tools;

import java.util.Optional;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.utils.Recipe;

public class AutoOutputUpgrade extends SlimefunItem {

    public AutoOutputUpgrade(ItemGroup group, SlimefunItemStack item, Recipe recipe) {
        super(group, item, recipe.getRecipeType(), recipe.getInput());

        addItemHandler(onUse());
    }

    public ItemUseHandler onUse() {
        return e -> {
            Optional<Block> optBlock = e.getClickedBlock();
            if (optBlock.isPresent()) {
                Block block = optBlock.get();

                String upgrades = BlockStorage.getLocationInfo(block.getLocation(), "upgrades");

                if (upgrades != null && upgrades.contains("id:auto_output")) {
                    return;
                }

                String blockFaceString = blockFaceToString(e.getClickedFace());
                if (blockFaceString == "invalid") {
                    return;
                }
                if (upgrades != null) {
                    BlockStorage.addBlockInfo(block, "upgrades",
                            upgrades + "," + "{id:auto_output,face:" + blockFaceString + "}");
                } else {

                    BlockStorage.addBlockInfo(block, "upgrades", "{id:auto_output,face:" + blockFaceString + "}");
                }
            }

            ItemStack stack = e.getItem();
            int amount = stack.getAmount();

            if (amount > 1) {
                stack.setAmount(amount - 1);
            } else {
                e.getPlayer().getInventory().remove(stack);
            }
        };
    }

    public static String blockFaceToString(BlockFace face) {
        String blockFaceString;

        switch (face) {
            case UP:
                blockFaceString = "up";
                break;
            case DOWN:
                blockFaceString = "down";
                break;
            case NORTH:
                blockFaceString = "north";
                break;
            case SOUTH:
                blockFaceString = "south";
                break;
            case EAST:
                blockFaceString = "east";
                break;
            case WEST:
                blockFaceString = "west";
                break;
            default:
                blockFaceString = "invalid";
                break;
        }

        return blockFaceString;
    }

    public static BlockFace stringToBlockFace(String blockFaceString) {
        BlockFace face;
        DynaTech.getInstance().getLogger().info(blockFaceString);
        switch (blockFaceString) {
            case "face:up":
                face = BlockFace.UP;
                break;
            case "face:down":
                face = BlockFace.DOWN;
                break;
            case "face:north":
                face = BlockFace.NORTH;
                break;
            case "face:south":
                face = BlockFace.SOUTH;
                break;
            case "face:east":
                face = BlockFace.EAST;
                break;
            case "face:west":
                face = BlockFace.WEST;
                break;
            default:
                face = BlockFace.SELF;
                break;
        }

        return face;
    }
}
