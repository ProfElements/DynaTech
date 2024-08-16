package me.profelements.dynatech.registries;

import me.profelements.dynatech.utils.ItemWrapper;
import me.profelements.dynatech.utils.Recipe;

public class Registries {
    public static final Registry<Recipe> RECIPES = Registry.create(Keys.RECIPES);
    public static final Registry<ItemWrapper> ITEMS = Registry.create(Keys.ITEMS);

    // public static final Registry<Block> BLOCKS =
    // Registry<Block>.create(Keys.BLOCKS);
    // public static final Registry<Fluid> FLUIDS =
    // Registry<Fluid>.create(Keys.FLUIDS);
    public static final class Keys {
        public static final TypedKey<Registry<Recipe>> RECIPES = TypedKey.create("dynatech", "recipe");
        public static final TypedKey<Registry<ItemWrapper>> ITEMS = TypedKey.create("dynatech", "item");
        // public static final TypedKey<Block> BLOCKS = TypedKey.create("dynatech",
        // "block");
        // public static final TypedKey<Fluid> FLUIDS = TypedKey.create("dynatech",
        // "fluid");
    }
}
