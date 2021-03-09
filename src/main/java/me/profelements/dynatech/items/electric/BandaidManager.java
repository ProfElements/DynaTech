package me.profelements.dynatech.items.electric;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.cscorelib2.data.PersistentDataAPI;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import me.profelements.dynatech.items.misc.ItemBand;

public class BandaidManager extends AMachine {

    public BandaidManager(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
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
                if (SlimefunItem.getByID(id) != null) {
                    SlimefunItem sfItem = SlimefunItem.getByID(id);
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
