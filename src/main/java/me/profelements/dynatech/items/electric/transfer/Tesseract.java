package me.profelements.dynatech.items.electric.transfer;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.inventory.InvUtils;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.DynaTechItems;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Tesseract extends SlimefunItem implements EnergyNetProvider {
    public static final NamespacedKey WIRELESS_LOCATION_KEY = new NamespacedKey(DynaTech.getInstance(), "tesseract-pair-location");
	private final int capacity;
    private final int energyRate;
            
    public Tesseract(ItemGroup itemGroup, int capacity, int energyRate, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.capacity = capacity;
        this.energyRate = energyRate;

        addItemHandler(onBlockBreak());
        
        new BlockMenuPreset("TESSERACT", "Tesseract") {

            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(Block b, Player p) { 
                return p.hasPermission("slimefun.inventory.bypass") || Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.INTERACT_BLOCK);

                
            }

            @Override 
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                return new int[0];
            }

            @Override
            public int[] getSlotsAccessedByItemTransport(DirtyChestMenu menu, ItemTransportFlow flow, ItemStack item) {
                if (flow == ItemTransportFlow.INSERT) {
                    return getInputSlots();
                } else {
                    return getOutputSlots();
                } 
            }
        };
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker(){

			@Override
            public boolean isSynchronized() {
				return false;
			}

			@Override
			public void tick(Block block, SlimefunItem sfItem, Config data) {
                Tesseract.this.tick(block);
				
			}

             
       });
    }

    private ItemHandler onBlockBreak() {
        return new BlockBreakHandler(false, false) {

			@Override
			public void onPlayerBreak(BlockBreakEvent event, ItemStack block, List<ItemStack> drops) {
                BlockMenu inv = BlockStorage.getInventory(event.getBlock());
                
                if (inv != null) {
                    inv.dropItems(event.getBlock().getLocation(), getInputSlots());
                    inv.dropItems(event.getBlock().getLocation(), getOutputSlots());
    
                }


				BlockStorage.clearBlockInfo(event.getBlock().getLocation());
			}
            
        };
    }

    protected void tick(Block b) {
        String wirelessLocation = BlockStorage.getLocationInfo(b.getLocation(), "tesseract-pair-location");
        if (wirelessLocation != null) {
            sendItemsAndCharge(b, wirelessLocation);
            
        }
    }

    private void sendItemsAndCharge(Block b, String wirelessLocation) {
        Location tesseractPair = StringToLocation(wirelessLocation);
    
        // Note: You should probably also see if the Future from getChunkAtAsync is finished here.
        // you don't really want to possibly trigger the chunk to load in another thread twice.
        if (!tesseractPair.getWorld().isChunkLoaded(tesseractPair.getBlockX() >> 4, tesseractPair.getBlockZ() >> 4)) {
            CompletableFuture<Chunk> chunkLoad = PaperLib.getChunkAtAsync(tesseractPair);
            if (!chunkLoad.isDone()) {
                return;
            } 
        }

        if (tesseractPair != null && BlockStorage.checkID(tesseractPair) != null && BlockStorage.checkID(tesseractPair).equals(DynaTechItems.TESSERACT.getItemId())) {
            BlockMenu input = BlockStorage.getInventory(tesseractPair);
            BlockMenu output = BlockStorage.getInventory(b);

            updateKnowledgePane(output, getCharge(b.getLocation()));
            
            for (int i : getInputSlots()) {
                ItemStack itemStack = input.getItemInSlot(i);
                
                if (itemStack != null && itemStack.getType() != Material.AIR && InvUtils.fitAll(output.toInventory(), new ItemStack[] {itemStack}, getOutputSlots())) {
                    output.pushItem(itemStack, getOutputSlots());
                    itemStack.setAmount(0);
                }
            }
            
        }

    }

    @Override
    public int getGeneratedOutput(Location l, Config data) {
        String tesseractPairLocation = BlockStorage.getLocationInfo(l, "tesseract-pair-location");
    
        int chargedNeeded = getCapacity() - getCharge(l);
    
        if(chargedNeeded != 0 && tesseractPairLocation != null) {
            Location tesseractPair = StringToLocation(tesseractPairLocation);
    
            // Note: You should probably also see if the Future from getChunkAtAsync is finished here.
            // you don't really want to possibly trigger the chunk to load in another thread twice.
            if (!tesseractPair.getWorld().isChunkLoaded(tesseractPair.getBlockX() >> 4, tesseractPair.getBlockZ() >> 4)) {
                CompletableFuture<Chunk> chunkLoad = PaperLib.getChunkAtAsync(tesseractPair);
                if (!chunkLoad.isDone()) {
                    return 0;
                } 
            }
    
            if (tesseractPair != null && BlockStorage.checkID(tesseractPair) != null && BlockStorage.checkID(tesseractPair).equals(DynaTechItems.TESSERACT.getItemId())) {
                int BankCharge = getCharge(tesseractPair);
                
                if (BankCharge > chargedNeeded && BankCharge != 0) {
                    if (chargedNeeded > getEnergyRate()) {
                        removeCharge(tesseractPair, getEnergyRate());
                        return getEnergyRate();
                    }
                    removeCharge(tesseractPair, chargedNeeded);
                    return chargedNeeded;
                } else if (BankCharge > 0) {
                    if (chargedNeeded > getEnergyRate()) {
                        removeCharge(tesseractPair, getEnergyRate());
                        return getEnergyRate();
                    }
                    removeCharge(tesseractPair, BankCharge);
                    return BankCharge;
                }
                
            }
    
        }
        return 0;
    }
    private void updateKnowledgePane(BlockMenu menu, int currentCharge) {
        ItemStack knowledgePane = menu.getItemInSlot(4);
        ItemMeta im = knowledgePane.getItemMeta();
        List<String> lore = im.hasLore() ? im.getLore() : new ArrayList<String>();

        lore.clear();
        lore.add(" ");
        lore.add(ChatColor.WHITE +"Current Power: " + currentCharge);
        lore.add(ChatColor.WHITE +"Current Status: " + ChatColor.RED + "CONNECTED");
        knowledgePane.setType(Material.RED_STAINED_GLASS_PANE);

        im.setLore(lore);
        knowledgePane.setItemMeta(im);
    }

    //Boilerplate for machines.
    public void constructMenu(BlockMenuPreset preset) {
        preset.drawBackground(ChestMenuUtils.getBackground(), getBorder());
        preset.drawBackground(ChestMenuUtils.getInputSlotTexture(), getInputBorder());
        preset.drawBackground(ChestMenuUtils.getOutputSlotTexture(), getOutputBorder());
        preset.addItem(4, new CustomItemStack(Material.PURPLE_STAINED_GLASS_PANE, "&fKnowledge Pane", "&fCurrent Power: Unknown", "&fCurrent Status: NOT CONNECTED"), ChestMenuUtils.getEmptyClickHandler());
    }

    
    public int[] getBorder() {
        return new int[] {13,22,31,49,40};
    }

    public int[] getInputBorder() {
        return new int[] {0,1,2,3,45,46,47,48};
    }

    public int[] getOutputBorder() {
        return new int[] {5,6,7,8,50,51,52,53};
    }
    public int[] getInputSlots() {
        return new int[] {9,10,11,12,18,19,20,21,27,28,29,30,36,37,38,39};  
    }

    public int[] getOutputSlots() {
        return new int[] {14,15,16,17,23,24,25,26,32,33,34,35,41,42,43,44};
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    public int getEnergyRate() {
        return energyRate;
    }

    public static void setItemLore(ItemStack item, Location l) {
        ItemMeta im = item.getItemMeta();
        List<String> lore = im.getLore();
        for (int i = 0; i < lore.size(); i++) {
            if (lore.get(i).contains("Location: ")) {
                lore.remove(i);
            } 
        }

        lore.add(ChatColor.WHITE + "Location: " + l.getWorld().getName() + " " + l.getBlockX() + " " + l.getBlockY() + " " + l.getBlockZ());

        im.setLore(lore);
        item.setItemMeta(im);
        
    }

    public static String LocationToString(Location l) {
        return l.getWorld().getName()+";"+l.getBlockX()+";"+l.getBlockY()+";"+l.getBlockZ();
    }

    public static final Location StringToLocation(String locString) {
        String[] locComponents = locString.split(";");
        return new Location(Bukkit.getWorld(locComponents[0]), Double.parseDouble(locComponents[1]), Double.parseDouble(locComponents[2]), Double.parseDouble(locComponents[3]));
    }
}
