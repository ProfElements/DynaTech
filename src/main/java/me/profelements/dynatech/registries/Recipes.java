package me.profelements.dynatech.registries;

import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.DynaTechItems;
import me.profelements.dynatech.utils.Recipe;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.bakedlibs.dough.items.CustomItemStack;

import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class Recipes {
    // START common use items
    private static final ItemStack STICK = new ItemStack(Material.STICK);
    private static final ItemStack OAK_LOG = new ItemStack(Material.OAK_LOG);
    private static final ItemStack REDSTONE_BLOCK = new ItemStack(Material.REDSTONE_BLOCK);
    private static final ItemStack BRICK = new ItemStack(Material.BRICK);
    private static final ItemStack STONE = new ItemStack(Material.STONE);
    private static final ItemStack IRON_INGOT = new ItemStack(Material.IRON_INGOT);
    private static final ItemStack IRON_BLOCK = new ItemStack(Material.IRON_BLOCK);
    private static final ItemStack DIAMOND = new ItemStack(Material.DIAMOND);
    private static final ItemStack DIAMOND_BLOCK = new ItemStack(Material.DIAMOND_BLOCK);
    private static final ItemStack CRYING_OBSIDIAN = new ItemStack(Material.CRYING_OBSIDIAN);
    private static final ItemStack UNBREAKING_3_ENCHANTED_BOOK = new CustomItemStack(Material.ENCHANTED_BOOK, meta -> {
        meta.addEnchant(Enchantment.DURABILITY, 3, false);
    });
    private static final ItemStack GLASS = new ItemStack(Material.GLASS);
    private static final ItemStack CYAN_CONCRETE = new ItemStack(Material.LIGHT_BLUE_CONCRETE);
    private static final ItemStack PINK_CONCRETE = new ItemStack(Material.PINK_CONCRETE);
    private static final ItemStack STRING = new ItemStack(Material.STRING);
    private static final ItemStack IRON_BARS = new ItemStack(Material.IRON_BARS);
    private static final ItemStack END_STONE = new ItemStack(Material.END_STONE);
    private static final ItemStack CHORUS_FRUIT = new ItemStack(Material.CHORUS_FRUIT);
    private static final ItemStack BOOK = new ItemStack(Material.BOOK);
    // END common use items

    // START Mechanical Components

    // Wood Machine Core
    public static final Recipe WOOD_MACHINE_CORE = Recipe.init()
            .setKey(Keys.WOOD_MACHINE_CORE.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] { STICK, OAK_LOG, STICK, OAK_LOG, REDSTONE_BLOCK, OAK_LOG, STICK, OAK_LOG,
                    STICK })
            .setOutput(Items.WOOD_MACHINE_CORE.stack())
            .register();

    // Stone Machine Core
    public static final Recipe STONE_MACHINE_CORE = Recipe.init()
            .setKey(Keys.STONE_MACHINE_CORE.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] { BRICK, STONE, BRICK,
                    STONE, Items.WOOD_MACHINE_CORE.stack(), STONE, BRICK, STONE, BRICK })
            .setOutput(Items.STONE_MACHINE_CORE.stack())
            .register();

    // Iron Machine Core
    public static final Recipe IRON_MACHINE_CORE = Recipe.init()
            .setKey(Keys.IRON_MACHINE_CORE.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] { IRON_INGOT, IRON_BLOCK, IRON_INGOT, IRON_BLOCK,
                    Items.STONE_MACHINE_CORE.stack(), IRON_BLOCK, IRON_INGOT, IRON_BLOCK, IRON_INGOT })
            .setOutput(Items.IRON_MACHINE_CORE.stack())
            .register();

    // Diamond Machine Core
    public static final Recipe DIAMOND_MACHINE_CORE = Recipe.init()
            .setKey(Keys.DIAMOND_MACHINE_CORE.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] { DIAMOND, DIAMOND_BLOCK, DIAMOND, DIAMOND_BLOCK,
                    Items.IRON_MACHINE_CORE.stack(), DIAMOND_BLOCK, DIAMOND, DIAMOND_BLOCK, DIAMOND })
            .setOutput(Items.DIAMOND_MACHINE_CORE.stack())
            .register();

    // Enchanted Machine Core
    public static final Recipe ENCHANTED_MACHINE_CORE = Recipe.init()
            .setKey(Keys.ENCHANTED_MACHINE_CORE.key())
            .setRecipeType(RecipeType.MAGIC_WORKBENCH)
            .setInput(new ItemStack[] { UNBREAKING_3_ENCHANTED_BOOK, CRYING_OBSIDIAN, UNBREAKING_3_ENCHANTED_BOOK,
                    CRYING_OBSIDIAN, Items.DIAMOND_MACHINE_CORE.stack(), CRYING_OBSIDIAN,
                    UNBREAKING_3_ENCHANTED_BOOK, CRYING_OBSIDIAN, UNBREAKING_3_ENCHANTED_BOOK })
            .setOutput(Items.ENCHANTED_MACHINE_CORE.stack())
            .register();

    // Energy Storage Component
    public static final Recipe ENERGY_STORAGE_COMPONENT = Recipe.init()
            .setKey(Keys.ENERGY_STORAGE_COMPONENT.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] { SlimefunItems.REDSTONE_ALLOY, GLASS, SlimefunItems.REDSTONE_ALLOY, GLASS,
                    SlimefunItems.BATTERY, GLASS, DynaTechItems.STAINLESS_STEEL_INGOT,
                    Items.IRON_MACHINE_CORE.stack(),
                    DynaTechItems.STAINLESS_STEEL_INGOT })
            .setOutput(Items.ENERGY_STORAGE_COMPONENT.stack())
            .register();

    // Energy Input Component
    public static final Recipe ENERGY_INPUT_COMPONENT = Recipe.init()
            .setKey(Keys.ENERGY_INPUT_COMPONENT.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE).setInput(new ItemStack[] {
                    SlimefunItems.REDSTONE_ALLOY, PINK_CONCRETE, SlimefunItems.REDSTONE_ALLOY,
                    SlimefunItems.COPPER_WIRE, Items.IRON_MACHINE_CORE.stack(), SlimefunItems.COPPER_WIRE,
                    SlimefunItems.REDSTONE_ALLOY, PINK_CONCRETE, SlimefunItems.REDSTONE_ALLOY,
            }).setOutput(Items.ENERGY_INPUT_COMPONENT.stack())
            .register();

    // Energy Output Component
    public static final Recipe ENERGY_OUTPUT_COMPONENT = Recipe.init()
            .setKey(Keys.ENERGY_OUTPUT_COMPONENT.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    SlimefunItems.REDSTONE_ALLOY, CYAN_CONCRETE, SlimefunItems.REDSTONE_ALLOY,
                    SlimefunItems.REDSTONE_ALLOY, CYAN_CONCRETE, SlimefunItems.REDSTONE_ALLOY
            })
            .setOutput(Items.ENERGY_OUTPUT_COMPONENT.stack())
            .register();

    // Degraded Wind Mill
    public static final Recipe DEGRADED_WIND_MILL = Recipe.init()
            .setKey(Keys.DEGRADED_WIND_MILL.key())
            .setRecipeType(DynaTechItems.DT_BLOCK_DROP)
            .setInput(new ItemStack[] { Items.WIND_MILL.stack() })
            .setOutput(Items.DEGRADED_WIND_MILL.stack())
            .register();

    // Degraded Wind Mill 2
    public static final Recipe DEGRADED_WIND_MILL_2 = Recipe.init()
            .setKey(Keys.DEGRADED_WIND_MILL_2.key())
            .setRecipeType(DynaTechItems.DT_BLOCK_DROP)
            .setInput(new ItemStack[] { Items.WIND_MILL_2.stack() })
            .setOutput(Items.DEGRADED_WIND_MILL_2.stack())
            .register();

    // Degraded Hydro Mill
    public static final Recipe DEGRADED_WATER_MILL = Recipe.init()
            .setKey(Keys.DEGRADED_WATER_MILL.key())
            .setRecipeType(DynaTechItems.DT_BLOCK_DROP)
            .setInput(new ItemStack[] { Items.WATER_MILL.stack() })
            .setOutput(Items.DEGRADED_WATER_MILL.stack())
            .register();

    // Degraded Hydro Mill 2
    public static final Recipe DEGRADED_WATER_MILL_2 = Recipe.init()
            .setKey(Keys.DEGRADED_WATER_MILL_2.key())
            .setRecipeType(DynaTechItems.DT_BLOCK_DROP)
            .setInput(new ItemStack[] { Items.WATER_MILL_2.stack() })
            .setOutput(Items.DEGRADED_WATER_MILL_2.stack())
            .register();
    // Degraded Egg Mill
    public static final Recipe DEGRADED_EGG_MILL = Recipe.init()
            .setKey(Keys.DEGRADED_EGG_MILL.key())
            .setRecipeType(DynaTechItems.DT_BLOCK_DROP)
            .setInput(new ItemStack[] { Items.EGG_MILL.stack() })
            .setOutput(Items.DEGRADED_EGG_MILL.stack())
            .register();

    // Degraded Egg Mill 2
    public static final Recipe DEGRADED_EGG_MILL_2 = Recipe.init()
            .setKey(Keys.DEGRADED_EGG_MILL_2.key())
            .setRecipeType(DynaTechItems.DT_BLOCK_DROP)
            .setInput(new ItemStack[] { Items.EGG_MILL_2.stack() })
            .setOutput(Items.DEGRADED_EGG_MILL_2.stack())
            .register();

    // END Mechanical Components

    // Start Generators

    // Water Mill Level 1 (Hydro Generator)
    public static final Recipe WATER_MILL = Recipe.init()
            .setKey(Keys.WATER_MILL.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    DynaTechItems.STAINLESS_STEEL_INGOT, Items.WOOD_MACHINE_CORE.stack(),
                    DynaTechItems.STAINLESS_STEEL_INGOT,
                    DynaTechItems.STAINLESS_STEEL_ROTOR_1, Items.ENERGY_STORAGE_COMPONENT.stack(),
                    DynaTechItems.STAINLESS_STEEL_ROTOR_1,
                    DynaTechItems.STAINLESS_STEEL_INGOT, Items.ENERGY_OUTPUT_COMPONENT.stack(),
                    DynaTechItems.STAINLESS_STEEL_INGOT,
            })
            .setOutput(Items.WATER_MILL.stack())
            .register();

    // Water Mill Repair
    public static final Recipe WATER_MILL_REPAIR = Recipe.init()
            .setKey(Keys.WATER_MILL_REPAIR.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    null, null, null,
                    null, DynaTechItems.STAINLESS_STEEL_INGOT, null,
                    DynaTechItems.STAINLESS_STEEL_ROTOR_1, Items.DEGRADED_WATER_MILL.stack(),
                    DynaTechItems.STAINLESS_STEEL_ROTOR_1,
            })
            .setOutput(Items.WATER_MILL.stack())
            .register();

    // Water Mill Level 2 (Hydro Turbine)
    public static final Recipe WATER_MILL_2 = Recipe.init()
            .setKey(Keys.WATER_MILL_2.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    DynaTechItems.STAINLESS_STEEL_INGOT, Items.STONE_MACHINE_CORE.stack(),
                    DynaTechItems.STAINLESS_STEEL_INGOT,
                    DynaTechItems.STAINLESS_STEEL_ROTOR_1, Items.WATER_MILL.stack(),
                    DynaTechItems.STAINLESS_STEEL_ROTOR_1,
                    DynaTechItems.STAINLESS_STEEL_INGOT, Items.WATER_MILL.stack(),
                    DynaTechItems.STAINLESS_STEEL_INGOT,
            })
            .setOutput(Items.WATER_MILL_2.stack())
            .register();

    // Water Mill Level 2 Repair
    public static final Recipe WATER_MILL_2_REPAIR = Recipe.init()
            .setKey(Keys.WATER_MILL_2_REPAIR.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    null, null, null,
                    null, DynaTechItems.STAINLESS_STEEL_INGOT, null,
                    DynaTechItems.STAINLESS_STEEL_ROTOR_1, Items.DEGRADED_WATER_MILL_2.stack(),
                    DynaTechItems.STAINLESS_STEEL_ROTOR_1,
            })
            .setOutput(Items.WATER_MILL_2.stack())
            .register();

    // Wind Mill Level 1
    public static final Recipe WIND_MILL = Recipe.init()
            .setKey(Keys.WIND_MILL.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    null, DynaTechItems.STAINLESS_STEEL_ROTOR_1, null,
                    DynaTechItems.STAINLESS_STEEL_ROTOR_1, Items.WOOD_MACHINE_CORE.stack(),
                    DynaTechItems.STAINLESS_STEEL_ROTOR_1,
                    Items.ENERGY_STORAGE_COMPONENT.stack(), DynaTechItems.STAINLESS_STEEL_ROTOR_1,
                    Items.ENERGY_OUTPUT_COMPONENT.stack(),
            })
            .setOutput(Items.WIND_MILL.stack())
            .register();

    // Wind Mill Level 1 Repair
    public static final Recipe WIND_MILL_REPAIR = Recipe.init()
            .setKey(Keys.WIND_MILL_REPAIR.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    null, IRON_INGOT, null,
                    IRON_INGOT, Items.DEGRADED_WIND_MILL.stack(), IRON_INGOT,
                    IRON_BLOCK, IRON_INGOT, IRON_BLOCK
            })
            .setOutput(Items.WIND_MILL.stack())
            .register();

    // Wind Mill Level 2
    public static final Recipe WIND_MILL_2 = Recipe.init()
            .setKey(Keys.WIND_MILL_2.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    null, DynaTechItems.STAINLESS_STEEL_ROTOR_1, null,
                    DynaTechItems.STAINLESS_STEEL_ROTOR_1, Items.STONE_MACHINE_CORE.stack(),
                    DynaTechItems.STAINLESS_STEEL_ROTOR_1,
                    Items.WIND_MILL.stack(), DynaTechItems.STAINLESS_STEEL_ROTOR_1,
                    Items.WIND_MILL.stack(),
            })
            .setOutput(Items.WIND_MILL_2.stack())
            .register();

    // Wind Mill Level 2 Repair
    public static final Recipe WIND_MILL_2_REPAIR = Recipe.init()
            .setKey(Keys.WIND_MILL_2_REPAIR.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    null, IRON_INGOT, null,
                    IRON_INGOT, Items.DEGRADED_WIND_MILL_2.stack(), IRON_INGOT,
                    IRON_BLOCK, IRON_INGOT, IRON_BLOCK
            })
            .setOutput(Items.WIND_MILL_2.stack())
            .register();

    // Egg Mill Level 1
    public static final Recipe EGG_MILL = Recipe.init()
            .setKey(Keys.EGG_MILL.key())
            .setRecipeType(RecipeType.MAGIC_WORKBENCH)
            .setInput(new ItemStack[] {
                    SlimefunItems.MAGIC_LUMP_3, END_STONE, SlimefunItems.MAGIC_LUMP_3,
                    CHORUS_FRUIT, Items.WOOD_MACHINE_CORE.stack(), CHORUS_FRUIT,
                    Items.ENERGY_OUTPUT_COMPONENT.stack(), END_STONE, Items.ENERGY_STORAGE_COMPONENT.stack(),
            })
            .setOutput(Items.EGG_MILL.stack())
            .register();

    // Egg Mill Level 1 Repair
    public static final Recipe EGG_MILL_REPAIR = Recipe.init()
            .setKey(Keys.EGG_MILL_REPAIR.key())
            .setRecipeType(RecipeType.MAGIC_WORKBENCH)
            .setInput(new ItemStack[] {
                    SlimefunItems.MAGIC_LUMP_1, END_STONE, SlimefunItems.MAGIC_LUMP_1,
                    CHORUS_FRUIT, Items.DEGRADED_EGG_MILL.stack(), CHORUS_FRUIT,
                    SlimefunItems.MAGIC_LUMP_1, END_STONE, SlimefunItems.MAGIC_LUMP_1,
            })
            .setOutput(Items.EGG_MILL.stack())
            .register();

    // Egg Mill Level 2
    public static final Recipe EGG_MILL_2 = Recipe.init()
            .setKey(Keys.EGG_MILL_2.key())
            .setRecipeType(RecipeType.MAGIC_WORKBENCH)
            .setInput(new ItemStack[] {
                    SlimefunItems.MAGIC_LUMP_3, END_STONE, SlimefunItems.MAGIC_LUMP_3,
                    CHORUS_FRUIT, Items.WOOD_MACHINE_CORE.stack(), CHORUS_FRUIT,
                    Items.EGG_MILL.stack(), END_STONE, Items.EGG_MILL.stack(),
            })
            .setOutput(Items.EGG_MILL_2.stack())
            .register();

    // Egg Mill Level 2 Repair
    public static final Recipe EGG_MILL_2_REPAIR = Recipe.init()
            .setKey(Keys.EGG_MILL_2_REPAIR.key())
            .setRecipeType(RecipeType.MAGIC_WORKBENCH)
            .setInput(new ItemStack[] {
                    SlimefunItems.MAGIC_LUMP_1, END_STONE, SlimefunItems.MAGIC_LUMP_1,
                    CHORUS_FRUIT, Items.DEGRADED_EGG_MILL_2.stack(), CHORUS_FRUIT,
                    SlimefunItems.MAGIC_LUMP_1, END_STONE, SlimefunItems.MAGIC_LUMP_1,
            })
            .setOutput(Items.EGG_MILL_2.stack())
            .register();

    // END Energy Generators

    // START Materials

    // Stainless Steel Ingot
    public static final Recipe STAINLESS_STEEL_INGOT = Recipe.init()
            .setKey(Keys.STAINLESS_STEEL_INGOT.key())
            .setRecipeType(RecipeType.SMELTERY)
            .setInput(new ItemStack[] {
                    SlimefunItems.STEEL_INGOT, SlimefunItems.IRON_DUST, SlimefunItems.ZINC_DUST,
                    SlimefunItems.CARBON,
            })
            .setOutput(DynaTechItems.STAINLESS_STEEL_INGOT)
            .register();

    // Stainless Steel Rotor
    public static final Recipe STAINLESS_STEEL_ROTOR = Recipe.init()
            .setKey(Keys.STAINLESS_STEEL_ROTOR.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    null, DynaTechItems.STAINLESS_STEEL_INGOT, null,
                    DynaTechItems.STAINLESS_STEEL_INGOT, IRON_BLOCK, DynaTechItems.STAINLESS_STEEL_INGOT,
                    null, DynaTechItems.STAINLESS_STEEL_INGOT, null,
            })
            .setOutput(DynaTechItems.STAINLESS_STEEL_ROTOR_1)
            .register();

    // Charcoal to Coal Coke Oven Recipe
    public static final Recipe CHARCOAL_TO_COAL = Recipe.init()
            .setKey(Keys.CHARCOAL_TO_COAL.key())
            .setRecipeType(DynaTechItems.DT_OVENING)
            .setInput(new ItemStack[] { new ItemStack(Material.CHARCOAL) })
            .setOutput(new ItemStack[] { new ItemStack(Material.COAL) })
            .register();

    // Logs to Charcoal Coke Oven Recipe
    // for (Material mat : Tag.LOGS.getValues()) {
    // Recipe.init()
    // .setKey(new NamespacedKey(DynaTech.getInstance(),
    // mat.toString().toLowerCase() + "_to_charcoal"))
    // .setRecipeType(DynaTechItems.DT_OVENING)
    // .setInput(new ItemStack(mat))
    // .setOutput(new ItemStack(Material.CHARCOAL))
    // .register(registry);
    // }
    //
    // Coal To Coal Coke Recipe
    public static final Recipe COAL_TO_COAL_COKE = Recipe.init()
            .setKey(Keys.COAL_TO_COAL_COKE.key())
            .setRecipeType(DynaTechItems.DT_OVENING)
            .setInput(new ItemStack(Material.COAL))
            .setOutput(DynaTechItems.COAL_COKE)
            .register();

    // END Materials
    // START Tools
    public static final Recipe INVENTORY_FILTER = Recipe.init()
            .setKey(Keys.INVENTORY_FILTER.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    STRING, IRON_BARS, STRING,
                    IRON_BARS, Items.IRON_MACHINE_CORE.stack(), IRON_BARS,
                    STRING, IRON_BARS, STRING,

            }).setOutput(DynaTechItems.INV_FILTER)
            .register();

    public static final Recipe RECIPE_BOOK = Recipe.init()
            .setKey(Keys.RECIPE_BOOK.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    null, DynaTechItems.STAINLESS_STEEL_INGOT, null,
                    DynaTechItems.STAINLESS_STEEL_INGOT, BOOK, DynaTechItems.STAINLESS_STEEL_INGOT,
                    null, DynaTechItems.STAINLESS_STEEL_INGOT, null,
            })
            .setOutput(DynaTechItems.RECIPE_BOOK)
            .register();
    public static final Recipe AUTO_OUTPUT_UGPRADE = Recipe.init()
            .setKey(Keys.AUTO_OUTPUT_UPGRADE.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    DynaTechItems.STAINLESS_STEEL_INGOT, SlimefunItems.CARGO_MOTOR,
                    DynaTechItems.STAINLESS_STEEL_INGOT,
                    GLASS, GLASS, GLASS,
                    DynaTechItems.STAINLESS_STEEL_INGOT, SlimefunItems.CARGO_MOTOR,
                    DynaTechItems.STAINLESS_STEEL_INGOT,
            })
            .setOutput(DynaTechItems.AUTO_OUTPUT_UPGRADE)
            .register();

    public static final Recipe AUTO_INPUT_UPGRADE = Recipe.init()
            .setKey(Keys.AUTO_INPUT_UPGRADE.key())
            .setRecipeType(RecipeType.ENHANCED_CRAFTING_TABLE)
            .setInput(new ItemStack[] {
                    DynaTechItems.STAINLESS_STEEL_INGOT, SlimefunItems.CARGO_MOTOR,
                    DynaTechItems.STAINLESS_STEEL_INGOT,
                    new ItemStack(Material.HOPPER), new ItemStack(Material.HOPPER), new ItemStack(Material.HOPPER),
                    DynaTechItems.STAINLESS_STEEL_INGOT, SlimefunItems.CARGO_MOTOR,
                    DynaTechItems.STAINLESS_STEEL_INGOT,
            })
            .setOutput(DynaTechItems.AUTO_INPUT_UPGRADE)
            .register();

    public static final class Keys {

        // START Mechanical Components
        public static final TypedKey<Recipe> WOOD_MACHINE_CORE = TypedKey.create("dynatech", "wood_machine_core");

        public static final TypedKey<Recipe> STONE_MACHINE_CORE = TypedKey.create("dynatech", "stone_machine_core");

        public static final TypedKey<Recipe> IRON_MACHINE_CORE = TypedKey.create("dynatech", "iron_machine_core");

        public static final TypedKey<Recipe> DIAMOND_MACHINE_CORE = TypedKey.create("dynatech", "diamond_machine_core");

        public static final TypedKey<Recipe> ENCHANTED_MACHINE_CORE = TypedKey.create("dynatech",
                "enchanted_machine_core");

        public static final TypedKey<Recipe> ENERGY_STORAGE_COMPONENT = TypedKey.create("dynatech",
                "energy_storage_component");

        public static final TypedKey<Recipe> ENERGY_INPUT_COMPONENT = TypedKey.create("dynatech",
                "energy_input_component");

        public static final TypedKey<Recipe> ENERGY_OUTPUT_COMPONENT = TypedKey.create("dynatech",
                "energy_output_component");

        public static final TypedKey<Recipe> DEGRADED_WATER_MILL = TypedKey.create("dynatech",
                "degraded_water_mill");

        public static final TypedKey<Recipe> DEGRADED_WATER_MILL_2 = TypedKey.create("dynatech",
                "degraded_water_mill_2");

        public static final TypedKey<Recipe> DEGRADED_WIND_MILL = TypedKey.create("dynatech",
                "degraded_wind_mill");

        public static final TypedKey<Recipe> DEGRADED_WIND_MILL_2 = TypedKey.create("dynatech",
                "degraded_wind_mill_2");

        public static final TypedKey<Recipe> DEGRADED_EGG_MILL = TypedKey.create("dynatech",
                "degraded_egg_mill");

        public static final TypedKey<Recipe> DEGRADED_EGG_MILL_2 = TypedKey.create("dynatech",
                "degraded_egg_mill_2");

        // END Mechanical Components
        // START Energy Generators
        public static final TypedKey<Recipe> WATER_MILL = TypedKey.create("dynatech",
                "water_mill");

        public static final TypedKey<Recipe> WATER_MILL_2 = TypedKey.create("dynatech",
                "water_mill_2");

        public static final TypedKey<Recipe> WATER_MILL_REPAIR = TypedKey.create("dynatech",
                "water_mill_repair");

        public static final TypedKey<Recipe> WATER_MILL_2_REPAIR = TypedKey.create("dynatech",
                "water_mill_2_repair");

        public static final TypedKey<Recipe> WIND_MILL = TypedKey.create("dynatech",
                "wind_mill");

        public static final TypedKey<Recipe> WIND_MILL_2 = TypedKey.create("dynatech",
                "wind_mill_2");

        public static final TypedKey<Recipe> WIND_MILL_REPAIR = TypedKey.create("dynatech",
                "wind_mill_repair");

        public static final TypedKey<Recipe> WIND_MILL_2_REPAIR = TypedKey.create("dynatech",
                "wind_mill_2_repair");

        public static final TypedKey<Recipe> EGG_MILL = TypedKey.create("dynatech",
                "egg_mill");

        public static final TypedKey<Recipe> EGG_MILL_2 = TypedKey.create("dynatech",
                "egg_mill_2");

        public static final TypedKey<Recipe> EGG_MILL_REPAIR = TypedKey.create("dynatech",
                "egg_mill_repair");

        public static final TypedKey<Recipe> EGG_MILL_2_REPAIR = TypedKey.create("dynatech",
                "egg_mill_2_repair");

        public static final TypedKey<Recipe> DURABILITY_GENERATOR = TypedKey.create("dynatech",
                "durability_generator");

        public static final TypedKey<Recipe> FOOD_GENERATOR = TypedKey.create("dynatech", "food_generator");

        public static final TypedKey<Recipe> STARDUST_GENERATOR = TypedKey.create("dynatech",
                "stardust_generator");
        // END Energy Generators

        // START Materials
        public static final TypedKey<Recipe> STAINLESS_STEEL_INGOT = TypedKey.create("dynatech",
                "stainless_steel_ingot");

        public static final TypedKey<Recipe> STAINLESS_STEEL_ROTOR = TypedKey.create("dynatech",
                "stainless_steel_rotor");

        public static final TypedKey<Recipe> CHARCOAL_TO_COAL = TypedKey.create("dynatech", "charcoal_to_coal");

        public static final TypedKey<Recipe> COAL_TO_COAL_COKE = TypedKey.create("dynatech", "coal_to_coal_coke");
        // END Materials

        // START Tools
        public static final TypedKey<Recipe> INVENTORY_FILTER = TypedKey.create("dynatech", "inventory_filter");

        public static final TypedKey<Recipe> RECIPE_BOOK = TypedKey.create("dynatech", "recipe_book");

        public static final TypedKey<Recipe> AUTO_OUTPUT_UPGRADE = TypedKey.create("dynatech", "auto_output_upgrade");

        public static final TypedKey<Recipe> AUTO_INPUT_UPGRADE = TypedKey.create("dynatech", "auto_input_upgrade");
        // END Tools
    }
}
