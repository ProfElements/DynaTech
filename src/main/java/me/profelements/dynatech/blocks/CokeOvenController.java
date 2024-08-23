package me.profelements.dynatech.blocks;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import io.github.bakedlibs.dough.blocks.BlockPosition;
import io.github.bakedlibs.dough.collections.Pair;
import io.github.bakedlibs.dough.items.ItemUtils;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.registries.ItemGroups;
import me.profelements.dynatech.registries.Items;
import me.profelements.dynatech.registries.RecipeTypes;
import me.profelements.dynatech.registries.Registries;
import me.profelements.dynatech.utils.Recipe;

public class CokeOvenController extends SlimefunItem {

    static HashSet<Pair<BlockPosition, BlockFace>> validBlocks = new HashSet<>();

    static HashMap<BlockPosition, Optional<Recipe>> blockPosToMaybeRecipe = new HashMap<>();

    static HashMap<BlockPosition, Integer> blockPosToTimeLeft = new HashMap<>();

    public CokeOvenController(SlimefunItemStack stack) {
        super(ItemGroups.EXPERIMENTAL, stack, RecipeType.NULL, new ItemStack[] {});

        addItemHandler(onTick(), onUse(), onBreak());
    }

    public BlockTicker onTick() {
        return new BlockTicker() {

            @Override
            public boolean isSynchronized() {
                return true;
            }

            @Override
            public void tick(Block blk, SlimefunItem sfItem, Config cfg) {
                tickBlock(blk);
            }

        };
    }

    public BlockUseHandler onUse() {
        return new BlockUseHandler() {
            @Override
            public void onRightClick(PlayerRightClickEvent event) {
                if (validateMultiblock(event.getClickedBlock().get(), event.getPlayer().getFacing())) {
                    validBlocks.add(new Pair<>(new BlockPosition(event.getClickedBlock().get().getLocation()),
                            event.getPlayer().getFacing()));

                    BlockStorage.addBlockInfo(event.getClickedBlock().get(), "dynatech:valid", String.valueOf(true));
                    BlockStorage.addBlockInfo(event.getClickedBlock().get(), "dynatech:facing",
                            event.getPlayer().getFacing().toString());

                    event.getPlayer().sendMessage("Coke Oven multiblock is valid.");
                } else {

                    BlockStorage.addBlockInfo(event.getClickedBlock().get(), "dynatech:valid", String.valueOf(false));
                    event.getPlayer().sendMessage("Coke Oven multiblock is not valid.");
                }

                DynaTech.runSync(() -> {
                    event.getPlayer().closeInventory();
                });
            }
        };
    }

    public void tickBlock(Block blk) {

        // Check block storage for validity and add it to the Map if it is valid
        String boolStr = BlockStorage.getLocationInfo(blk.getLocation(), "dynatech:valid");
        String facingStr = BlockStorage.getLocationInfo(blk.getLocation(), "dynatech:facing");

        if (boolStr != null && facingStr != null) {
            Boolean valid = Boolean.valueOf(boolStr);
            BlockFace facing = BlockFace.valueOf(facingStr);

            if (valid) {
                validBlocks.add(new Pair<>(new BlockPosition(blk), facing));
            }
        }

        BlockPosition pos = new BlockPosition(blk);
        boolean isValid = !validBlocks.stream().filter((filter) -> {
            return filter.getFirstValue().equals(pos);
        }).toList().isEmpty();

        if (!isValid) {
            return;
        }

        Pair<BlockPosition, BlockFace> blockValid = validBlocks.stream().filter((filter) -> {
            return filter.getFirstValue().equals(pos);
        }).toList().get(0);

        BlockFace facing = blockValid.getSecondValue();
        BlockFace right = rotateCounterClockwise(facing);
        BlockFace left = right.getOppositeFace();

        Block inputBarrel = pos.getBlock().getRelative(BlockFace.UP).getRelative(facing).getRelative(left);
        Block outputBarrel = pos.getBlock().getRelative(BlockFace.UP).getRelative(facing).getRelative(right);

        Optional<Container> inputBarrelContainer = Optional.empty();
        Optional<Container> outputBarrelContainer = Optional.empty();

        if (PaperLib.getBlockState(inputBarrel, false).getState() instanceof Container container) {
            inputBarrelContainer = Optional.of(container);

        }

        if (PaperLib.getBlockState(outputBarrel, false).getState() instanceof Container oContainer) {
            outputBarrelContainer = Optional.of(oContainer);
        }

        if (inputBarrelContainer.isEmpty() || outputBarrelContainer.isEmpty()) {
            return;
        }

        Optional<Recipe> maybeRecipe = blockPosToMaybeRecipe.getOrDefault(pos, Optional.empty());

        if (maybeRecipe.isEmpty()) {

            List<Recipe> maybeRecipes = Registries.RECIPES.getEntries().stream().filter((recipe) -> {
                return recipe.getRecipeType().equals(RecipeTypes.OVENING);
            }).toList();

            List<ItemStack> itemsToRemove = new ArrayList<>();
            Inventory brlInv = inputBarrelContainer.get().getInventory();
            for (Recipe recipe : maybeRecipes) {
                for (ItemStack stack : recipe.getInput()) {
                    if (brlInv.containsAtLeast(stack, stack.getAmount())) {
                        itemsToRemove.add(stack);
                    }
                }
                if (recipe.getInput().length == itemsToRemove.size()) {

                    blockPosToMaybeRecipe.put(pos, Optional.of(recipe));
                    // 2 ticks per second at 80 seconds (160 ticks)
                    blockPosToTimeLeft.put(pos, 160);

                    for (int i = 0; i < brlInv.getSize(); i++) {
                        ItemStack invStack = brlInv.getItem(i);

                        if (SlimefunUtils.isItemSimilar(invStack, itemsToRemove.get(0), true)) {
                            ItemUtils.consumeItem(invStack, itemsToRemove.get(0).getAmount(), false);
                            return;
                        }
                    }

                    inputBarrelContainer.get().update();
                    return;
                }
                itemsToRemove.clear();

            }
        }
        if (maybeRecipe.isPresent())

        {
            int ticksLeft = blockPosToTimeLeft.get(pos);
            Location dropLoc = pos.getBlock().getRelative(facing.getOppositeFace()).getLocation();
            ticksLeft = ticksLeft - 1;
            if (ticksLeft <= 0) {

                Inventory oBrlInv = outputBarrelContainer.get().getInventory();
                blockPosToTimeLeft.remove(pos);
                blockPosToMaybeRecipe.remove(pos);

                for (ItemStack outStack : maybeRecipe.get().getOutput()) {
                    HashMap<Integer, ItemStack> map = oBrlInv.addItem(outStack);

                    if (!map.isEmpty()) {
                        for (ItemStack extraStacks : map.values()) {
                            pos.getWorld().dropItemNaturally(
                                    dropLoc, extraStacks);
                        }
                    }

                    outputBarrelContainer.get().update();
                    blockPosToMaybeRecipe.put(pos, Optional.empty());
                }
            } else {
                blockPosToTimeLeft.put(pos, ticksLeft);
            }
        }
    }

