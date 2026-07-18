package ru.twixrpg.donate;

import net.ess3.api.IEssentials;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class DonatePlugin extends JavaPlugin {

    private static DonatePlugin instance;
    private IEssentials essentials;

    @Override
    public void onEnable() {
        instance = this;

        // Подключение к Essentials
        if (Bukkit.getPluginManager().getPlugin("Essentials") != null) {
            essentials = (IEssentials) Bukkit.getPluginManager().getPlugin("Essentials");
            getLogger().info("§aEssentials найден! Киты будут загружаться динамически.");
        } else {
            getLogger().warning("§cEssentials не найден! Содержимое китов не будет отображаться.");
            getLogger().warning("§cУстановите EssentialsX для корректной работы.");
        }

        getServer().getPluginManager().registerEvents(new DonateListener(this), this);
        getCommand("donate").setExecutor(new DonateCommand(this));
        getLogger().info("§dDonateGUI §7плагин успешно загружен! §5| TwixRPG");
    }

    @Override
    public void onDisable() {
        getLogger().info("§dDonateGUI §7отключён. §5| TwixRPG");
    }

    public static DonatePlugin getInstance() {
        return instance;
    }

    public IEssentials getEssentials() {
        return essentials;
    }
}