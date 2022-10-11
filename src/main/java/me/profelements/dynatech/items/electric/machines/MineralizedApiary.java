package me.profelements.dynatech.items.electric.machines;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Beehive;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.profelements.dynatech.items.abstracts.AbstractElectricMachine;

public class MineralizedApiary extends AbstractElectricMachine {

    private static final int[] BACKGROUND_SLOTS = new int[] { 0, 1, 2, 3, 9, 18, 27, 36, 37, 38, 39 };
    private static final int[] OUTPUT_BORDER_SLOTS = new int[] { 4, 5, 6, 7, 8, 13, 17, 26, 31, 35, 40, 41, 42, 43, 44};
    private static final int[] INPUT_BORDER_SLOTS = new int[] {10, 11, 12, 19, 21, 28, 29, 30}; 
    
    private static final int[] OUTPUT_SLOTS = new int[] { 14, 15, 16, 23, 24, 25, 32, 33, 34};

    private static final ItemStack PROGRESS_ITEM = new ItemStack(Material.HONEYCOMB);
    private ItemStack material = new ItemStack(Material.AIR);



    public MineralizedApiary(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, ItemStack material) {
        super(itemGroup, item, recipeType, recipe);

        this.material = material;
    }
        

    @Override
    public void postRegister() {
        registerRecipe(60, new ItemStack[] { this.material }, new ItemStack[] { this.material });
    }
    
    @Override
    public void tick(BlockMenu menu, Block b) {
        BlockState state = PaperLib.getBlockState(menu.getBlock(), false).getState(); 
        if (state instanceof Beehive) {
            Beehive hive = (Beehive) state;

            int count = hive.getEntityCount();
            
            updateInfoStack(menu, count); 
        }
        super.tick(menu, b);
    }

    @Override
	public MachineRecipe findNextRecipe(BlockMenu menu) {

        MachineRecipe recipe = recipes.get(0);
    
        BlockState state = PaperLib.getBlockState(menu.getBlock(), false).getState(); 
        if (state instanceof Beehive) {
            Beehive hive = (Beehive) state;

            int count = hive.getEntityCount();
            
            if (count == 1) {
                //30 second recipe if 1 bee; 
                return new MachineRecipe(30, new ItemStack[] { this.material }, new ItemStack[] { this.material }); 
            } else if (count > 1) {
                // Minus 10 from 30 seconds other wise;
                return new MachineRecipe(30 - ((count -1) * 10), new ItemStack[] { this.material }, new ItemStack[] { this.material }); 
            }
        }

        return recipe;

    }


	@Override
	public List<ItemStack> getDisplayRecipes() {
	    List<ItemStack> display = new ArrayList<>();

        display.add(this.material);
        display.add(this.material);

        return display;
	}

	@Override
	protected ItemStack getProgressBar() {
		return PROGRESS_ITEM;
	}

	@Override
	protected void setupMenu(BlockMenuPreset preset) {
	    preset.drawBackground(ChestMenuUtils.getBackground(), BACKGROUND_SLOTS);
        preset.drawBackground(ChestMenuUtils.getInputSlotTexture(), INPUT_BORDER_SLOTS);
        preset.drawBackground(ChestMenuUtils.getOutputSlotTexture(), OUTPUT_BORDER_SLOTS);

        preset.addItem(getProgressSlot(), new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());
        preset.addItem(20, new CustomItemStack(Material.BEACON, "&fINFO", "&fBee Count: {}", "&fCurrent Speed: {}"), ChestMenuUtils.getEmptyClickHandler()); 
 
    }

	@Override
	protected int[] getInputSlots() {
		return new int[] {};
	}

	@Override
	protected int[] getOutputSlots() {
		return OUTPUT_SLOTS;
	}
    
    @Override
    public boolean isSynchronized() {
        return true;
    }

    private void updateInfoStack(BlockMenu menu, int beeCount) {
        int currSpeed = 100; 
        if (beeCount > 0) {
            currSpeed = 30 - ((beeCount - 1) * 10);
        }

        ItemStack infoStack = new CustomItemStack(Material.BEACON, "&fINFO", "&fBee Count: " + String.valueOf(beeCount), "&fCurrent Speed: " + String.valueOf(currSpeed) + "s");  
        menu.replaceExistingItem(20, infoStack);
    }
}
