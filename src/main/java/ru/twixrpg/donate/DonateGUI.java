package ru.twixrpg.donate;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class DonateGUI {

    private final DonatePlugin plugin;

    // Названия инвентарей (для идентификации)
    public static final String MAIN_MENU_TITLE    = "§5§lTwixRPG §8» §dДонат-привилегии";
    public static final String COAL_TITLE         = "§8§lCoal §8» §7Привилегия";
    public static final String IRON_TITLE         = "§7§lIron §8» §7Привилегия";
    public static final String GOLD_TITLE         = "§6§lGold §8» §7Привилегия";
    public static final String DIAMOND_TITLE      = "§b§lDiamond §8» §7Привилегия";
    public static final String EMERALD_TITLE      = "§a§lEmerald §8» §7Привилегия";
    public static final String NETHERITE_TITLE    = "§4§lNetherite §8» §7Привилегия";
    public static final String BUY_TITLE          = "§5§lTwixRPG §8» §dКупить донат";

    public DonateGUI(DonatePlugin plugin) {
        this.plugin = plugin;
    }

    // ========================== ГЛАВНОЕ МЕНЮ ==========================

    public void openMainMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, MAIN_MENU_TITLE);

        // Заполнение рамки фиолетовыми/розовыми стёклами
        fillBorders(inv);

        // === Донат-предметы (2-я и 3-я строка по центру) ===

        // Coal — слот 10
        inv.setItem(10, createDonateItem(
                Material.COAL,
                "§8§l✦ COAL ✦",
                Arrays.asList(
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§7Стоимость: §c25 ₽",
                        "",
                        "§dКиты:",
                        "§7• §fcoal §8(кд §71 день§8)",
                        "",
                        "§dКоманды:",
                        "§7• §f/craft §8(верстак)",
                        "",
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§e§l➤ §eПКМ §7для просмотра содержимого кита"
                )
        ));

        // Iron — слот 19
        inv.setItem(19, createDonateItem(
                Material.IRON_INGOT,
                "§7§l✦ IRON ✦",
                Arrays.asList(
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§7Стоимость: §c75 ₽",
                        "",
                        "§dКиты:",
                        "§7• §firon §8(кд §71 день§8)",
                        "",
                        "§dКоманды:",
                        "§7• §f/hat §7• §f/top §7• §f/craft",
                        "",
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§e§l➤ §eПКМ §7для просмотра содержимого кита"
                )
        ));

        // Gold — слот 28
        inv.setItem(28, createDonateItem(
                Material.GOLD_INGOT,
                "§6§l✦ GOLD ✦",
                Arrays.asList(
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§7Стоимость: §c150 ₽",
                        "",
                        "§dКиты:",
                        "§7• §fgold §8(кд §72 дня§8)",
                        "",
                        "§dКоманды:",
                        "§7• §f/fix §7• §f/feed §7• §f/craft",
                        "§7• §f/hat §7• §f/top",
                        "",
                        "§dБонусы:",
                        "§7• §fПрефикс на выбор",
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§e§l➤ §eПКМ §7для просмотра содержимого кита"
                )
        ));

        // Diamond — слот 11
        inv.setItem(11, createDonateItem(
                Material.DIAMOND,
                "§b§l✦ DIAMOND ✦",
                Arrays.asList(
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§7Стоимость: §c300 ₽",
                        "",
                        "§dКиты:",
                        "§7• §fdiamond §8(кд §72 дня§8)",
                        "§7• §flight §8(кд §72 дня§8)",
                        "",
                        "§dКоманды:",
                        "§7• §f/anvil §7• §f/craft §7• §f/hat",
                        "§7• §f/top §7• §f/fix §7• §f/feed",
                        "",
                        "§dБонусы:",
                        "§7• §fКастом роль в Discord §8(на выбор)",
                        "§7• §fПрефикс на выбор",
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§e§l➤ §eПКМ §7для просмотра содержимого кита"
                )
        ));

        // Emerald — слот 20
        inv.setItem(20, createDonateItem(
                Material.EMERALD,
                "§a§l✦ EMERALD ✦",
                Arrays.asList(
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§7Стоимость: §c399 ₽",
                        "",
                        "§dКиты:",
                        "§7• §femerald §8(кд §73 дня§8)",
                        "§7• §fmid §8(кд §72 дня§8)",
                        "",
                        "§dКоманды:",
                        "§7• §f/ec §7• §f/craft §7• §f/hat",
                        "§7• §f/top §7• §f/fix §7• §f/feed",
                        "§7• §f/anvil",
                        "",
                        "§dБонусы:",
                        "§7• §f2 Кастом роли в Discord §8(на выбор)",
                        "§7• §fПрефикс на выбор",
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§e§l➤ §eПКМ §7для просмотра содержимого кита"
                )
        ));

        // Netherite — слот 29
        inv.setItem(29, createDonateItem(
                Material.NETHERITE_INGOT,
                "§4§l✦ NETHERITE ✦",
                Arrays.asList(
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§7Стоимость: §c499 ₽",
                        "",
                        "§dКиты:",
                        "§7• §fnetherite §8(кд §74 дня§8)",
                        "§7• §fhigh §8(кд §74 дня§8)",
                        "",
                        "§dКоманды:",
                        "§7• §f/tpa §7• §f/craft §7• §f/hat",
                        "§7• §f/top §7• §f/fix §7• §f/feed",
                        "§7• §f/anvil §7• §f/ec",
                        "",
                        "§dБонусы:",
                        "§7• §f4 Кастом роли в Discord",
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§e§l➤ §eПКМ §7для просмотра содержимого кита"
                )
        ));

        // Кнопка "Купить донат" — слот 49 (нижний правый угол, в декоративной рамке)
        inv.setItem(49, createBuyButton());

        // Декоративная звезда по центру верхней строки — слот 4
        inv.setItem(4, createDecoItem(Material.NETHER_STAR, "§5§l★ TwixRPG ★", Arrays.asList(
                "§7Выберите привилегию и нажмите §eПКМ",
                "§7чтобы увидеть содержимое кита!"
        )));

        player.openInventory(inv);
    }

    // ========================== КИТ-МЕНЮ ==========================

    public void openKitMenu(Player player, String donateType) {
        switch (donateType.toLowerCase()) {
            case "coal":     openCoalKit(player);     break;
            case "iron":     openIronKit(player);     break;
            case "gold":     openGoldKit(player);     break;
            case "diamond":  openDiamondKit(player);  break;
            case "emerald":  openEmeraldKit(player);  break;
            case "netherite":openNetheriteKit(player); break;
        }
    }

    private void openCoalKit(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, COAL_TITLE);
        fillBorders(inv);

        // Содержимое кита coal (примерный стандартный набор)
        inv.setItem(10, createKitItem(Material.IRON_SWORD,       "§8§lМеч угля",       1));
        inv.setItem(11, createKitItem(Material.IRON_PICKAXE,     "§8§lКирка угля",     1));
        inv.setItem(12, createKitItem(Material.IRON_AXE,         "§8§lТопор угля",     1));
        inv.setItem(13, createKitItem(Material.IRON_SHOVEL,      "§8§lЛопата угля",    1));
        inv.setItem(14, createKitItem(Material.BREAD,            "§fХлеб",             16));
        inv.setItem(15, createKitItem(Material.COAL,             "§8§lУголь",          32));
        inv.setItem(16, createKitItem(Material.TORCH,            "§eФакел",            32));

        inv.setItem(20, createKitItem(Material.IRON_HELMET,      "§8§lШлем угля",      1));
        inv.setItem(21, createKitItem(Material.IRON_CHESTPLATE,  "§8§lНагрудник угля", 1));
        inv.setItem(22, createKitItem(Material.IRON_LEGGINGS,    "§8§lПоножи угля",    1));
        inv.setItem(23, createKitItem(Material.IRON_BOOTS,       "§8§lБотинки угля",   1));

        // Инфо
        inv.setItem(4, createInfoItem("§8§l✦ КИТ: COAL ✦", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Кд кита: §f1 день",
                "§7Команды: §f/craft",
                "§7Стоимость: §c25 ₽",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
        )));

        inv.setItem(49, createBuyButton());
        inv.setItem(45, createBackButton());

        player.openInventory(inv);
    }

    private void openIronKit(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, IRON_TITLE);
        fillBorders(inv);

        inv.setItem(10, createKitItem(Material.IRON_SWORD,       "§7§lМеч железа",       1));
        inv.setItem(11, createKitItem(Material.IRON_PICKAXE,     "§7§lКирка железа",     1));
        inv.setItem(12, createKitItem(Material.IRON_AXE,         "§7§lТопор железа",     1));
        inv.setItem(13, createKitItem(Material.IRON_SHOVEL,      "§7§lЛопата железа",    1));
        inv.setItem(14, createKitItem(Material.COOKED_BEEF,      "§fСтейк",              24));
        inv.setItem(15, createKitItem(Material.IRON_INGOT,       "§7§lЖелезный слиток",  16));
        inv.setItem(16, createKitItem(Material.GOLDEN_APPLE,     "§6Золотое яблоко",     3));

        inv.setItem(20, createKitItem(Material.IRON_HELMET,      "§7§lШлем железа",      1));
        inv.setItem(21, createKitItem(Material.IRON_CHESTPLATE,  "§7§lНагрудник железа", 1));
        inv.setItem(22, createKitItem(Material.IRON_LEGGINGS,    "§7§lПоножи железа",    1));
        inv.setItem(23, createKitItem(Material.IRON_BOOTS,       "§7§lБотинки железа",   1));
        inv.setItem(24, createKitItem(Material.SHIELD,           "§7§lЩит",              1));

        inv.setItem(4, createInfoItem("§7§l✦ КИТ: IRON ✦", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Кд кита: §f1 день",
                "§7Команды: §f/hat, /top, /craft",
                "§7Стоимость: §c75 ₽",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
        )));

        inv.setItem(49, createBuyButton());
        inv.setItem(45, createBackButton());

        player.openInventory(inv);
    }

    private void openGoldKit(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, GOLD_TITLE);
        fillBorders(inv);

        inv.setItem(10, createKitItem(Material.GOLDEN_SWORD,     "§6§lМеч золота",       1));
        inv.setItem(11, createKitItem(Material.DIAMOND_PICKAXE,  "§6§lКирка золота",     1));
        inv.setItem(12, createKitItem(Material.DIAMOND_AXE,      "§6§lТопор золота",     1));
        inv.setItem(13, createKitItem(Material.DIAMOND_SHOVEL,   "§6§lЛопата золота",    1));
        inv.setItem(14, createKitItem(Material.COOKED_BEEF,      "§fСтейк",              32));
        inv.setItem(15, createKitItem(Material.GOLD_INGOT,       "§6§lЗолотой слиток",   32));
        inv.setItem(16, createKitItem(Material.GOLDEN_APPLE,     "§6Золотое яблоко",     8));

        inv.setItem(20, createKitItem(Material.DIAMOND_HELMET,    "§6§lШлем золота",      1));
        inv.setItem(21, createKitItem(Material.DIAMOND_CHESTPLATE,"§6§lНагрудник золота", 1));
        inv.setItem(22, createKitItem(Material.DIAMOND_LEGGINGS,  "§6§lПоножи золота",    1));
        inv.setItem(23, createKitItem(Material.DIAMOND_BOOTS,     "§6§lБотинки золота",   1));
        inv.setItem(24, createKitItem(Material.SHIELD,            "§6§lЩит",              1));
        inv.setItem(25, createKitItem(Material.EXPERIENCE_BOTTLE, "§aБутылка опыта",      16));

        inv.setItem(4, createInfoItem("§6§l✦ КИТ: GOLD ✦", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Кд кита: §f2 дня",
                "§7Команды: §f/fix, /feed, /craft, /hat, /top",
                "§7Бонусы: §fПрефикс на выбор",
                "§7Стоимость: §c150 ₽",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
        )));

        inv.setItem(49, createBuyButton());
        inv.setItem(45, createBackButton());

        player.openInventory(inv);
    }

    private void openDiamondKit(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, DIAMOND_TITLE);
        fillBorders(inv);

        inv.setItem(10, createKitItem(Material.DIAMOND_SWORD,     "§b§lАлмазный меч",       1));
        inv.setItem(11, createKitItem(Material.DIAMOND_PICKAXE,   "§b§lАлмазная кирка",     1));
        inv.setItem(12, createKitItem(Material.DIAMOND_AXE,       "§b§lАлмазный топор",     1));
        inv.setItem(13, createKitItem(Material.DIAMOND_SHOVEL,    "§b§lАлмазная лопата",    1));
        inv.setItem(14, createKitItem(Material.COOKED_BEEF,       "§fСтейк",                32));
        inv.setItem(15, createKitItem(Material.DIAMOND,           "§b§lАлмаз",              16));
        inv.setItem(16, createKitItem(Material.ENCHANTED_GOLDEN_APPLE,"§6Зачаровное яблоко",2));

        inv.setItem(19, createKitItem(Material.DIAMOND_HELMET,    "§b§lАлмазный шлем",      1));
        inv.setItem(20, createKitItem(Material.DIAMOND_CHESTPLATE,"§b§lАлмазный нагрудник", 1));
        inv.setItem(21, createKitItem(Material.DIAMOND_LEGGINGS,  "§b§lАлмазные поножи",    1));
        inv.setItem(22, createKitItem(Material.DIAMOND_BOOTS,     "§b§lАлмазные ботинки",   1));
        inv.setItem(23, createKitItem(Material.SHIELD,            "§b§lЩит",                1));
        inv.setItem(24, createKitItem(Material.EXPERIENCE_BOTTLE, "§aБутылка опыта",        32));
        inv.setItem(25, createKitItem(Material.TOTEM_OF_UNDYING,  "§eТотем",                1));

        // КИТ light
        inv.setItem(28, createKitItem(Material.GLOWSTONE,         "§e§lСветовой блок",      16));
        inv.setItem(29, createKitItem(Material.SEA_LANTERN,       "§b§lМорской фонарь",     8));
        inv.setItem(30, createKitItem(Material.LANTERN,           "§eФонарь",               16));

        inv.setItem(4, createInfoItem("§b§l✦ КИТ: DIAMOND ✦", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Кит §fdiamond §7кд: §f2 дня",
                "§7Кит §flight §7кд: §f2 дня",
                "§7Команды: §f/anvil, /craft, /hat,",
                "           §f/top, /fix, /feed",
                "§7Бонусы: §fПрефикс + роль в Discord",
                "§7Стоимость: §c300 ₽",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
        )));

        inv.setItem(49, createBuyButton());
        inv.setItem(45, createBackButton());

        player.openInventory(inv);
    }

    private void openEmeraldKit(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, EMERALD_TITLE);
        fillBorders(inv);

        inv.setItem(10, createKitItem(Material.DIAMOND_SWORD,     "§a§lЭмеральдовый меч",       1));
        inv.setItem(11, createKitItem(Material.DIAMOND_PICKAXE,   "§a§lЭмеральдовая кирка",     1));
        inv.setItem(12, createKitItem(Material.DIAMOND_AXE,       "§a§lЭмеральдовый топор",     1));
        inv.setItem(13, createKitItem(Material.DIAMOND_SHOVEL,    "§a§lЭмеральдовая лопата",    1));
        inv.setItem(14, createKitItem(Material.COOKED_BEEF,       "§fСтейк",                    48));
        inv.setItem(15, createKitItem(Material.EMERALD,           "§a§lИзумруд",                32));
        inv.setItem(16, createKitItem(Material.ENCHANTED_GOLDEN_APPLE,"§6Зачарованное яблоко",  4));

        inv.setItem(19, createKitItem(Material.DIAMOND_HELMET,    "§a§lЭмеральдовый шлем",      1));
        inv.setItem(20, createKitItem(Material.DIAMOND_CHESTPLATE,"§a§lЭмеральдовый нагрудник", 1));
        inv.setItem(21, createKitItem(Material.DIAMOND_LEGGINGS,  "§a§lЭмеральдовые поножи",    1));
        inv.setItem(22, createKitItem(Material.DIAMOND_BOOTS,     "§a§lЭмеральдовые ботинки",   1));
        inv.setItem(23, createKitItem(Material.SHIELD,            "§a§lЩит",                    1));
        inv.setItem(24, createKitItem(Material.EXPERIENCE_BOTTLE, "§aБутылка опыта",            48));
        inv.setItem(25, createKitItem(Material.TOTEM_OF_UNDYING,  "§eТотем",                    2));

        // Кит mid
        inv.setItem(28, createKitItem(Material.NETHER_STAR,       "§5§lЗвезда Незера",          1));
        inv.setItem(29, createKitItem(Material.BEACON,            "§b§lМаяк",                   1));
        inv.setItem(30, createKitItem(Material.AMETHYST_SHARD,    "§dАметист",                  32));

        inv.setItem(4, createInfoItem("§a§l✦ КИТ: EMERALD ✦", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Кит §femerald §7кд: §f3 дня",
                "§7Кит §fmid §7кд: §f2 дня",
                "§7Команды: §f/ec, /craft, /hat, /top,",
                "           §f/fix, /feed, /anvil",
                "§7Бонусы: §f2 роли в Discord + Префикс",
                "§7Стоимость: §c399 ₽",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
        )));

        inv.setItem(49, createBuyButton());
        inv.setItem(45, createBackButton());

        player.openInventory(inv);
    }

    private void openNetheriteKit(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, NETHERITE_TITLE);
        fillBorders(inv);

        inv.setItem(10, createKitItem(Material.NETHERITE_SWORD,     "§4§lМеч незерита",         1));
        inv.setItem(11, createKitItem(Material.NETHERITE_PICKAXE,   "§4§lКирка незерита",       1));
        inv.setItem(12, createKitItem(Material.NETHERITE_AXE,       "§4§lТопор незерита",       1));
        inv.setItem(13, createKitItem(Material.NETHERITE_SHOVEL,    "§4§lЛопата незерита",      1));
        inv.setItem(14, createKitItem(Material.COOKED_BEEF,         "§fСтейк",                  64));
        inv.setItem(15, createKitItem(Material.NETHERITE_INGOT,     "§4§lСлиток незерита",      8));
        inv.setItem(16, createKitItem(Material.ENCHANTED_GOLDEN_APPLE,"§6Зачарованное яблоко",  8));

        inv.setItem(19, createKitItem(Material.NETHERITE_HELMET,    "§4§lШлем незерита",        1));
        inv.setItem(20, createKitItem(Material.NETHERITE_CHESTPLATE,"§4§lНагрудник незерита",   1));
        inv.setItem(21, createKitItem(Material.NETHERITE_LEGGINGS,  "§4§lПоножи незерита",      1));
        inv.setItem(22, createKitItem(Material.NETHERITE_BOOTS,     "§4§lБотинки незерита",     1));
        inv.setItem(23, createKitItem(Material.SHIELD,              "§4§lЩит",                  1));
        inv.setItem(24, createKitItem(Material.EXPERIENCE_BOTTLE,   "§aБутылка опыта",          64));
        inv.setItem(25, createKitItem(Material.TOTEM_OF_UNDYING,    "§eТотем",                  3));

        // Кит high
        inv.setItem(28, createKitItem(Material.NETHER_STAR,         "§5§lЗвезда Незера",        2));
        inv.setItem(29, createKitItem(Material.BEACON,              "§b§lМаяк",                 1));
        inv.setItem(30, createKitItem(Material.ELYTRA,              "§dЭлитры",                 1));
        inv.setItem(31, createKitItem(Material.FIREWORK_ROCKET,     "§cРакета",                 16));
        inv.setItem(32, createKitItem(Material.AMETHYST_SHARD,      "§dАметист",                64));

        inv.setItem(4, createInfoItem("§4§l✦ КИТ: NETHERITE ✦", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§7Кит §fnetherite §7кд: §f4 дня",
                "§7Кит §fhigh §7кд: §f4 дня",
                "§7Команды: §f/tpa, /craft, /hat, /top,",
                "           §f/fix, /feed, /anvil, /ec",
                "§7Бонусы: §f4 роли в Discord",
                "§7Стоимость: §c499 ₽",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
        )));

        inv.setItem(49, createBuyButton());
        inv.setItem(45, createBackButton());

        player.openInventory(inv);
    }

    // ========================== МЕНЮ "КУПИТЬ ДОНАТ" ==========================

    public void openBuyMenu(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, BUY_TITLE);
        fillBordersPink(inv);

        inv.setItem(4, createDecoItem(Material.NETHER_STAR, "§5§l★ Купить привилегию ★", Arrays.asList(
                "§7Выберите удобный способ связи",
                "§7и напишите нам!"
        )));

        // Telegram
        inv.setItem(20, createContactItem(
                Material.PAPER,
                "§b§l✈ Telegram",
                Arrays.asList(
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§7Напишите нам в Telegram:",
                        "",
                        "§f@Twixit0",
                        "§f@Westaso",
                        "",
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§e§l➤ §eНажмите для копирования"
                )
        ));

        // Discord
        inv.setItem(24, createContactItem(
                Material.BOOK,
                "§5§l✉ Discord",
                Arrays.asList(
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§7Напишите нам в Discord:",
                        "",
                        "§ftw ix687",
                        "§fruin4ik",
                        "",
                        "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                        "§e§l➤ §eНажмите для копирования"
                )
        ));

        inv.setItem(40, createDecoItem(Material.AMETHYST_SHARD, "§d§lКак купить?", Arrays.asList(
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬",
                "§71. §fВыберите привилегию в /donate",
                "§72. §fСвяжитесь с нами через Telegram",
                "    §fили Discord",
                "§73. §fСообщите ник и привилегию",
                "§74. §fПолучите свою привилегию!",
                "§8▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬▬"
        )));

        inv.setItem(45, createBackButton());

        player.openInventory(inv);
    }

    // ========================== ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ==========================

    private void fillBorders(Inventory inv) {
        ItemStack purplePane = createGlassPane(Material.PURPLE_STAINED_GLASS_PANE, "§5 ");
        ItemStack pinkPane   = createGlassPane(Material.MAGENTA_STAINED_GLASS_PANE, "§d ");
        ItemStack darkPane   = createGlassPane(Material.BLACK_STAINED_GLASS_PANE, "§8 ");

        // Верхняя строка
        for (int i = 0; i < 9; i++) {
            inv.setItem(i, i % 2 == 0 ? purplePane : pinkPane);
        }
        // Нижняя строка
        for (int i = 45; i < 54; i++) {
            inv.setItem(i, i % 2 == 0 ? purplePane : pinkPane);
        }
        // Левая колонка
        for (int i = 9; i < 45; i += 9) {
            inv.setItem(i, darkPane);
        }
        // Правая колонка
        for (int i = 17; i < 54; i += 9) {
            inv.setItem(i, darkPane);
        }
        // Вторая строка разделитель для красоты
        inv.setItem(1, purplePane);
        inv.setItem(7, purplePane);
    }

    private void fillBordersPink(Inventory inv) {
        ItemStack pinkPane   = createGlassPane(Material.PINK_STAINED_GLASS_PANE,    "§d ");
        ItemStack purplePane = createGlassPane(Material.PURPLE_STAINED_GLASS_PANE,  "§5 ");
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

    private ItemStack createKitItem(Material material, String name, int amount) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_ENCHANTS);
            item.setItemMeta(meta);
        }
        return item;
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
