package me.profelements.dynatech.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.registries.Registries;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;

public class ChestMenuUtils {

    private static final ItemStack BACKGROUND_ITEM = io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
            .getBackground();
    private static final MenuClickHandler NO_CLICK = io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils
            .getEmptyClickHandler();

    public static void openRecipeBook(Player p) {
        ChestMenu menu = new ChestMenu("Recipe Book");
        menu.setEmptySlotsClickable(false);

        Set<Recipe> recipes = Registries.RECIPES.getEntries();

        Set<ItemStack> outputs = new HashSet<>();

        for (Recipe recipe : recipes) {
            outputs.add(recipe.getOutput()[0]);
        }

        int iter = 0;
        for (ItemStack recipeOutput : outputs) {
            if (iter == 54) {
                break;
            }

            menu.addItem(iter, recipeOutput, new MenuClickHandler() {
                @Override
                public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
                    openRecipeWithItem(p, menu.getItemInSlot(slot), 0);
                    return false;
                }
            });
            iter++;
        }

        menu.open(p);
    }

    private static void openRecipeWithItem(Player p, ItemStack item, int idx) {
        ChestMenu menu = new ChestMenu("Recipe");
        menu.setEmptySlotsClickable(false);

        menu.addItem(0, io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils.getBackButton(p, ""),
                new MenuClickHandler() {
                    @Override
                    public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
                        openRecipeBook(p);
                        return false;
                    }
                });

        for (int i = 1; i < 9; i++) {
            menu.addItem(i, BACKGROUND_ITEM, NO_CLICK);
        }

        List<Recipe> recipes = Registries.RECIPES.getEntries().stream()
                .filter((recipe) -> recipe.getOutput()[0].equals(item)).toList();

        Recipe recipe = recipes.get(idx);

        int[] recipeSlots = new int[] { 12, 13, 14, 21, 22, 23, 30, 31, 32 };
        int iter = 0;

        for (ItemStack recipeItem : recipe.getInput()) {
            if (RecipeRegistry.getInstance().getRecipesByOutput(recipeItem).toList().size() > 0) {
                menu.addItem(recipeSlots[iter], recipeItem, new MenuClickHandler() {

                    @Override
                    public boolean onClick(Player p, int slot, ItemStack itemS, ClickAction action) {
                        openRecipeWithItem(p, recipeItem, 0);
                        return false;
                    }

                });
            } else {
                menu.addItem(recipeSlots[iter], recipeItem, NO_CLICK);
            }
            iter++;
        }

        menu.addItem(19, recipe.getRecipeType().toItem(), NO_CLICK);
        menu.addItem(25, recipe.getOutput()[0], NO_CLICK);

        // air , air , air
        menu.addItem(36,
                io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils.getPreviousButton(p, idx + 1, recipes.size()),
                new MenuClickHandler() {

                    @Override
                    public boolean onClick(Player p, int slot, ItemStack itemS, ClickAction action) {
                        openRecipeWithItem(p, item, Math.max(0, idx - 1));
                        return false;
                    }

                });

        menu.addItem(44,
                io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils.getNextButton(p, idx + 1, recipes.size()),
                new MenuClickHandler() {

                    @Override
                    public boolean onClick(Player p, int slot, ItemStack itemS, ClickAction action) {
                        openRecipeWithItem(p, item, Math.min(recipes.size() - 1, idx + 1));
                        return false;
                    }

                });
        for (int i = 37; i < 44; i++) {
            menu.addItem(i, BACKGROUND_ITEM, NO_CLICK);
        }

        menu.open(p);
    }
}
