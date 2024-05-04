package dorayakiumai.survival_pvpve.command;

import dorayakiumai.survival_pvpve.loot.LootChest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Set_LootChest implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            player.sendMessage("LootChestを設置します");
            LootChest.SetLootChestInventory(player);
        }
        return true;
    }
}
