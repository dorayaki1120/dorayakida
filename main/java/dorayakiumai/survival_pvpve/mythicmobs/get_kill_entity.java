package dorayakiumai.survival_pvpve.mythicmobs;

//import io.lumine.mythic.api.mobs.MythicMob;

import dorayakiumai.survival_pvpve.customitem.CustomItemsManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;


public class get_kill_entity implements Listener {
    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

//        String name = "つよいゾンビ";
//        String customname = "'つよいゾンビ'";

//        for (Player player : Bukkit.getServer().getOnlinePlayers()) {
//
//        }
        if (Objects.requireNonNull(entity.getName()).contains("つよいゾンビ")) {
            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
                player.sendMessage("つよいゾンビをたおした");
                World deathworld = entity.getWorld();
                Location deathlocation = entity.getLocation();
                deathworld.dropItemNaturally(deathlocation, CustomItemsManager.getCustomItem("upgrade_ARROW_SPEED"));
            }
        }
//        if (Objects.requireNonNull(entity.getCustomName()).equals(customname)) {
//            for (Player player : Bukkit.getServer().getOnlinePlayers()) {
//                player.sendMessage("custom_つよいゾンビをたおした");
//            }
//        }
    }
}
