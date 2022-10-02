package me.profelements.dynatech.items.electric;

import java.util.ArrayList;
import java.util.List;

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
    

    public FurnaceController(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }
    
    protected void tick(Block b, SlimefunItem item) {
        for (BlockFace face : BlockFace.values()) {
            if (face == BlockFace.UP || face == BlockFace.DOWN) {
                continue;
            }
            Block relBlock = b.getRelative(face);
            if (getMachines().contains(relBlock.getType())) { 
                BlockStateSnapshotResult result = PaperLib.getBlockState(relBlock, false);
                BlockState state = result.getState();
                
                if (state instanceof Furnace && ((Furnace) state).getCookTimeTotal() > 0) {
                    Furnace furnace = (Furnace) state;
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

