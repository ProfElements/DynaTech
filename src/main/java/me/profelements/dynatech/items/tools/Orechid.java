package me.profelements.dynatech.items.tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import io.github.mooy1.infinitylib.PluginUtils;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.collections.RandomizedSet;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.electric.abstracts.AMachine;

//I feel like this can somehow be much better :O

public class Orechid extends AMachine implements RecipeDisplayItem {

    private static final RandomizedSet<Material> OVERWORLD_ORES = new RandomizedSet<Material>();
    private static final RandomizedSet<Material> NETHER_ORES = new RandomizedSet<Material>();
    //private static final List<Material> END_ORES = new ArrayList<>();

    //Somehow setup a RecipeType for this for people to use in addons
    //private static final RecipeType ORECHID = new RecipeType(new NamespacedKey(DynaTech.getInstance(), "dt_orechid"), new CustomItem(Material.WITHER_ROSE, "&bConverted with the Orechid"));

    public Orechid(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void tick(Block b) {
        if (PluginUtils.getCurrentTick() % 10 == 0) {
            for (BlockFace relative : BlockFace.values()) {
                if (getCharge(b.getLocation()) < getEnergyConsumption()) {
                    break;
                }
    
                if (relative == BlockFace.UP || relative == BlockFace.DOWN) {
                    continue;
                }
                
                Block relBlock = b.getRelative(relative);
    
                if (relBlock.getType() == Material.STONE) {
                    DynaTech.runSync(()-> relBlock.setType(getOverWorldOres().getRandom()));
                    removeCharge(b.getLocation(), getEnergyConsumption());
    
                } else if (relBlock.getType() == Material.NETHERRACK) {
                    DynaTech.runSync(()-> relBlock.setType(getNetherOres().getRandom()));
                    removeCharge(b.getLocation(), getEnergyConsumption());
    
                }
            }        
        }
    }



    private static final RandomizedSet<Material> getOverWorldOres() {
        OVERWORLD_ORES.add(Material.COAL_ORE, 3);
        OVERWORLD_ORES.add(Material.IRON_ORE, 2);
        OVERWORLD_ORES.add(Material.GOLD_ORE, 2);
        OVERWORLD_ORES.add(Material.DIAMOND_ORE, 1);
        OVERWORLD_ORES.add(Material.EMERALD_ORE, 1);
        OVERWORLD_ORES.add(Material.REDSTONE_ORE, 3);
        OVERWORLD_ORES.add(Material.LAPIS_ORE, 3);

        return OVERWORLD_ORES;
    }

    private static final RandomizedSet<Material> getNetherOres() {
        NETHER_ORES.add(Material.NETHER_QUARTZ_ORE, 3);
        NETHER_ORES.add(Material.NETHER_GOLD_ORE, 3);
        NETHER_ORES.add(Material.ANCIENT_DEBRIS, 1);
        NETHER_ORES.add(Material.BASALT, 5);
        NETHER_ORES.add(Material.BLACKSTONE, 5);

        return NETHER_ORES;
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayList = new ArrayList<>();

        for (Material m : getOverWorldOres()) {
            displayList.add(new ItemStack(Material.STONE));
            displayList.add(new ItemStack(m));
        }

        for (Material m : getNetherOres()) {
            displayList.add(new ItemStack(Material.NETHERRACK));
            displayList.add(new ItemStack(m));
        }

        return displayList;
    }

    @Override
    public boolean isGraphical() {
        return false;
    }

    @Override
    public String getMachineIdentifier() {
        return "ORECHID";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.WITHER_ROSE);
    }
    
}
