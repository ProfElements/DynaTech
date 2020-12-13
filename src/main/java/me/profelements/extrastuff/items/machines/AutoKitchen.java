package me.profelements.extrastuff.items.machines;

import io.github.thebusybiscuit.exoticgarden.ExoticGarden;
import io.github.thebusybiscuit.exoticgarden.ExoticGardenRecipeTypes;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class AutoKitchen extends AContainer {

    private static final int[] border = {4, 22};
    private static final int[] input_border = {3, 12, 21};
    private static final int[] output_border = {5, 6, 7, 8, 14, 17, 23, 24, 25, 26};

    public AutoKitchen(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        new BlockMenuPreset(getId(), getItemName()) {

            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(Block b, Player p) {
             return p.hasPermission("slimefun.inventory.bypass") || SlimefunPlugin.getProtectionManager().hasPermission(p, b.getLocation(), ProtectableAction.INTERACT_BLOCK);
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[0];
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
                if (flow == ItemTransportFlow.WITHDRAW) {
                    return getOutputSlots();
                }

                int fullSlots = 0;
                List<Integer> slots = new LinkedList<>();

                for (int slot : getInputSlots()) {
                    ItemStack stack =  menu.getItemInSlot(slot);
                    if (stack != null && SlimefunUtils.isItemSimilar(stack, item, true, false)) {
                        if (stack.getAmount() >= stack.getMaxStackSize()) {
                            fullSlots++;
                        }

                        slots.add(slot);
                    }

                }

                if (slots.isEmpty()) {
                    return getInputSlots();
                } else if (fullSlots == slots.size()) {
                    return new int[0];
                } else {
                    Collections.sort(slots, compareSlots(menu));
                    int[] array = new int[slots.size()];

                    for (int i = 0; i < slots.size(); i++ ) {
                        array[i] = slots.get(i);
                    }

                    return array;

                }
            }
        };

        registerBlockHandler(getId(), (p, b, tool, reason) -> {
            BlockMenu inv = BlockStorage.getInventory(b);

            if (inv != null) {
                inv.dropItems(b.getLocation(), getInputSlots());
                inv.dropItems(b.getLocation(), getOutputSlots());
            }

            progress.remove(b);
            processing.remove(b);
            return true;
        });

            registerRecipes();
    }




    private Comparator<Integer> compareSlots(DirtyChestMenu menu) {
        return (slot1, slot2) -> menu.getItemInSlot(slot1).getAmount() - menu.getItemInSlot(slot2).getAmount();
    }

    protected void constructMenu(BlockMenuPreset preset) {
        for (int slot : border ) {
            preset.addItem(slot, new CustomItem(Material.GRAY_STAINED_GLASS_PANE, ""), ChestMenuUtils.getEmptyClickHandler() );
        }

        for (int slot : input_border ) {
            preset.addItem(slot, new CustomItem(Material.CYAN_STAINED_GLASS_PANE, ""), ChestMenuUtils.getEmptyClickHandler() );
        }

        for (int slot : output_border ) {
            preset.addItem(slot, new CustomItem(Material.ORANGE_STAINED_GLASS_PANE, ""), ChestMenuUtils.getEmptyClickHandler() );
        }

        preset.addItem(13, new CustomItem(Material.BLACK_STAINED_GLASS_PANE, ""), ChestMenuUtils.getEmptyClickHandler() );

        for (int slot : getOutputSlots()) {
            preset.addMenuClickHandler(slot, new ChestMenu.AdvancedMenuClickHandler() {

                @Override
                public boolean onClick(Player p,  int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    return cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR;
                }

            });
        }
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.FLINT_AND_STEEL);
    }

    @Override
    public int[] getOutputSlots() {
        return new int[] {15, 16};
    }

    @Override
    public int[] getInputSlots() {
        return new int[] {0, 1, 2, 9, 10, 11, 18, 19, 20};
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "AUTO_KITCHEN";
    }

    private void registerRecipes() {
        List<ItemStack[]> inputs = RecipeType.getRecipeInputList((MultiBlockMachine) ExoticGarden.getKitchen());

        for (ItemStack[] input : inputs) {
            registerRecipe(30, input, new ItemStack[] {RecipeType.getRecipeOutputList((MultiBlockMachine) ExoticGarden.getKitchen(), input)});
        }

    }

}
