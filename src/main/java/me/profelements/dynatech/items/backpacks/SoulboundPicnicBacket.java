package me.profelements.dynatech.items.backpacks;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Soulbound;

public class SoulboundPicnicBacket extends PicnicBasket implements Soulbound {
    
	@ParametersAreNonnullByDefault
    public SoulboundPicnicBacket(int size, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(size, itemGroup, item, recipeType, recipe);
	}

}
