package be.kingquest.friendSpigot.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class FreundeAnfragen {

    public static void FreundeAnfragen(Player player, List<String> online) {

        ItemStack Hintergrund = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta HintergrundMeta = Hintergrund.getItemMeta();
        HintergrundMeta.setDisplayName("§6");
        Hintergrund.setItemMeta(HintergrundMeta);

        ItemStack Freunde = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta FreundeMeta = Freunde.getItemMeta();
        FreundeMeta.setDisplayName("§dFreunde");
        Freunde.setItemMeta(FreundeMeta);

        ItemStack Freundschaftsanfragen = new ItemStack(Material.PAPER);
        ItemMeta FreundschaftsanfragenMeta = Freundschaftsanfragen.getItemMeta();
        FreundschaftsanfragenMeta.setDisplayName("§bFreundschaftsanfragen");
        Freundschaftsanfragen.setItemMeta(FreundschaftsanfragenMeta);

        ItemStack Einstellungen = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta EinstellungenMeta = Einstellungen.getItemMeta();
        EinstellungenMeta.setDisplayName("§dEinstellungen");
        Einstellungen.setItemMeta(EinstellungenMeta);

        Inventory inventory = Bukkit.createInventory(null, 9*6, "Freundschaftsanfragen");

        inventory.setItem(0, Hintergrund);
        inventory.setItem(1, Hintergrund);
        inventory.setItem(2, Freunde);
        inventory.setItem(3, Hintergrund);
        inventory.setItem(4, Freundschaftsanfragen);
        inventory.setItem(5, Hintergrund);
        inventory.setItem(6, Einstellungen);
        inventory.setItem(7, Hintergrund);
        inventory.setItem(8, Hintergrund);

        inventory.setItem(9, Hintergrund);
        inventory.setItem(10, Hintergrund);
        inventory.setItem(11, Hintergrund);
        inventory.setItem(12, Hintergrund);
        inventory.setItem(13, Hintergrund);
        inventory.setItem(14, Hintergrund);
        inventory.setItem(15, Hintergrund);
        inventory.setItem(16, Hintergrund);
        inventory.setItem(17, Hintergrund);

        inventory.setItem(18, Hintergrund);
        inventory.setItem(26, Hintergrund);

        inventory.setItem(27, Hintergrund);
        inventory.setItem(35, Hintergrund);

        inventory.setItem(36, Hintergrund);
        inventory.setItem(44, Hintergrund);

        inventory.setItem(45, Hintergrund);
        inventory.setItem(46, Hintergrund);
        inventory.setItem(47, Hintergrund);
        inventory.setItem(48, Hintergrund);
        inventory.setItem(49, Hintergrund);
        inventory.setItem(50, Hintergrund);
        inventory.setItem(51, Hintergrund);
        inventory.setItem(52, Hintergrund);
        inventory.setItem(53, Hintergrund);

        for (String username : online) {
            ItemStack item = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setOwner(username);
            item.setItemMeta(meta);
            inventory.addItem(item);
        }


        player.openInventory(inventory);
    }
}
