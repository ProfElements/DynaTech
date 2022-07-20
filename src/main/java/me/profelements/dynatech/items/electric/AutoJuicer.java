package me.profelements.dynatech.items.electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotHopperable;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class AutoJuicer extends AMachine implements NotHopperable {

    private static final MultiBlockMachine mblock = (MultiBlockMachine) RecipeType.JUICER.getMachine();

    private static final int[] BORDER = {0, 1, 2, 3, 4, 13, 31, 36, 37, 38, 39, 40};
    private static final int[] BORDER_IN = {9, 10, 11, 12, 18, 21, 27, 28, 29, 30};
    private static final int[] BORDER_OUT = {5, 6, 7, 8, 14, 23, 32, 41, 42, 43, 44};

    public AutoJuicer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void tick(Block b) {
        if(getCharge(b.getLocation()) < getEnergyConsumption()) {
            return;
        }
        craftIfValid(b);
    }

    private void craftIfValid(Block block) {
        BlockMenu menu = BlockStorage.getInventory(block);

        for(int outslot : getOutputSlots()) {
            ItemStack outItem = menu.getItemInSlot(outslot);
            if (outItem == null || outItem.getAmount() < outItem.getMaxStackSize()) {
                break;
            } else if (outslot == getOutputSlots()[8]) {
                return;
            }
        }
        for(ItemStack[] input : RecipeType.getRecipeInputList(mblock)) {
            if(isCraftable(menu, input)) {
                ItemStack output = RecipeType.getRecipeOutputList(mblock, input).clone();
                craft(output, menu);
                removeCharge(block.getLocation(), getEnergyConsumption());
                return;
            }
        }
    }

    private void craft(ItemStack output, BlockMenu inv) {
        for(int i = 0; i < 2; i++) {
            ItemStack item = inv.getItemInSlot(getInputSlots()[i]);

            if(item != null && item.getType() != Material.AIR) {
                inv.consumeItem(getInputSlots()[i]);
            }
        }
        inv.pushItem(output, getOutputSlots());
    }

    private boolean isCraftable(BlockMenu inv, ItemStack[] recipe) {
        for(int i = 0; i < 2; i++) {
            ItemStack item = inv.getItemInSlot(getInputSlots()[i]);
            if ((item != null && item.getAmount() == 1)
                    || !SlimefunUtils.isItemSimilar(item, recipe[i], true)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.GLASS_BOTTLE);
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
        return new int[] {15,16,17,24,25,26,33,34,35};
    }

    @Override
    public int[] getInputSlots() {
        return new int[] {19,20};
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "AUTO_JUICER";
    }
}
