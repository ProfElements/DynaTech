package me.profelements.dynatech.items.tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import org.bukkit.inventory.ItemStack;

public class ElectricalStimulator extends UnplaceableBlock implements Rechargeable {

    public ElectricalStimulator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public float getMaxItemCharge(ItemStack item) {
        return 1024;
    }    

    public float getEnergyComsumption() {
        return 32f;
    }
}
