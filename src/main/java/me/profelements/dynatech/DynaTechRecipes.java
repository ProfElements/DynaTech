package me.profelements.dynatech;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;

import me.profelements.dynatech.utils.Recipe;
import me.profelements.dynatech.utils.RecipeRegistry;
import me.profelements.dynatech.utils.TimedRecipe;

public class DynaTechRecipes {
    private DynaTechRecipes() {
    }
    // START Recipes

    public static final void registerRecipes(RecipeRegistry registry) {
        // Logs to Charcoal Coke Oven Recipe
        for (Material mat : Tag.LOGS.getValues()) {
            Recipe.init()
                    .setKey(new NamespacedKey(DynaTech.getInstance(), mat.toString().toLowerCase() + "_to_charcoal"))
                    .setRecipeType(DynaTechItems.DT_OVENING)
                    .setInput(new ItemStack(mat))
                    .setOutput(new ItemStack(Material.CHARCOAL))
                    .register();
        }
        // START Tree Growth Chamber Recipes
        // OAK, BIRCH, SPRUCE, DARK_OAK, MANGROVE, CHERRY, JUNGLE, ACAICA, AZALEA
        // 1 SAPLING (10 secs) (128/s; 64 / default slimefun tick) -> 2 SAPLING, 6 LOG,
        // 3 LEAF
        TimedRecipe.init()
                .setTimeInTicks(20)
                .setKey(new NamespacedKey(DynaTech.getInstance(), "oak_tree"))
                .setRecipeType(DynaTechItems.DT_TREE_GROWTH_CHAMBER)
                .setInput(new ItemStack(Material.OAK_SAPLING))
                .setOutput(
                        new ItemStack[] {
                                new ItemStack(Material.OAK_SAPLING, 2),
                                new ItemStack(Material.OAK_LOG, 6),
                                new ItemStack(Material.OAK_LEAVES, 3)
                        })
                .register();

        // END Tree Growth Chamber Recipes
    }

    // END Recipes
}
