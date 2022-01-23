package me.profelements.dynatech.items.electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class WeatherController extends AMachine implements RecipeDisplayItem {

    private static final int[] BORDER = new int[] {1,2,6,7,9,10,11,15,16,17,19,20,24,25};
    private static final int[] BORDER_IN = new int[] {3,4,5,12,14,21,22,23};
    private static final int[] BORDER_OUT = new int[] {0,8,18,26};

    public WeatherController(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        addItemHandler(onBlockBreak());
    }

    @Override
    public void tick(Block b) {
        if (getCharge(b.getLocation()) < getEnergyConsumption()) {
            return;
        }

        BlockMenu menu = BlockStorage.getInventory(b);
        ItemStack item = menu.getItemInSlot(getInputSlots()[0]);

        if (item != null && (item.getType() == Material.SUNFLOWER || item.getType() == Material.LILAC || item.getType() == Material.CREEPER_HEAD)) {
            if (item.getType() == Material.SUNFLOWER) {
                if (b.getWorld().isClearWeather()) {
                    return;
                } 
                DynaTech.runSync(() -> {
                    b.getWorld().setClearWeatherDuration(1200);
                    removeCharge(b.getLocation(), getEnergyConsumption());
                });
            }
            
            if (item.getType() == Material.LILAC) {
                if (b.getWorld().hasStorm()) {
                    return;
                }
                DynaTech.runSync(() -> {
                    b.getWorld().setStorm(true);
                    b.getWorld().setWeatherDuration(1200);
                    removeCharge(b.getLocation(), getEnergyConsumption());
                });
            }

            if (item.getType() == Material.CREEPER_HEAD) {
                if (b.getWorld().isThundering()) {
                    return;
                }
                DynaTech.runSync(()-> {
                    b.getWorld().setThundering(true);
                    b.getWorld().setThunderDuration(1200);
                    removeCharge(b.getLocation(), getEnergyConsumption());
                });
            }
        }
    }


    public ItemHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent event, ItemStack item, List<ItemStack> drops) {
                event.getBlock().getWorld().setClearWeatherDuration(60);
            }
        };
    }

    @Nonnull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> items = new ArrayList<>();
        items.add(new ItemStack(Material.SUNFLOWER));
        items.add(new CustomItemStack(Material.DIAMOND, "&fMakes its sunny in philadelphia."));

        items.add(new ItemStack(Material.LILAC));
        items.add(new CustomItemStack(Material.DIAMOND, "&fMakes its rain while the old man snores"));

        items.add(new ItemStack(Material.CREEPER_HEAD));
        items.add(new CustomItemStack(Material.DIAMOND, "&fMakes it thunder."));

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
