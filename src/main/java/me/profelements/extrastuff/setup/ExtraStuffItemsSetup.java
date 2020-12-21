package me.profelements.extrastuff.setup;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.profelements.extrastuff.ExtraStuff;
import me.profelements.extrastuff.ExtraStuffItems;
import me.profelements.extrastuff.items.backpacks.PicnicBasket;
import me.profelements.extrastuff.items.machines.AntigravityBubble;
import me.profelements.extrastuff.items.machines.AutoKitchen;
import me.profelements.extrastuff.items.machines.DragonEggGenerator;
import me.profelements.extrastuff.items.machines.GrowthChamber;
import me.profelements.extrastuff.items.machines.HydroGenerator;

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

        new SlimefunItem(ExtraStuffItems.extraStuff, ExtraStuffItems.STAINLESS_STEEL_ROTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        null, ExtraStuffItems.STAINLESS_STEEL, null,
                        ExtraStuffItems.STAINLESS_STEEL, new ItemStack(Material.IRON_BLOCK), ExtraStuffItems.STAINLESS_STEEL,
                        null, ExtraStuffItems.STAINLESS_STEEL, null
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

        new GrowthChamber(ExtraStuffItems.extraStuff, ExtraStuffItems.GROWTH_CHAMBER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.HARDENED_GLASS,SlimefunItems.HARDENED_GLASS,SlimefunItems.HARDENED_GLASS,
                        new ItemStack(Material.BONE_BLOCK),new ItemStack(Material.GRASS_BLOCK),new ItemStack(Material.BONE_BLOCK),
                        ExtraStuffItems.STAINLESS_STEEL,new ItemStack(Material.BONE_BLOCK),ExtraStuffItems.STAINLESS_STEEL

                })
                .setEnergyCapacity(512)
                .setEnergyConsumption(32)
                .setProcessingSpeed(1)
                .register(plugin);

        new AntigravityBubble(ExtraStuffItems.extraStuff, ExtraStuffItems.ANTIGRAVITY_BUBBLE, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.BLISTERING_INGOT_3, new ItemStack(Material.DIAMOND_BLOCK), SlimefunItems.BLISTERING_INGOT_3,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.BIG_CAPACITOR , SlimefunItems.REINFORCED_ALLOY_INGOT,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, ExtraStuffItems.STAINLESS_STEEL, SlimefunItems.REINFORCED_ALLOY_INGOT, 
                })
                .setEnergyCapacity(1024)
                .setEnergyConsumption(128)
                .setProcessingSpeed(1)
                .register(plugin);

        new HydroGenerator(ExtraStuffItems.extraStuff, ExtraStuffItems.WATER_MILL, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.ALUMINUM_INGOT, SlimefunItems.SULFATE, SlimefunItems.ALUMINUM_INGOT,
                        SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ENERGY_CONNECTOR, SlimefunItems.ALUMINUM_INGOT,
                        new ItemStack(Material.WHITE_CONCRETE), null, new ItemStack(Material.WHITE_CONCRETE)
                }).register(plugin);

        new DragonEggGenerator(ExtraStuffItems.extraStuff, ExtraStuffItems.DRAGON_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.CRYING_OBSIDIAN), new ItemStack(Material.END_STONE), new ItemStack(Material.CRYING_OBSIDIAN),
                        new ItemStack(Material.CRYING_OBSIDIAN), SlimefunItems.LEAD_INGOT, new ItemStack(Material.CRYING_OBSIDIAN),
                        SlimefunItems.LEAD_INGOT, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.LEAD_INGOT


                }).register(plugin);

    }




}
