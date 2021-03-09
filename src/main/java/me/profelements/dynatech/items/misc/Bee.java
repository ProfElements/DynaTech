package me.profelements.dynatech.items.misc;

import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class Bee extends UnplaceableBlock {
    
    private int speedMultiplier;

    public Bee(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int speedMulitplier) {
        super(category, item, recipeType, recipe);
        this.speedMultiplier = speedMulitplier;
    }

    public float getSpeedMultipler() {
        return speedMultiplier;
    }

    public void setSpeedMultiplier(int speedMultiplier) {
        Validate.isTrue(speedMultiplier > 0, "The Speed Multiplier must be greater then 0");
        this.speedMultiplier = speedMultiplier;
    }
    
}
