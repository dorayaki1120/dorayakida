package dorayakiumai.survival_pvpve.enchantment.event;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import dorayakiumai.survival_pvpve.UI.Open_UI;
import dorayakiumai.survival_pvpve.customitem.CustomEnchantsManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.Objects;

public class Enchantments_event implements Listener {
    BukkitTask chargingTask;
    float count = 5.0F; // 5秒カウントダウン
    @EventHandler
    public void CUSTOM_SHOT(EntityShootBowEvent event) {

        Player player = (Player) event.getEntity();

            if (player.getInventory().getItemInMainHand().getType().equals(Material.BOW)) {
//                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("矢を放った!:最大火力まで" +  String.format("%.1f", count) + "s"));
                stopCountdown(); // タスクをキャンセル
                float force = event.getForce(); //引き絞り度(?)が手に入る
                event.setCancelled(true); // 通常のアイテム動作をキャンセル
                ItemStack bow = player.getInventory().getItemInMainHand();
                ItemMeta meta = bow.getItemMeta();
                float fasterSpeed = 1;
                if (meta != null && meta.getPersistentDataContainer().has(new NamespacedKey(Survival_PvPvE.inst(), "ARROW_SPEED"), PersistentDataType.INTEGER)) {
                    fasterSpeed = fasterSpeed + CustomEnchantsManager.getCustomEnchantLevel(bow,Survival_PvPvE.inst(),"ARROW_SPEED");
                }
                Arrow arrow = player.launchProjectile(Arrow.class);
                if (count > 0.1F) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "チャージ途中で撃ったため威力が半減しました:" + String.format("%.1f", count) + "s"));
                    arrow.setDamage((5 * (1 + CustomEnchantsManager.getCustomEnchantLevel(bow, Survival_PvPvE.inst(), "DAMAGE")) * force) / 2);
                    arrow.setVelocity(event.getProjectile().getVelocity().multiply(fasterSpeed * force));
                } else {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "最大チャージ!"));
                    arrow.setDamage(5 * (1 + CustomEnchantsManager.getCustomEnchantLevel(bow, Survival_PvPvE.inst(), "DAMAGE")) * force);
                    arrow.setVelocity(event.getProjectile().getVelocity().multiply(fasterSpeed * force));
                }
            }
        }

        @EventHandler
        public void PlayerInteractCheck(PlayerInteractEvent event) {
            Player player = event.getPlayer();
            Action action = event.getAction();
            if (player.getGameMode().equals(GameMode.CREATIVE)) return;
            ItemStack mainitem = player.getInventory().getItemInMainHand();
            ItemStack offitem = player.getInventory().getItemInOffHand();
            // 右クリック（空中またはブロック）のチェック
            if (mainitem.hasItemMeta() && mainitem.getType().equals(Material.WRITTEN_BOOK)) {
                event.setCancelled(true); // 通常のアイテム動作をキャンセル
            }
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                if (offitem.hasItemMeta() && Objects.requireNonNull(offitem.getItemMeta()).getPersistentDataContainer().has(new NamespacedKey(Survival_PvPvE.inst(), "weaponitems"))) {
                    if (Objects.requireNonNull(mainitem.getItemMeta()).getPersistentDataContainer().has(new NamespacedKey(Survival_PvPvE.inst(), "gameitems")) && mainitem.getType().equals(Material.WRITTEN_BOOK)) {
                        event.setCancelled(true); // 通常のアイテム動作をキャンセル
                        Open_UI.Open_UpgradeUI(player,offitem);
                        return;
                    }
                }
                // カスタムアイテムかどうかのチェック
                if (mainitem.hasItemMeta() && mainitem.getType().equals(Material.BOW)) {
                    stopCountdown();
                    startCountdown(player);
                }
            }
        }
        @EventHandler
        public void PlayerMainhandItemCheck(PlayerChangedMainHandEvent event) {
            stopCountdown();
        }


        public void startCountdown(Player player) {
            count = 5.0F;
            //SHOTTIME_SPEEDでここの変数を変化させる
            chargingTask = new BukkitRunnable() {

                @Override
                public void run() {
                    if (count > 0.1F) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("最大チャージまで: " + String.format("%.1f", count) + "s"));
                        count -= 0.1F;
                    } else {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("最大チャージ!"));
                    }
                }
            }.runTaskTimer(Survival_PvPvE.inst(), 0, 2); // 20 ticks = 1秒
        }

    public void stopCountdown() {
        if (chargingTask != null) {
            chargingTask.cancel();
            chargingTask = null; // タスクをnullに設定して、参照を解除
        }
    }
}
