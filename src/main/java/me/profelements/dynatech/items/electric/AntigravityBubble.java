package me.profelements.dynatech.items.electric;

import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.Objects.handlers.ItemHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.profelements.dynatech.items.electric.abstracts.AMachine;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;

import java.util.*;

public class AntigravityBubble extends AMachine {

    private final Set<UUID> enabledPlayers = new HashSet<>();

    private static final int[] BORDER = new int[] { 1, 2, 6, 7, 9, 10, 11, 15, 16, 17, 19, 20, 24, 25 };
    private static final int[] BORDER_IN = new int[] { 3, 4, 5, 12, 14, 21, 22, 23 };
    private static final int[] BORDER_OUT = new int[] { 0, 8, 18, 26 };

    public AntigravityBubble(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemHandler(onBreak());
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {

            @Override
            public void tick(Block b, SlimefunItem  sfItem, Config data) {
                AntigravityBubble.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        });
    }

    @Override
    public void tick(Block b) {
        Collection<Entity> bubbledEntities = b.getWorld().getNearbyEntities(b.getLocation(), 25, 25, 25);

        for (Entity entity : bubbledEntities) {
            if (entity instanceof Player) {
                Player p = (Player) entity;

                if (!p.getAllowFlight()) {
                    enabledPlayers.add(p.getUniqueId());
                    p.setAllowFlight(true);
                    removeCharge(b.getLocation(), getEnergyConsumption());
                }
            }
        }

        final Iterator<UUID> playerIterator = enabledPlayers.iterator();
        while (playerIterator.hasNext()) {
            final UUID uuid = playerIterator.next();
            Player p = Bukkit.getPlayer(uuid);

            if (p != null && !bubbledEntities.contains(p)) {
                p.setAllowFlight(false);
                p.setFlying(false);
                p.setFallDistance(0.0f);
                playerIterator.remove();
            }
        }
    }
        
    private ItemHandler onBreak() {
        return new BlockBreakHandler(false, false) {
        
            @Override
            public void onPlayerBreak(BlockBreakEvent e, ItemStack tool, List<ItemStack> drops) {
                final Iterator<UUID> playerIterator = enabledPlayers.iterator();
                while (playerIterator.hasNext()) {
                    final UUID uuid = playerIterator.next();
                    Player p = Bukkit.getPlayer(uuid);
                    if (p != null) {
                        p.setAllowFlight(false);
                        p.setFlying(false);
                        p.setFallDistance(0.0F);
                        playerIterator.remove();
                    }
                }
            }
        };
    }

    @Override
    public boolean isGraphical() {
        return false;
    }
    
    @Override
    public String getMachineIdentifier() {
        return "ANTIGRAVITY_BUBBLE";
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
        return new ItemStack(Material.DRAGON_EGG);
    }
    
    @Override
    public int getProgressBarSlot() {
        return 4;
    }
    
}
