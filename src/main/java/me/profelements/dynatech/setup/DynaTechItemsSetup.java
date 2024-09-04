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
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.blocks.CokeOvenController;
import me.profelements.dynatech.fluids.FluidTank;
import me.profelements.dynatech.items.RecipeBook;
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
import me.profelements.dynatech.items.electric.generators.EggMill;
import me.profelements.dynatech.items.electric.generators.HydroGenerator;
import me.profelements.dynatech.items.electric.generators.StardustReactor;
import me.profelements.dynatech.items.electric.generators.WaterMill;
import me.profelements.dynatech.items.electric.generators.WindMill;
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
import me.profelements.dynatech.items.machines.PetalApothecary;
import me.profelements.dynatech.items.misc.Bee;
import me.profelements.dynatech.items.misc.ItemBand;
import me.profelements.dynatech.items.misc.MobDropItem;
import me.profelements.dynatech.items.misc.StarDustMeteor;
import me.profelements.dynatech.items.misc.VexGem;
import me.profelements.dynatech.items.misc.WitherGolem;
import me.profelements.dynatech.items.tools.AngelGem;
import me.profelements.dynatech.items.tools.AutoInputUpgrade;
import me.profelements.dynatech.items.tools.AutoOutputUpgrade;
import me.profelements.dynatech.items.tools.DimensionalHome;
import me.profelements.dynatech.items.tools.ElectricalStimulator;
import me.profelements.dynatech.items.tools.InventoryFilter;
import me.profelements.dynatech.items.tools.LiquidContainerItem;
import me.profelements.dynatech.items.tools.LiquidTank;
import me.profelements.dynatech.items.tools.Scoop;
import me.profelements.dynatech.items.tools.TesseractBinder;
import me.profelements.dynatech.registries.ItemGroups;
import me.profelements.dynatech.registries.Items;
import me.profelements.dynatech.registries.Recipes;
import me.profelements.dynatech.registries.TypedKey;
import me.profelements.dynatech.utils.ItemWrapper;
import me.profelements.dynatech.utils.LiquidRegistry;
import me.profelements.dynatech.utils.Recipe;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import dev.j3fftw.extrautils.utils.LoreBuilderDynamic;

import javax.annotation.Nonnull;

public class DynaTechItemsSetup {

    private DynaTechItemsSetup() {
    }

