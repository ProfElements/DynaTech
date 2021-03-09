package me.profelements.dynatech.items.electric.generators;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.profelements.dynatech.DynaTechItems;
import me.profelements.dynatech.items.electric.abstracts.AMachineGenerator;

import javax.annotation.Nonnull;

public class StardustReactor extends AMachineGenerator {

    public StardustReactor(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

    }

    @Override
    protected void registerDefaultFuelTypes() {
        registerFuel(new MachineFuel(32, DynaTechItems.STAR_DUST));
    }

    @Nonnull
    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.IRON_CHESTPLATE);
    }

    @Override
    public String getMachineIdentifier() {
        return "STARDUST_REACTOR";
    }

}
