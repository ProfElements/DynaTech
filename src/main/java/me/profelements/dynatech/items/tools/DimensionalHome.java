package me.profelements.dynatech.items.tools;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.data.PersistentDataAPI;
import me.profelements.dynatech.DynaTech;

public class DimensionalHome extends SlimefunItem {
    
    private NamespacedKey chunkId = new NamespacedKey(DynaTech.getInstance(), "chunk-id");

    private int id = 1;
    private boolean idSet = false;


    public DimensionalHome(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        addItemHandler(onRightClick());
    }

    public ItemUseHandler onRightClick() {
        return new ItemUseHandler() {

			@Override
			public void onRightClick(PlayerRightClickEvent e) {
                
                if (!idSet) { 
                    e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer())); 
                    idSet = true;
                }

                if(e.getPlayer().getWorld() != Bukkit.getServer().getWorld("dimensionalhome")) {
                    PaperLib.teleportAsync(e.getPlayer(), new Location(Bukkit.getServer().getWorld("dimensionalhome"), 16 * PersistentDataAPI.getInt(e.getItem().getItemMeta(), chunkId) + 8, 65, 16 * 0 + 8));
                } else {
                    PaperLib.teleportAsync(e.getPlayer(), e.getPlayer().getBedSpawnLocation() == null ? e.getPlayer().getBedSpawnLocation() : Bukkit.getServer().getWorld("world").getSpawnLocation());
                }

                e.cancel();
			}
        };
    }

    public int getChunkId() {
        return id;
    }

    protected ItemMeta updateLore(ItemStack item, Player p) {
        ItemMeta im = item.getItemMeta();

        if (!im.hasLore()) {
            throw new IllegalArgumentException("This item does not have any lore!");
        }

        List<String> lore = im.getLore();

        for (int line = 0; line < lore.size(); line++ ) {
            if (lore.get(line).contains("CHUNK ID: <id>")) {
                id++;
                lore.set(line, lore.get(line).replace("<id>", String.valueOf(id)));
                PersistentDataAPI.setInt(this.getItem().getItemMeta(), chunkId, id);

            }

        im.setLore(lore);
        
        }
        
        return im;
     
    }
}
