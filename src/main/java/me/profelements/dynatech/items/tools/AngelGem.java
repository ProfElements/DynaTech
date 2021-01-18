package me.profelements.dynatech.items.tools;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemDropHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class AngelGem extends SlimefunItem {

    private ItemSetting<Float> maxFlightSpeed = new ItemSetting<Float>("max-flight-speed", 1.0f);
    private ItemSetting<Boolean> hasMaxFlightSpeed = new ItemSetting<Boolean>("has-max-flight-speed", false);

    private boolean enabledPlayer = false;

    private float flySpeed = 0.1f;

    public AngelGem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemSetting(maxFlightSpeed);

        addItemHandler(onRightClick(), onItemDrop());
    }

    private ItemDropHandler onItemDrop() {
        return new ItemDropHandler() {

            @Override
            public boolean onItemDrop(PlayerDropItemEvent e, Player p, Item item) {
                if (enabledPlayer && e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    e.getPlayer().setFlying(false);
                    e.getPlayer().setAllowFlight(false);
                    e.getPlayer().setFlySpeed(0.1f);
                    e.getPlayer().setFallDistance(0.0f);
                    enabledPlayer = false;
                }
                return true;
            }

        };
    }

    private ItemUseHandler onRightClick() {
        return new ItemUseHandler() {
            @Override
            public void onRightClick(PlayerRightClickEvent e) {
                if (e.getPlayer().isSneaking()) {
                    e.getPlayer().setFlying(false);
                    e.getPlayer().setAllowFlight(false);
                    e.getPlayer().setFallDistance(0f);
                    e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer()));
                    enabledPlayer = false;
                }
                if (!e.getPlayer().getAllowFlight()) {
                    e.getPlayer().setAllowFlight(true);
                    setFlySpeed(0.10f);
                    e.getPlayer().setFlySpeed(getFlySpeed());
                    e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer()));
                    enabledPlayer = true;
                } else {
                    if (hasMaxFlightSpeed.getValue()) {
                        if (getFlySpeed() < maxFlightSpeed.getValue()) {
                            if (getFlySpeed() + 0.10f > maxFlightSpeed.getValue()) {
                                setFlySpeed(maxFlightSpeed.getValue());
                            } else {
                                setFlySpeed(getFlySpeed() + 0.10f);
                            }
                            e.getPlayer().setFlySpeed(getFlySpeed());
                            e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer()));
                        } else {
                            setFlySpeed(0.10f);
                            e.getPlayer().setFlySpeed(getFlySpeed());
                            e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer()));
                        }
                    } else {
                        if (getFlySpeed() < 1f) {
                            setFlySpeed(getFlySpeed() + 0.10f);
                            e.getPlayer().setFlySpeed(getFlySpeed());
                            e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer()));
                        } else {
                            setFlySpeed(0.10f);
                            e.getPlayer().setFlySpeed(getFlySpeed());
                            e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer()));
                        }
                    }
                }
                e.cancel();
            }   
        };
    }

    protected ItemMeta updateLore(ItemStack item, Player p) {
        ItemMeta im = item.getItemMeta();

        if (!im.hasLore()) {
            throw new IllegalArgumentException("This item does not have any lore!");
        }

        List<String> lore = im.getLore();

        for (int line = 0; line < lore.size(); line++ ) {
            if (lore.get(line).contains("Flight: <enabled>")) {
                lore.set(line, lore.get(line).replace("<enabled>", p.getAllowFlight() ? "Enabled" : "Disabled"));
            }
            if (lore.get(line).contains(ChatColor.GRAY + "Flight Speed: ")) {
                lore.set(line, lore.get(line).replaceFirst(".*", ChatColor.GRAY + "Flight Speed: " + getFlySpeed()));
            }
        }

        im.setLore(lore);
        return im;
    }


    public float getFlySpeed() {
        return flySpeed;
    }

    public void setFlySpeed(float newFlySpeed) {
        Validate.isTrue(newFlySpeed > 0, "Must be greater then 0");

        BigDecimal bd = new BigDecimal(Float.toString(newFlySpeed));
        bd = bd.setScale(1, RoundingMode.DOWN);
        flySpeed = bd.floatValue();
    }

}
