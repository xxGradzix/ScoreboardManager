package com.xxgradzix.me.scoreboardManager.customScoreboards;

import com.xxgradzix.me.scoreboardManager.ScoreboardManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ScoreboardRegistry {

    public static final ScoreboardRegistry INSTANCE = new ScoreboardRegistry();

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

    public void closeAllScoreboardsForPlayer(Player player) {
        for (CustomScoreboard customScoreboard : customScoreboards.values()) {
            customScoreboard.removePlayer(player);
        }
    }

    public Optional<CustomScoreboard> getScoreboard(String aDefault) {
        return Optional.ofNullable(customScoreboards.get(aDefault));
    }
}
