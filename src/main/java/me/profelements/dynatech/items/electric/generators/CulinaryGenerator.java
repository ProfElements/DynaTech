package me.profelements.dynatech.items.electric.generators;

import io.github.thebusybiscuit.exoticgarden.items.CustomFood;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.common.ChatColors;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.NumberUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.abstracts.AbstractGenerator;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

public class CulinaryGenerator extends AbstractGenerator {
   
    private static final int[] INPUT_SLOTS = new int[] { 19, 20 };
    private static final int[] OUTPUT_SLOTS = new int[] { 24, 25 };

    private static final int[] INPUT_BORDER_SLOTS = new int[] { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
    private static final int[] OUTPUT_BORDER_SLOTS = new int[] {14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
    private static final int[] BACKGROUND_SLOTS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44 }; 

    private static final ItemStack PROGRESS_ITEM = new ItemStack(Material.IRON_SHOVEL);

    private ItemSetting<Boolean> exoticGardenIntegration = new ItemSetting<>(this, "exotic-garden-integration", true);

    public CulinaryGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemSetting(exoticGardenIntegration);
    }

    @Override
    public void postRegister() {
        registerDefaultFuelTypes();
    } 

    public void registerDefaultFuelTypes() {
        //1 Food Levels
        fuels.add(new MachineFuel(2, new ItemStack(Material.BEETROOT)));
        fuels.add(new MachineFuel(2, new ItemStack(Material.DRIED_KELP)));
        fuels.add(new MachineFuel(2, new ItemStack(Material.POTATO)));
        fuels.add(new MachineFuel(2, new ItemStack(Material.TROPICAL_FISH)));

        //2 Food Levels
        fuels.add(new MachineFuel(4, new ItemStack(Material.COOKIE)));
        fuels.add(new MachineFuel(4, new ItemStack(Material.MELON_SLICE)));
        fuels.add(new MachineFuel(4, new ItemStack(Material.CHICKEN)));
        fuels.add(new MachineFuel(4, new ItemStack(Material.COD)));
        fuels.add(new MachineFuel(4, new ItemStack(Material.MUTTON)));
        fuels.add(new MachineFuel(4, new ItemStack(Material.SWEET_BERRIES)));
        fuels.add(new MachineFuel(4, new ItemStack(Material.SALMON)));

        //3 Food Levels
        fuels.add(new MachineFuel(6, new ItemStack(Material.CARROT)));
        fuels.add(new MachineFuel(6, new ItemStack(Material.BEEF)));
        fuels.add(new MachineFuel(6, new ItemStack(Material.PORKCHOP)));
        fuels.add(new MachineFuel(6, new ItemStack(Material.RABBIT)));

        //4 Food Levels
        fuels.add(new MachineFuel(10, new ItemStack(Material.APPLE)));
        fuels.add(new MachineFuel(10, new ItemStack(Material.GOLDEN_APPLE)));
        fuels.add(new MachineFuel(10, new ItemStack(Material.ENCHANTED_GOLDEN_APPLE)));
        fuels.add(new MachineFuel(10, new ItemStack(Material.CHORUS_FRUIT)));

        //5 Food Levels
        fuels.add(new MachineFuel(18, new ItemStack(Material.BAKED_POTATO)));
        fuels.add(new MachineFuel(18, new ItemStack(Material.COOKED_RABBIT)));
        fuels.add(new MachineFuel(18, new ItemStack(Material.COOKED_COD)));
        fuels.add(new MachineFuel(18, new ItemStack(Material.BREAD)));

        //6 Food Levels
        fuels.add(new MachineFuel(24, new ItemStack(Material.COOKED_MUTTON)));
        fuels.add(new MachineFuel(24, new ItemStack(Material.COOKED_CHICKEN)));
        fuels.add(new MachineFuel(24, new ItemStack(Material.COOKED_SALMON)));
        fuels.add(new MachineFuel(24, new ItemStack(Material.GOLDEN_CARROT)));

        //8 Food Levels
        fuels.add(new MachineFuel(36, new ItemStack(Material.COOKED_PORKCHOP)));
        fuels.add(new MachineFuel(36, new ItemStack(Material.COOKED_BEEF)));
        fuels.add(new MachineFuel(36, new ItemStack(Material.PUMPKIN_PIE)));
        
        if(DynaTech.isExoticGardenInstalled() && exoticGardenIntegration.getValue()) {
            for (SlimefunItem sfItem : Slimefun.getRegistry().getEnabledSlimefunItems()) {
                if (sfItem instanceof CustomFood) {
                    CustomFood cfItem = (CustomFood) sfItem;
                    MachineFuel fuel = new MachineFuel(cfItem.getFoodValue() * 4, sfItem.getItem());
                    fuels.add(fuel);
                }
            }
        }

    }
    
    @Nonnull
    @Override
    public ItemStack getProgressBar() {
        return PROGRESS_ITEM;
    }	

	@Override
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

        for (int slot : getOutputSlots()) {
            preset.addMenuClickHandler(slot,new ChestMenu.AdvancedMenuClickHandler() {
                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    return cursor.getType().isAir();
                }

                @Override
                public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
                    return false;
                }
            });
        }	
	}

	@Override
	protected int[] getInputSlots() {
    	return INPUT_SLOTS;
	}

	@Override
	protected int[] getOutputSlots() {
	    return OUTPUT_SLOTS;
	}

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> list = new ArrayList<>();

        for (MachineFuel fuel : fuels) {
            ItemStack item = fuel.getInput().clone();
            ItemMeta im = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add(ChatColors.color("&8\u21E8 &7Lasts " + NumberUtils.getTimeLeft(fuel.getTicks() / 2)));
            lore.add(ChatColors.color("&8\u21E8 &e\u26A1 &7" + getEnergyProduction() * 2) + " J/s");
            lore.add(ChatColors.color("&8\u21E8 &e\u26A1 &7" + NumberUtils.getCompactDouble((double) fuel.getTicks() * getEnergyProduction()) + " J in total"));
            im.setLore(lore);
            item.setItemMeta(im);
            list.add(item);
        }

        return list;
    }
}
