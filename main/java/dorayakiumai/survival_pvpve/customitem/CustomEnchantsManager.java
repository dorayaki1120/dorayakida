package dorayakiumai.survival_pvpve.customitem;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class CustomEnchantsManager {

    public static void addCustomEnchantLevel(ItemStack item, JavaPlugin plugin, String enchantmentName, int uplevel) {
        NamespacedKey key = new NamespacedKey(plugin, enchantmentName);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            if (meta.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
                int level = getCustomEnchantLevel(item, plugin, enchantmentName);
                int setlevel = level + uplevel;
                setlore(meta, enchantmentName, setlevel);
                meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, setlevel);
                item.setItemMeta(meta);
            }
            else {
                meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, 1);
                setlore(meta, enchantmentName, 1);
                item.setItemMeta(meta);
            }
        }
    }

    public static void setItemCost(ItemStack item, JavaPlugin plugin, String costtype, int cost) {
        NamespacedKey key = new NamespacedKey(plugin, costtype);
        ItemMeta meta = item.getItemMeta();
        Objects.requireNonNull(meta).getPersistentDataContainer().set(key, PersistentDataType.INTEGER, cost);
        setcost(meta, costtype, cost);
        item.setItemMeta(meta);
    }

    public static int getItemCost(ItemStack item, JavaPlugin plugin, String costtype) {
        NamespacedKey key = new NamespacedKey(plugin, costtype);
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
            return meta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
        }
        else {
            return 0; // エンチャントがない場合は0を返す
        }
    }

    public static int getCustomEnchantLevel(ItemStack item, JavaPlugin plugin, String enchantmentName) {
        NamespacedKey key = new NamespacedKey(plugin, enchantmentName);
        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.getPersistentDataContainer().has(key, PersistentDataType.INTEGER)) {
            return meta.getPersistentDataContainer().get(key, PersistentDataType.INTEGER);
        }
        else {
            return 0; // エンチャントがない場合は0を返す
        }
    }

    public static void setlore(ItemMeta meta, String enchantname, int level) {
        String enchantDescription = "";
        switch (enchantname) {
            case "ARROW_SPEED" -> enchantDescription = "矢の速度上昇 ";
            case "SHOTTIME_SPEED" -> enchantDescription = "発射までの速度上昇 ";
            case "DAMAGE" -> enchantDescription = "威力上昇 ";
//            case "COST_IRON" -> enchantDescription = "コスト: [鉄インゴット] ";
//            case "COST_GOLD" -> enchantDescription = "コスト: [金インゴット] ";
//            case "COST_DIAMOND" -> enchantDescription = "コスト: [ダイヤモンド] ";
            default -> {
            }
        }
        List<String> lore = meta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }
        int lore_number = 0;
        for (String checklore : lore) {
            lore_number++;
            if (checklore.contains(enchantDescription)) {
                if (level == 10) {
                    lore.set(lore_number - 1, ChatColor.BOLD + "" + ChatColor.AQUA + enchantDescription + level);
                    meta.setLore(lore);
                    return;
                }
                lore.set(lore_number - 1, ChatColor.GRAY + enchantDescription + level);
                meta.setLore(lore);
                return;
                }
            }
        lore.add(ChatColor.GRAY + enchantDescription + level);
        meta.setLore(lore);
    }

    public static void setcost(ItemMeta meta, String costtype, int cost) {
        String costDescription = "";
        switch (costtype) {
            case "COST_UPGRADE" -> costDescription = "コスト: [このアイテム] ";
            case "COST_IRON" -> costDescription = "コスト: [鉄インゴット] ";
            case "COST_GOLD" -> costDescription = "コスト: [金インゴット] ";
            case "COST_DIAMOND" -> costDescription = "コスト: [ダイヤモンド] ";
            default -> {
            }
        }
        List<String> lore = meta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }
        lore.add(ChatColor.GRAY + "" + ChatColor.UNDERLINE + costDescription + cost + "個");
        meta.setLore(lore);
    }

    public static ItemStack removelore(ItemStack item, String notremovelore) {
        ItemMeta meta = item.getItemMeta();
        String enchantDescription = "";
        switch (notremovelore) {
            case "COST_UPGRADE" -> enchantDescription = "コスト: [このアイテム]";
            case "COST_IRON" -> enchantDescription = "コスト: [鉄インゴット]";
            case "COST_GOLD" -> enchantDescription = "コスト: [金インゴット]";
            case "COST_DIAMOND" -> enchantDescription = "コスト: [ダイヤモンド]";
            default -> {
            }
        }
        List<String> lore_list = Objects.requireNonNull(meta).getLore();
        if (lore_list == null) {
            lore_list = new ArrayList<>();
        }
        Iterator<String> iterator = Objects.requireNonNull(lore_list).iterator();
//        int lore_number = 0;
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (!element.contains(enchantDescription)) {
                iterator.remove();
            }
        }
        meta.setLore(lore_list);
        item.setItemMeta(meta);
        return item;
//        for (String checklore : lore) {
//            if (checklore.contains(enchantDescription)) {
//                continue;
//            }
//            lore.remove(lore_number);
//            meta.setLore(lore);
//            lore_number++;
//        }
//        meta.setLore(lore);
//        item.setItemMeta(meta);
//        return item;
    }
}
