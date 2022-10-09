package me.profelements.dynatech.items.electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.abstracts.AbstractElectricTicker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Nonnull;

public class PotionSprinkler extends AbstractElectricTicker {

    //TODO: Refactor this based arouhnd the same Ideas as AntigravityBubble

    private final Map<Location, Set<UUID>> enabledEntities = new HashMap<>();
    private int plyrsApplied = 0;

    private static final int[] BACKGROUND_SLOTS = new int[] { 1, 2, 6, 7, 9, 10, 11, 15, 16, 17, 19, 20, 24, 25 };
    private static final int[] INPUT_BORDER_SLOTS = new int[] { 3, 4, 5, 12, 14, 21, 22, 23 };
    private static final int[] OUTPUT_BORDER_SLOTS = new int[] { 0, 8, 18, 26 };

    public PotionSprinkler(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        new BlockMenuPreset(getId(), getItemName()) {
            @Override
            public void init() {
                setupMenu(this); 
            }


			@Override
            public boolean canOpen(Block b, Player p) {
                return p.hasPermission("slimefun.inventory.bypass") || Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.INTERACT_BLOCK); 
            }
            
            @Nonnull
            @Override
            public int[] getSlotsAccessedByItemTransport(ItemTransportFlow flow) {
                if (flow == ItemTransportFlow.INSERT) {
                    return new int[] {13};
                } else {
                    return null; 
                }
            }

        };
    }

    @Override
    protected void onPlace(BlockPlaceEvent e, Block blockPlaced) {
        enabledEntities.put(blockPlaced.getLocation(), new HashSet<>()); 
    }

    @Override
    protected void onBreak(BlockBreakEvent e, Location l) {
        enabledEntities.remove(l);
        
    }



    private void setupMenu(BlockMenuPreset preset) {
    	for (int slot : BACKGROUND_SLOTS) {
            preset.addItem(slot, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int slot : INPUT_BORDER_SLOTS) {
            preset.addItem(slot, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int slot : OUTPUT_BORDER_SLOTS) {
            preset.addItem(slot, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        } 
    }

    @Override
    public void tick(Block b, SlimefunItem sfItem) {
        if (getCharge(b.getLocation()) < getEnergyConsumption()) {
            return;
        }

        BlockMenu menu = BlockStorage.getInventory(b);
        ItemStack item = menu.getItemInSlot(13);

        if (item != null && item.getType() == Material.POTION && item.hasItemMeta() && item.getItemMeta() instanceof PotionMeta) {
            PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
            PotionData pd = potionMeta.getBasePotionData();
            for (Entity ent : b.getWorld().getNearbyEntities(b.getLocation(), 10, 10, 10, e -> (e instanceof LivingEntity))) {
                LivingEntity p = (LivingEntity) ent;
                if (!enabledEntities.get(b.getLocation()).contains(p.getUniqueId())) {
                    int amplifier = pd.isUpgraded() ? 1 : 0;
                    int duration = pd.isExtended() ? 9600 : 3600;
                    PotionEffectType pet = pd.getType().getEffectType();

                    if (pet != null) {
                        PotionEffect pe = new PotionEffect(pet, duration, amplifier);
                        applyPotionEffect(pe, p);
                        enabledEntities.get(b.getLocation()).add(p.getUniqueId());
                    }
                    
                }
            }
            if (plyrsApplied > 8) {
                menu.consumeItem(13);
                plyrsApplied = 0;
            }
        }

        enabledEntities.getOrDefault(b.getLocation(), new HashSet<>()).removeIf(uuid -> (Bukkit.getEntity(uuid) != null 
                    && Bukkit.getEntity(uuid) instanceof LivingEntity 
                    && ((LivingEntity) Bukkit.getEntity(uuid)).getActivePotionEffects().isEmpty())); 
    }

    private void applyPotionEffect(PotionEffect pe, LivingEntity livingEntity) {
        pe.apply(livingEntity);
        plyrsApplied++;
    }

    @Override
    protected boolean isSynchronized() {
        return true;
    }
}
