package me.profelements.dynatech.items.electric.transfer;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.inventory.DirtyChestMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class WirelessItemInput extends SlimefunItem implements EnergyNetComponent {

      private final int capacity;
            
    public WirelessItemInput(ItemGroup itemGroup, int capacity, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.capacity = capacity;

        addItemHandler(onBlockBreak());
        
        new BlockMenuPreset("WIRELESS_ITEM_INPUT", "Wireless Item Input") {

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
                WirelessItemInput.this.tick(block);
				
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
            BlockMenu menu = BlockStorage.getInventory(b);
            updateKnowledgePane(menu, getCharge(b.getLocation()));
    }

    private void updateKnowledgePane(BlockMenu menu, int currentCharge) {
        ItemStack knowledgePane = menu.getItemInSlot(4);
        ItemMeta im = knowledgePane.getItemMeta();
        List<String> lore = im.hasLore() ? im.getLore() : new ArrayList<String>();

        lore.clear();
        lore.add(" ");
        lore.add(ChatColor.WHITE +"Current Power: " + currentCharge);
        lore.add(ChatColor.WHITE +"Current Status: Interesting.");

        im.setLore(lore);
        knowledgePane.setItemMeta(im);
    }

    //Boilerplate for machines.
    public void constructMenu(BlockMenuPreset preset) {
        preset.drawBackground(ChestMenuUtils.getInputSlotTexture(), getBorder());
        preset.addItem(4, new CustomItemStack(Material.PURPLE_STAINED_GLASS_PANE, "&fKnowledge Pane"), ChestMenuUtils.getEmptyClickHandler());
    }

    
    public int[] getBorder() {
        return new int[] {0,1,2,3,5,6,7,8,45,46,47,48,49,50,51,52,53};
    }

    public int[] getInputSlots() {
        return new int[] {9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44};
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

}

    

    
       
        
