package me.profelements.dynatech.items.electric.generators;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;

import javax.annotation.Nonnull;

public class DragonEggGenerator extends SlimefunItem implements EnergyNetProvider {

    public DragonEggGenerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public int getGeneratedOutput(@Nonnull Location location, @Nonnull Config config) {
        Block dragonEgg = location.getBlock().getRelative(BlockFace.UP);
        if (dragonEgg.getType() == Material.DRAGON_EGG) {
            return getEnergyProduction();
        }
        return 0;
    }

    @Override
    public boolean isChargeable() {
        return false;
    }

    public static final int getEnergyProduction() {
        return 32;
    }

    @Override
    public int getCapacity() {
        return 512;
    }
}
