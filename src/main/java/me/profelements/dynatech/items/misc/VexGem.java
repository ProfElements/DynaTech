package me.profelements.dynatech.items.misc;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.RandomMobDrop;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class VexGem extends SlimefunItem implements NotPlaceable, RandomMobDrop {

    private final ItemSetting<Boolean> dropSetting = new ItemSetting<>(this, " drop-from-vexs", true);
    private final ItemSetting<Integer> chance = new IntRangeSetting(this ,"vex-drop-chance", 0, 10, 100);

    public VexGem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemSetting(dropSetting);
        addItemSetting(chance);

        addItemHandler(getItemHandler());
    }

    @Override
    public int getMobDropChance() {
        return chance.getValue();
    }

    public boolean isDroppedFromVexs() {
        return dropSetting.getValue();
    }

    public ItemUseHandler getItemHandler() {
        return PlayerRightClickEvent::cancel;
    }

}
    