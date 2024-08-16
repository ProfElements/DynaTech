package me.profelements.dynatech.registries.events;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.google.common.base.Preconditions;

import me.profelements.dynatech.registries.Registry;
import me.profelements.dynatech.registries.TypedKey;

public class RegistryAddEvent<T> extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private final TypedKey<Registry<T>> registryKey;
    private TypedKey<T> entryKey;
    private T entry;
    private boolean cancelled;

    @ParametersAreNonnullByDefault
    private RegistryAddEvent(TypedKey<Registry<T>> registryKey, TypedKey<T> entryKey, T entry) {
        this.registryKey = registryKey;
        this.entryKey = entryKey;
        this.entry = entry;
    }

    public static <T> RegistryAddEvent<T> create(TypedKey<Registry<T>> registryKey, TypedKey<T> entryKey, T entry) {
        Preconditions.checkNotNull(registryKey);
        Preconditions.checkNotNull(entryKey);
        Preconditions.checkNotNull(entry);

        return new RegistryAddEvent<>(registryKey, entryKey, entry);
    }

    @Nonnull
    public TypedKey<Registry<T>> getRegistyKey() {
        return registryKey;
    }

    @Nonnull
    public TypedKey<T> getEntryKey() {
        return entryKey;
    }

    public void setEntryKey(TypedKey<T> entry) {
        Preconditions.checkNotNull(entry);
        entryKey = entry;
    }

    @Nonnull
    public T getEntry() {
        return entry;
    }

    public void setEntry(T entryKey) {
        Preconditions.checkNotNull(entryKey);
        entry = entryKey;
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
