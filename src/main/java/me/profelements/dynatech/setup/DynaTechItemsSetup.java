package me.profelements.dynatech.setup;

import io.github.mooy1.infinityexpansion.items.mobdata.MobData;
import io.github.mooy1.infinityexpansion.items.mobdata.MobDataCard;
import io.github.mooy1.infinityexpansion.items.mobdata.MobDataTier;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.DynaTechItems;
import me.profelements.dynatech.items.backpacks.PicnicBasket;
import me.profelements.dynatech.items.backpacks.SoulboundPicnicBacket;
import me.profelements.dynatech.items.electric.AntigravityBubble;
import me.profelements.dynatech.items.electric.BandaidManager;
import me.profelements.dynatech.items.electric.BarbedWire;
import me.profelements.dynatech.items.electric.FurnaceController;
import me.profelements.dynatech.items.electric.KitchenAutoCrafter;
import me.profelements.dynatech.items.electric.MaterialHive;
import me.profelements.dynatech.items.electric.PotionSprinkler;
import me.profelements.dynatech.items.electric.SeedPlucker;
import me.profelements.dynatech.items.electric.WeatherController;
import me.profelements.dynatech.items.electric.WirelessCharger;
import me.profelements.dynatech.items.electric.machines.MineralizedApiary;
import me.profelements.dynatech.items.electric.machines.Orechid;
import me.profelements.dynatech.items.electric.generators.ChippingGenerator;
import me.profelements.dynatech.items.electric.generators.CulinaryGenerator;
import me.profelements.dynatech.items.electric.generators.DragonEggGenerator;
import me.profelements.dynatech.items.electric.generators.HydroGenerator;
import me.profelements.dynatech.items.electric.generators.StardustReactor;
import me.profelements.dynatech.items.electric.growthchambers.GrowthChamber;
import me.profelements.dynatech.items.electric.growthchambers.GrowthChamberEnd;
import me.profelements.dynatech.items.electric.growthchambers.GrowthChamberEndMK2;
import me.profelements.dynatech.items.electric.growthchambers.GrowthChamberMK2;
import me.profelements.dynatech.items.electric.growthchambers.GrowthChamberNether;
import me.profelements.dynatech.items.electric.growthchambers.GrowthChamberNetherMK2;
import me.profelements.dynatech.items.electric.growthchambers.GrowthChamberOcean;
import me.profelements.dynatech.items.electric.growthchambers.GrowthChamberOceanMK2;
import me.profelements.dynatech.items.electric.transfer.Tesseract;
import me.profelements.dynatech.items.electric.transfer.WirelessEnergyBank;
import me.profelements.dynatech.items.electric.transfer.WirelessEnergyPoint;
import me.profelements.dynatech.items.electric.transfer.WirelessItemInput;
import me.profelements.dynatech.items.electric.transfer.WirelessItemOutput;
import me.profelements.dynatech.items.misc.Bee;
import me.profelements.dynatech.items.misc.ItemBand;
import me.profelements.dynatech.items.misc.MobDropItem;
import me.profelements.dynatech.items.misc.StarDustMeteor;
import me.profelements.dynatech.items.misc.VexGem;
import me.profelements.dynatech.items.misc.WitherGolem;
import me.profelements.dynatech.items.tools.AngelGem;
import me.profelements.dynatech.items.tools.DimensionalHome;
import me.profelements.dynatech.items.tools.ElectricalStimulator;
import me.profelements.dynatech.items.tools.InventoryFilter;
import me.profelements.dynatech.items.tools.LiquidTank;
import me.profelements.dynatech.items.tools.Scoop;
import me.profelements.dynatech.items.tools.TesseractBinder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import dev.j3fftw.extrautils.utils.LoreBuilderDynamic;

import javax.annotation.Nonnull;

public class DynaTechItemsSetup {

    private DynaTechItemsSetup() {}

    public static void setup(@Nonnull DynaTech plugin) {

        //General
        //Resources
        new SlimefunItem(DynaTechItems.DT_RESOURCES, DynaTechItems.STAINLESS_STEEL, RecipeType.SMELTERY,
        new ItemStack[] {
            new ItemStack(Material.IRON_INGOT), SlimefunItems.IRON_DUST, SlimefunItems.ZINC_DUST,
                null, null, null,
                null, null, null,
        }).register(plugin);

        new SlimefunItem(DynaTechItems.DT_RESOURCES, DynaTechItems.STAINLESS_STEEL_ROTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                null, DynaTechItems.STAINLESS_STEEL, null,
                DynaTechItems.STAINLESS_STEEL, new ItemStack(Material.IRON_BLOCK), DynaTechItems.STAINLESS_STEEL,
                null, DynaTechItems.STAINLESS_STEEL, null
        }).register(plugin);
        
