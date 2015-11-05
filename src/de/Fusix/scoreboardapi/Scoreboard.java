package de.Fusix.scoreboardapi;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

public class Scoreboard {
    
    private org.bukkit.scoreboard.Scoreboard sb;
    private Objective o;
    private String title;
    private String type;
    private HashMap<String, Integer> scores = new HashMap<>();
    
    public Scoreboard(String type, String title, int duration) {
        sb = Bukkit.getScoreboardManager().getNewScoreboard();
        o = sb.registerNewObjective(title, type);
        this.title = title;
        this.type = type;
        o.setDisplayName(title);
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
    }
    
    public void setTitle(String title) {
        o.setDisplayName(title);
    }

    public void setType(String type) {
        o = sb.registerNewObjective(title, type);
    }
    
    /*public static void setDuration() {
        
    }*/

    public void setScore(int score, String scoreName) {
        o.getScore(scoreName).setScore(score);
        scores.put(scoreName, score);
    }
    
    public void removeScore(String scoreName) {
        scores.remove(scoreName);
        sb.resetScores(scoreName);
    }
    
    public String getName() {
        return o.getDisplayName();
    }
    
    public void apply(Player player) {
        player.setScoreboard(sb);
    }

}
