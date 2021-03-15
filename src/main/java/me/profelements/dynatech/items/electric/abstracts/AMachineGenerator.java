package me.profelements.dynatech.items.electric.abstracts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemState;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunPlugin;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import io.github.thebusybiscuit.slimefun4.utils.NumberUtils;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.github.thebusybiscuit.slimefun4.utils.itemstack.ItemStackWrapper;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import me.mrCookieSlime.Slimefun.cscorelib2.chat.ChatColors;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.mrCookieSlime.Slimefun.cscorelib2.protection.ProtectableAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;

public abstract class AMachineGenerator extends SlimefunItem implements RecipeDisplayItem, EnergyNetProvider {

    public static Map<Block, MachineFuel> processing = new HashMap<>();
    public static Map<Block, Integer> progress = new HashMap<>();
    protected final Set<MachineFuel> fuelTypes = new HashSet<>();

    private int energyProducedPerTick = -1;
    private int energyCapacity = -1;

    private static final int[] BORDER = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 13, 31, 36, 37, 38, 39, 40, 41, 42, 43,
            44 };
    private static final int[] BORDER_IN = new int[] { 9, 10, 11, 12, 18, 21, 27, 28, 29, 30 };
    private static final int[] BORDER_OUT = new int[] { 14, 15, 16, 17, 23, 26, 32, 33, 34, 35 };
    private static final int PROGRESS_BAR_SLOT = 22;

    private static final int[] INPUT_SLOTS = new int[] { 19, 20 };
    private static final int[] OUTPUT_SLOTS = new int[] { 24, 25 };

    @ParametersAreNonnullByDefault
    public AMachineGenerator(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        if (isGraphical()) {
            new BlockMenuPreset(item.getItemId(), getInventoryTitle()) {
                @Override
                public void init() {
                    constructMenu(this);
                }

                @Override
                public void newInstance(BlockMenu menu, Block b) {
                    newMachineInstance(menu, b);
                }

                @Override
                public boolean canOpen(Block b, Player p) {
                    return p.hasPermission("slimefun.inventory.bypass") || SlimefunPlugin.getProtectionManager()
                            .hasPermission(p, b.getLocation(), ProtectableAction.INTERACT_BLOCK);
                }

                @Override
                public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                    if (flow == ItemTransportFlow.INSERT) {
                        return getInputSlots();
                    } else {
                        return getOutputSlots();
                    }
                }
            };

            addItemHandler(onBreak());

            registerDefaultFuelTypes();

        }
    }

    private BlockBreakHandler onBreak() {
        return new SimpleBlockBreakHandler() {

            @Override
            public void onBlockBreak(Block b) {
                BlockMenu inv = BlockStorage.getInventory(b);

                if (inv != null) {
                    inv.dropItems(b.getLocation(), getInputSlots());
                    inv.dropItems(b.getLocation(), getOutputSlots());
    
                }
    
                processing.remove(b);
                progress.remove(b);
            }
            
        };
    }

    public void newMachineInstance(BlockMenu menu, Block b) {
    }

    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : getBorders().get(0)) {
            preset.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : getBorders().get(1)) {
            preset.addItem(i, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : getBorders().get(2)) {
            preset.addItem(i, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        preset.addItem(getProgressBarSlot(), new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "),
                ChestMenuUtils.getEmptyClickHandler());

        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {

                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    return cursor == null || cursor.getType() == Material.AIR;
                }

            });

        }
    }

    public boolean isGraphical() {
        return true;
    }

    public MachineFuel getProcessing(Block b) {
        return processing.get(b);
    }

    public boolean isProcessing(Block b) {
        return progress.get(b) != null;
    }

    @Override
    public int getGeneratedOutput(Location l, @Nonnull Config data) {
        BlockMenu inv = BlockStorage.getInventory(l.getBlock());

        if (isProcessing(l.getBlock())) {
            int timeleft = progress.get(l.getBlock());

            if (timeleft > 0) {
                ChestMenuUtils.updateProgressbar(inv, getProgressBarSlot(), timeleft, processing.get(l.getBlock()).getTicks(), getProgressBar());

                if (isChargeable()) {
                    int charge = getCharge(l, data);

                    if (getCapacity() - charge >= getEnergyProduction()) {
                        progress.put(l.getBlock(), timeleft - 1);
                        return getEnergyProduction();
                    }

                    return 0;
                } else {
                    progress.put(l.getBlock(), timeleft - 1);
                    return getEnergyProduction();
                }
            } else {
                ItemStack fuel = processing.get(l.getBlock()).getInput();

                if (isBucket(fuel)) {
                    inv.pushItem(new ItemStack(Material.BUCKET), getOutputSlots());
                }

                inv.replaceExistingItem(getProgressBarSlot(), new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "));
                
                progress.remove(l.getBlock());
                processing.remove(l.getBlock());
                return 0;
            }
        } else {
            Map<Integer, Integer> found = new HashMap<>();
            MachineFuel fuel = findRecipe(inv, found);

            if (fuel != null) {
                for (Map.Entry<Integer, Integer> entry : found.entrySet()) {
                    inv.consumeItem(entry.getKey(), entry.getValue());
                }

                processing.put(l.getBlock(), fuel);
                progress.put(l.getBlock(), fuel.getTicks());
            }

            return 0;
        }
        
    }

    private boolean isBucket(@Nullable ItemStack item) {
        if (item == null) {
            return false;
        }

        ItemStackWrapper wrapper = new ItemStackWrapper(item);
        return item.getType() == Material.LAVA_BUCKET || SlimefunUtils.isItemSimilar(wrapper, SlimefunItems.FUEL_BUCKET, true) || SlimefunUtils.isItemSimilar(wrapper, SlimefunItems.OIL_BUCKET, true);
    }

    public MachineFuel findRecipe(BlockMenu menu, Map<Integer, Integer> found) {
        for (MachineFuel fuel : fuelTypes) {
            for (int slot : getInputSlots()) {
                if (fuel.test(menu.getItemInSlot(slot))) {
                    found.put(slot, fuel.getInput().getAmount());
                    return fuel;
                }
            }
        }

        return null;
    }

    protected void registerDefaultFuelTypes() {}
    
    public void registerFuel(@Nonnull MachineFuel fuel) {
        Validate.notNull(fuel, "Machine fuel cannot be null.");
        fuelTypes.add(fuel);
    }

    @Nonnull
    public Set<MachineFuel> getFuelTypes() {
        return fuelTypes;
    }

    @Nonnull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> list =  new ArrayList<>();

        for (MachineFuel fuel : fuelTypes) {
            ItemStack item = fuel.getInput().clone();
            ItemMeta im = item.getItemMeta();
            List<String> lore = new ArrayList<>();
            lore.add(ChatColors.color("&8\u21E8 &7Lasts " + NumberUtils.getTimeLeft(fuel.getTicks() / 2)));
            lore.add(ChatColors.color("&8\u21E8 &e\u26A1 &7" + getEnergyProduction() * 2) + " J/s");
            lore.add(ChatColors.color("&8\u21E8 &e\u26A1 &7" + fuel.getTicks() * getEnergyProduction()) + " J in total");
            im.setLore(lore);
            item.setItemMeta(im);
            list.add(item);
        }

        return list;
    }

    //Generic Getters
    public String getInventoryTitle() {
        return getItemName();
    }

    public abstract String getMachineIdentifier();

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.GENERATOR;
    }

    //BlockMenu Getters
    @Nonnull
    public abstract ItemStack getProgressBar();

    public List<int[]> getBorders() {
        List<int[]> borders = new ArrayList<>();
        borders.add(BORDER); //BORDER
        borders.add(BORDER_IN); //BORDER_IN
        borders.add(BORDER_OUT); //BORDER_OUT

        return borders;
    }

    public int getProgressBarSlot() {
        return PROGRESS_BAR_SLOT;
    }

    public int[] getInputSlots() {
        return INPUT_SLOTS;
    }

    public int[] getOutputSlots() {
        return OUTPUT_SLOTS;
    }


    //Registry Stuff
    public int getCapacity() {
        return energyCapacity;
    }

    public int getEnergyProduction() {
        return energyProducedPerTick;
    }

    public final AMachineGenerator setEnergyCapacity(int capacity) {
        Validate.isTrue(capacity > 0, "Energy capacity must be greater then 0");

        if(getState() == ItemState.UNREGISTERED) {
            this.energyCapacity = capacity;
            return this;
        } else {
            throw new IllegalStateException("You cannot modify already registered items.");
        }

    }

    public final AMachineGenerator setEnergyProduction(int energyProduction) {
        Validate.isTrue(energyProduction > 0, "Energy production must be greater then 0");
        Validate.isTrue(energyCapacity > 0, "Energy capacity must be specified before energy production");
        Validate.isTrue(energyProduction <= energyCapacity, "Energy production can not be greater the energy capacity.");

        this.energyProducedPerTick = energyProduction;
        return this;

    }

    @Override
    public void register(@Nonnull SlimefunAddon slimefunAddon) {
        this.addon = slimefunAddon;

        if (getCapacity() <= 0) {
            warn("The capacity has not been configured correctly. The Item was disabled.");
            warn("Make sure to call '" + getClass().getSimpleName() + "#setEnergyCapacity(...)' before registering!");
        }

        if (getEnergyProduction() <= 0) {
            warn("The energy production has not been configured correctly. The Item was disabled.");
            warn("Make sure to call '" + getClass().getSimpleName() + "#setEnergyConsumption(...)' before registering!");
        }

        if (getCapacity() > 0 && getEnergyProduction() > 0) {
            super.register(addon);
        }

    }

}
