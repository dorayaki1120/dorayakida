package dorayakiumai.survival_pvpve.loot;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Track_LootBlock implements Listener {
    @EventHandler
    public void PlaceLootBlock(BlockPlaceEvent event) {
        ItemStack placedItem = event.getItemInHand();
        if (placedItem.hasItemMeta()) {
            ItemMeta meta = placedItem.getItemMeta();
            if (meta != null) {
                Location blockLocation = event.getBlock().getLocation();
                FileConfiguration config = Survival_PvPvE.inst().getConfig();
                if (meta.getPersistentDataContainer().has(new NamespacedKey(Survival_PvPvE.inst(), "lootblocks_tier_iron"), PersistentDataType.STRING)) {
                    List<String> locations = config.getStringList("lootblocks_tier_iron");
                    if (locations.contains(Objects.requireNonNull(blockLocation.getWorld()).getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ())) {
                        event.getPlayer().sendMessage("この座標は既に登録されているため無効になりました");
                        return;
                    }
                    locations.add(Objects.requireNonNull(blockLocation.getWorld()).getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ());
                    event.getPlayer().sendMessage("configに保存しました" + locations);
                    config.set("lootblocks_tier_iron", locations);
                    Survival_PvPvE.inst().saveConfig();
                    event.getPlayer().sendMessage(ChatColor.GREEN + "LootBlockを設置しました：" + meta.getDisplayName() + " (" + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ() + ")");
                }
                else if (meta.getPersistentDataContainer().has(new NamespacedKey(Survival_PvPvE.inst(), "lootblocks_tier_gold"), PersistentDataType.STRING)) {
                    List<String> locations = config.getStringList("lootblocks_tier_gold");
                    if (locations.contains(Objects.requireNonNull(blockLocation.getWorld()).getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ())) {
                        event.getPlayer().sendMessage("この座標は既に登録されているため無効になりました");
                        return;
                    }
                    locations.add(Objects.requireNonNull(blockLocation.getWorld()).getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ());
                    event.getPlayer().sendMessage("configに保存しました" + locations);
                    config.set("lootblocks_tier_gold", locations);
                    Survival_PvPvE.inst().saveConfig();
                    event.getPlayer().sendMessage(ChatColor.GREEN + "LootBlockを設置しました：" + meta.getDisplayName() + " (" + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ() + ")");
                }
                else if (meta.getPersistentDataContainer().has(new NamespacedKey(Survival_PvPvE.inst(), "lootblocks_tier_diamond"), PersistentDataType.STRING)) {
                    List<String> locations = config.getStringList("lootblocks_tier_diamond");
                    if (locations.contains(Objects.requireNonNull(blockLocation.getWorld()).getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ())) {
                        event.getPlayer().sendMessage("この座標は既に登録されているため無効になりました");
                        return;
                    }
                    locations.add(Objects.requireNonNull(blockLocation.getWorld()).getName() + "," + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ());
                    event.getPlayer().sendMessage("configに保存しました" + locations);
                    config.set("lootblocks_tier_diamond", locations);
                    Survival_PvPvE.inst().saveConfig();
                    event.getPlayer().sendMessage(ChatColor.GREEN + "LootBlockを設置しました：" + meta.getDisplayName() + " (" + blockLocation.getBlockX() + "," + blockLocation.getBlockY() + "," + blockLocation.getBlockZ() + ")");
                }
                else {
                    event.getPlayer().sendMessage("めたあったけど対象じゃないぜ!");
                }
            }
        }
    }

    @EventHandler
    public void BreakLootBlock(BlockBreakEvent event) {
        Block block = event.getBlock();
        Location location = block.getLocation();
        if (Material.IRON_BLOCK.isBlock() || Material.GOLD_BLOCK.isBlock() || Material.DIAMOND_BLOCK.isBlock()) {
            switch (block.getType()) {
                case IRON_BLOCK -> {
                    FileConfiguration config = Survival_PvPvE.inst().getConfig();
                    List<String> locations = config.getStringList("lootblocks_tier_iron");
                    if (locations.contains(Objects.requireNonNull(location.getWorld()).getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ())) {
                        List<String> removelocations = new ArrayList<>();
                        removelocations.add(Objects.requireNonNull(location.getWorld()).getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ());
//                            player.sendMessage("けすよていlocations:" + removelocations);
                        locations.removeAll(removelocations);
//                            player.sendMessage("きれいになったlocations:" + locations);
                        config.set("lootblocks_tier_iron", locations);
                        Survival_PvPvE.inst().saveConfig();
                        event.getPlayer().sendMessage(ChatColor.GREEN + "IRON" + ChatColor.GREEN + ":で登録した座標を削除しました->" + ChatColor.GRAY + location.getBlockX() + "/" + location.getBlockY() + "/" + location.getBlockZ());
                    }
                }
                case GOLD_BLOCK -> {
                    FileConfiguration config = Survival_PvPvE.inst().getConfig();
                    List<String> locations = config.getStringList("lootblocks_tier_gold");
                    if (locations.contains(Objects.requireNonNull(location.getWorld()).getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ())) {
                        List<String> removelocations = new ArrayList<>();
                        removelocations.add(Objects.requireNonNull(location.getWorld()).getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ());
//                            player.sendMessage("けすよていlocations:" + removelocations);
                        locations.removeAll(removelocations);
//                            player.sendMessage("きれいになったlocations:" + locations);
                        config.set("lootblocks_tier_gold", locations);
                        Survival_PvPvE.inst().saveConfig();
                        event.getPlayer().sendMessage(ChatColor.GREEN + "GOLD" + ChatColor.GREEN + ":で登録した座標を削除しました->" + ChatColor.GOLD + location.getBlockX() + "/" + location.getBlockY() + "/" + location.getBlockZ());
                    }
                }
                case DIAMOND_BLOCK -> {
                    FileConfiguration config = Survival_PvPvE.inst().getConfig();
                    List<String> locations = config.getStringList("lootblocks_tier_diamond");
                    if (locations.contains(Objects.requireNonNull(location.getWorld()).getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ())) {
                        List<String> removelocations = new ArrayList<>();
                        removelocations.add(Objects.requireNonNull(location.getWorld()).getName() + "," + location.getBlockX() + "," + location.getBlockY() + "," + location.getBlockZ());
//                            player.sendMessage("けすよていlocations:" + removelocations);
                        locations.removeAll(removelocations);
//                            player.sendMessage("きれいになったlocations:" + locations);
                        config.set("lootblocks_tier_diamond", locations);
                        Survival_PvPvE.inst().saveConfig();
                        event.getPlayer().sendMessage(ChatColor.GREEN + "DIAMOND" + ChatColor.GREEN + ":で登録した座標を削除しました->" + ChatColor.AQUA + location.getBlockX() + "/" + location.getBlockY() + "/" + location.getBlockZ());
                    }
                }
            }
        }
    }
}
