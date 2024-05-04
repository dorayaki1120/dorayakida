package dorayakiumai.survival_pvpve.UI;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import dorayakiumai.survival_pvpve.command.Inventory_Holder;
import dorayakiumai.survival_pvpve.customitem.CustomEnchantsManager;
import dorayakiumai.survival_pvpve.customitem.CustomItemsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ClickCancel_UI implements Listener {
    @EventHandler
    public void UI_click_cancel(InventoryClickEvent event) {
        if (event.getInventory().getHolder() instanceof Inventory_Holder) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
                if (Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName().equals(Objects.requireNonNull(CustomItemsManager.getCustomItem("upgrade_ARROW_SPEED").getItemMeta()).getDisplayName())) {
                        int needamount = 1 + CustomEnchantsManager.getCustomEnchantLevel(player.getInventory().getItemInOffHand(), Survival_PvPvE.inst(), "ARROW_SPEED");
                        if (player.getInventory().contains(CustomItemsManager.getCustomItem("upgrade_ARROW_SPEED"), needamount)) {

                            ItemStack[] contents = player.getInventory().getContents();
                            ItemStack[] return_contents = RemoveItems(CustomItemsManager.getCustomItem("upgrade_ARROW_SPEED"), needamount, contents);
                            // インベントリを更新
                            player.getInventory().setContents(return_contents);

                        player.closeInventory();
                        int uplevel = 1;
                        player.sendMessage("矢の速度上昇のエンチャントレベルを上げます");
                        CustomEnchantsManager.addCustomEnchantLevel(player.getInventory().getItemInOffHand(), Survival_PvPvE.inst(), "ARROW_SPEED", uplevel);
                    }
                    else {
                        player.sendMessage("アイテムがないため強化出来ませんでした");
                    }
                }
                if (Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName().equals(Objects.requireNonNull(CustomItemsManager.getCustomItem("upgrade_DAMAGE").getItemMeta()).getDisplayName())) {
                    int needamount = 1 + CustomEnchantsManager.getCustomEnchantLevel(player.getInventory().getItemInOffHand(), Survival_PvPvE.inst(), "DAMAGE");
                        if (player.getInventory().contains(CustomItemsManager.getCustomItem("upgrade_DAMAGE"), needamount)) {

                            ItemStack[] contents = player.getInventory().getContents();
                            ItemStack[] return_contents = RemoveItems(CustomItemsManager.getCustomItem("upgrade_DAMAGE"), needamount, contents);
                            // インベントリを更新
                            player.getInventory().setContents(return_contents);

                        player.closeInventory();
                        int uplevel = 1;
                        player.sendMessage("威力上昇のエンチャントレベルを上げます");
                        CustomEnchantsManager.addCustomEnchantLevel(player.getInventory().getItemInOffHand(), Survival_PvPvE.inst(), "DAMAGE", uplevel);
                    }
                    else {
                        player.sendMessage("アイテムがないため強化出来ませんでした");
                    }
                }
                if (Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName().equals(Objects.requireNonNull(CustomItemsManager.getCustomItem("upgrade_SHOTTIME_SPEED").getItemMeta()).getDisplayName())) {
                    int needamount = 1 + CustomEnchantsManager.getCustomEnchantLevel(player.getInventory().getItemInOffHand(), Survival_PvPvE.inst(), "SHOTTIME_SPEED");
                    if (player.getInventory().contains(CustomItemsManager.getCustomItem("upgrade_SHOTTIME_SPEED"), needamount)) {

                        ItemStack[] contents = player.getInventory().getContents();
                        ItemStack[] return_contents = RemoveItems(CustomItemsManager.getCustomItem("upgrade_SHOTTIME_SPEED"), needamount, contents);
                        // インベントリを更新
                        player.getInventory().setContents(return_contents);
                        // インベントリを更新
                        player.getInventory().setContents(contents);
                        player.closeInventory();
                        int uplevel = 1;
                        player.sendMessage("発射までの速度上昇のエンチャントレベルを上げます");
                        CustomEnchantsManager.addCustomEnchantLevel(player.getInventory().getItemInOffHand(), Survival_PvPvE.inst(), "SHOTTIME_SPEED", uplevel);
                    }
                    else {
                        player.sendMessage("アイテムがないため強化出来ませんでした");
                    }
                }
            }
        }
    }

    public static ItemStack[] RemoveItems(ItemStack removeitem, int removeamount, ItemStack[] contents) {
        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            if (item != null && item.getType() == removeitem.getType()) {
                int amountInSlot = item.getAmount();

                if (amountInSlot <= removeamount) {
                    // スロット内のアイテムをすべて削除
                    contents[i] = null;
                    removeamount -= amountInSlot;
                } else {
                    // スロット内のアイテムを一部削除
                    item.setAmount(amountInSlot - removeamount);
                    break; // 指定した数だけ削除したのでループを抜ける
                }
            }
        }
        return contents;
    }
}