        new SlimefunItem(DynaTechItems.DT_RESOURCES, DynaTechItems.ANCIENT_MACHINE_CORE, RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                SlimefunItems.LEAD_INGOT, SlimefunItems.MAGIC_LUMP_1, SlimefunItems.LEAD_INGOT,
                SlimefunItems.MAGIC_LUMP_1, new ItemStack(Material.REDSTONE_BLOCK), SlimefunItems.MAGIC_LUMP_1,
                SlimefunItems.LEAD_INGOT, SlimefunItems.MAGIC_LUMP_1, SlimefunItems.LEAD_INGOT
        }).register(plugin);

        new SlimefunItem(DynaTechItems.DT_RESOURCES, DynaTechItems.MACHINE_SCRAP, RecipeType.GRIND_STONE,
            new ItemStack[] {
                    SlimefunItems.PROGRAMMABLE_ANDROID, null, null,
                    null, null, null,
                    null, null, null,
        }, new SlimefunItemStack(DynaTechItems.MACHINE_SCRAP, 8)).register(plugin);
                
        new SlimefunItem(DynaTechItems.DT_RESOURCES, DynaTechItems.ADVANCED_MACHINE_SCRAP, RecipeType.GRIND_STONE,
            new ItemStack[] {
                SlimefunItems.PROGRAMMABLE_ANDROID_2, null, null,
                null, null, null,
                null, null, null,
        }, new SlimefunItemStack(DynaTechItems.ADVANCED_MACHINE_SCRAP, 8)).register(plugin);

        new VexGem(DynaTechItems.DT_RESOURCES, DynaTechItems.VEX_GEM, RecipeType.MOB_DROP, 
            new ItemStack[] {
                null, null, null,
                null, new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode("c2ec5a516617ff1573cd2f9d5f3969f56d5575c4ff4efefabd2a18dc7ab98cd")), "&aVex"), null,
                null, null, null
        }).register(plugin);
        
        RecipeType.MAGIC_WORKBENCH.register(new ItemStack[] {
            null, SlimefunItems.SYNTHETIC_SAPPHIRE, null,
            new ItemStack(Material.PHANTOM_MEMBRANE), SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.PHANTOM_MEMBRANE),
            DynaTechItems.STAR_DUST, DynaTechItems.STAR_DUST, DynaTechItems.STAR_DUST
        }, DynaTechItems.VEX_GEM);

        new SlimefunItem(DynaTechItems.DT_RESOURCES, DynaTechItems.STAR_DUST, RecipeType.GRIND_STONE,
            new ItemStack[] {
                StarDustMeteor.STARDUST_METEOR, null, null,
                null, null, null,
                null, null, null
        }).register(plugin);
            
        new StarDustMeteor(DynaTechItems.DT_RESOURCES).register(plugin);

        new MobDropItem(DynaTechItems.DT_RESOURCES, DynaTechItems.GHOSTLY_ESSENCE, RecipeType.MOB_DROP, 
            new ItemStack[] {
                null, null, null,
                null, new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode("c2ec5a516617ff1573cd2f9d5f3969f56d5575c4ff4efefabd2a18dc7ab98cd")), "&aVex"), null,
                null, null, null
        }, 80).register(plugin);

        new SlimefunItem(DynaTechItems.DT_RESOURCES, DynaTechItems.TESSERACTING_OBJ, RecipeType.ANCIENT_ALTAR,
            new ItemStack[] {
                DynaTechItems.GHOSTLY_ESSENCE, SlimefunItems.ENDER_RUNE, DynaTechItems.VEX_GEM, 
                SlimefunItems.ENDER_RUNE, new ItemStack(Material.WITHER_ROSE), SlimefunItems.ENDER_RUNE, 
                DynaTechItems.VEX_GEM, SlimefunItems.ENDER_RUNE, DynaTechItems.GHOSTLY_ESSENCE
            }
        ).register(plugin);

        new Bee(DynaTechItems.DT_RESOURCES, DynaTechItems.BEE, DynaTechItems.DynaTechScoop,
            new ItemStack[] {
                null, null, null,
                null, new CustomItemStack(PlayerHead.getItemStack(PlayerSkin.fromHashCode("12724a9a4cdd68ba49415560e5be40b4a1c47cb5be1d66aedb52a30e62ef2d47")), "&aAny Bee"), null,
                null, null, null
            }, 2
        ).register(plugin);

        new Bee(DynaTechItems.DT_RESOURCES, DynaTechItems.ROBOTIC_BEE, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.SYNTHETIC_SAPPHIRE, DynaTechItems.MACHINE_SCRAP, SlimefunItems.SYNTHETIC_SAPPHIRE,
                SlimefunItems.REINFORCED_ALLOY_INGOT, DynaTechItems.ANCIENT_MACHINE_CORE, SlimefunItems.REINFORCED_ALLOY_INGOT
            }, 7
        ).register(plugin);

        new Bee(DynaTechItems.DT_RESOURCES, DynaTechItems.ADVANCED_ROBOTIC_BEE, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE, SlimefunItems.REINFORCED_PLATE,
                DynaTechItems.VEX_GEM, DynaTechItems.ADVANCED_MACHINE_SCRAP, DynaTechItems.VEX_GEM,
                SlimefunItems.GOLD_24K_BLOCK, DynaTechItems.ANCIENT_MACHINE_CORE, SlimefunItems.GOLD_24K_BLOCK
            }, 11
        ).register(plugin);

        
        //Machines
        //Generators


        //Materials
        

        
        //Tools
        new PicnicBasket(27, DynaTechItems.DT_TOOLS, DynaTechItems.PICNIC_BASKET, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.CLOTH, SlimefunItems.CLOTH, SlimefunItems.CLOTH,
                new ItemStack(Material.BAMBOO), SlimefunItems.COOLER, new ItemStack(Material.BAMBOO),
                SlimefunItems.HEATING_COIL, new ItemStack(Material.BAMBOO), SlimefunItems.COOLING_UNIT
        }).register(plugin);
        
        new SoulboundPicnicBacket(27, DynaTechItems.DT_TOOLS, DynaTechItems.SOULBOUND_PICNIC_BASKET, RecipeType.MAGIC_WORKBENCH,
                new ItemStack[] {
                new ItemStack(Material.CRYING_OBSIDIAN), SlimefunItems.ENDER_RUNE, new ItemStack(Material.CRYING_OBSIDIAN),
                SlimefunItems.ENDER_RUNE, DynaTechItems.PICNIC_BASKET, SlimefunItems.ENDER_RUNE,
                new ItemStack(Material.CRYING_OBSIDIAN), SlimefunItems.ENDER_RUNE, new ItemStack(Material.CRYING_OBSIDIAN)
        }).register(plugin);

        new InventoryFilter(DynaTechItems.DT_TOOLS, DynaTechItems.INVENTORY_FILTER, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.REINFORCED_CLOTH, new ItemStack(Material.IRON_BARS), SlimefunItems.REINFORCED_CLOTH,
                new ItemStack(Material.IRON_BARS), null, new ItemStack(Material.IRON_BARS),
                SlimefunItems.REINFORCED_CLOTH, new ItemStack(Material.IRON_BARS), SlimefunItems.REINFORCED_CLOTH
        }).register(plugin);

        new ElectricalStimulator(DynaTechItems.DT_TOOLS, DynaTechItems.ELECTRICAL_STIMULATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                DynaTechItems.STAINLESS_STEEL, null, DynaTechItems.STAINLESS_STEEL,
                DynaTechItems.STAINLESS_STEEL, SlimefunItems.FOOD_FABRICATOR, DynaTechItems.STAINLESS_STEEL,
                SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET, SlimefunItems.PLASTIC_SHEET
        }).register(plugin);

        new AngelGem(DynaTechItems.DT_TOOLS, DynaTechItems.ANGEL_GEM, RecipeType.ANCIENT_ALTAR,
            new ItemStack[] {
                new ItemStack(Material.NETHERITE_INGOT), SlimefunItems.NUCLEAR_REACTOR, new ItemStack(Material.NETHERITE_INGOT),
                SlimefunItems.GOLD_24K_BLOCK, DynaTechItems.VEX_GEM, SlimefunItems.GOLD_24K_BLOCK,
                SlimefunItems.BLISTERING_INGOT_3, DynaTechItems.STAINLESS_STEEL_ROTOR, SlimefunItems.BLISTERING_INGOT_3
        }).register(plugin);

        new Scoop(DynaTechItems.DT_TOOLS, DynaTechItems.SCOOP, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL), new ItemStack(Material.WHITE_WOOL),
                new ItemStack(Material.WHITE_WOOL), SlimefunItems.BATTERY, new ItemStack(Material.WHITE_WOOL),
                null, new ItemStack(Material.STICK), null
        }).register(plugin);

        new DimensionalHome(DynaTechItems.DT_TOOLS, DynaTechItems.DIMENSIONAL_HOME, RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                null, null, SlimefunItems.GOLD_24K_BLOCK,
                SlimefunItems.BRONZE_INGOT, new ItemStack(Material.BLAZE_ROD), null,
                SlimefunItems.SYNTHETIC_SAPPHIRE, SlimefunItems.BRONZE_INGOT, null
        }).register(plugin);
        
        new ItemBand(DynaTechItems.DT_TOOLS, DynaTechItems.ITEM_BAND_HEALTH, RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                new ItemStack(Material.GOLDEN_CARROT), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.GOLDEN_CARROT),
                new ItemStack(Material.NETHER_STAR), DynaTechItems.VEX_GEM, new ItemStack(Material.NETHER_STAR),
                new ItemStack(Material.GOLDEN_CARROT), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.GOLDEN_CARROT)
            }, new PotionEffect[] {new PotionEffect(PotionEffectType.HEALTH_BOOST, 20*15, 1, true) }
        ).register(plugin);

        new ItemBand(DynaTechItems.DT_TOOLS, DynaTechItems.ITEM_BAND_HASTE, RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                SlimefunItems.COBALT_PICKAXE, new ItemStack(Material.NETHER_STAR), SlimefunItems.COBALT_PICKAXE,
                new ItemStack(Material.NETHER_STAR), DynaTechItems.VEX_GEM, new ItemStack(Material.NETHER_STAR),
                SlimefunItems.COBALT_PICKAXE, new ItemStack(Material.NETHER_STAR), SlimefunItems.COBALT_PICKAXE
            }, new PotionEffect[] {new PotionEffect(PotionEffectType.FAST_DIGGING, 20*15, 1, true) }    
        ).register(plugin);

        new TesseractBinder(DynaTechItems.DT_TOOLS, DynaTechItems.TESSERACT_BINDER, RecipeType.MAGIC_WORKBENCH, 
            new ItemStack[] {
                null, DynaTechItems.TESSERACTING_OBJ, null,
                null, DynaTechItems.STAINLESS_STEEL, null,
                null, DynaTechItems.STAINLESS_STEEL, null,
            }
        ).register(plugin);

        if (DynaTech.isInfinityExpansionInstalled()) {
            new MobDataCard("Vex", MobDataTier.HOSTILE, new ItemStack[] {
                new SlimefunItemStack(DynaTechItems.VEX_GEM, 16), new SlimefunItemStack(DynaTechItems.GHOSTLY_ESSENCE, 16), new SlimefunItemStack(DynaTechItems.VEX_GEM, 16),
                new SlimefunItemStack(DynaTechItems.GHOSTLY_ESSENCE, 16), MobData.EMPTY_DATA_CARD, new SlimefunItemStack(DynaTechItems.GHOSTLY_ESSENCE, 16),
                new SlimefunItemStack(DynaTechItems.VEX_GEM, 16), new SlimefunItemStack(DynaTechItems.GHOSTLY_ESSENCE, 16), new SlimefunItemStack(DynaTechItems.VEX_GEM, 16)
            })
            .addDrop(DynaTechItems.VEX_GEM, 1)
            .addDrop(DynaTechItems.GHOSTLY_ESSENCE, 9)
            .register(plugin);

            new MobDataCard("Phantom", MobDataTier.HOSTILE, new ItemStack[] {
                new ItemStack(Material.PHANTOM_MEMBRANE, 16), new ItemStack(Material.PHANTOM_MEMBRANE, 16), new ItemStack(Material.PHANTOM_MEMBRANE, 16),
                new ItemStack(Material.PHANTOM_MEMBRANE, 16), MobData.EMPTY_DATA_CARD, new ItemStack(Material.PHANTOM_MEMBRANE, 16),
                new ItemStack(Material.PHANTOM_MEMBRANE, 16), new ItemStack(Material.PHANTOM_MEMBRANE, 16), new ItemStack(Material.PHANTOM_MEMBRANE, 16),
            })
            .addDrop(Material.PHANTOM_MEMBRANE, 0.25f)
            .register(plugin);
        }
        
        new WitherGolem(DynaTechItems.DT_TOOLS, DynaTechItems.WITHER_GOLEM).register(plugin);

        //Machines
        if (DynaTech.isExoticGardenInstalled()) {
            new KitchenAutoCrafter(DynaTechItems.DT_MACHINES, DynaTechItems.KITCHEN_AUTO_CRAFTER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                new ItemStack(Material.BRICK), SlimefunItems.ELECTRIC_FURNACE, new ItemStack(Material.BRICK),
                DynaTechItems.STAINLESS_STEEL, SlimefunItems.GOLD_24K_BLOCK, DynaTechItems.STAINLESS_STEEL,
                new ItemStack(Material.TERRACOTTA), new ItemStack(Material.TERRACOTTA), new ItemStack(Material.TERRACOTTA),
            })
            .setCapacity(512)
            .setEnergyConsumption(16)
            .register(plugin); 
        }
       
        new GrowthChamber(DynaTechItems.DT_MACHINES, DynaTechItems.GROWTH_CHAMBER, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.HARDENED_GLASS,SlimefunItems.TREE_GROWTH_ACCELERATOR,SlimefunItems.HARDENED_GLASS,
                new ItemStack(Material.BONE_BLOCK),new ItemStack(Material.GRASS_BLOCK),new ItemStack(Material.BONE_BLOCK),
                DynaTechItems.STAINLESS_STEEL,SlimefunItems.CROP_GROWTH_ACCELERATOR_2,DynaTechItems.STAINLESS_STEEL
        })
        .setCapacity(512)
        .setConsumption(32)
        .setProcessingSpeed(1)
        .register(plugin);

        new GrowthChamberMK2(DynaTechItems.DT_MACHINES, DynaTechItems.GROWTH_CHAMBER_MK2, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.STEEL_PLATE,DynaTechItems.GROWTH_CHAMBER,SlimefunItems.STEEL_PLATE,
                new ItemStack(Material.GRASS_BLOCK),new ItemStack(Material.LIME_STAINED_GLASS),new ItemStack(Material.SAND),
                SlimefunItems.STEEL_PLATE,DynaTechItems.GROWTH_CHAMBER,SlimefunItems.STEEL_PLATE
        })
        .setCapacity(1024)
        .setConsumption(128)
        .setProcessingSpeed(3)
        .register(plugin);

        new GrowthChamberEnd(DynaTechItems.DT_MACHINES, DynaTechItems.GROWTH_CHAMBER_END, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.HARDENED_GLASS,new ItemStack(Material.MAGENTA_STAINED_GLASS),SlimefunItems.HARDENED_GLASS,
                new ItemStack(Material.PURPUR_BLOCK),new ItemStack(Material.CHORUS_FLOWER),new ItemStack(Material.END_STONE),
                DynaTechItems.STAINLESS_STEEL,DynaTechItems.GROWTH_CHAMBER,DynaTechItems.STAINLESS_STEEL
        })
        .setCapacity(512)
        .setConsumption(32)
        .setProcessingSpeed(1)
        .register(plugin);

        new GrowthChamberEndMK2(DynaTechItems.DT_MACHINES, DynaTechItems.GROWTH_CHAMBER_END_MK2, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.STEEL_PLATE,DynaTechItems.GROWTH_CHAMBER_END,SlimefunItems.STEEL_PLATE,
                new ItemStack(Material.PURPUR_PILLAR),new ItemStack(Material.PURPLE_STAINED_GLASS),new ItemStack(Material.END_STONE_BRICKS),
                SlimefunItems.STEEL_PLATE,DynaTechItems.GROWTH_CHAMBER_END,SlimefunItems.STEEL_PLATE
        })
        .setCapacity(1024)
        .setConsumption(128)
        .setProcessingSpeed(3)
        .register(plugin);

        new GrowthChamberNether(DynaTechItems.DT_MACHINES, DynaTechItems.GROWTH_CHAMBER_NETHER, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.HARDENED_GLASS,new ItemStack(Material.RED_STAINED_GLASS),SlimefunItems.HARDENED_GLASS,
                new ItemStack(Material.CRIMSON_NYLIUM),new ItemStack(Material.SOUL_SAND),new ItemStack(Material.WARPED_NYLIUM),
                DynaTechItems.STAINLESS_STEEL,DynaTechItems.GROWTH_CHAMBER,DynaTechItems.STAINLESS_STEEL
        })
        .setCapacity(512)
        .setConsumption(32)
        .setProcessingSpeed(1)
        .register(plugin);

        new GrowthChamberNetherMK2(DynaTechItems.DT_MACHINES, DynaTechItems.GROWTH_CHAMBER_NETHER_MK2, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.STEEL_PLATE,DynaTechItems.GROWTH_CHAMBER_NETHER,SlimefunItems.STEEL_PLATE,
                new ItemStack(Material.CRIMSON_NYLIUM),new ItemStack(Material.SOUL_SAND),new ItemStack(Material.WARPED_NYLIUM),
                SlimefunItems.STEEL_PLATE,DynaTechItems.GROWTH_CHAMBER_NETHER,SlimefunItems.STEEL_PLATE
        })
        .setCapacity(1024)
        .setConsumption(128)
        .setProcessingSpeed(3)
        .register(plugin);

        new GrowthChamberOcean(DynaTechItems.DT_MACHINES, DynaTechItems.GROWTH_CHAMBER_OCEAN, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.HARDENED_GLASS,new ItemStack(Material.CYAN_STAINED_GLASS),SlimefunItems.HARDENED_GLASS,
                new ItemStack(Material.WATER_BUCKET),new ItemStack(Material.SAND),new ItemStack(Material.WATER_BUCKET),
                DynaTechItems.STAINLESS_STEEL,DynaTechItems.GROWTH_CHAMBER,DynaTechItems.STAINLESS_STEEL
        })
        .setCapacity(512)
        .setConsumption(32)
        .setProcessingSpeed(1)
        .register(plugin);

        new GrowthChamberOceanMK2(DynaTechItems.DT_MACHINES, DynaTechItems.GROWTH_CHAMBER_OCEAN_MK2, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.STEEL_PLATE,DynaTechItems.GROWTH_CHAMBER_OCEAN,SlimefunItems.STEEL_PLATE,
                new ItemStack(Material.GRAVEL),new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS),new ItemStack(Material.DIRT),
                SlimefunItems.STEEL_PLATE,DynaTechItems.GROWTH_CHAMBER_OCEAN,SlimefunItems.STEEL_PLATE

        })
        .setCapacity(1024)
        .setConsumption(128)
        .setProcessingSpeed(3)
        .register(plugin);

        new AntigravityBubble(DynaTechItems.DT_MACHINES, DynaTechItems.ANTIGRAVITY_BUBBLE, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.BLISTERING_INGOT_3, new ItemStack(Material.DIAMOND_BLOCK), SlimefunItems.BLISTERING_INGOT_3,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.BIG_CAPACITOR , SlimefunItems.REINFORCED_ALLOY_INGOT,
                SlimefunItems.REINFORCED_ALLOY_INGOT, DynaTechItems.STAINLESS_STEEL, SlimefunItems.REINFORCED_ALLOY_INGOT, 
        })
        .setCapacity(1024)
        .setConsumption(128)
        .setProcessingSpeed(1)
        .register(plugin);
        
        new WeatherController(DynaTechItems.DT_MACHINES, DynaTechItems.WEATHER_CONTROLLER, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                null,SlimefunItems.HARDENED_METAL_INGOT,null,
                new ItemStack(Material.WATER_BUCKET), DynaTechItems.ANCIENT_MACHINE_CORE, new ItemStack(Material.LAVA_BUCKET),
                DynaTechItems.STAINLESS_STEEL, new ItemStack(Material.CRYING_OBSIDIAN), DynaTechItems.STAINLESS_STEEL
        })
        .setCapacity(512)
        .setConsumption(32)
        .setProcessingSpeed(1)
        .register(plugin);
        
        new PotionSprinkler(DynaTechItems.DT_MACHINES, DynaTechItems.POTION_SPRINKLER, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.BREWING_STAND), null, new ItemStack(Material.BREWING_STAND),
                new ItemStack(Material.IRON_BARS), DynaTechItems.ANCIENT_MACHINE_CORE, new ItemStack(Material.IRON_BARS),
                SlimefunItems.FERROSILICON, SlimefunItems.FERROSILICON, SlimefunItems.FERROSILICON
        })
        .setCapacity(256)
        .setConsumption(32)
        .setProcessingSpeed(1)
        .register(plugin);
        
        new BarbedWire(DynaTechItems.DT_MACHINES, DynaTechItems.BARBED_WIRE, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.IRON_SWORD),new ItemStack(Material.IRON_SWORD),new ItemStack(Material.IRON_SWORD),
                DynaTechItems.STAINLESS_STEEL, new ItemStack(Material.OAK_LOG), DynaTechItems.STAINLESS_STEEL,
                SlimefunItems.ZINC_INGOT, SlimefunItems.ZINC_INGOT, SlimefunItems.ZINC_INGOT
        })
        .setCapacity(1024)
        .setConsumption(16)
        .setProcessingSpeed(1)
        .register(plugin);
        
        MaterialHive hive = new MaterialHive(DynaTechItems.DT_MACHINES, DynaTechItems.MATERIAL_HIVE, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.SYNTHETIC_DIAMOND, SlimefunItems.HARDENED_METAL_INGOT,
                SlimefunItems.HARDENED_METAL_INGOT, new ItemStack(Material.BEEHIVE), SlimefunItems.HARDENED_METAL_INGOT,
                DynaTechItems.ADVANCED_MACHINE_SCRAP, DynaTechItems.MACHINE_SCRAP, DynaTechItems.ADVANCED_MACHINE_SCRAP
        });

        hive
        .setCapacity(8192)
        .setConsumption(1024)
        .setProcessingSpeed(1)
        .register(plugin);   

        registerMineralizedApiaries(hive, plugin);

        new WirelessCharger(DynaTechItems.DT_MACHINES, DynaTechItems.WIRELESS_CHARGER, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                null, SlimefunItems.GPS_TRANSMITTER, null,
                SlimefunItems.GOLD_24K, SlimefunItems.CHARGING_BENCH, SlimefunItems.GOLD_24K,
                null, SlimefunItems.SMALL_CAPACITOR, null
        }, 16
        )
        .setCapacity(1024)
        .setConsumption(16)
        .setProcessingSpeed(1)
        .register(plugin);

        new SeedPlucker(DynaTechItems.DT_MACHINES, DynaTechItems.SEED_PLUCKER, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.HARDENED_GLASS,DynaTechItems.STAINLESS_STEEL ,SlimefunItems.HARDENED_GLASS,
                new ItemStack(Material.BONE_BLOCK),null,new ItemStack(Material.BONE_BLOCK),
                DynaTechItems.STAINLESS_STEEL,new ItemStack(Material.SHEARS) ,DynaTechItems.STAINLESS_STEEL                
        })
        .setCapacity(512)
        .setConsumption(32)
        .setProcessingSpeed(1)
        .register(plugin);
            

        new BandaidManager(DynaTechItems.DT_MACHINES, DynaTechItems.BANDAID_MANAGER, RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                SlimefunItems.BLANK_RUNE, DynaTechItems.ANCIENT_MACHINE_CORE, SlimefunItems.BLANK_RUNE,
                SlimefunItems.REINFORCED_CLOTH, new ItemStack(Material.ENCHANTING_TABLE), SlimefunItems.REINFORCED_CLOTH,
                null, SlimefunItems.WITHER_PROOF_OBSIDIAN, null
        })
        .setCapacity(1024)
        .setConsumption(48)
        .setProcessingSpeed(1)
        .register(plugin);
        
        new Orechid(DynaTechItems.DT_MACHINES, DynaTechItems.ORECHID, RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                SlimefunItems.ENDER_RUNE, SlimefunItems.ENDER_RUNE, SlimefunItems.ENDER_RUNE,
                SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.WITHER_ROSE), SlimefunItems.MAGIC_LUMP_3,
                SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.REINFORCED_PLATE, SlimefunItems.HARDENED_METAL_INGOT
        })
        .setCapacity(16384)
        .setConsumption(1024)
        .setProcessingSpeed(1)
        .register(plugin);
                
        //Make Wireless Energy Bank and Wireless Energy more costly
        new WirelessEnergyBank(DynaTechItems.DT_MACHINES, 10240, DynaTechItems.WIRELESS_ENERGY_BANK, RecipeType.ENHANCED_CRAFTING_TABLE, 
            new ItemStack[] {
                DynaTechItems.ADVANCED_MACHINE_SCRAP, DynaTechItems.WIRELESS_CHARGER, DynaTechItems.ADVANCED_MACHINE_SCRAP, 
                DynaTechItems.WIRELESS_CHARGER, SlimefunItems.BIG_CAPACITOR, DynaTechItems.WIRELESS_CHARGER,
                DynaTechItems.GHOSTLY_ESSENCE, DynaTechItems.WIRELESS_CHARGER, DynaTechItems.GHOSTLY_ESSENCE
        }).register(plugin);
        
        new WirelessEnergyPoint(DynaTechItems.DT_MACHINES, 5120, 1024, DynaTechItems.WIRELESS_ENERGY_POINT, RecipeType.ENHANCED_CRAFTING_TABLE, 
            new ItemStack[] {
                SlimefunItems.ENERGY_CONNECTOR, DynaTechItems.GHOSTLY_ESSENCE, SlimefunItems.ENERGY_CONNECTOR,
                DynaTechItems.GHOSTLY_ESSENCE, DynaTechItems.ANCIENT_MACHINE_CORE, DynaTechItems.GHOSTLY_ESSENCE,
                SlimefunItems.ENERGY_CONNECTOR, DynaTechItems.GHOSTLY_ESSENCE, SlimefunItems.ENERGY_CONNECTOR
        }).register(plugin);

        new WirelessItemInput(DynaTechItems.DT_MACHINES, 1024, DynaTechItems.WIRELESS_ITEM_INPUT, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.CARGO_INPUT_NODE, DynaTechItems.GHOSTLY_ESSENCE, SlimefunItems.CARGO_INPUT_NODE,
                DynaTechItems.GHOSTLY_ESSENCE, DynaTechItems.ANCIENT_MACHINE_CORE, DynaTechItems.GHOSTLY_ESSENCE,
                SlimefunItems.CARGO_INPUT_NODE, DynaTechItems.GHOSTLY_ESSENCE, SlimefunItems.CARGO_INPUT_NODE
        }).register(plugin);

        new WirelessItemOutput(DynaTechItems.DT_MACHINES, 1024, DynaTechItems.WIRELESS_ITEM_OUTPUT, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                DynaTechItems.ADVANCED_MACHINE_SCRAP, DynaTechItems.GHOSTLY_ESSENCE, DynaTechItems.ADVANCED_MACHINE_SCRAP,
                DynaTechItems.GHOSTLY_ESSENCE, SlimefunItems.BIG_CAPACITOR, DynaTechItems.GHOSTLY_ESSENCE,
                SlimefunItems.CARGO_OUTPUT_NODE_2, DynaTechItems.GHOSTLY_ESSENCE, SlimefunItems.CARGO_OUTPUT_NODE_2
        }).register(plugin);

        new Tesseract(DynaTechItems.DT_MACHINES, 65535, 1024, DynaTechItems.TESSERACT, RecipeType.MAGIC_WORKBENCH,
            new ItemStack[] {
                DynaTechItems.WIRELESS_ENERGY_BANK, DynaTechItems.TESSERACTING_OBJ, DynaTechItems.WIRELESS_ITEM_INPUT,
                DynaTechItems.TESSERACTING_OBJ, DynaTechItems.GHOSTLY_ESSENCE, DynaTechItems.TESSERACTING_OBJ,
                DynaTechItems.WIRELESS_ITEM_OUTPUT, DynaTechItems.TESSERACTING_OBJ, DynaTechItems.WIRELESS_ENERGY_POINT
        }).register(plugin);

        new FurnaceController(DynaTechItems.DT_MACHINES, DynaTechItems.EXTERNAL_HEATER, RecipeType.ENHANCED_CRAFTING_TABLE, 
            new ItemStack[] {
                DynaTechItems.STAINLESS_STEEL, SlimefunItems.HARDENED_METAL_INGOT, DynaTechItems.STAINLESS_STEEL,
                new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSERVER), new ItemStack(Material.OBSIDIAN),
                new ItemStack(Material.OBSIDIAN), SlimefunItems.ENERGY_REGULATOR, new ItemStack(Material.OBSIDIAN)
        })
        .setCapacity(2048)
        .setConsumption(128)
        .setProcessingSpeed(1)
        .register(plugin);

        new LiquidTank(DynaTechItems.DT_TOOLS, DynaTechItems.LIQUID_TANK, 16000, RecipeType.ENHANCED_CRAFTING_TABLE, 
            new ItemStack[] {
                DynaTechItems.STAINLESS_STEEL, new ItemStack(Material.BUCKET), DynaTechItems.STAINLESS_STEEL,
                new ItemStack(Material.BUCKET), new ItemStack(Material.BUCKET), new ItemStack(Material.BUCKET),
                DynaTechItems.STAINLESS_STEEL, new ItemStack(Material.BUCKET), DynaTechItems.STAINLESS_STEEL,
        }).register(plugin);
        

        //Generators
        new HydroGenerator(DynaTechItems.DT_GENERATORS, 16, 256, DynaTechItems.WATER_MILL, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.ALUMINUM_INGOT, SlimefunItems.SULFATE, SlimefunItems.ALUMINUM_INGOT,
                SlimefunItems.ALUMINUM_INGOT, SlimefunItems.ENERGY_CONNECTOR, SlimefunItems.ALUMINUM_INGOT,
                DynaTechItems.STAINLESS_STEEL_ROTOR, null, DynaTechItems.STAINLESS_STEEL_ROTOR,
        }).register(plugin);

        new HydroGenerator(DynaTechItems.DT_GENERATORS, 64, 512, DynaTechItems.WATER_TURBINE, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                DynaTechItems.WATER_MILL, SlimefunItems.REINFORCED_ALLOY_INGOT, DynaTechItems.WATER_MILL,
                SlimefunItems.REINFORCED_ALLOY_INGOT, SlimefunItems.GOLD_8K, SlimefunItems.REINFORCED_ALLOY_INGOT,
                DynaTechItems.WATER_MILL, SlimefunItems.REINFORCED_ALLOY_INGOT, DynaTechItems.WATER_MILL
        }).register(plugin);

        new DragonEggGenerator(DynaTechItems.DT_GENERATORS, DynaTechItems.DRAGON_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.CRYING_OBSIDIAN), new ItemStack(Material.END_STONE), new ItemStack(Material.CRYING_OBSIDIAN),
                new ItemStack(Material.CRYING_OBSIDIAN), SlimefunItems.LEAD_INGOT, new ItemStack(Material.CRYING_OBSIDIAN),
                SlimefunItems.LEAD_INGOT, SlimefunItems.MEDIUM_CAPACITOR, SlimefunItems.LEAD_INGOT
        }).register(plugin);

        new ChippingGenerator(DynaTechItems.DT_GENERATORS, DynaTechItems.CHIPPING_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE, 
            new ItemStack[] {
                DynaTechItems.STAINLESS_STEEL, DynaTechItems.STAINLESS_STEEL, DynaTechItems.STAINLESS_STEEL,
                new ItemStack(Material.DIAMOND_AXE), DynaTechItems.ANCIENT_MACHINE_CORE,new ItemStack(Material.DIAMOND_AXE),
                DynaTechItems.STAINLESS_STEEL, DynaTechItems.STAINLESS_STEEL, DynaTechItems.STAINLESS_STEEL
        })
        .setEnergyCapacity(256)
        .setEnergyProduction(8)
        .setProcessingSpeed(1)
        .register(plugin);

        new CulinaryGenerator(DynaTechItems.DT_GENERATORS, DynaTechItems.CULINARY_GENERATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                SlimefunItems.ALUMINUM_BRASS_INGOT,SlimefunItems.ALUMINUM_BRASS_INGOT,SlimefunItems.ALUMINUM_BRASS_INGOT,
                SlimefunItems.LEAD_DUST, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.LEAD_DUST,
                new ItemStack(Material.CAMPFIRE),new ItemStack(Material.CAMPFIRE),new ItemStack(Material.CAMPFIRE)
        })
        .setEnergyCapacity(256)
        .setEnergyProduction(16)
        .register(plugin);
        
        new StardustReactor(DynaTechItems.DT_GENERATORS, DynaTechItems.STARDUST_REACTOR, RecipeType.ENHANCED_CRAFTING_TABLE,
            new ItemStack[] {
                new ItemStack(Material.FIRE_CHARGE), new ItemStack(Material.FIRE_CHARGE),new ItemStack(Material.FIRE_CHARGE),
                null, SlimefunItems.NUCLEAR_REACTOR, null,
                DynaTechItems.ADVANCED_MACHINE_SCRAP, DynaTechItems.ANCIENT_MACHINE_CORE, DynaTechItems.ADVANCED_MACHINE_SCRAP    
        })
        .setEnergyCapacity(32676)
        .setEnergyProduction(1024)
        .register(plugin);
    }

	private static void registerMineralizedApiaries(MaterialHive hive, SlimefunAddon plugin) {
        for (String id: hive.slimefunItemsAccepted.getValue()) {
            SlimefunItem item = SlimefunItem.getById(id);

            if (item != null) {
                SlimefunItemStack apiary = new SlimefunItemStack("DT_" + id.replace("_INGOT", "") + "_MINERALIZED_APIARY",
                    Material.BEEHIVE,
                    "&f" + item.getItemName().replace(" Ingot", "") + " Mineralized Apiary",
                    "",
                    "&fProduces a material",
                    "&fwith the help of bees",
                    "",
                    LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
                    LoreBuilder.powerBuffer(16384),
                    LoreBuilderDynamic.powerPerTick(1024)
                );

                new MineralizedApiary(DynaTechItems.DT_HIVES, apiary, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                    SlimefunItems.LARGE_CAPACITOR, item.getItem(), SlimefunItems.LARGE_CAPACITOR,
                    item.getItem(), DynaTechItems.MATERIAL_HIVE, item.getItem(),
                    DynaTechItems.MACHINE_SCRAP, DynaTechItems.VEX_GEM, DynaTechItems.MACHINE_SCRAP,
                }, item.getItem())
                .setCapacity(16384)
                .setConsumption(1024)
                .setProcessingSpeed(1)
                .register(plugin);
            }
        }

    for (String name: hive.vanillaItemsAccepted.getValue()) {
            ItemStack item = new ItemStack(Material.matchMaterial(name));

            if (item != null) {
                SlimefunItemStack apiary = new SlimefunItemStack("DT_" + name.replace("_INGOT", "") + "_MINERALIZED_APIARY",
                    Material.BEEHIVE,
                    "&f" + ItemUtils.getItemName(item).replace(" Ingot", "") + " Mineralized Apiary",
                    "",
                    "&fProduces a material",
                    "&fwith the help of bees",
                    "",
                    LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
                    LoreBuilder.powerBuffer(16384),
                    LoreBuilderDynamic.powerPerTick(1024)
                );

                new MineralizedApiary(DynaTechItems.DT_HIVES, apiary, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                    SlimefunItems.LARGE_CAPACITOR, item, SlimefunItems.LARGE_CAPACITOR,
                    item, DynaTechItems.MATERIAL_HIVE, item,
                    DynaTechItems.MACHINE_SCRAP, DynaTechItems.VEX_GEM, DynaTechItems.MACHINE_SCRAP,
                }, item)
                .setCapacity(16384)
                .setConsumption(1024)
                .setProcessingSpeed(1)
                .register(plugin);
            }
        }

	}
}
