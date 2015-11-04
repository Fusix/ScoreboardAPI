# ScoreboardAPI

## How to use

First you have to initialize a scoreboard and pass its instance to a variable. You can set your starting values here. You can change them later by using the methods mentioned below.

```java
Scoreboard sb = ScoreboardAPI.newScoreboard("dummy", "myScoreBoard", -1);
```

You have several options to choose e.g what kind of scoreboard you want and what should be displayed. You can also set the time you want the scoreboard to be displayed.

#### sb.setType(String type);
At this point you can set the type of the Scoreboard. In other words what your scoreboard is supposed to display.

| Criteria | Description          |
| ------------- | ----------- |
| dummy      | Score is only changed by commands, and not by game events such as death. This is useful for event flags, state mappings, and currencies.|
| trigger     | Score is only changed by commands, and not by game events such as death. The /trigger command can be used by a player to set or increment/decrement their own score in an objective with this criteria. The /trigger command will fail if the objective has not been "enabled" for the player using it, and the objective will be disabled for the player after they use the /trigger command on it. Note that the /trigger command can be used by ordinary players even if Cheats are off and they are not an Operator. This is useful for player input via /tellraw interfaces.    |
| deathCount     | Score increments automatically for a player when they die.     |
| playerKillCount     | Score increments automatically for a player when they kill another player.    |
| totalKillCount     | Score increments automatically for a player when they kill another player or a mob.     |
| health     | Ranges from 0 to 20 on a normal player; represents the amount of half-hearts the player has. May appear as 0 for players before their health has changed for the first time. Extra hearts and absorption hearts also count to the health score, meaning that with Attributes/Modifiers or the Health Boost or Absorption status effects, health can far surpass 20.    |

#### sb.setTitle(String title);
You can set the title of the Scoreboard. You can also use color codes.

___
*Not implemented yet!*
```text
sb.setDuration(int time);
This sets the duration how long the scoreboard should be displayed.
By setting the Duration to `-1` you will see the scoreboard as long as you live (a long time hopefully).
```
___

#### sb.setScore(int score, String scoreName);
You can set the score of something with a specific name. You may use color codes. 
*Keep in mind that you can only use integers for the score!*

#### sb.removeScore(String scoreName);
This option will remove the score of something called by its scorename completely from the scoreboard.

### sb.apply(Player player);
Applying your configured scoreboard to a Player is easy. Just use the method above and pass in the player who should see the scoreboard.
