package me.profelements.dynatech.items.electric;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.profelements.dynatech.items.abstracts.AbstractElectricTicker;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class AntigravityBubble extends AbstractElectricTicker {

    private final Map<Location, Set<UUID>> enabledPlayers = new HashMap<>(); 

	public AntigravityBubble(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
		super(itemGroup, item, recipeType, recipe);
	}

    @Override
    protected void onPlace(BlockPlaceEvent e, Block blockPlaced) {
        enabledPlayers.put(blockPlaced.getLocation(), new HashSet<>()); 
    }

    @Override
    protected void onBreak(BlockBreakEvent e, Location l) {
        for (UUID plyr : enabledPlayers.get(l)) {
            Player player = Bukkit.getPlayer(plyr);

            player.setAllowFlight(false);
            player.setFlying(false);
            player.setFallDistance(0.f);
        }

        enabledPlayers.remove(l);
        
    }

	@Override
	protected void tick(Block b, SlimefunItem item) {
        Collection<Entity> bubbledEntities = b.getWorld().getNearbyEntities(b.getLocation(), 16, 16, 16, Player.class::isInstance);
        for (Entity entity : bubbledEntities) {
            Player p = (Player) entity; 

            if (!p.getAllowFlight() && (p.getGameMode() != GameMode.CREATIVE || p.getGameMode() != GameMode.SPECTATOR)) {
                enabledPlayers.get(b.getLocation()).add(p.getUniqueId()); 
                p.setAllowFlight(true); 
            }
        }



        for (UUID id : enabledPlayers.get(b.getLocation())) {
            Player p = Bukkit.getPlayer(id); 

            if (p != null && !bubbledEntities.contains(p)) {
                    p.setAllowFlight(false);
                    p.setFlying(false);
                    p.setFallDistance(0.f);
            }
        }

        enabledPlayers.get(b.getLocation()).removeIf(uuid -> (Bukkit.getPlayer(uuid) != null && !bubbledEntities.contains(Bukkit.getPlayer(uuid)))); 
        
	}

    @Override
    protected boolean isSynchronized() {
        return true;
    }
}
