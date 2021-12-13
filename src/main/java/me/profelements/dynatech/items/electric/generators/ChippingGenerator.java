package me.profelements.dynatech.items.electric.generators;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.profelements.dynatech.items.electric.abstracts.AMachineGenerator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;

public class ChippingGenerator extends AMachineGenerator {

    private final int powerPerDurability = 8;

    private final ItemStack progressBar = new ItemStack(Material.WOODEN_AXE);

    public ChippingGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }


    @Override
    public int getGeneratedOutput(Location l, Config data) {
        BlockMenu inv = BlockStorage.getInventory(l.getBlock());
        int julesAmount;

        for (int slot : getInputSlots()) {
            ItemStack item = inv.getItemInSlot(slot);
            // Do as many lightweight checks as possible before we do the intensive stuff
            if (item != null && !item.getType().isAir() && item.getType().isItem() && item.hasItemMeta()) {
                // `getItemMeta` does multiple clones! Even doing this once is slow, nevermind multiple times!
                ItemMeta meta = item.getItemMeta();
                if (meta instanceof Damageable && !meta.isUnbreakable()) {
                    Damageable im = (Damageable) meta;
                    if (!im.hasDamage()) {
                        julesAmount = item.getType().getMaxDurability() * powerPerDurability;
                        if (julesAmount != 0) {
                            inv.consumeItem(slot);
                            return julesAmount;
                        }
                    }

                    julesAmount = (item.getType().getMaxDurability() - im.getDamage()) * 2;
                    if (julesAmount != 0) {
                        inv.consumeItem(slot);
                        return julesAmount;
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public String getMachineIdentifier() {
        return "CHIPPING_GENERATOR";
    }

    @Nonnull
    @Override
    public ItemStack getProgressBar() {
        return progressBar;
    }
}
