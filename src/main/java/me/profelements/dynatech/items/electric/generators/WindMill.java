package me.profelements.dynatech.items.electric.generators;

import java.util.List;

import org.bukkit.Location;
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

public class WindMill extends SlimefunItem implements EnergyNetProvider {

    private final int energyMax;
    private final int energyMin;
    private final int energyCap;
    private final int durability;

    private static final String dura = "durability";

    public WindMill(ItemGroup group, SlimefunItemStack stack, Recipe recipe,
            int energyMax,
            int energyMin,
            int energyCap,
            int durability) {
        super(group, stack, recipe.getRecipeType(), recipe.getInput());

        this.energyMax = energyMax;
        this.energyMin = energyMin;
        this.energyCap = energyCap;
        this.durability = durability;

        addItemHandler(onBlockPlace());
        addItemHandler(onBlockBreak());
    }

    public BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {

            @Override
            public void onPlayerPlace(BlockPlaceEvent e) {
                BlockStorage.addBlockInfo(e.getBlock(), dura, String.valueOf(durability));
            }

        };
    }

    public BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {

            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {

                Location l = e.getBlock().getLocation();
                int durability = Integer.parseInt(BlockStorage.getLocationInfo(l, dura));
                if (durability <= 0) {

                    e.setDropItems(false);
                    String id = BlockStorage.getLocationInfo(l, "id");
                    if (id != null && SlimefunItem.getById(id + "_DEGRADED") != null) {
                        l.getWorld().dropItemNaturally(l, SlimefunItem.getById(id + "_DEGRADED").getItem());
                    }
                }

                BlockStorage.clearBlockInfo(l);
            }

        };
    }

    @Override
    public int getCapacity() {
        return energyCap;
    }

    @Override
    public int getGeneratedOutput(Location l, Config cfg) {
        int energy = 0;

        int durability = this.durability;
        String locDurability = BlockStorage.getLocationInfo(l, dura);
        if (locDurability != null) {
            durability = Integer.parseInt(BlockStorage.getLocationInfo(l, dura));
        }

        if (durability > 0) {
            final int y = Math.max(l.getBlockY(), 1);
            final float percent = (float) y / (float) l.getWorld().getMaxHeight();

            final float yEnergy = Math.round((1 - percent) * (float) energyMin + percent * (float) energyMax);

            energy = (int) yEnergy;
        }
        durability = Math.max(durability - 1, 0);
        BlockStorage.addBlockInfo(l, dura, String.valueOf(durability));

        return energy;
    }

}
