package dorayakiumai.survival_pvpve.UI;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import dorayakiumai.survival_pvpve.command.Inventory_Holder;
import dorayakiumai.survival_pvpve.customitem.CustomEnchantsManager;
import dorayakiumai.survival_pvpve.customitem.CustomItemsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public class Open_UI extends JavaPlugin {
    public static void Open_UpgradeUI(Player player, ItemStack offitem) {
        if (offitem.getType() == Material.BOW) {
            //skilltreeというgui自体の詳細を書く
            Inventory upgrade = Bukkit.createInventory(new Inventory_Holder(), 54, ChatColor.GREEN + "" + ChatColor.BOLD + "UPGRADE");
            //skilltreeというguiをプレイヤーに開かせる
            player.openInventory(upgrade);
            //forを使ってskilltreeのinventoryの中の全てのスロットに装飾に使うアイテム(glass)を置く
            int i;
            for (i = 0; i < 54; i++) {
                //iという変数が最初に0になって54以下の場合以下の文を実行する、実行した後にiという変数に1を足される
                upgrade.setItem(i, CustomItemsManager.getCustomItem("limegrass"));
            }
            //skilltreeというguiに入れるアイテムを書く
            upgrade.setItem(0, CustomItemsManager.getCustomItem("upgrade_ARROW_SPEED"));
            upgrade.setItem(1, CustomItemsManager.getCustomItem("upgrade_DAMAGE"));
            upgrade.setItem(2, CustomItemsManager.getCustomItem("upgrade_SHOTTIME_SPEED"));
            upgrade.setItem(31, CustomItemsManager.getCustomItem("upgrade_itemcheck"));
            upgrade.setItem(40, offitem);
            player.openInventory(upgrade);
        }
        if (offitem.getType() == Material.CROSSBOW) {
            //skilltreeというgui自体の詳細を書く
            Inventory upgrade = Bukkit.createInventory(new Inventory_Holder(), 54, ChatColor.GREEN + "" + ChatColor.BOLD + "UPGRADE");
            //skilltreeというguiをプレイヤーに開かせる
            player.openInventory(upgrade);
            //forを使ってskilltreeのinventoryの中の全てのスロットに装飾に使うアイテム(glass)を置く
            int i;
            for (i = 0; i < 54; i++) {
                //iという変数が最初に0になって54以下の場合以下の文を実行する、実行した後にiという変数に1を足される
                upgrade.setItem(i, CustomItemsManager.getCustomItem("limegrass"));
            }
            //skilltreeというguiに入れるアイテムを書く
            upgrade.setItem(0, CustomItemsManager.getCustomItem("upgrade_ARROW_SPEED"));
            upgrade.setItem(1, CustomItemsManager.getCustomItem("upgrade_DAMAGE"));
            upgrade.setItem(2, CustomItemsManager.getCustomItem("upgrade_SHOTTIME_SPEED"));
            upgrade.setItem(31, CustomItemsManager.getCustomItem("upgrade_itemcheck"));
            upgrade.setItem(40, offitem);
            player.openInventory(upgrade);
        }
    }

    public static void Open_Select_Shop_UI(Player player) {
        Inventory select_shop = Bukkit.createInventory(new Inventory_Holder(), 9, ChatColor.AQUA + "SELECT_SHOP");
        player.openInventory(select_shop);
        int i;
        for (i = 0; i < 9; i++) {
            select_shop.setItem(i, CustomItemsManager.getCustomItem("limegrass"));
        }

        select_shop.setItem(2, CustomItemsManager.getCustomItem("iron"));
        select_shop.setItem(4, CustomItemsManager.getCustomItem("gold"));
        select_shop.setItem(6, CustomItemsManager.getCustomItem("diamond"));
        player.openInventory(select_shop);
    }
    public static void Open_IRON_ShopUI(Player player) {
        //skilltreeというgui自体の詳細を書く
        Inventory shop = Bukkit.createInventory(new Inventory_Holder(), 54, ChatColor.GRAY + "" + ChatColor.BOLD + "IRON_SHOP");
        //skilltreeというguiをプレイヤーに開かせる
        player.openInventory(shop);
        //forを使ってskilltreeのinventoryの中の全てのスロットに装飾に使うアイテム(glass)を置く
        int i;
        for (i = 0; i < 54; i++) {
            //iという変数が最初に0になって54以下の場合以下の文を実行する、実行した後にiという変数に1を足される
            shop.setItem(i, CustomItemsManager.getCustomItem("limegrass"));
        }
        //skilltreeというguiに入れるアイテムを書く
        shop.setItem(0, CustomItemsManager.getShopItem("armor_IRON_HELMET"));
        shop.setItem(1, CustomItemsManager.getShopItem("armor_IRON_CHESTPLATE"));
        shop.setItem(2, CustomItemsManager.getShopItem("armor_IRON_LEGGINGS"));
        shop.setItem(3, CustomItemsManager.getShopItem("armor_IRON_BOOTS"));
        shop.setItem(4, CustomItemsManager.getShopItem("upgrade_ARROW_SPEED"));
        shop.setItem(5, CustomItemsManager.getShopItem("upgrade_DAMAGE"));
        shop.setItem(6, CustomItemsManager.getShopItem("upgrade_SHOTTIME_SPEED"));
        shop.setItem(9, CustomItemsManager.getShopItem("arrow_INVISIBILITY"));
        shop.setItem(10, CustomItemsManager.getShopItem("arrow_HUNGER"));
        shop.setItem(11, CustomItemsManager.getShopItem("arrow_DARKNESS"));
        shop.setItem(12, CustomItemsManager.getShopItem("arrow_SLOWNESS"));
        Check_Player_hasCost(player,shop,"IRON","COST_IRON");
        player.openInventory(shop);
    }

    public static void Open_GOLD_ShopUI(Player player) {
        //skilltreeというgui自体の詳細を書く
        Inventory shop = Bukkit.createInventory(new Inventory_Holder(), 54, ChatColor.GOLD + "" + ChatColor.BOLD + "GOLD_SHOP");
        //skilltreeというguiをプレイヤーに開かせる
        player.openInventory(shop);
        //forを使ってskilltreeのinventoryの中の全てのスロットに装飾に使うアイテム(glass)を置く
        int i;
        for (i = 0; i < 54; i++) {
            //iという変数が最初に0になって54以下の場合以下の文を実行する、実行した後にiという変数に1を足される
            shop.setItem(i, CustomItemsManager.getCustomItem("limegrass"));
        }
        //skilltreeというguiに入れるアイテムを書く
        shop.setItem(0, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.openInventory(shop);
    }

    public static void Open_DIAMOND_ShopUI(Player player) {
        //skilltreeというgui自体の詳細を書く
        Inventory shop = Bukkit.createInventory(new Inventory_Holder(), 54, ChatColor.AQUA + "" + ChatColor.BOLD + "DIAMOND_SHOP");
        //skilltreeというguiをプレイヤーに開かせる
        player.openInventory(shop);
        //forを使ってskilltreeのinventoryの中の全てのスロットに装飾に使うアイテム(glass)を置く
        int i;
        for (i = 0; i < 54; i++) {
            //iという変数が最初に0になって54以下の場合以下の文を実行する、実行した後にiという変数に1を足される
            shop.setItem(i, CustomItemsManager.getCustomItem("limegrass"));
        }
        //skilltreeというguiに入れるアイテムを書く
        shop.setItem(0, new ItemStack(Material.TOTEM_OF_UNDYING));
        player.openInventory(shop);
    }

    public static void Check_Player_hasCost(Player player, Inventory inventory, String inventorytype, String costtype) {
        int i = 0;
        if (Objects.equals(inventorytype, "IRON")) {
            for (ItemStack item : inventory.getContents()) {
                if (Objects.requireNonNull(item.getItemMeta()).getDisplayName().equals(ChatColor.AQUA + "") || item.getType().equals(Material.LIME_STAINED_GLASS_PANE)) {
                    i++;
                    continue;
                }
                item = new ItemStack (CustomEnchantsManager.removelore(item,costtype));
//                player.sendMessage("アイテム " + item.getItemMeta().getDisplayName() + ChatColor.AQUA + " コスト[" + CustomEnchantsManager.getItemCost(item, Survival_PvPvE.inst(), costtype) + "]");
                if (player.getInventory().contains(Material.IRON_INGOT, CustomEnchantsManager.getItemCost(item, Survival_PvPvE.inst(), costtype))) {
                    ItemMeta item_meta = item.getItemMeta();
//                    item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                    Objects.requireNonNull(item_meta).addEnchant(Enchantment.DURABILITY, 1, true);
                    Objects.requireNonNull(item_meta).addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    List<String> lores = item_meta.getLore();
                    Objects.requireNonNull(lores).add(ChatColor.AQUA + "" + ChatColor.BOLD + "購入可能");
                    item_meta.setLore(lores);
//                    player.sendMessage("買えるアイテムにエンチャントを付与しました" + ChatColor.GREEN + "スロット[" + i + "]" + "アイテム[" + item_meta.getDisplayName()+ "]");
                    item.setItemMeta(item_meta);
                }
                inventory.setItem(i, item);
                i++;
            }
        }
    }
}
