package me.profelements.dynatech.fluids;

import javax.annotation.Nullable;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import io.github.bakedlibs.dough.data.persistent.PersistentDataAPI;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.profelements.dynatech.DynaTech;

public record FluidStack(NamespacedKey fluid, int amount) {

    public static int BUCKET_AMOUNT = 1000;
    public static int BOTTLE_AMOUNT = 250;
    public static NamespacedKey LAVA_FLUID = NamespacedKey.minecraft("lava");
    public static NamespacedKey WATER_FLUID = NamespacedKey.minecraft("water");
    public static NamespacedKey MILK_FLUID = NamespacedKey.minecraft("milk");
    public static NamespacedKey POTION_FLUID = NamespacedKey.minecraft("potion");

    public static NamespacedKey FLUID_KEY = new NamespacedKey(DynaTech.getInstance(), "fluid");
    public static NamespacedKey FLUID_AMOUNT_KEY = new NamespacedKey(DynaTech.getInstance(), "fluid_amount");

    public static FluidStack of(NamespacedKey fluid, int amount) {
        return new FluidStack(fluid, amount);
    }

    public void toBlock(Block block) {
        String fluidType = BlockStorage.getLocationInfo(block.getLocation(), FLUID_KEY.toString());
        String fluidAmount = BlockStorage.getLocationInfo(block.getLocation(), FLUID_AMOUNT_KEY.toString());

        if (fluidType != null && fluidType != this.fluid().toString()) {
            return;
        }

        BlockStorage.addBlockInfo(block, FLUID_KEY.toString(), this.fluid().toString());

        int amount = 0;
        if (fluidAmount != null) {
            amount = Integer.parseInt(fluidAmount);
        }

        BlockStorage.addBlockInfo(block, FLUID_AMOUNT_KEY.toString(), String.valueOf(amount + this.amount()));
    }

    public static @Nullable FluidStack fromBlock(Block block) {
        String fluidType = BlockStorage.getLocationInfo(block.getLocation(), FLUID_KEY.toString());
        String fluidAmount = BlockStorage.getLocationInfo(block.getLocation(), FLUID_AMOUNT_KEY.toString());

        if (fluidType != null && fluidAmount != null) {
            return FluidStack.of(NamespacedKey.fromString(fluidType), Integer.parseInt(fluidAmount));
        } else {
            return null;
        }

    }

    public static @Nullable FluidStack fromItemStack(ItemStack itemStack) {
        String fluidType = PersistentDataAPI.getString(itemStack.getItemMeta(), FLUID_KEY, "");
        int fluidAmount = PersistentDataAPI.getInt(itemStack.getItemMeta(),
                FLUID_AMOUNT_KEY, 0);

        if (fluidType != "" && fluidAmount != 0) {
            return FluidStack.of(NamespacedKey.fromString(fluidType), fluidAmount);
        } else {
            return null;
        }

    }

    public void toItemStack(ItemStack itemStack) {
        ItemMeta meta = itemStack.getItemMeta();

        String fluidType = PersistentDataAPI.getString(meta, FLUID_KEY, "");
        int fluidAmount = PersistentDataAPI.getInt(meta, FLUID_AMOUNT_KEY, 0);

        if (fluidType.equals("") || this.fluid().equals(NamespacedKey.fromString(fluidType))) {
            PersistentDataAPI.setString(meta, FLUID_KEY, this.fluid().toString());
            PersistentDataAPI.setInt(meta, FLUID_AMOUNT_KEY, fluidAmount + this.amount());
        }

        itemStack.setItemMeta(meta);
    }

    public ItemMeta apply(ItemMeta meta) {
        String fluidType = PersistentDataAPI.getString(meta, FLUID_KEY, "");
        int fluidAmount = PersistentDataAPI.getInt(meta, FLUID_AMOUNT_KEY, 0);

        if (fluidType != null && this.fluid() == NamespacedKey.fromString(fluidType)) {
            PersistentDataAPI.setString(meta, FLUID_KEY, this.fluid().toString());
            PersistentDataAPI.setInt(meta, FLUID_AMOUNT_KEY, fluidAmount + this.amount());
        }

        return meta;
    }
}
