package com.xxgradzix.me.scoreboardManager.customScoreboards;

import org.bukkit.entity.Player;

import java.util.*;

public final class CustomPerPlayerMethodBasedScoreboard extends CustomPerPlayerScoreboard {
  private final Map<UUID, String> playerToTitleMap = new HashMap<>();
  private final Map<UUID, List<String>> playerToLinesMap = new HashMap<>();

  public CustomPerPlayerMethodBasedScoreboard(String id, CustomScoreboardOptions options) {
    super(id, options);

    setGenerateTitleFunction(this::getTitle);
    setGenerateLinesFunction(this::getLines);
  }

  public CustomPerPlayerMethodBasedScoreboard(String id) {
    this(id, CustomScoreboardOptions.defaultOptions);
  }

  private String getTitle(Player player) {
    if (player == null) return "";
    return playerToTitleMap.getOrDefault(player.getUniqueId(), "");
  }

  public void setTitle(Player player, String title) {
    playerToTitleMap.put(player.getUniqueId(), title);
    updateScoreboard();
  }

  private List<String> getLines(Player player) {
    if (player == null) return Collections.emptyList();
    return playerToLinesMap.get(player.getUniqueId());
  }

  public void setLines(Player player, List<String> lines) {
    playerToLinesMap.put(player.getUniqueId(), lines);
    updateScoreboard();
  }

  public void setLines(Player player, String... lines) {
    playerToLinesMap.put(player.getUniqueId(), Arrays.asList(lines));
    updateScoreboard();
  }
}
