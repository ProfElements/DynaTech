package me.profelements.dynatech.items.electric.generators;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.profelements.dynatech.items.electric.abstracts.AMachineGenerator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;

import javax.annotation.Nonnull;

public class HydroGenerator extends AMachineGenerator {

    public HydroGenerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public int getGeneratedOutput(@Nonnull Location location, @Nonnull Config config) {
        BlockData blockData = PaperLib.getBlockState(location.getBlock(), false).getState().getBlockData();

        if (blockData instanceof Waterlogged) {
            Waterlogged data = (Waterlogged) blockData;
            if (data.isWaterlogged()) {
                return getEnergyProduction();
            }

        }
        return 0;
    }

    @Override
    public boolean isChargeable() {
        return false;
    }

    @Override
    public String getMachineIdentifier() {
        return "WATER_MILL";
    }

    @Nonnull
    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.DRAGON_EGG);
    }

    @Override
    public boolean isGraphical() {
        return false;
    }
}
