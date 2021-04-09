package me.profelements.dynatech.items.tools;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import me.mrCookieSlime.Slimefun.cscorelib2.data.PersistentDataAPI;
import me.profelements.dynatech.DynaTechItems;
import me.profelements.dynatech.items.electric.transfer.Tesseract;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class TesseractBinder extends SlimefunItem {
    public TesseractBinder(Category category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);

        addItemHandler(bindTesseract());
    }

    private ItemUseHandler bindTesseract() {
        return e -> {
            e.cancel();

            Optional<Block> block = e.getClickedBlock();
            Optional<SlimefunItem> sfBlock = e.getSlimefunBlock();
            if (block.isPresent() && sfBlock.isPresent()) {
                Location blockLocation = block.get().getLocation();
                SlimefunItem sfItem = sfBlock.get();
                ItemStack item = e.getItem();

                if (e.getPlayer().isSneaking()) {
                    String locString = PersistentDataAPI.getString(item.getItemMeta(), Tesseract.WIRELESS_LOCATION_KEY);
                    if (item != null && BlockStorage.checkID(blockLocation).equals(DynaTechItems.TESSERACT.getItemId()) && item.hasItemMeta() && locString != null) {
                        BlockStorage.addBlockInfo(blockLocation, "tesseract-pair-location", locString);
                        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.WHITE + "Tesseract Connected!"));
                    }
                } else if (sfBlock != null && sfItem.getId().equals(DynaTechItems.TESSERACT.getItemId()) && blockLocation != null) {
                    ItemMeta im = item.getItemMeta();
                    String locString = Tesseract.LocationToString(blockLocation);
                        
                    PersistentDataAPI.setString(im, Tesseract.WIRELESS_LOCATION_KEY, locString);
                    item.setItemMeta(im);
                    Tesseract.setItemLore(item, blockLocation);
                }
                
            }
        };
    }    
}
