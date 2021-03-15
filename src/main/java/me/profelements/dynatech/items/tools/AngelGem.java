package me.profelements.dynatech.items.tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemSetting;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotPlaceable;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemDropHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.profelements.dynatech.DynaTech;

import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class AngelGem extends SlimefunItem implements NotPlaceable, Listener {

    private final ItemSetting<Double> maxFlightSpeed = new ItemSetting<>(this, "max-flight-speed", 1.0d);
    private final ItemSetting<Boolean> hasMaxFlightSpeed = new ItemSetting<>(this, "has-max-flight-speed", false);

    private float flySpeed = 0.1f;

    public AngelGem(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemSetting(maxFlightSpeed, hasMaxFlightSpeed);

        addItemHandler(onRightClick(), onItemDrop());

        Bukkit.getPluginManager().registerEvents(this, DynaTech.getInstance());
    }

    private ItemDropHandler onItemDrop() {
        return (e, p, item) -> {
            if (isItem(item.getItemStack())  && e.getPlayer().getGameMode() != GameMode.CREATIVE) {
                e.getPlayer().setFlying(false);
                e.getPlayer().setAllowFlight(false);
                e.getPlayer().setFlySpeed(0.1f);
                e.getPlayer().setFallDistance(0.0f);
            }
            return true;
        };
    }

    private ItemUseHandler onRightClick() {
        return e -> {
            if (e.getPlayer().isSneaking()) {
                e.getPlayer().setFlying(false);
                e.getPlayer().setAllowFlight(false);
                e.getPlayer().setFallDistance(0f);
                e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer()));
            }
            if (!e.getPlayer().getAllowFlight()) {
                e.getPlayer().setAllowFlight(true);
                setFlySpeed(0.10f);
            } else {
                if (hasMaxFlightSpeed.getValue()) {
                    if (getFlySpeed() < maxFlightSpeed.getValue()) {
                        if (getFlySpeed() + 0.10f > maxFlightSpeed.getValue()) {
                            setFlySpeed(maxFlightSpeed.getValue().floatValue());
                        } else {
                            setFlySpeed(getFlySpeed() + 0.10f);
                        }
                    } else {
                        setFlySpeed(0.10f);
                    }
                } else {
                    if (getFlySpeed() < 1f) {
                        setFlySpeed(getFlySpeed() + 0.10f);
                    } else {
                        setFlySpeed(0.10f);
                    }
                }
            }
            e.getPlayer().setFlySpeed(getFlySpeed());
            e.getItem().setItemMeta(updateLore(e.getItem(), e.getPlayer()));
            e.cancel();
        };
    }

    @EventHandler
    public void getItemClicked(InventoryClickEvent e) {
        List<HumanEntity> views = e.getViewers();
        if (isItem(e.getCursor()) || isItem(e.getCurrentItem())) {
            for (HumanEntity he : views) {
                if (he instanceof Player) {
                    Player p = (Player) he;
                    p.setFlying(false);
                    p.setAllowFlight(false);
                    p.setFallDistance(0f);
                }
            }
        }
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
