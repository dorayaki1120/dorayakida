package dorayakiumai.survival_pvpve.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scoreboard.*;

import java.util.Objects;

import static dorayakiumai.survival_pvpve.command.Check_Players.aliveplayerslist;

public class Survival_PvPvE_scoreboard implements Listener {

    private static int aliveplayerscount = 0;
    public static void updateScoreboard(Player player, int remainingPlayers) {
        aliveplayerscount = remainingPlayers;
        // スコアボードマネージャーを取得
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = Objects.requireNonNull(manager).getNewScoreboard();

        // Objective を作成し、表示スロットを設定
        Objective objective = scoreboard.registerNewObjective("Survival_PvPvE", "dummy", "Survival_PvPvE");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        // スコアを設定
        Score remainingPlayersScore = objective.getScore("残り人数: " + remainingPlayers);
        remainingPlayersScore.setScore(1); // 1は表示順序です

        // プレイヤーにスコアボードを設定
        player.setScoreboard(scoreboard);
    }

    public static void removeScoreboard(Player player) {
        Objective objective = player.getScoreboard().getObjective("Survival_PvPvE");
        if (objective != null) {
            aliveplayerscount --;
            aliveplayerslist.remove(player);
            objective.unregister(); // 目標を登録解除する
            for (Player aliveplayer : aliveplayerslist) {
                updateScoreboard(aliveplayer, aliveplayerscount);
            }
        }
        if (aliveplayerscount == 1) {
            aliveplayerslist.get(0).sendTitle(ChatColor.GREEN + "優勝おめでとう", ChatColor.GOLD + aliveplayerslist.get(0).getName(), 10, 70, 20);
        }
    }

    private static Location deathlocation;
    @EventHandler
    public void deathjoinplayer(PlayerDeathEvent event) {
        Player player = event.getEntity();
        deathlocation = player.getLastDeathLocation();
        player.sendMessage(String.valueOf(deathlocation));
        player.setGameMode(GameMode.SPECTATOR);
        if (player.getScoreboard().getObjective("Survival_PvPvE") != null) {
            removeScoreboard(player);
        }
        if (player.getKiller() == null) {
            player.getServer().broadcastMessage(ChatColor.AQUA + "???" + ChatColor.RED + ChatColor.BOLD + " -> <KILL> -> " + ChatColor.DARK_PURPLE + player.getName());
            return;
        }
        Entity killer = player.getKiller();
        player.getServer().broadcastMessage(ChatColor.AQUA + "" + killer.getName() + ChatColor.RED + ChatColor.BOLD + " -> <KILL> -> " + ChatColor.DARK_PURPLE + player.getName());
    }

    @EventHandler
    public void respawnplayer(PlayerRespawnEvent event) {
        event.getPlayer().teleport(deathlocation);
    }
}
