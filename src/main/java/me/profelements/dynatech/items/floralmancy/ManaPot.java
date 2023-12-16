package me.profelements.dynatech.items.floralmancy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.bakedlibs.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.DynaTechItems;

public class ManaPot extends SlimefunItem implements ManaStorage {
        
    public ManaPot() {
        super(DynaTechItems.DT_FLORALMANCY, ITEM, RecipeType.ANCIENT_ALTAR, new ItemStack[] {
            new ItemStack(Material.BRICK), new ItemStack(Material.BRICK), new ItemStack(Material.BRICK), 
            SlimefunItems.MAGIC_LUMP_1, SlimefunItems.MAGIC_LUMP_1, SlimefunItems.MAGIC_LUMP_1,
        });

        addItemHandler(onBlockBreak());
        addItemHandler(onBlockPlace());
    }
    
    private BlockPlaceHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(BlockPlaceEvent e) {
                setManaAmount(new BlockPosition(e.getBlock().getLocation()), getManaAmount(e.getItemInHand()));
            } 
        }; 
    }
    
    private BlockBreakHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack tool, List<ItemStack> drops) {
                final ItemStack itemToDrop = BlockStorage.check(e.getBlock()).getItem().clone();

                setManaAmount(itemToDrop, getManaAmount(e.getBlock().getLocation()));
                setManaLore(itemToDrop); 
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), itemToDrop); 
                e.setDropItems(false);

                BlockStorage.clearBlockInfo(e.getBlock());
            }
        };
    }
    
    private void setManaLore(ItemStack item) {
            final ItemMeta meta = item.getItemMeta();
            final List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>(); 

            lore.add("&fMana: " + getManaAmount(item) + " / " + getManaCapacity()); 

            meta.setLore(lore);
            item.setItemMeta(meta); 
    }


    @Override
    public int getManaCapacity() {
        return 1024;
    }

    private static final NamespacedKey ID = new NamespacedKey(DynaTech.getInstance(), "mana_pot"); 
    public static final SlimefunItemStack ITEM = new SlimefunItemStack(ID.getNamespace().toUpperCase() + ":" + ID.getKey().toUpperCase(), Material.DECORATED_POT, 
            "&fMana Pot",
            "",
            "&fHolds some mana",
            ""
            ); 
}


 
