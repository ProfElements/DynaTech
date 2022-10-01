package me.profelements.dynatech.items.abstracts;

import javax.annotation.Nonnull;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.Validate;

public abstract class AbstractElectricTicker extends AbstractTicker implements EnergyNetComponent {
    
    private int energyConsumedPerTick = -1;
    private int energyCapacity = -1;
    private int processingSpeed = -1; 
    
    protected AbstractElectricTicker(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe); 
    }

    @Override
    protected boolean checkTickPreconditions(Block b) {
        return takeCharge(b.getLocation()); 
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    public int getCapacity() {
        return energyCapacity; 
    }

    public int getEnergyConsumption() {
        return energyConsumedPerTick;
    }
    
    public int getSpeed() {
        return processingSpeed;
    }

    public final AbstractElectricTicker setCapacity(int capacity) {
        Validate.isTrue(capacity > 0, "The capacity must be greater then 0");

        this.energyCapacity = capacity; 
        return this;
    }

    public final AbstractElectricTicker setConsumption(int consumption) {
        Validate.isTrue(getCapacity() > 0, "Capacity must be set before consumption");
        Validate.isTrue(consumption < getCapacity() && consumption != 0, "Consuption can not be greater then capacity"); 
        
        this.energyConsumedPerTick = consumption;
        return this;
    }

    public final AbstractElectricTicker setProcessingSpeed(int speed) {
        Validate.isTrue(speed > 0, "Speed must be greater then zero!"); 

        this.processingSpeed = speed; 
        return this; 
    }

    protected boolean takeCharge(Location l) {
        Validate.notNull(l, "Can't take energy from a null location"); 

        if (isChargeable()) {
            int charge = getCharge(l); 

             if (charge < getEnergyConsumption()) {
                return false;
             }

             setCharge(l, charge - getEnergyConsumption()); 
        }
        return true; 
    }
}
