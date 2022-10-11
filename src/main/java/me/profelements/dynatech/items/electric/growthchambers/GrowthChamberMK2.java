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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GrowthChamberMK2 extends AbstractElectricMachine {
   
    private static final int[] INPUT_SLOTS = new int[] {1,2,3,4,5,6,7};
    private static final int[] OUTPUT_SLOTS = new int[] {28,29,30,31,32,33,34,37,38,39,40,41,42,43,46,47,48,49,50,51,52};

    private static final int[] INPUT_BORDER_SLOTS = new int[] {0,8,9,10,11,12,14,15,16,17};
    private static final int[] OUTPUT_BORDER_SLOTS = new int[] {18,19,20,21,22,23,24,25,26,27,35,36,44,45,53};
    private static final int[] BACKGROUND_SLOTS = new int[] {}; 

    private static final ItemStack PROGRESS_ITEM = new ItemStack(Material.DIAMOND_HOE);


    private ItemSetting<Boolean> exoticGardenIntegration = new ItemSetting<Boolean>(this, "exotic-garden-integration", true);

    public GrowthChamberMK2(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemSetting(exoticGardenIntegration);
    }
    
    @Override
    public void postRegister() { 
        registerDefaultRecipes();
    }


    protected void registerDefaultRecipes() {

        registerRecipe(9, new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS, 9));
        registerRecipe(15, new ItemStack[] {new ItemStack(Material.MELON_SEEDS)}, new ItemStack[] {new ItemStack(Material.MELON , 3), new ItemStack(Material.MELON_SEEDS, 3)});
        registerRecipe(15, new ItemStack[] {new ItemStack(Material.PUMPKIN_SEEDS)}, new ItemStack[] {new ItemStack(Material.PUMPKIN , 3), new ItemStack(Material.PUMPKIN_SEEDS, 3)});
        registerRecipe(15, new ItemStack[] {new ItemStack(Material.BEETROOT_SEEDS)}, new ItemStack[] {new ItemStack(Material.BEETROOT , 9), new ItemStack(Material.BEETROOT_SEEDS, 6)});
        registerRecipe(12, new ItemStack[] {new ItemStack(Material.WHEAT_SEEDS)}, new ItemStack[] {new ItemStack(Material.WHEAT , 9), new ItemStack(Material.WHEAT_SEEDS, 6)});
        registerRecipe(9, new ItemStack(Material.APPLE), new ItemStack(Material.APPLE, 9));
        registerRecipe(9, new ItemStack(Material.BROWN_MUSHROOM), new ItemStack(Material.BROWN_MUSHROOM, 9));
        registerRecipe(9, new ItemStack(Material.RED_MUSHROOM), new ItemStack(Material.RED_MUSHROOM, 9));
        registerRecipe(9, new ItemStack[] {new ItemStack(Material.DEAD_BUSH)}, new ItemStack[] {new ItemStack(Material.DEAD_BUSH , 9), new ItemStack(Material.STICK, 6)});
        registerRecipe(9, new ItemStack(Material.GRASS), new ItemStack(Material.GRASS, 9));
        registerRecipe(12, new ItemStack(Material.TALL_GRASS), new ItemStack(Material.TALL_GRASS, 9));
        registerRecipe(9, new ItemStack(Material.FERN), new ItemStack(Material.FERN, 9));
        registerRecipe(12, new ItemStack(Material.LARGE_FERN), new ItemStack(Material.LARGE_FERN, 9));
        registerRecipe(9, new ItemStack(Material.VINE), new ItemStack(Material.VINE, 9));

        // Flowers
        registerRecipe(9, new ItemStack(Material.DANDELION), new ItemStack(Material.DANDELION, 9));
        registerRecipe(9, new ItemStack(Material.POPPY), new ItemStack(Material.POPPY, 3));
        registerRecipe(9, new ItemStack(Material.BLUE_ORCHID), new ItemStack(Material.BLUE_ORCHID, 9));
        registerRecipe(9, new ItemStack(Material.ALLIUM), new ItemStack(Material.ALLIUM, 9));
        registerRecipe(9, new ItemStack(Material.AZURE_BLUET), new ItemStack(Material.AZURE_BLUET, 9));
        registerRecipe(9, new ItemStack(Material.RED_TULIP), new ItemStack(Material.RED_TULIP, 9));
        registerRecipe(9, new ItemStack(Material.ORANGE_TULIP), new ItemStack(Material.ORANGE_TULIP, 9));
        registerRecipe(9, new ItemStack(Material.WHITE_TULIP), new ItemStack(Material.WHITE_TULIP, 9));
        registerRecipe(9, new ItemStack(Material.PINK_TULIP), new ItemStack(Material.PINK_TULIP, 9));
        registerRecipe(9, new ItemStack(Material.OXEYE_DAISY), new ItemStack(Material.OXEYE_DAISY, 9));
        registerRecipe(9, new ItemStack(Material.CORNFLOWER), new ItemStack(Material.CORNFLOWER, 9));
        registerRecipe(9, new ItemStack(Material.LILY_OF_THE_VALLEY), new ItemStack(Material.LILY_OF_THE_VALLEY, 9));
        registerRecipe(12, new ItemStack(Material.WITHER_ROSE), new ItemStack(Material.WITHER_ROSE, 6));
        registerRecipe(12, new ItemStack(Material.SUNFLOWER), new ItemStack(Material.SUNFLOWER, 6));
        registerRecipe(12, new ItemStack(Material.LILAC), new ItemStack(Material.LILAC, 6));
        registerRecipe(12, new ItemStack(Material.ROSE_BUSH), new ItemStack(Material.ROSE_BUSH, 6));
        registerRecipe(12, new ItemStack(Material.PEONY), new ItemStack(Material.PEONY, 6));

        registerRecipe(12, new ItemStack(Material.CARROT), new ItemStack(Material.CARROT, 9));
        registerRecipe(12, new ItemStack(Material.POTATO), new ItemStack(Material.POTATO, 9));
        registerRecipe(12, new ItemStack(Material.SWEET_BERRIES), new ItemStack(Material.SWEET_BERRIES, 9));
        registerRecipe(12, new ItemStack(Material.SUGAR_CANE), new ItemStack(Material.SUGAR_CANE, 9));
        registerRecipe(12, new ItemStack(Material.BAMBOO), new ItemStack(Material.BAMBOO, 9));
        registerRecipe(12, new ItemStack(Material.CACTUS), new ItemStack(Material.CACTUS, 9));

        registerRecipe(30, new ItemStack[] {new ItemStack(Material.OAK_SAPLING)}, new ItemStack[] {new ItemStack(Material.OAK_SAPLING , 9), new ItemStack(Material.OAK_LOG, 18), new ItemStack(Material.APPLE, 6), new ItemStack(Material.OAK_LEAVES, 9), new ItemStack(Material.STICK, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.BIRCH_SAPLING)}, new ItemStack[] {new ItemStack(Material.BIRCH_SAPLING , 9), new ItemStack(Material.BIRCH_LOG, 18), new ItemStack(Material.APPLE, 6), new ItemStack(Material.BIRCH_LEAVES, 9), new ItemStack(Material.STICK, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.SPRUCE_SAPLING)}, new ItemStack[] {new ItemStack(Material.SPRUCE_SAPLING , 9), new ItemStack(Material.SPRUCE_LOG, 18), new ItemStack(Material.APPLE, 6), new ItemStack(Material.SPRUCE_LEAVES, 9), new ItemStack(Material.STICK, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.DARK_OAK_SAPLING)}, new ItemStack[] {new ItemStack(Material.DARK_OAK_SAPLING , 9), new ItemStack(Material.DARK_OAK_LOG, 18), new ItemStack(Material.APPLE, 6), new ItemStack(Material.DARK_OAK_LEAVES, 9), new ItemStack(Material.STICK, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.JUNGLE_SAPLING)}, new ItemStack[] {new ItemStack(Material.JUNGLE_SAPLING, 9), new ItemStack(Material.JUNGLE_LOG, 18), new ItemStack(Material.APPLE, 6), new ItemStack(Material.JUNGLE_LEAVES, 9), new ItemStack(Material.STICK, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.ACACIA_SAPLING)}, new ItemStack[] {new ItemStack(Material.ACACIA_SAPLING, 9), new ItemStack(Material.ACACIA_LOG, 18), new ItemStack(Material.APPLE, 6), new ItemStack(Material.ACACIA_LEAVES, 9), new ItemStack(Material.STICK, 6)});
        
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
    public int[] getInputSlots() {
        return INPUT_SLOTS; 
    }

    @Override
    public int[] getOutputSlots() {
        return OUTPUT_SLOTS; 
    }

    @Override
    public ItemStack getProgressBar() {
        return PROGRESS_ITEM;   
    }
    
    @Override
    protected int getProgressSlot() {
        return 13;
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


}
