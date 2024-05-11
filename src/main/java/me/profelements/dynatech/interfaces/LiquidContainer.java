package me.profelements.dynatech.interfaces;

import me.profelements.dynatech.utils.Liquid;

public interface LiquidContainer {

    Liquid getLiquid();

    int getLiquidCapacity();

    default boolean isFinite() {
        return true;
    }
}
