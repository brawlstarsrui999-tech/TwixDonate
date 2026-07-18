package ru.twixrpg.donate;

import org.bukkit.plugin.java.JavaPlugin;

public class DonatePlugin extends JavaPlugin {

    private static DonatePlugin instance;

    @Override
    public void onEnable() {
        instance = this;
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
}
