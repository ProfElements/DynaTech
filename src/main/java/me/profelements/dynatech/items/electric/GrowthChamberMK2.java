package me.profelements.dynatech.items.electric;

import java.util.ArrayList;
import java.util.List;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.electric.abstracts.AMachine;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;

public class GrowthChamberMK2 extends AMachine {
    
    private static int[] BORDER = new int[] {};
    private static int[] BORDER_IN = new int[] {0,8,9,10,11,12,14,15,16,17};
    private static int[] BORDER_OUT = new int[] {18,19,20,21,22,23,24,25,26,27,35,36,44,45,53};
    
    private ItemSetting<Boolean> exoticGardenIntegration = new ItemSetting<Boolean>("exotic-garden-integration", true);

    public GrowthChamberMK2(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemSetting(exoticGardenIntegration);
    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(20, new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS, 8));
        registerRecipe(20, new ItemStack[] {new ItemStack(Material.MELON_SEEDS)}, new ItemStack[] {new ItemStack(Material.MELON , 6), new ItemStack(Material.MELON_SEEDS, 3)});
        registerRecipe(20, new ItemStack[] {new ItemStack(Material.PUMPKIN_SEEDS)}, new ItemStack[] {new ItemStack(Material.PUMPKIN , 6), new ItemStack(Material.PUMPKIN_SEEDS, 3)});
        registerRecipe(20, new ItemStack[] {new ItemStack(Material.BEETROOT_SEEDS)}, new ItemStack[] {new ItemStack(Material.BEETROOT , 12), new ItemStack(Material.BEETROOT_SEEDS, 3)});
        registerRecipe(20, new ItemStack[] {new ItemStack(Material.WHEAT_SEEDS)}, new ItemStack[] {new ItemStack(Material.WHEAT , 12), new ItemStack(Material.WHEAT_SEEDS, 3)});
        registerRecipe(20, new ItemStack(Material.APPLE), new ItemStack(Material.APPLE, 3));
        registerRecipe(20, new ItemStack(Material.BROWN_MUSHROOM), new ItemStack(Material.BROWN_MUSHROOM, 6));
        registerRecipe(20, new ItemStack(Material.RED_MUSHROOM), new ItemStack(Material.RED_MUSHROOM, 6));
        registerRecipe(20, new ItemStack(Material.CRIMSON_ROOTS), new ItemStack(Material.CRIMSON_ROOTS, 6));
        registerRecipe(20, new ItemStack(Material.WARPED_ROOTS), new ItemStack(Material.WARPED_ROOTS, 6));
        registerRecipe(20, new ItemStack(Material.NETHER_SPROUTS), new ItemStack(Material.NETHER_SPROUTS, 6));
        registerRecipe(20, new ItemStack(Material.LILY_PAD), new ItemStack(Material.LILY_PAD, 6));
        registerRecipe(20, new ItemStack(Material.VINE), new ItemStack(Material.VINE, 6));
        registerRecipe(20, new ItemStack(Material.SEA_PICKLE), new ItemStack(Material.SEA_PICKLE, 6));
        registerRecipe(20, new ItemStack(Material.SEAGRASS), new ItemStack(Material.SEAGRASS, 6));

        registerRecipe(25, new ItemStack(Material.NETHER_WART), new ItemStack(Material.NETHER_WART, 12));
        registerRecipe(25, new ItemStack(Material.WEEPING_VINES), new ItemStack(Material.WEEPING_VINES, 6));
        registerRecipe(25, new ItemStack(Material.TWISTING_VINES), new ItemStack(Material.TWISTING_VINES, 6));

