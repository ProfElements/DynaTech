package me.profelements.dynatech.utils;

import org.bukkit.Material;

import javax.annotation.Nonnull;

/**
 * This class is a {@link Material} wrapper that supports backward compatibility.
 */
public enum AMaterial {
    SHORT_GRASS(Material.getMaterial("SHORT_GRASS"), Material.getMaterial("GRASS"));

    private final Material material;

    AMaterial(Material... materials) {
        for (Material mat : materials) {
            if (mat != null) {
                this.material = mat;
                return;
            }
        }
        throw new IllegalArgumentException("No valid material found");
    }

    @Nonnull
    public Material get() {
        return material;
    }
}
