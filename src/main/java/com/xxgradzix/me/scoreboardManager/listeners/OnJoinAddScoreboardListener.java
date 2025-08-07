package com.xxgradzix.me.scoreboardManager.listeners;

import com.xxgradzix.me.scoreboardManager.customScoreboards.ScoreboardRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnJoinAddScoreboardListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        ScoreboardRegistry.INSTANCE.getScoreboard("default").ifPresent(scoreboard -> {
            scoreboard.addPlayer(event.getPlayer());
        });

    }

}
