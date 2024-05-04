package dorayakiumai.survival_pvpve.command;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class LootBlock implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            if (args.length == 2) {

                String blockType = args[0]; // ブロックの種類
                String amountStr = args[1]; // 数量の文字列

                int amount;

                try {
                    amount = Integer.parseInt(amountStr);
                } catch (NumberFormatException e) {
                    player.sendMessage("数量は整数で指定してください。");
                    player.sendMessage("エラー: /lootblock [ティアー(iron/gold/diamond)] [個数]");
                    return true;
                }

                if (Objects.equals(blockType, "iron") || (Objects.equals(blockType, "gold")) || (Objects.equals(blockType, "diamond"))) {
                    switch (blockType) {
                        case "iron" -> {
                            ItemStack lootblock_iron = new ItemStack(Material.IRON_BLOCK, amount);
                            ItemMeta lootblock_iron_meta = lootblock_iron.getItemMeta();
                            Objects.requireNonNull(lootblock_iron_meta).setDisplayName(ChatColor.GRAY + "LootBlock_iron");
                            lootblock_iron_meta.getPersistentDataContainer().set(new NamespacedKey(Survival_PvPvE.inst(), "lootblocks_tier_iron"), PersistentDataType.STRING, "IRON");
                            lootblock_iron.setItemMeta(lootblock_iron_meta);
                            player.getInventory().addItem(lootblock_iron);
                            player.sendMessage(lootblock_iron_meta.getDisplayName() + "を" + amount + "個入手しました");
                        }
                        case "gold" -> {
                            ItemStack lootblock_gold = new ItemStack(Material.GOLD_BLOCK, amount);
                            ItemMeta lootblock_gold_meta = lootblock_gold.getItemMeta();
                            Objects.requireNonNull(lootblock_gold_meta).setDisplayName(ChatColor.GOLD + "LootBlock_gold");
                            lootblock_gold_meta.getPersistentDataContainer().set(new NamespacedKey(Survival_PvPvE.inst(), "lootblocks_tier_gold"), PersistentDataType.STRING, "GOLD");
                            lootblock_gold.setItemMeta(lootblock_gold_meta);
                            player.getInventory().addItem(lootblock_gold);
                            player.sendMessage(lootblock_gold_meta.getDisplayName() + "を" + amount + "個入手しました");
                        }
                        case "diamond" -> {
                            ItemStack lootblock_diamond = new ItemStack(Material.DIAMOND_BLOCK, amount);
                            ItemMeta lootblock_diamond_meta = lootblock_diamond.getItemMeta();
                            Objects.requireNonNull(lootblock_diamond_meta).setDisplayName(ChatColor.AQUA + "LootBlock_diamond");
                            lootblock_diamond_meta.getPersistentDataContainer().set(new NamespacedKey(Survival_PvPvE.inst(), "lootblocks_tier_diamond"), PersistentDataType.STRING, "DIAMOND");
                            lootblock_diamond.setItemMeta(lootblock_diamond_meta);
                            player.getInventory().addItem(lootblock_diamond);
                            player.sendMessage(lootblock_diamond_meta.getDisplayName() + "を" + amount + "個入手しました");
                        }
                    }
                } else {
                    player.sendMessage("ティアーは三種類の中から選んでください");
                    player.sendMessage("エラー: /lootblock [ティアー(iron/gold/diamond)] [個数]");
                }
            } else {
                player.sendMessage("エラー: /lootblock [ティアー(iron/gold/diamond)] [個数]");
            }
            return true;
        }
        return true;
    }
}
