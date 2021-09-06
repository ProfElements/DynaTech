package me.profelements.dynatech.listeners;

import io.github.thebusybiscuit.slimefun4.api.player.PlayerBackpack;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.tools.InventoryFilter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

public class InventoryFilterListener implements Listener {
    
    private final InventoryFilter inventoryFilter;
    private final Set<Material> blacklistedMaterials = EnumSet.noneOf(Material.class);

    public InventoryFilterListener(@Nonnull DynaTech plugin, @Nonnull InventoryFilter inventoryFilter) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.inventoryFilter = inventoryFilter;
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if(e.getEntity() instanceof Player) {
            checkAndFilter((Player) e.getEntity());
        }
    }

    private void checkAndFilter(Player p) {
        if (inventoryFilter != null && !inventoryFilter.isDisabled()) {
            for (ItemStack item : p.getInventory().getContents()) {
                if (item != null && item.getType() == inventoryFilter.getItem().getType() && item.hasItemMeta() && inventoryFilter.isItem(item)) {
                    if (SlimefunUtils.canPlayerUseItem(p, item, true)) {
                        PlayerProfile.getBackpack(item, backpack -> DynaTech.runSync(() -> filterInventory(p, backpack)));
                    }
                }
            }
        }
    }

    private void filterInventory(@Nonnull Player p, @Nonnull PlayerBackpack backpack) {
        List<String> blacklistedStrings = new ArrayList<String>();
        if (backpack.getInventory().isEmpty()) {
            blacklistedMaterials.clear();
            blacklistedStrings.clear();
        }

        for (ItemStack item : backpack.getInventory().getContents()) {
            if (item != null && item.getType() != Material.AIR) {
                //Leaving this here to dont have to deal with checking if its using a default or custom name ie if they are farming custom items like SfItems.
                blacklistedMaterials.add(item.getType());
                blacklistedStrings.add(item.getItemMeta().getDisplayName());
            }
        }

        //CANT DROP AIR SO HAVE TO ITERATE THROUGH THE INVENTORY
        Inventory inv = p.getInventory();
        for (ItemStack item : inv.getStorageContents()) {
            if (item != null && blacklistedMaterials.contains(item.getType()) && blacklistedStrings.contains(item.getItemMeta().getDisplayName())) {
                    inv.remove(item);
                    break;
            }
        }
    }
    
}
