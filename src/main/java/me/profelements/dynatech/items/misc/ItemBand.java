package me.profelements.dynatech.items.misc;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.data.PersistentDataAPI;
import me.profelements.dynatech.DynaTech;

public class ItemBand extends SlimefunItem {

    public static final NamespacedKey key = new NamespacedKey(DynaTech.getInstance(), "item_band");
    private final PotionEffect[] potionEffects;

    public ItemBand(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, PotionEffect[] potionEffects) {
        super(category, item, recipeType, recipe);
        
        this.potionEffects = potionEffects;
    }

    public PotionEffect[] getPotionEffects() {
        return potionEffects;
    }

    public static boolean containsItemBand(ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
            return PersistentDataAPI.getString(item.getItemMeta(), key) != null;
        } else {
            return false;
        }
    }

    public ItemStack applyToItem(ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
           

            ItemMeta im = item.getItemMeta();
            List<String> lore = im.hasLore() ? im.getLore() : new ArrayList<>();
            
            lore.add(ChatColor.WHITE + "Bandaid: " + getPotionEffects()[0].getType().getName());
            PersistentDataAPI.setString(im, key, this.getId());

            im.setLore(lore);
            item.setItemMeta(im);
            return item;
        }
        return null;
    }

    public static ItemStack removeFromItem( ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
            ItemMeta im = item.getItemMeta();
            List<String> lore = im.getLore();
            
            im.getPersistentDataContainer().remove(key);
            

            for (int line = 0; line < lore.size(); line++ ) {
                if (lore.get(line).contains(ChatColor.WHITE + "Bandaid: ")) {
                    lore.remove(line);
                }
            }
    
            im.setLore(lore);
            item.setItemMeta(im);

            return item;
        }
            return null;
    }
    
}
