package me.profelements.dynatech.items.electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.profelements.dynatech.DynaTechItems;
import me.profelements.dynatech.items.abstracts.AbstractElectricMachine;
import me.profelements.dynatech.items.misc.ItemBand;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BandaidManager extends AbstractElectricMachine {
    
    private static final int[] INPUT_SLOTS = new int[] { 19, 20 };
    private static final int[] OUTPUT_SLOTS = new int[] { 24, 25 };

    private static final int[] INPUT_BORDER_SLOTS = new int[] { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
    private static final int[] OUTPUT_BORDER_SLOTS = new int[] {14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
    private static final int[] BACKGROUND_SLOTS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44 }; 

    private static final ItemStack PROGRESS_ITEM = new ItemStack(Material.PHANTOM_MEMBRANE);


    public BandaidManager(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }
    

    @Override
    public MachineRecipe findNextRecipe(BlockMenu inv) {

        ItemStack target = inv.getItemInSlot(getInputSlots()[0]);
        if (target != null) {
            ItemStack itemBand = inv.getItemInSlot(getInputSlots()[1]);
            if (itemBand != null) {
                SlimefunItem sfBand = SlimefunItem.getByItem(itemBand);
                if (sfBand != null) {
                    if (sfBand instanceof ItemBand) {
                        ItemBand band = (ItemBand) sfBand;
                        ItemStack result = band.applyToItem(target.clone());

                        if (result.getAmount() > 1) {
                            result.setAmount(1);
                        }

                        inv.consumeItem(getInputSlots()[0]);
                        inv.consumeItem(getInputSlots()[1]);

                        return new MachineRecipe(30, new ItemStack[] {target, itemBand}, new ItemStack[] {result});
                    }
                    
                }
            } else if (ItemBand.containsItemBand(target)) {
                String id = PersistentDataAPI.getString(target.getItemMeta(), ItemBand.KEY);
                if (id != null && SlimefunItem.getById(id) != null) {
                    SlimefunItem sfItem = SlimefunItem.getById(id);
                    ItemStack result = ItemBand.removeFromItem(target.clone());
                        
                    if (result.getAmount() > 1) {
                        result.setAmount(1);
                    }

                    inv.consumeItem(getInputSlots()[0]);

                    return new MachineRecipe(60, new ItemStack[] {target}, new ItemStack[] {result, sfItem.getItem()});
                }

            }
        }
        return null;
    }
    
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
        
        preset.addItem(getProgressSlot(), new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());

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
		List<ItemStack> display = new ArrayList<>();

        display.add(DynaTechItems.ITEM_BAND_HASTE);
        display.add(new CustomItemStack(Material.IRON_PICKAXE, "Any Tool"));
        display.add(DynaTechItems.ITEM_BAND_HEALTH);
        display.add(new CustomItemStack(Material.IRON_PICKAXE, "Any Tool"));
	    
        return display;
    }
    
}
