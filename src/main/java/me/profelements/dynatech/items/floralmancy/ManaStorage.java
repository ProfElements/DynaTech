package me.profelements.dynatech.items.floralmancy;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.bakedlibs.dough.blocks.BlockPosition;
import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.Validate;
import io.github.thebusybiscuit.slimefun4.utils.NumberUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.profelements.dynatech.DynaTech;

public interface ManaStorage {

        int getManaCapacity();
        
        default boolean isFillable() {
            return getManaCapacity() > 0; 
        }

        default int getManaAmount(@Nonnull Location location) {
            Validate.notNull(location, "Location was null!");
            
            if (!isFillable()) {
                return 0; 
            }

            return getManaAmount(new BlockPosition(location)); 
        }

        default int getManaAmount(@Nonnull BlockPosition location) {
            Validate.notNull(location, "Location was null!");
            
            if (!isFillable()) {
                return 0;
            }

            return getManaAmount(location, BlockStorage.getLocationInfo(location.toLocation())); 

        }
        
        default int getManaAmount(@Nonnull ItemStack item) {
            Validate.notNull(item, "Item was null!");

            if (!isFillable()) {
                return 0;
            }

            return getManaAmount(item.getItemMeta());
        }


        default int getManaAmount(@Nonnull BlockPosition location, @Nonnull Config config) {
            Validate.notNull(location, "Location was null!");
            Validate.notNull(config, "Config was null!");

            if (!isFillable()) {
                return 0;
            }
            

            String mana = config.getString("mana-storage"); 

            if (mana != null) {
                return Integer.parseInt(mana); 
            } else {
                return 0;
            }    
        }

        default int getManaAmount(@Nonnull ItemMeta meta) {
            Validate.notNull(meta, "meta was null!");
            
            NamespacedKey key = new NamespacedKey(DynaTech.getInstance(), "mana-storage"); 

            return PersistentDataAPI.getInt(meta, key); 
        }

        default void setManaAmount(@Nonnull BlockPosition location, int manaAmount) {
            Validate.notNull(location, "Location was null!");
            Validate.isTrue(manaAmount > 0, "Mana amount was less then 0") ; 
            Validate.isTrue(manaAmount < getManaCapacity(), "Mana amount was greater then mana capacity"); 
            
            int capacity = getManaCapacity();

            if (capacity > 0) {
                manaAmount = NumberUtils.clamp(0, manaAmount, capacity); 
                
                if (manaAmount != getManaAmount(location)) {
                    BlockStorage.addBlockInfo(location.toLocation(), "mana-storage", String.valueOf(manaAmount), false);

                }
            }
        }

        default void setManaAmount(@Nonnull ItemStack item, int manaAmount) {
            Validate.notNull(item, "Item was null!");
            Validate.isTrue(item.getType() != Material.AIR, "Item was air!");
            Validate.isTrue(manaAmount > 0, "Mana amount was less then 0");
            Validate.isTrue(manaAmount < getManaCapacity(), "Mana amoutn was greater then mana capacity");

            int capacity = getManaCapacity();

            if (capacity > 0) {
                manaAmount = NumberUtils.clamp(0, manaAmount, capacity);

                if (manaAmount != getManaAmount(item)) {
                    NamespacedKey key = new NamespacedKey(DynaTech.getInstance(), "mana-storage"); 
                    
                    PersistentDataAPI.setInt(item.getItemMeta(), key, manaAmount); 
                }
            }
        }

        default void addManaAmount(@Nonnull BlockPosition location, int manaAmount) {
            Validate.notNull(location, "Location was null");
            Validate.isTrue(manaAmount > 0, "Mana amount was less then 0");

            int capacity = getManaCapacity();

            if (capacity > 0) {
                int currManaAmount = getManaAmount(location); 
                
                if (capacity > currManaAmount) {
                    manaAmount = Math.min(capacity, currManaAmount + manaAmount);

                    BlockStorage.addBlockInfo(location.toLocation(), "mana-storage", String.valueOf(manaAmount), false);
                }
            }
        }

        default void addManaAmount(@Nonnull ItemStack item, int manaAmount) {
            Validate.notNull(item, "Item was null!");
            Validate.isTrue(manaAmount > 0, "Mana amount was less then 0!");

            int capacity = getManaCapacity();

            if (capacity > 0) {
                int currManaAmount = getManaAmount(item);

                if (capacity > currManaAmount) {
                    NamespacedKey key = new NamespacedKey(DynaTech.getInstance(), "mana-storage"); 
                    manaAmount = Math.min(capacity, currManaAmount + manaAmount); 

                    PersistentDataAPI.setInt(item.getItemMeta(), key, manaAmount); 
                }
            }
        }

        default void removeManaAmount(@Nonnull BlockPosition location, int manaAmount) {
            Validate.notNull(location, "Location was null");
            Validate.isTrue(manaAmount > 0, "Mana amount was less then 0"); 

            int capacity = getManaCapacity();

            if (capacity > 0) {
                int currManaAmount = getManaAmount(location);

                if (currManaAmount > 0) {
                    manaAmount = Math.max(0, currManaAmount - manaAmount); 
                    
                    BlockStorage.addBlockInfo(location.toLocation(), "mana-storage", String.valueOf(manaAmount), false);
                }
            }
        }


        default void removeManaAmount(@Nonnull ItemStack item, int manaAmount) {
            Validate.notNull(item, "Item was null!");
            Validate.isTrue(manaAmount > 0, "Mana amount was less then 0!");

            int capacity = getManaCapacity();

            if (capacity > 0) {
                int currManaAmount = getManaAmount(item);

                if (currManaAmount > 0) {
                    NamespacedKey key = new NamespacedKey(DynaTech.getInstance(), "mana-storage"); 
                    manaAmount = Math.max(0, currManaAmount - manaAmount); 

                    PersistentDataAPI.setInt(item.getItemMeta(), key, manaAmount); 
                }
            }
        }
}
