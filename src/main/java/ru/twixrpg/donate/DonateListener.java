package ru.twixrpg.donate;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DonateListener implements Listener {

    private final DonatePlugin plugin;
    private final DonateGUI gui;

    public DonateListener(DonatePlugin plugin) {
        this.plugin = plugin;
        this.gui = new DonateGUI(plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();
        String title = event.getView().getTitle();

        // === ГЛАВНОЕ МЕНЮ ===
        if (title.equals(DonateGUI.MAIN_MENU_TITLE)) {
            event.setCancelled(true);

            ItemStack clicked = event.getCurrentItem();
            if (clicked == null || !clicked.hasItemMeta()) return;

            int slot = event.getRawSlot();
            ClickType clickType = event.getClick();

            // Кнопка "Купить донат" — слот 49
            if (slot == 49) {
                gui.openBuyMenu(player);
                return;
            }

            // ПКМ по донат-предметам — открываем кит
            if (clickType == ClickType.RIGHT) {
                switch (slot) {
                    case 10: gui.openKitMenu(player, "coal");     break;
                    case 19: gui.openKitMenu(player, "iron");     break;
                    case 28: gui.openKitMenu(player, "gold");     break;
                    case 11: gui.openKitMenu(player, "diamond");  break;
                    case 20: gui.openKitMenu(player, "emerald");  break;
                    case 29: gui.openKitMenu(player, "netherite"); break;
                }
            }
        }

        // === КИТ-МЕНЮ — COAL ===
        else if (title.equals(DonateGUI.COAL_TITLE)) {
            event.setCancelled(true);
            handleKitMenu(player, event.getRawSlot());
        }

        // === КИТ-МЕНЮ — IRON ===
        else if (title.equals(DonateGUI.IRON_TITLE)) {
            event.setCancelled(true);
            handleKitMenu(player, event.getRawSlot());
        }

        // === КИТ-МЕНЮ — GOLD ===
        else if (title.equals(DonateGUI.GOLD_TITLE)) {
            event.setCancelled(true);
            handleKitMenu(player, event.getRawSlot());
        }

        // === КИТ-МЕНЮ — DIAMOND ===
        else if (title.equals(DonateGUI.DIAMOND_TITLE)) {
            event.setCancelled(true);
            handleKitMenu(player, event.getRawSlot());
        }

        // === КИТ-МЕНЮ — EMERALD ===
        else if (title.equals(DonateGUI.EMERALD_TITLE)) {
            event.setCancelled(true);
            handleKitMenu(player, event.getRawSlot());
        }

        // === КИТ-МЕНЮ — NETHERITE ===
        else if (title.equals(DonateGUI.NETHERITE_TITLE)) {
            event.setCancelled(true);
            handleKitMenu(player, event.getRawSlot());
        }

        // === МЕНЮ ПОКУПКИ ===
        else if (title.equals(DonateGUI.BUY_TITLE)) {
            event.setCancelled(true);
            int slot = event.getRawSlot();

            if (slot == 45) {
                // Назад
                gui.openMainMenu(player);
            } else if (slot == 20) {
                // Telegram — отправляем сообщение в чат
                player.closeInventory();
                player.sendMessage("");
                player.sendMessage("§5§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                player.sendMessage("§d§l        ✈ TwixRPG — Telegram");
                player.sendMessage("§5§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                player.sendMessage("§7Напишите нам:");
                player.sendMessage("§f  • @Twixit0");
                player.sendMessage("§f  • @Westaso");
                player.sendMessage("§5§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                player.sendMessage("");
            } else if (slot == 24) {
                // Discord
                player.closeInventory();
                player.sendMessage("");
                player.sendMessage("§5§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                player.sendMessage("§d§l        ✉ TwixRPG — Discord");
                player.sendMessage("§5§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                player.sendMessage("§7Напишите нам:");
                player.sendMessage("§f  • twix687");
                player.sendMessage("§f  • ruin4ik");
                player.sendMessage("§5§l▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");
                player.sendMessage("");
            }
        }
    }

    // Обработка кнопок в меню кита (Назад и Купить)
    private void handleKitMenu(Player player, int slot) {
        if (slot == 45) {
            // Назад
            gui.openMainMenu(player);
        } else if (slot == 49) {
            // Купить донат
            gui.openBuyMenu(player);
        }
    }
}
