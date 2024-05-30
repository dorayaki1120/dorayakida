package dorayakiumai.survival_pvpve.loot;

import dorayakiumai.survival_pvpve.Survival_PvPvE;
import dorayakiumai.survival_pvpve.customitem.CustomItemsManager;
import io.lumine.mythic.bukkit.MythicBukkit;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Random;

public class LootChest extends JavaPlugin {

    public static void SetLootChestInventory(Player player) {
        LootChest_IRON(player);
        LootChest_GOLD(player);
        LootChest_DIAMOND(player);
    }

    public static void LootChest_IRON(Player player) {
        FileConfiguration config = Survival_PvPvE.inst().getConfig();
        List<String> locationstrings = config.getStringList("lootblocks_tier_iron");
        for (String locationstring : locationstrings) {
            //ここに座標をLocation型に変える処理を入れる
            String[] parts = locationstring.split(",");

            // 各部分を適切な型に変換
            if (parts.length == 4) {
                String worldName = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                int z = Integer.parseInt(parts[3]);

                // ワールドオブジェクトを取得
                World world = Bukkit.getWorld(worldName);
                if (world == null) {
                    continue;  // ワールドが見つからない場合はこの座標を無視
                }

                // Location オブジェクトを作成
                Location location = new Location(world, x, y, z);
                Location placelocation = location.clone().add(0, 1, 0);

                Block block = placelocation.getBlock();
                block.setType(Material.AIR);
                Random ran = new Random();
                int coun = ran.nextInt(100);
                if (coun <= 20) continue;
                block.setType(Material.CHEST);

                BlockState state = block.getState();

                if (state instanceof Chest chest) {
                    Inventory inv = chest.getInventory();
                    // ランダムにアイテムを選択してチェストに追加
                    for (int i = 0; i <= 26; i++) {
                        Random rand = new Random();
                        int count = rand.nextInt(100);
                        if (count == 0) {
                            inv.setItem(i,(new ItemStack(Material.DIAMOND)));
                        }
                        else if (count == 1) {
                            inv.setItem(i, CustomItemsManager.getCustomItem("upgrade_ARROW_SPEED"));
                        }
                        else if (count == 2) {
                            inv.setItem(i,CustomItemsManager.getCustomItem("upgrade_DAMAGE"));
                        }
                        else if (count == 3) {
                            //元々スタックするアイテムで、MM側から一回取り出してコピーだとスタックしちゃうからこれだけは毎回取得
                            //どうしてもうまくいかなかったので火打石に変えて実装、前は鎖だった
                            inv.setItem(i,CustomItemsManager.getCustomItem("upgrade_SHOTTIME_SPEED"));
                        }
                        else if (count <= 5) {
                            inv.setItem(i,(new ItemStack(Material.CROSSBOW)));
                        }
                        else if (count <= 10) {
                            inv.setItem(i,CustomItemsManager.getCustomItem("bow"));
                        }
                        else if (count <= 20) {
                            inv.setItem(i,(new ItemStack(Material.IRON_INGOT, 1)));
                        }
                        else if (count <= 40) {
                            inv.setItem(i,(new ItemStack(Material.ARROW, 2)));
                        }
                        else if (count <= 50) {
                            inv.setItem(i,(new ItemStack(Material.COOKED_BEEF, 1)));
                        }
                    }
                } else {
                    player.sendMessage("IRONだめでした");
                    return;
                }
            }
        }
    }

