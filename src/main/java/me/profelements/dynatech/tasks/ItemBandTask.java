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
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.isValid() || p.isDead()) {
                continue;
            }
            for (ItemStack item : p.getEquipment().getArmorContents()) {
                testItemBand(p, item);
            }
            testItemBand(p, p.getEquipment().getItemInMainHand());
        }
    }
    
    private static void testItemBand(Player p, ItemStack item) {
        if (item != null && item.getType() != Material.AIR && item.hasItemMeta()) {
            String id = PersistentDataAPI.getString(item.getItemMeta(), ItemBand.KEY);

            if (id != null) {
                SlimefunItem sfItem = SlimefunItem.getByID(id);

                if (sfItem instanceof ItemBand) {
                    ItemBand itemBand = (ItemBand) sfItem;

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
