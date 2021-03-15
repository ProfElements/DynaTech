package me.profelements.dynatech.listeners;

import javax.annotation.Nonnull;

import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.tools.ElectricalStimulator;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

public class ElectricalStimulatorListener implements Listener {
    
    private final ElectricalStimulator electricalStimulator;

    public ElectricalStimulatorListener(@Nonnull DynaTech plugin, @Nonnull ElectricalStimulator electricalStimulator) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        this.electricalStimulator = electricalStimulator;
    }

    @EventHandler
    public void onHungerLoss(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getFoodLevel() < p.getFoodLevel()) {
                feedPlayer((Player) e.getEntity()); 
            }
        }
    }

    @EventHandler
    public void onHungerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.STARVATION) {
            feedPlayer((Player) e.getEntity());
        }
    }

    private void feedPlayer(Player p) {
        if (electricalStimulator == null || electricalStimulator.isDisabled()) {
            return;
        }

        for (ItemStack item : p.getInventory().getStorageContents()) {
            if (item != null && item.getType() == electricalStimulator.getItem().getType() && item.hasItemMeta() && electricalStimulator.isItem(item)) {
                if (SlimefunUtils.canPlayerUseItem(p, item, true)) {
                    p.setFoodLevel(20);
                    p.setSaturation(20f);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1F , 1F);
                    electricalStimulator.removeItemCharge(item,  electricalStimulator.getEnergyComsumption());
                    break;
                } else {
                    return;
                }
            }
        }
    }

}
