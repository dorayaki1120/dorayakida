package dorayakiumai.survival_pvpve.command;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Custom_enchant implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length >= 1) {
                String enchantType = args[0]; // エンチャントの名前
                List<NamespacedKey> custom_enchantments = new ArrayList<>();
                custom_enchantments.add(new NamespacedKey(Survival_PvPvE.inst(), "ARROW_SPEED"));
                custom_enchantments.add(new NamespacedKey(Survival_PvPvE.inst(), "SHOTTIME_SPEED"));
                custom_enchantments.add(new NamespacedKey(Survival_PvPvE.inst(), "DAMAGE"));
                    ItemStack mainhand = player.getInventory().getItemInMainHand();
                    ItemMeta mainhand_meta = mainhand.getItemMeta();
                    if (mainhand_meta == null) {
                        player.sendMessage("手に持っているアイテムには適用できません。");
                        player.sendMessage("エラー:/custom_enchant [追加したいエンチャント名]");
                        return true;
                    }
                    for (NamespacedKey key : custom_enchantments) {
                        if (key.getKey().toUpperCase().equals(enchantType)) {
                            player.sendMessage("見つかったエンチャント" + key + "/探したエンチャント" + enchantType);
                            // エンチャントの名前に対応する説明を取得
                            String enchantDescription = "";
                            switch (enchantType) {
                                case "ARROW_SPEED" -> enchantDescription = "矢の速度上昇";
                                case "SHOTTIME_SPEED" -> enchantDescription = "発射までの速度上昇";
                                case "DAMAGE" -> enchantDescription = "威力上昇";
                                default -> {
                                }
                            }

                            int enchantLevel = 1;
                            mainhand.setItemMeta(mainhand_meta);
                            List<String> lore = mainhand_meta.getLore();
                            if (lore == null) {
                                lore = new ArrayList<>();
                            }
                            lore.add(ChatColor.GRAY + enchantDescription + enchantLevel);
                            mainhand_meta.setLore(lore);
                            mainhand_meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, enchantLevel);
                            mainhand.setItemMeta(mainhand_meta);
                            player.sendMessage(mainhand_meta.getPersistentDataContainer().getKeys().toString());
                            player.sendMessage(mainhand_meta.getDisplayName() + "に" + enchantType + "エンチャントを付与しました");
                            break;
                        }
                    }
                return true;
                }
            else {
                player.sendMessage("コマンドの入力が違います");
                player.sendMessage("エラー:/custom_enchant [追加したいエンチャント名]");
                return true;
            }
        }
        return true;
    }
}