package me.profelements.dynatech.items.tools;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class ElectricalStimulator extends UnplaceableBlock implements Rechargeable {

    public ElectricalStimulator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public float getMaxItemCharge(ItemStack item) {
        return 1024;
    }    

    public float getEnergyComsumption() {
        return 32f;
    }
}
