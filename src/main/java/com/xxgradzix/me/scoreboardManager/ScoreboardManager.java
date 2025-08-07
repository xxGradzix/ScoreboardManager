package com.xxgradzix.me.scoreboardManager;

import com.xxgradzix.me.scoreboardManager.customScoreboards.CustomPerPlayerScoreboard;
import com.xxgradzix.me.scoreboardManager.customScoreboards.ScoreboardRegistry;
import com.xxgradzix.me.scoreboardManager.listeners.OnJoinAddScoreboardListener;
import com.xxgradzix.me.scoreboardManager.messages.MessageManager;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

public final class ScoreboardManager extends JavaPlugin {

    private MessageManager messages;

    @Override
    public void onEnable() {
        // Plugin startup logic

        try {
            this.messages = ConfigManager.create(MessageManager.class, (it) -> {
                it.withConfigurer(new YamlBukkitConfigurer());
                it.withBindFile(new File(this.getDataFolder(), "scoreboards.yml"));
                it.saveDefaults();
                it.load(true);
            });
        } catch (Exception exception) {
            this.getLogger().log(Level.SEVERE, "Error loading scoreboards.yml", exception);
            return;
        }

        Map<String, MessageManager.ScoreboardDTO> scoreboards = messages.getScoreboards();

        for (Map.Entry<String, MessageManager.ScoreboardDTO> entry : scoreboards.entrySet()) {

            CustomPerPlayerScoreboard scoreboard = new CustomPerPlayerScoreboard(
                    entry.getKey(),
                    (player) -> entry.getValue().getTitle(),
                    (player) -> entry.getValue().getLines().stream().map(line -> PlaceholderAPI.setPlaceholders(player, line)).collect(Collectors.toList())
            );
        }

        getServer().getPluginManager().registerEvents(new OnJoinAddScoreboardListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
