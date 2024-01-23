package me.profelements.dynatech.listeners;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerBackpack;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.profelements.dynatech.DynaTech;
import me.profelements.dynatech.events.PicnicBasketFeedPlayerEvent;
import me.profelements.dynatech.items.backpacks.PicnicBasket;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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
                for (ItemStack food : PicnicBasket.getFoods().keySet()) {
                    if (SlimefunUtils.isItemSimilar(food, item, false, false) && (p.getFoodLevel() + PicnicBasket.getFoods().get(food).getFirstValue()) <= 20) {
                        p.setFoodLevel(p.getFoodLevel() + PicnicBasket.getFoods().get(food).getFirstValue());
                        p.setSaturation(p.getSaturation() + PicnicBasket.getFoods().get(food).getSecondValue());
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1F, 1F);

                        if (item.getAmount() > 1) {
                            item.setAmount(item.getAmount() - 1);
                        } else {
                            inv.setItem(slot, null);
                        }

                        backpack.markDirty();
                        return true; 
                    }
                }
            }
        }
        return false;
    }
}
