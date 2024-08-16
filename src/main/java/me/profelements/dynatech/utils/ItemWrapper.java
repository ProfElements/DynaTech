package me.profelements.dynatech.utils;

import me.profelements.dynatech.registries.TypedKey;
import me.profelements.dynatech.registries.Registries;

import com.google.common.base.Preconditions;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;

public record ItemWrapper(TypedKey<ItemWrapper> key, SlimefunItemStack stack) {

    public static ItemWrapper create(TypedKey<ItemWrapper> key, SlimefunItemStack stack) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(stack);

        ItemWrapper item = new ItemWrapper(key, stack);
        Registries.ITEMS.register(key, item);

        return item;
    }
}
