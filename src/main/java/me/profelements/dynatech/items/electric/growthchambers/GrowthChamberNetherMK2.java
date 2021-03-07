package me.profelements.dynatech.items.electric.growthchambers;

import java.util.ArrayList;
import java.util.List;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.profelements.dynatech.items.electric.abstracts.AMachine;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GrowthChamberNetherMK2 extends AMachine {
    
    private static int[] BORDER = new int[] {};
    private static int[] BORDER_IN = new int[] {0,8,9,10,11,12,14,15,16,17};
    private static int[] BORDER_OUT = new int[] {18,19,20,21,22,23,24,25,26,27,35,36,44,45,53};
    
    public GrowthChamberNetherMK2(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(12, new ItemStack(Material.NETHER_WART), new ItemStack(Material.NETHER_WART, 12));
        registerRecipe(9, new ItemStack(Material.WEEPING_VINES), new ItemStack(Material.WEEPING_VINES, 12));
        registerRecipe(9, new ItemStack(Material.TWISTING_VINES), new ItemStack(Material.TWISTING_VINES, 12));
        registerRecipe(9, new ItemStack(Material.CRIMSON_ROOTS), new ItemStack(Material.CRIMSON_ROOTS, 12));
        registerRecipe(9, new ItemStack(Material.WARPED_ROOTS), new ItemStack(Material.WARPED_ROOTS, 12));
        registerRecipe(9, new ItemStack(Material.NETHER_SPROUTS), new ItemStack(Material.NETHER_SPROUTS, 12));

        registerRecipe(30, new ItemStack[] {new ItemStack(Material.CRIMSON_FUNGUS)}, new ItemStack[] {new ItemStack(Material.CRIMSON_FUNGUS, 6), new ItemStack(Material.CRIMSON_STEM, 18), new ItemStack(Material.SHROOMLIGHT, 6), new ItemStack(Material.NETHER_WART_BLOCK, 12)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.WARPED_FUNGUS)}, new ItemStack[] {new ItemStack(Material.WARPED_FUNGUS, 6), new ItemStack(Material.WARPED_STEM, 18), new ItemStack(Material.SHROOMLIGHT, 6), new ItemStack(Material.WARPED_WART_BLOCK, 12)});

    }

    @Override
    public boolean isGraphical() {
        return true;
    }
    
    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_NETHER_MK2";
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
        return new ItemStack(Material.NETHERRACK);
    }
    
    @Override
    public int getProgressBarSlot() {
        return 13;
    }
    
}
