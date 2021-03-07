package me.profelements.dynatech.events;

import org.apache.commons.lang.Validate;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

import me.profelements.dynatech.items.backpacks.PicnicBasket;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class PicnicBasketFeedPlayerEvent extends PlayerEvent implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private final PicnicBasket picnicBasket;
    private final ItemStack picnicBasketItem;

    private ItemStack itemConsumed;
    private boolean cancelled;

    @ParametersAreNonnullByDefault
    public PicnicBasketFeedPlayerEvent(Player player, PicnicBasket picnicBasket, ItemStack picnicBasketItem, ItemStack itemConsumed) {
        super(player);

        this.picnicBasket = picnicBasket;
        this.picnicBasketItem = picnicBasketItem;
        this.itemConsumed =itemConsumed;


    }

    @Nonnull
    public PicnicBasket getPicnicBasket() {
        return picnicBasket;
    }

    @Nonnull
    public ItemStack getPicnicBasketItem() {
        return picnicBasketItem;
    }

    @Nonnull
    public ItemStack getItemConsumed() {
        return itemConsumed.clone();
    }

    public void setConsumedItem(@Nonnull ItemStack item) {
        Validate.notNull(item, "Consumed item can not be null.");
        Validate.isTrue(item.getType().isEdible(), "Item must be edible.");

        this.itemConsumed = item;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Nonnull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Nonnull
    @Override
    public HandlerList getHandlers() {
        return getHandlerList();
    }


}


