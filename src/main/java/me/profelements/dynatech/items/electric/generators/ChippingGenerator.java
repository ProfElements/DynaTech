package me.profelements.dynatech.items.electric.generators;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.profelements.dynatech.items.electric.abstracts.AMachineGenerator;

public class ChippingGenerator extends AMachineGenerator {

    public ChippingGenerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }

    
    @Override
    public int getGeneratedOutput(Location l, Config data) {
        BlockMenu inv = BlockStorage.getInventory(l.getBlock());
        int julesAmount = 0;

        for (int slot : getInputSlots()) {
            ItemStack item = inv.getItemInSlot(slot);
            if (item != null && item.getItemMeta() instanceof Damageable ) {
                Damageable im = (Damageable) item.getItemMeta();
                if (!im.hasDamage()) {
                    
                    julesAmount = item.getType().getMaxDurability()*2;
                    if (julesAmount != 0) {
                        inv.consumeItem(slot);
                        return julesAmount;
                    }
                    
                }

                julesAmount = (item.getType().getMaxDurability()- im.getDamage())*2;
                if (julesAmount != 0) {
                    inv.consumeItem(slot);
                    return julesAmount;
                }
                
            }
        }
        return 0;



    }


    @Override
    public String getMachineIdentifier() {
        return "CHIPPING_GENERATOR";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.WOODEN_AXE);
    }
    
}
