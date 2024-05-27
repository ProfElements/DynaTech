package me.profelements.dynatech.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import io.github.bakedlibs.dough.inventory.InvUtils;
import io.github.thebusybiscuit.slimefun4.api.events.AsyncMachineOperationFinishEvent;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.DynaTechItems;
import me.profelements.dynatech.items.tools.AutoOutputUpgrade;

public class UpgradesListener implements Listener {

    public UpgradesListener(DynaTech plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onMachineFinish(AsyncMachineOperationFinishEvent e) {
        if (!(e.getOperation() instanceof CraftingOperation)) {
            return;
        }

        checkInputUpgrade(e);

        Location l = e.getPosition().toLocation();
        String upgrades = BlockStorage.getLocationInfo(l, "upgrades");

        if (upgrades == null) {
            return;
        }

        int upgradeIdx = upgrades.indexOf("{id:auto_output");
        if (upgradeIdx == -1) {
            return;
        }

        int upgradeIdx2 = upgrades.indexOf("}", upgradeIdx);

        String upgradeString = upgrades.substring(upgradeIdx, upgradeIdx2 + 1);

        if (upgrades != null && upgrades.contains("id:auto_output")) {
            int index = upgradeString.indexOf("face:");
            int index2 = upgradeString.indexOf("}");
            BlockFace face = AutoOutputUpgrade.stringToBlockFace(upgradeString.substring(index, index2));
            // DynaTech.getInstance().getLogger().info(face.toString());
            // Grab menu and then grab output slots
            if (e.getProcessor().getOwner() instanceof AContainer cont
                    && e.getOperation() instanceof CraftingOperation op && op.isFinished()) {
                BlockMenu menu = BlockStorage.getInventory(l);
                int[] outputSlots = cont.getOutputSlots();
                ItemStack[] outputItems = op.getResults();
                List<Boolean> outed = new ArrayList<>(outputItems.length);
                // Clear `outputItems` from `outputSlots`
                if (l.getBlock().getRelative(face).getType().equals(Material.CHEST)) {
                    for (int i = 0; i < outputSlots.length; i++) {
                        ItemStack item = menu.getItemInSlot(outputSlots[i]);
                        for (ItemStack outputItem : outputItems) {
                            if (SlimefunUtils.isItemSimilar(item, outputItem, true) && outed.size() < (i + 1)) {
                                int amount = item.getAmount();
                                int outAmount = outputItem.getAmount();
                                if (amount >= outAmount) {
                                    menu.consumeItem(outputSlots[i], outAmount);
                                    outed.add(true);
                                }
                            }
                        }
                    }

                    DynaTech.runSync(() -> {
                        BlockState state = PaperLib.getBlockState(l.getBlock().getRelative(face), false).getState();
                        if (state instanceof Chest chest
                                && InvUtils.fitAll(chest.getBlockInventory(), outputItems)) {
                            Inventory inv = chest.getBlockInventory();

                            inv.addItem(outputItems);
                            chest.update(true, false);
                        }
                    });
                }
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Location l = e.getBlock().getLocation();
        String upgrades = BlockStorage.getLocationInfo(l, "upgrades");

        if (upgrades != null && upgrades.contains("auto_output")) {
            l.getWorld().dropItemNaturally(l, DynaTechItems.AUTO_OUTPUT_UPGRADE);
        }

        if (upgrades != null && upgrades.contains("auto_input")) {
            l.getWorld().dropItemNaturally(l, DynaTechItems.AUTO_INPUT_UPGRADE);
        }
    }

    private static void checkInputUpgrade(AsyncMachineOperationFinishEvent e) {
        Location l = e.getPosition().toLocation();
        String upgrades = BlockStorage.getLocationInfo(l, "upgrades");

        if (upgrades == null) {
            return;
        }

        int upgradeIdx = upgrades.indexOf("{id:auto_input");
        if (upgradeIdx == -1) {
            return;
        }

        int upgradeIdx2 = upgrades.indexOf("}", upgradeIdx);

        String upgradeString = upgrades.substring(upgradeIdx, upgradeIdx2 + 1);

        if (upgradeString.contains("id:auto_input")) {
            DynaTech.getInstance().getLogger().info("GOT TO INPUT FOUND");
            // Grab face
            int index = upgradeString.indexOf("face:");
            int index2 = upgradeString.indexOf("}");
            BlockFace face = AutoOutputUpgrade.stringToBlockFace(upgradeString.substring(index, index2));
            if (face == BlockFace.SELF) {
                return;
            }

            DynaTech.runSync(() -> {
                BlockState state = PaperLib.getBlockState(l.getBlock().getRelative(face), false).getState();
                if (state instanceof Chest chest && e.getProcessor().getOwner() instanceof AContainer acont) {
                    BlockMenu inv = BlockStorage.getInventory(l);
                    int[] slots = acont.getInputSlots();
                    for (int slot : slots) {
                        Inventory chsInv = chest.getBlockInventory();
                        ItemStack inputStack = inv.getItemInSlot(slot);
                        for (ItemStack stack : chsInv.getContents()) {
                            if (inputStack == null && stack != null
                                    || inputStack != null && stack != null && stack.isSimilar(inputStack)) {
                                int chsAmount = stack.getAmount();

                                if (inputStack == null) {

                                    DynaTech.getInstance().getLogger().info("GOT TO NULLY FOUND");
                                    inv.pushItem(stack, acont.getInputSlots());
                                    chsInv.remove(stack);
                                } else {
                                    if (inputStack.getAmount() == inputStack.getMaxStackSize()) {
                                        return;
                                    } else {
                                        int diff = inputStack.getMaxStackSize() - inputStack.getAmount();
                                        if (diff >= chsAmount) {

                                            DynaTech.getInstance().getLogger().info("GOT TO DIFFY FOUND");
                                            inputStack.setAmount(inputStack.getAmount() + chsAmount);
                                            chsInv.remove(stack);
                                        } else {

                                            DynaTech.getInstance().getLogger().info("GOT TO DIFFY2 FOUND");
                                            inputStack.setAmount(inputStack.getAmount() + diff);
                                            stack.setAmount(chsAmount - diff);
                                        }
                                    }
                                }

                            }
                        }
                    }
                }

            });
        }
    }
}