    public static void setup(@Nonnull DynaTech plugin) {

        // General
        // Resources
        new SlimefunItem(ItemGroups.RESOURCES, Items.ANCIENT_MACHINE_CORE.stack(),
                Recipes.ANCIENT_MACHINE_CORE.getRecipeType(), Recipes.ANCIENT_MACHINE_CORE.getInput()).register(plugin);

        new SlimefunItem(ItemGroups.RESOURCES, Items.MACHINE_SCRAP.stack(), Recipes.MACHINE_SCRAP.getRecipeType(),
                Recipes.MACHINE_SCRAP.getInput(), new SlimefunItemStack(Items.MACHINE_SCRAP.stack(), 8))
                .register(plugin);

        new SlimefunItem(ItemGroups.RESOURCES, Items.ADVANCED_MACHINE_SCRAP.stack(),
                Recipes.ADVANCED_MACHINE_SCRAP.getRecipeType(), Recipes.ADVANCED_MACHINE_SCRAP.getInput(),
                new SlimefunItemStack(Items.ADVANCED_MACHINE_SCRAP.stack(), 8)).register(plugin);

        new VexGem(ItemGroups.RESOURCES, Items.VEX_GEM.stack(), Recipes.VEX_GEM.getRecipeType(),
                Recipes.VEX_GEM.getInput()).register(plugin);

        new SlimefunItem(ItemGroups.RESOURCES, Items.STAR_DUST.stack(), Recipes.STAR_DUST.getRecipeType(),
                Recipes.STAR_DUST.getInput()).register(plugin);

        new StarDustMeteor(ItemGroups.RESOURCES).register(plugin);

        new MobDropItem(ItemGroups.RESOURCES, Items.GHOSTLY_ESSENCE.stack(), Recipes.GHOSTLY_ESSENCE.getRecipeType(),
                Recipes.GHOSTLY_ESSENCE.getInput(), 80).register(plugin);

        new SlimefunItem(ItemGroups.RESOURCES, Items.TESSERACTING_OBJ.stack(), Recipes.TESSERACTING_OBJ.getRecipeType(),
                Recipes.TESSERACTING_OBJ.getInput()).register(plugin);

        new Bee(ItemGroups.RESOURCES, Items.BEE.stack(), Recipes.BEE.getRecipeType(), Recipes.BEE.getInput(), 2)
                .register(plugin);

        new Bee(ItemGroups.RESOURCES, Items.ROBOTIC_BEE.stack(), Recipes.ROBOTIC_BEE.getRecipeType(),
                Recipes.ROBOTIC_BEE.getInput(), 7).register(plugin);

        new Bee(ItemGroups.RESOURCES, Items.ADVANCED_ROBOTIC_BEE.stack(),
                Recipes.ADVANCED_ROBOTIC_BEE.getRecipeType(), Recipes.ADVANCED_ROBOTIC_BEE.getInput(), 11)
                .register(plugin);

        // Machines
        // Generators

        // Materials

        // Tools
        new PicnicBasket(27, ItemGroups.TOOLS, Items.PICNIC_BASKET.stack(), Recipes.PICNIC_BASKET.getRecipeType(),
                Recipes.PICNIC_BASKET.getInput()).register(plugin);

        new SoulboundPicnicBacket(27, ItemGroups.TOOLS, Items.SOUL_BOUND_PICNIC_BASKET.stack(),
                Recipes.SOUL_BOUND_PICNIC_BASKET.getRecipeType(), Recipes.SOUL_BOUND_PICNIC_BASKET.getInput())
                .register(plugin);

        new ElectricalStimulator(ItemGroups.TOOLS, Items.ELECTRICAL_STIMULATOR.stack(),
                Recipes.ELECTRICAL_STIMULATOR.getRecipeType(),
                Recipes.ELECTRICAL_STIMULATOR.getInput()).register(plugin);

        new AngelGem(ItemGroups.TOOLS, Items.ANGEL_GEM.stack(), Recipes.ANGEL_GEM.getRecipeType(),
                Recipes.ANGEL_GEM.getInput()).register(plugin);

        new Scoop(ItemGroups.TOOLS, Items.SCOOP.stack(), Recipes.SCOOP.getRecipeType(), Recipes.SCOOP.getInput())
                .register(plugin);

        new DimensionalHome(ItemGroups.TOOLS, Items.DIMENSIONAL_HOME.stack(), Recipes.DIMENSIONAL_HOME.getRecipeType(),
                Recipes.DIMENSIONAL_HOME.getInput()).register(plugin);

        new ItemBand(ItemGroups.TOOLS, Items.ITEM_BAND_HEALTH.stack(), Recipes.ITEM_BAND_HEALTH.getRecipeType(),
                Recipes.ITEM_BAND_HEALTH.getInput(),
                new PotionEffect[] { new PotionEffect(PotionEffectType.HEALTH_BOOST, 20 * 15, 1, true) })
                .register(plugin);

        new ItemBand(ItemGroups.TOOLS, Items.ITEM_BAND_HASTE.stack(), Recipes.ITEM_BAND_HASTE.getRecipeType(),
                Recipes.ITEM_BAND_HASTE.getInput(),
                new PotionEffect[] { new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 15, 1, true) })
                .register(plugin);

        new TesseractBinder(ItemGroups.TOOLS, Items.TESSERACT_BINDER.stack(), Recipes.TESSERACT_BINDER.getRecipeType(),
                Recipes.TESSERACT_BINDER.getInput()).register(plugin);

        if (DynaTech.isInfinityExpansionInstalled()) {
            new MobDataCard("Vex", MobDataTier.HOSTILE, new ItemStack[] {
                    new SlimefunItemStack(Items.VEX_GEM.stack(), 16),
                    new SlimefunItemStack(Items.GHOSTLY_ESSENCE.stack(), 16),
                    new SlimefunItemStack(Items.VEX_GEM.stack(), 16),
                    new SlimefunItemStack(Items.GHOSTLY_ESSENCE.stack(), 16), MobData.EMPTY_DATA_CARD,
                    new SlimefunItemStack(Items.GHOSTLY_ESSENCE.stack(), 16),
                    new SlimefunItemStack(Items.VEX_GEM.stack(), 16),
                    new SlimefunItemStack(Items.GHOSTLY_ESSENCE.stack(), 16),
                    new SlimefunItemStack(Items.VEX_GEM.stack(), 16)
            })
                    .addDrop(Items.VEX_GEM.stack(), 1)
                    .addDrop(Items.GHOSTLY_ESSENCE.stack(), 9)
                    .register(plugin);

            new MobDataCard("Phantom", MobDataTier.HOSTILE, new ItemStack[] {
                    new ItemStack(Material.PHANTOM_MEMBRANE, 16), new ItemStack(Material.PHANTOM_MEMBRANE, 16),
                    new ItemStack(Material.PHANTOM_MEMBRANE, 16),
                    new ItemStack(Material.PHANTOM_MEMBRANE, 16), MobData.EMPTY_DATA_CARD,
                    new ItemStack(Material.PHANTOM_MEMBRANE, 16),
                    new ItemStack(Material.PHANTOM_MEMBRANE, 16), new ItemStack(Material.PHANTOM_MEMBRANE, 16),
                    new ItemStack(Material.PHANTOM_MEMBRANE, 16),
            })
                    .addDrop(Material.PHANTOM_MEMBRANE, 0.25f)
                    .register(plugin);
        }

        new WitherGolem(ItemGroups.TOOLS, Items.WITHER_SKELETON_GOLEM.stack()).register(plugin);

