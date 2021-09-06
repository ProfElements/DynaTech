package me.profelements.dynatech.items.electric.transfer;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


public class WirelessItemOutput extends SlimefunItem implements EnergyNetComponent {

    
    protected static final NamespacedKey WIRELESS_LOCATION_KEY = new NamespacedKey(DynaTech.getInstance(), "wireless-input-location");
	private final int capacity;
            
    public WirelessItemOutput(ItemGroup itemGroup, int capacity, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.capacity = capacity;

        addItemHandler(onBlockBreak(), onBlockPlace(), onRightClick());
        
        new BlockMenuPreset("WIRELESS_ITEM_OUTPUT", "Wireless Item Output") {

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
                WirelessItemOutput.this.tick(block);
				
			}

             
       });
    }

    private ItemHandler onRightClick() {
        return new ItemUseHandler() {

            @Override
            public void onRightClick(PlayerRightClickEvent event) {

                Optional<Block> blockClicked = event.getClickedBlock();           
                Optional<SlimefunItem> sfBlockClicked = event.getSlimefunBlock();
                if (blockClicked.isPresent() && sfBlockClicked.isPresent()) {
                    Location blockLoc = blockClicked.get().getLocation();
                    SlimefunItem sfBlock = sfBlockClicked.get();
                    ItemStack item = event.getItem();


                    if (sfBlock != null && sfBlock.getId().equals(DynaTechItems.WIRELESS_ITEM_INPUT.getItemId()) && blockLoc != null) {
                        event.cancel();
                        ItemMeta im = item.getItemMeta();
                        String locationString = LocationToString(blockLoc);
                        
                        PersistentDataAPI.setString(im, WIRELESS_LOCATION_KEY, locationString);
                        item.setItemMeta(im);
                        setItemLore(item, blockLoc);
                    }
                }   
            } 
        };
    }

    private ItemHandler onBlockPlace() {
        return new BlockPlaceHandler(false) {
            @Override
            public void onPlayerPlace(BlockPlaceEvent event) {
                
                
                Location blockLoc = event.getBlockPlaced().getLocation();
                ItemStack item = event.getItemInHand();
                String locationString = PersistentDataAPI.getString(item.getItemMeta(), WIRELESS_LOCATION_KEY);
                
                if (item != null && item.getType() == DynaTechItems.WIRELESS_ITEM_OUTPUT.getType() && item.hasItemMeta() && locationString != null) {
                    BlockStorage.addBlockInfo(blockLoc, "wireless-input-location", locationString);
                    
                }   
            }
            
        };
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
        String wirelessLocation = BlockStorage.getLocationInfo(b.getLocation(), "wireless-input-location");
        if (wirelessLocation != null) {
            sendItemsFromInput(b, wirelessLocation);
            
        }
    }

    private void sendItemsFromInput(Block b, String wirelessLocation) {
        Location wirelessItemInput = StringToLocation(wirelessLocation);
    
        // Note: You should probably also see if the Future from getChunkAtAsync is finished here.
        // you don't really want to possibly trigger the chunk to load in another thread twice.
        if (!wirelessItemInput.getWorld().isChunkLoaded(wirelessItemInput.getBlockX() >> 4, wirelessItemInput.getBlockZ() >> 4)) {
            CompletableFuture<Chunk> chunkLoad = PaperLib.getChunkAtAsync(wirelessItemInput);
            if (!chunkLoad.isDone()) {
                return;
            } 
        }

        if (wirelessItemInput != null && BlockStorage.checkID(wirelessItemInput) != null && BlockStorage.checkID(wirelessItemInput).equals(DynaTechItems.WIRELESS_ITEM_INPUT.getItemId())) {
            BlockMenu input = BlockStorage.getInventory(wirelessItemInput);
            BlockMenu output = BlockStorage.getInventory(b);
            updateKnowledgePane(output, getCharge(b.getLocation()));
            
            for (int i : getOutputSlots()) {
                if (getCharge(wirelessItemInput) < getEnergyConsumption() || getCharge(b.getLocation()) < getEnergyConsumption()) {
                    return;
                }
                ItemStack itemStack = input.getItemInSlot(i);
                
                if (itemStack != null && itemStack.getType() != Material.AIR && InvUtils.fitAll(output.toInventory(), new ItemStack[] {itemStack}, getOutputSlots())) {
                    removeCharge(wirelessItemInput, getEnergyConsumption());
                    removeCharge(b.getLocation(), getEnergyConsumption());
                    output.pushItem(itemStack, getOutputSlots());
                    itemStack.setAmount(0);
                }
            }
            
        }

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
        preset.drawBackground(ChestMenuUtils.getOutputSlotTexture(), getBorder());
        preset.addItem(4, new CustomItemStack(Material.PURPLE_STAINED_GLASS_PANE, "&fKnowledge Pane", "&fCurrent Power: Unknown", "&fCurrent Status: NOT CONNECTED"), ChestMenuUtils.getEmptyClickHandler());
    }

    
    public int[] getBorder() {
        return new int[] {0,1,2,3,5,6,7,8,45,46,47,48,49,50,51,52,53};
    }

    public int[] getOutputSlots() {
        return new int[] {9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44};
    }

    public int[] getInputSlots() {
        return new int[0];  
    }
 
    @Override
    public int getCapacity() {
        return capacity;
    }

    public int getEnergyConsumption() {
        return 8;
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    private void setItemLore(ItemStack item, Location l) {
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

    private String LocationToString(Location l) {
        return l.getWorld().getName()+";"+l.getBlockX()+";"+l.getBlockY()+";"+l.getBlockZ();
    }

    private static final Location StringToLocation(String locString) {
        String[] locComponents = locString.split(";");
        return new Location(Bukkit.getWorld(locComponents[0]), Double.parseDouble(locComponents[1]), Double.parseDouble(locComponents[2]), Double.parseDouble(locComponents[3]));
    }

}

    

    
       
        
