package me.profelements.dynatech.items.electric.growthchambers;


import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class GrowthChamber extends AMachine {

    private ItemSetting<Boolean> exoticGardenIntegration = new ItemSetting<Boolean>(this, "exotic-garden-integration", true);

    public GrowthChamber(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemSetting(exoticGardenIntegration);
    }

    @Override
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
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.FLOWERING_AZALEA)}, new ItemStack[] {new ItemStack(Material.FLOWERING_AZALEA, 2), new ItemStack(Material.AZALEA_LEAVES, 2), new ItemStack(Material.FLOWERING_AZALEA_LEAVES, 3), new ItemStack(Material.OAK_LOG, 2)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.AZALEA)}, new ItemStack[] {new ItemStack(Material.AZALEA, 2), new ItemStack(Material.AZALEA_LEAVES, 3), new ItemStack(Material.FLOWERING_AZALEA_LEAVES, 2), new ItemStack(Material.OAK_LOG, 2)});
        registerRecipe(12, new ItemStack(Material.BIG_DRIPLEAF), new ItemStack(Material.BIG_DRIPLEAF, 2));
        registerRecipe(9, new ItemStack(Material.SMALL_DRIPLEAF), new ItemStack(Material.SMALL_DRIPLEAF, 2));
        registerRecipe(9, new ItemStack(Material.SPORE_BLOSSOM), new ItemStack(Material.SPORE_BLOSSOM, 2));
        registerRecipe(9, new ItemStack[] {new ItemStack(Material.MOSS_BLOCK)}, new ItemStack[]{new ItemStack(Material.MOSS_BLOCK, 2), new ItemStack(Material.MOSS_CARPET, 2)});
        registerRecipe(9, new ItemStack(Material.GLOW_LICHEN), new ItemStack(Material.GLOW_LICHEN, 3));
        registerRecipe(9, new ItemStack(Material.HANGING_ROOTS), new ItemStack(Material.HANGING_ROOTS, 3));

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
        registerRecipe(12, new ItemStack(Material.GLOW_BERRIES), new ItemStack(Material.GLOW_BERRIES, 3));

        registerRecipe(30, new ItemStack[] {new ItemStack(Material.OAK_SAPLING)}, new ItemStack[] {new ItemStack(Material.OAK_SAPLING , 3), new ItemStack(Material.OAK_LOG, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.BIRCH_SAPLING)}, new ItemStack[] {new ItemStack(Material.BIRCH_SAPLING , 3), new ItemStack(Material.BIRCH_LOG, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.SPRUCE_SAPLING)}, new ItemStack[] {new ItemStack(Material.SPRUCE_SAPLING , 3), new ItemStack(Material.SPRUCE_LOG, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.DARK_OAK_SAPLING)}, new ItemStack[] {new ItemStack(Material.DARK_OAK_SAPLING , 3), new ItemStack(Material.DARK_OAK_LOG, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.JUNGLE_SAPLING)}, new ItemStack[] {new ItemStack(Material.JUNGLE_SAPLING, 3), new ItemStack(Material.JUNGLE_LOG, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.ACACIA_SAPLING)}, new ItemStack[] {new ItemStack(Material.ACACIA_SAPLING, 3), new ItemStack(Material.ACACIA_LOG, 6)});
        registerRecipe(30, new ItemStack[] {new ItemStack(Material.MANGROVE_PROPAGULE)}, new ItemStack[] {new ItemStack(Material.MANGROVE_PROPAGULE, 3), new ItemStack(Material.MANGROVE_LOG, 6)});

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
                            ItemStack fruit = SlimefunItem.getById(sfItem.getId().replace("_BUSH", "")).getItem().clone();
                            MachineRecipe recipe = new MachineRecipe(21, new ItemStack[] {sfItem.getItem()}, new ItemStack[] {sfItem.getItem(), fruit});
                            
                            inv.consumeItem(inputSlot);

                            return recipe;
                        } else 
                        if (sfItem.getId().contains("_PLANT")) {
                            ItemStack fruit = SlimefunItem.getById(sfItem.getId().replace("_PLANT", "")) != null ? SlimefunItem.getById(sfItem.getId().replace("_PLANT", "")).getItem().clone() : SlimefunItem.getById(sfItem.getId().replace("_PLANT", "_ESSENCE")).getItem().clone();
                            MachineRecipe recipe = new MachineRecipe(12, new ItemStack[] {sfItem.getItem()}, new ItemStack[] {sfItem.getItem(), fruit});
                            
                            inv.consumeItem(inputSlot);

                            return recipe;
                        } else
                        if (sfItem.getId().contains("_SAPLING")) {
                            ItemStack fruit = SlimefunItem.getById(sfItem.getId().replace("_SAPLING", "")).getItem().clone();
                            fruit.setAmount(3);
                            MachineRecipe recipe = new MachineRecipe(30, new ItemStack[] {sfItem.getItem()}, new ItemStack[] {sfItem.getItem(), fruit});
                            
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
        return new ItemStack(Material.IRON_HOE);
    }

    @Override
    public String getMachineIdentifier() {
        return "GROWTH_CHAMBER";
    }

}
