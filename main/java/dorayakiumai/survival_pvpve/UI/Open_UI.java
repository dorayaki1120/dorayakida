package dorayakiumai.survival_pvpve.UI;

import dorayakiumai.survival_pvpve.command.Inventory_Holder;
import dorayakiumai.survival_pvpve.customitem.CustomItemsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Open_UI extends JavaPlugin {
    public static void Open_UpgradeUI(Player player, ItemStack offitem) {
        if (offitem.getType() == Material.BOW) {
            //skilltreeというgui自体の詳細を書く
            Inventory upgrade = Bukkit.createInventory(new Inventory_Holder(), 54, ChatColor.GREEN + "" + ChatColor.BOLD + "強化画面");
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
            Inventory upgrade = Bukkit.createInventory(new Inventory_Holder(), 54, ChatColor.GREEN + "" + ChatColor.BOLD + "強化画面");
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
}
