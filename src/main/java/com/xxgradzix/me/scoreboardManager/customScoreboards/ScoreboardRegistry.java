package com.xxgradzix.me.scoreboardManager.customScoreboards;

import com.xxgradzix.me.scoreboardManager.ScoreboardManager;
import com.xxgradzix.me.scoreboardManager.messages.ColorFixer;
import com.xxgradzix.me.scoreboardManager.messages.ScoreboardConfig;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class ScoreboardRegistry {

    public static final ScoreboardRegistry INSTANCE = new ScoreboardRegistry();

    public Set<CustomScoreboard> getScoreboards() {;
        return new HashSet<>(customScoreboards.values());
    }

    private final Map<String, CustomScoreboard> customScoreboards = new HashMap<>();

    private ScoreboardRegistry() {

    }

    public void addScoreboardToRegistry(String id, CustomScoreboard scoreboard) {
        if (scoreboard == null || id == null || id.isEmpty()) return;
        customScoreboards.put(id, scoreboard);
    }

    public void removeScoreboardFromRegistry(String id) {
        customScoreboards.remove(id);
    }

    public void closeOtherAllScoreboardsForPlayer(Player player) {
        for (CustomScoreboard customScoreboard : customScoreboards.values()) {
            if (customScoreboard.getActivePlayers().contains(player.getUniqueId())) {
                customScoreboard.removePlayer(player);
                return;
            }
        }
    }

    public Optional<CustomScoreboard> getScoreboard(String aDefault) {
        return Optional.ofNullable(customScoreboards.get(aDefault));
    }

    public void loadScoreboardsFromConfig(ScoreboardConfig scoreboardConfig) {
        if (scoreboardConfig == null) return;

        // Clear existing scoreboards
//        customScoreboards.clear();

        scoreboardConfig.load(true);
        Map<String, ScoreboardConfig.ScoreboardDTO> scoreboards = scoreboardConfig.getScoreboards();

        for (Map.Entry<String, ScoreboardConfig.ScoreboardDTO> entry : scoreboards.entrySet()) {

            CustomPerPlayerScoreboard scoreboard = new CustomPerPlayerScoreboard(
                    entry.getKey(),
                    (player) -> ColorFixer.addColors(PlaceholderAPI.setPlaceholders(player, entry.getValue().getTitle())),
                    (player) -> ColorFixer.addColors(entry.getValue().getLines().stream().map(line -> PlaceholderAPI.setPlaceholders(player, line)).collect(Collectors.toList()))
            );



        }

    }

    public void scheduleUpdating() {

        getScoreboard("default").ifPresent(scoreboard -> {

            Bukkit.getScheduler().runTaskTimer(
                    ScoreboardManager.instance,
                    scoreboard::updateScoreboard,
                    0L, 5 * 20L);

        });
    }
}
