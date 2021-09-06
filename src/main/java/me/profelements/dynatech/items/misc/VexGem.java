package me.profelements.dynatech.items.misc;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.settings.IntRangeSetting;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.RandomMobDrop;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.inventory.ItemStack;

public class VexGem extends SlimefunItem implements NotPlaceable, RandomMobDrop {

    private final ItemSetting<Boolean> dropSetting = new ItemSetting<>(this, " drop-from-vexs", true);
    private final ItemSetting<Integer> chance = new IntRangeSetting(this ,"vex-drop-chance", 0, 10, 100);

    public VexGem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

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
