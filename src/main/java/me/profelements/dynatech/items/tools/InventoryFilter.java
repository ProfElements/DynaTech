package me.profelements.dynatech.items.tools;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class InventoryFilter extends SlimefunBackpack {

    public InventoryFilter(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(9, category, item, recipeType, recipe);
    }
    
}