    public BlockBreakHandler onBreak() {
        return new BlockBreakHandler(false, false) {
            @Override
            public void onPlayerBreak(BlockBreakEvent event, ItemStack tool, List<ItemStack> drops) {
                validBlocks.removeIf(
                        (pair -> {
                            return pair.getFirstValue().equals(new BlockPosition(event.getBlock().getLocation()));
                        }));

                BlockStorage.clearBlockInfo(event.getBlock());
            }

        };
    }

    private boolean validateMultiblock(Block controllerBlock, BlockFace facing) {
        int width = 3;
        int depth = 3;
        int height = 3;

        BlockFace backSide = facing;
        BlockFace leftSide = rotateCounterClockwise(facing);
        BlockFace frontSide = rotateCounterClockwise(leftSide);
        BlockFace rightSide = rotateCounterClockwise(frontSide);

        // back back left
        Vector backCornerMod = new Vector(backSide.getModX() + backSide.getModX() + leftSide.getModX(),
                backSide.getModY() + backSide.getModY() + leftSide.getModY(),
                backSide.getModZ() + backSide.getModZ() + leftSide.getModZ());
        Vector rightSideMod = new Vector(rightSide.getModX(), rightSide.getModY(), rightSide.getModZ());
        Vector frontSideMod = new Vector(frontSide.getModX(), frontSide.getModY(), frontSide.getModZ());
        Location backLeftLocation = controllerBlock.getLocation().add(backCornerMod);

        /// Bottom Back Left to Top Forward Right (BBL to TFR)

        Predicate<Block>[][][] blockPreds = getMultiblockPattern();
        for (int z = 0; z < height; z++) {
            for (int y = 0; y < depth; y++) {
                for (int x = 0; x < width; x++) {
                    Predicate<Block> blockPredicate = blockPreds[y][x][z];

                    if (blockPredicate.test(backLeftLocation.getBlock())) {
                        backLeftLocation = backLeftLocation.add(rightSideMod);
                        continue;
                    } else {
                        return false;
                    }
                }
                backLeftLocation = backLeftLocation
                        .add(new Vector(leftSide.getModX(), leftSide.getModY(), leftSide.getModZ()).multiply(width))
                        .add(frontSideMod);
            }
            backLeftLocation = controllerBlock.getLocation().add(backCornerMod).add(new Vector(0, z + 1, 0));
        }

        return true;

    }

    private BlockFace rotateCounterClockwise(BlockFace facing) {
        switch (facing) {
            case BlockFace.NORTH:
                return BlockFace.WEST;
            case BlockFace.EAST:
                return BlockFace.NORTH;
            case BlockFace.SOUTH:
                return BlockFace.EAST;
            case BlockFace.WEST:
                return BlockFace.SOUTH;
            default:
                return BlockFace.SELF;
        }
    }

    Predicate<Block>[][][] getMultiblockPattern() {
        Predicate<Block>[][][] pattern = (Predicate<Block>[][][]) Array.newInstance(Predicate.class, 3, 3, 3);

        for (int y = 0; y < 3; y++) {
            for (int z = 0; z < 3; z++) {
                for (int x = 0; x < 3; x++) {
                    pattern[z][x][y] = blk -> (blk.getType().equals(Material.MUD_BRICKS));
                }
            }
        }

        Predicate<Block> isControl = blk -> (BlockStorage.checkID(blk)
                .equals(Items.COAL_COKE_OVEN.stack().getItemId()));
        pattern[2][1][0] = isControl;

        Predicate<Block> isBarrel = blk -> (blk.getType().equals(Material.BARREL));
        pattern[1][0][1] = isBarrel;
        pattern[1][2][1] = isBarrel;

        return pattern;
    }

}
