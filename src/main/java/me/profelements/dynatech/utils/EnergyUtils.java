package me.profelements.dynatech.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.bakedlibs.dough.blocks.BlockPosition;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

public class EnergyUtils {

    private EnergyUtils() {
    }

    public static final int moveEnergyFromTo(BlockPosition from, BlockPosition to, int fromEnergyRate,
            int toEnergyMax) {
        Location fromLocation = from.toLocation();
        Location toLocation = to.toLocation();
        String energyKey = "energy-charge";

        String fromEnergyAmount = BlockStorage.getLocationInfo(fromLocation, energyKey);
        String toEnergyAmount = BlockStorage.getLocationInfo(toLocation, energyKey);
        if (fromEnergyAmount == null || toEnergyAmount == null) {
            return 0;
        }

        int fromEnergy = Integer.parseInt(fromEnergyAmount);
        int toEnergy = Integer.parseInt(toEnergyAmount);

        int energyToTransfer = Math.min(fromEnergyRate, Math.min(toEnergyMax - toEnergy, fromEnergy));

        int newFromEnergy = fromEnergy - energyToTransfer;
        int newToEnergy = toEnergy + energyToTransfer;

        BlockStorage.addBlockInfo(fromLocation, energyKey, String.valueOf(newFromEnergy));
        BlockStorage.addBlockInfo(toLocation, energyKey, String.valueOf(newToEnergy));

        return energyToTransfer;
    }

    public static final void moveInventoryFromTo(BlockPosition from, BlockPosition to, int[] fromSlots, int[] toSlots) {
        BlockMenu fromMenu = BlockStorage.getInventory(from.toLocation());
        BlockMenu toMenu = BlockStorage.getInventory(to.toLocation());
        if (fromMenu == null || toMenu == null) {
            return;
        }

        Map<Integer, ItemStack> itemsToTransfer = new HashMap<Integer, ItemStack>(fromSlots.length);
        for (int slot : fromSlots) {
            ItemStack stack = fromMenu.getItemInSlot(slot);

            if (stack == null) {
                continue;
            }

            itemsToTransfer.put(slot, stack);
        }

        if (itemsToTransfer.isEmpty()) {
            return;
        }

        List<Integer> emptySlots = new ArrayList<>(toSlots.length);
        for (int slot : toSlots) {
            ItemStack stack = toMenu.getItemInSlot(slot);
            // Skips currently occupied slots (should probably do this better
            if (stack != null) {
                continue;
            }

            emptySlots.add(slot);
        }

        Iterator<Map.Entry<Integer, ItemStack>> entryIter = itemsToTransfer.entrySet().iterator();
        for (int slot : emptySlots) {
            if (!entryIter.hasNext()) {
                return;
            }

            Map.Entry<Integer, ItemStack> entry = entryIter.next();
            // if (entry == null) {
            // return;
            // }

            int fromSlot = entry.getKey();
            ItemStack item = entry.getValue();

            toMenu.toInventory().setItem(slot, item);
            fromMenu.replaceExistingItem(fromSlot, new ItemStack(Material.AIR, 0));
        }

        fromMenu.markDirty();
        toMenu.markDirty();
    }
}
