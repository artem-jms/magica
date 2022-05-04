package magica.com.magica;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class EventListener implements Listener {

    Inventory menuInventory = Magica.getInstance().inventory;
    Inventory skillsInventory = Magica.getInstance().skillsInventory;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().setMaxHealth(10.0);
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity() instanceof ArmorStand) return;
        Location l = event.getEntity().getLocation();
        int maxExp = 20;
        int minExp = 1;
        double exp = minExp + Math.random()*maxExp;
        int maxPurse = 10;
        int minPurse = 1;
        double purse = minPurse + Math.random()*maxPurse;
        ArmorStand addictionStand = (ArmorStand) event.getEntity().getWorld().spawnEntity(l.add(0, -1, 0), EntityType.ARMOR_STAND);
        addictionStand.setVisible(false);
        addictionStand.setGravity(false);
        addictionStand.setInvulnerable(true);
        addictionStand.setCustomName(ChatColor.GREEN + "+" + String.valueOf(Math.round(exp)) + ChatColor.YELLOW + " +" + String.valueOf(Math.round(purse)));
        addictionStand.setCustomNameVisible(true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Magica.getPlugin(Magica.class), new Runnable() {
            @Override
            public void run() {
                addictionStand.remove();
            }
        }, 30L);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof ArmorStand) return;
        Location l = event.getEntity().getLocation();
        ArmorStand healthStand = (ArmorStand) event.getEntity().getWorld().spawnEntity(l.add(0, -.6, 0), EntityType.ARMOR_STAND);
        healthStand.setVisible(false);
        healthStand.setGravity(false);
        healthStand.setInvulnerable(true);
        healthStand.setCustomName(ChatColor.GRAY + String.valueOf(Math.round(event.getFinalDamage())));
        healthStand.setCustomNameVisible(true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(Magica.getPlugin(Magica.class), new Runnable() {
            @Override
            public void run() {
                healthStand.remove();
            }
        }, 15L);
    }

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() == menuInventory) {
            if (event.getCurrentItem() == null) return;
            event.setCancelled(true);
            ItemStack item = event.getCurrentItem();
            Player player = (Player) event.getWhoClicked();
            if (item.getType() == Material.PLAYER_HEAD) {
                openSkillsInventory(player);
            }
        }
        if (event.getInventory() == skillsInventory) {
            if (event.getCurrentItem() == null) return;
            event.setCancelled(true);
        }
    }


    public void openSkillsInventory (Player player) {
        List<String> lore = new ArrayList<>();

        ItemStack glassItem = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta glassMeta = glassItem.getItemMeta();
        assert glassMeta != null;
        glassMeta.setDisplayName(" ");
        glassItem.setItemMeta(glassMeta);
        for (int i = 0; i < skillsInventory.getSize(); i++) {
            skillsInventory.setItem(i, glassItem);
        }

        ItemStack power = new ItemStack(Material.GOLDEN_SWORD);
        ItemStack agility = new ItemStack(Material.FEATHER);
        ItemStack intelligence = new ItemStack(Material.LAPIS_LAZULI);

        ItemMeta powerMeta = power.getItemMeta();
        ItemMeta agilityMeta = agility.getItemMeta();
        ItemMeta intelligenceMeta = intelligence.getItemMeta();

        powerMeta.setDisplayName(ChatColor.RED + "❤ Сила " + "1 ");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Уровень: " + ChatColor.RED + "1");
        lore.add(" ");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Нажмите чтобы открыть прокачку");
        powerMeta.setLore(lore);
        powerMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        power.setItemMeta(powerMeta);
        skillsInventory.setItem(21, power);
        lore.clear();

        agilityMeta.setDisplayName(ChatColor.GOLD + "Ω Ловкость " + "1 ");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Уровень: " + ChatColor.GOLD + "1");
        lore.add(" ");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Нажмите чтобы открыть прокачку");
        agilityMeta.setLore(lore);
        agility.setItemMeta(agilityMeta);
        skillsInventory.setItem(22, agility);
        lore.clear();

        intelligenceMeta.setDisplayName(ChatColor.BLUE + "★ Интелект "+ "1 ");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Уровень: " + ChatColor.BLUE + "1");
        lore.add(" ");
        lore.add(" ");
        lore.add(ChatColor.GRAY + "Нажмите чтобы открыть прокачку");
        intelligenceMeta.setLore(lore);
        intelligence.setItemMeta(intelligenceMeta);
        skillsInventory.setItem(23, intelligence);
        lore.clear();

        player.openInventory(skillsInventory);

    }


}
