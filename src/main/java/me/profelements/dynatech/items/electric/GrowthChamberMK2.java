package me.profelements.dynatech.items.electric;

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

    private Set<UUID> enabledPlayers = new HashSet<>();

    private static int[] BORDER = new int[] {};
    private static int[] BORDER_IN = new int[] {9,10,11,12,14,15,16,17};
    private static int[] BORDER_OUT = new int[] {18,19,20,21,22,23,24,25,26};

    public GrowthChamberMK2(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(20, new ItemStack(Material.WHEAT_SEEDS), new ItemStack(Material.WHEAT, 16));
        registerRecipe(20, new ItemStack(Material.BEETROOT_SEEDS), new ItemStack(Material.BEETROOT, 16));
        registerRecipe(20, new ItemStack(Material.PUMPKIN_SEEDS), new ItemStack(Material.PUMPKIN, 8));
        registerRecipe(20, new ItemStack(Material.MELON_SEEDS), new ItemStack(Material.MELON, 8));
        registerRecipe(20, new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS, 16));

        registerRecipe(25, new ItemStack(Material.NETHER_WART), new ItemStack(Material.NETHER_WART, 16));

        registerRecipe(30, new ItemStack(Material.CARROT), new ItemStack(Material.CARROT, 12));
        registerRecipe(30, new ItemStack(Material.POTATO), new ItemStack(Material.POTATO, 12));
        registerRecipe(30, new ItemStack(Material.SWEET_BERRIES), new ItemStack(Material.SWEET_BERRIES, 12));

        registerRecipe(35, new ItemStack(Material.KELP), new ItemStack(Material.KELP, 8));
        registerRecipe(35, new ItemStack(Material.SUGAR_CANE), new ItemStack(Material.SUGAR_CANE, 8));
        registerRecipe(35, new ItemStack(Material.BAMBOO), new ItemStack(Material.BAMBOO, 8));
        registerRecipe(35, new ItemStack(Material.CACTUS), new ItemStack(Material.CACTUS, 8));

        registerRecipe(40, new ItemStack[] {new ItemStack(Material.OAK_SAPLING)}, new ItemStack[] {new ItemStack(Material.OAK_SAPLING , 12), new ItemStack(Material.OAK_LOG, 24)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.BIRCH_SAPLING)}, new ItemStack[] {new ItemStack(Material.BIRCH_SAPLING , 12), new ItemStack(Material.BIRCH_LOG, 24)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.SPRUCE_SAPLING)}, new ItemStack[] {new ItemStack(Material.SPRUCE_SAPLING , 12), new ItemStack(Material.SPRUCE_LOG, 24)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.DARK_OAK_SAPLING)}, new ItemStack[] {new ItemStack(Material.DARK_OAK_SAPLING , 12), new ItemStack(Material.DARK_OAK_LOG, 24)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.JUNGLE_SAPLING)}, new ItemStack[] {new ItemStack(Material.JUNGLE_SAPLING, 12), new ItemStack(Material.JUNGLE_LOG, 24)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.ACACIA_SAPLING)}, new ItemStack[] {new ItemStack(Material.ACACIA_SAPLING, 12), new ItemStack(Material.ACACIA_LOG, 24)});

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
                            fruit.setAmount(12);
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
        return new int[] {0,1,2,3,4,5,6,7,8};
    }
    @Override
    public int[] getOutputSlots() {
        return new int[] {27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53};
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
