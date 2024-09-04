package me.profelements.dynatech.registries;

import java.util.Set;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;

import com.google.common.base.Preconditions;

import me.profelements.dynatech.registries.events.RegistryAddEvent;
import me.profelements.dynatech.registries.events.RegistryFreezeEvent;
import me.profelements.dynatech.registries.events.RegistryRemoveEvent;

public class Registry<T> {
    public static final Map<NamespacedKey, Registry<?>> registries = new ConcurrentHashMap<>();
    private final Map<TypedKey<T>, T> entries = new ConcurrentHashMap<>();
    private final TypedKey<Registry<T>> key;
    private boolean frozen;

    public Registry(TypedKey<Registry<T>> key) {
        this.key = key;
        this.frozen = false;
    }

    static <T> Registry<T> create(TypedKey<Registry<T>> key) {
        Preconditions.checkNotNull(key);
        var registry = new Registry<T>(key);

        registries.put(key.key(), registry);

        return registry;
    }

    static <T> Registry<T> withFiller(TypedKey<Registry<T>> key, Consumer<Registry<T>> fillFunc) {
        Preconditions.checkNotNull(key);
        Registry<T> registry = new Registry<>(key);

        fillFunc.accept(registry);

        return registry;
    }

    public void register(TypedKey<T> key, T entry) {
        if (this.isFrozen()) {
            return;
        }

        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(entry);

        RegistryAddEvent<T> event = RegistryAddEvent.create(this.getKey(), key, entry);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            entries.putIfAbsent(event.getEntryKey(), event.getEntry());
        }
    }

    public void unregister(TypedKey<T> key) {
        if (this.isFrozen()) {
            return;
        }

        Preconditions.checkNotNull(key);

        RegistryRemoveEvent<T> event = RegistryRemoveEvent.create(this.getKey(), key);
        Bukkit.getPluginManager().callEvent(event);

        if (!event.isCancelled()) {
            entries.remove(key);
        }
    }

    public boolean isFrozen() {
        return this.frozen;
    }

    void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    public Registry<T> freeze() {

        RegistryFreezeEvent<T> event = RegistryFreezeEvent.create(this.getKey());
        Bukkit.getPluginManager().callEvent(event);

        this.setFrozen(true);
        return this;
    }

    public void register(Supplier<TypedKey<T>> key, Supplier<T> entry) {
        register(key.get(), entry.get());
    }

    public Set<T> getEntries() {
        return entries.values().stream().collect(Collectors.toUnmodifiableSet());
    }

    public Set<TypedKey<T>> getKeys() {
        return entries.keySet().stream().collect(Collectors.toUnmodifiableSet());
    }

    public T entry(TypedKey<T> key) {
        Preconditions.checkNotNull(key);
        return entries.get(key);
    }

    public TypedKey<Registry<T>> getKey() {
        Preconditions.checkNotNull(this.key);
        return this.key;
    }

    public static <T> Registry<?> getByKey(TypedKey<Registry<T>> key) {
        return registries.get(key.key());
    }
}
