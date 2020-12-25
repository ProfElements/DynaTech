package me.profelements.dynatech.items.electric;

import io.github.thebusybiscuit.exoticgarden.ExoticGardenRecipeTypes;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.profelements.dynatech.items.electric.abstracts.AMachine;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class AutoKitchen extends AMachine {

    private static final MultiBlockMachine mblock = (MultiBlockMachine) ExoticGardenRecipeTypes.KITCHEN.getMachine();

    public static final int[] BORDER = {4,5,6,7,8,13,31,40,41,42,43,44};
    private static final int[] BORDER_IN = {0,1,2,3,12,21,30,36,37,38,39};
    private static final int[] BORDER_OUT = {14,15,16,17,23,26,32,33,34,35};

    public AutoKitchen(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
    }
    @Override
    public void tick(Block b) {
        if (getCharge(b.getLocation()) < getEnergyConsumption()) {
            return;
        }
        craftIfValid(b);
    }

    private void craftIfValid(Block block) {
        BlockMenu menu = BlockStorage.getInventory(block);

        for (int outSlot : getOutputSlots()) {
            ItemStack outItem = menu.getItemInSlot(outSlot);
            if (outItem == null || outItem.getAmount() < outItem.getMaxStackSize()) {
                break;
            } else if (outSlot == getOutputSlots()[1]) {
                return;
            }
        }

        for (ItemStack[] input : RecipeType.getRecipeInputList(mblock)) {
            if (isCraftable(menu, input)) {
                ItemStack output = RecipeType.getRecipeOutputList(mblock, input).clone();
                craft(output, menu);
                removeCharge(block.getLocation(), getEnergyConsumption());
                return;
            }
        }
    }

    private boolean isCraftable(BlockMenu inv, ItemStack[] recipe) {
        for (int i = 0; i < 9; i++) {
            ItemStack item = inv.getItemInSlot(getInputSlots()[i]);
            if ((item != null && item.getAmount() == 1)
                    || !SlimefunUtils.isItemSimilar(inv.getItemInSlot(getInputSlots()[i]), recipe[i], true)) {
                return false;
            }
        }

        return true;
    }

    private void craft(ItemStack output, BlockMenu inv) {
        for (int i = 0; i < 9; i++) {
            ItemStack item = inv.getItemInSlot(getInputSlots()[i]);

            if (item != null && item.getType() != Material.AIR) {
                inv.consumeItem(getInputSlots()[i]);
            }
        }

        inv.pushItem(output, getOutputSlots());
    }


    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.SHEARS);
    }

    @Override
    public int getProgressBarSlot() {
        return 22;
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
    public int[] getOutputSlots() {
        return new int[] {24,25};
    }

    @Override
    public int[] getInputSlots() {
        return new int[] {9,10,11,18,19,20,27,28,29};
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "AUTO_KITCHEN";
    }
}
