package com.xxgradzix.me.scoreboardManager;

import com.xxgradzix.me.scoreboardManager.customScoreboards.CustomPerPlayerScoreboard;
import com.xxgradzix.me.scoreboardManager.customScoreboards.CustomScoreboard;
import com.xxgradzix.me.scoreboardManager.customScoreboards.ScoreboardRegistry;
import com.xxgradzix.me.scoreboardManager.listeners.OnJoinAddScoreboardListener;
import com.xxgradzix.me.scoreboardManager.messages.ColorFixer;
import com.xxgradzix.me.scoreboardManager.messages.ScoreboardConfig;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

public final class ScoreboardManager extends JavaPlugin {

    public static Plugin instance;
    private ScoreboardConfig scoreboardConfig;

    @Override
    public void onEnable() {

        instance = this;

        try {
            this.scoreboardConfig = ConfigManager.create(ScoreboardConfig.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer());
                it.withBindFile(new File(this.getDataFolder(), "scoreboards.yml"));
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            this.getLogger().log(Level.SEVERE, "Error loading scoreboards.yml", exception);
            return;
        }

        getCommand("scoreboardManager").setExecutor(new CustomScoreboardCommand(scoreboardConfig));

        getServer().getPluginManager().registerEvents(new OnJoinAddScoreboardListener(), this);

        // Load scoreboards from config
        ScoreboardRegistry.INSTANCE.loadScoreboardsFromConfig(scoreboardConfig);
        ScoreboardRegistry.INSTANCE.scheduleUpdating();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
