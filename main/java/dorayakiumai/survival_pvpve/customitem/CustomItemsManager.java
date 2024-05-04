package dorayakiumai.survival_pvpve.customitem;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class CustomItemsManager {
    private static final Map<String, ItemStack> customItems = new HashMap<>();

    public static void initialize() {
        //ARROW_SPEED
        ItemStack upgrade_ARROW_SPEED = new ItemStack(Material.SPYGLASS);
        ItemMeta upgradeitem_ARROW_SPEED_meta = upgrade_ARROW_SPEED.getItemMeta();
        Objects.requireNonNull(upgradeitem_ARROW_SPEED_meta).setDisplayName(ChatColor.GREEN + "望遠鏡");
        List<String> ARROW_SPEED_lore = upgradeitem_ARROW_SPEED_meta.getLore();
        if (ARROW_SPEED_lore == null) {
            ARROW_SPEED_lore = new ArrayList<>();
        }
        ARROW_SPEED_lore.add(ChatColor.GRAY + ":分類:");
        ARROW_SPEED_lore.add(ChatColor.GREEN + "強化用アイテム");
        ARROW_SPEED_lore.add(ChatColor.GRAY + ":説明:");
        ARROW_SPEED_lore.add(ChatColor.GREEN + "矢の速度を上げる");
        upgradeitem_ARROW_SPEED_meta.setLore(ARROW_SPEED_lore);
        upgradeitem_ARROW_SPEED_meta.getPersistentDataContainer().set(new NamespacedKey(Survival_PvPvE.inst(), "upgradeitems"), PersistentDataType.STRING, "矢の速度上昇");
        upgrade_ARROW_SPEED.setItemMeta(upgradeitem_ARROW_SPEED_meta);
        customItems.put("upgrade_ARROW_SPEED", upgrade_ARROW_SPEED);

        //DAMAGE
        ItemStack upgrade_DAMAGE = new ItemStack(Material.TNT_MINECART);
        ItemMeta upgrade_DAMAGE_meta = upgrade_DAMAGE.getItemMeta();
        Objects.requireNonNull(upgrade_DAMAGE_meta).setDisplayName(ChatColor.GREEN + "TNTトロッコ");
        List<String> DAMAGE_lore = upgrade_DAMAGE_meta.getLore();
        if (DAMAGE_lore == null) {
            DAMAGE_lore = new ArrayList<>();
        }
        DAMAGE_lore.add(ChatColor.GRAY + ":分類:");
        DAMAGE_lore.add(ChatColor.GREEN + "強化用アイテム");
        DAMAGE_lore.add(ChatColor.GRAY + ":説明:");
        DAMAGE_lore.add(ChatColor.GREEN + "矢のダメージを上げる");
        upgrade_DAMAGE_meta.setLore(DAMAGE_lore);
        upgrade_DAMAGE_meta.getPersistentDataContainer().set(new NamespacedKey(Survival_PvPvE.inst(), "upgradeitems"), PersistentDataType.STRING, "威力上昇");
        upgrade_DAMAGE.setItemMeta(upgrade_DAMAGE_meta);
        customItems.put("upgrade_DAMAGE", upgrade_DAMAGE);

        //SHOTTIME_SPEED
        ItemStack upgrade_SHOTTIME_SPEED = new ItemStack(Material.CHAIN);
        ItemMeta upgrade_SHOTTIME_SPEED_meta = upgrade_SHOTTIME_SPEED.getItemMeta();
        Objects.requireNonNull(upgrade_SHOTTIME_SPEED_meta).setDisplayName(ChatColor.GREEN + "鎖");
        List<String> SHOTTIME_lore = upgrade_SHOTTIME_SPEED_meta.getLore();
        if (SHOTTIME_lore == null) {
            SHOTTIME_lore = new ArrayList<>();
        }
        SHOTTIME_lore.add(ChatColor.GRAY + ":分類:");
        SHOTTIME_lore.add(ChatColor.GREEN + "強化用アイテム");
        SHOTTIME_lore.add(ChatColor.GRAY + ":説明:");
        SHOTTIME_lore.add(ChatColor.GREEN + "最大チャージまでの速度を早める");
        upgrade_SHOTTIME_SPEED_meta.setLore(SHOTTIME_lore);
        upgrade_SHOTTIME_SPEED_meta.getPersistentDataContainer().set(new NamespacedKey(Survival_PvPvE.inst(), "upgradeitems"), PersistentDataType.STRING, "発射までの速度上昇");
        upgrade_SHOTTIME_SPEED.setItemMeta(upgrade_SHOTTIME_SPEED_meta);
        customItems.put("upgrade_SHOTTIME_SPEED", upgrade_SHOTTIME_SPEED);

        //装飾用
        ItemStack limegrass = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
        ItemMeta limegrass_meta = limegrass.getItemMeta();
        Objects.requireNonNull(limegrass_meta).setDisplayName(ChatColor.AQUA + "");
        limegrass.setItemMeta(limegrass_meta);
        customItems.put("limegrass", limegrass);

        //装飾用
        ItemStack upgrade_itemcheck = new ItemStack(Material.BIRCH_SIGN);
        ItemMeta upgrade_itemcheck_meta = upgrade_itemcheck.getItemMeta();
        Objects.requireNonNull(upgrade_itemcheck_meta).setDisplayName(ChatColor.YELLOW + "" + ChatColor.BOLD + "↓強化する予定のアイテム↓");
        upgrade_itemcheck.setItemMeta(upgrade_itemcheck_meta);
        customItems.put("upgrade_itemcheck", upgrade_itemcheck);

        //gamebook
        ItemStack gamebook = new ItemStack(Material.WRITTEN_BOOK);
        ItemMeta gamebook_meta = gamebook.getItemMeta();
        Objects.requireNonNull(gamebook_meta).setDisplayName(ChatColor.AQUA + "ゲームブック");
        List<String> gamebook_lore = gamebook_meta.getLore();
        if (gamebook_lore == null) {
            gamebook_lore = new ArrayList<>();
        }
        gamebook_lore.add(ChatColor.GRAY + ":分類:");
        gamebook_lore.add(ChatColor.AQUA + "ゲーム用アイテム");
        gamebook_lore.add(ChatColor.GRAY + ":説明:");
        gamebook_lore.add(ChatColor.AQUA + "この本をメインに、強化したいアイテムをオフハンドに持ち");
        gamebook_lore.add(ChatColor.AQUA + "右クリックで強化画面を開ける");
        gamebook_meta.setLore(gamebook_lore);
        gamebook_meta.getPersistentDataContainer().set(new NamespacedKey(Survival_PvPvE.inst(), "gameitems"), PersistentDataType.STRING, "GAMEBOOK");
        gamebook.setItemMeta(gamebook_meta);
        customItems.put("gamebook", gamebook);

        //弓
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta bow_meta = bow.getItemMeta();
        Objects.requireNonNull(bow_meta).setDisplayName(ChatColor.RED + "弓");
        List<String> bow_lore = bow_meta.getLore();
        if (bow_lore == null) {
            bow_lore = new ArrayList<>();
        }
        bow_lore.add(ChatColor.GRAY + ":分類:");
        bow_lore.add(ChatColor.RED + "ウェポンアイテム");
        bow_lore.add(ChatColor.GRAY + ":説明:");
        bow_lore.add(ChatColor.RED + "" + ChatColor.BOLD + "弓を引いた際に出るカウントダウンが終わらないうちに撃つと");
        bow_lore.add(ChatColor.RED + "" + ChatColor.BOLD + "最大ダメージの半分しか与えられない");
        bow_lore.add(ChatColor.GRAY + ":エンチャント:");
        bow_meta.setLore(bow_lore);
        bow_meta.getPersistentDataContainer().set(new NamespacedKey(Survival_PvPvE.inst(), "weaponitems"), PersistentDataType.STRING, "BOW");
        bow.setItemMeta(bow_meta);
        customItems.put("bow", bow);
    }

    public static ItemStack getCustomItem(String key) {
        return customItems.get(key).clone();  // アイテムの複製を返すことで、元のアイテムが変更されるのを防ぐ
    }
}
