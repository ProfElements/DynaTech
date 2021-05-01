package me.profelements.dynatech;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;

import io.github.mooy1.infinitylib.AbstractAddon;
import io.github.mooy1.infinitylib.bstats.bukkit.Metrics;
import io.github.mooy1.infinitylib.commands.AbstractCommand;
import me.profelements.dynatech.items.backpacks.PicnicBasket;
import me.profelements.dynatech.items.misc.DimensionalHomeDimension;
import me.profelements.dynatech.items.tools.ElectricalStimulator;
import me.profelements.dynatech.items.tools.InventoryFilter;
import me.profelements.dynatech.listeners.ElectricalStimulatorListener;
import me.profelements.dynatech.listeners.InventoryFilterListener;
import me.profelements.dynatech.listeners.PicnicBasketListener;
import me.profelements.dynatech.setup.DynaTechItemsSetup;
import me.profelements.dynatech.tasks.ItemBandTask;

public final class DynaTech extends AbstractAddon {

    private static DynaTech instance;
    private static boolean exoticGardenInstalled;
    private static boolean infinityExpansionInstalled;
    
    @Override
    public void onEnable() {
        instance = this;
        super.onEnable();
        
        exoticGardenInstalled = getServer().getPluginManager().isPluginEnabled("ExoticGarden");
        infinityExpansionInstalled = getServer().getPluginManager().isPluginEnabled("InfinityExpansion");

        if (!getConfig().getBoolean("options.disable-dimensionalhome-world")) {
            new WorldCreator("dimensionalhome").generator(new DimensionalHomeDimension()).createWorld();
        }

        DynaTechItemsSetup.setup(this);
        new PicnicBasketListener(this, (PicnicBasket) DynaTechItems.PICNIC_BASKET.getItem());
        new ElectricalStimulatorListener(this, (ElectricalStimulator) DynaTechItems.ELECTRICAL_STIMULATOR.getItem());
        new InventoryFilterListener(this, (InventoryFilter) DynaTechItems.INVENTORY_FILTER.getItem());

        //Tasks
        scheduleRepeatingAsync(new ItemBandTask(), 0L, 5 * 20L);

        if (System.getProperty("java.version").startsWith("1.8")) {
            log(Level.WARNING, "           DynaTech will be switching to JAVA 11        ");
            log(Level.WARNING, "                Please Update to JAVA 11                ");
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);

        instance = null;
    }

    @Nonnull
    public static DynaTech inst() {
        return instance;
    }

    @Nonnull
    @Override
    protected Metrics setupMetrics() {
        return new Metrics(this, 9689);
    }

    @Nonnull
    @Override
    protected String getGithubPath() {
        return "ProfElements/DynaTech/master";
    }

    @Nonnull
    @Override
    protected List<AbstractCommand> getSubCommands() {
        return new ArrayList<>();
    }

    @Nonnull
    @Override
    protected String getAutoUpdatePath() {
        return "options.auto-update";
    }

    public static boolean isExoticGardenInstalled() {
        return exoticGardenInstalled;
    }

    public static boolean isInfinityExpansionInstalled() {
        return infinityExpansionInstalled;
    }
    
}
