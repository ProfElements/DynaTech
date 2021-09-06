package me.profelements.dynatech.items.electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class BarbedWire extends AMachine {
    private static final int MAX_DIRECTION_VEL = 10000;

    public BarbedWire(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }
    
    
    @Override
    public void tick(Block b) {
        if (getCharge(b.getLocation()) < getEnergyConsumption())  {
            return;
        }
        
        DynaTech.runSync(()->sendEntitiesFlying(b.getLocation(), b.getWorld()));
        removeCharge(b.getLocation(), getEnergyConsumption());

    }

    public void sendEntitiesFlying(@Nonnull Location loc, @Nonnull World w) {
        List<Entity> shotEntities = new ArrayList<>();
        int waitTime = 0;
        for (Entity e : w.getNearbyEntities(loc, 9, 9, 9)) {
            Vector tempV = e.getVelocity();
            if (e.getType() != EntityType.PLAYER && e.getType() != EntityType.ARMOR_STAND && e.getType() != EntityType.DROPPED_ITEM && !shotEntities.contains(e)) {
                Vector tempV2 = tempV.multiply(-1).multiply(1.2).add(new Vector(1, 0.7, 1));
                
                if (tempV2.getX() >= MAX_DIRECTION_VEL || tempV2.getY() >= MAX_DIRECTION_VEL || tempV2.getZ() >= MAX_DIRECTION_VEL) {
                    tempV2 = new Vector(0, 0, 0);
                }


                if (NumberConversions.isFinite(tempV2.getX()) && NumberConversions.isFinite(tempV2.getY()) && NumberConversions.isFinite(tempV2.getZ())) {
                    e.setVelocity(tempV2);
                    shotEntities.add(e);  
                } else if (NumberConversions.isFinite(tempV.getX()) && NumberConversions.isFinite(tempV.getY()) && NumberConversions.isFinite(tempV.getZ())) {
                    e.setVelocity(tempV);
                } else {
                    e.setVelocity(new Vector(0, 0, 0));
                }

            }
            if (shotEntities.contains(e) && waitTime > 8) {
                e.setVelocity(tempV);
            }
            
			waitTime++;   
        }
    }


    @Override
    public String getMachineIdentifier() {
        return "BARBED_WIRE";
    }


    @Override
    public boolean isGraphical() {
        return false;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.IRON_BARS);
    }
    
}
