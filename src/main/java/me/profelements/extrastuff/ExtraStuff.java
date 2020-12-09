package me.profelements.extrastuff;

import me.profelements.extrastuff.items.backpacks.PicnicBasket;
import me.profelements.extrastuff.listeners.PicnicBasketListener;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.mrCookieSlime.Slimefun.cscorelib2.config.Config;

import javax.annotation.Nonnull;
import java.util.HashSet;
import java.util.Set;

public class ExtraStuff extends JavaPlugin implements SlimefunAddon {

    private static ExtraStuff instance;

    @Override
    public void onEnable() {
        Set<Material> testSet = new HashSet<>();

        instance = this;
        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {}

        ExtraStuffItemsSetup.setup(this);


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
}
