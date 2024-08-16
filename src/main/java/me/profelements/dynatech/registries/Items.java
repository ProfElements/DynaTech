package me.profelements.dynatech.registries;

import me.profelements.dynatech.DynaTechItems;
import me.profelements.dynatech.utils.ItemWrapper;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;

import dev.j3fftw.extrautils.utils.LoreBuilderDynamic;

public class Items {

    // START Mechanical Components
    public static final ItemWrapper WOOD_MACHINE_CORE = ItemWrapper.create(Keys.WOOD_MACHINE_CORE,
            new SlimefunItemStack(
                    Items.Keys.WOOD_MACHINE_CORE.asSlimefunId(),
                    Material.MANGROVE_WOOD, "&fWood Machine Core"));

    public static final ItemWrapper STONE_MACHINE_CORE = ItemWrapper.create(Keys.STONE_MACHINE_CORE,
            new SlimefunItemStack(
                    Items.Keys.STONE_MACHINE_CORE.asSlimefunId(),
                    Material.SMOOTH_STONE, "&fStone Machine Core"));

    public static final ItemWrapper IRON_MACHINE_CORE = ItemWrapper.create(Keys.IRON_MACHINE_CORE,
            new SlimefunItemStack(
                    Items.Keys.IRON_MACHINE_CORE.asSlimefunId(),
                    Material.GRAY_CONCRETE, "&fIron Machine Core"));

    public static final ItemWrapper DIAMOND_MACHINE_CORE = ItemWrapper.create(Keys.DIAMOND_MACHINE_CORE,
            new SlimefunItemStack(
                    Items.Keys.DIAMOND_MACHINE_CORE.asSlimefunId(),
                    Material.LIGHT_BLUE_CONCRETE, "&fDiamond Machine Core"));

    public static final ItemWrapper ENCHANTED_MACHINE_CORE = ItemWrapper.create(Keys.ENCHANTED_MACHINE_CORE,
            new SlimefunItemStack(
                    Items.Keys.ENCHANTED_MACHINE_CORE.asSlimefunId(),
                    Material.RESPAWN_ANCHOR, "&bEnchanted Machine Core"));

    public static final ItemWrapper ENERGY_STORAGE_COMPONENT = ItemWrapper.create(Keys.ENERGY_STORAGE_COMPONENT,
            new SlimefunItemStack(
                    Items.Keys.ENERGY_STORAGE_COMPONENT.asSlimefunId(), Material.REDSTONE_LAMP,
                    "&cEnergy Storage Component"));

    public static final ItemWrapper ENERGY_INPUT_COMPONENT = ItemWrapper.create(Keys.ENERGY_INPUT_COMPONENT,
            new SlimefunItemStack(
                    Items.Keys.ENERGY_INPUT_COMPONENT.asSlimefunId(),
                    Material.COMPARATOR, "&cEnergy Input Component"));

    public static final ItemWrapper ENERGY_OUTPUT_COMPONENT = ItemWrapper.create(Keys.ENERGY_OUTPUT_COMPONENT,
            new SlimefunItemStack(
                    Items.Keys.ENERGY_OUTPUT_COMPONENT.asSlimefunId(),
                    Material.REPEATER, "&cEnergy Output Component"));

    public static final ItemWrapper DEGRADED_WATER_MILL = ItemWrapper.create(Keys.DEGRADED_WATER_MILL,
            new SlimefunItemStack(
                    Items.Keys.DEGRADED_WATER_MILL.asSlimefunId(),
                    Material.COBBLESTONE_WALL, "&cDegraded Water Mill"));

    public static final ItemWrapper DEGRADED_WATER_MILL_2 = ItemWrapper.create(Keys.DEGRADED_WATER_MILL_2,
            new SlimefunItemStack(
                    Items.Keys.DEGRADED_WATER_MILL_2.asSlimefunId(),
                    Material.PRISMARINE_WALL, "&cDegraded Hydro Turbine"));

    public static final ItemWrapper DEGRADED_WIND_MILL = ItemWrapper.create(Keys.DEGRADED_WIND_MILL,
            new SlimefunItemStack(
                    Items.Keys.DEGRADED_WIND_MILL.asSlimefunId(),
                    Material.LIGHT_GRAY_GLAZED_TERRACOTTA, "&cDegraded Wind Mill"));

    public static final ItemWrapper DEGRADED_WIND_MILL_2 = ItemWrapper.create(Keys.DEGRADED_WIND_MILL_2,
            new SlimefunItemStack(
                    Items.Keys.DEGRADED_WIND_MILL_2.asSlimefunId(),
                    Material.GRAY_GLAZED_TERRACOTTA, "&cDegraded Wind Turbine"));

