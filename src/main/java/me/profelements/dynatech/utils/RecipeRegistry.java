package me.profelements.dynatech.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class RecipeRegistry {
    private static final ArrayList<Recipe> RECIPES = new ArrayList<>();
    private static RecipeRegistry INSTANCE;

    private RecipeRegistry() {
        INSTANCE = this;
    }

    public static RecipeRegistry getInstance() {
        return INSTANCE;
    }

    public static RecipeRegistry init() {
        return new RecipeRegistry();
    }

    public RecipeRegistry addRecipe(Recipe recipe) {
        RECIPES.add(recipe);
        return this;
    }

    public final ArrayList<Recipe> getRecipes() {
        return RECIPES;
    }

    public final Recipe getRecipeByKey(NamespacedKey key) {
        return getRecipes().stream().filter(r -> r.getKey().equals(key)).toList().get(0);
    }

    public final Stream<Recipe> getRecipesByRecipeType(RecipeType type) {
        return getRecipes().stream().filter(r -> r.getRecipeType().equals(type));
    }

    public final Stream<Recipe> getRecipesByInput(ItemStack[] input) {
        return getRecipes().stream().filter(r -> r.getInput().equals(input));
    }

    public final Stream<Recipe> getRecipesByOutput(ItemStack output) {
        return getRecipes().stream().filter(r -> r.getOutput()[0].equals(output));
    }

    public final Stream<Recipe> getRecipeByOutputs(ItemStack[] outputs) {
        return getRecipes().stream().filter(r -> r.getOutput().equals(outputs));
    }

    public final boolean isMatching(RecipeType type, ItemStack[] input, ItemStack output) {
        List<Recipe> recipes = getRecipesByRecipeType(type).filter(r -> r.getOutput().equals(output))
                .filter(r -> r.getInput().equals(input)).toList();
        return !recipes.isEmpty();
    }

}
