package dorayakiumai.survival_pvpve.command;

import dorayakiumai.survival_pvpve.damagearea.damage_area;
import dorayakiumai.survival_pvpve.scoreboard.Survival_PvPvE_scoreboard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class end_PvPvE implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            damage_area.stop_damage_area(player);
            damage_area.remove_bossbar();
            Survival_PvPvE_scoreboard.removeScoreboard(player);
        }
        return true;
    }
}