    public static final ItemWrapper DEGRADED_EGG_MILL = ItemWrapper.create(Keys.DEGRADED_WIND_MILL,
            new SlimefunItemStack(
                    Items.Keys.DEGRADED_EGG_MILL.asSlimefunId(),
                    Material.CRYING_OBSIDIAN, "&cDegraded Dragon Egg Mill"));

    public static final ItemWrapper DEGRADED_EGG_MILL_2 = ItemWrapper.create(Keys.DEGRADED_WIND_MILL_2,
            new SlimefunItemStack(
                    Items.Keys.DEGRADED_EGG_MILL_2.asSlimefunId(),
                    Material.RESPAWN_ANCHOR, "&cDegraded Dragon Egg Turbine"));
    // END Mechanical Components

    // START Energy Generators
    public static final ItemWrapper WATER_MILL = ItemWrapper.create(Keys.WATER_MILL,
            new SlimefunItemStack(Items.Keys.WATER_MILL.asSlimefunId(),
                    Material.COBBLESTONE_WALL,
                    "&bWater Mill",
                    "",
                    "&7Degrades over time.",
                    "",

                    LoreBuilder.machine(MachineTier.BASIC, MachineType.GENERATOR),
                    LoreBuilderDynamic.powerPerTick(16),
                    LoreBuilder.powerBuffer(64)));

    public static final ItemWrapper WATER_MILL_2 = ItemWrapper.create(Keys.WATER_MILL_2,
            new SlimefunItemStack(Items.Keys.WATER_MILL_2.asSlimefunId(),
                    Material.PRISMARINE_WALL,
                    "&bHydro Turbine",
                    "",
                    "&7Degrades over time.",
                    "",
                    LoreBuilder.machine(MachineTier.MEDIUM, MachineType.GENERATOR),
                    LoreBuilderDynamic.powerPerTick(64),
                    LoreBuilder.powerBuffer(256)));

    public static final ItemWrapper WIND_MILL = ItemWrapper.create(Keys.WIND_MILL,
            new SlimefunItemStack(Items.Keys.WIND_MILL.asSlimefunId(),
                    Material.LIGHT_GRAY_GLAZED_TERRACOTTA,
                    "&bWind Mill",
                    "",
                    "&7Degrades over time.",
                    "",
                    LoreBuilder.machine(MachineTier.BASIC, MachineType.GENERATOR),
                    "&fMinimum Energy Generation Rate: &b16j/tick",
                    "&fMaximum Energy Generation Rate: &b64j/tick",
                    LoreBuilder.powerBuffer(256)));

    public static final ItemWrapper WIND_MILL_2 = ItemWrapper.create(Keys.WIND_MILL_2,
            new SlimefunItemStack(Items.Keys.WIND_MILL_2.asSlimefunId(),
                    Material.GRAY_GLAZED_TERRACOTTA,
                    "&bWind Turbine",
                    "",
                    "&7Degrades over time.",
                    "",
                    LoreBuilder.machine(MachineTier.MEDIUM, MachineType.GENERATOR),
                    "&fMinimum Energy Generation Rate: &b64j/tick",
                    "&fMaximum Energy Generation Rate: &b256j/tick",
                    LoreBuilder.powerBuffer(1024)));

    public static final ItemWrapper EGG_MILL = ItemWrapper.create(Keys.EGG_MILL,
            new SlimefunItemStack(Items.Keys.EGG_MILL.asSlimefunId(),
                    Material.CRYING_OBSIDIAN,
                    "&bDragon Egg Mill",
                    "",
                    "&7Degrades over time.",
                    "",
                    LoreBuilder.machine(MachineTier.BASIC, MachineType.GENERATOR),
                    LoreBuilderDynamic.powerPerTick(16),
                    LoreBuilder.powerBuffer(64)));

    public static final ItemWrapper EGG_MILL_2 = ItemWrapper.create(Keys.EGG_MILL_2,
            new SlimefunItemStack(Items.Keys.EGG_MILL_2.asSlimefunId(),
                    Material.RESPAWN_ANCHOR,
                    "&bDragon Egg Turbine",
                    "",
                    "&7Degrades over time.",
                    "",
                    LoreBuilder.machine(MachineTier.MEDIUM, MachineType.GENERATOR),
                    LoreBuilderDynamic.powerPerTick(64),
                    LoreBuilder.powerBuffer(256)));

    public static final ItemWrapper DURABILITY_GENERATOR = ItemWrapper.create(Keys.DURABILITY_GENERATOR,
            new SlimefunItemStack(
                    Items.Keys.DURABILITY_GENERATOR.asSlimefunId(),
                    Material.SPRUCE_WOOD,
                    "&bChipping Generator",
                    "",
                    "&7Exchanges durability for power.",
                    "",
                    LoreBuilder.machine(MachineTier.MEDIUM, MachineType.GENERATOR),
                    LoreBuilder.powerBuffer(256),
                    LoreBuilderDynamic.power(8, " per durability point")));

