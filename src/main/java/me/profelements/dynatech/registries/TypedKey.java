package me.profelements.dynatech.registries;

import java.util.Locale;

import javax.annotation.Nonnull;

import org.bukkit.NamespacedKey;

public record TypedKey<T>(@Nonnull NamespacedKey key) {
    public static <T> TypedKey<T> create(NamespacedKey key) {
        return new TypedKey<>(key);
    }

    public static <T> TypedKey<T> create(String namespace, String key) {
        return new TypedKey<>(new NamespacedKey(namespace, key));
    }

    // THIS IS TEMPORARY TILL SLIMEFUN MOVES TO NamespacedKey
    public String asSlimefunId() {
        return this.key().toString().replace(':', '_').toUpperCase(Locale.ROOT);
    }
}
