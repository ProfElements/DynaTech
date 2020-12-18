package me.profelements.extrastuff;

import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.skull.SkullItem;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class ExtraStuffItems {

    private ExtraStuffItems() {}

    public static final Category extraStuff = new Category(new NamespacedKey(ExtraStuff.getInstance(),
            "extrastuff"),
            new CustomItem(Material.CONDUIT, "&2Extra Stuff")
    );

    //Materials
    public static final SlimefunItemStack STAINLESS_STEEL = new SlimefunItemStack("STAINLESS_STEEL", Material.IRON_INGOT, "&6Stainless Steel Ingot");
    public static final SlimefunItemStack STAINLESS_STEEL_ROTOR = new SlimefunItemStack("STAINLESS_STEEL_ROTOR", Material.IRON_BLOCK, "&6Stainless Steel Rotor");

    //Backpacks
    public static final SlimefunItemStack PICNIC_BASKET = new SlimefunItemStack("PICNIC_BASKET",
            new CustomItem(SkullItem.fromHash("7a6bf916e28ccb80b4ebfacf98686ad6af7c4fb257e57a8cb78c71d19dccb2")),
            "&6Picnic Basket",
            "",
            "&fAllows you to store food",
            "&fAutomatically consumes them when you're hungry",
            "&fMust be in your inventory",
            "",
            "&fSize: &e27",
            "",
            "&7ID: <ID>",
            "",
            "&eRight Click &7to open."
    );

    //Machines
    public static final SlimefunItemStack AUTO_KITCHEN = new SlimefunItemStack("AUTO_KITCHEN",
            Material.SMOKER,
            "&6Auto Kitchen",
            "",
            "&fAutomatically makes Kitchen recipes",
            "",
            "&f&oSmells like cookies",
            "",
            LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE),
            LoreBuilder.speed(1),
            LoreBuilder.powerPerSecond(16)
    );

    public static final SlimefunItemStack GROWTH_CHAMBER = new SlimefunItemStack("GROWTH_CHAMBER",
            Material.GREEN_STAINED_GLASS,
            "&6Growth Chamber",
            "",
            "&fAutomatically grows some plants.",
            "",
            "&f&oIts like a small greenhouse!",
            "",
            LoreBuilder.machine(MachineTier.MEDIUM, MachineType.MACHINE),
            LoreBuilder.speed(1),
            LoreBuilder.powerPerSecond(32)
            );


    public static final SlimefunItemStack WATER_MILL = new SlimefunItemStack("WATER_MILL",
            Material.COBBLESTONE_WALL,
            "&6Hydro Generator",
            "",
            "&fCreates energy from flowing water",
            "",
            LoreBuilder.machine(MachineTier.MEDIUM, MachineType.GENERATOR),
            LoreBuilder.powerBuffer(128),
            LoreBuilder.powerPerSecond(32)
    );

    public static final SlimefunItemStack DRAGON_GENERATOR = new SlimefunItemStack("DRAGON_GENERATOR",
            Material.GRAY_CONCRETE
            ,"&6Dragon Egg Generator",
            "",
            "&fCreates energy from the warmth of a Dragon Egg",
            "",
            LoreBuilder.machine(MachineTier.MEDIUM, MachineType.GENERATOR),
            LoreBuilder.powerBuffer(512),
            LoreBuilder.powerPerSecond(32)
    );



}