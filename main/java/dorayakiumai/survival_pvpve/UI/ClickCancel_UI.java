package dorayakiumai.survival_pvpve.UI;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import dorayakiumai.survival_pvpve.command.Inventory_Holder;
import dorayakiumai.survival_pvpve.customitem.CustomEnchantsManager;
import dorayakiumai.survival_pvpve.customitem.CustomItemsManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
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
            if (event.getView().getTitle().equals(ChatColor.GREEN + "" + ChatColor.BOLD + "UPGRADE")) {
                upgrade_UI(event,player);
            }
            else if (event.getView().getTitle().equals(ChatColor.AQUA + "SELECT_SHOP")) {
                select_shop_UI(event,player);
            }
            else if (event.getView().getTitle().equals(ChatColor.GRAY + "" + ChatColor.BOLD + "IRON_SHOP")) {
                IRON_shopUI(event,player);
            }
            else if (event.getView().getTitle().equals(ChatColor.GOLD + "" + ChatColor.BOLD + "GOLD_SHOP")) {
                GOLD_shopUI(event,player);
            }
            else if (event.getView().getTitle().equals(ChatColor.AQUA + "" + ChatColor.BOLD + "DIAMOND_SHOP")) {
                DIAMOND_shopUI(event,player);
            }
        }
    }

    public static void upgrade_UI(InventoryClickEvent event, Player player) {
        if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
            if (Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName().equals(Objects.requireNonNull(CustomItemsManager.getCustomItem("upgrade_ARROW_SPEED").getItemMeta()).getDisplayName())) {
                int needamount = 1 + CustomEnchantsManager.getCustomEnchantLevel(player.getInventory().getItemInOffHand(), Survival_PvPvE.inst(), "ARROW_SPEED");
                if (needamount == 11) {
                    player.sendMessage("レベルが最大のため強化出来ませんでした");
                    return;
                }
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
                if (needamount == 11) {
                    player.sendMessage("レベルが最大のため強化出来ませんでした");
                    return;
                }
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
                if (needamount == 11) {
                    player.sendMessage("レベルが最大のため強化出来ませんでした");
                    return;
                }
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

    public static void select_shop_UI(InventoryClickEvent event, Player player) {
        if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
            if (event.getCurrentItem().getType().equals(Material.IRON_INGOT)) {
                Open_UI.Open_IRON_ShopUI(player);
            }
            if (event.getCurrentItem().getType().equals(Material.GOLD_INGOT)) {
                Open_UI.Open_GOLD_ShopUI(player);
            }
            if (event.getCurrentItem().getType().equals(Material.DIAMOND)) {
                Open_UI.Open_DIAMOND_ShopUI(player);
            }
        }
    }
    public static void IRON_shopUI(InventoryClickEvent event, Player player) {
        if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()) {
            if (player.getInventory().getContents().length == 26) {
                player.sendMessage("インベントリに空きがないため買えませんでした");
                return;
            }
            if (CustomItemsManager.isMythicItem(event.getCurrentItem()) && event.getCurrentItem().getType().equals(Material.IRON_HELMET)) {
                int needamount = 50;
                if (player.getInventory().contains(Material.IRON_INGOT, needamount)) {
                    ItemStack[] contents = player.getInventory().getContents();
                    ItemStack[] return_contents = RemoveItems(new ItemStack(Material.IRON_INGOT), needamount, contents);
                    // インベントリを更新
                    player.getInventory().setContents(return_contents);
                    // インベントリを更新
                    player.getInventory().setContents(contents);
                    player.getInventory().addItem(CustomItemsManager.getCustomItem("armor_IRON_HELMET"));
                    player.sendMessage("購入しました:" + Objects.requireNonNull(CustomItemsManager.getCustomItem("armor_IRON_HELMET").getItemMeta()).getDisplayName());
                    player.closeInventory();
                }
                else {
                    player.sendMessage("アイテムがないため買えませんでした");
                }
            }
            if (CustomItemsManager.isMythicItem(event.getCurrentItem()) && event.getCurrentItem().getType().equals(Material.IRON_CHESTPLATE)) {
                int needamount = 80;
                if (player.getInventory().contains(Material.IRON_INGOT, needamount)) {
                    ItemStack[] contents = player.getInventory().getContents();
                    ItemStack[] return_contents = RemoveItems(new ItemStack(Material.IRON_INGOT), needamount, contents);
                    // インベントリを更新
                    player.getInventory().setContents(return_contents);
                    // インベントリを更新
                    player.getInventory().setContents(contents);
                    player.getInventory().addItem(CustomItemsManager.getCustomItem("armor_IRON_CHESTPLATE"));
                    player.sendMessage("購入しました:" + Objects.requireNonNull(CustomItemsManager.getCustomItem("armor_IRON_CHESTPLATE").getItemMeta()).getDisplayName());
                    player.closeInventory();
                }
                else {
                    player.sendMessage("アイテムがないため買えませんでした");
                }
            }
            if (CustomItemsManager.isMythicItem(event.getCurrentItem()) && event.getCurrentItem().getType().equals(Material.IRON_LEGGINGS)) {
                int needamount = 70;
                if (player.getInventory().contains(Material.IRON_INGOT, needamount)) {
                    ItemStack[] contents = player.getInventory().getContents();
                    ItemStack[] return_contents = RemoveItems(new ItemStack(Material.IRON_INGOT), needamount, contents);
                    // インベントリを更新
                    player.getInventory().setContents(return_contents);
                    // インベントリを更新
                    player.getInventory().setContents(contents);
                    player.getInventory().addItem(CustomItemsManager.getCustomItem("armor_IRON_LEGGINGS"));
                    player.sendMessage("購入しました:" + Objects.requireNonNull(CustomItemsManager.getCustomItem("armor_IRON_LEGGINGS").getItemMeta()).getDisplayName());
                    player.closeInventory();
                }
                else {
                    player.sendMessage("アイテムがないため買えませんでした");
                }
            }
            if (CustomItemsManager.isMythicItem(event.getCurrentItem()) && event.getCurrentItem().getType().equals(Material.IRON_BOOTS)) {
                int needamount = 40;
                if (player.getInventory().contains(Material.IRON_INGOT, needamount)) {
                    ItemStack[] contents = player.getInventory().getContents();
                    ItemStack[] return_contents = RemoveItems(new ItemStack(Material.IRON_INGOT), needamount, contents);
                    // インベントリを更新
                    player.getInventory().setContents(return_contents);
                    // インベントリを更新
                    player.getInventory().setContents(contents);
                    player.getInventory().addItem(CustomItemsManager.getCustomItem("armor_IRON_BOOTS"));
                    player.sendMessage("購入しました:" + Objects.requireNonNull(CustomItemsManager.getCustomItem("armor_IRON_BOOTS").getItemMeta()).getDisplayName());
                    player.closeInventory();
                }
                else {
                    player.sendMessage("アイテムがないため買えませんでした");
                }
            }
            if (CustomItemsManager.isMythicItem(event.getCurrentItem()) && event.getCurrentItem().getType().equals(Material.SPYGLASS)) {
                int needamount = 48;
                if (player.getInventory().contains(Material.IRON_INGOT, needamount)) {
                    ItemStack[] contents = player.getInventory().getContents();
                    ItemStack[] return_contents = RemoveItems(new ItemStack(Material.IRON_INGOT), needamount, contents);
                    // インベントリを更新
                    player.getInventory().setContents(return_contents);
                    // インベントリを更新
                    player.getInventory().setContents(contents);
                    player.getInventory().addItem(CustomItemsManager.getCustomItem("upgrade_ARROW_SPEED"));
                    player.sendMessage("購入しました:" + Objects.requireNonNull(CustomItemsManager.getCustomItem("upgrade_ARROW_SPEED").getItemMeta()).getDisplayName());
                    player.closeInventory();
                } else {
                    player.sendMessage("アイテムがないため買えませんでした");
                }
            }
            if (CustomItemsManager.isMythicItem(event.getCurrentItem()) && event.getCurrentItem().getType().equals(Material.TNT_MINECART)) {
                int needamount = 48;
                if (player.getInventory().contains(Material.IRON_INGOT, needamount)) {
                    ItemStack[] contents = player.getInventory().getContents();
                    ItemStack[] return_contents = RemoveItems(new ItemStack(Material.IRON_INGOT), needamount, contents);
                    // インベントリを更新
                    player.getInventory().setContents(return_contents);
                    // インベントリを更新
                    player.getInventory().setContents(contents);
                    player.getInventory().addItem(CustomItemsManager.getCustomItem("upgrade_DAMAGE"));
                    player.sendMessage("購入しました:" + Objects.requireNonNull(CustomItemsManager.getCustomItem("upgrade_DAMAGE").getItemMeta()).getDisplayName());
                    player.closeInventory();
                }
                else {
                    player.sendMessage("アイテムがないため買えませんでした");
                }
            }
            if (CustomItemsManager.isMythicItem(event.getCurrentItem()) && event.getCurrentItem().getType().equals(Material.FLINT_AND_STEEL)) {
                int needamount = 48;
                if (player.getInventory().contains(Material.IRON_INGOT, needamount)) {
                    ItemStack[] contents = player.getInventory().getContents();
                    ItemStack[] return_contents = RemoveItems(new ItemStack(Material.IRON_INGOT), needamount, contents);
                    // インベントリを更新
                    player.getInventory().setContents(return_contents);
                    // インベントリを更新
                    player.getInventory().setContents(contents);
                    player.getInventory().addItem(CustomItemsManager.getCustomItem("upgrade_SHOTTIME_SPEED"));
                    player.sendMessage("購入しました:" + Objects.requireNonNull(CustomItemsManager.getCustomItem("upgrade_SHOTTIME_SPEED").getItemMeta()).getDisplayName());
                    player.closeInventory();
                }
                else {
                    player.sendMessage("アイテムがないため買えませんでした");
                }
            }
            if (CustomItemsManager.isMythicItem(event.getCurrentItem()) && event.getCurrentItem().getType().equals(Material.TIPPED_ARROW)) {
                int needamount = 32;
                if (player.getInventory().contains(Material.IRON_INGOT, needamount)) {
                    String enchantDescription = "";
                    ItemStack itemStack = Objects.requireNonNull(event.getCurrentItem());
                    if (Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName().equals(Objects.requireNonNull(CustomItemsManager.getShopItem("arrow_INVISIBILITY").getItemMeta()).getDisplayName())) {
                        enchantDescription = "arrow_INVISIBILITY";
                    } else if (Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName().equals(Objects.requireNonNull(CustomItemsManager.getShopItem("arrow_HUNGER").getItemMeta()).getDisplayName())) {
                        enchantDescription = "arrow_HUNGER";
                    } else if (Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName().equals(Objects.requireNonNull(CustomItemsManager.getShopItem("arrow_DARKNESS").getItemMeta()).getDisplayName())) {
                        enchantDescription = "arrow_DARKNESS";
                    } else if (Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName().equals(Objects.requireNonNull(CustomItemsManager.getShopItem("arrow_SLOWNESS").getItemMeta()).getDisplayName())) {
                        enchantDescription = "arrow_SLOWNESS";
                    }
                    if (enchantDescription.equals("")) {
                        player.sendMessage("登録されていないアイテムだったため買えませんでした");
                        player.closeInventory();
                        return;
                    }
                    ItemStack[] contents = player.getInventory().getContents();
                    ItemStack[] return_contents = RemoveItems(new ItemStack(Material.IRON_INGOT), needamount, contents);
                    // インベントリを更新
                    player.getInventory().setContents(return_contents);
                    // インベントリを更新
                    player.getInventory().setContents(contents);
                    player.getInventory().addItem(CustomItemsManager.getCustomItem(enchantDescription));
                    player.sendMessage("購入しました:" + Objects.requireNonNull(event.getCurrentItem().getItemMeta()).getDisplayName());
                    player.closeInventory();
                }
                else {
                    player.sendMessage("アイテムがないため買えませんでした");
                }
            }
        }
    }

    public static void GOLD_shopUI(InventoryClickEvent event, Player player) {
        if (player.getInventory().getContents().length == 26) {
            player.sendMessage("インベントリに空きがないため買えませんでした");
            return;
        }
        if (Objects.requireNonNull(event.getCurrentItem()).getType().equals(Material.TOTEM_OF_UNDYING)) {
            int needamount = 24;
            if (player.getInventory().contains(Material.GOLD_INGOT, needamount)) {
                ItemStack[] contents = player.getInventory().getContents();
                ItemStack[] return_contents = RemoveItems(new ItemStack(Material.GOLD_INGOT), needamount, contents);
                // インベントリを更新
                player.getInventory().setContents(return_contents);
                // インベントリを更新
                player.getInventory().setContents(contents);
                player.getInventory().addItem(new ItemStack(Material.TOTEM_OF_UNDYING));
                player.sendMessage("購入しました:" + Objects.requireNonNull(new ItemStack(Material.TOTEM_OF_UNDYING).getItemMeta()).getDisplayName());
                player.closeInventory();
            }
            else {
                player.sendMessage("アイテムがないため買えませんでした");
            }
        }
    }

    public static void DIAMOND_shopUI(InventoryClickEvent event, Player player) {
        if (player.getInventory().getContents().length == 26) {
            player.sendMessage("インベントリに空きがないため買えませんでした");
            return;
        }
        if (Objects.requireNonNull(event.getCurrentItem()).getType().equals(Material.TOTEM_OF_UNDYING)) {
            int needamount = 4;
            if (player.getInventory().contains(Material.DIAMOND, needamount)) {
                ItemStack[] contents = player.getInventory().getContents();
                ItemStack[] return_contents = RemoveItems(new ItemStack(Material.DIAMOND), needamount, contents);
                // インベントリを更新
                player.getInventory().setContents(return_contents);
                // インベントリを更新
                player.getInventory().setContents(contents);
                player.getInventory().addItem(new ItemStack(Material.TOTEM_OF_UNDYING));
                player.sendMessage("購入しました:" + Objects.requireNonNull(new ItemStack(Material.TOTEM_OF_UNDYING).getItemMeta()).getDisplayName());
                player.closeInventory();
            }
            else {
                player.sendMessage("アイテムがないため買えませんでした");
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
