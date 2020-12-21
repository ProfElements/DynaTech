package me.profelements.extrastuff.items.machines;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class AntigravityBubble extends AMachine {


    private static int[] BORDER     = new int[] {1,2,6,7,9,10,11,15,16,17,19,20,24,25};
    private static int[] BORDER_IN  = new int[] {3,4,5,12,14,21,22,23};
    private static int[] BORDER_OUT = new int[] {0,8,18,26};

    public AntigravityBubble(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }


    @Override
    public void tick(Block b) {
        doFlightIfAvailable(b);
    }

    protected void doFlightIfAvailable(Block block) {
        if (getCharge(block.getLocation()) < getEnergyConsumption()) {
        return;
        }
        
        for (Player p : block.getWorld().getPlayers()) {
            double distance = block.getLocation().distance(p.getLocation());
     
            if(p.getInventory().getItemInOffHand() != null && SlimefunItem.getByItem(p.getInventory().getItemInOffHand()) != null) {
                if (distance < 45 && SlimefunItem.getByItem(p.getInventory().getItemInOffHand()).getId() == "ANTIGRAVITY_CONTROLLER") {
                    
                p.setAllowFlight(true);
                removeCharge(block.getLocation(), getEnergyConsumption());
                } else if (p.getInventory().getItemInOffHand() == null ||p.getInventory().getItemInOffHand().getType() == Material.AIR || (distance >= 45 && p.getAllowFlight())) {
                    
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.setFallDistance(0f);
                }
            }
        }
                
    };

    @Override
    public boolean isGraphical() {
        return false;
    }
    
    @Override
    public String getMachineIdentifier() {
        return "ANTIGRAVITY_BUBBLE";
    }


    @Override
    public List<int[]> getBorders() {
        List<int[]> borders = new ArrayList<>();
        borders.add(BORDER);
        borders.add(BORDER_IN);
        borders.add(BORDER_OUT);
        
        return borders;
    }
    
    @Override
    public int[] getInputSlots() {
        return new int[] {13};
    }
    @Override
    public int[] getOutputSlots() {
        return new int[] {13};
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.DRAGON_EGG);
    }
    
    @Override
    public int getProgressBarSlot() {
        return 4;
    }
    
}
