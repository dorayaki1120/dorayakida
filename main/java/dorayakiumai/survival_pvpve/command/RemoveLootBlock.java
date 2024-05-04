package dorayakiumai.survival_pvpve.command;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RemoveLootBlock implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 4) {

                String tier = args[0];
                String X = args[1];
                String Y = args[2];
                String Z = args[3];

                int LocationX;
                int LocationY;
                int LocationZ;

                try {
                    LocationX = Integer.parseInt(X);
                    LocationY = Integer.parseInt(Y);
                    LocationZ = Integer.parseInt(Z);
                } catch (NumberFormatException e) {
                    player.sendMessage("数量は整数で指定してください。");
                    player.sendMessage("エラー: /remove_lootblock [tier] [X] [Y] [Z] ");
                    return true;
                }

                FileConfiguration config = Survival_PvPvE.inst().getConfig();

                if (Objects.equals(tier, "iron") || (Objects.equals(tier, "gold")) || (Objects.equals(tier, "diamond"))) {
                    switch (tier) {
                        case "iron" -> {
                            List<String> locations = config.getStringList("lootblocks_tier_iron");
//                            player.sendMessage("とってきたlocations:" + locations);
                            List<String> removelocations = new ArrayList<>();
                            removelocations.add(Objects.requireNonNull(player.getWorld()).getName() + "," + LocationX + "," + LocationY + "," + LocationZ);
//                            player.sendMessage("けすよていlocations:" + removelocations);
                            locations.removeAll(removelocations);
//                            player.sendMessage("きれいになったlocations:" + locations);
                            config.set("lootblocks_tier_iron", locations);
                            Survival_PvPvE.inst().saveConfig();
                            player.sendMessage(tier + ChatColor.GREEN + ":で登録した座標を削除しました->" + ChatColor.GRAY + LocationX + "/" + LocationY + "/" + LocationZ);
                        }
                        case "gold" -> {
                            List<String> locations = config.getStringList("lootblocks_tier_gold");
//                            player.sendMessage("とってきたlocations:" + locations);
                            List<String> removelocations = new ArrayList<>();
                            removelocations.add(Objects.requireNonNull(player.getWorld()).getName() + "," + LocationX + "," + LocationY + "," + LocationZ);
//                            player.sendMessage("けすよていlocations:" + removelocations);
                            locations.removeAll(removelocations);
//                            player.sendMessage("きれいになったlocations:" + locations);
                            config.set("lootblocks_tier_gold", locations);
                            Survival_PvPvE.inst().saveConfig();
                            player.sendMessage(tier + ChatColor.GREEN + ":で登録した座標を削除しました->" + ChatColor.GOLD + LocationX + "/" + LocationY + "/" + LocationZ);
                        }
                        case "diamond" -> {
                            List<String> locations = config.getStringList("lootblocks_tier_diamond");
//                            player.sendMessage("とってきたlocations:" + locations);
                            List<String> removelocations = new ArrayList<>();
                            removelocations.add(Objects.requireNonNull(player.getWorld()).getName() + "," + LocationX + "," + LocationY + "," + LocationZ);
//                            player.sendMessage("けすよていlocations:" + removelocations);
                            locations.removeAll(removelocations);
//                            player.sendMessage("きれいになったlocations:" + locations);
                            config.set("lootblocks_tier_diamond", locations);
                            Survival_PvPvE.inst().saveConfig();
                            player.sendMessage(tier + ChatColor.GREEN + ":で登録した座標を削除しました->" + ChatColor.AQUA + LocationX + "/" + LocationY + "/" + LocationZ);
                        }
                    }
                } else {
                    player.sendMessage("ティアーはこの中から選んでください(iron/gold/diamond)");
                    player.sendMessage("エラー: /remove_lootblock [tier] [X] [Y] [Z]");
                }
            } else {
                player.sendMessage("エラー: /remove_lootblock [tier] [X] [Y] [Z]");
            }
            return true;
        }
        return true;
    }
}
