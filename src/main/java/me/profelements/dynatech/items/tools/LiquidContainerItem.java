package me.profelements.dynatech.items.tools;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.profelements.dynatech.interfaces.LiquidContainer;
import me.profelements.dynatech.utils.Liquid;

public class LiquidContainerItem extends SlimefunItem implements LiquidContainer {

    private final Liquid liquid;
    private final int liquidCapacity;

    public LiquidContainerItem(ItemGroup group, SlimefunItemStack stack, RecipeType recipeType, ItemStack[] input,
            Liquid liquid, int liquidCapacity) {
        super(group, stack, recipeType, input);

        this.liquid = liquid;
        this.liquidCapacity = liquidCapacity;

        addItemHandler(onItemUse());
    }

    private ItemUseHandler onItemUse() {
        return new ItemUseHandler() {
            @Override
            public void onRightClick(PlayerRightClickEvent event) {
                event.cancel();
            }

        };
    }

    @Override
    public Liquid getLiquid() {
        return this.liquid;
    }

    @Override
    public int getLiquidCapacity() {
        return this.liquidCapacity;
    }
}
