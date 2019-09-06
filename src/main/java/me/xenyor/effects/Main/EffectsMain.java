package me.xenyor.effects.Main;

import me.xenyor.effects.InventoryManager.EffectsOpenInv;
import me.xenyor.effects.Listeners.EffectsInventoryClick;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class EffectsMain extends JavaPlugin {

    private static final Logger log = Logger.getLogger("Minecraft");

    private static YamlConfiguration customConfig;


    public void onEnable() {

        log.info("[Effects] Plugin has been enabled (V. " + getDescription().getVersion() + ")");

        createCustomConfig();
        registerCommands();
        registerEvents();

    }
    public void onDisable() {
        log.info("[Effects] Plugin has been disabled.");

    }

    private void registerCommands() {
        getCommand("effects").setExecutor(new EffectsOpenInv());

    }
    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new EffectsInventoryClick(), this);

    }

    public static YamlConfiguration getCustomConfig() {
        return customConfig;
    }

    private void createCustomConfig() {
        File customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}