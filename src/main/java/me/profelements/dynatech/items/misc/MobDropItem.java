package me.profelements.dynatech.items.misc;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.core.attributes.RandomMobDrop;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class MobDropItem extends SlimefunItem implements RandomMobDrop {

    private final ItemSetting<Boolean> dropSetting = new ItemSetting<>(this, "drop-from-mob", true);
    private int dropChance = 0;

    public MobDropItem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int dropChance) {
        super(category, item, recipeType, recipe);
        this.dropChance = dropChance;

        addItemSetting(dropSetting);
    }

    public boolean isDroppedFromMob() {
        return dropSetting.getValue();
    }

    @Override
    public int getMobDropChance() {
        return dropChance;
    }
    
}
