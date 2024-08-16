package me.profelements.dynatech.fluids;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.profelements.dynatech.DynaTech;

public class FluidTank extends SlimefunItem implements Listener {

    public FluidTank(ItemGroup itemGroup, SlimefunItemStack stack) {
        super(itemGroup, stack, RecipeType.NULL, new ItemStack[] {});

        Bukkit.getPluginManager().registerEvents(this, DynaTech.getInstance());
        addItemHandler(onItemUse());
    }

    public ItemUseHandler onItemUse() {
        return new ItemUseHandler() {

            @Override
            public void onRightClick(PlayerRightClickEvent event) {

                ItemStack handItem = event.getPlayer().getEquipment().getItem(event.getHand());
                if (event.getPlayer().isSneaking()) {
                    FluidStack handFluid = FluidStack.fromItemStack(handItem);

                    if (handFluid == null || !handItem.getType().equals(Material.BUCKET)) {
                        handItem.setType(Material.BUCKET);
                        return;
                    }

                    if (handFluid.fluid().equals(FluidStack.LAVA_FLUID)) {
                        handItem.setType(Material.LAVA_BUCKET);
                        return;
                    }

                    if (handFluid.fluid().equals(FluidStack.WATER_FLUID)) {
                        handItem.setType(Material.WATER_BUCKET);
                        return;
                    }

                    if (handFluid.fluid().equals(FluidStack.MILK_FLUID)) {
                        handItem.setType(Material.MILK_BUCKET);
                        return;
                    }
                }
            }
        };
    }

    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent event) {
        SlimefunItem sf = SlimefunItem.getByItem(event.getPlayer().getEquipment().getItem(event.getHand()));
        if (sf == null || !this.getId().equals(sf.getId())) {
            return;
        }

        FluidStack stack = FluidTankAdapter.getFluidFromItemStack(event.getItemStack());

        ItemStack handItem = event.getPlayer().getEquipment().getItem(event.getHand()).clone();

        FluidStack handFluid = FluidStack.fromItemStack(handItem);
        ItemMeta handMeta = handItem.getItemMeta();

        int amount = 0;
        if (handFluid == null ||
                handFluid.fluid().equals(stack.fluid()) && handFluid.amount() < 16000) {

            if (handFluid != null) {
                amount = handFluid.amount();
            }
            PersistentDataAPI.setString(handMeta, FluidStack.FLUID_KEY,
                    stack.fluid().toString());
            PersistentDataAPI.setInt(handMeta, FluidStack.FLUID_AMOUNT_KEY, amount +
                    stack.amount());

            handFluid = FluidStack.of(stack.fluid(), amount + stack.amount());

        }

        if (handFluid.amount() == 16000) {
            event.setCancelled(true);
        }

        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.WHITE + "Fluid Held: " + handFluid.fluid().toString());
        lore.add(ChatColor.WHITE + "Fluid Amount: " + String.valueOf(handFluid.amount()));
        handMeta.setLore(lore);
        handItem.setItemMeta(handMeta);

        event.setItemStack(handItem);
    }

    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent event) {
        SlimefunItem sf = SlimefunItem.getByItem(event.getPlayer().getEquipment().getItem(event.getHand()));
        if (sf == null || !this.getId().equals(sf.getId())) {
            return;
        }

        ItemStack handItem = event.getPlayer().getEquipment().getItem(event.getHand()).clone();
        FluidStack handFluid = FluidStack.fromItemStack(handItem);
        ItemMeta handMeta = handItem.getItemMeta();

        if (handFluid != null && handFluid.amount() >= 1000) {
            PersistentDataAPI.setString(handMeta, FluidStack.FLUID_KEY,
                    handFluid.fluid().toString());
            PersistentDataAPI.setInt(handMeta, FluidStack.FLUID_AMOUNT_KEY,
                    handFluid.amount() - 1000);

            handFluid = FluidStack.of(handFluid.fluid(), handFluid.amount() - 1000);

        }

        if (handFluid.amount() == 0) {
            PersistentDataAPI.setString(handMeta, FluidStack.FLUID_KEY, "");
            PersistentDataAPI.setInt(handMeta, FluidStack.FLUID_AMOUNT_KEY, 0);
            handItem.setType(Material.BUCKET);

            ArrayList<String> lore = new ArrayList<>();
            handMeta.setLore(lore);
        } else {

            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.WHITE + "Fluid Held: " + handFluid.fluid().toString());
            lore.add(ChatColor.WHITE + "Fluid Amount: " + String.valueOf(handFluid.amount()));
            handMeta.setLore(lore);
        }

        handItem.setItemMeta(handMeta);

        event.setItemStack(handItem);
    }

    @EventHandler
    public void onCauldronInteract(CauldronLevelChangeEvent event) {
        if (event.getEntity() instanceof Player p) {
            SlimefunItem sf = SlimefunItem.getByItem(p.getEquipment().getItemInMainHand());
            if (sf != null && this.getId().equals(sf.getId())) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerConsume(PlayerItemConsumeEvent event) {
        SlimefunItem sf = SlimefunItem.getByItem(event.getPlayer().getEquipment().getItem(event.getHand()));
        if (sf != null && this.getId().equals(sf.getId())) {
            event.setCancelled(true);
        }
    }
}
