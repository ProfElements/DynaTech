package me.profelements.extrastuff.setup;


import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.profelements.extrastuff.ExtraStuff;
import me.profelements.extrastuff.ExtraStuffItems;
import me.profelements.extrastuff.items.backpacks.PicnicBasket;
import me.profelements.extrastuff.items.machines.AutoKitchen;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;


public class ExtraStuffItemsSetup {

    private ExtraStuffItemsSetup() {}

    public static final void setup(@Nonnull ExtraStuff plugin) {

        //Materials
        new SlimefunItem(ExtraStuffItems.extraStuff, ExtraStuffItems.STAINLESS_STEEL, RecipeType.SMELTERY,
                new ItemStack[] {
                    new ItemStack(Material.IRON_INGOT), SlimefunItems.IRON_DUST, SlimefunItems.ZINC_DUST,
                        null, null, null,
                        null, null, null,
                }).register(plugin);

        //Backpacks
        new PicnicBasket(27, ExtraStuffItems.extraStuff, ExtraStuffItems.PICNIC_BASKET, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.CLOTH,
                        new ItemStack(Material.BAMBOO), SlimefunItems.COOLER, new ItemStack(Material.BAMBOO),
                        SlimefunItems.HEATING_COIL, new ItemStack(Material.BAMBOO), SlimefunItems.COOLING_UNIT
                }).register(plugin);


        //Machines
        new AutoKitchen(ExtraStuffItems.extraStuff, ExtraStuffItems.AUTO_KITCHEN, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.BRICK), SlimefunItems.ELECTRIC_FURNACE, new ItemStack(Material.BRICK),
                        ExtraStuffItems.STAINLESS_STEEL, SlimefunItems.GOLD_24K_BLOCK, ExtraStuffItems.STAINLESS_STEEL,
                        new ItemStack(Material.TERRACOTTA), new ItemStack(Material.TERRACOTTA), new ItemStack(Material.TERRACOTTA)

                })
                .setEnergyCapacity(512)
                .setEnergyConsumption(16)
                .setProcessingSpeed(1)
                .register(plugin);

    }




}
