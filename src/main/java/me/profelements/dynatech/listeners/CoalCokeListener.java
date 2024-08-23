package me.profelements.dynatech.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.plugin.Plugin;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.registries.Items;

public class CoalCokeListener implements Listener {

    public CoalCokeListener(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBurn(FurnaceBurnEvent event) {
        SlimefunItem sfItem = SlimefunItem.getByItem(event.getFuel());

        if (sfItem != null && sfItem.getId().equals(Items.COAL_COKE.stack().getItemId())) {
            int burnTime = event.getBurnTime();
            DynaTech.getInstance().getLogger().info("Found Coal Coke, burnTime Original: " + String.valueOf(burnTime)
                    + " burnTime * 8 " + String.valueOf(burnTime * 8));
            event.setBurnTime(burnTime * 8);
        }
    }
}
