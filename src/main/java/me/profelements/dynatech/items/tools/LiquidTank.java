package me.profelements.dynatech.items.tools;


import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.DynaTechItems;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LiquidTank extends SlimefunItem implements NotPlaceable {

    private static final NamespacedKey FLUID_NAME = new NamespacedKey(DynaTech.getInstance(), "liquid-name");
    private static final NamespacedKey FLUID_AMOUNT = new NamespacedKey(DynaTech.getInstance(), "liquid-amount");

    private final int maxLiquidAmount;

    public LiquidTank(ItemGroup itemGroup, SlimefunItemStack item, int maxLiquidAmount, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.maxLiquidAmount = maxLiquidAmount;

        addItemHandler(onRightClick());
        addItemHandler(onEntityClick());
    }

    private final EntityInteractHandler onEntityClick() {
        return (e, item, something) -> {
            if ((e.getRightClicked().getType() == EntityType.COW || e.getRightClicked().getType() == EntityType.MUSHROOM_COW) && SlimefunUtils.isItemSimilar(item, DynaTechItems.LIQUID_TANK, true)) {
                e.setCancelled(true);
            }
        };
    }

    private final ItemUseHandler onRightClick() {
        return e -> {
            e.cancel();

            Optional<Block> b = e.getClickedBlock();
            Optional<SlimefunItem> item = e.getSlimefunItem();
            ItemStack itemStack = e.getItem();
            
            if (b.isPresent() && item.isPresent() && item.get() instanceof LiquidTank && Slimefun.getProtectionManager().hasPermission(e.getPlayer(), b.get().getLocation(), Interaction.PLACE_BLOCK)) {
                Block liquid = b.get().getRelative(e.getClickedFace());
                BlockState liquidState = PaperLib.getBlockState(liquid, false).getState();
                LiquidTank liquidTank = (LiquidTank) item.get();

                String fluidName = getLiquid(itemStack).getFirstValue();
                int fluidAmount = getLiquid(itemStack).getSecondValue();

                if (liquid.getType() == Material.AIR && fluidName != null && e.getPlayer().isSneaking() && fluidAmount >= 1000) {
                        if (fluidName.equals("WATER") && !e.getPlayer().getWorld().isUltraWarm()) {
                            removeLiquid(itemStack, fluidName, 1000);
                            liquidState.setType(Material.WATER);
                            liquidState.update(true, true);
                            
                            updateLore(itemStack);
                            
                        } else if (fluidName.equals("LAVA")) {
                            removeLiquid(itemStack, fluidName, 1000);
                            liquidState.setType(Material.LAVA);
                            liquidState.update(true, true);
                            
                            updateLore(itemStack);
                        }
                    
                } else if ((liquid.getType() == Material.LAVA || liquid.getType() == Material.WATER) && fluidName != null && fluidAmount <= liquidTank.getMaxLiquidAmount() && liquid.isLiquid()) {
                        addLiquid(itemStack, liquid.getType().name(), 1000);
                        liquidState.setType(Material.AIR);
                        liquidState.update(true, true);
                        updateLore(itemStack);
                }
            }
        };
    }

    public int getMaxLiquidAmount() {
        return maxLiquidAmount;
    }

    public static final List<String> getPlaceableFluids() {
        List<String> PLACEABLE_FLUIDS = new ArrayList<>();
        PLACEABLE_FLUIDS.add("WATER");
        PLACEABLE_FLUIDS.add("LAVA");

        return PLACEABLE_FLUIDS;
    }

    public void addLiquid(ItemStack item, String fluidName, int fluidAmount) {
        ItemMeta im = item.getItemMeta();

        String itemFluidName = PersistentDataAPI.getString(im, FLUID_NAME);
        int itemFluidAmount = PersistentDataAPI.getInt(im, FLUID_AMOUNT);

        int resultFluidAmount = itemFluidAmount + fluidAmount;
        if (itemFluidName != null && itemFluidName.equals(fluidName) && itemFluidAmount != 0 && resultFluidAmount <= getMaxLiquidAmount()) {
            setLiquid(item, fluidName, resultFluidAmount);
        } else if (resultFluidAmount >= getMaxLiquidAmount()) {
            setLiquid(item, fluidName, getMaxLiquidAmount());
        } else {
            setLiquid(item, fluidName, fluidAmount);
        }

    }

    public void removeLiquid(ItemStack item, String fluidName, int fluidAmount) {
        ItemMeta im = item.getItemMeta();

        String itemFluidName = PersistentDataAPI.getString(im, FLUID_NAME);
        int itemFluidAmount = PersistentDataAPI.getInt(im, FLUID_AMOUNT);

        int resultFluidAmount = itemFluidAmount - fluidAmount;
        if (itemFluidName != null && itemFluidName.equals(fluidName) && itemFluidAmount != 0 && resultFluidAmount > 0) {
            setLiquid(item, fluidName, resultFluidAmount);
        } else {
            setLiquid(item, "NO_FLUID", 0);
        }
    }

    public void setLiquid(ItemStack item, String fluidName, int fluidAmount) {
        ItemMeta im = item.getItemMeta();

        PersistentDataAPI.setString(im, FLUID_NAME, fluidName);
        PersistentDataAPI.setInt(im, FLUID_AMOUNT, fluidAmount);

        item.setItemMeta(im);
    }

    public Pair<String, Integer> getLiquid(ItemStack item) {
        String fluidName = PersistentDataAPI.getString(item.getItemMeta(), FLUID_NAME);
        int fluidAmount = PersistentDataAPI.getInt(item.getItemMeta(), FLUID_AMOUNT);
        if (item.hasItemMeta() && fluidName != null && fluidAmount != 0) {
            return new Pair<>(fluidName, fluidAmount);
        }
        return new Pair<>("NO_FLUID", 0);
    }

    public void updateLore(ItemStack item) {
        String fluidName = PersistentDataAPI.getString(item.getItemMeta(), FLUID_NAME);
        int fluidAmount = PersistentDataAPI.getInt(item.getItemMeta(), FLUID_AMOUNT);

        ItemMeta im = item.getItemMeta();
        List<String> lore = im.getLore();

        if (fluidName == null) {
            return;
        }

        for (int i = 0; i < lore.size(); i++) {
            if (lore.get(i).contains("Fluid Held: ")) {

                lore.set(i, ChatColor.WHITE + "Fluid Held: " + fluidName);
            }

            if (lore.get(i).contains("Amount: ")) {

                lore.set(i, ChatColor.WHITE + "Amount: " + fluidAmount + "mb / " + getMaxLiquidAmount());
            }
        }

        im.setLore(lore);
        item.setItemMeta(im);
    }

}
