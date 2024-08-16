package me.profelements.dynatech.registries.events;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.google.common.base.Preconditions;

import me.profelements.dynatech.registries.Registry;
import me.profelements.dynatech.registries.TypedKey;

public class RegistryRemoveEvent<T> extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final TypedKey<Registry<T>> registryKey;
    private TypedKey<T> entryKey;
    private boolean cancelled;

    @ParametersAreNonnullByDefault
    private RegistryRemoveEvent(TypedKey<Registry<T>> registryKey, TypedKey<T> entryKey) {
        this.registryKey = registryKey;
        this.entryKey = entryKey;
    }

    public static <T> RegistryRemoveEvent<T> create(TypedKey<Registry<T>> registryKey, TypedKey<T> entryKey) {
        Preconditions.checkNotNull(registryKey);
        Preconditions.checkNotNull(entryKey);

        return new RegistryRemoveEvent<>(registryKey, entryKey);
    }

    @Nonnull
    public TypedKey<Registry<T>> getRegistyKey() {
        return registryKey;
    }

    @Nonnull
    public TypedKey<T> getEntryKey() {
        return entryKey;
    }

    @Override
    public void setCancelled(boolean cancel) {
        cancelled = cancel;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Nonnull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return getHandlerList();
    }
}
