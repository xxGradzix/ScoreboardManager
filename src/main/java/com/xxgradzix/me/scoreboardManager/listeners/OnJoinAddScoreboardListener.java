package com.xxgradzix.me.scoreboardManager.listeners;

import com.xxgradzix.me.scoreboardManager.ScoreboardManager;
import com.xxgradzix.me.scoreboardManager.customScoreboards.ScoreboardRegistry;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinAddScoreboardListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Bukkit.getScheduler().runTaskLater(ScoreboardManager.instance, () -> {

            ScoreboardRegistry.INSTANCE.getScoreboard("default").ifPresent(scoreboard -> {
                scoreboard.addPlayer(event.getPlayer());
            });

        }, 0L);

    }

}
