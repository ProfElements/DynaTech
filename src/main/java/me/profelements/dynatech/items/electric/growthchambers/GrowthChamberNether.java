package me.profelements.dynatech.items.electric.growthchambers;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GrowthChamberNether extends AMachine {

    public GrowthChamberNether(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(12, new ItemStack(Material.NETHER_WART), new ItemStack(Material.NETHER_WART, 4));
        registerRecipe(9, new ItemStack(Material.WEEPING_VINES), new ItemStack(Material.WEEPING_VINES, 4));
        registerRecipe(9, new ItemStack(Material.TWISTING_VINES), new ItemStack(Material.TWISTING_VINES, 4));
        registerRecipe(9, new ItemStack(Material.CRIMSON_ROOTS), new ItemStack(Material.CRIMSON_ROOTS, 4));
        registerRecipe(9, new ItemStack(Material.WARPED_ROOTS), new ItemStack(Material.WARPED_ROOTS, 4));
        registerRecipe(9, new ItemStack(Material.NETHER_SPROUTS), new ItemStack(Material.NETHER_SPROUTS, 4));

        registerRecipe(30, new ItemStack[] {new ItemStack(Material.CRIMSON_FUNGUS)}, new ItemStack[] {new ItemStack(Material.CRIMSON_FUNGUS, 2), new ItemStack(Material.CRIMSON_STEM, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.WARPED_FUNGUS)}, new ItemStack[] {new ItemStack(Material.WARPED_FUNGUS, 2), new ItemStack(Material.WARPED_STEM, 6)});

    }
    
    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.NETHERRACK);
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_NETHER";
    }

}
