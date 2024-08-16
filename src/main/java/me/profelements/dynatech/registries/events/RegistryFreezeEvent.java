package me.profelements.dynatech.registries.events;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import com.google.common.base.Preconditions;

import me.profelements.dynatech.registries.Registry;
import me.profelements.dynatech.registries.TypedKey;

public class RegistryFreezeEvent<T> extends Event {
    private static final HandlerList handlers = new HandlerList();

    private final TypedKey<Registry<T>> registryKey;

    @ParametersAreNonnullByDefault
    private RegistryFreezeEvent(TypedKey<Registry<T>> registryKey) {
        this.registryKey = registryKey;
    }

    public static <T> RegistryFreezeEvent<T> create(TypedKey<Registry<T>> registryKey) {
        Preconditions.checkNotNull(registryKey);

        return new RegistryFreezeEvent<>(registryKey);
    }

    public TypedKey<Registry<T>> getRegistryKey() {
        return registryKey;
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
