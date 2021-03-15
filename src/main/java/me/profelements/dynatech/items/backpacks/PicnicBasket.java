package me.profelements.dynatech.items.backpacks;

import io.github.thebusybiscuit.exoticgarden.items.CustomFood;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.implementation.items.backpacks.SlimefunBackpack;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.profelements.dynatech.DynaTech;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class PicnicBasket extends SlimefunBackpack {

    private final List<Material> defaultBlacklist = new ArrayList<>();

    private final ItemSetting<List<String>> blacklistedMaterials = new ItemSetting<>(this, "blacklisted-materials", ToStringList(getDefaultBlacklist()));

    public PicnicBasket(int size, Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(size, category, item, recipeType, recipe);

        /*Maybe use Material.getMaterial() and send a set of strings*/

        addItemSetting(blacklistedMaterials);
    }

    @Override
    public boolean isItemAllowed(@Nonnull ItemStack item, @Nullable SlimefunItem itemAsSlimefunItem) {
        if (DynaTech.isExoticGardenInstalled()) {
            if (itemAsSlimefunItem instanceof CustomFood) {
                return true;
            } else {
                return item.getType().isEdible() && !blacklistedMaterials.getValue().contains(item.getType().toString());
            }
        }
        return item.getType().isEdible() && !blacklistedMaterials.getValue().contains(item.getType().toString());
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
        defaultBlacklist.add(Material.HONEY_BOTTLE);

        return defaultBlacklist;
    }

    private List<String> ToStringList(List<Material> mats) {
        List<String> materials = new ArrayList<>();

        for (Material mat : mats) {
            materials.add(mat.toString());
        }

        return materials;
    }
}
