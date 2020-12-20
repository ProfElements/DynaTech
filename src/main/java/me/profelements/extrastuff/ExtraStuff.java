package me.profelements.extrastuff;

import me.profelements.extrastuff.items.backpacks.PicnicBasket;
import me.profelements.extrastuff.listeners.PicnicBasketListener;
import me.profelements.extrastuff.setup.ExtraStuffItemsSetup;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;

import javax.annotation.Nonnull;

public class ExtraStuff extends JavaPlugin implements SlimefunAddon {



    private static ExtraStuff instance;

    @Override
    public void onEnable() {

        instance = this;
        Config cfg = new Config(this);
        final Metrics metrics = new Metrics(this, 9689);

        if (cfg.getBoolean("options.auto-update")) {}

        ExtraStuffItemsSetup.setup(this);
        new PicnicBasketListener(this, (PicnicBasket) ExtraStuffItems.PICNIC_BASKET.getItem());

    }

    @Override
    public void onDisable() {
        instance = null;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/ProfElements/ExtraStuff/issues";
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Nonnull
    public static ExtraStuff getInstance() {
        return instance;
    }

    public static boolean isIsExoticGardenInstalled() {
        return Bukkit.getServer().getPluginManager().isPluginEnabled("ExoticGarden");
    }
}
