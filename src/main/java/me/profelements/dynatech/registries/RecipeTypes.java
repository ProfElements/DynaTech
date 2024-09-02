package me.profelements.dynatech.registries;

import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import io.github.bakedlibs.dough.items.CustomItemStack;

import me.profelements.dynatech.items.electric.MaterialHive;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RecipeTypes {

    public static void init(Registry<RecipeType> registry) {
        registry.register(Keys.SCOOPING, SCOOPING);
        registry.register(Keys.OVENING, OVENING);
        registry.register(Keys.BLOCK_DROP, BLOCK_DROP);
        registry.register(Keys.TREE_GROWTH_CHAMBER, TREE_GROWTH_CHAMBER);
        registry.register(Keys.MATERIAL_HIVE, MATERIAL_HIVE);
        registry.register(Keys.PETAL_APOTHECARY, PETAL_APOTHECARY);
    }

    public static final RecipeType SCOOPING = new RecipeType(Keys.SCOOPING.key(),
            new CustomItemStack(Material.IRON_SHOVEL, "Use the Scoop to get this item."));

    public static final RecipeType OVENING = new RecipeType(Keys.OVENING.key(),
            new CustomItemStack(Material.SMOKER, "Throw into the Coal Coke Oven multiblock"));

    public static final RecipeType BLOCK_DROP = new RecipeType(Keys.BLOCK_DROP.key(),
            new CustomItemStack(Material.COBWEB, "Drops from a block"));

    public static final RecipeType TREE_GROWTH_CHAMBER = new RecipeType(Keys.TREE_GROWTH_CHAMBER.key(),
            new CustomItemStack(Material.LIME_CONCRETE, "Throw into the Tree Growth Chamber machine"));

    public static final RecipeType MATERIAL_HIVE = new RecipeType(Keys.MATERIAL_HIVE.key(),
            Items.MATERIAL_HIVE.stack(),
            (recipe, output) -> {
                MaterialHive materialHive = ((MaterialHive) Items.MATERIAL_HIVE.stack().getItem());
                materialHive.getMachineRecipes().add(new MachineRecipe(1800, recipe, new ItemStack[] { output }));
            });

    public static final RecipeType PETAL_APOTHECARY = new RecipeType(Keys.PETAL_APOTHECARY.key(),
            Items.PETAL_APOTHECARY.stack(),
            (recipe, output) -> {

            });

    public static final class Keys {
        public static final TypedKey<RecipeType> SCOOPING = TypedKey.create("dynatech", "scooping");
        public static final TypedKey<RecipeType> OVENING = TypedKey.create("dynatech", "ovening");
        public static final TypedKey<RecipeType> BLOCK_DROP = TypedKey.create("dynatech", "block_drop");
        public static final TypedKey<RecipeType> TREE_GROWTH_CHAMBER = TypedKey.create("dynatech",
                "tree_growth_chamber");
        public static final TypedKey<RecipeType> MATERIAL_HIVE = TypedKey.create("dynatech", "material_hive");
        public static final TypedKey<RecipeType> PETAL_APOTHECARY = TypedKey.create("dynatech", "petal_apothecary");
    }
}
