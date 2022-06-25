package me.profelements.dynatech.items.misc;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;

import com.google.common.base.Preconditions;

import org.bukkit.inventory.ItemStack;

public class Bee extends UnplaceableBlock {
    
    private int speedMultiplier;

    public Bee(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int speedMulitplier) {
        super(itemGroup, item, recipeType, recipe);
        this.speedMultiplier = speedMulitplier;
    }

    public float getSpeedMultipler() {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(int speedMultiplier) {
      Preconditions.checkArgument(speedMultiplier > 0, " The Speed multipler must be greater then 0");  
        this.speedMultiplier = speedMultiplier;
    }
    
}
