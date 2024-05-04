package dorayakiumai.survival_pvpve.attribute;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class Custom_Attribute implements Listener {

    @EventHandler

    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        AttributeInstance atr = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (atr != null) {
            for (AttributeModifier modifier : atr.getModifiers()) {
                if (modifier.getName().equalsIgnoreCase("MAXhealth_boost")) {
                    atr.removeModifier(modifier);
                }
            }
        }
    }
}
