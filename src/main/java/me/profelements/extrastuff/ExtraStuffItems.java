package me.profelements.extrastuff;

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
}
