package me.profelements.dynatech.items.electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.data.persistent.PersistentDataAPI;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import me.profelements.dynatech.items.misc.ItemBand;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BandaidManager extends AMachine {

    public BandaidManager(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public MachineRecipe findNextRecipe(BlockMenu inv) {

        ItemStack target = inv.getItemInSlot(getInputSlots()[0]);
        if (target != null) {
            ItemStack itemBand = inv.getItemInSlot(getInputSlots()[1]);
            if (itemBand != null) {
                SlimefunItem sfBand = SlimefunItem.getByItem(itemBand);
                if (sfBand != null) {
                    if (sfBand instanceof ItemBand) {
                        ItemBand band = (ItemBand) sfBand;
                        ItemStack result = band.applyToItem(target).clone();

                        inv.consumeItem(getInputSlots()[0]);
                        inv.consumeItem(getInputSlots()[1]);

                        return new MachineRecipe(30, new ItemStack[] {target, itemBand}, new ItemStack[] {result});
                    }
                    
                }
            } else if (ItemBand.containsItemBand(target)) {
                String id = PersistentDataAPI.getString(target.getItemMeta(), ItemBand.KEY);
                if (SlimefunItem.getById(id) != null) {
                    SlimefunItem sfItem = SlimefunItem.getById(id);
                    ItemStack result = ItemBand.removeFromItem(target).clone();

                    inv.consumeItem(getInputSlots()[0]);

                    return new MachineRecipe(60, new ItemStack[] {target}, new ItemStack[] {result, sfItem.getItem()});
                }

            }
        }
        return null;
    }

    @Override
    public String getMachineIdentifier() {
        return "BANDAID_MANAGER";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.PHANTOM_MEMBRANE);
    }
    
}
