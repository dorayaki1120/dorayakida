package dorayakiumai.survival_pvpve.command;

import dorayakiumai.survival_pvpve.customitem.CustomItemsManager;
import dorayakiumai.survival_pvpve.scoreboard.Survival_PvPvE_scoreboard;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import static dorayakiumai.survival_pvpve.game.PlayerEventTrack.applyPotionEffect;

public class Check_Players implements CommandExecutor {
    public static List<Player> aliveplayerslist = new ArrayList<>();
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            int joinplayerscount = 0;
            List<Player> joinplayers = new ArrayList<>();
            for (Player thisplayer : player.getServer().getOnlinePlayers()) {
                if (thisplayer.hasPotionEffect(PotionEffectType.GLOWING)) {
                    joinplayerscount = joinplayerscount + 1;
                    joinplayers.add(thisplayer);
                    if (joinplayerscount == 0) return true;
                    ready_joinplayers(joinplayers, joinplayerscount);
                    thisplayer.sendTitle(ChatColor.GREEN + "参加しました", ChatColor.GRAY + "開始までお待ちください", 10, 70, 20);
                }
                else if (thisplayer.getScoreboard().getObjective("Survival_PvPvE") != null) {
                    Survival_PvPvE_scoreboard.removeScoreboard(thisplayer);
                    joinplayers.remove(thisplayer);
                }
            }
        }
        return true;
    }

    public void ready_joinplayers(List<Player> joinplayers, int joinplayerscount) {
        aliveplayerslist = joinplayers;
        for (Player player : joinplayers) {
            Survival_PvPvE_scoreboard.updateScoreboard(player, joinplayerscount);
            Inventory playerInventory = player.getInventory();
            applyPotionEffect(player, PotionEffectType.SATURATION, 1, 100);
            playerInventory.addItem(CustomItemsManager.getCustomItem("gamebook")); // プレイヤーのインベントリにアイテムを追加
            player.sendMessage(ChatColor.GREEN + "ゲームブックを配布しました");
        }
    }
}
