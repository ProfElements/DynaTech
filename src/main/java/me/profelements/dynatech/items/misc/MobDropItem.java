package me.profelements.dynatech.items.misc;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RandomMobDrop;
import org.bukkit.inventory.ItemStack;

public class MobDropItem extends SlimefunItem implements RandomMobDrop {

    private final ItemSetting<Boolean> dropSetting = new ItemSetting<>(this, "drop-from-mob", true);
    private int dropChance = 0;

    public MobDropItem(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, int dropChance) {
        super(itemGroup, item, recipeType, recipe);
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
