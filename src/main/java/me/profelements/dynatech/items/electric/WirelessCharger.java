package me.profelements.dynatech.items.electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WirelessCharger extends AMachine {

    private final double radius;

    public WirelessCharger(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, double radius) {
        super(itemGroup, item, recipeType, recipe);
        this.radius = radius;
    }

    @Override
    public void tick(Block b) {
        if (getCharge(b.getLocation()) < getEnergyConsumption()) {
            return;
        }

        for (Player p : b.getWorld().getPlayers()) {
            double distance = p.getLocation().distance(b.getLocation());

            if (distance <= radius) {
                for (ItemStack item : p.getInventory()) {
                    SlimefunItem sfItem = SlimefunItem.getByItem(item);

                    if (sfItem instanceof Rechargeable) {
                        Rechargeable rcItem = (Rechargeable) sfItem;
                        
                        if (rcItem.getItemCharge(item) != rcItem.getMaxItemCharge(item)) {
                            
                            removeCharge(b.getLocation(), getEnergyConsumption());
                            rcItem.addItemCharge(item, getEnergyConsumption());
                            p.updateInventory();

                        }
                    }
                }
            }
        }
    }

    @Override
    public String getMachineIdentifier() {
        return "WIRElESS_CHARGER";
    }

    @Override
    public boolean isGraphical() {
        return false;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.END_ROD);
    }
    
}
