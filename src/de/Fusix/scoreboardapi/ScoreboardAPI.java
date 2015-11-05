package de.Fusix.scoreboardapi;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class ScoreboardAPI extends JavaPlugin {
    
    private static ArrayList<de.Fusix.scoreboardapi.Scoreboard> scoreboards = new ArrayList<>();
    
    public static Scoreboard newScoreboard(String type, String title, int duration) {
        Scoreboard sb = new de.Fusix.scoreboardapi.Scoreboard(type, title, duration);
        scoreboards.add(sb);
        return sb;
    }
        
    public static void setActiveOnPlayer(Player player, String scoreboardName) {
        for(int i = 0; i < scoreboards.size(); i++) {
            if(scoreboards.get(i).getName().equals(scoreboardName)) {
                scoreboards.get(i).apply(player);
                break;
            }
        }
    }
    
}
