package me.profelements.dynatech.items.abstracts;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;

public abstract class AbstractTicker extends SlimefunItem {
    
    protected AbstractTicker(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe); 
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return AbstractTicker.this.isSynchronized();
            }

            @Override
            public void tick(Block b, SlimefunItem item, Config data) {
                if (checkTickPreconditions(b)) {
                    AbstractTicker.this.tick(b, item);
                    onTickFinish(b); 
                }

            }
        });

        addItemHandler(new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack item, List<ItemStack> drops) {
                    onBreak(e, e.getBlock().getLocation()); 
            }
        });
        
        addItemHandler(new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(BlockPlaceEvent e) {
                    onPlace(e, e.getBlockPlaced()); 
            }
        });

    }
    
    protected void onBreak(BlockBreakEvent e, Location l) {};

    protected void onPlace(BlockPlaceEvent e, Block blockPlaced) {};

    protected boolean checkTickPreconditions(Block b) { return true; };
    protected boolean onTickFinish(Block b) { return true; }  

    protected abstract void tick(Block b, SlimefunItem item); 

    protected boolean isSynchronized() {
        return false;
    }
}
