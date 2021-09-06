package me.profelements.dynatech.items.electric.growthchambers;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GrowthChamberEnd extends AMachine {

    public GrowthChamberEnd(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(9, new ItemStack[] {new ItemStack(Material.CHORUS_FLOWER)}, new ItemStack[] {new ItemStack(Material.CHORUS_FLOWER , 2), new ItemStack(Material.CHORUS_FRUIT, 8)});

    } 

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.CHORUS_FLOWER);
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_END";
    }

}
