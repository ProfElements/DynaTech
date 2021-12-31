package me.profelements.dynatech.items.tools;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.DynaTechItems;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import java.util.List;

public class DimensionalHome extends SlimefunItem {
    
    private static final NamespacedKey CHUNK_KEY = new NamespacedKey(DynaTech.getInstance(), "chunk-key");
    private static final World DIM_HOME_WORLD = Bukkit.getServer().getWorld("dimensionalhome");
    private final Config CURRENT_HIGHEST_CHUNK_ID = new Config("plugins/DynaTech/current-chunk-highest.yml");
    private int id = CURRENT_HIGHEST_CHUNK_ID.getInt("current-chunk-highest-id");

    public DimensionalHome(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onRightClick());
    }

    public ItemUseHandler onRightClick() {
        return new ItemUseHandler() {
			@Override
			public void onRightClick(PlayerRightClickEvent e) {
			    e.cancel();

                Player p = e.getPlayer();
                ItemStack item = e.getItem();
                int chunkKey = PersistentDataAPI.getInt(item.getItemMeta(), CHUNK_KEY);

                if (SlimefunUtils.isItemSimilar(item, DynaTechItems.DIMENSIONAL_HOME, true)) {
                    if (chunkKey > 0) {
                        if (p.getLocation().getWorld() != DIM_HOME_WORLD) {
                            Location dimHomeLocation = new Location(DIM_HOME_WORLD, 16 * chunkKey + 8d, 65, 8);
                            PaperLib.teleportAsync(p, dimHomeLocation);
                        } else {
                            if (p.getBedSpawnLocation() != null) {
                                PaperLib.teleportAsync(p, p.getBedSpawnLocation());
                            } else {
                                PaperLib.teleportAsync(p, Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
                            }
                        }
                    } else {
                        // Setup ChunkKey
                        updateLore(item);
                    }
                }
			} 
        };
    }

    private void updateLore(@Nonnull ItemStack item) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = im.getLore();

        for (int line = 0; line < lore.size(); line++ ) {
            if (lore.get(line).contains("CHUNK ID: <id>")) {
                id++;
                lore.set(line, lore.get(line).replace("<id>", String.valueOf(id)));
                PersistentDataAPI.setInt(im, CHUNK_KEY, id);
                
                // THIS IS PROBABLY BAD AND A BAD WAY TO KEEP AN CHUNK ID
                CURRENT_HIGHEST_CHUNK_ID.setValue("current-chunk-highest-id", id);
                CURRENT_HIGHEST_CHUNK_ID.save();
            }
    
        }

        im.setLore(lore);
        item.setItemMeta(im);
    }
    
}
