package me.profelements.dynatech.items.electric.generators;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.profelements.dynatech.utils.Recipe;

public class EggMill extends SlimefunItem implements EnergyNetProvider {

    private static final String DURABILITY_STRING = "dynatech:durability";

    private final int durabilityMax;
    private final int energyRate;
    private final int energyMax;

    public EggMill(ItemGroup group, SlimefunItemStack stack, Recipe recipe, int durabilityMax, int energyRate,
            int energyMax) {
        super(group, stack, recipe.getRecipeType(), recipe.getInput());

        this.durabilityMax = durabilityMax;
        this.energyRate = energyRate;
        this.energyMax = energyMax;

        addItemHandler(onBlockPlace());
        addItemHandler(onBlockBreak());
    }

    private final BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(BlockPlaceEvent event) {
                BlockStorage.addBlockInfo(event.getBlock(), DURABILITY_STRING, String.valueOf(durabilityMax));

            }
        };
    }

    private final BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {

            @Override
            public void onPlayerBreak(BlockBreakEvent event, ItemStack tool, List<ItemStack> drops) {
                Location l = event.getBlock().getLocation();

                int currDurability = Integer
                        .parseInt(BlockStorage.getLocationInfo(l, DURABILITY_STRING));

                if (currDurability <= 0) {
                    event.setDropItems(false);
                    String id = BlockStorage.getLocationInfo(l, "id");
                    if (id != null && SlimefunItem.getById(id + "_DEGRADED") != null) {
                        ItemStack item = SlimefunItem.getById(id + "_DEGRADED").getItem();
                        l.getWorld().dropItemNaturally(l, item);
                    }
                }

                BlockStorage.clearBlockInfo(l);
            }

        };

    }

    @Override
    public int getCapacity() {
        return this.energyMax;
    }

    @Override
    public int getGeneratedOutput(Location l, Config cfg) {
        int energy = 0;
        int currDurability = Integer
                .parseInt(BlockStorage.getLocationInfo(l, DURABILITY_STRING));

        if (currDurability > 0) {
            Block block = l.getBlock().getRelative(BlockFace.UP);
            if (block.getType().equals(Material.DRAGON_EGG)) {
                energy = this.energyRate;
            }
        }

        BlockStorage.addBlockInfo(l, DURABILITY_STRING, String.valueOf(Math.max(currDurability - 1, 0)));
        return energy;

    }
}
