package me.profelements.dynatech.attributes;

import java.util.logging.Level;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import io.github.bakedlibs.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.core.attributes.ItemAttribute;
import io.github.thebusybiscuit.slimefun4.utils.NumberUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.profelements.dynatech.DynaTech;

/**
 * This interface, when attached to a class that inherits {@link SlimefunItem}, marks 
 * the item as an liquid containter.
 * This lets the item interact with registered liquids.
 *
 */
public interface LiquidStorage extends ItemAttribute {
    
    int getLiquidCapacity();

    default boolean isFillable() {
        return getLiquidCapacity() > 0;
    }

    default int getLiquidAmount(@Nonnull BlockPosition l) {
        if (!isFillable()) {
            return 0;
        }

        return getLiquidAmount(l, BlockStorage.getLocationInfo(l.toLocation()));
    }

    //TODO: Move from CSCorelib Config to Dough Config
    default int getLiquidAmount(@Nonnull BlockPosition l, Config config) {
        Preconditions.checkNotNull(l, "Location was null");
        Preconditions.checkNotNull(config, "Config was null");

        if (!isFillable()) {
            return 0;
        }

        String fluidAmount = config.getString("fluid-amount");

        if (fluidAmount != null) {
            return Integer.parseInt(fluidAmount);
        } else {
            return 0;
        }
    }

    default String getLiquid(@Nonnull BlockPosition l) {
        return getLiquid(l, BlockStorage.getLocationInfo(l.toLocation()));
    }

    default String getLiquid(@Nonnull BlockPosition l, @Nonnull Config config) {
        Preconditions.checkNotNull(l, "Location was null");
        Preconditions.checkNotNull(config, "Config was null");

        String fluidName = config.getString("fluid-name");

        if (fluidName != null) {
            return fluidName;
        } else {
            return "NO_LIQUID";
        }
    }

    default void setLiquidAmount(@Nonnull BlockPosition l, int liquidAmount) {
        Preconditions.checkNotNull("Location was null");
        Preconditions.checkArgument(liquidAmount >= 0, "The fluid amount must be set to 0 or more");

        try {
            int liquidCapacity = getLiquidCapacity();

            if (liquidCapacity > 0) {
                liquidAmount = NumberUtils.clamp(0, liquidAmount, liquidCapacity);

                if (liquidAmount != getLiquidAmount(l)) {
                    BlockStorage.addBlockInfo(l.toLocation(), "fluid-amount", String.valueOf(liquidAmount), false);
                }
            }
        } catch (Exception | LinkageError x) {
            DynaTech.getInstance().getLogger().log(Level.SEVERE, x,() -> "Exception while trying to set the fluid-amount for \"" + getId() + "\" at " + l);
        }
    }
    
    default void addLiquidAmount(@Nonnull BlockPosition l, int liquidAmount) {
        Preconditions.checkNotNull("Location was null");
        Preconditions.checkArgument(liquidAmount > 0, "The fluid amount must be greater then 0");

        try {
            int liquidCapacity = getLiquidCapacity();

            if (liquidCapacity > 0) {
                int currentLiquidAmount = getLiquidAmount(l);

                if (currentLiquidAmount < liquidCapacity) {
                    int newLiquidAmount = Math.min(liquidCapacity, currentLiquidAmount + liquidAmount);
                    BlockStorage.addBlockInfo(l.toLocation(), "fluid-amount", String.valueOf(newLiquidAmount), false);
                }
            }
        } catch (Exception | LinkageError x) {
            DynaTech.getInstance().getLogger().log(Level.SEVERE, x,() -> "Exception while trying to add an fluid-amount for \"" + getId() + "\" at " + l);
        }
    }

    default void removeLiquidAmount(@Nonnull BlockPosition l, int liquidAmount) {
        Preconditions.checkNotNull("Location was null");
        Preconditions.checkArgument(liquidAmount > 0, "The fluid amount must be greater then 0");
        
        try {
            int liquidCapacity = getLiquidCapacity();
            if (liquidCapacity > 0) {
                int currentLiquidAmount = getLiquidAmount(l);

                if (currentLiquidAmount > 0) {
                    int newLiquidAmount = Math.max(0, currentLiquidAmount - liquidAmount);
                    BlockStorage.addBlockInfo(l.toLocation(), "fluid-amount", String.valueOf(newLiquidAmount), false);
                }
            }

        } catch (Exception | LinkageError x) {
            DynaTech.getInstance().getLogger().log(Level.SEVERE, x,() -> "Exception while trying to add an fluid-amount for \"" + getId() + "\" at " + l);
        }

    }

    default void setLiquid(@Nonnull BlockPosition l, @Nullable String fluidName) {
        Preconditions.checkNotNull(l, "Location was null");

        try {
            int liquidCapacity = getLiquidCapacity();
            
            //changing fluids must happen when no other fluid is in the block
            if (liquidCapacity == 0) {
                if (fluidName == null) {
                    BlockStorage.addBlockInfo(l.toLocation(), "fluid-name", "NO_LIQUID", false);
                } else {
                    BlockStorage.addBlockInfo(l.toLocation(), "fluid-name", fluidName, false);
                }
            }
        } catch (Exception | LinkageError x) {
            DynaTech.getInstance().getLogger().log(Level.SEVERE, x,() -> "Exception while trying to set the fluid-name for \"" + getId() + "\" at " + l);
        }
    }
}
