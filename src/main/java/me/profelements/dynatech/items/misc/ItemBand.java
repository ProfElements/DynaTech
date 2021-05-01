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

import javax.annotation.Nullable;

public class ItemBand extends SlimefunItem {

    public static final NamespacedKey KEY = new NamespacedKey(DynaTech.inst(), "item_band");
    private final PotionEffect[] potionEffects;

    public ItemBand(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, PotionEffect[] potionEffects) {
        super(category, item, recipeType, recipe);
        
        this.potionEffects = potionEffects;
    }

    public PotionEffect[] getPotionEffects() {
        return potionEffects;
    }

    public static boolean containsItemBand(ItemStack item) {
        if (item != null && item.getType() != Material.AIR && item.hasItemMeta()) {
            return PersistentDataAPI.getString(item.getItemMeta(), KEY) != null;
        } else {
            return false;
        }
    }

    @Nullable
    public ItemStack applyToItem(@Nullable ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
           

            ItemMeta im = item.getItemMeta();
            List<String> lore = im.hasLore() ? im.getLore() : new ArrayList<>();
            
            lore.add(ChatColor.WHITE + "Bandaid: " + getPotionEffects()[0].getType().getName());
            PersistentDataAPI.setString(im, KEY, this.getId());

            im.setLore(lore);
            item.setItemMeta(im);
            return item;
        }
        return null;
    }

    @Nullable
    public static ItemStack removeFromItem(@Nullable ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
            ItemMeta im = item.getItemMeta();
            List<String> lore = im.getLore();
            
            im.getPersistentDataContainer().remove(KEY);

            lore.removeIf(line -> line.contains(ChatColor.WHITE + "Bandaid: "));
    
            im.setLore(lore);
            item.setItemMeta(im);

            return item;
        }
        return null;
    }
    
}
