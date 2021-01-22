package me.profelements.dynatech.setup;

import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.DynaTechItems;
import me.profelements.dynatech.items.backpacks.PicnicBasket;
import me.profelements.dynatech.items.electric.AntigravityBubble;
import me.profelements.dynatech.items.electric.AutoKitchen;
import me.profelements.dynatech.items.electric.BarbedWire;
import me.profelements.dynatech.items.electric.GrowthChamber;
import me.profelements.dynatech.items.electric.MaterialHive;
import me.profelements.dynatech.items.electric.PotionSprinkler;
import me.profelements.dynatech.items.electric.WeatherController;
import me.profelements.dynatech.items.electric.WirelessCharger;
import me.profelements.dynatech.items.electric.generators.ChippingGenerator;
import me.profelements.dynatech.items.electric.generators.CulinaryGenerator;
import me.profelements.dynatech.items.electric.generators.DragonEggGenerator;
import me.profelements.dynatech.items.electric.generators.HydroGenerator;
import me.profelements.dynatech.items.misc.Bee;
import me.profelements.dynatech.items.misc.VexGem;
import me.profelements.dynatech.items.tools.AngelGem;
import me.profelements.dynatech.items.tools.DimensionalHome;
import me.profelements.dynatech.items.tools.ElectricalStimulator;
import me.profelements.dynatech.items.tools.InventoryFilter;
import me.profelements.dynatech.items.tools.Scoop;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class DynaTechItemsSetup {

    private DynaTechItemsSetup() {}

    public static final void setup(@Nonnull DynaTech plugin) {

        //Materials
        new SlimefunItem(DynaTechItems.DynaTechGeneral, DynaTechItems.STAINLESS_STEEL, RecipeType.SMELTERY,
                new ItemStack[] {
                    new ItemStack(Material.IRON_INGOT), SlimefunItems.IRON_DUST, SlimefunItems.ZINC_DUST,
                        null, null, null,
                        null, null, null,
                }).register(plugin);

        new SlimefunItem(DynaTechItems.DynaTechGeneral, DynaTechItems.STAINLESS_STEEL_ROTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        null, DynaTechItems.STAINLESS_STEEL, null,
                        DynaTechItems.STAINLESS_STEEL, new ItemStack(Material.IRON_BLOCK), DynaTechItems.STAINLESS_STEEL,
                        null, DynaTechItems.STAINLESS_STEEL, null
                }).register(plugin);
        
        new SlimefunItem(DynaTechItems.DynaTechGeneral, DynaTechItems.ANCIENT_MACHINE_CORE, RecipeType.MAGIC_WORKBENCH,
                new ItemStack[] {
                        SlimefunItems.LEAD_INGOT, SlimefunItems.MAGIC_LUMP_1, SlimefunItems.LEAD_INGOT,
                        SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.REDSTONE_BLOCK), SlimefunItems.MAGIC_LUMP_1,
                        SlimefunItems.LEAD_INGOT, SlimefunItems.MAGIC_LUMP_1, SlimefunItems.LEAD_INGOT
                }).register(plugin);
        
        new SlimefunItem(DynaTechItems.DynaTechGeneral, DynaTechItems.MACHINE_SCRAP, RecipeType.GRIND_STONE,
                new ItemStack[] {
                        SlimefunItems.PROGRAMMABLE_ANDROID, null, null,
                        null, null, null,
                        null, null, null,
                }, new SlimefunItemStack(DynaTechItems.MACHINE_SCRAP, 8)).register(plugin); 
                
        new SlimefunItem(DynaTechItems.DynaTechGeneral, DynaTechItems.ADVANCED_MACHINE_SCRAP, RecipeType.GRIND_STONE,
                new ItemStack[] {
                        SlimefunItems.PROGRAMMABLE_ANDROID_2, null, null,
                        null, null, null,
                        null, null, null,
                }, new SlimefunItemStack(DynaTechItems.ADVANCED_MACHINE_SCRAP, 8)).register(plugin);

        new VexGem(DynaTechItems.DynaTechGeneral, DynaTechItems.VEX_GEM, RecipeType.MOB_DROP, 
                new ItemStack[] {
                        null, null, null,
                        null, new CustomItem(SkullItem.fromHash("c2ec5a516617ff1573cd2f9d5f3969f56d5575c4ff4efefabd2a18dc7ab98cd"), "&aVex"), null,
                        null, null, null
                }).register(plugin);

        //Bees
        new Bee(DynaTechItems.DynaTechGeneral, DynaTechItems.BEE, DynaTechItems.DynaTechScoop,
                new ItemStack[] {
                        null, null, null,
                        null, new CustomItem(SkullItem.fromHash("12724a9a4cdd68ba49415560e5be40b4a1c47cb5be1d66aedb52a30e62ef2d47"), "&aAny Bee"), null,
                        null, null, null
                }, 2
                ).register(plugin);

        new Bee(DynaTechItems.DynaTechGeneral, DynaTechItems.ROBOTIC_BEE, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE,
                        DynaTechItems.VEX_GEM, DynaTechItems.MACHINE_SCRAP, DynaTechItems.VEX_GEM,
                        SlimefunItems.REINFORCED_PLATE, DynaTechItems.ANCIENT_MACHINE_CORE, SlimefunItems.REINFORCED_PLATE
                }, 7
                ).register(plugin);

        new Bee(DynaTechItems.DynaTechGeneral, DynaTechItems.ADVANCED_ROBOTIC_BEE, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE,
                DynaTechItems.ANGEL_GEM, DynaTechItems.ADVANCED_MACHINE_SCRAP, DynaTechItems.ANGEL_GEM,
                SlimefunItems.GOLD_24K_BLOCK, DynaTechItems.ANCIENT_MACHINE_CORE, SlimefunItems.GOLD_24K_BLOCK
                }, 10
                ).register(plugin);

        //Backpacks
        new PicnicBasket(27, DynaTechItems.DynaTechGeneral, DynaTechItems.PICNIC_BASKET, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.CLOTH,
                        new ItemStack(Material.BAMBOO), SlimefunItems.COOLER, new ItemStack(Material.BAMBOO),
                        SlimefunItems.HEATING_COIL, new ItemStack(Material.BAMBOO), SlimefunItems.COOLING_UNIT
                }).register(plugin);
        
        //Tools
        new InventoryFilter(DynaTechItems.DynaTechGeneral, DynaTechItems.INVENTORY_FILTER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.REINFORCED_CLOTH, new ItemStack(Material.IRON_BARS), SlimefunItems.REINFORCED_CLOTH,
                        new ItemStack(Material.IRON_BARS), null, new ItemStack(Material.IRON_BARS),
                        SlimefunItems.REINFORCED_CLOTH, new ItemStack(Material.IRON_BARS), SlimefunItems.REINFORCED_CLOTH
                }).register(plugin);

        new ElectricalStimulator(DynaTechItems.DynaTechGeneral, DynaTechItems.ELECTRICAL_STIMULATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        DynaTechItems.STAINLESS_STEEL, null, DynaTechItems.STAINLESS_STEEL,
                        DynaTechItems.STAINLESS_STEEL, SlimefunItems.FOOD_FABRICATOR, DynaTechItems.STAINLESS_STEEL,
                        SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET
                }).register(plugin);

        new AngelGem(DynaTechItems.DynaTechGeneral, DynaTechItems.ANGEL_GEM, RecipeType.ANCIENT_ALTAR,
                new ItemStack[] {
                        new ItemStack(Material.NETHERITE_INGOT), SlimefunItems.NUCLEAR_REACTOR, new ItemStack(Material.NETHERITE_INGOT),
                        SlimefunItems.GOLD_24K_BLOCK, DynaTechItems.VEX_GEM, SlimefunItems.GOLD_24K_BLOCK,
                        SlimefunItems.BLISTERING_INGOT_3, DynaTechItems.STAINLESS_STEEL_ROTOR, SlimefunItems.BLISTERING_INGOT_3
                }).register(plugin);

        new Scoop(DynaTechItems.DynaTechGeneral, DynaTechItems.SCOOP, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL),
                        new ItemStack(Material.WHITE_WOOL), SlimefunItems.BATTERY, new ItemStack(Material.WHITE_WOOL),
                        null, new ItemStack(Material.STICK), null
                }).register(plugin);

        new DimensionalHome(DynaTechItems.DynaTechGeneral, DynaTechItems.DIMENSIONAL_HOME, RecipeType.MAGIC_WORKBENCH,
                new ItemStack[] {
                    null, null, SlimefunItems.GOLD_24K_BLOCK,
                    SlimefunItems.BRONZE_INGOT, new ItemStack(Material.BLAZE_ROD), null,
                    SlimefunItems.SYNTHETIC_SAPPHIRE, SlimefunItems.BRONZE_INGOT, null
                }).register(plugin);
        
        //Machines
        if (DynaTech.isExoticGardenInstalled()) {
                new AutoKitchen(DynaTechItems.DynaTechGeneral, DynaTechItems.AUTO_KITCHEN, RecipeType.ENHANCED_CRAFTING_TABLE,
                        new ItemStack[] {
                        new ItemStack(Material.BRICK), SlimefunItems.ELECTRIC_FURNACE, new ItemStack(Material.BRICK),
                        DynaTechItems.STAINLESS_STEEL, SlimefunItems.GOLD_24K_BLOCK, DynaTechItems.STAINLESS_STEEL,
                        new ItemStack(Material.TERRACOTTA), new ItemStack(Material.TERRACOTTA), new ItemStack(Material.TERRACOTTA)

                        })
                        .setEnergyCapacity(512)
                        .setEnergyConsumption(16)
                        .setProcessingSpeed(1)
                        .register(plugin);
        }
       

        new GrowthChamber(DynaTechItems.DynaTechGeneral, DynaTechItems.GROWTH_CHAMBER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.HARDENED_GLASS,SlimefunItems.TREE_GROWTH_ACCELERATOR,SlimefunItems.HARDENED_GLASS,
                        new ItemStack(Material.BONE_BLOCK),new ItemStack(Material.GRASS_BLOCK),new ItemStack(Material.BONE_BLOCK),
                        DynaTechItems.STAINLESS_STEEL,SlimefunItems.CROP_GROWTH_ACCELERATOR_2,DynaTechItems.STAINLESS_STEEL

                })
                .setEnergyCapacity(512)
                .setEnergyConsumption(32)
                .setProcessingSpeed(1)
                .register(plugin);

        new AntigravityBubble(DynaTechItems.DynaTechGeneral, DynaTechItems.ANTIGRAVITY_BUBBLE, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.BLISTERING_INGOT_3, new ItemStack(Material.DIAMOND_BLOCK), SlimefunItems.BLISTERING_INGOT_3,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.BIG_CAPACITOR , SlimefunItems.REINFORCED_ALLOY_INGOT,
                        SlimefunItems.REINFORCED_ALLOY_INGOT, DynaTechItems.STAINLESS_STEEL, SlimefunItems.REINFORCED_ALLOY_INGOT, 
                })
                .setEnergyCapacity(1024)
                .setEnergyConsumption(128)
                .setProcessingSpeed(1)
                .register(plugin);
        
        new WeatherController(DynaTechItems.DynaTechGeneral, DynaTechItems.WEATHER_CONTROLLER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        null,SlimefunItems.HARDENED_METAL_INGOT,null,
                        new ItemStack(Material.WATER_BUCKET), DynaTechItems.ANCIENT_MACHINE_CORE, new ItemStack(Material.LAVA_BUCKET),
                        DynaTechItems.STAINLESS_STEEL, new ItemStack(Material.CRYING_OBSIDIAN), DynaTechItems.STAINLESS_STEEL
                })
                .setEnergyCapacity(512)
                .setEnergyConsumption(32)
                .setProcessingSpeed(1)
                .register(plugin);
        
        new PotionSprinkler(DynaTechItems.DynaTechGeneral, DynaTechItems.POTION_SPRINKLER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.BREWING_STAND), null, new ItemStack(Material.BREWING_STAND),
                        new ItemStack(Material.IRON_BARS), DynaTechItems.ANCIENT_MACHINE_CORE, new ItemStack(Material.IRON_BARS),
                        SlimefunItems.FERROSILICON, SlimefunItems.FERROSILICON, SlimefunItems.FERROSILICON
                })
                .setEnergyCapacity(256)
                .setEnergyConsumption(32)
                .setProcessingSpeed(1)
                .register(plugin);
        
        new BarbedWire(DynaTechItems.DynaTechGeneral, DynaTechItems.BARBED_WIRE, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.IRON_SWORD),new ItemStack(Material.IRON_SWORD),new ItemStack(Material.IRON_SWORD),
                        DynaTechItems.STAINLESS_STEEL, new ItemStack(Material.OAK_LOG), DynaTechItems.STAINLESS_STEEL,
                        SlimefunItems.ZINC_INGOT, SlimefunItems.ZINC_INGOT, SlimefunItems.ZINC_INGOT
                })        
                .setEnergyCapacity(128)
                .setEnergyConsumption(16)
                .setProcessingSpeed(1)
                .register(plugin);
        
        new MaterialHive(DynaTechItems.DynaTechGeneral, DynaTechItems.MATERIAL_HIVE, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                    SlimefunItems.REINFORCED_PLATE, SlimefunItems.BOOSTED_URANIUM, SlimefunItems.REINFORCED_PLATE,
                    SlimefunItems.REINFORCED_PLATE, new ItemStack(Material.BEEHIVE), SlimefunItems.REINFORCED_PLATE,
                    DynaTechItems.ADVANCED_MACHINE_SCRAP, DynaTechItems.MACHINE_SCRAP, DynaTechItems.ADVANCED_MACHINE_SCRAP
                })
                .setEnergyCapacity(8192)
                .setEnergyConsumption(1024)
                .setProcessingSpeed(1)
                .register(plugin);       

        new WirelessCharger(DynaTechItems.DynaTechGeneral, DynaTechItems.WIRELESS_CHARGER, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                null, SlimefunItems.GPS_TRANSMITTER, null,
                SlimefunItems.GOLD_24K, SlimefunItems.CHARGING_BENCH, SlimefunItems.GOLD_24K,
                null, SlimefunItems.SMALL_CAPACITOR, null
            }, 10
            )
            .setEnergyCapacity(1024)
            .setEnergyConsumption(16)
            .setProcessingSpeed(1)
            .register(plugin);

        //Generators
        new HydroGenerator(DynaTechItems.DynaTechGeneral, DynaTechItems.WATER_MILL, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.ALUMINUM_INGOT, SlimefunItems.SULFATE, SlimefunItems.ALUMINUM_INGOT,
                        SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ENERGY_CONNECTOR, SlimefunItems.ALUMINUM_INGOT,
                        new ItemStack(Material.WHITE_CONCRETE), null, new ItemStack(Material.WHITE_CONCRETE)
                })
                .setEnergyCapacity(256)
                .setEnergyProduction(16)
                .register(plugin);

        new DragonEggGenerator(DynaTechItems.DynaTechGeneral, DynaTechItems.DRAGON_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.CRYING_OBSIDIAN), new ItemStack(Material.END_STONE), new ItemStack(Material.CRYING_OBSIDIAN),
                        new ItemStack(Material.CRYING_OBSIDIAN), SlimefunItems.LEAD_INGOT, new ItemStack(Material.CRYING_OBSIDIAN),
                        SlimefunItems.LEAD_INGOT, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.LEAD_INGOT


                })
                .setEnergyCapacity(512)
                .setEnergyProduction(32)
                .register(plugin);

        new ChippingGenerator(DynaTechItems.DynaTechGeneral, DynaTechItems.CHIPPING_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, 
                new ItemStack[] {
                        DynaTechItems.STAINLESS_STEEL, DynaTechItems.STAINLESS_STEEL, DynaTechItems.STAINLESS_STEEL,
                        new ItemStack(Material.DIAMOND_AXE), DynaTechItems.ANCIENT_MACHINE_CORE,new ItemStack(Material.DIAMOND_AXE),
                        DynaTechItems.STAINLESS_STEEL, DynaTechItems.STAINLESS_STEEL, DynaTechItems.STAINLESS_STEEL
                })
                .setEnergyCapacity(256)
                .setEnergyProduction(2)
                .register(plugin);

        new CulinaryGenerator(DynaTechItems.DynaTechGeneral, DynaTechItems.CULINARY_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.ALUMINUM_BRASS_INGOT,SlimefunItems.ALUMINUM_BRASS_INGOT,SlimefunItems.ALUMINUM_BRASS_INGOT,
                        SlimefunItems.LEAD_DUST, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.LEAD_DUST,
                        new ItemStack(Material.CAMPFIRE),new ItemStack(Material.CAMPFIRE),new ItemStack(Material.CAMPFIRE)
                })
                .setEnergyCapacity(256)
                .setEnergyProduction(16)
                .register(plugin);

        }

}
