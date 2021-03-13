package me.profelements.dynatech.items.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.electric.abstracts.AMachine;

//I feel like this can somehow be much better :O

public class Orechid extends AMachine implements RecipeDisplayItem {

    private static final List<Material> OVERWORLD_ORES = new ArrayList<>();
    private static final List<Material> NETHER_ORES = new ArrayList<>();
    //private static final List<Material> END_ORES = new ArrayList<>();

    //Somehow setup a RecipeType for this for people to use in addons
    //private static final RecipeType ORECHID = new RecipeType(new NamespacedKey(DynaTech.getInstance(), "dt_orechid"), new CustomItem(Material.WITHER_ROSE, "&bConverted with the Orechid"));

    public Orechid(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    @Override
    public void tick(Block b) {

        if (getCharge(b.getLocation()) < getEnergyConsumption()) {
            return;
        }

        for (BlockFace relative : BlockFace.values()) {
            if (relative == BlockFace.UP || relative == BlockFace.DOWN) {
                continue;
            }

            Block relBlock = b.getRelative(relative);

            if (relBlock.getType() == Material.STONE) {
                int overworldOreIndex = ThreadLocalRandom.current().nextInt(getOverWorldOres().size() - 1);
                DynaTech.runSync(()-> relBlock.setType(getOverWorldOres().get(overworldOreIndex)));

                removeCharge(b.getLocation(), getEnergyConsumption());

            } else if (relBlock.getType() == Material.NETHERRACK) {
                int netherOreIndex = ThreadLocalRandom.current().nextInt(getNetherOres().size() - 1);
                DynaTech.runSync(()-> relBlock.setType(getNetherOres().get(netherOreIndex)));

                removeCharge(b.getLocation(), getEnergyConsumption());

            }
        }
    }



    private static final List<Material> getOverWorldOres() {
        OVERWORLD_ORES.add(Material.COAL_ORE);
        OVERWORLD_ORES.add(Material.IRON_ORE);
        OVERWORLD_ORES.add(Material.GOLD_ORE);
        OVERWORLD_ORES.add(Material.DIAMOND_ORE);
        OVERWORLD_ORES.add(Material.EMERALD_ORE);
        OVERWORLD_ORES.add(Material.REDSTONE_ORE);
        OVERWORLD_ORES.add(Material.LAPIS_ORE);

        return OVERWORLD_ORES;
    }

    private static final List<Material> getNetherOres() {
        NETHER_ORES.add(Material.NETHER_QUARTZ_ORE);
        NETHER_ORES.add(Material.NETHER_GOLD_ORE);
        NETHER_ORES.add(Material.ANCIENT_DEBRIS);
        NETHER_ORES.add(Material.BASALT);
        NETHER_ORES.add(Material.BLACKSTONE);

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
