package me.profelements.dynatech.utils;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import com.google.common.base.Preconditions;

public class Liquid {
    private NamespacedKey KEY;
    private String NAME;
    private Color LIQUID_COLOR;
    private Material LIQUID_MATERIAL;
    private Material STORAGE_MATERIAL;

    private Liquid() {
    }

    public static Liquid init() {
        return new Liquid();
    }

    public Liquid setKey(NamespacedKey key) {
        Preconditions.checkNotNull(key, "The liquid's key should not be null!");
        this.KEY = key;
        return this;
    }

    public final NamespacedKey getKey() {
        return this.KEY;
    }

    public Liquid setName(String name) {
        Preconditions.checkNotNull(name, "The liquid's name should not be null!");
        this.NAME = name;
        return this;
    }

    public final String getName() {
        return this.NAME;
    }

    public Liquid setColor(Color color) {
        Preconditions.checkNotNull(color, " The Liquid's color should not be null!");
        this.LIQUID_COLOR = color;
        return this;
    }

    public final Color getColor() {
        return this.LIQUID_COLOR;
    }

    public Liquid setLiquidMaterial(Material mat) {
        Preconditions.checkArgument(mat == Material.LAVA || mat == Material.WATER,
                "The liquid's liquid material should be a liquid!");
        this.LIQUID_MATERIAL = mat;
        return this;
    }

    public final Material getLiquidMaterial() {
        return this.LIQUID_MATERIAL;
    }

    public Liquid setStorageMaterial(Material mat) {
        Preconditions.checkNotNull(mat, "The Liquid's storage material should not be null!");
        this.STORAGE_MATERIAL = mat;
        return this;
    }

    public final Material getStorageMaterial() {
        return this.STORAGE_MATERIAL;
    }

    public final Liquid build() {
        Preconditions.checkNotNull(this.KEY, "The liquid's key should not be null!");
        Preconditions.checkNotNull(this.NAME, "The liquid's name should not be null!");
        Preconditions.checkNotNull(this.LIQUID_COLOR, " The Liquid's color should not be null!");
        Preconditions.checkNotNull(this.LIQUID_MATERIAL, "The Liquid's liquid material should not be null!");
        Preconditions.checkNotNull(this.STORAGE_MATERIAL, "The Liquid's storage material should not be null!");
        return this;
    }

    public void register(LiquidRegistry registry) {
        Liquid liquid = this.build();
        registry.addLiquid(liquid);
    }
}
