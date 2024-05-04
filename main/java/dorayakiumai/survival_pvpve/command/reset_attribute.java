package dorayakiumai.survival_pvpve.command;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class reset_attribute implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            AttributeInstance atr = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
            if (atr == null) {
                player.sendMessage("なかみがないぜ(health)");
            }
            for (AttributeModifier modifier: Objects.requireNonNull(atr).getModifiers()) {
                if (modifier.getName().equalsIgnoreCase("MAXhealth_boost")) {
                    atr.removeModifier(modifier);
                    player.sendMessage("無事消せたよ!!!");
                }
            }
        }
        return true;
    }
}
