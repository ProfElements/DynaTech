package me.profelements.dynatech.listeners;

import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.tools.InventoryFilter;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class InventoryFilterListener implements Listener {

    public InventoryFilterListener(@Nonnull DynaTech plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void onPlayerAttemptPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player p) {
            filterInventory(p, e);
        }
    }

    private void filterInventory(Player player, EntityPickupItemEvent event) {
        for (ItemStack stack : player.getInventory().getContents()) {
            if (SlimefunItem.getByItem(stack) instanceof InventoryFilter) {

                List<String> slimefunItems = new ArrayList<>();
                List<ItemStack> regItems = new ArrayList<>();

                PlayerProfile.getBackpack(stack, backpack -> {
                    for (ItemStack bpStack : backpack.getInventory().getContents()) {
                        SlimefunItem item = SlimefunItem.getByItem(bpStack);
                        if (item != null) {
                            slimefunItems.add(item.getId());
                        } else {
                            regItems.add(bpStack);
                        }
                    }
                });

                Item itemEntity = event.getItem();
                ItemStack itemEntityStack = itemEntity.getItemStack();

                for (ItemStack checkStack : regItems) {
                    if (checkStack != null && checkStack.isSimilar(itemEntityStack)) {
                        event.setCancelled(true);
                        break;
                    }
                }

                SlimefunItem item = SlimefunItem.getByItem(itemEntityStack);
                if (item != null
                        && slimefunItems.contains(item.getId())) {
                    event.setCancelled(true);
                }

                break;
            }
        }
    }
}
