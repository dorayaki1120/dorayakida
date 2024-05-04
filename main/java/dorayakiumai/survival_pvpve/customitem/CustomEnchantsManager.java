package dorayakiumai.survival_pvpve.customitem;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

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
            case "ARROW_SPEED" -> enchantDescription = "矢の速度上昇";
            case "SHOTTIME_SPEED" -> enchantDescription = "発射までの速度上昇";
            case "DAMAGE" -> enchantDescription = "威力上昇";
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
                lore.set(lore_number - 1,ChatColor.GRAY + enchantDescription + level);
                meta.setLore(lore);
                return;
                }
            }
        lore.add(ChatColor.GRAY + enchantDescription + level);
        meta.setLore(lore);
    }
}
