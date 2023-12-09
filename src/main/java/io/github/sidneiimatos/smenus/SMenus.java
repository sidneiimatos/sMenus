package io.github.sidneiimatos.smenus;

import io.github.sidneiimatos.smenus.commands.MenuCommand;
import io.github.sidneiimatos.smenus.utils.ConfigAccesor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

public final class SMenus extends JavaPlugin {
    private static SMenus instance;
    public static ConfigAccesor config;

    @Override
    public void onEnable() {
        config = new ConfigAccesor(this, "config.yml");
        config.saveDefaultConfig();
        getCommand("menu").setExecutor((CommandExecutor) new MenuCommand());
        //registerEvents();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    /*public void registerEvents() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents((Listener)new MenuInventory(), (Plugin) this);
    }*/

    public static SMenus getInstance() {
        return instance;
    }
}