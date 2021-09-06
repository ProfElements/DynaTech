package me.profelements.dynatech.items.electric.growthchambers;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GrowthChamberOcean extends AMachine {

    public GrowthChamberOcean(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(9, new ItemStack(Material.LILY_PAD), new ItemStack(Material.LILY_PAD, 3));
        registerRecipe(9, new ItemStack(Material.SEA_PICKLE), new ItemStack(Material.SEA_PICKLE, 3));
        registerRecipe(12, new ItemStack(Material.SEAGRASS), new ItemStack(Material.SEAGRASS, 4));
        registerRecipe(9, new ItemStack(Material.KELP), new ItemStack(Material.KELP, 3));
    // Coral blocks
        // Brings dead coral blocks back to life!
        registerRecipe(9, new ItemStack(Material.DEAD_TUBE_CORAL_BLOCK), new ItemStack(Material.TUBE_CORAL_BLOCK, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_BRAIN_CORAL_BLOCK), new ItemStack(Material.BRAIN_CORAL_BLOCK, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_BUBBLE_CORAL_BLOCK), new ItemStack(Material.BUBBLE_CORAL_BLOCK, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_FIRE_CORAL_BLOCK), new ItemStack(Material.FIRE_CORAL_BLOCK, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_HORN_CORAL_BLOCK), new ItemStack(Material.HORN_CORAL_BLOCK, 1));
        // Block duplication
        registerRecipe(12, new ItemStack(Material.TUBE_CORAL_BLOCK), new ItemStack(Material.TUBE_CORAL_BLOCK, 2));
        registerRecipe(12, new ItemStack(Material.BRAIN_CORAL_BLOCK), new ItemStack(Material.BRAIN_CORAL_BLOCK, 2));
        registerRecipe(12, new ItemStack(Material.BUBBLE_CORAL_BLOCK), new ItemStack(Material.BUBBLE_CORAL_BLOCK, 2));
        registerRecipe(12, new ItemStack(Material.FIRE_CORAL_BLOCK), new ItemStack(Material.FIRE_CORAL_BLOCK, 2));
        registerRecipe(12, new ItemStack(Material.HORN_CORAL_BLOCK), new ItemStack(Material.HORN_CORAL_BLOCK, 2));

    // Coral
        // Revive for coral
        registerRecipe(9, new ItemStack(Material.DEAD_TUBE_CORAL), new ItemStack(Material.TUBE_CORAL, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_BRAIN_CORAL), new ItemStack(Material.BRAIN_CORAL, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_BUBBLE_CORAL), new ItemStack(Material.BUBBLE_CORAL, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_FIRE_CORAL), new ItemStack(Material.FIRE_CORAL, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_HORN_CORAL), new ItemStack(Material.HORN_CORAL, 1));
        // Coral duplication
        registerRecipe(12, new ItemStack(Material.TUBE_CORAL), new ItemStack(Material.TUBE_CORAL, 2));
        registerRecipe(12, new ItemStack(Material.BRAIN_CORAL), new ItemStack(Material.BRAIN_CORAL, 2));
        registerRecipe(12, new ItemStack(Material.BUBBLE_CORAL), new ItemStack(Material.BUBBLE_CORAL, 2));
        registerRecipe(12, new ItemStack(Material.FIRE_CORAL), new ItemStack(Material.FIRE_CORAL, 2));
        registerRecipe(12, new ItemStack(Material.HORN_CORAL), new ItemStack(Material.HORN_CORAL, 2));

    // Coral fans
        // Medical attention for the fans
        registerRecipe(9, new ItemStack(Material.DEAD_TUBE_CORAL_FAN), new ItemStack(Material.TUBE_CORAL_FAN, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_BRAIN_CORAL_FAN), new ItemStack(Material.BRAIN_CORAL_FAN, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_BUBBLE_CORAL_FAN), new ItemStack(Material.BUBBLE_CORAL_FAN, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_FIRE_CORAL_FAN), new ItemStack(Material.FIRE_CORAL_FAN, 1));
        registerRecipe(9, new ItemStack(Material.DEAD_HORN_CORAL_FAN), new ItemStack(Material.HORN_CORAL_FAN, 1));
        // Fan duplication
        registerRecipe(12, new ItemStack(Material.TUBE_CORAL_FAN), new ItemStack(Material.TUBE_CORAL_FAN, 2));
        registerRecipe(12, new ItemStack(Material.BRAIN_CORAL_FAN), new ItemStack(Material.BRAIN_CORAL_FAN, 2));
        registerRecipe(12, new ItemStack(Material.BUBBLE_CORAL_FAN), new ItemStack(Material.BUBBLE_CORAL_FAN, 2));
        registerRecipe(12, new ItemStack(Material.FIRE_CORAL_FAN), new ItemStack(Material.FIRE_CORAL_FAN, 2));
        registerRecipe(12, new ItemStack(Material.HORN_CORAL_FAN), new ItemStack(Material.HORN_CORAL_FAN, 2));


    }
    
    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.CONDUIT);
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_OCEAN";
    }

}
