package com.xxgradzix.me.scoreboardManager;

import com.xxgradzix.me.scoreboardManager.customScoreboards.CustomScoreboard;
import com.xxgradzix.me.scoreboardManager.customScoreboards.ScoreboardRegistry;
import com.xxgradzix.me.scoreboardManager.messages.ScoreboardConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class CustomScoreboardCommand implements CommandExecutor {

    private final ScoreboardConfig scoreboardConfig;

    public CustomScoreboardCommand(ScoreboardConfig scoreboardConfig) {
        this.scoreboardConfig = scoreboardConfig;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if(strings.length == 1) {
            if(strings[0].equalsIgnoreCase("reload")) {
                ScoreboardRegistry.INSTANCE.loadScoreboardsFromConfig(scoreboardConfig);
                {
                    ScoreboardRegistry.INSTANCE.getScoreboard("default").ifPresent((sb) -> {

                        for (Player player : Bukkit.getOnlinePlayers()) {

                            sb.addPlayer(player);
                        }
                    });
                }
                return true;
            }
        }

        if(strings.length == 1) {
            if(strings[0].equalsIgnoreCase("switch")) {
                if (!(commandSender instanceof Player player)) {
                    commandSender.sendMessage("This command can only be used by players.");
                    return true;
                }
                ScoreboardRegistry.INSTANCE.closeOtherAllScoreboardsForPlayer(player);

                Set<CustomScoreboard> scoreboards = ScoreboardRegistry.INSTANCE.getScoreboards();

                CustomScoreboard nextScoreboard = scoreboards.stream()
                        .filter(sb -> !sb.getActivePlayersList().contains(player.getUniqueId()))
                        .findFirst()
                        .orElse(null);

                if (nextScoreboard != null) {
                    nextScoreboard.addPlayer(player);
                }

                return true;
            }

        }


        return false;
    }
}
