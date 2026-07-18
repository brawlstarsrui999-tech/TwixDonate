package ru.twixrpg.donate;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

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

            if (event.getCurrentItem() == null || !event.getCurrentItem().hasItemMeta()) return;

            int slot = event.getRawSlot();

            // Кнопка "Купить донат" — слот 49
            if (slot == 49) {
                gui.openBuyMenu(player);
                return;
            }

            // ЛКМ или ПКМ по привилегии — открываем кит
            switch (slot) {
                case 10: gui.openKitMenu(player, "coal");      break;
                case 19: gui.openKitMenu(player, "iron");      break;
                case 28: gui.openKitMenu(player, "gold");      break;
                case 11: gui.openKitMenu(player, "diamond");   break;
                case 20: gui.openKitMenu(player, "emerald");   break;
                case 29: gui.openKitMenu(player, "netherite"); break;
            }
        }

        // === КИТ-МЕНЮ (все привилегии) ===
        else if (isKitMenu(title)) {
            event.setCancelled(true);
            int slot = event.getRawSlot();
            if (slot == 45) {
                gui.openMainMenu(player);
            } else if (slot == 49) {
                gui.openBuyMenu(player);
            }
        }

        // === МЕНЮ ПОКУПКИ ===
        else if (title.equals(DonateGUI.BUY_TITLE)) {
            event.setCancelled(true);
            int slot = event.getRawSlot();

            if (slot == 45) {
                gui.openMainMenu(player);
            } else if (slot == 20) {
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

    /**
     * Проверяет, является ли инвентарь меню кита.
     */
    private boolean isKitMenu(String title) {
        return title.equals(DonateGUI.COAL_TITLE)
                || title.equals(DonateGUI.IRON_TITLE)
                || title.equals(DonateGUI.GOLD_TITLE)
                || title.equals(DonateGUI.DIAMOND_TITLE)
                || title.equals(DonateGUI.EMERALD_TITLE)
                || title.equals(DonateGUI.NETHERITE_TITLE);
    }
}