    public static final ItemWrapper FOOD_GENERATOR = ItemWrapper.create(Keys.FOOD_GENERATOR,
            new SlimefunItemStack(
                    Items.Keys.FOOD_GENERATOR.asSlimefunId(),
                    Material.BLAST_FURNACE,
                    "&bCulinary Generator",
                    "",
                    "&7Exchanges hunger for power",
                    "",
                    LoreBuilder.machine(MachineTier.MEDIUM, MachineType.GENERATOR),
                    LoreBuilder.powerBuffer(256),
                    LoreBuilderDynamic.powerPerSecond(8)));

    public static final ItemWrapper STARDUST_GENERATOR = ItemWrapper.create(Keys.STARDUST_GENERATOR,
            new SlimefunItemStack(
                    Items.Keys.STARDUST_GENERATOR.asSlimefunId(),
                    Material.IRON_BLOCK,
                    "&bStardust Reactor",
                    "",
                    "&7Exchanges Star Dust for a large amount of power.",
                    "",
                    LoreBuilder.machine(MachineTier.END_GAME, MachineType.GENERATOR),
                    LoreBuilder.powerBuffer(32768),
                    LoreBuilderDynamic.powerPerSecond(1024)));

    // END Energy Generators
    public static final class Keys {
        // START Mechanical Components
        public static final TypedKey<ItemWrapper> WOOD_MACHINE_CORE = TypedKey.create("dynatech", "wood_machine_core");

        public static final TypedKey<ItemWrapper> STONE_MACHINE_CORE = TypedKey.create("dynatech",
                "stone_machine_core");

        public static final TypedKey<ItemWrapper> IRON_MACHINE_CORE = TypedKey.create("dynatech", "iron_machine_core");

        public static final TypedKey<ItemWrapper> DIAMOND_MACHINE_CORE = TypedKey.create("dynatech",
                "diamond_machine_core");

        public static final TypedKey<ItemWrapper> ENCHANTED_MACHINE_CORE = TypedKey.create("dynatech",
                "enchanted_machine_core");

        public static final TypedKey<ItemWrapper> ENERGY_STORAGE_COMPONENT = TypedKey.create("dynatech",
                "energy_storage_component");

        public static final TypedKey<ItemWrapper> ENERGY_INPUT_COMPONENT = TypedKey.create("dynatech",
                "energy_input_component");

        public static final TypedKey<ItemWrapper> ENERGY_OUTPUT_COMPONENT = TypedKey.create("dynatech",
                "energy_output_component");

        public static final TypedKey<ItemWrapper> DEGRADED_WATER_MILL = TypedKey.create("dynatech",
                "degraded_water_mill");

        public static final TypedKey<ItemWrapper> DEGRADED_WATER_MILL_2 = TypedKey.create("dynatech",
                "degraded_water_mill_2");

        public static final TypedKey<ItemWrapper> DEGRADED_WIND_MILL = TypedKey.create("dynatech",
                "degraded_wind_mill");

        public static final TypedKey<ItemWrapper> DEGRADED_WIND_MILL_2 = TypedKey.create("dynatech",
                "degraded_wind_mill_2");

        public static final TypedKey<ItemWrapper> DEGRADED_EGG_MILL = TypedKey.create("dynatech",
                "degraded_egg_mill");

        public static final TypedKey<ItemWrapper> DEGRADED_EGG_MILL_2 = TypedKey.create("dynatech",
                "degraded_egg_mill_2");

        // END Mechanical Components
        // START Energy Generators
        public static final TypedKey<ItemWrapper> WATER_MILL = TypedKey.create("dynatech",
                "water_mill");

        public static final TypedKey<ItemWrapper> WATER_MILL_2 = TypedKey.create("dynatech",
                "water_mill_2");

        public static final TypedKey<ItemWrapper> WIND_MILL = TypedKey.create("dynatech",
                "wind_mill");

        public static final TypedKey<ItemWrapper> WIND_MILL_2 = TypedKey.create("dynatech",
                "wind_mill_2");

        public static final TypedKey<ItemWrapper> EGG_MILL = TypedKey.create("dynatech",
                "egg_mill");

        public static final TypedKey<ItemWrapper> EGG_MILL_2 = TypedKey.create("dynatech",
                "egg_mill_2");

        public static final TypedKey<ItemWrapper> DURABILITY_GENERATOR = TypedKey.create("dynatech",
                "durability_generator");

        public static final TypedKey<ItemWrapper> FOOD_GENERATOR = TypedKey.create("dynatech", "food_generator");

        public static final TypedKey<ItemWrapper> STARDUST_GENERATOR = TypedKey.create("dynatech",
                "stardust_generator");
        // END Energy Generators

    }
}
