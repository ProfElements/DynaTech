package me.profelements.dynatech.items.tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import me.profelements.dynatech.DynaTech;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.List;

///THIS ABSOLUTELY NEEDS TO BE REDONE 
public class DimensionalHome extends SlimefunItem {
    
    private final NamespacedKey chunkId = new NamespacedKey(DynaTech.getInstance(), "chunk-id");

    private int id = 1;
    private boolean idSet = false;
    private final World dimHomeWorld = Bukkit.getServer().getWorld("dimensionalhome");

    public DimensionalHome(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onRightClick());
    }

    public ItemUseHandler onRightClick() {
        return e -> {
            Player p = e.getPlayer();
            Location playerPrevLocation = p.getLocation();

            if (e.getPlayer().getWorld() != dimHomeWorld && idSet) {

                if (doesntContainNewChunkID(e.getItem())) {
                    idSet = false;
                }
                
                PaperLib.teleportAsync(p, new Location(dimHomeWorld, 16 * PersistentDataAPI.getInt(e.getItem().getItemMeta(), chunkId) + 8d, 65, 8));
            } else if (idSet) {

                if (doesntContainNewChunkID(e.getItem())) {
                    idSet = false;
                }

                PaperLib.teleportAsync(p,
                    p.getWorld() != this.dimHomeWorld
                        ? playerPrevLocation
                        : p.getBedSpawnLocation() != null
                        ? p.getBedSpawnLocation()
                        : Bukkit.getWorlds().get(0).getSpawnLocation());
            } else {
                updateLore(e.getItem());
            }

            e.cancel();
        };
    }

    protected boolean doesntContainNewChunkID(@Nonnull ItemStack item) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = im.getLore();

        for (String s : lore) {
            if (s.contains("CHUNK ID: <id>")) {
                return true;
            }
        }

        return false;
    }

    private void updateLore(@Nonnull ItemStack item) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = im.getLore();

        for (int line = 0; line < lore.size(); line++ ) {
            if (lore.get(line).contains("CHUNK ID: <id>")) {
                id++;
                lore.set(line, lore.get(line).replace("<id>", String.valueOf(id)));
                PersistentDataAPI.setInt(im, chunkId, id);
                        
                idSet = true;
            }
    
        }

        im.setLore(lore);
        item.setItemMeta(im);
    }
    
}
