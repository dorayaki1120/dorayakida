package dorayakiumai.survival_pvpve.arrow;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import java.util.List;
import java.util.Objects;

public class ArrowManager {
    public static PotionType CheckArrowPotion(ItemStack usearrow) {
            PotionMeta arrow_potionmeta = ((PotionMeta) usearrow.getItemMeta());
            return Objects.requireNonNull(arrow_potionmeta).getBasePotionType();
    }
    public static List<PotionEffect> CheckMMArrowPotion(ItemStack usearrow) {
        PotionMeta arrow_potionmeta = ((PotionMeta) usearrow.getItemMeta());
        return Objects.requireNonNull(arrow_potionmeta).getCustomEffects();
    }
}
