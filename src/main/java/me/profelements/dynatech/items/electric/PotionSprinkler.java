package me.profelements.dynatech.items.electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PotionSprinkler extends AMachine {

    //TODO: Refactor this based arouhnd the same Ideas as AntigravityBubble

    private final Set<UUID> enabledPlayers = new HashSet<>();
    private int plyrsApplied = 0;

    private static final int[] BORDER = new int[] { 1, 2, 6, 7, 9, 10, 11, 15, 16, 17, 19, 20, 24, 25 };
    private static final int[] BORDER_IN = new int[] { 3, 4, 5, 12, 14, 21, 22, 23 };
    private static final int[] BORDER_OUT = new int[] { 0, 8, 18, 26 };

    public PotionSprinkler(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void tick(Block b) {
        if (getCharge(b.getLocation()) < getEnergyConsumption()) {
            return;
        }

        BlockMenu menu = BlockStorage.getInventory(b);
        ItemStack item = menu.getItemInSlot(getInputSlots()[0]);

        if (item != null && item.getType() == Material.POTION && item.hasItemMeta()) {
            PotionMeta potionMeta = (PotionMeta) item.getItemMeta();
            if (potionMeta != null) {
                PotionData pd = potionMeta.getBasePotionData();
                for (Player p : b.getWorld().getPlayers()) {
                    double distance = b.getLocation().distance(p.getLocation());
                    if (distance < 10 && !enabledPlayers.contains(p.getUniqueId())) {
                        int amplifier = pd.isUpgraded() ? 1 : 0;
                        int duration = pd.isExtended() ? 9600 : 3600;
                        PotionEffectType pet = pd.getType().getEffectType();

                        if (pet != null) {
                            PotionEffect pe = new PotionEffect(pet, duration, amplifier);
                            DynaTech.runSync(() -> applyPotionEffect(pe, p));
                            enabledPlayers.add(p.getUniqueId());
                        }
                        
                    }
                }
            }
            if (plyrsApplied > 8) {
                menu.consumeItem(getInputSlots()[0]);
                plyrsApplied = 0;
            }
        }

        final Iterator<UUID> playerIterator = enabledPlayers.iterator();
        while (playerIterator.hasNext()) {
            final UUID uuid = playerIterator.next();
            Player p = Bukkit.getPlayer(uuid);

            if (p != null && p.getActivePotionEffects().isEmpty()) {
                playerIterator.remove();
            }
        }


    }

    private void applyPotionEffect(PotionEffect pe, LivingEntity livingEntity) {
        pe.apply(livingEntity);
        plyrsApplied++;
    }

    @Override
    public String getMachineIdentifier() {
        return "POTION_SPRINKLER";
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
    public int[] getInputSlots() {
        return new int[] {13};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[] {13};
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.GLASS_BOTTLE);
    }
    
    @Override
    public int getProgressBarSlot() {
        return 4;
    }

}
