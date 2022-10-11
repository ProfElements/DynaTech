package me.profelements.dynatech.items.electric.growthchambers;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.profelements.dynatech.items.abstracts.AbstractElectricMachine;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GrowthChamberOcean extends AbstractElectricMachine {

    private static final int[] INPUT_SLOTS = new int[] { 19, 20 };
    private static final int[] OUTPUT_SLOTS = new int[] { 24, 25 };

    private static final int[] INPUT_BORDER_SLOTS = new int[] { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
    private static final int[] OUTPUT_BORDER_SLOTS = new int[] {14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
    private static final int[] BACKGROUND_SLOTS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44 }; 

    private static final ItemStack PROGRESS_ITEM = new ItemStack(Material.CONDUIT); 

    public GrowthChamberOcean(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

    }

    @Override
    public void postRegister() {
        registerDefaultRecipes();
    }

    protected void registerDefaultRecipes() {

        registerRecipe(9, new ItemStack(Material.LILY_PAD), new ItemStack(Material.LILY_PAD, 3));
        registerRecipe(9, new ItemStack(Material.SEA_PICKLE), new ItemStack(Material.SEA_PICKLE, 3));
        registerRecipe(12, new ItemStack(Material.SEAGRASS), new ItemStack(Material.SEAGRASS, 4));
        registerRecipe(9, new ItemStack(Material.KELP), new ItemStack(Material.KELP, 3));
    // Coral blocks
        // Brings dead coral blocks back to life!
        registerRecipe(9, new ItemStack(Material.DEAD_TUBE_CORAL_BLOCK), new ItemStack(Material.TUBE_CORAL_BLOCK, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_BRAIN_CORAL_BLOCK), new ItemStack(Material.BRAIN_CORAL_BLOCK, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_BUBBLE_CORAL_BLOCK), new ItemStack(Material.BUBBLE_CORAL_BLOCK, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK), new ItemStack(Material.FIRE_CORAL_BLOCK, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_HORN_CORAL_BLOCK), new ItemStack(Material.HORN_CORAL_BLOCK, 1));
        // Block duplication
        registerRecipe(12, new ItemStack(Material.TUBE_CORAL_BLOCK), new ItemStack(Material.TUBE_CORAL_BLOCK, 2));
        registerRecipe(12, new ItemStack(Material.BRAIN_CORAL_BLOCK), new ItemStack(Material.BRAIN_CORAL_BLOCK, 2));
        registerRecipe(12, new ItemStack(Material.BUBBLE_CORAL_BLOCK), new ItemStack(Material.BUBBLE_CORAL_BLOCK, 2));
        registerRecipe(12, new ItemStack(Material.FIRE_CORAL_BLOCK), new ItemStack(Material.FIRE_CORAL_BLOCK, 2));
        registerRecipe(12, new ItemStack(Material.HORN_CORAL_BLOCK), new ItemStack(Material.HORN_CORAL_BLOCK, 2));

    // Coral
        // Revive for coral
        registerRecipe(9, new ItemStack(Material.DEAD_TUBE_CORAL), new ItemStack(Material.TUBE_CORAL, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_BRAIN_CORAL), new ItemStack(Material.BRAIN_CORAL, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_BUBBLE_CORAL), new ItemStack(Material.BUBBLE_CORAL, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_FIRE_CORAL), new ItemStack(Material.FIRE_CORAL, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_HORN_CORAL), new ItemStack(Material.HORN_CORAL, 1));
        // Coral duplication
        registerRecipe(12, new ItemStack(Material.TUBE_CORAL), new ItemStack(Material.TUBE_CORAL, 2));
        registerRecipe(12, new ItemStack(Material.BRAIN_CORAL), new ItemStack(Material.BRAIN_CORAL, 2));
        registerRecipe(12, new ItemStack(Material.BUBBLE_CORAL), new ItemStack(Material.BUBBLE_CORAL, 2));
        registerRecipe(12, new ItemStack(Material.FIRE_CORAL), new ItemStack(Material.FIRE_CORAL, 2));
        registerRecipe(12, new ItemStack(Material.HORN_CORAL), new ItemStack(Material.HORN_CORAL, 2));

    // Coral fans
        // Medical attention for the fans
        registerRecipe(9, new ItemStack(Material.DEAD_TUBE_CORAL_FAN), new ItemStack(Material.TUBE_CORAL_FAN, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_BRAIN_CORAL_FAN), new ItemStack(Material.BRAIN_CORAL_FAN, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_BUBBLE_CORAL_FAN), new ItemStack(Material.BUBBLE_CORAL_FAN, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_FIRE_CORAL_FAN), new ItemStack(Material.FIRE_CORAL_FAN, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_HORN_CORAL_FAN), new ItemStack(Material.HORN_CORAL_FAN, 1));
        // Fan duplication
        registerRecipe(12, new ItemStack(Material.TUBE_CORAL_FAN), new ItemStack(Material.TUBE_CORAL_FAN, 2));
        registerRecipe(12, new ItemStack(Material.BRAIN_CORAL_FAN), new ItemStack(Material.BRAIN_CORAL_FAN, 2));
        registerRecipe(12, new ItemStack(Material.BUBBLE_CORAL_FAN), new ItemStack(Material.BUBBLE_CORAL_FAN, 2));
        registerRecipe(12, new ItemStack(Material.FIRE_CORAL_FAN), new ItemStack(Material.FIRE_CORAL_FAN, 2));
        registerRecipe(12, new ItemStack(Material.HORN_CORAL_FAN), new ItemStack(Material.HORN_CORAL_FAN, 2));


    }
   

    @Override
	public List<ItemStack> getDisplayRecipes() {
		List<ItemStack> display = new ArrayList<>(); 
        for (MachineRecipe recipe : recipes) {
           display.add(recipe.getInput()[0]);
           if (recipe.getOutput().length > 1) {
            display.add(recipe.getOutput()[1]);
           } else {
            display.add(recipe.getOutput()[0]);
           }
        }
        return display;
	}

	@Override
	protected void setupMenu(BlockMenuPreset preset) {
		for (int slot : BACKGROUND_SLOTS) {
            preset.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int slot : INPUT_BORDER_SLOTS) {
            preset.addItem(slot, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int slot : OUTPUT_BORDER_SLOTS) {
            preset.addItem(slot, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        preset.addItem(getProgressSlot(), new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());
        
        for (int slot : getOutputSlots()) {
            preset.addMenuClickHandler(slot,new ChestMenu.AdvancedMenuClickHandler() {
                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    return cursor.getType().isAir();
                }

                @Override
                public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
                    return false;
                }
            });
        }
	}

	@Override
	protected int[] getInputSlots() {
		return INPUT_SLOTS;
	}

	@Override
	protected int[] getOutputSlots() {
		return OUTPUT_SLOTS;
	}

    @Override
    public ItemStack getProgressBar() {
        return PROGRESS_ITEM;
    }


}
