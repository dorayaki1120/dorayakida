package dorayakiumai.survival_pvpve.damagearea;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class damage_area extends JavaPlugin{


    public static void move_damage_area(double x, double z, Player player){

        World world = Bukkit.getWorlds().get(0);
        AtomicReference<Double> nowX = new AtomicReference<>(world.getWorldBorder().getCenter().getX());
        AtomicReference<Double> nowZ = new AtomicReference<>(world.getWorldBorder().getCenter().getZ());

        int waitingtime = 10;
        int ticktime = 20;
        Random random = new Random();

        double nextX = x + random.nextInt(200) - 100;
        double nextZ = z + random.nextInt(200) - 100;

        player.sendMessage("次の地点が決まりました:X座標/" + nextX + ":Z座標/" + nextZ);
        AtomicInteger totaltime = new AtomicInteger(waitingtime * ticktime);

        double nextmovepointX = (nextX - x) / totaltime.get();
        double nextmovepointZ = (nextZ - z) / totaltime.get();

        //for(int i = 0; i < totaltime; i++) {
            //nowX += nextmovepointX;
            //nowZ += nextmovepointZ;
            //world.getWorldBorder().setCenter(nowX, nowZ);
        //
        //}
        Bukkit.getScheduler().runTaskTimer(Survival_PvPvE.getPlugin(Survival_PvPvE.class), () -> {
            nowX.updateAndGet(v -> v + nextmovepointX);
            nowZ.updateAndGet(v -> v + nextmovepointZ);
            world.getWorldBorder().setCenter(nowX.get(), nowZ.get());
            //Location = world.getWorldBorder().getCenter().getBlock().getLocation();
            //world.spawnParticle(Particle.END_ROD, location, 100, 0.0, 5, 0.0);
            //player.sendMessage("ば" + nowX + nowZ);
            totaltime.getAndDecrement();
            if (totaltime.decrementAndGet() < 0) {
                Bukkit.getScheduler().cancelTasks(Survival_PvPvE.getPlugin(Survival_PvPvE.class));
                player.sendMessage("目標地点に到達、再設定");
                move_damage_area(x,z,player);
            }
        }, 0L, 1L);
    }

    public static void stop_damage_area(Player player) {
        Bukkit.getScheduler().cancelTasks(Survival_PvPvE.getPlugin(Survival_PvPvE.class));
        World world = Bukkit.getWorlds().get(0);
        world.getWorldBorder().setCenter(player.getLocation().getX(), player.getLocation().getZ());
        world.getWorldBorder().setSize(100000);
        player.sendMessage("安置をストップしました");
    }
}
