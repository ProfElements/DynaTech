package me.profelements.dynatech.items.electric;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.CSCoreLibPlugin.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.electric.abstracts.AMachine;

public class WeatherController extends AMachine {

    private static final int[] BORDER = new int[] {1,2,6,7,9,10,11,15,16,17,19,20,24,25};
    private static final int[] BORDER_IN = new int[] {3,4,5,12,14,21,22,23};
    private static final int[] BORDER_OUT = new int[] {0,8,18,26};

    public WeatherController(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void tick(Block b) {
        if (getCharge(b.getLocation()) < getEnergyConsumption()) {
            return;
        }

        BlockMenu menu = BlockStorage.getInventory(b);
        ItemStack item = menu.getItemInSlot(getInputSlots()[0]);

        if( item != null && (item.getType() == Material.SUNFLOWER || item.getType() == Material.LILAC || item.getType() == Material.CREEPER_HEAD) ) {
            if (item.getType() == Material.SUNFLOWER) {
                if (b.getWorld().isClearWeather()) {
                    return;
                } 
                b.getWorld().setClearWeatherDuration(60*20);
                removeCharge(b.getLocation(), getEnergyConsumption());
            }
            
            if (item.getType() == Material.LILAC) {
                if(b.getWorld().hasStorm()) {
                    return;
                }
                DynaTech.runSync(()->b.getWorld().setStorm(true));
                b.getWorld().setWeatherDuration(60*20);
                removeCharge(b.getLocation(), getEnergyConsumption());
            }

            if (item.getType() == Material.CREEPER_HEAD) {
                if (b.getWorld().isThundering()) {
                    return;
                }
                DynaTech.runSync(()->b.getWorld().setThundering(true));
                b.getWorld().setThunderDuration(60*20);
                removeCharge(b.getLocation(), getEnergyConsumption());
            }
        }
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();
        items.add(new ItemStack(Material.SUNFLOWER));
        items.add(new CustomItem(Material.DIAMOND, "Makes its sunny in philadelphia."));

        items.add(new ItemStack(Material.LILAC));
        items.add(new CustomItem(Material.DIAMOND, "Makes its rain while the old man snores"));

        items.add(new ItemStack(Material.CREEPER_HEAD));
        items.add(new CustomItem(Material.DIAMOND, "Makes it thunder."));

        return items;
    }


    @Override
    public String getMachineIdentifier() {
        return "WEATHER_CONTROLLER";
    }


    @Override
    public List<int[]> getBorders() {
        List<int[]> borders = new ArrayList<>();
        borders.add(BORDER);
        borders.add(BORDER_IN);
        borders.add(BORDER_OUT);
        
        return borders;
    }
    
    @Override
    public int[] getInputSlots() {
        return new int[] {13};
    }
    @Override
    public int[] getOutputSlots() {
        return new int[] {13};
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.SUNFLOWER);
    }
    
    @Override
    public int getProgressBarSlot() {
        return 4;
    }
    

}