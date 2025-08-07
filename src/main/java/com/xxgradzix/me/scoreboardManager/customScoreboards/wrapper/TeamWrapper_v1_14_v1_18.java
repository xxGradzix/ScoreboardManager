package com.xxgradzix.me.scoreboardManager.customScoreboards.wrapper;

import org.bukkit.ChatColor;
import org.bukkit.scoreboard.Team;

public class TeamWrapper_v1_14_v1_18 extends InternalTeamWrapper {
  @Override
  public void setColor(Team team, ChatColor color) {
    team.setColor(color);
  }
}
