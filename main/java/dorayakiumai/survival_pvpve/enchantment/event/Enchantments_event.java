package dorayakiumai.survival_pvpve.enchantment.event;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import dorayakiumai.survival_pvpve.UI.Open_UI;
import dorayakiumai.survival_pvpve.arrow.ArrowManager;
import dorayakiumai.survival_pvpve.customitem.CustomEnchantsManager;
import dorayakiumai.survival_pvpve.customitem.CustomItemsManager;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.AbstractArrow;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerChangedMainHandEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Enchantments_event implements Listener {
    Map<Player,BukkitTask> chargingTask = new HashMap<>();
    Map<Player,Float> countlist = new HashMap<>();
    @EventHandler
    public void CUSTOM_SHOT(EntityShootBowEvent event) {
        if (event.getEntity().getType() != EntityType.PLAYER) return;
        Player player = (Player) event.getEntity();
        if (player.getGameMode().equals(GameMode.CREATIVE)) return;
        if (player.getInventory().getItemInMainHand().getType().equals(Material.BOW) || countlist == null) {
            ItemStack usearrow = event.getConsumable();
//            player.sendMessage(usearrow.getType().toString());
            //                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("矢を放った!:最大火力まで" +  String.format("%.1f", count) + "s"));
            stopCountdown(player); // タスクをキャンセル
            float force = event.getForce(); //引き絞り度(?)が手に入る
//            ItemStack getarrow = ((Arrow) event.getProjectile()).getItem();
//            player.sendMessage(getarrow.getBasePotionType().toString());
            event.setCancelled(true); // 通常のアイテム動作をキャンセル
            player.updateInventory();
            ItemStack bow = event.getBow();
            ItemMeta meta = Objects.requireNonNull(bow).getItemMeta();
            float fasterSpeed = 1 + CustomEnchantsManager.getCustomEnchantLevel(bow,Survival_PvPvE.inst(),"ARROW_SPEED");
            if (meta != null && meta.getPersistentDataContainer().has(new NamespacedKey(Survival_PvPvE.inst(), "ARROW_SPEED"), PersistentDataType.INTEGER)) {
                fasterSpeed = fasterSpeed / 5;
            }
//            player.sendMessage(Objects.requireNonNull(usearrow).toString());
//            player.sendMessage(Objects.requireNonNull(usearrow).toString());
            Arrow arrow = player.launchProjectile(Arrow.class);
            arrow.setPickupStatus(AbstractArrow.PickupStatus.CREATIVE_ONLY);
//            player.sendMessage(Objects.requireNonNull(usearrow).getType().toString());
            if (Objects.requireNonNull(usearrow).getType().equals(Material.TIPPED_ARROW)) {
//                player.sendMessage("これ効果付きの矢だ!");
//                player.sendMessage(usearrow.toString());
                if (CustomItemsManager.isMythicItem(usearrow)) {
//                    player.sendMessage("MM");
                    List<PotionEffect> potion = ArrowManager.CheckMMArrowPotion(usearrow);
                    for (PotionEffect effect : potion) {
                        PotionEffectType type = effect.getType();
                        int amplifier = effect.getAmplifier();
                        int duration = effect.getDuration();
//                        player.sendMessage(effect.toString());
                        arrow.addCustomEffect(new PotionEffect(type, duration, amplifier), true);
                    }
                }
                else {
                    PotionType potion = ArrowManager.CheckArrowPotion(usearrow);
                    arrow.setBasePotionType(Objects.requireNonNull(potion));
                }
            }
            UseArrowRemove(usearrow,player);
            Float mycount = countlist.get(player);
            countlist.remove(player);
            if (mycount > 0.1F) {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.RED + "ダメージ半減: " + String.format("%.1f", mycount) + "s"));
                arrow.setVelocity(event.getProjectile().getVelocity().multiply(fasterSpeed + force));
                arrow.setDamage((2 * (1 + CustomEnchantsManager.getCustomEnchantLevel(bow, Survival_PvPvE.inst(), "DAMAGE")) * force) / 2);
            } else {
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.GREEN + "最大チャージ!"));
                arrow.setVelocity(event.getProjectile().getVelocity().multiply(fasterSpeed + force));
                arrow.setDamage(2 * (1 + CustomEnchantsManager.getCustomEnchantLevel(bow, Survival_PvPvE.inst(), "DAMAGE")) * force);
            }
        }
    }

        @EventHandler
        public void PlayerInteractCheck(PlayerInteractEvent event) {
            Player player = event.getPlayer();
            Action action = event.getAction();
            ItemStack mainitem = player.getInventory().getItemInMainHand();
            ItemStack offitem = player.getInventory().getItemInOffHand();
            if (offitem.getType().equals(Material.BOW)) event.setCancelled(true);
            // 右クリック（空中またはブロック）のチェック
            if (mainitem.hasItemMeta() && mainitem.getType().equals(Material.WRITTEN_BOOK) || mainitem.getType().equals(Material.FLINT_AND_STEEL)) {
                event.setCancelled(true); // 通常のアイテム動作をキャンセル
            }
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                if (CustomItemsManager.isMythicItem(mainitem) && mainitem.getType().equals(Material.WRITTEN_BOOK)) {
                    event.setCancelled(true); // 通常のアイテム動作をキャンセル
                    if (offitem.hasItemMeta() && CustomItemsManager.isMythicItem(offitem)) {
                        Open_UI.Open_UpgradeUI(player,offitem);
                    }
                    else {
                        Open_UI.Open_Select_Shop_UI(player);
                    }
                    return;
                }
                // カスタムアイテムかどうかのチェック
                if (mainitem.hasItemMeta() && mainitem.getType().equals(Material.BOW)) {
                    //バニラの効能付きの矢を撃つとエラーが出る
                    if (!player.getInventory().contains(Material.ARROW) && !player.getInventory().contains(Material.SPECTRAL_ARROW) && !player.getInventory().contains(Material.TIPPED_ARROW)) {
                        return;
                    }
                    stopCountdown(player);
                    startCountdown(player,mainitem);
                }
            }
        }
        @EventHandler
        public void PlayerMainhandItemCheck(PlayerChangedMainHandEvent event) {
            stopCountdown(event.getPlayer());
        }


        public void startCountdown(Player player,ItemStack bow) {
        float count = 5.0F;
            if (CustomEnchantsManager.getCustomEnchantLevel(bow, Survival_PvPvE.inst(), "SHOTTIME_SPEED") != 0) {
                count = count - 0.5F * CustomEnchantsManager.getCustomEnchantLevel(bow, Survival_PvPvE.inst(), "SHOTTIME_SPEED");
            }
            //SHOTTIME_SPEEDでここの変数を変化させる
            countlist.put(player,count);
            BukkitTask task = new BukkitRunnable() {
                @Override
                public void run() {
                    if (player.getInventory().getItemInMainHand().getType() != Material.BOW || player.isDead()) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("キャンセルしました"));
                        stopCountdown(player);
                    } else {
                        if (countlist.get(player) > 0.1F) {
                            countlist.put(player,countlist.get(player) - 0.1F);
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("最大チャージまで: " + String.format("%.1f", countlist.get(player)) + "s"));
                        } else {
                            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("最大チャージ!"));
                            this.cancel();
                        }
                    }
                }
            }.runTaskTimer(Survival_PvPvE.inst(), 0, 2); // 20 ticks = 1秒
            chargingTask.put(player,task);
        }
    public void stopCountdown(Player player) {
        if (chargingTask.containsKey(player)) {
            chargingTask.get(player).cancel();
            chargingTask.remove(player);
        }
    }

    public void UseArrowRemove(ItemStack arrow,Player player) {
        if (player.getInventory().getItemInOffHand().equals(arrow)) {
            int amountInSlot = player.getInventory().getItemInOffHand().getAmount();
            player.getInventory().getItemInOffHand().setAmount(amountInSlot - 1);
            return;
        }
        for (int i = 0; i < player.getInventory().getContents().length; i++) {
            ItemStack item = player.getInventory().getContents()[i];
            if (item == null) continue;
            if (item.getType() == arrow.getType()) {
                if (arrow.getType().equals(Material.ARROW)) {
                    int amountInSlot = item.getAmount();
                    item.setAmount(amountInSlot - 1);
                    return;
                }
                PotionMeta item_potionmeta = ((PotionMeta) item.getItemMeta());
                PotionMeta arrow_potionmeta = ((PotionMeta) arrow.getItemMeta());
                PotionType item_potiontype = Objects.requireNonNull(item_potionmeta).getBasePotionType();
                PotionType arrow_potiontype = Objects.requireNonNull(arrow_potionmeta).getBasePotionType();
                if (item_potiontype == arrow_potiontype) {
                    int amountInSlot = item.getAmount();
                    item.setAmount(amountInSlot - 1);
                    return;
                }
            }
        }
    }
    @EventHandler
    public void ArrowRemove(ProjectileHitEvent event) {
        if (event.getEntity() instanceof Arrow) {
            Arrow arrow = (Arrow) event.getEntity();
            arrow.remove();
        }
    }
}
