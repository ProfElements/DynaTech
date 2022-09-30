package me.profelements.dynatech.items.abstracts;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

public abstract class AbstractMachine extends AbstractTickingContainer implements MachineProcessHolder<CraftingOperation>, RecipeDisplayItem {
        
    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this); 

    protected AbstractMachine(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        
        processor.setProgressBar(getProgressBar()); 
    }

    @Nonnull
    @Override
    public MachineProcessor<CraftingOperation> getMachineProcessor() {
        return processor; 
    }

    @Nullable 
    public abstract MachineRecipe findNextRecipe(BlockMenu menu);

    @Nonnull
    protected abstract ItemStack getProgressBar();

    protected boolean checkCraftPreconditions(Block b) {
        return true;
    }

    protected boolean onCraftFinish(BlockMenu menu, ItemStack[] ingredients) {
        return true; 
    }

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
    protected void tick(BlockMenu menu, Block b) {
        CraftingOperation currentOp = processor.getOperation(b);

        if (currentOp != null) {
            if (checkCraftPreconditions(b)) {
                
                if(!currentOp.isFinished()) {
                    processor.updateProgressBar(menu, 22, currentOp); 
                    currentOp.addProgress(1);
                } else {
                    menu.replaceExistingItem(22, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " ")); 

                    boolean isFinished = onCraftFinish(menu, currentOp.getIngredients()); 
                    if (isFinished) {
                        addOutputs(menu, b, currentOp.getResults()); 
                    }

                    processor.endOperation(b);
                }     
            }
        } else {
            MachineRecipe next = findNextRecipe(menu);

            if (next != null) {
                currentOp = new CraftingOperation(next);
                processor.startOperation(b, currentOp);

                processor.updateProgressBar(menu, 22, currentOp);
            }
        }
    }
}