        // START Machines
        if (DynaTech.isExoticGardenInstalled()) {
            new KitchenAutoCrafter(ItemGroups.MACHINES, Items.KITCHEN_AUTO_CRAFTER.stack(),
                    Recipes.KITCHEN_AUTO_CRAFTER.getRecipeType(), Recipes.KITCHEN_AUTO_CRAFTER.getInput())
                    .setCapacity(512)
                    .setEnergyConsumption(16)
                    .register(plugin);
        }

        new GrowthChamber(ItemGroups.MACHINES, Items.GROWTH_CHAMBER.stack(), Recipes.GROWTH_CHAMBER.getRecipeType(),
                Recipes.GROWTH_CHAMBER.getInput())
                .setCapacity(512)
                .setConsumption(32)
                .setProcessingSpeed(1)
                .register(plugin);

        new GrowthChamberMK2(ItemGroups.MACHINES, Items.GROWTH_CHAMBER_MK2.stack(),
                Recipes.GROWTH_CHAMBER_MK2.getRecipeType(), Recipes.GROWTH_CHAMBER_MK2.getInput())
                .setCapacity(1024)
                .setConsumption(128)
                .setProcessingSpeed(3)
                .register(plugin);

        new GrowthChamberEnd(ItemGroups.MACHINES, Items.GROWTH_CHAMBER_END.stack(),
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.HARDENED_GLASS, new ItemStack(Material.MAGENTA_STAINED_GLASS),
                        SlimefunItems.HARDENED_GLASS,
                        new ItemStack(Material.PURPUR_BLOCK), new ItemStack(Material.CHORUS_FLOWER),
                        new ItemStack(Material.END_STONE),
                        Items.STAINLESS_STEEL_INGOT.stack(), Items.GROWTH_CHAMBER.stack(),
                        Items.STAINLESS_STEEL_INGOT.stack()
                })
                .setCapacity(512)
                .setConsumption(32)
                .setProcessingSpeed(1)
                .register(plugin);

        new GrowthChamberEndMK2(ItemGroups.MACHINES, Items.GROWTH_CHAMBER_MK2_END.stack(),
                Recipes.GROWTH_CHAMBER_MK2_END.getRecipeType(), Recipes.GROWTH_CHAMBER_MK2_END.getInput())
                .setCapacity(1024)
                .setConsumption(128)
                .setProcessingSpeed(3)
                .register(plugin);
        new GrowthChamberNether(ItemGroups.MACHINES, Items.GROWTH_CHAMBER_NETHER.stack(),
                Recipes.GROWTH_CHAMBER_NETHER.getRecipeType(), Recipes.GROWTH_CHAMBER_NETHER.getInput())
                .setCapacity(512)
                .setConsumption(32)
                .setProcessingSpeed(1)
                .register(plugin);

        new GrowthChamberNetherMK2(ItemGroups.MACHINES, Items.GROWTH_CHAMBER_MK2_NETHER.stack(),
                Recipes.GROWTH_CHAMBER_MK2_NETHER.getRecipeType(), Recipes.GROWTH_CHAMBER_MK2_NETHER.getInput())
                .setCapacity(1024)
                .setConsumption(128)
                .setProcessingSpeed(3)
                .register(plugin);

        new GrowthChamberOcean(ItemGroups.MACHINES, Items.GROWTH_CHAMBER_OCEAN.stack(),
                Recipes.GROWTH_CHAMBER_OCEAN.getRecipeType(), Recipes.GROWTH_CHAMBER_OCEAN.getInput())
                .setCapacity(512)
                .setConsumption(32)
                .setProcessingSpeed(1)
                .register(plugin);

        new GrowthChamberOceanMK2(ItemGroups.MACHINES, Items.GROWTH_CHAMBER_MK2_OCEAN.stack(),
                Recipes.GROWTH_CHAMBER_MK2_OCEAN.getRecipeType(), Recipes.GROWTH_CHAMBER_MK2_OCEAN.getInput())
                .setCapacity(1024)
                .setConsumption(128)
                .setProcessingSpeed(3)
                .register(plugin);

        new AntigravityBubble(ItemGroups.MACHINES, Items.ANTIGRAVITY_BUBBLE.stack(),
                Recipes.ANTIGRAVITY_BUBBLE.getRecipeType(), Recipes.ANTIGRAVITY_BUBBLE.getInput())
                .setCapacity(1024)
                .setConsumption(128)
                .setProcessingSpeed(1)
                .register(plugin);

        new WeatherController(ItemGroups.MACHINES, Items.WEATHER_CONTROLLER.stack(),
                Recipes.WEATHER_CONTROLLER.getRecipeType(), Recipes.WEATHER_CONTROLLER.getInput())
                .setCapacity(512)
                .setConsumption(32)
                .setProcessingSpeed(1)
                .register(plugin);

        new PotionSprinkler(ItemGroups.MACHINES, Items.POTION_SPRINKLER.stack(),
                Recipes.POTION_SPRINKLER.getRecipeType(), Recipes.POTION_SPRINKLER.getInput())
                .setCapacity(256)
                .setConsumption(32)
                .setProcessingSpeed(1)
                .register(plugin);

        new BarbedWire(ItemGroups.MACHINES, Items.BARBED_WIRE.stack(), Recipes.BARBED_WIRE.getRecipeType(),
                Recipes.BARBED_WIRE.getInput())
                .setCapacity(1024)
                .setConsumption(16)
                .setProcessingSpeed(1)
                .register(plugin);

        MaterialHive hive = new MaterialHive(ItemGroups.MACHINES, Items.MATERIAL_HIVE.stack(),
                Recipes.MATERIAL_HIVE.getRecipeType(), Recipes.MATERIAL_HIVE.getInput());

        hive
                .setCapacity(8192)
                .setConsumption(1024)
                .setProcessingSpeed(1)
                .register(plugin);

        registerMineralizedApiaries(hive, plugin);

        new WirelessCharger(ItemGroups.MACHINES, Items.WIRELESS_CHARGER.stack(),
                Recipes.WIRELESS_CHARGER.getRecipeType(), Recipes.WIRELESS_CHARGER.getInput(), 16)
                .setCapacity(1024)
                .setConsumption(16)
                .setProcessingSpeed(1)
                .register(plugin);

        new SeedPlucker(ItemGroups.MACHINES, Items.SEED_PLUCKER.stack(), Recipes.SEED_PLUCKER.getRecipeType(),
                Recipes.SEED_PLUCKER.getInput())
                .setCapacity(512)
                .setConsumption(32)
                .setProcessingSpeed(1)
                .register(plugin);

        new BandaidManager(ItemGroups.MACHINES, Items.BANDAID_MANAGER.stack(), RecipeType.MAGIC_WORKBENCH,
                new ItemStack[] {
                        SlimefunItems.BLANK_RUNE, Items.ANCIENT_MACHINE_CORE.stack(), SlimefunItems.BLANK_RUNE,
                        SlimefunItems.REINFORCED_CLOTH, new ItemStack(Material.ENCHANTING_TABLE),
                        SlimefunItems.REINFORCED_CLOTH,
                        null, SlimefunItems.WITHER_PROOF_OBSIDIAN, null
                })
                .setCapacity(1024)
                .setConsumption(48)
                .setProcessingSpeed(1)
                .register(plugin);

        new Orechid(ItemGroups.MACHINES, Items.ORECHID.stack(), RecipeType.MAGIC_WORKBENCH,
                new ItemStack[] {
                        SlimefunItems.ENDER_RUNE, SlimefunItems.ENDER_RUNE, SlimefunItems.ENDER_RUNE,
                        SlimefunItems.MAGIC_LUMP_3, new ItemStack(Material.WITHER_ROSE), SlimefunItems.MAGIC_LUMP_3,
                        SlimefunItems.HARDENED_METAL_INGOT, SlimefunItems.REINFORCED_PLATE,
                        SlimefunItems.HARDENED_METAL_INGOT
                })
                .setCapacity(16384)
                .setConsumption(1024)
                .setProcessingSpeed(1)
                .register(plugin);

        // Make Wireless Energy Bank and Wireless Energy more costly
        new WirelessEnergyBank(ItemGroups.MACHINES, 10240, Items.WIRELESS_ENERGY_BANK.stack(),
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        Items.ADVANCED_MACHINE_SCRAP.stack(), Items.WIRELESS_CHARGER.stack(),
                        Items.ADVANCED_MACHINE_SCRAP.stack(),
                        Items.WIRELESS_CHARGER.stack(), SlimefunItems.BIG_CAPACITOR, Items.WIRELESS_CHARGER.stack(),
                        Items.GHOSTLY_ESSENCE.stack(), Items.WIRELESS_CHARGER.stack(), Items.GHOSTLY_ESSENCE.stack()
                }).register(plugin);

        new WirelessEnergyPoint(ItemGroups.MACHINES, 5120, 1024, Items.WIRELESS_ENERGY_POINT.stack(),
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.ENERGY_CONNECTOR, Items.GHOSTLY_ESSENCE.stack(), SlimefunItems.ENERGY_CONNECTOR,
                        Items.GHOSTLY_ESSENCE.stack(), Items.ANCIENT_MACHINE_CORE.stack(),
                        Items.GHOSTLY_ESSENCE.stack(),
                        SlimefunItems.ENERGY_CONNECTOR, Items.GHOSTLY_ESSENCE.stack(), SlimefunItems.ENERGY_CONNECTOR
                }).register(plugin);

        new WirelessItemInput(ItemGroups.MACHINES, 1024, Items.WIRELESS_ITEM_INPUT.stack(),
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.CARGO_INPUT_NODE, Items.GHOSTLY_ESSENCE.stack(), SlimefunItems.CARGO_INPUT_NODE,
                        Items.GHOSTLY_ESSENCE.stack(), Items.ANCIENT_MACHINE_CORE.stack(),
                        Items.GHOSTLY_ESSENCE.stack(),
                        SlimefunItems.CARGO_INPUT_NODE, Items.GHOSTLY_ESSENCE.stack(), SlimefunItems.CARGO_INPUT_NODE
                }).register(plugin);

        new WirelessItemOutput(ItemGroups.MACHINES, 1024, Items.WIRELESS_ITEM_OUTPUT.stack(),
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        Items.ADVANCED_MACHINE_SCRAP.stack(), Items.GHOSTLY_ESSENCE.stack(),
                        Items.ADVANCED_MACHINE_SCRAP.stack(),
                        Items.GHOSTLY_ESSENCE.stack(), SlimefunItems.BIG_CAPACITOR, Items.GHOSTLY_ESSENCE.stack(),
                        SlimefunItems.CARGO_OUTPUT_NODE_2, Items.GHOSTLY_ESSENCE.stack(),
                        SlimefunItems.CARGO_OUTPUT_NODE_2
                }).register(plugin);

        new Tesseract(ItemGroups.MACHINES, 65535, 1024, Items.TESSERACT.stack(), RecipeType.MAGIC_WORKBENCH,
                new ItemStack[] {
                        Items.WIRELESS_ENERGY_BANK.stack(), Items.TESSERACTING_OBJ.stack(),
                        Items.WIRELESS_ITEM_INPUT.stack(),
                        Items.TESSERACTING_OBJ.stack(), Items.GHOSTLY_ESSENCE.stack(), Items.TESSERACTING_OBJ.stack(),
                        Items.WIRELESS_ITEM_OUTPUT.stack(), Items.TESSERACTING_OBJ.stack(),
                        Items.WIRELESS_ENERGY_POINT.stack()
                }).register(plugin);

        new FurnaceController(ItemGroups.MACHINES, Items.EXTERNAL_HEATER.stack(),
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        Items.STAINLESS_STEEL_INGOT.stack(), SlimefunItems.HARDENED_METAL_INGOT,
                        Items.STAINLESS_STEEL_INGOT.stack(),
                        new ItemStack(Material.OBSIDIAN), new ItemStack(Material.OBSERVER),
                        new ItemStack(Material.OBSIDIAN),
                        new ItemStack(Material.OBSIDIAN), SlimefunItems.ENERGY_REGULATOR,
                        new ItemStack(Material.OBSIDIAN)
                })
                .setCapacity(2048)
                .setConsumption(128)
                .setProcessingSpeed(1)
                .register(plugin);

        // START EXPERIMENTAL SHENANIGANS
        new PetalApothecary(ItemGroups.EXPERIMENTAL,
                Items.PETAL_APOTHECARY.stack()).register(plugin);
        // END EXPERIMENTAL SHENANIGANS
        // END Machines
        new LiquidTank(ItemGroups.TOOLS, Items.LIQUID_TANK.stack(), 16000, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        Items.STAINLESS_STEEL_INGOT.stack(), new ItemStack(Material.BUCKET),
                        Items.STAINLESS_STEEL_INGOT.stack(),
                        new ItemStack(Material.BUCKET), new ItemStack(Material.BUCKET), new ItemStack(Material.BUCKET),
                        Items.STAINLESS_STEEL_INGOT.stack(), new ItemStack(Material.BUCKET),
                        Items.STAINLESS_STEEL_INGOT.stack(),
                }).register(plugin);

        // START Generators
        new ChippingGenerator(ItemGroups.GENERATORS, Items.DURABILITY_GENERATOR.stack(),
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        Items.STAINLESS_STEEL_INGOT.stack(), Items.STAINLESS_STEEL_INGOT.stack(),
                        Items.STAINLESS_STEEL_INGOT.stack(),
                        new ItemStack(Material.DIAMOND_AXE), Items.ANCIENT_MACHINE_CORE.stack(),
                        new ItemStack(Material.DIAMOND_AXE),
                        Items.STAINLESS_STEEL_INGOT.stack(), Items.STAINLESS_STEEL_INGOT.stack(),
                        Items.STAINLESS_STEEL_INGOT.stack(),
                })
                .setEnergyCapacity(256)
                .setEnergyProduction(8)
                .setProcessingSpeed(1)
                .register(plugin);

        new CulinaryGenerator(ItemGroups.GENERATORS, Items.FOOD_GENERATOR.stack(),
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        SlimefunItems.ALUMINUM_BRASS_INGOT, SlimefunItems.ALUMINUM_BRASS_INGOT,
                        SlimefunItems.ALUMINUM_BRASS_INGOT,
                        SlimefunItems.LEAD_DUST, SlimefunItems.SMALL_CAPACITOR, SlimefunItems.LEAD_DUST,
                        new ItemStack(Material.CAMPFIRE), new ItemStack(Material.CAMPFIRE),
                        new ItemStack(Material.CAMPFIRE)
                })
                .setEnergyCapacity(256)
                .setEnergyProduction(16)
                .register(plugin);

        new StardustReactor(ItemGroups.GENERATORS, Items.STARDUST_GENERATOR.stack(),
                RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[] {
                        new ItemStack(Material.FIRE_CHARGE), new ItemStack(Material.FIRE_CHARGE),
                        new ItemStack(Material.FIRE_CHARGE),
                        null, SlimefunItems.NUCLEAR_REACTOR, null,
                        Items.ADVANCED_MACHINE_SCRAP.stack(), Items.ANCIENT_MACHINE_CORE.stack(),
                        Items.ADVANCED_MACHINE_SCRAP.stack()
                })
                .setEnergyCapacity(32676)
                .setEnergyProduction(1024)
                .register(plugin);
        // END Generators
        // START Mechanical Components

        // final Recipe woodMachineCoreRecipe = Recipes.WOOD_MACHINE_CORE;
        new SlimefunItem(ItemGroups.EXPERIMENTAL, Items.WOOD_MACHINE_CORE.stack()).register(plugin);
        // new UnplaceableBlock(ItemGroups.EXPERIMENTAL,
        // Items.WOOD_MACHINE_CORE.stack()).register(plugin);

        final Recipe stoneMachineCoreRecipe = Recipes.STONE_MACHINE_CORE;
        new UnplaceableBlock(ItemGroups.EXPERIMENTAL, Items.STONE_MACHINE_CORE.stack(),
                stoneMachineCoreRecipe.getRecipeType(), stoneMachineCoreRecipe.getInput()).register(plugin);

        final Recipe ironMachineCoreRecipe = Recipes.IRON_MACHINE_CORE;
        new UnplaceableBlock(ItemGroups.EXPERIMENTAL, Items.IRON_MACHINE_CORE.stack(),
                ironMachineCoreRecipe.getRecipeType(), ironMachineCoreRecipe.getInput()).register(plugin);

        final Recipe diamondMachineCoreRecipe = Recipes.DIAMOND_MACHINE_CORE;
        new UnplaceableBlock(ItemGroups.EXPERIMENTAL, Items.DIAMOND_MACHINE_CORE.stack(),
                diamondMachineCoreRecipe.getRecipeType(), diamondMachineCoreRecipe.getInput()).register(plugin);

        final Recipe enchantedMachineCoreRecipe = Recipes.ENCHANTED_MACHINE_CORE;
        new UnplaceableBlock(ItemGroups.EXPERIMENTAL, Items.ENCHANTED_MACHINE_CORE.stack(),
                enchantedMachineCoreRecipe.getRecipeType(), enchantedMachineCoreRecipe.getInput()).register(plugin);

        final Recipe energyStorageRecipe = Recipes.ENERGY_STORAGE_COMPONENT;
        new UnplaceableBlock(ItemGroups.EXPERIMENTAL, Items.ENERGY_STORAGE_COMPONENT.stack(),
                energyStorageRecipe.getRecipeType(), energyStorageRecipe.getInput()).register(plugin);

        final Recipe energyInputRecipe = Recipes.ENERGY_INPUT_COMPONENT;
        new UnplaceableBlock(ItemGroups.EXPERIMENTAL, Items.ENERGY_INPUT_COMPONENT.stack(),
                energyInputRecipe.getRecipeType(), energyInputRecipe.getInput()).register(plugin);

        final Recipe energyOutputRecipe = Recipes.ENERGY_OUTPUT_COMPONENT;
        new UnplaceableBlock(ItemGroups.EXPERIMENTAL, Items.ENERGY_OUTPUT_COMPONENT.stack(),
                energyOutputRecipe.getRecipeType(), energyOutputRecipe.getInput()).register(plugin);

        final Recipe degradedWaterMillRecipe = Recipes.DEGRADED_WATER_MILL;
        new SlimefunItem(ItemGroups.EXPERIMENTAL, Items.DEGRADED_WATER_MILL.stack(),
                degradedWaterMillRecipe.getRecipeType(), degradedWaterMillRecipe.getInput()).register(plugin);

        final Recipe degradedWaterMill2Recipe = Recipes.DEGRADED_WATER_MILL_2;
        new SlimefunItem(ItemGroups.EXPERIMENTAL, Items.DEGRADED_WATER_MILL_2.stack(),
                degradedWaterMill2Recipe.getRecipeType(),
                degradedWaterMill2Recipe.getInput()).register(plugin);

        final Recipe degradedWindMillRecipe = Recipes.DEGRADED_WIND_MILL;
        new SlimefunItem(ItemGroups.EXPERIMENTAL, Items.DEGRADED_WIND_MILL.stack(),
                degradedWindMillRecipe.getRecipeType(),
                degradedWindMillRecipe.getInput()).register(plugin);

        final Recipe degradedWindMill2Recipe = Recipes.DEGRADED_WIND_MILL_2;
        new SlimefunItem(ItemGroups.EXPERIMENTAL, Items.DEGRADED_WIND_MILL_2.stack(),
                degradedWindMill2Recipe.getRecipeType(),
                degradedWindMill2Recipe.getInput()).register(plugin);

        final Recipe degradedEggMillRecipe = Recipes.DEGRADED_EGG_MILL;
        new SlimefunItem(ItemGroups.EXPERIMENTAL, Items.DEGRADED_EGG_MILL.stack(),
                degradedEggMillRecipe.getRecipeType(),
                degradedEggMillRecipe.getInput()).register(plugin);

        final Recipe degradedEggMill2Recipe = Recipes.DEGRADED_EGG_MILL_2;
        new SlimefunItem(ItemGroups.EXPERIMENTAL, Items.DEGRADED_EGG_MILL_2.stack(),
                degradedEggMill2Recipe.getRecipeType(),
                degradedEggMill2Recipe.getInput()).register(plugin);

        // END Mechanical Components

        // START Energy Generators

        final Recipe waterMillRecipe = Recipes.WATER_MILL;
        new WaterMill(ItemGroups.EXPERIMENTAL, Items.WATER_MILL.stack(), waterMillRecipe, 64, 16, 2500)
                .register(plugin);

        final Recipe waterMill2Recipe = Recipes.WATER_MILL_2;
        new WaterMill(ItemGroups.EXPERIMENTAL, Items.WATER_MILL_2.stack(), waterMill2Recipe, 256, 64, 5000)
                .register(plugin);

        final Recipe windMillRecipe = Recipes.WIND_MILL;
        new WindMill(ItemGroups.EXPERIMENTAL, Items.WIND_MILL.stack(), windMillRecipe, 64, 16, 256, 2500)
                .register(plugin);

        final Recipe windMill2Recipe = Recipes.WIND_MILL_2;
        new WindMill(ItemGroups.EXPERIMENTAL, Items.WIND_MILL_2.stack(), windMill2Recipe, 256, 64, 1024, 5000)
                .register(plugin);

        final Recipe eggMillRecipe = Recipes.EGG_MILL;
        new EggMill(ItemGroups.EXPERIMENTAL, Items.EGG_MILL.stack(), eggMillRecipe, 2500, 16, 64)
                .register(plugin);

        final Recipe eggMill2Recipe = Recipes.EGG_MILL_2;
        new EggMill(ItemGroups.EXPERIMENTAL, Items.EGG_MILL_2.stack(), eggMill2Recipe, 5000, 64, 256)
                .register(plugin);

        // END Energy Generators

        // START Materials

        final Recipe stainlessSteelIngotRecipe = Recipes.STAINLESS_STEEL_INGOT;
        new SlimefunItem(ItemGroups.EXPERIMENTAL, Items.STAINLESS_STEEL_INGOT.stack(),
                stainlessSteelIngotRecipe.getRecipeType(), stainlessSteelIngotRecipe.getInput()).register(plugin);

        final Recipe stainlessSteelRotorRecipe = Recipes.STAINLESS_STEEL_ROTOR;
        new SlimefunItem(ItemGroups.EXPERIMENTAL, Items.STAINLESS_STEEL_ROTOR.stack(),
                stainlessSteelRotorRecipe.getRecipeType(), stainlessSteelRotorRecipe.getInput()).register(plugin);

        final Recipe coalCokeRecipe = Recipes.COAL_TO_COAL_COKE;
        new SlimefunItem(ItemGroups.EXPERIMENTAL, Items.COAL_COKE.stack(), coalCokeRecipe.getRecipeType(),
                coalCokeRecipe.getInput()).register(plugin);

        // END Materials

        // START Liquids
        new LiquidContainerItem(ItemGroups.EXPERIMENTAL, Items.LAVA_BOTTLE.stack(), RecipeType.NULL,
                new ItemStack[] {}, LiquidRegistry.getInstance().getByKey(NamespacedKey.minecraft("lava")), 250)
                .register(plugin);

        new LiquidContainerItem(ItemGroups.EXPERIMENTAL, Items.HONEY_BUCKET.stack(), RecipeType.NULL,
                new ItemStack[] {}, LiquidRegistry.getInstance().getByKey(NamespacedKey.minecraft("honey")), 1000)
                .register(plugin);

        new LiquidContainerItem(ItemGroups.EXPERIMENTAL, Items.POTION_BUCKET.stack(), RecipeType.NULL,
                new ItemStack[] {}, LiquidRegistry.getInstance().getByKey(NamespacedKey.minecraft("potion")), 1000)
                .register(plugin);

        new LiquidContainerItem(ItemGroups.EXPERIMENTAL, Items.MILK_BOTTLE.stack(), RecipeType.NULL,
                new ItemStack[] {}, LiquidRegistry.getInstance().getByKey(NamespacedKey.minecraft("milk")), 1000)
                .register(plugin);
        // END Liquids

        // START Tools
        final Recipe inventoryFilterRecipe = Recipes.INVENTORY_FILTER;
        new InventoryFilter(ItemGroups.EXPERIMENTAL, Items.INVENTORY_FILTER.stack(),
                inventoryFilterRecipe.getRecipeType(), inventoryFilterRecipe.getInput()).register(plugin);

        new RecipeBook(ItemGroups.EXPERIMENTAL, Items.RECIPE_BOOK.stack(), Recipes.RECIPE_BOOK).register(plugin);

        new AutoOutputUpgrade(ItemGroups.EXPERIMENTAL, Items.AUTO_OUTPUT_UPGRADE.stack(),
                Recipes.AUTO_OUTPUT_UGPRADE).register(plugin);

        new AutoInputUpgrade(ItemGroups.EXPERIMENTAL, Items.AUTO_INPUT_UPGRADE.stack(),
                Recipes.AUTO_INPUT_UPGRADE)
                .register(plugin);

        new FluidTank(ItemGroups.EXPERIMENTAL, Items.FLUID_TANK.stack()).register(plugin);
        // END Tools

        // START Machines
        new CokeOvenController(Items.COAL_COKE_OVEN.stack()).register(plugin);
    }

    private static void registerMineralizedApiaries(MaterialHive hive, SlimefunAddon plugin) {
        for (String id : hive.slimefunItemsAccepted.getValue()) {
            SlimefunItem item = SlimefunItem.getById(id);

            if (item != null) {

                TypedKey<ItemWrapper> APIARY_KEY = TypedKey.create("dynatech",
                        id.replace("_INGOT", "").toLowerCase() + "mineralized_apiary");
                ItemWrapper APIARY = ItemWrapper.create(APIARY_KEY, new SlimefunItemStack(
                        APIARY_KEY.asSlimefunId(),
                        Material.BEEHIVE,
                        "&f" + item.getItemName().replace(" Ingot", "") + " Mineralized Apiary",
                        "",
                        "&fProduces a material",
                        "&fwith the help of bees",
                        "",
                        LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
                        LoreBuilder.powerBuffer(16384),
                        LoreBuilderDynamic.powerPerTick(1024)));

                Recipe APIARY_RECIPE = Recipe.init()
                        .setKey(APIARY_KEY.key())
                        .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
                        .setInput(new ItemStack[] {
                                SlimefunItems.LARGE_CAPACITOR, item.getItem(), SlimefunItems.LARGE_CAPACITOR,
                                item.getItem(), Items.MATERIAL_HIVE.stack(), item.getItem(),
                                Items.MACHINE_SCRAP.stack(), Items.VEX_GEM.stack(), Items.MACHINE_SCRAP.stack(),
                        })
                        .setOutput(APIARY.stack())
                        .register();

                new MineralizedApiary(ItemGroups.HIVES, APIARY.stack(), APIARY_RECIPE.getRecipeType(),
                        APIARY_RECIPE.getInput(), item.getItem())
                        .setCapacity(16384)
                        .setConsumption(1024)
                        .setProcessingSpeed(1)
                        .register(plugin);
            }
        }

        for (String name : hive.vanillaItemsAccepted.getValue()) {
            ItemStack item = new ItemStack(Material.matchMaterial(name));
            TypedKey<ItemWrapper> APIARY_KEY = TypedKey.create("dynatech",
                    name.replace("_INGOT", "").toLowerCase() + "mineralized_apiary");

            ItemWrapper APIARY = ItemWrapper.create(APIARY_KEY, new SlimefunItemStack(APIARY_KEY.asSlimefunId(),
                    Material.BEEHIVE,
                    "&f" + ItemUtils.getItemName(item).replace(" Ingot", "") + " Mineralized Apiary",
                    "",
                    "&fProduces a material",
                    "&fwith the help of bees",
                    "",
                    LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
                    LoreBuilder.powerBuffer(16384),
                    LoreBuilderDynamic.powerPerTick(1024)));

            Recipe APIARY_RECIPE = Recipe.init()
                    .setKey(APIARY_KEY.key())
                    .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
                    .setInput(new ItemStack[] {
                            SlimefunItems.LARGE_CAPACITOR, item, SlimefunItems.LARGE_CAPACITOR,
                            item, Items.MATERIAL_HIVE.stack(), item,
                            Items.MACHINE_SCRAP.stack(), Items.VEX_GEM.stack(), Items.MACHINE_SCRAP.stack(),
                    })
                    .setOutput(APIARY.stack())
                    .register();

            new MineralizedApiary(ItemGroups.HIVES, APIARY.stack(), APIARY_RECIPE.getRecipeType(),
                    APIARY_RECIPE.getInput(), item)
                    .setCapacity(16384)
                    .setConsumption(1024)
                    .setProcessingSpeed(1)
                    .register(plugin);
        }

    }
}
