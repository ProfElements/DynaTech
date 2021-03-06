package me.profelements.dynatech.items.electric;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.electric.abstracts.AMachine;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GrowthChamberEndMK2 extends AMachine {

    public GrowthChamberEndMK2(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(20, new ItemStack[] {new ItemStack(Material.CHORUS_FLOWER)}, new ItemStack[] {new ItemStack(Material.CHORUS_FLOWER , 6), new ItemStack(Material.CHORUS_FRUIT, 24)});

    } 

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.CHORUS_FLOWER);
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_END_MK2";
    }

}
