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

public class GrowthChamberNetherMK2 extends AMachine {

    public GrowthChamberNetherMK2(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(20, new ItemStack(Material.NETHER_WART), new ItemStack(Material.NETHER_WART, 12));
        registerRecipe(20, new ItemStack(Material.WEEPING_VINES), new ItemStack(Material.WEEPING_VINES, 12));
        registerRecipe(20, new ItemStack(Material.TWISTING_VINES), new ItemStack(Material.TWISTING_VINES, 12));
        registerRecipe(20, new ItemStack(Material.CRIMSON_ROOTS), new ItemStack(Material.CRIMSON_ROOTS, 12));
        registerRecipe(20, new ItemStack(Material.WARPED_ROOTS), new ItemStack(Material.WARPED_ROOTS, 12));
        registerRecipe(20, new ItemStack(Material.NETHER_SPROUTS), new ItemStack(Material.NETHER_SPROUTS, 12));

        registerRecipe(40, new ItemStack[] {new ItemStack(Material.CRIMSON_FUNGUS)}, new ItemStack[] {new ItemStack(Material.CRIMSON_FUNGUS, 6), new ItemStack(Material.CRIMSON_STEM, 18)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.WARPED_FUNGUS)}, new ItemStack[] {new ItemStack(Material.WARPED_FUNGUS, 6), new ItemStack(Material.WARPED_STEM, 18)});

    }
    
    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.NETHERRACK);
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_NETHER_MK2";
    }

}
