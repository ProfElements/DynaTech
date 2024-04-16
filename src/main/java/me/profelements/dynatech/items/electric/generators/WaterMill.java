package me.profelements.dynatech.items.electric.generators;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.Waterlogged;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import io.github.bakedlibs.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.utils.Recipe;

public class WaterMill extends SlimefunItem implements EnergyNetProvider {

    private final HashMap<BlockPosition, Integer> durabilityMap = new HashMap<>();
    private final HashMap<BlockPosition, Boolean> hasWaterMap = new HashMap<>();
    private final int energyCapacity;
    private final int energyOutAmount;
    private final int durabilityDefault;

    public WaterMill(ItemGroup group, SlimefunItemStack stack, Recipe recipe, int energyCapacity, int energyOutAmount,
            int durabilityDefault) {
        super(group, stack, recipe.getRecipeType(), recipe.getInput());

        this.energyCapacity = energyCapacity;
        this.energyOutAmount = energyOutAmount;
        this.durabilityDefault = durabilityDefault;

        addItemHandler(onBlockPlace());
        addItemHandler(onBlockBreak());
    }

    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {

            @Override
            public void onPlayerPlace(BlockPlaceEvent event) {
                BlockStorage.addBlockInfo(event.getBlock(), "durability", String.valueOf(durabilityDefault));
            }

        };
    }

    private BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent event, ItemStack arg1, List<ItemStack> arg2) {
                event.setDropItems(false);

                final Location l = event.getBlock().getLocation();
                final BlockPosition p = new BlockPosition(l);
                int durability = durabilityMap.get(p);

                if (durability == 0) {
                    String id = BlockStorage.getLocationInfo(l, "id");
                    if (id != null && SlimefunItem.getById(id + "_DEGRADED") != null) {
                        l.getWorld().dropItemNaturally(l, SlimefunItem.getById(id + "_DEGRADED").getItem());

                    }
                }

                durabilityMap.remove(p);
                hasWaterMap.remove(p);
                BlockStorage.clearBlockInfo(l);
            }
        };
    }

    @Override
    public int getGeneratedOutput(Location l, Config c) {
        final BlockPosition pos = new BlockPosition(l);
        final Block block = pos.getBlock();
        int energyAmount = 0;

        int durabilityDef = this.durabilityDefault;
        String durabilityStr = BlockStorage.getLocationInfo(l, "durability");

        if (durabilityStr != null) {
            durabilityDef = Integer.parseInt(durabilityStr);
        }

        int durability = durabilityMap.getOrDefault(pos, durabilityDef);
        boolean hasWater = hasWaterMap.getOrDefault(pos, false);
        if (DynaTech.getInstance().getTickInterval() % 600 == 0 || (durability == 2500 && hasWater == false)) {
            if (durability > 0 && block.getType() == Material.COBBLESTONE_WALL
                    || block.getType() == Material.PRISMARINE_WALL) {
                final BlockData data = PaperLib.getBlockState(block, false).getState().getBlockData();
                if (data instanceof Waterlogged wl && wl.isWaterlogged()) {
                    hasWaterMap.put(pos, true);
                    energyAmount = this.energyOutAmount;
                } else {
                    hasWaterMap.put(pos, false);
                }
            }
        } else {
            if (hasWater == true && durability > 0) {
                energyAmount = this.energyOutAmount;
            } else {
                hasWaterMap.put(pos, false);
            }
        }

        int storageDurability = Math.max(durability - 1, 0);
        BlockStorage.addBlockInfo(l, "durability", String.valueOf(storageDurability));
        durabilityMap.put(pos, storageDurability);

        return energyAmount;
    }

    @Override
    public int getCapacity() {
        return this.energyCapacity;
    }

}
