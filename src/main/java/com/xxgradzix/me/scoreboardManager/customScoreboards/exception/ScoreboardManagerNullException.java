package com.xxgradzix.me.scoreboardManager.customScoreboards.exception;

public class ScoreboardManagerNullException extends RuntimeException {
  public ScoreboardManagerNullException() {
    super("Bukkit's scoreboard manager is null. Please report this!");
  }
}
