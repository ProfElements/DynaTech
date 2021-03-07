package me.profelements.dynatech.items.electric.growthchambers;

import java.util.ArrayList;
import java.util.List;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.profelements.dynatech.items.electric.abstracts.AMachine;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GrowthChamberOceanMK2 extends AMachine {
    
    private static int[] BORDER = new int[] {};
    private static int[] BORDER_IN = new int[] {0,8,9,10,11,12,14,15,16,17};
    private static int[] BORDER_OUT = new int[] {18,19,20,21,22,23,24,25,26,27,35,36,44,45,53};
    
    public GrowthChamberOceanMK2(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(9, new ItemStack(Material.LILY_PAD), new ItemStack(Material.LILY_PAD, 9));
        registerRecipe(9, new ItemStack(Material.SEA_PICKLE), new ItemStack(Material.SEA_PICKLE, 9));
        registerRecipe(12, new ItemStack(Material.SEAGRASS), new ItemStack(Material.SEAGRASS, 12));
        registerRecipe(9, new ItemStack(Material.KELP), new ItemStack(Material.KELP, 9));
    // Coral blocks
        // Brings dead coral blocks back to life!
        registerRecipe(9, new ItemStack(Material.DEAD_TUBE_CORAL_BLOCK), new ItemStack(Material.TUBE_CORAL_BLOCK, 3));
        registerRecipe(9, new ItemStack(Material.DEAD_BRAIN_CORAL_BLOCK), new ItemStack(Material.BRAIN_CORAL_BLOCK, 3));
        registerRecipe(9, new ItemStack(Material.DEAD_BUBBLE_CORAL_BLOCK), new ItemStack(Material.BUBBLE_CORAL_BLOCK, 3));
        registerRecipe(9, new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK), new ItemStack(Material.FIRE_CORAL_BLOCK, 3));
        registerRecipe(9, new ItemStack(Material.DEAD_HORN_CORAL_BLOCK), new ItemStack(Material.HORN_CORAL_BLOCK, 3));
        // Block duplication
        registerRecipe(12, new ItemStack(Material.TUBE_CORAL_BLOCK), new ItemStack(Material.TUBE_CORAL_BLOCK, 6));
        registerRecipe(12, new ItemStack(Material.BRAIN_CORAL_BLOCK), new ItemStack(Material.BRAIN_CORAL_BLOCK, 6));
        registerRecipe(12, new ItemStack(Material.BUBBLE_CORAL_BLOCK), new ItemStack(Material.BUBBLE_CORAL_BLOCK, 6));
        registerRecipe(12, new ItemStack(Material.FIRE_CORAL_BLOCK), new ItemStack(Material.FIRE_CORAL_BLOCK, 6));
        registerRecipe(12, new ItemStack(Material.HORN_CORAL_BLOCK), new ItemStack(Material.HORN_CORAL_BLOCK, 6));

    // Coral
        // Revive for coral
        registerRecipe(9, new ItemStack(Material.DEAD_TUBE_CORAL), new ItemStack(Material.TUBE_CORAL, 3));
        registerRecipe(9, new ItemStack(Material.DEAD_BRAIN_CORAL), new ItemStack(Material.BRAIN_CORAL, 3));
        registerRecipe(9, new ItemStack(Material.DEAD_BUBBLE_CORAL), new ItemStack(Material.BUBBLE_CORAL, 3));
        registerRecipe(9, new ItemStack(Material.DEAD_FIRE_CORAL), new ItemStack(Material.FIRE_CORAL, 3));
        registerRecipe(9, new ItemStack(Material.DEAD_HORN_CORAL), new ItemStack(Material.HORN_CORAL, 3));
        // Coral duplication
        registerRecipe(12, new ItemStack(Material.TUBE_CORAL), new ItemStack(Material.TUBE_CORAL, 6));
        registerRecipe(12, new ItemStack(Material.BRAIN_CORAL), new ItemStack(Material.BRAIN_CORAL, 6));
        registerRecipe(12, new ItemStack(Material.BUBBLE_CORAL), new ItemStack(Material.BUBBLE_CORAL, 6));
        registerRecipe(12, new ItemStack(Material.FIRE_CORAL), new ItemStack(Material.FIRE_CORAL, 6));
        registerRecipe(12, new ItemStack(Material.HORN_CORAL), new ItemStack(Material.HORN_CORAL, 6));

    // Coral fans
        // Medical attention for the fans
        registerRecipe(9, new ItemStack(Material.DEAD_TUBE_CORAL_FAN), new ItemStack(Material.TUBE_CORAL_FAN, 3));
        registerRecipe(9, new ItemStack(Material.DEAD_BRAIN_CORAL_FAN), new ItemStack(Material.BRAIN_CORAL_FAN, 3));
        registerRecipe(9, new ItemStack(Material.DEAD_BUBBLE_CORAL_FAN), new ItemStack(Material.BUBBLE_CORAL_FAN, 3));
        registerRecipe(9, new ItemStack(Material.DEAD_FIRE_CORAL_FAN), new ItemStack(Material.FIRE_CORAL_FAN, 3));
        registerRecipe(9, new ItemStack(Material.DEAD_HORN_CORAL_FAN), new ItemStack(Material.HORN_CORAL_FAN, 3));
        // Fan duplication
        registerRecipe(12, new ItemStack(Material.TUBE_CORAL_FAN), new ItemStack(Material.TUBE_CORAL_FAN, 6));
        registerRecipe(12, new ItemStack(Material.BRAIN_CORAL_FAN), new ItemStack(Material.BRAIN_CORAL_FAN, 6));
        registerRecipe(12, new ItemStack(Material.BUBBLE_CORAL_FAN), new ItemStack(Material.BUBBLE_CORAL_FAN, 6));
        registerRecipe(12, new ItemStack(Material.FIRE_CORAL_FAN), new ItemStack(Material.FIRE_CORAL_FAN, 6));
        registerRecipe(12, new ItemStack(Material.HORN_CORAL_FAN), new ItemStack(Material.HORN_CORAL_FAN, 6));

    }

    @Override
    public boolean isGraphical() {
        return true;
    }
    
    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_OCEAN_MK2";
    }


    @Override
    public List<int[]> getBorders() {
        List<int[]> borders = new ArrayList<>();
        borders.add(BORDER);
        borders.add(BORDER_IN);
        borders.add(BORDER_OUT);
        
        return borders;
    }
    
    @Override
    public int[] getInputSlots() {
        return new int[] {1,2,3,4,5,6,7};
    }
    @Override
    public int[] getOutputSlots() {
        return new int[] {28,29,30,31,32,33,34,37,38,39,40,41,42,43,46,47,48,49,50,51,52};
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.CONDUIT);
    }
    
    @Override
    public int getProgressBarSlot() {
        return 13;
    }
    
}
