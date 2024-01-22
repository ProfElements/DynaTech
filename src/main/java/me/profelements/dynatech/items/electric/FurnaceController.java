package me.profelements.dynatech.items.electric;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Furnace;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.features.blockstatesnapshot.BlockStateSnapshotResult;
import me.profelements.dynatech.items.abstracts.AbstractElectricTicker;

public class FurnaceController extends AbstractElectricTicker {
    
    private static final Set<BlockFace> ignoredFaces = new HashSet<>();

    public FurnaceController(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        ignoredFaces.add(BlockFace.UP);
        ignoredFaces.add(BlockFace.DOWN);
        ignoredFaces.add(BlockFace.NORTH_NORTH_EAST);
        ignoredFaces.add(BlockFace.NORTH_NORTH_WEST);
        ignoredFaces.add(BlockFace.SOUTH_SOUTH_EAST);
        ignoredFaces.add(BlockFace.SOUTH_SOUTH_WEST);
        ignoredFaces.add(BlockFace.WEST_NORTH_WEST);
        ignoredFaces.add(BlockFace.WEST_SOUTH_WEST);
        ignoredFaces.add(BlockFace.EAST_SOUTH_EAST);
        ignoredFaces.add(BlockFace.EAST_NORTH_EAST);

    }
    
    protected void tick(Block b, SlimefunItem item) {
        for (BlockFace face : BlockFace.values()) {
            if (ignoredFaces.contains(face)) {
                continue;
            }

            Block relBlock = b.getRelative(face);
            if (getMachines().contains(relBlock.getType())) { 
                BlockStateSnapshotResult result = PaperLib.getBlockState(relBlock, false);
                BlockState state = result.getState();
                
                if (state instanceof Furnace furnace && furnace.getCookTimeTotal() > 0) {
                    furnace.setBurnTime((short) 1600);

                    if (result.isSnapshot()) {
                        state.update(true, true);
                    }
                }
            }
        }
    }
    
    @Override
    protected boolean isSynchronized() {
        return true;
    }
    

    private List<Material> getMachines() {
        List<Material> machines = new ArrayList<>();

        machines.add(Material.FURNACE);
        machines.add(Material.BLAST_FURNACE);
        machines.add(Material.SMOKER);
        
        return machines;
    }
}

