package me.profelements.dynatech.items.backpacks;

import io.github.bakedlibs.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PicnicBasket extends SlimefunBackpack {
        
    protected static Map<ItemStack, Pair<Integer, Float>> foods = new HashMap<>();

    private final List<Material> defaultBlacklist = new ArrayList<>();

    private final ItemSetting<List<String>> blacklistedMaterials = new ItemSetting<>(this, "blacklisted-materials", toStringList(getDefaultBlacklist()));

    public PicnicBasket(int size, ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(size, itemGroup, item, recipeType, recipe);

        /*Maybe use Material.getMaterial() and send a set of strings*/

        addItemSetting(blacklistedMaterials);
    }


    @Override
    public void postRegister() {
        registerDefaultFoods();
    }


    @Override
    public boolean isItemAllowed(@Nonnull ItemStack item, @Nullable SlimefunItem itemAsSlimefunItem) {
        for (ItemStack stack : getFoods().keySet()) {
                if (SlimefunUtils.isItemSimilar(stack, item, false, false)) {
                    return true; 
                }
            }
        return false;
    }

    private List<Material> getDefaultBlacklist() {
        defaultBlacklist.add(Material.PUFFERFISH);
        defaultBlacklist.add(Material.POISONOUS_POTATO);
        defaultBlacklist.add(Material.SPIDER_EYE);
        defaultBlacklist.add(Material.CHORUS_FRUIT);
        defaultBlacklist.add(Material.ENCHANTED_GOLDEN_APPLE);
        defaultBlacklist.add(Material.GOLDEN_APPLE);
        defaultBlacklist.add(Material.ROTTEN_FLESH);

        //Returns Stuff, maybe will figure this out later.
        defaultBlacklist.add(Material.SUSPICIOUS_STEW);
        defaultBlacklist.add(Material.MUSHROOM_STEW);
        defaultBlacklist.add(Material.RABBIT_STEW);
        defaultBlacklist.add(Material.BEETROOT_SOUP);
        defaultBlacklist.add(Material.HONEY_BOTTLE);

        return defaultBlacklist;
    }

    private List<String> toStringList(List<Material> mats) {
        List<String> materials = new ArrayList<>();

        for (Material mat : mats) {
            materials.add(mat.toString());
        }

        return materials;
    }

    @Nonnull
    public static Map<ItemStack, Pair<Integer, Float>> getFoods() {
        return foods;
    }
 
    private static void registerDefaultFoods() {
        registerFood(new ItemStack(Material.APPLE), new Pair<>(4, 3F)); 
        registerFood(new ItemStack(Material.MELON_SLICE), new Pair<>(2, 1F));
        registerFood(new ItemStack(Material.SWEET_BERRIES), new Pair<>(2, 1F));
        registerFood(new ItemStack(Material.GLOW_BERRIES), new Pair<>(2, 1F));
        registerFood(new ItemStack(Material.CARROT), new Pair<>(3, 3F));
        registerFood(new ItemStack(Material.GOLDEN_CARROT), new Pair<>(6, 15F));
        registerFood(new ItemStack(Material.POTATO), new Pair<>(1, 1F));
        registerFood(new ItemStack(Material.BAKED_POTATO), new Pair<>(5, 6F));
        registerFood(new ItemStack(Material.BEETROOT), new Pair<>(1, 1F));
        registerFood(new ItemStack(Material.DRIED_KELP), new Pair<>(1, 1F));
        registerFood(new ItemStack(Material.BEEF), new Pair<>(3, 1F));
        registerFood(new ItemStack(Material.COOKED_BEEF), new Pair<>(8, 13F));
        registerFood(new ItemStack(Material.PORKCHOP), new Pair<>(3, 1F));
        registerFood(new ItemStack(Material.COOKED_PORKCHOP), new Pair<>(8, 13F));
        registerFood(new ItemStack(Material.MUTTON), new Pair<>(2, 1F));
        registerFood(new ItemStack(Material.COOKED_MUTTON), new Pair<>(6, 9F));
        registerFood(new ItemStack(Material.CHICKEN), new Pair<>(1, 1F));
        registerFood(new ItemStack(Material.COOKED_CHICKEN), new Pair<>(6, 7F));
        registerFood(new ItemStack(Material.RABBIT), new Pair<>(3, 1F));
        registerFood(new ItemStack(Material.COOKED_RABBIT), new Pair<>(5, 6F));
        registerFood(new ItemStack(Material.COD), new Pair<>(2, 1F));
        registerFood(new ItemStack(Material.COOKED_COD), new Pair<>(5, 6F));
        registerFood(new ItemStack(Material.SALMON), new Pair<>(2, 1F));
        registerFood(new ItemStack(Material.COOKED_SALMON), new Pair<>(6, 9F));
        registerFood(new ItemStack(Material.TROPICAL_FISH), new Pair<>(1, 1F));
        registerFood(new ItemStack(Material.BREAD), new Pair<>(5, 6F));
        registerFood(new ItemStack(Material.COOKIE), new Pair<>(2, 1F));
        registerFood(new ItemStack(Material.CAKE), new Pair<>(14, 14F));
        registerFood(new ItemStack(Material.PUMPKIN_PIE), new Pair<>(8, 5F));
    }
        

    public static void registerFood(ItemStack item, Pair<Integer, Float> pair) {
        getFoods().put(item, pair);
    }


	
}
