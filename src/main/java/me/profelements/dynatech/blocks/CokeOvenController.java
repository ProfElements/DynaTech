package me.profelements.dynatech.blocks;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.profelements.dynatech.DynaTechItems;

public class CokeOvenController extends SlimefunItem {

    public CokeOvenController(SlimefunItemStack stack) {
        super(DynaTechItems.DT_EXPERIMENTAL, stack, RecipeType.NULL, new ItemStack[] {});

        addItemHandler(onUse());
    }

    public BlockUseHandler onUse() {
        return new BlockUseHandler() {
            @Override
            public void onRightClick(PlayerRightClickEvent event) {
                event.getPlayer().closeInventory();
                if (validateMultiblock(event.getClickedBlock().get(), event.getPlayer().getFacing())) {
                    event.getPlayer().sendMessage("Touched the Beacon");
                }

            }
        };
    }

    private boolean validateMultiblock(Block controllerBlock, BlockFace facing) {
        int width = 3;
        int depth = 3;
        int height = 3;

        BlockFace backSide = facing;
        BlockFace leftSide = rotateCounterClockwise(facing);
        BlockFace frontSide = rotateCounterClockwise(leftSide);
        BlockFace rightSide = rotateCounterClockwise(frontSide);

        // back back left
        Vector backCornerMod = new Vector(backSide.getModX() + backSide.getModX() + leftSide.getModX(),
                backSide.getModY() + backSide.getModY() + leftSide.getModY(),
                backSide.getModZ() + backSide.getModZ() + leftSide.getModZ());
        Vector rightSideMod = new Vector(rightSide.getModX(), rightSide.getModY(), rightSide.getModZ());
        Vector frontSideMod = new Vector(frontSide.getModX(), frontSide.getModY(), frontSide.getModZ());
        Location backLeftLocation = controllerBlock.getLocation().add(backCornerMod);

        Predicate<Block>[][][] blockPreds = getMultiblockPattern();
        for (int z = 0; z < height; z++) {
            for (int y = 0; y < depth; y++) {
                for (int x = 0; x < width; x++) {
                    Predicate<Block> blockPredicate = blockPreds[y][x][z];

                    if (blockPredicate.test(backLeftLocation.getBlock())) {
                        backLeftLocation = backLeftLocation.add(rightSideMod);
                        continue;
                    } else {
                        return false;
                    }
                }
                backLeftLocation = backLeftLocation
                        .add(new Vector(leftSide.getModX(), leftSide.getModY(), leftSide.getModZ()).multiply(width))
                        .add(frontSideMod);
            }
            backLeftLocation = controllerBlock.getLocation().add(backCornerMod).add(new Vector(0, z + 1, 0));
        }

        return true;

    }

    private BlockFace rotateCounterClockwise(BlockFace facing) {
        switch (facing) {
            case BlockFace.NORTH:
                return BlockFace.WEST;
            case BlockFace.EAST:
                return BlockFace.NORTH;
            case BlockFace.SOUTH:
                return BlockFace.EAST;
            case BlockFace.WEST:
                return BlockFace.SOUTH;
            default:
                return BlockFace.SELF;
        }
    }

    Predicate<Block>[][][] getMultiblockPattern() {
        Predicate<Block>[][][] pattern = (Predicate<Block>[][][]) Array.newInstance(Predicate.class, 3, 3, 3);

        for (int y = 0; y < 3; y++) {
            for (int z = 0; z < 3; z++) {
                for (int x = 0; x < 3; x++) {
                    pattern[z][x][y] = blk -> (blk.getType().equals(Material.MUD_BRICKS));
                }
            }
        }

        Predicate<Block> isControl = blk -> (BlockStorage.checkID(blk)
                .equals(DynaTechItems.COKE_OVEN_CONTROLLER.getItemId()));
        pattern[2][1][0] = isControl;

        Predicate<Block> isBarrel = blk -> (blk.getType().equals(Material.BARREL));
        pattern[1][0][1] = isBarrel;
        pattern[1][2][1] = isBarrel;

        return pattern;
    }

}
