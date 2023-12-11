package me.profelements.dynatech.listeners;

import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.DynaTechItems;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class InventoryFilterListener implements Listener {
    
    public InventoryFilterListener(@Nonnull DynaTech plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if(e.getEntity() instanceof Player p) {
            filterInv(p, e);
        }
    }
    
    private void filterInv(@Nonnull Player p, @Nonnull EntityPickupItemEvent e) {
        List<ItemStack> itemBlackList = new ArrayList<>();
        for (ItemStack item : p.getInventory().getContents()) {
            if (SlimefunUtils.isItemSimilar(item,  DynaTechItems.INVENTORY_FILTER, true, false)) {
                PlayerProfile.getBackpack(item, backpack -> {

                        for (ItemStack bpItem : backpack.getInventory().getContents()) {
                            if (bpItem != null && bpItem.getType() != Material.AIR) {
                                itemBlackList.add(item);
                            }
                        }
    
                });
            }
            break;
        }
            
        //Clear and add back drops if not filtered by Inventory filtered
        for (ItemStack item : itemBlackList) {
            if (SlimefunUtils.isItemSimilar(item, e.getItem().getItemStack(), true, false)) {
                Item itemEnt = e.getItem();
                itemEnt.remove();
            }
            break;
        }   
    }
}
