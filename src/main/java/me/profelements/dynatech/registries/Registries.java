package me.profelements.dynatech.registries;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.profelements.dynatech.utils.ItemWrapper;
import me.profelements.dynatech.utils.Recipe;

public class Registries {
    public static Registry<ItemGroup> ITEM_GROUPS = Registry.create(Keys.ITEM_GROUPS);
    public static Registry<ItemWrapper> ITEMS = Registry.create(Keys.ITEMS);
    public static Registry<RecipeType> RECIPE_TYPES = Registry.create(Keys.RECIPE_TYPES);
    public static Registry<Recipe> RECIPES = Registry.create(Keys.RECIPES);

    // FOR PEOPLE WHO WANT TO USE THESE listen to `RegistryFreezeEvent`
    //

    // public static final Registry<Block> BLOCKS =
    // Registry<Block>.create(Keys.BLOCKS);
    // public static final Registry<Fluid> FLUIDS =
    // Registry<Fluid>.create(Keys.FLUIDS);
    public static final class Keys {
        public static final TypedKey<Registry<RecipeType>> RECIPE_TYPES = TypedKey.create("dynatech", "recipe_types");
        public static final TypedKey<Registry<Recipe>> RECIPES = TypedKey.create("dynatech", "recipes");
        public static final TypedKey<Registry<ItemGroup>> ITEM_GROUPS = TypedKey.create("dynatech", "item_groups");
        public static final TypedKey<Registry<ItemWrapper>> ITEMS = TypedKey.create("dynatech", "items");
        // public static final TypedKey<Block> BLOCKS = TypedKey.create("dynatech",
        // "block");
        // public static final TypedKey<Fluid> FLUIDS = TypedKey.create("dynatech",
        // "fluid");
    }
}
