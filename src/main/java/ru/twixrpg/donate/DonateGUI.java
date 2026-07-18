package ru.twixrpg.donate;

import com.earth2me.essentials.Kit;
import com.earth2me.essentials.MetaItemStack;  // ✅ Добавлен!
import net.ess3.api.IEssentials;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class DonateGUI {

    private final DonatePlugin plugin;

    // === Названия инвентарей ===
    public static final String MAIN_MENU_TITLE = "§5§lTwixRPG §8» §dДонат-привилегии";
    public static final String COAL_TITLE     = "§8§lCoal §8» §7Привилегия";
    public static final String IRON_TITLE     = "§7§lIron §8» §7Привилегия";
    public static final String GOLD_TITLE     = "§6§lGold §8» §7Привилегия";
    public static final String DIAMOND_TITLE  = "§b§lDiamond §8» §7Привилегия";
    public static final String EMERALD_TITLE  = "§a§lEmerald §8» §7Привилегия";
    public static final String NETHERITE_TITLE= "§4§lNetherite §8» §7Привилегия";
    public static final String BUY_TITLE      = "§5§lTwixRPG §8» §dКупить донат";

    // === Маппинг привилегий на Essentials киты ===
    private static final Map<String, List<String>> DONATE_KITS = new LinkedHashMap<>();
    static {
        DONATE_KITS.put("coal",     Collections.singletonList("coal"));
        DONATE_KITS.put("iron",     Collections.singletonList("iron"));
        DONATE_KITS.put("gold",     Collections.singletonList("gold"));
        DONATE_KITS.put("diamond",  Arrays.asList("diamond", "light"));
        DONATE_KITS.put("emerald",  Arrays.asList("emerald", "mid"));
        DONATE_KITS.put("netherite",Arrays.asList("netherite", "high"));
    }

    // === Слоты для содержимого китов (без рамки) ===
    private static final int[] INTERIOR_SLOTS = {
            10, 11, 12, 13, 14, 15, 16,
            19, 20, 21, 22, 23, 24, 25,
            28, 29, 30, 31, 32, 33, 34,
            37, 38, 39, 40, 41, 42, 43
    };

    public DonateGUI(DonatePlugin plugin) {
        this.plugin = plugin;
    }

    // ===================== ПОЛУЧЕНИЕ КИТОВ ИЗ ESSENTIALS =====================

    /**
     * Получает список предметов из кита Essentials.
     * @param kitName Название кита в Essentials
     * @return Список ItemStack из кита, или пустой список если кит не найден
     */
    private List<ItemStack> getKitItems(String kitName) {
        IEssentials ess = plugin.getEssentials();
        if (ess == null) return Collections.emptyList();
        try {
            Kit kit = new Kit(kitName, ess);
            List<String> itemStrings = kit.getItems(); // ✅ List<String> из конфига
            List<ItemStack> items = new ArrayList<>();
            for (String itemString : itemStrings) {
                try {
                    String[] parts = itemString.split(" +");
                    ItemStack parseStack = ess.getItemDb().get(
                            parts[0],
                            parts.length > 1 ? Integer.parseInt(parts[1]) : 1
                    );
                    if (parseStack.getType() != Material.AIR) {
                        MetaItemStack metaStack = new MetaItemStack(parseStack);
                        if (parts.length > 2) {
                            metaStack.parseStringMeta(
                                    null,
                                    ess.getSettings().allowUnsafeEnchantments(),
                                    parts, 2, ess
                            );
                        }
                        items.add(metaStack.getItemStack());
                    }
                } catch (Exception e) {
                    plugin.getLogger().warning(
                            "Не удалось распарсить предмет '" + itemString
                                    + "' из кита '" + kitName + "': " + e.getMessage()
                    );
                }
            }
            return items;
        } catch (Exception e) {
            plugin.getLogger().warning(
                    "Не удалось загрузить кит '" + kitName + "': " + e.getMessage()
            );
            return Collections.emptyList();
        }
    }
    /**
     * Форматирует название материала в читаемый вид.
     * IRON_SWORD -> Iron Sword
     */
    private String formatMaterialName(Material mat) {
        String name = mat.name().toLowerCase().replace("_", " ");
        StringBuilder sb = new StringBuilder();
        for (String word : name.split(" ")) {
            if (!word.isEmpty()) {
                sb.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1)).append(" ");
            }
        }
        return sb.toString().trim();
    }

    /**
     * Создаёт отображаемый предмет с именем, если оно не задано.
     */
    private ItemStack prepareDisplayItem(ItemStack original) {
        ItemStack display = original.clone();
        ItemMeta meta = display.getItemMeta();
        if (meta != null && !meta.hasDisplayName()) {
            meta.setDisplayName("§f" + formatMaterialName(display.getType()));
            display.setItemMeta(meta);
        }
        return display;
    }

    // ===================== ГЛАВНОЕ МЕНЮ =====================

    public void openMainMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, MAIN_MENU_TITLE);
        fillBorders(inv);

        // Coal — слот 10
        inv.setItem(10, createDonateItem(Material.COAL, "§8§l✦ COAL ✦", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Стоимость: §c25 ₽", "",
                "§dКиты:",
                "§7• §fcoal §8(кд §71 день§8)", "",
                "§dКоманды:",
                "§7• §f/craft §8(верстак)", "",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§e§l➤ §eНажмите §7для просмотра кита"
        )));

        // Iron — слот 19
        inv.setItem(19, createDonateItem(Material.IRON_INGOT, "§7§l✦ IRON ✦", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Стоимость: §c75 ₽", "",
                "§dКиты:",
                "§7• §firon §8(кд §71 день§8)", "",
                "§dКоманды:",
                "§7• §f/hat §7• §f/top §7• §f/craft", "",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§e§l➤ §eНажмите §7для просмотра кита"
        )));

        // Gold — слот 28
        inv.setItem(28, createDonateItem(Material.GOLD_INGOT, "§6§l✦ GOLD ✦", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Стоимость: §c150 ₽", "",
                "§dКиты:",
                "§7• §fgold §8(кд §72 дня§8)", "",
                "§dКоманды:",
                "§7• §f/fix §7• §f/feed §7• §f/craft",
                "§7• §f/hat §7• §f/top", "",
                "§dБонусы:",
                "§7• §fПрефикс на выбор",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§e§l➤ §eНажмите §7для просмотра кита"
        )));

        // Diamond — слот 11
        inv.setItem(11, createDonateItem(Material.DIAMOND, "§b§l✦ DIAMOND ✦", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Стоимость: §c300 ₽", "",
                "§dКиты:",
                "§7• §fdiamond §8(кд §72 дня§8)",
                "§7• §flight §8(кд §72 дня§8)", "",
                "§dКоманды:",
                "§7• §f/anvil §7• §f/craft §7• §f/hat",
                "§7• §f/top §7• §f/fix §7• §f/feed", "",
                "§dБонусы:",
                "§7• §fКастом роль в Discord §8(на выбор)",
                "§7• §fПрефикс на выбор",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§e§l➤ §eНажмите §7для просмотра кита"
        )));

        // Emerald — слот 20
        inv.setItem(20, createDonateItem(Material.EMERALD, "§a§l✦ EMERALD ✦", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Стоимость: §c399 ₽", "",
                "§dКиты:",
                "§7• §femerald §8(кд §73 дня§8)",
                "§7• §fmid §8(кд §72 дня§8)", "",
                "§dКоманды:",
                "§7• §f/ec §7• §f/craft §7• §f/hat",
                "§7• §f/top §7• §f/fix §7• §f/feed",
                "§7• §f/anvil", "",
                "§dБонусы:",
                "§7• §f2 Кастом роли в Discord §8(на выбор)",
                "§7• §fПрефикс на выбор",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§e§l➤ §eНажмите §7для просмотра кита"
        )));

        // Netherite — слот 29
        inv.setItem(29, createDonateItem(Material.NETHERITE_INGOT, "§4§l✦ NETHERITE ✦", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Стоимость: §c499 ₽", "",
                "§dКиты:",
                "§7• §fnetherite §8(кд §74 дня§8)",
                "§7• §fhigh §8(кд §74 дня§8)", "",
                "§dКоманды:",
                "§7• §f/tpa §7• §f/craft §7• §f/hat",
                "§7• §f/top §7• §f/fix §7• §f/feed",
                "§7• §f/anvil §7• §f/ec", "",
                "§dБонусы:",
                "§7• §f4 Кастом роли в Discord",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§e§l➤ §eНажмите §7для просмотра кита"
        )));

        // Кнопка "Купить донат"
        inv.setItem(49, createBuyButton());

        // Звезда по центру
        inv.setItem(4, createDecoItem(Material.NETHER_STAR, "§5§l★ TwixRPG ★", Arrays.asList(
                "§7Нажмите на привилегию,",
                "§7чтобы увидеть содержимое кита!"
        )));

        player.openInventory(inv);
    }

    // ===================== МЕНЮ КИТА (ДИНАМИЧЕСКОЕ ИЗ ESSENTIALS) =====================

    /**
     * Открывает меню кита для привилегии.
     * Предметы загружаются динамически из EssentialsX.
     */
    public void openKitMenu(Player player, String donateType) {
        List<String> kitNames = DONATE_KITS.get(donateType.toLowerCase());
        if (kitNames == null) return;

        String title;
        switch (donateType.toLowerCase()) {
            case "coal":     title = COAL_TITLE;      break;
            case "iron":     title = IRON_TITLE;      break;
            case "gold":     title = GOLD_TITLE;      break;
            case "diamond":  title = DIAMOND_TITLE;   break;
            case "emerald":  title = EMERALD_TITLE;   break;
            case "netherite":title = NETHERITE_TITLE; break;
            default:         title = MAIN_MENU_TITLE;
        }

        Inventory inv = Bukkit.createInventory(null, 54, title);
        fillBorders(inv);

        int slotIndex = 0;

        for (int k = 0; k < kitNames.size(); k++) {
            String kitName = kitNames.get(k);

            // Заголовок кита (если несколько китов)
            if (kitNames.size() > 1) {
                if (slotIndex < INTERIOR_SLOTS.length) {
                    inv.setItem(INTERIOR_SLOTS[slotIndex], createKitHeaderItem(kitName));
                    slotIndex++;
                }
            }

            // Загружаем предметы из Essentials
            List<ItemStack> kitItems = getKitItems(kitName);

            if (kitItems.isEmpty()) {
                // Кит не найден — показываем заглушку
                if (slotIndex < INTERIOR_SLOTS.length) {
                    inv.setItem(INTERIOR_SLOTS[slotIndex], createKitNotFoundItem(kitName));
                    slotIndex++;
                }
            } else {
                // Размещаем предметы из кита
                for (ItemStack item : kitItems) {
                    if (slotIndex >= INTERIOR_SLOTS.length) break;
                    inv.setItem(INTERIOR_SLOTS[slotIndex], prepareDisplayItem(item));
                    slotIndex++;
                }
            }

            // Переход на следующую строку для следующего кита
            if (k < kitNames.size() - 1) {
                int currentRow = slotIndex / 7;
                slotIndex = (currentRow + 1) * 7;
            }
        }

        // Инфо-предмет
        inv.setItem(4, createKitInfoItem(donateType));

        // Кнопки
        inv.setItem(45, createBackButton());
        inv.setItem(49, createBuyButton());

        player.openInventory(inv);
    }

    // ===================== МЕНЮ "КУПИТЬ ДОНАТ" =====================

    public void openBuyMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, BUY_TITLE);
        fillBordersPink(inv);

        inv.setItem(4, createDecoItem(Material.NETHER_STAR, "§5§l★ Купить привилегию ★", Arrays.asList(
                "§7Выберите удобный способ связи",
                "§7и напишите нам!"
        )));

        // Telegram
        inv.setItem(20, createContactItem(Material.PAPER, "§b§l✈ Telegram", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Напишите нам в Telegram:", "",
                "§f@Twixit0",
                "§f@Westaso", "",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§e§l➤ §eНажмите для копирования"
        )));

        // Discord
        inv.setItem(24, createContactItem(Material.BOOK, "§5§l✉ Discord", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Напишите нам в Discord:", "",
                "§ftwix687",
                "§fruin4ik", "",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§e§l➤ §eНажмите для копирования"
        )));

        inv.setItem(40, createDecoItem(Material.AMETHYST_SHARD, "§d§lКак купить?", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§71. §fВыберите привилегию в /donate",
                "§72. §fСвяжитесь с нами через Telegram",
                "   §fили Discord",
                "§73. §fСообщите ник и привилегию",
                "§74. §fПолучите свою привилегию!",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
        )));

        inv.setItem(45, createBackButton());
        player.openInventory(inv);
    }

    // ===================== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ =====================

    private void fillBorders(Inventory inv) {
        ItemStack purplePane = createGlassPane(Material.PURPLE_STAINED_GLASS_PANE, "§5 ");
        ItemStack pinkPane   = createGlassPane(Material.MAGENTA_STAINED_GLASS_PANE, "§d ");
        ItemStack darkPane   = createGlassPane(Material.BLACK_STAINED_GLASS_PANE, "§8 ");

        for (int i = 0; i < 9; i++) {
            inv.setItem(i, i % 2 == 0 ? purplePane : pinkPane);
        }
        for (int i = 45; i < 54; i++) {
            inv.setItem(i, i % 2 == 0 ? purplePane : pinkPane);
        }
        for (int i = 9; i < 45; i += 9) {
            inv.setItem(i, darkPane);
        }
        for (int i = 17; i < 54; i += 9) {
            inv.setItem(i, darkPane);
        }
        inv.setItem(1, purplePane);
        inv.setItem(7, purplePane);
    }

    private void fillBordersPink(Inventory inv) {
        ItemStack pinkPane   = createGlassPane(Material.PINK_STAINED_GLASS_PANE, "§d ");
        ItemStack purplePane = createGlassPane(Material.PURPLE_STAINED_GLASS_PANE, "§5 ");
        ItemStack magPane    = createGlassPane(Material.MAGENTA_STAINED_GLASS_PANE, "§d ");

        for (int i = 0; i < 9; i++) {
            inv.setItem(i, i % 2 == 0 ? pinkPane : purplePane);
        }
        for (int i = 45; i < 54; i++) {
            inv.setItem(i, i % 2 == 0 ? pinkPane : purplePane);
        }
        for (int i = 9; i < 45; i += 9) {
            inv.setItem(i, magPane);
        }
        for (int i = 17; i < 54; i += 9) {
            inv.setItem(i, magPane);
        }
    }

    private ItemStack createGlassPane(Material material, String name) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createDonateItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore);
            meta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createKitHeaderItem(String kitName) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§e§l▸ Кит: §f" + kitName);
            meta.setLore(Arrays.asList(
                    "§7Предметы из кита §f" + kitName,
                    "§7(загружено из Essentials)"
            ));
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createKitNotFoundItem(String kitName) {
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§c§lКит не найден!");
            meta.setLore(Arrays.asList(
                    "§7Кит §f" + kitName + " §7не найден в Essentials.",
                    "§7Убедитесь, что кит существует в §fkits.yml"
            ));
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createKitInfoItem(String donateType) {
        List<String> kitNames = DONATE_KITS.get(donateType.toLowerCase());
        List<String> lore = new ArrayList<>();
        lore.add("§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        String displayName;
        switch (donateType.toLowerCase()) {
            case "coal":     displayName = "§8§l✦ COAL ✦";     break;
            case "iron":     displayName = "§7§l✦ IRON ✦";     break;
            case "gold":     displayName = "§6§l✦ GOLD ✦";     break;
            case "diamond":  displayName = "§b§l✦ DIAMOND ✦";  break;
            case "emerald":  displayName = "§a§l✦ EMERALD ✦";  break;
            case "netherite":displayName = "§4§l✦ NETHERITE ✦";break;
            default:         displayName = "§f§l✦ DONATE ✦";
        }

        for (String kitName : kitNames) {
            lore.add("§7Кит: §f" + kitName);
        }

        lore.add("");
        lore.add("§7Предметы загружаются из §dEssentials");
        lore.add("§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬");

        return createInfoItem(displayName, lore);
    }

    private ItemStack createInfoItem(String name, List<String> lore) {
        ItemStack item = new ItemStack(Material.NETHER_STAR);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createDecoItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createContactItem(Material material, String name, List<String> lore) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(lore);
            meta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createBackButton() {
        return createDecoItem(Material.ARROW, "§c§l◄ Назад", Arrays.asList(
                "§7Нажмите, чтобы вернуться",
                "§7в главное меню"
        ));
    }

    private ItemStack createBuyButton() {
        ItemStack item = new ItemStack(Material.LIME_DYE);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§a§l✦ TwixRPG — Купить донат ✦");
            meta.setLore(Arrays.asList(
                    "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                    "§7Хотите купить привилегию?",
                    "§7Нажмите, чтобы узнать как!",
                    "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
            ));
            meta.addEnchant(Enchantment.LUCK_OF_THE_SEA, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(meta);
        }
        return item;
    }
}