package me.profelements.dynatech.items.misc;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;

public class WitherGolem extends MultiBlockMachine {
  
  public WitherGolem(Category category, SlimefunItemStack item) {
    super(category, item, new ItemStack[] {null, new ItemStack(Material.CARVED_PUMPKIN), null, null, new ItemStack(Material.POLISHED_BLACKSTONE), null, null, new ItemStack(Material. POLISHED_BLACKSTONE), null}, BlockFace.SELF);
  }

  @Override
  public void onInteract(@Nonnull Player p, @Nonnull Block b) {
    Block pumpkinHead = b.getRelative(BlockFace.UP);
    Block bottomBlackstone = b.getRelative(BlockFace.DOWN);
  
    p.getWorld().spawnEntity(p.getLocation(), EntityType.WITHER_SKELETON);

    pumpkinHead.setType(Material.AIR);
    b.setType(Material.AIR);
    bottomBlackstone.setType(Material.AIR);
  }

}
