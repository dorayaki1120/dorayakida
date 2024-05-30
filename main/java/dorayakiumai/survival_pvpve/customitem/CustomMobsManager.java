package dorayakiumai.survival_pvpve.customitem;

import io.lumine.mythic.api.MythicProvider;
import io.lumine.mythic.api.exceptions.InvalidMobTypeException;
import io.lumine.mythic.api.mobs.MobManager;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.bukkit.BukkitAPIHelper;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class CustomMobsManager {
    private static final Map<String, MythicMob> customMobs = new HashMap<>();
    static BukkitAPIHelper APIhelper = MythicBukkit.inst().getAPIHelper();

    public static void mobs_initialize() {
        MobManager mobManager = MythicProvider.get().getMobManager();
        MythicMob LootBox = mobManager.getMythicMob("LootBox").orElse(null);
        customMobs.put("LootBox", LootBox);
    }

    public static MythicMob getCustomMobs(String key) {
        return customMobs.get(key);  // アイテムの複製を返すことで、元のアイテムが変更されるのを防ぐ
    }

    public static void spawn_custommobs(Player player, String name, Location location) throws InvalidMobTypeException {
        if (getCustomMobs(name) == null) {
            player.getServer().broadcastMessage(ChatColor.RED + "モブを召喚できませんでした");
            return;
        }
        APIhelper.spawnMythicMob(name, location);
    }
}
