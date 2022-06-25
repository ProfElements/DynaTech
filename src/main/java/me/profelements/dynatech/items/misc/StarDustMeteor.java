package me.profelements.dynatech.items.misc;

import io.github.thebusybiscuit.slimefun4.api.geo.GEOResource;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;
import me.profelements.dynatech.DynaTech;
import org.bukkit.NamespacedKey;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class StarDustMeteor extends UnplaceableBlock implements GEOResource {

    public static final SlimefunItemStack STARDUST_METEOR = new SlimefunItemStack(
        "STARDUST_METEOR",
        PlayerHead.getItemStack(PlayerSkin.fromHashCode("c482d1ba4bdac990f6ea987703587fd79fe55555363251984679d4f279cc0c2a")),
        "&6Stardust Meteor",
        "",
        "&fGeomined from Mountain or Badlands Biomes"
    );
    
    private final NamespacedKey key = new NamespacedKey(DynaTech.getInstance(), "stardust_meteor");
  
    public StarDustMeteor(ItemGroup itemGroup) {
        super(itemGroup, STARDUST_METEOR, RecipeType.GEO_MINER, new ItemStack[0]);
        register();
    }
    
    @Nonnull
    @Override
    public NamespacedKey getKey() {
      return key;
    }
    
    @Nonnull
    @Override
    public ItemStack getItem() {
      return STARDUST_METEOR.clone();
    }
  
    @Nonnull
    @Override
    public String getName() {
      return "Stardust Meteor";
    }
    
    @Override
    public boolean isObtainableFromGEOMiner() {
      return true;
    }
    
    @Override
    public int getDefaultSupply(@Nonnull Environment environment, @Nonnull Biome biome) {
        if (biome == Biome.MEADOW || biome == Biome.BADLANDS) {
            return 16;
        } else {
            return 0;
        }
    }
    
    @Override
    public int getMaxDeviation() {
      return 4;
    }
  
}
