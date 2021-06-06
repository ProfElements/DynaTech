package me.profelements.dynatech.items.electric.generators;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.blocks.BlockPosition;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.concurrent.TimeUnit;

public class HydroGenerator extends SlimefunItem implements EnergyNetProvider {

    private final int energy;
    private final int capacity;

    private final LoadingCache<BlockPosition, Integer> cachedGeneration = CacheBuilder.newBuilder()
        .refreshAfterWrite(1, TimeUnit.MINUTES)
        .expireAfterAccess(3, TimeUnit.MINUTES)
        .build(new CacheLoader<>() {
            @Override
            public Integer load(@Nonnull BlockPosition key) {
                return fetchOutputForBlock(key);
            }
        });

    public HydroGenerator(Category category, int energy, int capacity, SlimefunItemStack item, RecipeType recipeType,
                          ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        this.energy = energy;
        this.capacity = capacity;
    }

    @Override
    public int getGeneratedOutput(@Nonnull Location location, @Nonnull Config config) {
        final BlockPosition pos = new BlockPosition(location);
        Integer i = cachedGeneration.getIfPresent(pos);

        if (i != null) {
            return i;
        } else {
            int output = fetchOutputForBlock(pos);
            cachedGeneration.put(pos, output);
            return output;
        }
    }

    private int fetchOutputForBlock(@Nonnull BlockPosition position) {
        final Block b = position.getBlock();

        if (b.getType() == Material.COBBLESTONE_WALL || b.getType() == Material.PRISMARINE_WALL) {
            BlockData blockData = PaperLib.getBlockState(b, false).getState().getBlockData();
            if (blockData instanceof Waterlogged) {
                Waterlogged data = (Waterlogged) blockData;
                if (data.isWaterlogged()) {
                    return getEnergyProduction();
                }
            }
        } else {
            // Block has been removed, invalidate the cache
            cachedGeneration.invalidate(position);
        }
        return 0;
    }

    @Override
    public boolean isChargeable() {
        return false;
    }

    public int getEnergyProduction() {
        return energy;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }
}
