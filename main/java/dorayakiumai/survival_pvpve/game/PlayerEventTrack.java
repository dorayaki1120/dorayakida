package dorayakiumai.survival_pvpve.game;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerEventTrack implements Listener {
    @EventHandler
    public void CheckJoinPlayers(PlayerMoveEvent event) {
        if (getUnderBlock(event.getPlayer()).equals(Material.AMETHYST_BLOCK)) {
            applyPotionEffect(event.getPlayer(), PotionEffectType.GLOWING, -1, 1);
        }
        else {
            event.getPlayer().removePotionEffect(PotionEffectType.GLOWING);
        }
    }

    public static Material getUnderBlock(Player player) {
        Location playerLocation = player.getLocation();
        World world = player.getWorld();
        int x = playerLocation.getBlockX();
        int y = playerLocation.getBlockY();
        int z = playerLocation.getBlockZ();
        return world.getBlockAt(x, y - 1, z).getType(); // プレイヤーの足元のブロック
    }

    public static void applyPotionEffect(Player player, PotionEffectType effectType, int duration, int amplifier) {
        // ポーション効果を作成
        PotionEffect effect = new PotionEffect(effectType, duration, amplifier);

        // プレイヤーにポーション効果を与える
        player.addPotionEffect(effect);
    }
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            // プレイヤーが窒息ダメージを受けていて、かつブロックに埋まっている場合
            if (event.getCause() == EntityDamageEvent.DamageCause.SUFFOCATION && player.isInsideVehicle()) {
                // ダメージを無効化する
                event.setCancelled(true);
            }
        }
    }
}
