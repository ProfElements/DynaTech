package me.profelements.dynatech.items.electric.transfer;

import java.util.ArrayList;
import java.util.List;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;

import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;


public class WirelessItemInput extends SlimefunItem implements EnergyNetComponent {

      private final int capacity;
            
    public WirelessItemInput(Category category, int capacity, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        this.capacity = capacity;
        
        new BlockMenuPreset("WIRELESS_INPUT_PRESET", "Wireless Item Input") {

            @Override
            public void init() {
                constructMenu(this);
            }

            @Override
            public boolean canOpen(Block b, Player p) { 
                return p.hasPermission("slimefun.inventory.bypass") || SlimefunPlugin.getProtectionManager().hasPermission(p, b.getLocation(), ProtectableAction.INTERACT_BLOCK);

                
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
                WirelessItemInput.this.tick(block);
				
			}

             
       });
    }

    protected void tick(Block b) {
        String wirelessLocation = BlockStorage.getLocationInfo(b.getLocation(), "wireless-output-location");
        if (wirelessLocation != null) {
            BlockMenu menu = BlockStorage.getInventory(b);
            updateKnowledgePane(menu, wirelessLocation, getCharge(b.getLocation()));
        }
    }

    private void updateKnowledgePane(BlockMenu menu, String wirelessLocation, int currentCharge) {
        ItemStack knowledgePane = menu.getItemInSlot(4);
        ItemMeta im = knowledgePane.getItemMeta();
        List<String> lore = im.hasLore() ? im.getLore() : new ArrayList<String>();

        lore.clear();
        lore.add(" ");
        lore.add(ChatColor.WHITE +"Output Location: " + wirelessLocation.replace(";", " "));
        lore.add(ChatColor.WHITE +"Current Power: " + currentCharge);
        lore.add(ChatColor.WHITE +"Wireless Output's Power: " + getCharge(StringToLocation(wirelessLocation)));

        im.setLore(lore);
        knowledgePane.setItemMeta(im);
    }

    //Boilerplate for machines.
    public void constructMenu(BlockMenuPreset preset) {
        preset.drawBackground(ChestMenuUtils.getInputSlotTexture(), getBorder());
        preset.addItem(4, new CustomItem(Material.PURPLE_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());
    }

    
    public int[] getBorder() {
        return new int[] {0,1,2,3,5,6,7,8,47,48,49,50,51,52,53};
    }

    public int[] getInputSlots() {
        return new int[] {9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46};
    }

    public int[] getOutputSlots() {
        return new int[0];  
    }
 
    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    private static final Location StringToLocation(String locString) {
        String[] locComponents = locString.split(";");
        return new Location(Bukkit.getWorld(locComponents[0]), Double.parseDouble(locComponents[1]), Double.parseDouble(locComponents[2]), Double.parseDouble(locComponents[3]));
    }

}

    

    
       
        
