package me.profelements.dynatech.items.electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.abstracts.AbstractElectricTicker;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class WeatherController extends AbstractElectricTicker implements RecipeDisplayItem {

    private static final int[] BACKGROUND_SLOTS = new int[] {0,1,2,3,5,6,7,8};
    private static final int[] INPUT_BORDER_SLOTS = new int[] {};
    private static final int[] OUTPUT_BORDER_SLOTS = new int[] {};

    public WeatherController(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    
        new BlockMenuPreset(getId(), getItemName()) {
            @Override
            public void init() {
                setupMenu(this); 
            }

            @Override
            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.INTERACT_BLOCK); 
            }
            
            @Nonnull
            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.INSERT) {
                    return null;
                } else {
                    return null; 
                }
            }

        };

    }

	protected void setupMenu(BlockMenuPreset preset) {
		for (int slot : BACKGROUND_SLOTS) {
            preset.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int slot : INPUT_BORDER_SLOTS) {
            preset.addItem(slot, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int slot : OUTPUT_BORDER_SLOTS) {
            preset.addItem(slot, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        } 

    }

    public void tick(Block b, SlimefunItem sfItem) {
        if (getCharge(b.getLocation()) < getEnergyConsumption()) {
            return;
        }

        BlockMenu menu = BlockStorage.getInventory(b);
        ItemStack item = menu.getItemInSlot(4);

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

    @Override
    protected void onBreak(BlockBreakEvent e, Location l) {
        l.getWorld().setClearWeatherDuration(60);
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
}
