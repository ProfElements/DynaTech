package me.profelements.dynatech.listeners;

import io.github.thebusybiscuit.exoticgarden.items.CustomFood;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerBackpack;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
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
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;


public class PicnicBasketListener implements Listener {

    private final DynaTech plugin;
    private final PicnicBasket picnicBasket;

    public PicnicBasketListener(@Nonnull DynaTech plugin, @Nonnull PicnicBasket picnicBasket) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        this.plugin = plugin;
        this.picnicBasket = picnicBasket;
    }

    @EventHandler
    public void onHungerLoss(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (e.getFoodLevel() < p.getFoodLevel()) {
                checkAndConsume((Player) e.getEntity());
            }
        }
    }

    @EventHandler
    public void onHungerDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.STARVATION) {
            checkAndConsume((Player) e.getEntity());
        }
    }

    private void checkAndConsume(Player p) {
        if (picnicBasket == null || picnicBasket.isDisabled()) {
            return;
        }

        for (ItemStack item : p.getInventory().getContents()) {
            if (item != null && item.getType() == picnicBasket.getItem().getType() &&  item.hasItemMeta() && picnicBasket.isItem(item)) {
                if (SlimefunUtils.canPlayerUseItem(p, picnicBasket.getItem(), true)) {
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

    private void consumeFood(Player p, ItemStack picnicBasketItem, PlayerBackpack backpack) {
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
                boolean itemConsumed = false;

                if (DynaTech.isExoticGardenInstalled()) {
                    if (SlimefunItem.getByItem(item) != null) {
                        SlimefunItem sfItem = SlimefunItem.getByItem(item);
                        if (sfItem instanceof CustomFood) {
                            CustomFood cfItem = (CustomFood) sfItem;

                            if (cfItem.getFoodValue() + p.getFoodLevel() <= 20) {
                                p.setFoodLevel(p.getFoodLevel() + cfItem.getFoodValue());
                                itemConsumed = true;
                            }
                        }
                    } else {
                        Material material = item.getType();
                        if (material == Material.COOKED_PORKCHOP || material == Material.PUMPKIN_PIE || material == Material.COOKED_BEEF) {

                            if (p.getFoodLevel() <= 12) {
                                p.setFoodLevel(p.getFoodLevel() + 8);
                                itemConsumed = true;
                            }
                        } else if (material == Material.COOKED_CHICKEN || material == Material.COOKED_MUTTON || material == Material.COOKED_SALMON ||
                                material == Material.GOLDEN_CARROT) {

                            if (p.getFoodLevel() <= 14) {
                                p.setFoodLevel(p.getFoodLevel() + 6);
                                itemConsumed = true;
                            }

                        } else if (material == Material.BAKED_POTATO || material == Material.COOKED_RABBIT || material == Material.COOKED_COD ||
                                material == Material.BREAD) {
                            if (p.getFoodLevel() <= 15) {
                                p.setFoodLevel(p.getFoodLevel() + 5);
                                itemConsumed = true;
                            }

                        } else if (material == Material.APPLE) {

                            if (p.getFoodLevel() <= 16) {
                                p.setFoodLevel(p.getFoodLevel() + 4);
                                itemConsumed = true;
                            }
                        } else if (material == Material.CARROT || material == Material.BEEF || material == Material.PORKCHOP ||
                                material == Material.RABBIT) {

                            if (p.getFoodLevel() <= 17) {
                                p.setFoodLevel(p.getFoodLevel() + 3);
                                itemConsumed = true;
                            }
                        } else if (material == Material.COOKIE || material == Material.MELON_SLICE || material == Material.CHICKEN ||
                                material == Material.COD || material == Material.MUTTON || material == Material.SALMON ||
                                material == Material.SWEET_BERRIES) {

                            if (p.getFoodLevel() <= 18) {
                                p.setFoodLevel(p.getFoodLevel() + 2);
                                itemConsumed = true;
                            }
                        } else if (material == Material.BEETROOT || material == Material.DRIED_KELP || material == Material.POTATO ||
                                material == Material.TROPICAL_FISH) {

                            if (p.getFoodLevel() <= 19) {
                                p.setFoodLevel(p.getFoodLevel() + 1);
                                itemConsumed = true;
                            }
                        }
                    }


                    if (item.getAmount() > 1 && itemConsumed) {
                        item.setAmount(item.getAmount() - 1);
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1F, 1F);
                        p.setSaturation(p.getSaturation()+4F);

                    } else if (itemConsumed) {
                        inv.setItem(slot, null);
                        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_BURP, 1F, 1F);
                        p.setSaturation(p.getSaturation()+4F);
                    }

                    backpack.markDirty();
                }
            }

        }
    }
}
