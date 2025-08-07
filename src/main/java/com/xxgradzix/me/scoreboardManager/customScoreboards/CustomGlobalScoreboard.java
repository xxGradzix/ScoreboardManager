package com.xxgradzix.me.scoreboardManager.customScoreboards;

import com.xxgradzix.me.scoreboardManager.customScoreboards.exception.ScoreboardLineTooLongException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class CustomGlobalScoreboard extends CustomScoreboard {
  private Supplier<String> titleSupplier;
  private Supplier<List<String>> linesSupplier;

  private Scoreboard scoreboard;

  protected CustomGlobalScoreboard(String id, CustomScoreboardOptions options) {
    super(id);
    this.setOptions(options);
  }

  public CustomGlobalScoreboard(
        String id,
        Supplier<String> titleSupplier,
        Supplier<List<String>> linesSupplier,
        CustomScoreboardOptions options
  ) {
    super(id);
    this.titleSupplier = titleSupplier;
    this.linesSupplier = linesSupplier;
    this.setOptions(options);
  }

  public CustomGlobalScoreboard(
        String id,
        Supplier<String> titleSupplier,
        Supplier<List<String>> linesSupplier
  ) {
    super(id);
    this.titleSupplier = titleSupplier;
    this.linesSupplier = linesSupplier;
    this.setOptions(CustomScoreboardOptions.defaultOptions);
  }

  // MARK: Public API

  /**
   * Update the scoreboard. Call this when the scoreboard contents should change in some way.
   * @throws ScoreboardLineTooLongException Thrown if a line on the scoreboard is over 64 characters
   */
  public void updateScoreboard() throws ScoreboardLineTooLongException {
    createBukkitScoreboardIfNull();
    if (linesSupplier != null) updateScoreboard(toBukkitScoreboard(), linesSupplier.get());
  }

  /**
   * Add a player to the scoreboard
   * @param player The player to add
   */
  @Override
  public void addPlayer(Player player) {
    super.addPlayer(player);
    createBukkitScoreboardIfNull();
    player.setScoreboard(scoreboard);
  }

  /**
   * Set the options of the Scoreboard. Will update the scoreboard to reflect any changes.
   * @param options The new options of the scoreboard.
   */
  @Override
  public void setOptions(CustomScoreboardOptions options) {
    super.setOptions(options);
    updateScoreboard();
  }

  /**
   * Get the Bukkit Scoreboard that backs this JGlobalScoreboard.
   * @return The Bukkit Scoreboard
   */
  public Scoreboard toBukkitScoreboard() {
    return scoreboard;
  }

  // MARK: Private API

  /**
   * Creates the Bukkit Scoreboard for this scoreboard to use
   */
  private void createBukkitScoreboardIfNull() {
    if (this.scoreboard != null) return;

    ScoreboardManager scoreboardManager = Bukkit.getServer().getScoreboardManager();
    if (scoreboardManager == null) return;

    scoreboard = scoreboardManager.getNewScoreboard();

    for (UUID playerUUID : getActivePlayers()) {
      Player player = Bukkit.getPlayer(playerUUID);

      if (player != null) {
        player.setScoreboard(scoreboard);
      }
    }
  }

  protected void setLinesSupplier(Supplier<List<String>> linesSupplier) {
    this.linesSupplier = linesSupplier;
  }

  protected void setTitleSupplier(Supplier<String> titleSupplier) {
    this.titleSupplier = titleSupplier;
  }

  @Override
  protected String getTitle(Scoreboard scoreboard) {
    return titleSupplier.get();
  }
}