        registerRecipe(30, new ItemStack(Material.CARROT), new ItemStack(Material.CARROT, 9));
        registerRecipe(30, new ItemStack(Material.POTATO), new ItemStack(Material.POTATO, 9));
        registerRecipe(30, new ItemStack(Material.POISONOUS_POTATO), new ItemStack(Material.POISONOUS_POTATO, 3));
        registerRecipe(30, new ItemStack(Material.SWEET_BERRIES), new ItemStack(Material.SWEET_BERRIES, 9));
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.CHORUS_FLOWER)}, new ItemStack[] {new ItemStack(Material.CHORUS_FLOWER , 2), new ItemStack(Material.CHORUS_FRUIT, 6)});

        registerRecipe(35, new ItemStack(Material.KELP), new ItemStack(Material.KELP, 6));
        registerRecipe(35, new ItemStack(Material.SUGAR_CANE), new ItemStack(Material.SUGAR_CANE, 6));
        registerRecipe(35, new ItemStack(Material.BAMBOO), new ItemStack(Material.BAMBOO, 6));
        registerRecipe(35, new ItemStack(Material.CACTUS), new ItemStack(Material.CACTUS, 6));
       
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.OAK_SAPLING)}, new ItemStack[] {new ItemStack(Material.OAK_SAPLING , 9), new ItemStack(Material.OAK_LOG, 18), new ItemStack(Material.APPLE, 6), new ItemStack(Material.OAK_LEAVES, 9)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.BIRCH_SAPLING)}, new ItemStack[] {new ItemStack(Material.BIRCH_SAPLING , 9), new ItemStack(Material.BIRCH_LOG, 18), new ItemStack(Material.APPLE, 6), new ItemStack(Material.BIRCH_LEAVES, 9)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.SPRUCE_SAPLING)}, new ItemStack[] {new ItemStack(Material.SPRUCE_SAPLING , 9), new ItemStack(Material.SPRUCE_LOG, 18), new ItemStack(Material.APPLE, 6), new ItemStack(Material.SPRUCE_LEAVES, 9)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.DARK_OAK_SAPLING)}, new ItemStack[] {new ItemStack(Material.DARK_OAK_SAPLING , 9), new ItemStack(Material.DARK_OAK_LOG, 18), new ItemStack(Material.APPLE, 6), new ItemStack(Material.DARK_OAK_LEAVES, 9)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.JUNGLE_SAPLING)}, new ItemStack[] {new ItemStack(Material.JUNGLE_SAPLING, 9), new ItemStack(Material.JUNGLE_LOG, 18), new ItemStack(Material.APPLE, 6), new ItemStack(Material.JUNGLE_LEAVES, 9)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.ACACIA_SAPLING)}, new ItemStack[] {new ItemStack(Material.ACACIA_SAPLING, 9), new ItemStack(Material.ACACIA_LOG, 18), new ItemStack(Material.APPLE, 6), new ItemStack(Material.ACACIA_LEAVES, 9)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.CRIMSON_FUNGUS)}, new ItemStack[] {new ItemStack(Material.CRIMSON_FUNGUS, 6), new ItemStack(Material.CRIMSON_STEM, 18), new ItemStack(Material.SHROOMLIGHT, 6), new ItemStack(Material.NETHER_WART_BLOCK, 9)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.WARPED_FUNGUS)}, new ItemStack[] {new ItemStack(Material.WARPED_FUNGUS, 6), new ItemStack(Material.WARPED_STEM, 18), new ItemStack(Material.SHROOMLIGHT, 6), new ItemStack(Material.WARPED_WART_BLOCK, 9)});

    }

    @Override
    public MachineRecipe findNextRecipe(BlockMenu inv) {
        if (DynaTech.isExoticGardenInstalled() && exoticGardenIntegration.getValue()) {
            for (int inputSlot : getInputSlots()) {
                ItemStack item = inv.getItemInSlot(inputSlot);
                if (item != null && SlimefunItem.getByItem(item) != null) {
                    SlimefunItem sfItem = SlimefunItem.getByItem(item);
                    if (sfItem.getId().contains("_BUSH") || sfItem.getId().contains("_PLANT") || sfItem.getId().contains("_SAPLING")) {
                        if (sfItem.getId().contains("_BUSH")) {
                            ItemStack fruit = SlimefunItem.getByID(sfItem.getId().replace("_BUSH", "")).getItem();
                            MachineRecipe recipe = new MachineRecipe(20, new ItemStack[] {sfItem.getItem()}, new ItemStack[] {sfItem.getItem(), fruit});

                            inv.consumeItem(inputSlot);

                            return recipe;
                        } else 
                        if (sfItem.getId().contains("_PLANT")) {
                            ItemStack fruit = SlimefunItem.getByID(sfItem.getId().replace("_PLANT", "")) != null ? SlimefunItem.getByID(sfItem.getId().replace("_PLANT", "")).getItem() : SlimefunItem.getByID(sfItem.getId().replace("_PLANT", "_ESSENCE")).getItem();
                            MachineRecipe recipe = new MachineRecipe(10, new ItemStack[] {sfItem.getItem()}, new ItemStack[] {sfItem.getItem(), fruit});

                            inv.consumeItem(inputSlot);

                            return recipe;
                        } else
                        if (sfItem.getId().contains("_SAPLING")) {
                            ItemStack fruit = SlimefunItem.getByID(sfItem.getId().replace("_SAPLING", "")).getItem();
                            fruit.setAmount(9);
                            MachineRecipe recipe = new MachineRecipe(60, new ItemStack[] {sfItem.getItem()}, new ItemStack[] {sfItem.getItem(), fruit});

                            inv.consumeItem(inputSlot);

                            return recipe;
                        }          

                    }
                }
            }
        }
        return super.findNextRecipe(inv);

    } 

    @Override
    public boolean isGraphical() {
        return true;
    }
    
    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER_MK2";
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
        return new int[] {1,2,3,4,5,6,7};
    }
    @Override
    public int[] getOutputSlots() {
        return new int[] {28,29,30,31,32,33,34,37,38,39,40,41,42,43,46,47,48,49,50,51,52};
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.IRON_HOE);
    }
    
    @Override
    public int getProgressBarSlot() {
        return 13;
    }
    
}
