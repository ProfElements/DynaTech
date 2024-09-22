package me.profelements.dynatech.items.electric.machines;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.RandomizedSet;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.abstracts.AbstractElectricTicker;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;

//I feel like this can somehow be much better :O (review)

public class Orechid extends AbstractElectricTicker implements RecipeDisplayItem {

    public static final Map<Material, RandomizedSet<ItemStack>> oreMap = new EnumMap<>(Material.class);
    // private static final List<Material> END_ORES = new ArrayList<>();

    private static final Set<BlockFace> ignoredFaces = new HashSet<>();

    public Orechid(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        ignoredFaces.add(BlockFace.UP);
        ignoredFaces.add(BlockFace.DOWN);
        ignoredFaces.add(BlockFace.NORTH_NORTH_EAST);
        ignoredFaces.add(BlockFace.NORTH_NORTH_WEST);
        ignoredFaces.add(BlockFace.SOUTH_SOUTH_EAST);
        ignoredFaces.add(BlockFace.SOUTH_SOUTH_WEST);
        ignoredFaces.add(BlockFace.WEST_NORTH_WEST);
        ignoredFaces.add(BlockFace.WEST_SOUTH_WEST);
        ignoredFaces.add(BlockFace.EAST_SOUTH_EAST);
        ignoredFaces.add(BlockFace.EAST_NORTH_EAST);

        registerDefaultOres();
    }

    @Override
    protected boolean checkTickPreconditions(Block b) {
        return true;
    }

    @Override
    public void tick(Block b, SlimefunItem sfItem) {
        if (DynaTech.getInstance() == null) {
            return;
        }

        if (DynaTech.getInstance().getTickInterval() % 10 == 0) {
            for (BlockFace relative : BlockFace.values()) {
                if (getCharge(b.getLocation()) < getEnergyConsumption()) {
                    break;
                }

                if (ignoredFaces.contains(relative)) {
                    continue;
                }

                Block relBlock = b.getRelative(relative);

                if (oreMap.containsKey(relBlock.getType())) {
                    RandomizedSet<ItemStack> set = oreMap.get(relBlock.getType());
                    if (set == null) {
                        return;
                    }

                    ItemStack item = set.getRandom();

                    SlimefunItem sfi = SlimefunItem.getByItem(item);

                    DynaTech.runSync(() -> {
                        relBlock.setType(item.getType());
                        if (sfi != null) {
                            BlockStorage.addBlockInfo(relBlock, "id", sfi.getId());
                        }
                    });

                    removeCharge(b.getLocation(), getEnergyConsumption());
                }
            }
        }
    }

    public static void registerOre(@Nonnull Material from, @Nonnull Material result, float weight) {
        oreMap.computeIfAbsent(from, k -> new RandomizedSet<>()).add(new ItemStack(result), weight);
    }

    /**
     * For Slimefun items
     */
    public static void registerOre(@Nonnull Material from, @Nonnull SlimefunItemStack result, float weight) {
        oreMap.computeIfAbsent(from, k -> new RandomizedSet<>()).add(result, weight);
    }

    private static void registerDefaultOres() {
        // registerOre(Material.STONE, Material.COAL_ORE, 3);
        // registerOre(Material.STONE, Material.IRON_ORE, 2);
        // registerOre(Material.STONE, Material.GOLD_ORE, 2);
        // registerOre(Material.STONE, Material.DIAMOND_ORE, 1);
        // registerOre(Material.STONE, Material.EMERALD_ORE, 1);
        // registerOre(Material.STONE, Material.REDSTONE_ORE, 3);
        // registerOre(Material.STONE, Material.LAPIS_ORE, 3);
        // registerOre(Material.STONE, Material.COPPER_ORE, 3);
        //
        // registerOre(Material.NETHERRACK, Material.NETHER_QUARTZ_ORE, 3);
        // registerOre(Material.NETHERRACK, Material.NETHER_GOLD_ORE, 3);
        // registerOre(Material.NETHERRACK, Material.ANCIENT_DEBRIS, 1);
        // registerOre(Material.NETHERRACK, Material.BASALT, 5);
        // registerOre(Material.NETHERRACK, Material.BLACKSTONE, 5);
        //
        // registerOre(Material.DEEPSLATE, Material.DEEPSLATE_COAL_ORE, 3);
        // registerOre(Material.DEEPSLATE, Material.DEEPSLATE_IRON_ORE, 2);
        // registerOre(Material.DEEPSLATE, Material.DEEPSLATE_GOLD_ORE, 2);
        // registerOre(Material.DEEPSLATE, Material.DEEPSLATE_DIAMOND_ORE, 1);
        // registerOre(Material.DEEPSLATE, Material.DEEPSLATE_EMERALD_ORE, 1);
        // registerOre(Material.DEEPSLATE, Material.DEEPSLATE_REDSTONE_ORE, 3);
        // registerOre(Material.DEEPSLATE, Material.DEEPSLATE_LAPIS_ORE, 3);
        // registerOre(Material.DEEPSLATE, Material.DEEPSLATE_COPPER_ORE, 3);
        //
    }

    @Nonnull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayList = new ArrayList<>();

        for (Map.Entry<Material, RandomizedSet<ItemStack>> entry : oreMap.entrySet()) {
            for (ItemStack stack : entry.getValue().toMap().keySet()) {
                displayList.add(new ItemStack(entry.getKey()));
                displayList.add(stack);
            }
        }

        return displayList;
    }

}
