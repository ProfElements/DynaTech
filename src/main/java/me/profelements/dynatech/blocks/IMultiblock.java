package me.profelements.dynatech.blocks;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import com.google.common.base.Predicate;

public interface IMultiblock {

    Predicate<Block>[][][] getMultiblockBlocks();

    boolean isController(World w, int x, int y, int z);

    boolean isValidMultiblock(Location l, BlockFace facing);

    void createMultiblock(Location l, BlockFace facing);

    void destroyMultiblock(Location l, BlockFace facing);

}
