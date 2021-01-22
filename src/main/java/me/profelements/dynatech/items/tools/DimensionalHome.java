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
import me.mrCookieSlime.CSCoreLibPlugin.cscorelib2.data.PersistentDataAPI;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.profelements.dynatech.DynaTech;

public class DimensionalHome extends SlimefunItem {
    
    private NamespacedKey chunkId = new NamespacedKey(DynaTech.getInstance(), "chunk-id");

    private int id = 0;


    public DimensionalHome(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        addItemHandler(onRightClick());
    }

    public ItemUseHandler onRightClick() {
        return new ItemUseHandler() {

			@Override
			public void onRightClick(PlayerRightClickEvent e) {

                if(e.getPlayer().getWorld() == Bukkit.getServer().getWorld("world")) {
                    e.getPlayer().teleport(new Location(Bukkit.getServer().getWorld("dimensionalhome"), 16 * PersistentDataAPI.getInt(e.getItem().getItemMeta(), chunkId) + 8, 65, 16 * 0 + 8));
                } else {
                    Location teleport = e.getPlayer().getBedSpawnLocation() == null ? Bukkit.getServer().getWorld("world").getSpawnLocation() : e.getPlayer().getBedSpawnLocation();
                    e.getPlayer().teleport(teleport);
                }
				e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer()));
				
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
                lore.set(line, lore.get(line).replace("<id>", String.valueOf(getChunkId())));
                PersistentDataAPI.setInt(this.getItem().getItemMeta(), chunkId, id);
                id++;

            }

        im.setLore(lore);
        
        }
        
        return im;
     
    }
}
