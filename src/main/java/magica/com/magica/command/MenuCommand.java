package magica.com.magica.command;

import magica.com.magica.Magica;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class MenuCommand extends AbstractCommand {
    Inventory inventory = Magica.getInstance().inventory;

    public MenuCommand() {
        super("menu");
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        if (args.length == 0) {
            Player player = (Player) sender;
            ItemStack glassItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
            ItemMeta glassMeta = glassItem.getItemMeta();
            assert glassMeta != null;
            glassMeta.setDisplayName(" ");
            glassItem.setItemMeta(glassMeta);
            for (int i = 0; i < inventory.getSize(); i++) {
                inventory.setItem(i, glassItem);
            }
            ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
            skullMeta.setOwner("" + player.getName());
            skullMeta.setDisplayName(ChatColor.GREEN + player.getName());
            List<String> lore = new ArrayList<>();
            lore.add(" ");
            lore.add(ChatColor.YELLOW + "⁂" + ChatColor.GRAY + " Рейтинг " + ChatColor.YELLOW +"100213");
            lore.add(" ");
            lore.add(ChatColor.RED + "❤ " + ChatColor.GRAY + "Сила " + ChatColor.RED + "5 ");
            lore.add(ChatColor.GOLD + "✹ " + ChatColor.GRAY + "Ловкость " + ChatColor.GOLD + "12 ");
            lore.add(ChatColor.BLUE + "★ " + ChatColor.GRAY + "Интеллект " + ChatColor.BLUE + "11 ");
            lore.add(" ");
            lore.add(ChatColor.GRAY + "Золото/Изумруды " + ChatColor.YELLOW + "0 " + ChatColor.GRAY + "/" + ChatColor.DARK_GREEN + " 0");
//            lore.add(ChatColor.GRAY + "☘ Торговля " + ChatColor.YELLOW + "1 ");
//            lore.add(ChatColor.GRAY + "♕ Харизма " + ChatColor.LIGHT_PURPLE + "1 ");
            skullMeta.setLore(lore);
            skull.setItemMeta(skullMeta);
            inventory.setItem(13, skull);
            player.openInventory(inventory);
            return;
        }
    }

}
