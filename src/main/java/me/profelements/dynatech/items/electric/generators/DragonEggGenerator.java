package me.profelements.dynatech.items.electric.generators;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class DragonEggGenerator extends SlimefunItem implements EnergyNetProvider {

    public DragonEggGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public int getGeneratedOutput(@Nonnull Location location, @Nonnull Config config) {
        Block dragonEgg = location.getBlock().getRelative(BlockFace.UP);
        if (dragonEgg.getType() == Material.DRAGON_EGG) {
            return 32;
        }

        return 0;
    }

    @Override
    public boolean isChargeable() {
        return false;
    }

    @Override
    public int getCapacity() {
        return 512;
    }
}
