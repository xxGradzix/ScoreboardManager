package com.xxgradzix.me.scoreboardManager.messages;

import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;
import eu.okaeri.configs.annotation.CustomKey;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MessageManager extends OkaeriConfig {

    public static class ScoreboardDTO implements Serializable {
        private String title;
        private List<String> lines;

        public ScoreboardDTO(String title, List<String> lines) {
            this.title = title;
            this.lines = lines;
        }

        public String getTitle() {
            return title;
        }

        public List<String> getLines() {
            return lines;
        }
    }

    @Comment("Scoreboard Config")

    @CustomKey("scoreboards")
    private Map<String, ScoreboardDTO> scoreboards = Collections.singletonMap("default", new ScoreboardDTO("fademc", List.of("Line1", "Line2")));

    public Map<String, ScoreboardDTO> getScoreboards() {
        return scoreboards;
    }

    public static void sendMessageFormated(Player player, String message, MessageType type) {
        message = ColorFixer.addColors(message);
        switch (type) {
            case TITLE:
                player.sendTitle(message, null, 15, 70, 15);
                break;
            case SUBTITLE:
                player.sendTitle(null, message, 15, 70, 15);
                break;
            case ACTIONBAR:
                player.sendActionBar(TextComponent.fromLegacyText(message));
                break;
            case CHAT:
                player.sendMessage(message);
                break;
        }
    }


    public static void broadcastMessageFormated(String message, MessageType messageType) {
        message = ColorFixer.addColors(message);
        switch (messageType) {
            case TITLE:
                for(Player p : Bukkit.getOnlinePlayers())
                    p.sendTitle(message, null, 15, 70, 15);
                break;
            case SUBTITLE:
                for(Player p : Bukkit.getOnlinePlayers())
                    p.sendTitle(null, message, 15, 70, 15);
                break;
            case ACTIONBAR:
                for(Player p : Bukkit.getOnlinePlayers())
                    p.sendActionBar(TextComponent.fromLegacyText(message));
                break;
            case CHAT:
                Bukkit.broadcastMessage(message);
                break;
        }
    }

    public static String getRomanNumerals(int num) {
        String romeNum = "";
        switch (num) {
            case 0 -> romeNum = "";
            case 1 -> romeNum = "&7ɪ";
            case 2 -> romeNum = "#877239ɪɪ";
            case 3 -> romeNum = "#68c473ɪɪɪ";
            case 4 -> romeNum = "#4c7ca1ɪᴠ";
            case 5 -> romeNum = "#a30005ᴠ";
            case 6 -> romeNum = "&fᴠɪ";
            case 7 -> romeNum = "ᴠɪɪ";
            case 8 -> romeNum = "ᴠɪɪɪ";
            case 9 -> romeNum = "ɪx";
            case 10 -> romeNum = "x";
        }
        return ColorFixer.addColors(romeNum);
    }

    public static String secondsToTimeFormat(int seconds) {
        int minutes = seconds / 60;
        int sec = seconds % 60;
        int hours = minutes / 60;
        minutes %= 60;
        StringBuilder sb = new StringBuilder();
        sb.append("§3");
        if(hours > 0) {
            sb.append(hours).append(" §3ɢᴏᴅᴢɪɴ §b");
        }
        if(minutes > 0) {
            sb.append(minutes).append(" §3ᴍɪɴᴜᴛ §b");
        }
        if(sec > 0) {
            sb.append(sec).append(" §3ꜱᴇᴋᴜɴᴅ §b");
        }

        return sb.toString();
    }
    public static String secondsToTimeFormatSkipSeconds(int seconds) {
        int minutes = seconds / 60;
        int hours = minutes / 60;
        minutes %= 60;
        StringBuilder sb = new StringBuilder();
        sb.append("§3");
        if(hours > 0) {
            sb.append(hours).append(" §3ɢᴏᴅᴢɪɴ §b");
        }
        if(minutes > 0) {
            sb.append(minutes).append(" §3ᴍɪɴᴜᴛ §b");
        }

        return sb.toString();
    }
}