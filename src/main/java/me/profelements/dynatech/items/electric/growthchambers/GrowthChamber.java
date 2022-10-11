package me.profelements.dynatech.items.electric.growthchambers;

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
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.abstracts.AbstractElectricMachine;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GrowthChamber extends AbstractElectricMachine {

    private static final int[] INPUT_SLOTS = new int[] { 19, 20 };
    private static final int[] OUTPUT_SLOTS = new int[] { 24, 25 };

    private static final int[] INPUT_BORDER_SLOTS = new int[] { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
    private static final int[] OUTPUT_BORDER_SLOTS = new int[] {14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
    private static final int[] BACKGROUND_SLOTS = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43, 44 }; 

    private static final ItemStack PROGRESS_ITEM = new ItemStack(Material.IRON_HOE);

    private ItemSetting<Boolean> exoticGardenIntegration = new ItemSetting<Boolean>(this, "exotic-garden-integration", true);

    public GrowthChamber(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemSetting(exoticGardenIntegration);
    }

    @Override
    public void postRegister() {
        registerDefaultRecipes();
    } 


    protected void registerDefaultRecipes() {

        registerRecipe(9, new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS, 3));
        registerRecipe(15, new ItemStack[] {new ItemStack(Material.MELON_SEEDS)}, new ItemStack[] {new ItemStack(Material.MELON , 1), new ItemStack(Material.MELON_SEEDS, 1)});
        registerRecipe(15, new ItemStack[] {new ItemStack(Material.PUMPKIN_SEEDS)}, new ItemStack[] {new ItemStack(Material.PUMPKIN , 1), new ItemStack(Material.PUMPKIN_SEEDS, 1)});
        registerRecipe(15, new ItemStack[] {new ItemStack(Material.BEETROOT_SEEDS)}, new ItemStack[] {new ItemStack(Material.BEETROOT , 3), new ItemStack(Material.BEETROOT_SEEDS, 2)});
        registerRecipe(12, new ItemStack[] {new ItemStack(Material.WHEAT_SEEDS)}, new ItemStack[] {new ItemStack(Material.WHEAT , 3), new ItemStack(Material.WHEAT_SEEDS, 2)});
        registerRecipe(9, new ItemStack(Material.APPLE), new ItemStack(Material.APPLE, 3));
        registerRecipe(9, new ItemStack(Material.BROWN_MUSHROOM), new ItemStack(Material.BROWN_MUSHROOM, 3));
        registerRecipe(9, new ItemStack(Material.RED_MUSHROOM), new ItemStack(Material.RED_MUSHROOM, 3));
        registerRecipe(9, new ItemStack[] {new ItemStack(Material.DEAD_BUSH)}, new ItemStack[] {new ItemStack(Material.DEAD_BUSH , 3), new ItemStack(Material.STICK, 2)});
        registerRecipe(9, new ItemStack(Material.GRASS), new ItemStack(Material.GRASS, 3));
        registerRecipe(12, new ItemStack(Material.TALL_GRASS), new ItemStack(Material.TALL_GRASS, 3));
        registerRecipe(9, new ItemStack(Material.FERN), new ItemStack(Material.FERN, 3));
        registerRecipe(12, new ItemStack(Material.LARGE_FERN), new ItemStack(Material.LARGE_FERN, 3));
        registerRecipe(9, new ItemStack(Material.VINE), new ItemStack(Material.VINE, 3));

    // Flowers
        registerRecipe(9, new ItemStack(Material.DANDELION), new ItemStack(Material.DANDELION, 3));
        registerRecipe(9, new ItemStack(Material.POPPY), new ItemStack(Material.POPPY, 3));
        registerRecipe(9, new ItemStack(Material.BLUE_ORCHID), new ItemStack(Material.BLUE_ORCHID, 3));
        registerRecipe(9, new ItemStack(Material.ALLIUM), new ItemStack(Material.ALLIUM, 3));
        registerRecipe(9, new ItemStack(Material.AZURE_BLUET), new ItemStack(Material.AZURE_BLUET, 3));
        registerRecipe(9, new ItemStack(Material.RED_TULIP), new ItemStack(Material.RED_TULIP, 3));
        registerRecipe(9, new ItemStack(Material.ORANGE_TULIP), new ItemStack(Material.ORANGE_TULIP, 3));
        registerRecipe(9, new ItemStack(Material.WHITE_TULIP), new ItemStack(Material.WHITE_TULIP, 3));
        registerRecipe(9, new ItemStack(Material.PINK_TULIP), new ItemStack(Material.PINK_TULIP, 3));
        registerRecipe(9, new ItemStack(Material.OXEYE_DAISY), new ItemStack(Material.OXEYE_DAISY, 3));
        registerRecipe(9, new ItemStack(Material.CORNFLOWER), new ItemStack(Material.CORNFLOWER, 3));
        registerRecipe(9, new ItemStack(Material.LILY_OF_THE_VALLEY), new ItemStack(Material.LILY_OF_THE_VALLEY, 3));
        registerRecipe(12, new ItemStack(Material.WITHER_ROSE), new ItemStack(Material.WITHER_ROSE, 2));
        registerRecipe(12, new ItemStack(Material.SUNFLOWER), new ItemStack(Material.SUNFLOWER, 2));
        registerRecipe(12, new ItemStack(Material.LILAC), new ItemStack(Material.LILAC, 2));
        registerRecipe(12, new ItemStack(Material.ROSE_BUSH), new ItemStack(Material.ROSE_BUSH, 2));
        registerRecipe(12, new ItemStack(Material.PEONY), new ItemStack(Material.PEONY, 2));

        registerRecipe(12, new ItemStack(Material.CARROT), new ItemStack(Material.CARROT, 3));
        registerRecipe(12, new ItemStack(Material.POTATO), new ItemStack(Material.POTATO, 3));
        registerRecipe(12, new ItemStack(Material.SWEET_BERRIES), new ItemStack(Material.SWEET_BERRIES, 3));
        registerRecipe(12, new ItemStack(Material.SUGAR_CANE), new ItemStack(Material.SUGAR_CANE, 3));
        registerRecipe(12, new ItemStack(Material.BAMBOO), new ItemStack(Material.BAMBOO, 3));
        registerRecipe(12, new ItemStack(Material.CACTUS), new ItemStack(Material.CACTUS, 3));

        registerRecipe(30, new ItemStack[] {new ItemStack(Material.OAK_SAPLING)}, new ItemStack[] {new ItemStack(Material.OAK_SAPLING , 3), new ItemStack(Material.OAK_LOG, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.BIRCH_SAPLING)}, new ItemStack[] {new ItemStack(Material.BIRCH_SAPLING , 3), new ItemStack(Material.BIRCH_LOG, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.SPRUCE_SAPLING)}, new ItemStack[] {new ItemStack(Material.SPRUCE_SAPLING , 3), new ItemStack(Material.SPRUCE_LOG, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.DARK_OAK_SAPLING)}, new ItemStack[] {new ItemStack(Material.DARK_OAK_SAPLING , 3), new ItemStack(Material.DARK_OAK_LOG, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.JUNGLE_SAPLING)}, new ItemStack[] {new ItemStack(Material.JUNGLE_SAPLING, 3), new ItemStack(Material.JUNGLE_LOG, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.ACACIA_SAPLING)}, new ItemStack[] {new ItemStack(Material.ACACIA_SAPLING, 3), new ItemStack(Material.ACACIA_LOG, 6)});
        
        if (DynaTech.isExoticGardenInstalled() && exoticGardenIntegration.getValue()) {
            for (SlimefunItem item : Slimefun.getRegistry().getEnabledSlimefunItems()) {
                if (item.getId().contains("_BUSH") || item.getId().contains("_PLANT") || item.getId().contains("_SAPLING")) {
                    SlimefunItem fruit = SlimefunItem.getById(item.getId().split("_")[0]);
                    SlimefunItem essence = SlimefunItem.getById(item.getId().split("_")[0] + "_ESSENCE");
                    if ( fruit != null) { 
                       registerRecipe(new MachineRecipe(30, new ItemStack[] {item.getItem()}, new ItemStack[] { item.getItem(), fruit.getItem()}));
                    }

                    if (essence != null) { 
                       registerRecipe(new MachineRecipe(30, new ItemStack[] {item.getItem()}, new ItemStack[] { item.getItem(), essence.getItem()}));
                    }
                }
            }
        }
    }
    

    @Override
	public List<ItemStack> getDisplayRecipes() {
		List<ItemStack> display = new ArrayList<>(); 
        for (MachineRecipe recipe : recipes) {
           display.add(recipe.getInput()[0]);
           if (recipe.getOutput().length > 1) {
            display.add(recipe.getOutput()[1]);
           } else {
            display.add(recipe.getOutput()[0]);
           }
        }
        return display;
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
	protected ItemStack getProgressBar() {
		return PROGRESS_ITEM;
	}

}
