package dorayakiumai.survival_pvpve.command;

import dorayakiumai.survival_pvpve.customitem.CustomItemsManager;
import dorayakiumai.survival_pvpve.damagearea.damage_area;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class start_PvPvE implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 1) {
                double x = player.getLocation().getX();
                double z = player.getLocation().getZ();
                int newSize = Integer.parseInt(args[0]); // ワールドボーダーの新しいサイズを取得
                World world = Bukkit.getWorlds().get(0); // とりあえず最初のワールドを取得
                // コマンドを実行
                world.getWorldBorder().setCenter(x,z);
                world.getWorldBorder().setSize(newSize); // ワールドボーダーのサイズを変更
                sender.sendMessage("ダメージエリアを設定しました:" + "X座標/" + x + ":Z座標/" + z);
                damage_area.move_damage_area(x,z,player);
                for (Player giveplayer : Bukkit.getServer().getOnlinePlayers()) {
                    Inventory playerInventory = player.getInventory();
                    playerInventory.addItem(CustomItemsManager.getCustomItem("gamebook")); // プレイヤーのインベントリにアイテムを追加
                    giveplayer.sendMessage(ChatColor.GREEN + "ゲームブックを配布しました");
                }
                return true;
            } else {
                sender.sendMessage("エラー: /start [worldborderのサイズ]");
                return true;
            }
        }
        return true;
    }
}
