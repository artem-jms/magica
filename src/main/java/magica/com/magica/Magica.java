package magica.com.magica;

import magica.com.magica.command.MenuCommand;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.sql.Connection;

public final class Magica extends JavaPlugin {

    private static Magica instance;


    public Inventory inventory = Bukkit.createInventory(null, 9*6, "Menu");
    public Inventory skillsInventory = Bukkit.createInventory(null, 9*6, "Skills");

    @Override
    public void onEnable() {

        instance = this;
        new MenuCommand();
        GenerateMenus();
        Bukkit.getPluginManager().registerEvents(new EventListener(), this);
        BukkitScheduler scheduler = this.getServer().getScheduler();
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    double health = player.getHealth();
                    double maxHealth = player.getMaxHealth();
                    TextComponent hp = new TextComponent(ChatColor.RED + "❤ " + String.valueOf(Math.round(health)) + "/" + String.valueOf(Math.round(maxHealth)));
                    TextComponent def = new TextComponent(hp.getText() + ChatColor.GRAY + "     ◆ " + "0/0");
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, def);
                }
            }
        }, 0L, 10L);
    }

    @Override
    public void onDisable() {

    }

    public static Magica getInstance() {
        return instance;
    }

    private void GenerateMenus () {

    }
}
