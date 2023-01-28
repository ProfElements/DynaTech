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
import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LiquidTank extends SlimefunItem implements NotPlaceable, Listener {

    private static final NamespacedKey FLUID_NAME = new NamespacedKey(DynaTech.getInstance(), "liquid-name");
    private static final NamespacedKey FLUID_AMOUNT = new NamespacedKey(DynaTech.getInstance(), "liquid-amount");

    private final int maxLiquidAmount;

    public LiquidTank(ItemGroup itemGroup, SlimefunItemStack item, int maxLiquidAmount, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.maxLiquidAmount = maxLiquidAmount;

        Bukkit.getPluginManager().registerEvents(this, DynaTech.getInstance());
        addItemHandler(onEntityClick());
        addItemHandler(onRightClick());
    }

    private final EntityInteractHandler onEntityClick() {
        return (e, item, something) -> {
            if ((e.getRightClicked().getType() == EntityType.COW || e.getRightClicked().getType() == EntityType.MUSHROOM_COW) && SlimefunUtils.isItemSimilar(item, DynaTechItems.LIQUID_TANK, true)) {
                e.setCancelled(true);
            }
        };
    }
    @EventHandler
    private void onBucketChange(PlayerBucketFillEvent e) {
        e.setCancelled(true);
        ItemStack item = e.getPlayer().getEquipment().getItem(e.getHand());
        if (this.isItem(item) && this.canUse(e.getPlayer(), true) && SlimefunItem.getByItem(item) instanceof LiquidTank tank) {
        //Check if block == LAVA or WATER 
            String fluidName = PersistentDataAPI.getString(item.getItemMeta(), FLUID_NAME, "NO_LIQUID");
            int fluidAmount = PersistentDataAPI.getInt(item.getItemMeta(), FLUID_AMOUNT, 0);
            Block block = e.getBlock();
            if (block.isLiquid() && (fluidName.equals("NO_LIQUID") && fluidAmount == 0) || fluidName.equals(block.getType().toString()) && fluidAmount + 1000 <= getMaxLiquidAmount()) {
                ItemMeta meta = item.getItemMeta(); 
            
                PersistentDataAPI.setString(meta, FLUID_NAME, block.getType().toString());
                PersistentDataAPI.setInt(meta, FLUID_AMOUNT, fluidAmount + 1000); 
                
                List<String> lore = new ArrayList<>();
                lore.add(ChatColor.GRAY + "A Liquid tank holding up to 16 buckets of some liquids");
                lore.add("");
                lore.add("Right click to grab a liquid");
                lore.add("Shift right click to place a liquid");
                lore.add("");
                lore.add(ChatColor.WHITE + "Fluid Held: " + PersistentDataAPI.getString(meta, FLUID_NAME));
                lore.add(ChatColor.WHITE + "Fluid Amount: " + PersistentDataAPI.getInt(meta, FLUID_AMOUNT));
                meta.setLore(lore);
                item.setItemMeta(meta); 
                DynaTech.runSync(() -> { block.setType(Material.AIR); }); 
            }    
        }
    }

    private final ItemUseHandler onRightClick() {
        return e -> {
            if (e.getPlayer().isSneaking() && e.getClickedBlock().isPresent() && !e.getClickedBlock().get().isLiquid()) { 
                ItemStack item = e.getItem();
                String fluidName = PersistentDataAPI.getString(item.getItemMeta(), FLUID_NAME, "NO_LIQUID");
                int fluidAmount = PersistentDataAPI.getInt(item.getItemMeta(), FLUID_AMOUNT, 0);
                if (this.canUse(e.getPlayer(), true) && this.isItem(item) && !fluidName.equals("NO_LIQUID") && fluidAmount >= 1000) {
                    Material mat = Material.getMaterial(fluidName);

                    if (mat != null && e.getClickedBlock().isPresent()) {
                        Block block = e.getClickedBlock().get().getRelative(e.getClickedFace());
                        if ((block.isLiquid() || block.getType().isAir()) && !block.getWorld().isUltraWarm()) {
                            ItemMeta meta = item.getItemMeta(); 
                            if (fluidAmount - 1000 == 0) {
                                PersistentDataAPI.setString(meta, FLUID_NAME, "NO_LIQUID");
                            } else { 
                                PersistentDataAPI.setString(meta, FLUID_NAME, fluidName);
                            }
                            PersistentDataAPI.setInt(meta, FLUID_AMOUNT, fluidAmount - 1000); 
                            
                            List<String> lore = new ArrayList<>();
                            lore.add(ChatColor.GRAY + "A Liquid tank holding up to 16 buckets of some liquids");
                            lore.add("");
                            lore.add("Right click to grab a liquid");
                            lore.add("Shift right click to place a liquid");
                            lore.add("");
                            lore.add(ChatColor.WHITE + "Fluid Held: " + PersistentDataAPI.getString(meta, FLUID_NAME));
                            lore.add(ChatColor.WHITE + "Fluid Amount: " + PersistentDataAPI.getInt(meta, FLUID_AMOUNT));
                            meta.setLore(lore);
                            item.setItemMeta(meta); 
                            DynaTech.runSync(() -> { block.setType(mat); });
                        }
                    }
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
