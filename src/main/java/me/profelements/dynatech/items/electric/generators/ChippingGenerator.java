package me.profelements.dynatech.items.electric.generators;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.profelements.dynatech.items.abstracts.AbstractGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class ChippingGenerator extends AbstractGenerator {

    private final int powerPerDurability = 8;
    
    private static final int[] INPUT_SLOTS = new int[] { 19, 20 };
    private static final int[] OUTPUT_SLOTS = new int[] { 24, 25 };

    private static final int[] INPUT_BORDER_SLOTS = new int[] { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
    private static final int[] OUTPUT_BORDER_SLOTS = new int[] {14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
    private static final int[] BACKGROUND_SLOTS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44 }; 

    private final ItemStack PROGRESS_ITEM = new ItemStack(Material.NETHERITE_AXE);

    public ChippingGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    /*
    @Override
    public int getGeneratedOutput(Location l, Config data) {
        BlockMenu inv = BlockStorage.getInventory(l.getBlock());
        int julesAmount;

        for (int slot : getInputSlots()) {
            ItemStack item = inv.getItemInSlot(slot);
            // Do as many lightweight checks as possible before we do the intensive stuff
            if (item != null && !item.getType().isAir() && item.getType().isItem() && item.hasItemMeta()) {
                // `getItemMeta` does multiple clones! Even doing this once is slow, nevermind multiple times!
                ItemMeta meta = item.getItemMeta();
                if (meta instanceof Damageable && !meta.isUnbreakable()) {
                    Damageable im = (Damageable) meta;
                    if (!im.hasDamage()) {
                        julesAmount = item.getType().getMaxDurability() * powerPerDurability;
                        if (julesAmount != 0) {
                            inv.consumeItem(slot);
                            return julesAmount;
                        }
                    }

                    julesAmount = (item.getType().getMaxDurability() - im.getDamage()) * 2;
                    if (julesAmount != 0) {
                        inv.consumeItem(slot);
                        return julesAmount;
                    }
                }
            }
        }
        return 0;
    }
    */

    @Override
    public MachineFuel findNextFuel(BlockMenu inv) {
            
        for (int slot : getInputSlots()) {
            ItemStack item =  inv.getItemInSlot(slot); 
            if (item != null && !item.getType().isAir() && item.hasItemMeta()) {
                ItemMeta meta = item.getItemMeta(); 
                if (meta instanceof Damageable && !meta.isUnbreakable()) {
                    Damageable damage = (Damageable) meta; 
                    if (!damage.hasDamage()) {
                        int durability = item.getType().getMaxDurability(); 
                        inv.consumeItem(slot); 
                        return new MachineFuel(durability, item);  
                    } else {
                        int durability = item.getType().getMaxDurability() - damage.getDamage();
                        inv.consumeItem(slot);
                        return new MachineFuel(durability, item);  

                    }
                }
            }
        }

        return null;
    }

	@Override
	public List<ItemStack> getDisplayRecipes() {
		return new ArrayList<>();
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

        preset.addItem(22, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());
        
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
	protected ItemStack getProgressBar() {
		return PROGRESS_ITEM;
	}

	@Override
	protected int[] getInputSlots() {
		return INPUT_SLOTS;
	}


	@Override
	protected int[] getOutputSlots() {
		return OUTPUT_SLOTS;
	}

}
