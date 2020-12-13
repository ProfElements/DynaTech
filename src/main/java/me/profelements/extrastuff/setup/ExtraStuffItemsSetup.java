package me.profelements.extrastuff;

import io.github.thebusybiscuit.exoticgarden.ExoticGarden;
import io.github.thebusybiscuit.exoticgarden.items.Kitchen;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.profelements.extrastuff.items.backpacks.PicnicBasket;
import me.profelements.extrastuff.items.machines.AutoKitchen;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExtraStuffItemsSetup {

    private ExtraStuffItemsSetup() {}

    public static final void setup(@Nonnull ExtraStuff plugin) {

        //Materials
        new SlimefunItem(ExtraStuffItems.extraStuff, ExtraStuffItems.STAINLESS_STEEL, RecipeType.SMELTERY,
                new ItemStack[] {
                    new ItemStack(Material.IRON_INGOT), SlimefunItems.IRON_DUST, SlimefunItems.ZINC_DUST,
                        null, null, null,
                        null, null, null
                }
        );

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
                .setCapacity(128)
                .setEnergyConsumption(16)
                .setProcessingSpeed(1)
                .register(plugin);

        loadAutoKitchenRecipes();
    }




}
