package dorayakiumai.survival_pvpve.customitem;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import io.lumine.mythic.bukkit.MythicBukkit;
import io.lumine.mythic.core.utils.jnbt.CompoundTag;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomItemsManager {
    private static final Map<String, ItemStack> customItems = new HashMap<>();
    private static final Map<String, ItemStack> shopItems = new HashMap<>();

    public static void items_initialize() {
        //
        //shopでもlootchestでも手に入るアイテムは別で保存する必要がある
        //
        //ARROW_SPEED
        ItemStack upgrade_ARROW_SPEED = MythicBukkit.inst().getItemManager().getItemStack("upgrade_ARROW_SPEED");
        customItems.put("upgrade_ARROW_SPEED", upgrade_ARROW_SPEED);
        //なんか干渉しちゃうから新しく作る
        ItemStack upgrade_ARROW_SPEED_shop = MythicBukkit.inst().getItemManager().getItemStack("upgrade_ARROW_SPEED");
        CustomEnchantsManager.setItemCost(upgrade_ARROW_SPEED_shop, Survival_PvPvE.inst(), "COST_IRON", 48);
        CustomEnchantsManager.setItemCost(upgrade_ARROW_SPEED_shop, Survival_PvPvE.inst(), "COST_GOLD", 24);
        CustomEnchantsManager.setItemCost(upgrade_ARROW_SPEED_shop, Survival_PvPvE.inst(), "COST_DIAMOND", 4);
        shopItems.put("upgrade_ARROW_SPEED", upgrade_ARROW_SPEED_shop);

        //DAMAGE
        ItemStack upgrade_DAMAGE = MythicBukkit.inst().getItemManager().getItemStack("upgrade_DAMAGE");
        customItems.put("upgrade_DAMAGE", upgrade_DAMAGE);
        //なんか干渉しちゃうから新しく作る
        ItemStack upgrade_DAMAGE_shop = MythicBukkit.inst().getItemManager().getItemStack("upgrade_DAMAGE");
        CustomEnchantsManager.setItemCost(upgrade_DAMAGE_shop, Survival_PvPvE.inst(),"COST_IRON", 48);
        CustomEnchantsManager.setItemCost(upgrade_DAMAGE_shop, Survival_PvPvE.inst(),"COST_GOLD", 24);
        CustomEnchantsManager.setItemCost(upgrade_DAMAGE_shop, Survival_PvPvE.inst(), "COST_DIAMOND", 4);
        shopItems.put("upgrade_DAMAGE", upgrade_DAMAGE_shop);

        //SHOTTIME_SPEED
        ItemStack upgrade_SHOTTIME_SPEED = MythicBukkit.inst().getItemManager().getItemStack("upgrade_SHOTTIME_SPEED");
        customItems.put("upgrade_SHOTTIME_SPEED", upgrade_SHOTTIME_SPEED);
        //なんか干渉しちゃうから新しく作る
        ItemStack upgrade_SHOTTIME_SPEED_shop = MythicBukkit.inst().getItemManager().getItemStack("upgrade_SHOTTIME_SPEED");
        CustomEnchantsManager.setItemCost(upgrade_SHOTTIME_SPEED_shop, Survival_PvPvE.inst(),"COST_IRON", 48);
        CustomEnchantsManager.setItemCost(upgrade_SHOTTIME_SPEED_shop, Survival_PvPvE.inst(),"COST_GOLD", 24);
        CustomEnchantsManager.setItemCost(upgrade_SHOTTIME_SPEED_shop, Survival_PvPvE.inst(), "COST_DIAMOND", 4);
        shopItems.put("upgrade_SHOTTIME_SPEED", upgrade_SHOTTIME_SPEED_shop);

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

        //shop選択用
        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        ItemMeta iron_meta = iron.getItemMeta();
        Objects.requireNonNull(iron_meta).setDisplayName(ChatColor.GRAY + "鉄で買えるショップ");
        iron.setItemMeta(iron_meta);
        customItems.put("iron", iron);

        ItemStack gold = new ItemStack(Material.GOLD_INGOT);
        ItemMeta gold_meta = gold.getItemMeta();
        Objects.requireNonNull(gold_meta).setDisplayName(ChatColor.GOLD + "金で買えるショップ");
        gold.setItemMeta(gold_meta);
        customItems.put("gold", gold);

        ItemStack diamond = new ItemStack(Material.DIAMOND);
        ItemMeta diamond_meta = diamond.getItemMeta();
        Objects.requireNonNull(diamond_meta).setDisplayName(ChatColor.AQUA + "ダイヤで買えるショップ");
        diamond.setItemMeta(diamond_meta);
        customItems.put("diamond", diamond);

        //gamebook
        ItemStack gamebook = MythicBukkit.inst().getItemManager().getItemStack("game_gamebook");
        customItems.put("gamebook", gamebook);

        //弓
        ItemStack bow = MythicBukkit.inst().getItemManager().getItemStack("weapon_bow");
        customItems.put("bow", bow);

        //鉄で買えるアイテム
        //鉄装備
        ItemStack armor_IRON_HELMET = MythicBukkit.inst().getItemManager().getItemStack("armor_IRON_HELMET");
        customItems.put("armor_IRON_HELMET", armor_IRON_HELMET);
        CustomEnchantsManager.setItemCost(armor_IRON_HELMET, Survival_PvPvE.inst(), "COST_IRON", 50);
        shopItems.put("armor_IRON_HELMET", armor_IRON_HELMET);

        ItemStack armor_IRON_CHESTPLATE = MythicBukkit.inst().getItemManager().getItemStack("armor_IRON_CHESTPLATE");
        customItems.put("armor_IRON_CHESTPLATE", armor_IRON_CHESTPLATE);
        CustomEnchantsManager.setItemCost(armor_IRON_CHESTPLATE, Survival_PvPvE.inst(), "COST_IRON", 80);
        shopItems.put("armor_IRON_CHESTPLATE", armor_IRON_CHESTPLATE);

        ItemStack armor_IRON_LEGGINGS = MythicBukkit.inst().getItemManager().getItemStack("armor_IRON_LEGGINGS");
        customItems.put("armor_IRON_LEGGINGS", armor_IRON_LEGGINGS);
        CustomEnchantsManager.setItemCost(armor_IRON_LEGGINGS, Survival_PvPvE.inst(), "COST_IRON", 70);
        shopItems.put("armor_IRON_LEGGINGS", armor_IRON_LEGGINGS);

        ItemStack armor_IRON_BOOTS = MythicBukkit.inst().getItemManager().getItemStack("armor_IRON_BOOTS");
        customItems.put("armor_IRON_BOOTS", armor_IRON_BOOTS);
        CustomEnchantsManager.setItemCost(armor_IRON_BOOTS, Survival_PvPvE.inst(), "COST_IRON", 40);
        shopItems.put("armor_IRON_BOOTS", armor_IRON_BOOTS);

        //矢
        ItemStack arrow_INVISIBILITY = MythicBukkit.inst().getItemManager().getItemStack("arrow_INVISIBILITY");
        customItems.put("arrow_INVISIBILITY", arrow_INVISIBILITY);

        ItemStack arrow_INVISIBILITY_shop = MythicBukkit.inst().getItemManager().getItemStack("arrow_INVISIBILITY");
        CustomEnchantsManager.setItemCost(arrow_INVISIBILITY_shop, Survival_PvPvE.inst(), "COST_IRON", 32);
        shopItems.put("arrow_INVISIBILITY", arrow_INVISIBILITY_shop);

        ItemStack arrow_HUNGER = MythicBukkit.inst().getItemManager().getItemStack("arrow_HUNGER");
        customItems.put("arrow_HUNGER", arrow_HUNGER);
        ItemStack arrow_HUNGER_shop = MythicBukkit.inst().getItemManager().getItemStack("arrow_HUNGER");
        CustomEnchantsManager.setItemCost(arrow_HUNGER_shop, Survival_PvPvE.inst(), "COST_IRON", 32);
        shopItems.put("arrow_HUNGER", arrow_HUNGER_shop);

        ItemStack arrow_DARKNESS = MythicBukkit.inst().getItemManager().getItemStack("arrow_DARKNESS");
        customItems.put("arrow_DARKNESS", arrow_DARKNESS);
        ItemStack arrow_DARKNESS_shop = MythicBukkit.inst().getItemManager().getItemStack("arrow_DARKNESS");
        CustomEnchantsManager.setItemCost(arrow_DARKNESS_shop, Survival_PvPvE.inst(), "COST_IRON", 32);
        shopItems.put("arrow_DARKNESS", arrow_DARKNESS_shop);

        ItemStack arrow_SLOWNESS = MythicBukkit.inst().getItemManager().getItemStack("arrow_SLOWNESS");
        customItems.put("arrow_SLOWNESS", arrow_SLOWNESS);
        ItemStack arrow_SLOWNESS_shop = MythicBukkit.inst().getItemManager().getItemStack("arrow_SLOWNESS");
        CustomEnchantsManager.setItemCost(arrow_SLOWNESS_shop, Survival_PvPvE.inst(), "COST_IRON", 32);
        shopItems.put("arrow_SLOWNESS", arrow_SLOWNESS_shop);

    }

    public static ItemStack getCustomItem(String key) {
        return customItems.get(key).clone();  // アイテムの複製を返すことで、元のアイテムが変更されるのを防ぐ
    }

    public static ItemStack getShopItem(String key) {
        return shopItems.get(key).clone();
    }

    public static boolean isMythicItem(ItemStack item) {
        CompoundTag tag = MythicBukkit.inst().getVolatileCodeHandler().getItemHandler().getNBTData(item);
        return tag != null && tag.containsKey("MYTHIC_TYPE");
    }

//    @Nullable
//    public static String getMythicID(ItemStack item) {
//        if (item == null) return null;
//        CompoundTag tag = MythicBukkit.inst().getVolatileCodeHandler().getItemHandler().getNBTData(item);
//        if (tag == null || !tag.containsKey("MYTHIC_TYPE")) return null;
//        return tag.getString("MYTHIC_TYPE");
//    }
}
