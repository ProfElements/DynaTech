package me.profelements.extrastuff;

import me.profelements.extrastuff.items.backpacks.PicnicBasket;
import me.profelements.extrastuff.listeners.PicnicBasketListener;
import me.profelements.extrastuff.setup.ExtraStuffItemsSetup;
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
        Config cfg = new Config(getInstance());

        if (cfg.getBoolean("options.auto-update")) {}

        ExtraStuffItemsSetup.setup(getInstance());
        new PicnicBasketListener(this, (PicnicBasket) ExtraStuffItems.PICNIC_BASKET.getItem());

    }

    @Override
    public void onDisable() {
    }

    @Override
    public String getBugTrackerURL() {
        return null;
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
