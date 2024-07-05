package me.profelements.dynatech.items.electric.generators;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import me.profelements.dynatech.DynaTechItems;

import java.util.List;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class HydroGenerator extends SlimefunItem {

    private final int energy;

    public HydroGenerator(ItemGroup itemGroup, int energy, SlimefunItemStack item, RecipeType recipeType,
            ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.energy = energy;

        addItemHandler(onBlockBreak());
    }

    private BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent event, ItemStack arg1, List<ItemStack> arg2) {
                arg2.clear();
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),
                        DynaTechItems.DEGRADED_WATER_MILL);
            }
        };
    }

}
