package me.profelements.dynatech;

import io.github.bakedlibs.dough.updater.BlobBuildUpdater;
import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import me.profelements.dynatech.items.backpacks.PicnicBasket;
import me.profelements.dynatech.items.misc.DimensionalHomeDimension;
import me.profelements.dynatech.items.tools.ElectricalStimulator;
import me.profelements.dynatech.listeners.ElectricalStimulatorListener;
import me.profelements.dynatech.listeners.ExoticGardenIntegrationListener;
import me.profelements.dynatech.listeners.GastronomiconIntegrationListener;
import me.profelements.dynatech.listeners.InventoryFilterListener;
import me.profelements.dynatech.listeners.PicnicBasketListener;
import me.profelements.dynatech.setup.DynaTechItemsSetup;
import me.profelements.dynatech.tasks.ItemBandTask;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Preconditions;


public class DynaTech extends JavaPlugin implements SlimefunAddon {

    private static DynaTech instance;
    private static boolean exoticGardenInstalled;
    private static boolean infinityExpansionInstalled;

    private int tickInterval;

    @Override
    public void onEnable() {
        setInstance(this);
        setExoticGardenInstalled(Bukkit.getPluginManager().isPluginEnabled("ExoticGarden"));
        setInfinityExpansionInstalled(Bukkit.getPluginManager().isPluginEnabled("InfinityExpansion"));

        final int TICK_TIME = Slimefun.getTickerTask().getTickRate();

        saveDefaultConfig();

        new Metrics(this, 9689);

        if (!getConfig().getBoolean("options.disable-dimensionalhome-world")) {
            WorldCreator worldCreator = new WorldCreator("dimensionalhome");
            worldCreator.generator(new DimensionalHomeDimension());
            worldCreator.createWorld();
        }

        DynaTechItemsSetup.setup(this);
        new PicnicBasketListener(this, (PicnicBasket) DynaTechItems.PICNIC_BASKET.getItem());
        new ElectricalStimulatorListener(this, (ElectricalStimulator) DynaTechItems.ELECTRICAL_STIMULATOR.getItem());
        new InventoryFilterListener(this);

        try {
            Class.forName("io.github.schntgaispock.gastronomicon.api.items.FoodItemStack");
            new GastronomiconIntegrationListener(this);
        } catch (ClassNotFoundException ex) {
            // ignored
        }


        try {
            Class.forName("io.github.thebusybiscuit.exoticgarden.items.CustomFood");
            new ExoticGardenIntegrationListener(this);
        } catch (ClassNotFoundException ex) {
            // ignored
        }


        //Tasks
        getServer().getScheduler().runTaskTimerAsynchronously(DynaTech.getInstance(), new ItemBandTask(), 0L, 5 * 20L);
        getServer().getScheduler().runTaskTimer(DynaTech.getInstance(), () -> this.tickInterval++, 0, TICK_TIME);

        if (getConfig().getBoolean("options.auto-update", true) && getDescription().getVersion().startsWith("Main")) {
            new BlobBuildUpdater(this, getFile(), "DynaTech", "Main").start();
        }

        if (!Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_19)) {
                getLogger().warning("DynaTech only support 1.19+, disabling.");
                getServer().getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);

        setInstance(null);
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/ProfElements/DynaTech/issues";
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nonnull
    public static DynaTech getInstance() {
        return instance;
    }

    public int getTickInterval() {
        return tickInterval;
    }

    public static boolean isExoticGardenInstalled() {
        return exoticGardenInstalled;
    }

    public static boolean isInfinityExpansionInstalled() {
        return infinityExpansionInstalled;
    }

    public static void setInstance(DynaTech inst) {
        instance = inst;
    }

    public static void setExoticGardenInstalled(boolean isExoticGardenInstalled) {
        exoticGardenInstalled = isExoticGardenInstalled;
    }

    public static void setInfinityExpansionInstalled(boolean isInfinityExpansionInstalled) {
        infinityExpansionInstalled = isInfinityExpansionInstalled;
    }

    @Nullable
    public static BukkitTask runSync(@Nonnull Runnable runnable) {
        Preconditions.checkNotNull(runnable, "Cannot run null");

        if (instance == null || !instance.isEnabled()) {
            return null;
        }

        return instance.getServer().getScheduler().runTask(getInstance(), runnable);
    }

}
