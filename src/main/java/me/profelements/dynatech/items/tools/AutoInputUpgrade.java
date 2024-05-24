package me.profelements.dynatech.items.tools;

import java.util.Optional;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.profelements.dynatech.utils.Recipe;

public class AutoInputUpgrade extends SlimefunItem {
    public AutoInputUpgrade(ItemGroup group, SlimefunItemStack item, Recipe recipe) {
        super(group, item, recipe.getRecipeType(), recipe.getInput());

        addItemHandler(onUse());
    }

    public ItemUseHandler onUse() {
        return e -> {
            Optional<Block> optBlock = e.getClickedBlock();
            if (optBlock.isPresent()) {
                Block block = optBlock.get();

                String upgrades = BlockStorage.getLocationInfo(block.getLocation(), "upgrades");

                if (upgrades != null && upgrades.contains("id:auto_input")) {
                    return;
                }

                String blockFaceString = AutoOutputUpgrade.blockFaceToString(e.getClickedFace());
                if (blockFaceString == "invalid") {
                    return;
                }
                if (upgrades != null) {
                    BlockStorage.addBlockInfo(block, "upgrades",
                            upgrades + "," + "{id:auto_input,face:" + blockFaceString + "}");
                } else {

                    BlockStorage.addBlockInfo(block, "upgrades", "{id:auto_input,face:" + blockFaceString + "}");
                }
            }

            ItemStack stack = e.getItem();
            int amount = stack.getAmount();

            if (amount > 1) {
                stack.setAmount(amount - 1);
            } else {
                e.getPlayer().getInventory().remove(stack);
            }
        };
    }

}
