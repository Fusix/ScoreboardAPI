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
    private int duration;
    private HashMap<String, Integer> scores = new HashMap<>();
    
    public Scoreboard(String type, String title, int duration) {
        sb = Bukkit.getScoreboardManager().getNewScoreboard();
        o = sb.registerNewObjective(title, type);
        this.title = title;
        o.setDisplayName(title);
        o.setDisplaySlot(DisplaySlot.SIDEBAR);
        startDisplayDuration();
    }
    
    private void startDisplayDuration() {
        if(duration < -1) {
            throw new IllegalArgumentException("duration must be -1 or greater");
        } else if(duration != -1) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(ScoreboardAPI.getPlugin(ScoreboardAPI.class), new Runnable() {
                @Override
                public void run() {
                    for(Player p : Bukkit.getServer().getOnlinePlayers()) {
                        if(p.getScoreboard().equals(sb)) {
                            p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
                        }
                    }
                }
            }, duration*20);
        }
    }
    
    public static enum ScoreboardPreset {
        Health, Health2
    }
    
    public void loadPreset(ScoreboardPreset sbp) {
        switch(sbp) {
            case Health:
                o.unregister();
                o = sb.registerNewObjective(title, "health");
                o.setDisplaySlot(DisplaySlot.BELOW_NAME);
                o.setDisplayName("§c❤");
                break;
            case Health2:
                o.unregister();
                o = sb.registerNewObjective(title, "health");
                o.setDisplaySlot(DisplaySlot.BELOW_NAME);
                o.setDisplayName("/ 20");
                break;
        }
    }
    
    public void setTitle(String title) {
        o.setDisplayName(title);
    }

    public void setType(String type) {
        o.unregister();
        o = sb.registerNewObjective(title, type);
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
        startDisplayDuration();
    }

    public void setScore(int score, String scoreName) {
        o.getScore(scoreName.replace('&', '§')).setScore(score);
        scores.put(scoreName.replace('&', '§'), score);
    }
    
    public void removeScore(String scoreName) {
        scores.remove(scoreName.replace('&', '§'));
        sb.resetScores(scoreName.replace('&', '§'));
    }
    
    public void removeAllScores() {
        for(String key : scores.keySet()) sb.resetScores(key);
        scores = new HashMap<>();
    }
    
    public String getName() {
        return o.getDisplayName();
    }
    
    public void apply(Player player) {
        player.setScoreboard(sb);
    }
    
    public void revoke(Player player) {
	player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

}
