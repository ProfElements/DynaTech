package me.profelements.dynatech.items.machines;

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Location;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class HydroGenerator extends SlimefunItem implements EnergyNetProvider {

    public HydroGenerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public int getGeneratedOutput(@Nonnull Location location, @Nonnull Config config) {
        if (location.getBlock().getBlockData() instanceof Waterlogged) {
            Waterlogged data = (Waterlogged) location.getBlock().getBlockData();
            if (data.isWaterlogged()) {
                return getEnergyGeneration();
            }

        }
        return 0;
    }

    private int getEnergyGeneration() {
        return 16;
    }

    @Override
    public int getCapacity() {
        return 128;
    }

    @Override
    public boolean isChargeable() {
        return false;
    }


}
