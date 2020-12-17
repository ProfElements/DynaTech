package me.profelements.extrastuff.items.machines;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;

import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

import org.bukkit.Material;

import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class AutoKitchen extends AMachine {

    public static final int[] BORDER = {4,5,6,7,8,13,31,40,41,42,43,44};
    private static final int[] BORDER_IN = {0,1,2,3,12,21,30,36,37,38,39};
    private static final int[] BORDER_OUT = {14,15,16,17,23,26,32,33,34,35};

    public AutoKitchen(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.SHEARS);
    }

    @Override
    public int getProgressBarSlot() {
        return 22;
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
    public int[] getOutputSlots() {
        return new int[] {24,25};
    }

    @Override
    public int[] getInputSlots() {
        return new int[] {9,10,11,18,19,20,27,28,29};
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "AUTO_KITCHEN";
    }

    @Override
    public void registerDefaultRecipes() {
        registerRecipe(400, new ItemStack(Material.CARROT), new ItemStack(Material.GOLDEN_CARROT));


    }


}
