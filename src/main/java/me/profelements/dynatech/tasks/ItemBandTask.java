package me.profelements.dynatech.tasks;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.misc.ItemBand;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

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

    private static void testItemBand(@Nonnull Player p, @Nullable ItemStack item) {
        if (item != null && item.getType() != Material.AIR && item.hasItemMeta()) {
            String id = PersistentDataAPI.getString(item.getItemMeta(), ItemBand.KEY);

            if (id != null) {
                SlimefunItem sfItem = SlimefunItem.getById(id);

                if (sfItem instanceof ItemBand) {
                    ItemBand itemBand = (ItemBand) sfItem;

                    DynaTech.runSync(() -> {
                        for (PotionEffect pe : itemBand.getPotionEffects()) {
                            if (pe.getType() == PotionEffectType.HEALTH_BOOST)
                            {
                                double health = p.getHealth();
                                p.addPotionEffect(pe);
                                if (health > p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
                                    p.setHealth(p.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                                } else {
                                    p.setHealth(health);
                                }
                                
                            } else {
                                p.addPotionEffect(pe);
                            }
                        }
                    });
                }
            }
        }
    }

}
