package me.profelements.dynatech.items.electric.generators;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.profelements.dynatech.items.electric.abstracts.AMachineGenerator;

public class CulinaryGenerator extends AMachineGenerator {

    public CulinaryGenerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }


    @Override
    public void registerDefaultFuelTypes() {
       
        //1 Food Levels
        registerFuel(new MachineFuel(2, new ItemStack(Material.BEETROOT)));
        registerFuel(new MachineFuel(2, new ItemStack(Material.DRIED_KELP)));
        registerFuel(new MachineFuel(2, new ItemStack(Material.POTATO)));
        registerFuel(new MachineFuel(2, new ItemStack(Material.TROPICAL_FISH)));

        //2 Food Levels
        registerFuel(new MachineFuel(4, new ItemStack(Material.COOKIE)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.MELON_SLICE)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.CHICKEN)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.COD)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.MUTTON)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.SWEET_BERRIES)));
        registerFuel(new MachineFuel(4, new ItemStack(Material.SALMON)));

        //3 Food Levels
        registerFuel(new MachineFuel(6, new ItemStack(Material.CARROT)));
        registerFuel(new MachineFuel(6, new ItemStack(Material.BEEF)));
        registerFuel(new MachineFuel(6, new ItemStack(Material.PORKCHOP)));
        registerFuel(new MachineFuel(6, new ItemStack(Material.RABBIT)));

        //4 Food Levels
        registerFuel(new MachineFuel(10, new ItemStack(Material.APPLE)));
        registerFuel(new MachineFuel(10, new ItemStack(Material.GOLDEN_APPLE)));
        registerFuel(new MachineFuel(10, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE)));
        registerFuel(new MachineFuel(10, new ItemStack(Material.CHORUS_FRUIT)));

        //5 Food Levels
        registerFuel(new MachineFuel(18, new ItemStack(Material.BAKED_POTATO)));
        registerFuel(new MachineFuel(18, new ItemStack(Material.COOKED_RABBIT)));
        registerFuel(new MachineFuel(18, new ItemStack(Material.COOKED_COD)));
        registerFuel(new MachineFuel(18, new ItemStack(Material.BREAD)));

        //6 Food Levels
        registerFuel(new MachineFuel(24, new ItemStack(Material.COOKED_MUTTON)));
        registerFuel(new MachineFuel(24, new ItemStack(Material.COOKED_CHICKEN)));
        registerFuel(new MachineFuel(24, new ItemStack(Material.COOKED_SALMON)));
        registerFuel(new MachineFuel(24, new ItemStack(Material.GOLDEN_CARROT)));
        
        //8 Food Levels
        registerFuel(new MachineFuel(36, new ItemStack(Material.COOKED_PORKCHOP)));
        registerFuel(new MachineFuel(36, new ItemStack(Material.COOKED_BEEF)));
        registerFuel(new MachineFuel(36, new ItemStack(Material.PUMPKIN_PIE)));
    }

    @Override
    public String getMachineIdentifier() {
       return "CULINARY_GENERATOR";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.IRON_SHOVEL);
    }
    
}
