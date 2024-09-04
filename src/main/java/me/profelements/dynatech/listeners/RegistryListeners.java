package me.profelements.dynatech.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.registries.Items;
import me.profelements.dynatech.registries.Registries;
import me.profelements.dynatech.registries.Registry;
import me.profelements.dynatech.registries.events.RegistryFreezeEvent;
import me.profelements.dynatech.utils.ItemWrapper;

public class RegistryListeners implements Listener {

    public RegistryListeners(DynaTech plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public <T> void confirmFreeze(RegistryFreezeEvent<T> event) {

        if (event.getRegistryKey().equals(Registries.Keys.ITEMS)) {
            // GRAB STAINLESS STEEL
            Registry<ItemWrapper> items = (Registry<ItemWrapper>) Registry.getByKey(event.getRegistryKey());
            if (items.getKeys().contains(Items.Keys.STAINLESS_STEEL_INGOT)) {
                ItemWrapper wrapped = items.entry(Items.Keys.STAINLESS_STEEL_INGOT);

                SlimefunItemStack itemStackToUse = wrapped.stack();

            }

            if (items.getKeys().contains(Items.Keys.VEX_GEM)) {
                ItemWrapper wrapped = items.entry(Items.Keys.VEX_GEM);

                SlimefunItemStack itemStackToUse = wrapped.stack();

            }
        }

        DynaTech.getInstance().getLogger().info(event.getRegistryKey().key().toString() + " is getting frozen");
    }
}
