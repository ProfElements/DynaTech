package me.profelements.extrastuff;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.profelements.extrastuff.items.backpacks.PicnicBasket;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class ExtraStuffItemsSetup {

    private ExtraStuffItemsSetup() {}

    public static final void setup(@Nonnull ExtraStuff plugin) {

        //Backpacks
        new PicnicBasket(27, ExtraStuffItems.extraStuff, ExtraStuffItems.PICNIC_BASKET, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.CLOTH,
                        new ItemStack(Material.BAMBOO), SlimefunItems.COOLER, new ItemStack(Material.BAMBOO),
                        SlimefunItems.HEATING_COIL, new ItemStack(Material.BAMBOO), SlimefunItems.COOLING_UNIT
                }).register(plugin);

    }

}
