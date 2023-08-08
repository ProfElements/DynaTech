package me.profelements.dynatech.listeners;

import io.github.thebusybiscuit.exoticgarden.items.CustomFood;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerBackpack;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.events.PicnicBasketFeedPlayerEvent;
import me.profelements.dynatech.items.backpacks.PicnicBasket;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class PicnicBasketListener implements Listener {

    private final DynaTech plugin;  
    private final PicnicBasket picnicBasket;
    
    @ParametersAreNonnullByDefault
    public PicnicBasketListener(DynaTech plugin,PicnicBasket picnicBasket) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.plugin = plugin;
        this.picnicBasket = picnicBasket;
    }
    
    @EventHandler 
    public void onHungerLoss(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player player && e.getFoodLevel() < player.getFoodLevel()) {
            checkAndConsume(player);
        }
    }

    @EventHandler 
    public void onHungerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player player && e.getCause() == DamageCause.STARVATION) {
            checkAndConsume(player);
        }
    }       

    private void checkAndConsume(@Nonnull Player p) {
        if (picnicBasket == null || picnicBasket.isDisabled()) {
            return; 
        }

        for (ItemStack item : p.getInventory().getContents()) {
            if (picnicBasket.isItem(item) || SlimefunItem.getByItem(item) instanceof PicnicBasket) {
                if (picnicBasket.canUse(p, true)) {
                    takeFoodFromPicnicBasket(p, item);
                } else { 
                    return; 
                }
            }
        }
    }

    private void takeFoodFromPicnicBasket(@Nonnull Player p, @Nonnull ItemStack picnicBasket) {
        PlayerProfile.getBackpack(picnicBasket, backpack -> {
            if (backpack != null) {
                DynaTech.runSync(() -> consumeFood(p, picnicBasket, backpack));
            }
        });

    }
    
    private boolean consumeFood(@Nonnull Player p, @Nonnull ItemStack picnicBasketItem, @Nonnull PlayerBackpack backpack) {
        Inventory inv = backpack.getInventory();
        int slot = -1;

        for (int i = 0; i < inv.getSize(); i++) {
            ItemStack item = inv.getItem(i);
            
            if (item != null) {
                    slot = i; 
            }
        }

        if (slot >= 0) {
            ItemStack item = inv.getItem(slot); 
            PicnicBasketFeedPlayerEvent event = new PicnicBasketFeedPlayerEvent(p, picnicBasket, picnicBasketItem, item); 
            plugin.getServer().getPluginManager().callEvent(event);

            if (!event.isCancelled()) {
                if (getFoodItems().keySet().contains(item.getType()) && (p.getFoodLevel() + getFoodItems().get(item.getType())) <= 20) {
                    p.setFoodLevel(p.getFoodLevel() + getFoodItems().get(item.getType())); 
                    p.setSaturation(p.getSaturation() + 4F);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1F, 1F);
                    
                    
                    if (item.getAmount() > 1) {
                        item.setAmount(item.getAmount() - 1); 
                    } else {
                        inv.setItem(slot, null); 
                    }

                    backpack.markDirty();
                    return true;    
                } else if (DynaTech.isExoticGardenInstalled() && SlimefunItem.getByItem(item) instanceof CustomFood customFood && (p.getFoodLevel() + customFood.getFoodValue()) <= 20) {
                    p.setFoodLevel(p.getFoodLevel() + customFood.getFoodValue()); 
                    p.setSaturation(p.getSaturation() + 4F);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1F, 1F); 

                    if (item.getAmount() > 1) {
                        item.setAmount(item.getAmount() - 1);
                    } else {
                        inv.setItem(slot, null);
                    }

                   backpack.markDirty();
                   return true;
                } else { 
                    //TODO: THIS SHOULD NEVER HAPPEN IF IT DOES SOMETHING DID A BIG NO NO
                    return false; 
                }
            }
        }
        return false;
    }

    private Map<Material, Integer> getFoodItems() {
        Map<Material, Integer> foods = new HashMap<Material, Integer>();
                
        foods.put(Material.COOKED_PORKCHOP, 8);
        foods.put(Material.PUMPKIN_PIE, 8);
        foods.put(Material.COOKED_BEEF, 8);
        foods.put(Material.COOKED_CHICKEN, 6);
        foods.put(Material.COOKED_MUTTON, 6);
        foods.put(Material.COOKED_SALMON, 6);
        foods.put(Material.GOLDEN_CARROT, 6);
        foods.put(Material.BAKED_POTATO, 5);
        foods.put(Material.COOKED_RABBIT, 5);
        foods.put(Material.COOKED_COD, 5);
        foods.put(Material.BREAD, 5);
        foods.put(Material.APPLE, 4);
        foods.put(Material.CARROT, 3);
        foods.put(Material.BEEF, 3);
        foods.put(Material.PORKCHOP, 3);
        foods.put(Material.RABBIT, 3);
        foods.put(Material.COOKIE, 2);
        foods.put(Material.MELON_SLICE, 2);
        foods.put(Material.CHICKEN, 2);
        foods.put(Material.COD, 2);
        foods.put(Material.MUTTON, 2);
        foods.put(Material.SALMON, 2);
        foods.put(Material.SWEET_BERRIES, 2);
        foods.put(Material.GLOW_BERRIES, 2);
        foods.put(Material.BEETROOT, 1);
        foods.put(Material.DRIED_KELP, 1);
        foods.put(Material.POTATO, 1);
        foods.put(Material.TROPICAL_FISH, 1);

        return foods;
    } 
}
