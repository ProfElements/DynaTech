package me.profelements.dynatech.registries;

import org.bukkit.Material;

import io.github.bakedlibs.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;

public class ItemGroups {

    public static final void init(Registry<ItemGroup> registry) {
        registry.register(Keys.GENERAL, GENERAL);
        registry.register(Keys.RESOURCES, RESOURCES);
        registry.register(Keys.TOOLS, TOOLS);
        registry.register(Keys.MACHINES, MACHINES);
        registry.register(Keys.GENERATORS, GENERATORS);
        registry.register(Keys.EXPERIMENTAL, EXPERIMENTAL);
        registry.register(Keys.APIARIES, HIVES);
    }

    public static final NestedItemGroup GENERAL = new NestedItemGroup(
            Keys.GENERAL.key(),
            new CustomItemStack(Material.CONDUIT, "&bDynaTech"));

    public static final SubItemGroup RESOURCES = new SubItemGroup(
            Keys.RESOURCES.key(), GENERAL,
            new CustomItemStack(Material.PUFFERFISH, "&bDynaTech Resources"));

    public static final SubItemGroup TOOLS = new SubItemGroup(Keys.TOOLS.key(),
            GENERAL, new CustomItemStack(Material.DIAMOND_AXE, "&bDynaTech Tools"));

    public static final SubItemGroup MACHINES = new SubItemGroup(Keys.MACHINES.key(), GENERAL,
            new CustomItemStack(Material.SEA_LANTERN, "&bDynaTech Machines"));

    public static final SubItemGroup GENERATORS = new SubItemGroup(Keys.GENERATORS.key(), GENERAL,
            new CustomItemStack(Material.PRISMARINE_BRICKS, "&bDynaTech Generators"));

    public static final SubItemGroup EXPERIMENTAL = new SubItemGroup(Keys.EXPERIMENTAL.key(), GENERAL,
            new CustomItemStack(Material.REDSTONE_LAMP, "&fDynaTech Experimental"));

    public static final SubItemGroup HIVES = new SubItemGroup(Keys.APIARIES.key(),
            GENERAL, new CustomItemStack(Material.BEEHIVE, "&bDynaTech Apiaries"));

    public static final class Keys {
        public static final TypedKey<ItemGroup> GENERAL = TypedKey.create("dynatech", "general");
        public static final TypedKey<ItemGroup> RESOURCES = TypedKey.create("dynatech", "resources");
        public static final TypedKey<ItemGroup> TOOLS = TypedKey.create("dynatech", "tools");
        public static final TypedKey<ItemGroup> MACHINES = TypedKey.create("dynatech", "machines");
        public static final TypedKey<ItemGroup> GENERATORS = TypedKey.create("dynatech", "generators");
        public static final TypedKey<ItemGroup> EXPERIMENTAL = TypedKey.create("dynatech", "experimental");
        public static final TypedKey<ItemGroup> APIARIES = TypedKey.create("dynatech", "apiaries");
    }
}
