package me.profelements.dynatech.tasks;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.cscorelib2.data.PersistentDataAPI;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.misc.ItemBand;

public class ItemBandTask implements Runnable {


    //The value if not null will be a SlIMEFUN_ID that is an Item
    

    public ItemBandTask() {}

    @Override
    public void run() {
        for ( Player p : Bukkit.getOnlinePlayers() ) {
            if (!p.isValid() || p.isDead()) {
                continue;
            }

        ItemStack[] armor = p.getEquipment().getArmorContents();
        if (armor.length > 0) {
            for (int slot = 0; slot < 4; slot++) {
                ItemStack item = armor[slot];
                
                if (item == null) {
                    continue;
                }

                if (item != null & item.getType() != Material.AIR && item.hasItemMeta() && PersistentDataAPI.getString(item.getItemMeta(), ItemBand.key) != null) {
                    String id = PersistentDataAPI.getString(item.getItemMeta(), ItemBand.key);
                    
                    if(SlimefunItem.getByID(id) != null && SlimefunItem.getByID(id) instanceof ItemBand) {
                        ItemBand itemBand = (ItemBand) SlimefunItem.getByID(id);
                        
                        DynaTech.runSync(() -> {
                            for (PotionEffect pe : itemBand.getPotionEffects()) {
                                p.removePotionEffect(pe.getType());
                                p.addPotionEffect(pe);
                            }
                        });
                        
    
                    }
    
                }
            }
        }

        ItemStack heldItem = p.getEquipment().getItemInMainHand();
        
        if (heldItem != null  && heldItem.getType() != Material.AIR && PersistentDataAPI.getString(heldItem.getItemMeta(), ItemBand.key) != null) {
            String id = PersistentDataAPI.getString(heldItem.getItemMeta(), ItemBand.key);

            SlimefunItem heldItemBand = SlimefunItem.getByID(id);

            if (heldItemBand != null && heldItemBand instanceof ItemBand) {
                ItemBand itemBand = (ItemBand) heldItemBand;

                DynaTech.runSync(() -> {
                    for (PotionEffect pe : itemBand.getPotionEffects()) {
                        p.removePotionEffect(pe.getType());
                        p.addPotionEffect(pe);
                    }
                });
            }

        }

            
        }
    }
    
}
