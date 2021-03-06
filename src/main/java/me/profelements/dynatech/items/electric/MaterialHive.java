package me.profelements.dynatech.items.electric;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactive;
import io.github.thebusybiscuit.slimefun4.core.attributes.Radioactivity;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.cscorelib2.item.CustomItem;
import me.profelements.dynatech.DynaTechItems;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import me.profelements.dynatech.items.misc.Bee;

public class MaterialHive extends AMachine implements Radioactive {

    private static final int[] BORDER = new int[] {0,1,2,6,7,8,31,36,37,38,39,40,41,42,43,44};
    private static final int[] BORDER_IN = new int[] {9,10,11,12,18,21,27,28,29,30};
    private static final int[] BORDER_OUT = new int[] {14,15,16,17,23,26,32,33,34,35};
    private static final int[] BORDER_KEY = new int[] {3,5,13};

    private static final int[] INPUT_SLOTS = new int[] {19,20,4};

    public static final ItemSetting<List<String>> vanillaItemsAccepted = new ItemSetting<>("vanilla-items-accepted", getDefaultAllowedVanillaItems());
    public static final ItemSetting<List<String>> slimefunItemsAccepted = new ItemSetting<>("slimefun-items-accepted", getDefaultAllowedSlimefunItems());
    
    public MaterialHive(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemSetting(vanillaItemsAccepted);
        addItemSetting(slimefunItemsAccepted);
    }

    @Override
    public MachineRecipe findNextRecipe(BlockMenu inv) {
        ItemStack key = inv.getItemInSlot(getInputSlots()[2]);
        if (key != null && key.getAmount() == 64) {
            int secondRemovalAmount = 0;
            if ((SlimefunItem.getByItem(key) == null && vanillaItemsAccepted.getValue().contains(key.getType().toString())) || slimefunItemsAccepted.getValue().contains(SlimefunItem.getByItem(key).getId())) {
                
                boolean isSlimefunItem = SlimefunItem.getByItem(key) != null ? true :  false;
                
                ItemStack output = isSlimefunItem ? SlimefunItem.getByItem(key).getItem().clone() : new ItemStack(key.getType(), 1);
                ItemStack keyInput = isSlimefunItem ? SlimefunItem.getByItem(key).getItem().clone() : new ItemStack(key.getType(), 64);
                keyInput.setAmount(64);
                 
                ItemStack bee1 = inv.getItemInSlot(getInputSlots()[0]);
                ItemStack bee2 = inv.getItemInSlot(getInputSlots()[1]);  
                if (isBee(bee1)) {
                    Bee dtBee = (Bee) SlimefunItem.getByItem(bee1);
                    secondRemovalAmount += dtBee.getSpeedMultipler() * (bee1.getAmount() - 1);

                    if (isBee(bee2)) {
                        Bee dtBee2 = (Bee) SlimefunItem.getByItem(bee2);
                        secondRemovalAmount += dtBee2.getSpeedMultipler() * (bee2.getAmount() - 1); 
                    }

                    if (bee1 != null && bee1.getAmount() == 64 && bee2 != null && bee2.getAmount() == 64) {
                        SlimefunItem sfBee1 = SlimefunItem.getByItem(bee1);
                        SlimefunItem sfBee2 = SlimefunItem.getByItem(bee2);

                        if ( sfBee1 != null && sfBee2 != null && sfBee1.getId() == sfBee2.getId()) {
                            switch (sfBee1.getId()) {
                                case "BEE":
                                    return new MachineRecipe(1500, new ItemStack[] {DynaTechItems.BEE, keyInput}, new ItemStack[] {output});
                                case "ROBOTIC_BEE":
                                    return new MachineRecipe(900, new ItemStack[] {DynaTechItems.BEE, keyInput}, new ItemStack[] {output});
                                
                                case "ADVANCED_ROBOTIC_BEE":
                                    return new MachineRecipe(600, new ItemStack[] {DynaTechItems.BEE, keyInput}, new ItemStack[] {output});
                                
                                default:
                                    break;
                            }
                        } else {
                            return new MachineRecipe(1800 - secondRemovalAmount, new ItemStack[] {DynaTechItems.BEE, keyInput}, new ItemStack[] {output});
                        }
                    } else {
                        return new MachineRecipe(1800 - secondRemovalAmount, new ItemStack[] {DynaTechItems.BEE, keyInput}, new ItemStack[] {output});
                    }
                }
            }
            secondRemovalAmount = 0;
        }
        return null;
    }

