package dorayakiumai.survival_pvpve.command;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import dorayakiumai.survival_pvpve.damagearea.damage_area;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

import static dorayakiumai.survival_pvpve.command.Check_Players.aliveplayerslist;

public class start_PvPvE implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 3) {
                double x = -213;
                double z = 526;
                int newSize = Integer.parseInt(args[0]); // ワールドボーダーの新しいサイズを取得
                int movetime = Integer.parseInt(args[1]);
                int cooltime = Integer.parseInt(args[2]);
                World world = Bukkit.getWorlds().get(0); // とりあえず最初のワールドを取得
                // コマンドを実行
                world.getWorldBorder().setCenter(x,z);
                world.getWorldBorder().setSize(newSize); // ワールドボーダーのサイズを変更
                sender.sendMessage("ダメージエリアを設定:" + "X座標" + x + ":Z座標" + z + ":移動時間" + movetime + "s:待機時間" + cooltime + "s");
                start(x,z,newSize,player,movetime,cooltime,world);
                return true;
            } else {
                sender.sendMessage("エラー: /start [worldborderのサイズ] [移動時間(秒)] [移動後の待機時間(秒)]");
                return true;
            }
        }
        return true;
    }

    public static void start(double x, double z, float limitarea, Player player, int waitingtime, int cooltime, World world) {
        playersstandby(x,z,limitarea,world);
        damage_area.game_phase = 0;
        damage_area.move_damage_area(x,z,limitarea,player,waitingtime,cooltime);
    }

    public static void playersstandby(double x, double z, float limitarea, World world) {
        for (Player player : aliveplayerslist) {
            // 安置内のランダムな座標を生成
            Random random = new Random();
            double teleportx = random.nextDouble(limitarea * 2) - limitarea;
            double teleportz = random.nextDouble(limitarea * 2) - limitarea;
            double y = 200; // 適切な高さを設定する必要があります
            double chickeny = 100;

//            player.sendMessage("ランダム座標:X/" + teleportx + ":Z/" + teleportz);

            teleportx = teleportx / 2 + x;
            teleportz = teleportz / 2 + z;

//            player.sendMessage("テレポート先:X/" + teleportx + ":Z/" + teleportz);
            Location dropLocation = new Location(world, teleportx, y, teleportz); // ドロップ地点を作成

// プレイヤーを指定された高さに移動し、エリトラを装備
            player.teleport(dropLocation);
            player.getInventory().setChestplate(new ItemStack(Material.ELYTRA));

// 指定された高さに達したら、鶏に乗せる
            new BukkitRunnable() {
                Chicken chicken = null; // 初期化
                @Override
                public void run() {
                    if (player.getLocation().getY() <= chickeny) {
//                        player.sendMessage("パラシュートに乗ります");

                        // 鶏がまだスポーンしていない場合は生成してプレイヤーに乗せる
                        if (chicken == null || chicken.isDead()) {
                            chicken = (Chicken) player.getWorld().spawnEntity(player.getLocation(), EntityType.CHICKEN);
                            chicken.addPassenger(player);
                        } else {
                            Location playerLocation = player.getLocation();
                            Vector direction = playerLocation.getDirection().normalize(); // プレイヤーの視線方向を正規化
                            double horizontalSpeed = 0.5; // 水平な速度を調整する必要がある場合は適宜変更
                            Vector horizontalVelocity = new Vector(direction.getX() * horizontalSpeed, 0, direction.getZ() * horizontalSpeed);
                            chicken.setVelocity(horizontalVelocity);
                            chicken.setVelocity(horizontalVelocity.setY(-0.25));
                            chicken.setGravity(true);
                            if (chicken.isOnGround() || chicken.isInWater()) {
                                chicken.remove();
                                player.getInventory().setChestplate(null);
                                cancel();
                            }
                        }
                    }
                }
            }.runTaskTimer(Survival_PvPvE.inst(), 0L, 1L);
        }
    }
}
