package me.profelements.dynatech.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import com.google.common.base.Preconditions;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class Recipe {
    private NamespacedKey KEY;
    private RecipeType TYPE;
    private ItemStack[] INPUT;
    private ItemStack OUTPUT;

    private Recipe() {
    }

    public static Recipe init() {
        return new Recipe();
    }

    public Recipe setKey(NamespacedKey key) {
        Preconditions.checkNotNull(key, "The recipe's namespaced key should not be null");
        this.KEY = key;
        return this;
    }

    public final NamespacedKey getKey() {
        return this.KEY;
    }

    public Recipe setRecipeType(RecipeType type) {
        Preconditions.checkNotNull(type, "The recipe's type should not be null");
        this.TYPE = type;
        return this;
    }

    public final RecipeType getRecipeType() {
        return this.TYPE;
    }

    public Recipe setInput(ItemStack[] input) {
        Preconditions.checkNotNull(input, "This recipe's inputs should not be null");
        this.INPUT = input;
        return this;
    }

    public final ItemStack[] getInput() {
        return this.INPUT;
    }

    public Recipe setOutput(ItemStack output) {
        Preconditions.checkNotNull(output, "This recipe's output should not be null");
        this.OUTPUT = output;
        return this;
    }

    public final ItemStack getOutput() {
        return this.OUTPUT;
    }

    public final Recipe build() {
        Preconditions.checkNotNull(this.KEY, "The recipe's namespaced key should not be null");
        Preconditions.checkNotNull(this.TYPE, "The recipe's type should not be null");
        Preconditions.checkNotNull(this.INPUT, "This recipe's inputs should not be null");
        Preconditions.checkNotNull(this.OUTPUT, "This recipe's output should not be null");
        return this;
    }

    public void register(RecipeRegistry registry) {
        Recipe res = this.build();
        res.getRecipeType().register(res.getInput(), res.getOutput());
        registry.addRecipe(res);
    }

}
