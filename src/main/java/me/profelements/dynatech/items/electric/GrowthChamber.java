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

public class GrowthChamber extends AMachine {

    public GrowthChamber(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {

        registerRecipe(20, new ItemStack(Material.WHEAT_SEEDS), new ItemStack(Material.WHEAT, 4));
        registerRecipe(20, new ItemStack(Material.BEETROOT_SEEDS), new ItemStack(Material.BEETROOT, 4));
        registerRecipe(20, new ItemStack(Material.PUMPKIN_SEEDS), new ItemStack(Material.PUMPKIN, 2));
        registerRecipe(20, new ItemStack(Material.MELON_SEEDS), new ItemStack(Material.MELON, 2));
        registerRecipe(20, new ItemStack(Material.COCOA_BEANS), new ItemStack(Material.COCOA_BEANS, 4));

        registerRecipe(25, new ItemStack(Material.NETHER_WART), new ItemStack(Material.NETHER_WART, 4));

        registerRecipe(30, new ItemStack(Material.CARROT), new ItemStack(Material.CARROT, 3));
        registerRecipe(30, new ItemStack(Material.POTATO), new ItemStack(Material.POTATO, 3));
        registerRecipe(30, new ItemStack(Material.SWEET_BERRIES), new ItemStack(Material.SWEET_BERRIES, 3));

        registerRecipe(35, new ItemStack(Material.KELP), new ItemStack(Material.KELP, 2));
        registerRecipe(35, new ItemStack(Material.SUGAR_CANE), new ItemStack(Material.SUGAR_CANE, 2));
        registerRecipe(35, new ItemStack(Material.BAMBOO), new ItemStack(Material.BAMBOO, 2));
        registerRecipe(35, new ItemStack(Material.CACTUS), new ItemStack(Material.CACTUS, 2));

        registerRecipe(40, new ItemStack[] {new ItemStack(Material.OAK_SAPLING)}, new ItemStack[] {new ItemStack(Material.OAK_SAPLING , 3), new ItemStack(Material.OAK_LOG, 6)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.BIRCH_SAPLING)}, new ItemStack[] {new ItemStack(Material.BIRCH_SAPLING , 3), new ItemStack(Material.BIRCH_LOG, 6)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.SPRUCE_SAPLING)}, new ItemStack[] {new ItemStack(Material.SPRUCE_SAPLING , 3), new ItemStack(Material.SPRUCE_LOG, 6)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.DARK_OAK_SAPLING)}, new ItemStack[] {new ItemStack(Material.DARK_OAK_SAPLING , 3), new ItemStack(Material.DARK_OAK_LOG, 6)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.JUNGLE_SAPLING)}, new ItemStack[] {new ItemStack(Material.JUNGLE_SAPLING, 3), new ItemStack(Material.JUNGLE_LOG, 6)});
        registerRecipe(40, new ItemStack[] {new ItemStack(Material.ACACIA_SAPLING)}, new ItemStack[] {new ItemStack(Material.ACACIA_SAPLING, 3), new ItemStack(Material.ACACIA_LOG, 6)});

    }
    
    @Override
    public MachineRecipe findNextRecipe(BlockMenu inv) {
        if (DynaTech.isIsExoticGardenInstalled()) {
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
                            fruit.setAmount(3);
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
    public ItemStack getProgressBar() {
        return new ItemStack(Material.WOODEN_HOE);
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER";
    }



}
