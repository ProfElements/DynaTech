package me.profelements.dynatech.items.electric;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.NumberConversions;
import org.bukkit.util.Vector;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;

public class BarbedWire extends SlimefunItem implements EnergyNetComponent {

    public final ItemSetting<Integer> ENTITY_PUSH_FORCE = new ItemSetting<>(this, "entity-push-force", getDefaultEntityPushForce());
    public final ItemSetting<Integer> ENTITY_DETECT_RANGE = new ItemSetting<>(this, "entity-detect-range", getDefaultEntityDetectRange());
    public final ItemSetting<List<EntityType>> ENTITY_WHITELIST = new ItemSetting<>(this, "enity-blacklist", getDefaultEntityWhitelist());
    public final ItemSetting<Integer> BLOCK_ENERGY_CONSUMPTION = new ItemSetting<>(this, "block-energy-consumption", getDefaultEnergyConsumption());

    @ParametersAreNonnullByDefault
    public BarbedWire(ItemGroup group, SlimefunItemStack item,  RecipeType recipeType, ItemStack[] recipe) {
        super(group, item, recipeType, recipe);
        addItemSetting(ENTITY_PUSH_FORCE, ENTITY_DETECT_RANGE, BLOCK_ENERGY_CONSUMPTION);
    }

    private int getDefaultEntityPushForce() {
        return 10;
    }

    private int getDefaultEntityDetectRange() {
        return 9;
    }

    private List<EntityType> getDefaultEntityWhitelist() {
        ArrayList<EntityType> whitelist = new ArrayList<>();

        whitelist.add(EntityType.ARROW);
        whitelist.add(EntityType.BLAZE);
        whitelist.add(EntityType.CAVE_SPIDER);
        whitelist.add(EntityType.CREEPER);
        whitelist.add(EntityType.DRAGON_FIREBALL);
        whitelist.add(EntityType.DROWNED);
        whitelist.add(EntityType.EGG);
        whitelist.add(EntityType.ELDER_GUARDIAN);
        whitelist.add(EntityType.ENDERMAN);
        whitelist.add(EntityType.ENDERMITE);
        whitelist.add(EntityType.ENDER_PEARL);
        whitelist.add(EntityType.EVOKER);
        whitelist.add(EntityType.GHAST);
        whitelist.add(EntityType.GIANT);
        whitelist.add(EntityType.GUARDIAN);
        whitelist.add(EntityType.HOGLIN);
        whitelist.add(EntityType.HUSK);
        whitelist.add(EntityType.ILLUSIONER);
        whitelist.add(EntityType.LLAMA_SPIT);

        return whitelist;
    }
    private int getDefaultEnergyConsumption() {
        return 16;
    }

    @Override
    public int getCapacity() {
        return 128;
    }

    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    @Override
    public void preRegister() {
        super.preRegister();

        addItemHandler(new BlockTicker() {
            @Override
            public boolean isSynchronized() {
               return true;
            }

             @Override
             public void tick(Block block, SlimefunItem item, Config conf) {
                 BarbedWire.this.tick(block);
             }
        });
    }

    protected void tick(Block block) {
        int entityDetectRange = ENTITY_DETECT_RANGE.getValue();
        int energyConsumption = BLOCK_ENERGY_CONSUMPTION.getValue();
        List<EntityType> entityTypeWhitelist = ENTITY_WHITELIST.getValue();
        Location loc = block.getLocation();
        if (getCharge(loc) >= energyConsumption) {
            // Intentional. Remove it per tick whether or not it shields from mobs.
            removeCharge(loc, energyConsumption);
            for (Entity e : block.getWorld().getNearbyEntities(loc, entityDetectRange, entityDetectRange, entityDetectRange)) {
                if (entityTypeWhitelist.contains(e.getType())) {

                    /// Find the Direction Vector. normailze it. multiply by the push force. apply it to the entity.
                    Vector launchDirection = loc.subtract(e.getLocation()).toVector().normalize().multiply(ENTITY_PUSH_FORCE.getValue());

                    /// If any of the components are not finite just set it to zero.
                    if (NumberConversions.isFinite(launchDirection.getX()) && NumberConversions.isFinite(launchDirection.getY())
                        && NumberConversions.isFinite(launchDirection.getZ())) {
                        e.setVelocity(launchDirection);
                    } else {
                        e.setVelocity(new Vector(0, 0, 0));
                    }
                }
            }
        }
    }
}
