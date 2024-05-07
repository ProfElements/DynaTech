package me.profelements.dynatech.items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.profelements.dynatech.utils.ChestMenuUtils;
import me.profelements.dynatech.utils.Recipe;

public class RecipeBook extends SlimefunItem {

    public RecipeBook(ItemGroup itemGroup, SlimefunItemStack stack, Recipe recipe) {
        super(itemGroup, stack, recipe.getRecipeType(), recipe.getInput());

        addItemHandler(onRightClick());
    }

    private final ItemUseHandler onRightClick() {
        return new ItemUseHandler() {

            @Override
            public void onRightClick(PlayerRightClickEvent e) {
                ChestMenuUtils.openRecipeBook(e.getPlayer());
            }

        };
    }
}
