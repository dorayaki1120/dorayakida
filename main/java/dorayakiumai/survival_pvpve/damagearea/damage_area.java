package dorayakiumai.survival_pvpve.damagearea;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import dorayakiumai.survival_pvpve.customitem.CustomMobsManager;
import dorayakiumai.survival_pvpve.loot.LootChest;
import io.lumine.mythic.api.exceptions.InvalidMobTypeException;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class damage_area extends JavaPlugin{

    private static BossBar bossBar;
    static List<BossBar> bossBars = new ArrayList<>();
    public static int game_phase = 0;
    private static final int ticktime = 20;
    public static void move_damage_area(double x, double z, float limitarea, Player player, int waitingtime, int cooltime){

        World world = Bukkit.getWorlds().get(0);

//        if (map_size <= limitarea + (world.getWorldBorder().getSize() / 2)) {
//            player.sendMessage("変更前" + limitarea);
//            world.getWorldBorder().setSize(map_size);
//            limitarea = (float) (map_size - world.getWorldBorder().getSize() / 2);
//            player.sendMessage("変更後" + limitarea);
//        }

        float afterlimitarea = Math.max(10, Math.min(game_phase * 10, 200));

        player.sendMessage("中心を決める範囲:" + afterlimitarea);

        AtomicReference<Double> nowX = new AtomicReference<>(world.getWorldBorder().getCenter().getX());
        AtomicReference<Double> nowZ = new AtomicReference<>(world.getWorldBorder().getCenter().getZ());

        Random random = new Random();

        double nextX = random.nextDouble(afterlimitarea * 2) - afterlimitarea;
        double nextZ = random.nextDouble(afterlimitarea * 2) - afterlimitarea;

        nextX = nextX + x - nowX.get();
        nextZ = nextZ + z - nowZ.get();

        player.getServer().broadcastMessage(ChatColor.RED + "|-phase" + game_phase + "-|");

        AtomicInteger totaltime = new AtomicInteger(waitingtime * ticktime);
        double nextmovepointX = (nextX) / totaltime.get();
        double nextmovepointZ = (nextZ) / totaltime.get();

        bossBar = Bukkit.createBossBar("move", BarColor.RED, BarStyle.SOLID);
        bossBars.add(bossBar);
        for (Player thisplayer : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(thisplayer);
        }
//        player.sendMessage("1tick当たりの移動量:X/" + nextmovepointX + ":Z/" + nextmovepointZ);
        world.getWorldBorder().setSize(Math.max(10, world.getWorldBorder().getSize() - (game_phase * 10)), waitingtime);
        new BukkitRunnable() {
            private double bossbar_time = waitingtime * ticktime;
            @Override
            public void run() {
                nowX.updateAndGet(v -> v + nextmovepointX);
                nowZ.updateAndGet(v -> v + nextmovepointZ);
                world.getWorldBorder().setCenter(nowX.get(), nowZ.get());

                bossBar.setTitle("安置移動まで: " + (Math.max(0, Math.min((int) bossbar_time / ticktime, waitingtime))) + "秒");
                bossBar.setProgress(Math.max(0, Math.min(bossbar_time / (waitingtime * ticktime), 1)));

                // 残り時間を減らす
                bossbar_time--;
                totaltime.decrementAndGet();

                // 残り時間が0未満の場合は再設定
                if (totaltime.get() < 0) {
                    // このタスクをキャンセルして再度呼び出す
                    cancel();
                    bossBar.setProgress(0);
                    bossBar.removeAll();
                    bossBars.remove(bossBar);
                    LootChest.SetLootChestInventory(player);
                    stay_damage_area(x, z, afterlimitarea, player, waitingtime, cooltime);
                }
            }
        }.runTaskTimer(Survival_PvPvE.getPlugin(Survival_PvPvE.class), 0L, 1L);
    }

    public static void stay_damage_area(double x, double z, float afterlimitarea, Player player, int waitingtime, int cooltime) {
        for (Player thisplayer : Bukkit.getOnlinePlayers()) {
            thisplayer.playSound(thisplayer.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
        }
        player.getServer().broadcastMessage(ChatColor.GREEN + "|-phase" + game_phase + "-|を乗り越えました");
        AtomicInteger totaltime = new AtomicInteger(cooltime * ticktime);
        bossBar = Bukkit.createBossBar("stay", BarColor.GREEN, BarStyle.SOLID);
        bossBars.add(bossBar);
        for (Player thisplayer : Bukkit.getOnlinePlayers()) {
            bossBar.addPlayer(thisplayer);
        }
        fall_lootbox(player, afterlimitarea, x, z);
        new BukkitRunnable() {
            private double bossbar_time = cooltime * ticktime;
            @Override
            public void run() {
                // 残り時間を減らす
                int time = Math.max(0, Math.min((int) Math.ceil(bossbar_time / ticktime), cooltime));
                bossBar.setTitle("再開まで: " + time + "秒");
                bossBar.setProgress(Math.max(0, Math.min(bossbar_time / (cooltime * ticktime), 1)));

                bossbar_time--;
                int aftertime = Math.max(0, Math.min((int) Math.ceil(bossbar_time / ticktime), cooltime));
                totaltime.decrementAndGet();

                if (time != aftertime && aftertime <= 5 && aftertime != 0) {
                    for (Player thisplayer : Bukkit.getOnlinePlayers()) {
                        thisplayer.sendTitle(ChatColor.YELLOW + "" + aftertime, ChatColor.YELLOW + "再開まで", 5, 20, 5);
                        thisplayer.playSound(thisplayer.getLocation(), Sound.BLOCK_ANVIL_PLACE, 1, 1);
                    }
                }
//                if (time != aftertime  && aftertime == 0) {
//                    for (Player thisplayer : Bukkit.getOnlinePlayers()) {
//                        thisplayer.sendTitle(ChatColor.RED + "" + aftertime, ChatColor.RED + "再開まで", 5, 20, 5);
//                    }
//                }
                // 残り時間が0未満の場合は再設定
                if (totaltime.get() < 0) {
                    // このタスクをキャンセルして再度呼び出す
                    cancel();
                    bossBar.setProgress(0);
                    bossBar.removeAll();
                    bossBars.remove(bossBar);
                    add_game_phase();
                    for (Player thisplayer : Bukkit.getOnlinePlayers()) {
                        thisplayer.sendTitle(ChatColor.RED + "" + aftertime, ChatColor.DARK_PURPLE + "|-phase" + game_phase + "-|", 5, 20, 5);
                        thisplayer.playSound(thisplayer.getLocation(), Sound.ENTITY_ENDER_DRAGON_AMBIENT, 1, 1);
                    }
                    move_damage_area(x, z, afterlimitarea, player, waitingtime, cooltime);
                }
            }
        }.runTaskTimer(Survival_PvPvE.getPlugin(Survival_PvPvE.class), 0L, 1L);
    }

    public static void remove_bossbar() {
        if (bossBars != null) {
            for (BossBar bossbar : bossBars) {
                bossbar.removeAll();
            }
        }
    }
    public static void stop_damage_area(Player player) {
        Bukkit.getScheduler().cancelTasks(Survival_PvPvE.getPlugin(Survival_PvPvE.class));
        World world = Bukkit.getWorlds().get(0);
        world.getWorldBorder().setCenter(player.getLocation().getX(), player.getLocation().getZ());
        world.getWorldBorder().setSize(100000);
        player.sendMessage("安置をストップしました");
    }

    public static void fall_lootbox(Player player, float afterlimitarea, double x, double z) {
        World world = Bukkit.getWorlds().get(0);
        final String lootbox = "LootBox";
        int lootbox_amount = 5; //補給物資の数
//        double map_size = 200;
        double nextY = 150; //高さ

        Random random = new Random();
        int i;
        player.getServer().broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "補給物資が投下されました");
        for (i = 1; i <= lootbox_amount; i++) {

            double nextX = random.nextDouble(afterlimitarea * 2) - afterlimitarea;
            double nextZ = random.nextDouble(afterlimitarea * 2) - afterlimitarea;

            double finalX;
            double finalZ;

            finalX = nextX / 2 + x;
            finalZ = nextZ / 2 + z;

            Location location = new Location(world, finalX, nextY, finalZ);
            try {
                CustomMobsManager.spawn_custommobs(player,lootbox,location);
            } catch (InvalidMobTypeException e) {
                player.sendMessage(ChatColor.RED + "モブがいなかったため召喚できませんでした");
            }
        }
    }

    public static void add_game_phase() {
        game_phase++;
    }
}
