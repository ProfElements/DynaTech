package me.profelements.dynatech.items.tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.attributes.Rechargeable;
import io.github.thebusybiscuit.slimefun4.core.handlers.EntityInteractHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import me.profelements.dynatech.DynaTechItems;
import org.bukkit.Sound;
import org.bukkit.entity.Bee;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Scoop extends SlimefunItem implements Rechargeable, NotPlaceable {

    public Scoop(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        addItemHandler(getItemHandler());
    }

    public EntityInteractHandler getItemHandler() {
        return (e, item, offhand) -> {
            if (getItemCharge(item) < 8) {
                return;
            }

            Entity entity = e.getRightClicked();
            
            if (e.isCancelled() || !Slimefun.getProtectionManager().hasPermission(e.getPlayer(), entity.getLocation(), Interaction.INTERACT_ENTITY)) {
                return;
            }

            Player p = e.getPlayer();

            if (entity instanceof Bee) {
                
                entity.getWorld().dropItemNaturally(entity.getLocation(), DynaTechItems.BEE);
                entity.remove();
                removeItemCharge(item, 8);

                p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);
            }
        };
        
    }

    @Override
    public float getMaxItemCharge(ItemStack item) {
        return 512;
    }
        
}
