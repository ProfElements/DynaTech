package me.profelements.dynatech;

import org.apache.commons.lang.Validate;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import io.github.mooy1.infinitylib.PluginUtils;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;
import me.profelements.dynatech.items.backpacks.PicnicBasket;
import me.profelements.dynatech.items.misc.DimensionalHomeDimension;
import me.profelements.dynatech.items.tools.ElectricalStimulator;
import me.profelements.dynatech.items.tools.InventoryFilter;
import me.profelements.dynatech.listeners.ElectricalStimulatorListener;
import me.profelements.dynatech.listeners.InventoryFilterListener;
import me.profelements.dynatech.listeners.PicnicBasketListener;
import me.profelements.dynatech.setup.DynaTechItemsSetup;
import me.profelements.dynatech.tasks.ItemBandTask;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DynaTech extends JavaPlugin implements SlimefunAddon {

    private static DynaTech instance;
    private static boolean exoticGardenInstalled;
    private static boolean infinityExpansionInstalled;
    
    @Override
    public void onEnable() {
        instance = this;
        exoticGardenInstalled = Bukkit.getServer().getPluginManager().isPluginEnabled("ExoticGarden");
        infinityExpansionInstalled = Bukkit.getServer().getPluginManager().isPluginEnabled("InfinityExpansion");

        saveDefaultConfig();
        
        Config cfg = new Config(this);
        
        final Metrics metrics = new Metrics(this, 9689);

        if (!cfg.getBoolean("options.disable-dimensionalhome-world")) {
            WorldCreator worldCreator = new WorldCreator("dimensionalhome");
            worldCreator.generator(new DimensionalHomeDimension());
            worldCreator.createWorld();
        }

        DynaTechItemsSetup.setup(this);
        new PicnicBasketListener(this, (PicnicBasket) DynaTechItems.PICNIC_BASKET.getItem());
        new ElectricalStimulatorListener(this, (ElectricalStimulator) DynaTechItems.ELECTRICAL_STIMULATOR.getItem());
        new InventoryFilterListener(this, (InventoryFilter) DynaTechItems.INVENTORY_FILTER.getItem());

        //Tasks
        getServer().getScheduler().runTaskTimerAsynchronously(DynaTech.getInstance(), new ItemBandTask(), 0L, 5 * 20L);

        PluginUtils.setup("DynaTech", this, "ProfElements/DynaTech/master", getFile());
        PluginUtils.startTicker(() -> {});
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);

        instance = null;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/ProfElements/ExtraStuff/issues";
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

    public static boolean isExoticGardenInstalled() {
        return exoticGardenInstalled;
    }

    public static boolean isInfinityExpansionInstalled() {
        return infinityExpansionInstalled;
    }

    @Nullable
    public static BukkitTask runSync(@Nonnull Runnable runnable) {
        Validate.notNull(runnable, "Cannot run null");

        if (instance == null || !instance.isEnabled()) {
            return null;
        }

        return instance.getServer().getScheduler().runTask(getInstance(), runnable);
    }
    
}
