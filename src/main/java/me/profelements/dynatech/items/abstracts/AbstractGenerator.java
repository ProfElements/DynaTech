package me.profelements.dynatech.items.abstracts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import com.google.common.base.Preconditions;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.implementation.operations.FuelOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

public abstract class AbstractGenerator extends AbstractContainer implements MachineProcessHolder<FuelOperation>, RecipeDisplayItem, EnergyNetProvider {
   
    protected List<MachineFuel> fuels = new ArrayList<>(); 

    private int energyProduction =  -1;
    private int energyCapacity = -1;
    private int processingSpeed = -1;

    private final MachineProcessor<FuelOperation> processor = new MachineProcessor<>(this); 

	protected AbstractGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, item, recipeType, recipe);

        processor.setProgressBar(getProgressBar()); 
	}
     
    public int getCapacity() {
        return energyCapacity; 
    }

    public int getEnergyProduction() {
        return energyProduction;
    } 
    
    public int getSpeed() {
        return processingSpeed;
    }


    public final AbstractGenerator setEnergyCapacity(int capacity) {
        Preconditions.checkArgument(capacity > 0, "The capacity must be greater then 0");

        this.energyCapacity = capacity; 
        return this;
    }

    public final AbstractGenerator setEnergyProduction(int production) {
        Preconditions.checkArgument(getCapacity() > 0, "Capacity must be set before consumption");
        Preconditions.checkArgument(production < getCapacity() && production != 0, "Consuption can not be greater then capacity"); 
        
        this.energyProduction = production;
        return this;
    }

    public final AbstractGenerator setProcessingSpeed(int speed) {
        Preconditions.checkArgument(speed > 0, "Speed must be greater then zero!"); 

        this.processingSpeed = speed; 
        return this; 
    }



    @Nonnull
    @Override
    public MachineProcessor<FuelOperation> getMachineProcessor() {
        return processor;
    }
 
    @Nullable
	public MachineFuel findNextFuel(BlockMenu menu) {
        Map<Integer, ItemStack> inv = new HashMap<>();

        for (int slot : getInputSlots()) {
            ItemStack item = menu.getItemInSlot(slot);

            if (item != null) {
                inv.put(slot, ItemStackWrapper.wrap(item));
            }
        }

        Map<Integer, Integer> found = new HashMap<>();

        for (MachineFuel recipe : fuels) {
            ItemStack input = recipe.getInput();
            for (int slot : getInputSlots()) {
                if (SlimefunUtils.isItemSimilar(inv.get(slot), input, true)) {
                    found.put(slot, input.getAmount());
                    break;
                }
            }

            if (found.size() == 1) {
                if(recipe.getOutput() != null && !InvUtils.fitAll(menu.toInventory(), new ItemStack[] { recipe.getOutput() }, getOutputSlots())) {
                        return null;
                }
                
                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    menu.consumeItem(entry.getKey(), entry.getValue());
                }

                return recipe; 
            } else {
                found.clear();
            }
        }

        return null;
    }    

    @Nonnull
    protected abstract ItemStack getProgressBar();

    protected boolean checkFuelPreconditions(Block b) { return true; };
    
    protected boolean onFuelFinish(BlockMenu menu, ItemStack[] ingredients) { return true; };

    protected void addOutputs(BlockMenu menu, Block b, ItemStack[] outputs) {
        for (ItemStack output: outputs) {
            if (output != null) {
                menu.pushItem(output.clone(), getOutputSlots()); 
            }
        }
    }

    @Override
    protected void onBreak(BlockBreakEvent e, BlockMenu menu, Location l) {
        super.onBreak(e, menu, l); 

        menu.dropItems(l, getInputSlots());
        menu.dropItems(l, getOutputSlots());

        processor.endOperation(e.getBlock()); 
    }

    @Override
    public int getGeneratedOutput(Location l, Config data) {
        Block b = l.getBlock();
        BlockMenu menu = BlockStorage.getInventory(b); 
        
        FuelOperation currentOp = processor.getOperation(b);
        if (currentOp != null && menu != null) {
            if (checkFuelPreconditions(b)) {
                
                if(!currentOp.isFinished() && isChargeable()) {
                    int charge = getCharge(l, data);
                    processor.updateProgressBar(menu, 22, currentOp); 
                    
                    if(getCapacity() - charge >= getEnergyProduction()) {
                        currentOp.addProgress(1);
                        return getEnergyProduction();
                    }                    
                } else {
                    menu.replaceExistingItem(22, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ")); 

                    boolean isFinished = onFuelFinish(menu, new ItemStack[] { currentOp.getIngredient() }); 
                    if (isFinished) {
                        addOutputs(menu, b, new ItemStack[] {currentOp.getResult()}); 
                    }

                    processor.endOperation(b);
                    return 0;
                }     
            }
        } else {
            MachineFuel next = findNextFuel(menu);

            if (next != null) {
                currentOp = new FuelOperation(next);
                processor.startOperation(b, currentOp);

                processor.updateProgressBar(menu, 22, currentOp);
            }
        }
        return 0;
    }
}