    @Override
    public void constructMenu(BlockMenuPreset preset) {
        for (int i : getBorders().get(0)) {
            preset.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : getBorders().get(1)) {
            preset.addItem(i, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : getBorders().get(2)) {
            preset.addItem(i, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : getBorders().get(3)) {
            preset.addItem(i, new SlimefunItemStack("_UI_KEY", Material.LIGHT_BLUE_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());
        }

        preset.addItem(getProgressBarSlot(), new CustomItem(Material.BLACK_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());

        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new ChestMenu.AdvancedMenuClickHandler() {

                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    return cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR;
                }

            });

        }
    }
    
    @Override
    public List<int[]> getBorders() {
        List<int[]> borders = new ArrayList<>();
        borders.add(BORDER);
        borders.add(BORDER_IN);
        borders.add(BORDER_OUT);
        borders.add(BORDER_KEY);

        return borders;
    }

    @Override
    public int[] getInputSlots() {
        return INPUT_SLOTS;
    }

    @Override
    public Radioactivity getRadioactivity() {
        return Radioactivity.HIGH;
    }

    @Override
    public String getMachineIdentifier() {
        return "MATERIAL_HIVE";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.NETHERITE_HOE);
    }

    @Override
    public boolean isInputConsumed() {
        return false;
    }

    private static List<String> getDefaultAllowedVanillaItems() {
        List<String> materialsAllowed = new ArrayList<>();

        materialsAllowed.add("IRON_INGOT");
        materialsAllowed.add("GOLD_INGOT");
        materialsAllowed.add("NETHERITE_INGOT");
        materialsAllowed.add("DIAMOND");
        materialsAllowed.add("EMERALD");
        materialsAllowed.add("LAPIS_LAZULI");
        materialsAllowed.add("QUARTZ");
        materialsAllowed.add("REDSTONE");
        materialsAllowed.add("COAL");

        return materialsAllowed;
    }

    private static List<String> getDefaultAllowedSlimefunItems() {
        List<String> sfItemsAllowed = new ArrayList<>();

        //Ingots
        sfItemsAllowed.add("COPPER_INGOT");
        sfItemsAllowed.add("TIN_INGOT");
        sfItemsAllowed.add("SILVER_INGOT");
        sfItemsAllowed.add("ALUMINUM_INGOT");
        sfItemsAllowed.add("LEAD_INGOT");
        sfItemsAllowed.add("ZINC_INGOT");
        sfItemsAllowed.add("MAGNESIUM_INGOT");

        //Alloys
        sfItemsAllowed.add("STEEL_INGOT");
        sfItemsAllowed.add("DURALUMIN_INGOT");
        sfItemsAllowed.add("BILLION_INGOT");
        sfItemsAllowed.add("BRASS_INGOT");
        sfItemsAllowed.add("ALUMINUM_BRASS_INGOT");
        sfItemsAllowed.add("ALUMINUM_BRONZE_INGOT");
        sfItemsAllowed.add("CORINTHIAN_BRONZE_INGOT");
        sfItemsAllowed.add("SOLDER_INGOT");
        sfItemsAllowed.add("DAMASCUS_STEEL_INGOT");
        sfItemsAllowed.add("HARDENED_METAL_INGOT");
        sfItemsAllowed.add("REINFORCED_ALLOY_INGOT");
        sfItemsAllowed.add("FERROSILICON");
        sfItemsAllowed.add("GILDED_IRON");
        sfItemsAllowed.add("NICKEL_INGOT");
        sfItemsAllowed.add("COBALT_INGOT");
        
        //Gems
        sfItemsAllowed.add("SYNTHETIC_DIAMOND");
        sfItemsAllowed.add("SYNTHETIC_EMERALD");
        sfItemsAllowed.add("SYNTHETIC_SAPPHIRE");
        sfItemsAllowed.add("CARBONADO");

        return sfItemsAllowed;
    }

    private boolean isBee(ItemStack item) {
        if (item != null && item.getType() != Material.AIR) {
            SlimefunItem sfItem = SlimefunItem.getByItem(item);
            if (sfItem != null && sfItem instanceof Bee) {
                return true;
            }
        }
        return false;
    }
    
}
