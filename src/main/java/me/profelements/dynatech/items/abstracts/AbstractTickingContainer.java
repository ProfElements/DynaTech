package me.profelements.dynatech.items.abstracts;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;

public abstract class AbstractTickingContainer extends AbstractContainer {
    
    protected AbstractTickingContainer(ItemGroup itemGroup, SlimefunItemStack item ,RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe); 
    }

    @Override
    public void preRegister() {
        super.preRegister();

        addItemHandler(new BlockTicker() {
            @Override
            public boolean isSynchronized() {
                return AbstractTickingContainer.this.isSynchronized();
            }

            @Override
            public void tick(Block b, SlimefunItem item, Config data) {
                BlockMenu menu = BlockStorage.getInventory(b);
                if (menu != null) {
                    AbstractTickingContainer.this.tick(menu, b);
                }
            }

        }); 
    }
    
    protected abstract void tick(BlockMenu menu, Block b); 

    protected boolean isSynchronized() {
        return false;
    }

}