    public static void LootChest_GOLD(Player player) {
        FileConfiguration config = Survival_PvPvE.inst().getConfig();
        List<String> locationstrings = config.getStringList("lootblocks_tier_gold");
        for (String locationstring : locationstrings) {
            //ここに座標をLocation型に変える処理を入れる
            String[] parts = locationstring.split(",");

            // 各部分を適切な型に変換
            if (parts.length == 4) {
                String worldName = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                int z = Integer.parseInt(parts[3]);

                // ワールドオブジェクトを取得
                World world = Bukkit.getWorld(worldName);
                if (world == null) {
                    continue;  // ワールドが見つからない場合はこの座標を無視
                }

                // Location オブジェクトを作成
                Location location = new Location(world, x, y, z);
                Location placelocation = location.clone().add(0, 1, 0);

                Block block = placelocation.getBlock();
                block.setType(Material.AIR);
                Random ran = new Random();
                int coun = ran.nextInt(100);
                if (coun <= 20) continue;
                block.setType(Material.CHEST);

                BlockState state = block.getState();

                if (state instanceof Chest chest) {
                    Inventory inv = chest.getInventory();
                    // ランダムにアイテムを選択してチェストに追加
                    for (int i = 0; i <= 26; i++) {
                        Random rand = new Random();
                        int count = rand.nextInt(100);
                        if (count <= 1) {
                            inv.setItem(i,(new ItemStack(Material.DIAMOND, 1)));
                        }
                        else if (count == 2) {
                            inv.setItem(i, CustomItemsManager.getCustomItem("upgrade_ARROW_SPEED"));
                        }
                        else if (count == 3) {
                            inv.setItem(i,CustomItemsManager.getCustomItem("upgrade_DAMAGE"));
                        }
                        else if (count == 4) {
                            //元々スタックするアイテムで、MM側から一回取り出してコピーだとスタックしちゃうからこれだけは毎回取得
                            inv.setItem(i,CustomItemsManager.getCustomItem("upgrade_SHOTTIME_SPEED"));
                        }
                        else if (count == 5) {
                            inv.setItem(i,(new ItemStack(Material.CROSSBOW)));
                        }
                        else if (count <= 10) {
                            inv.setItem(i,CustomItemsManager.getCustomItem("bow"));
                        }
                        else if (count <= 15) {
                            inv.setItem(i,(new ItemStack(Material.GOLD_INGOT, 1)));
                        }
                        else if (count <= 33) {
                            inv.setItem(i,(new ItemStack(Material.IRON_INGOT, 1)));
                        }
                        else if (count <= 50) {
                            inv.setItem(i,(new ItemStack(Material.COOKED_BEEF, 1)));
                        }
                        else if (count <= 60) {
                            inv.setItem(i,(new ItemStack(Material.ARROW, 4)));
                        }
                    }
                } else {
                    player.sendMessage("GOLDだめでした");
                    return;
                }
            }
        }
    }

    public static void LootChest_DIAMOND(Player player) {
        FileConfiguration config = Survival_PvPvE.inst().getConfig();
        List<String> locationstrings = config.getStringList("lootblocks_tier_diamond");
        for (String locationstring : locationstrings) {
            //ここに座標をLocation型に変える処理を入れる
            String[] parts = locationstring.split(",");
            // 各部分を適切な型に変換
            if (parts.length == 4) {
                String worldName = parts[0];
                int x = Integer.parseInt(parts[1]);
                int y = Integer.parseInt(parts[2]);
                int z = Integer.parseInt(parts[3]);

                // ワールドオブジェクトを取得
                World world = Bukkit.getWorld(worldName);
                if (world == null) {
                    continue;  // ワールドが見つからない場合はこの座標を無視
                }

                // Location オブジェクトを作成
                Location location = new Location(world, x, y, z);
                Location placelocation = location.clone().add(0, 1, 0);

                Block block = placelocation.getBlock();
                block.setType(Material.AIR);
                Random ran = new Random();
                int coun = ran.nextInt(100);
                if (coun <= 50) continue;
                block.setType(Material.CHEST);

                BlockState state = block.getState();

                if (state instanceof Chest chest) {
                    Inventory inv = chest.getInventory();
                    // ランダムにアイテムを選択してチェストに追加
                    for (int i = 0; i <= 26; i++) {
                        Random rand = new Random();
                        int count = rand.nextInt(100);
                        if (count <= 2) {
                            inv.setItem(i,(new ItemStack(Material.DIAMOND, 1)));
                        }
                        else if (count == 3) {
                            inv.setItem(i,(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1)));
                        }
                        else if (count <= 5) {
                            inv.setItem(i, CustomItemsManager.getCustomItem("upgrade_ARROW_SPEED"));
                        }
                        else if (count <= 7) {
                            inv.setItem(i,CustomItemsManager.getCustomItem("upgrade_DAMAGE"));
                        }
                        else if (count <= 9) {
                            //元々スタックするアイテムで、MM側から一回取り出してコピーだとスタックしちゃうからこれだけは毎回取得
                            inv.setItem(i,CustomItemsManager.getCustomItem("upgrade_SHOTTIME_SPEED"));
                        }
                        else if (count <= 20) {
                            inv.setItem(i,(new ItemStack(Material.GOLD_INGOT, 1)));
                        }
                        else if (count <= 57) {
                            inv.setItem(i,(new ItemStack(Material.IRON_INGOT, 1)));
                        }
                        else if (count <= 80) {
                            inv.setItem(i,(new ItemStack(Material.COOKED_BEEF, 4)));
                        }
                        else {
                            inv.setItem(i,(new ItemStack(Material.ARROW, 8)));
                        }
                    }
                } else {
                    player.sendMessage("DIAMONDだめでした");
                    return;
                }
            }
        }
    }
}
