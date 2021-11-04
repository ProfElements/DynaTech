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
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.features.blockstatesnapshot.BlockStateSnapshotResult;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;

public class FurnaceController extends SlimefunItem implements EnergyNetComponent {
    
    private static final int J_PER_BLOCK = 128;

    public FurnaceController(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onBlockTick());
    }
    
    public BlockTicker onBlockTick() {
        return new BlockTicker() {

			@Override
			public boolean isSynchronized() {
				return true;
			}

			@Override
			public void tick(Block arg0, SlimefunItem arg1, Config arg2) {
			    FurnaceController.this.tick(arg0);	
			}
                
        };
    }

    public void tick(Block b) {
        for (BlockFace face : BlockFace.values()) {
            if (face == BlockFace.UP || face == BlockFace.DOWN) {
                continue;
            }
            Block relBlock = b.getRelative(face);
            if (getMachines().contains(relBlock.getType()) && getCharge(b.getLocation()) >= J_PER_BLOCK) { 
                BlockStateSnapshotResult result = PaperLib.getBlockState(relBlock, false);
                BlockState state = result.getState();
                
                if (state instanceof Furnace && ((Furnace) state).getCookTimeTotal() > 0) {
                    Furnace furnace = (Furnace) state;
                    removeCharge(b.getLocation(), J_PER_BLOCK);
                    furnace.setBurnTime((short) 1600);

                    if (result.isSnapshot()) {
                        state.update(true, true);
                    }
                }
            }
        }
    }

    

    private List<Material> getMachines() {
        List<Material> machines = new ArrayList<>();

        machines.add(Material.FURNACE);
        machines.add(Material.BLAST_FURNACE);
        machines.add(Material.SMOKER);
        
        return machines;
    }

	@Override
	public int getCapacity() {
	    return 2048;
	}

	@Override
	public EnergyNetComponentType getEnergyComponentType() {
	    return EnergyNetComponentType.CONSUMER;	
	}
}

