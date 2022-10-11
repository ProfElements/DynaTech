package me.profelements.dynatech.items.electric;

import io.github.thebusybiscuit.exoticgarden.items.ExoticGardenFruit;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.abstracts.AbstractElectricMachine;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SeedPlucker extends AbstractElectricMachine {
        
    private static final int[] INPUT_SLOTS = new int[] { 19, 20 };
    private static final int[] OUTPUT_SLOTS = new int[] { 24, 25 };

    private static final int[] INPUT_BORDER_SLOTS = new int[] { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
    private static final int[] OUTPUT_BORDER_SLOTS = new int[] {14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
    private static final int[] BACKGROUND_SLOTS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44 }; 

    private static final ItemStack PROGRESS_ITEM = new ItemStack(Material.IRON_HOE);

    private final ItemSetting<Boolean> exoticGardenIntegration = new ItemSetting<>(this, "exotic-garden-integration", true);

    public SeedPlucker(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemSetting(exoticGardenIntegration);
    }

    public void registerDefaultRecipes() {
        recipes.add(new MachineRecipe(10, new ItemStack[] {new ItemStack(Material.CHORUS_FRUIT, 4)},  new ItemStack[] {new ItemStack(Material.CHORUS_FLOWER)}));
        recipes.add(new MachineRecipe(10, new ItemStack[] {new ItemStack(Material.WHEAT)}, new ItemStack[] {new ItemStack(Material.WHEAT_SEEDS)}));
        recipes.add(new MachineRecipe(10, new ItemStack[] {new ItemStack(Material.BEETROOT)}, new ItemStack[] {new ItemStack(Material.BEETROOT_SEEDS)}));
        recipes.add(new MachineRecipe(10, new ItemStack[] {new ItemStack(Material.PUMPKIN)}, new ItemStack[] {new ItemStack(Material.PUMPKIN_SEEDS)}));
        recipes.add(new MachineRecipe(10, new ItemStack[] {new ItemStack(Material.MELON_SLICE)}, new ItemStack[] {new ItemStack(Material.MELON_SEEDS)}));
        
        if (DynaTech.isExoticGardenInstalled()) {
            for (SlimefunItem item : Slimefun.getRegistry().getEnabledSlimefunItems()) {
                if (item instanceof ExoticGardenFruit) {
                    SlimefunItem out = SlimefunItem.getById(item.getId().concat("_BUSH")); 
                    if (out != null) {
                        recipes.add(new MachineRecipe(10, new ItemStack[] { item.getItem() }, new ItemStack[] { out.getItem() }));
                    }
                    
                    out = SlimefunItem.getById(item.getId().concat("_PLANT"));
                    if (out != null) {
                        recipes.add(new MachineRecipe(10, new ItemStack[] { item.getItem() }, new ItemStack[] { out.getItem() }));
                    }     

                    out = SlimefunItem.getById(item.getId().concat("_SAPLING"));
                    if (out != null) {
                        recipes.add(new MachineRecipe(10, new ItemStack[] { item.getItem() }, new ItemStack[] { out.getItem() }));
                    }
                }
            }
        }
    }
    
    @Override
	protected ItemStack getProgressBar() {
		return PROGRESS_ITEM;
	}

    @Override
    public void postRegister() {
        super.postRegister();
        registerDefaultRecipes();
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
        
        

        for (MachineRecipe recipe : recipes) {
                
            if (recipe.getInput().length != 1) {
                break;
            }
            
            display.add(recipe.getInput()[0]);
            display.add(recipe.getOutput()[0]); 
        }
        
        return display;
	}

           
}
