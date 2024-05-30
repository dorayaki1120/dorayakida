package dorayakiumai.survival_pvpve;

import dorayakiumai.survival_pvpve.UI.ClickCancel_UI;
import dorayakiumai.survival_pvpve.command.*;
import dorayakiumai.survival_pvpve.customitem.CustomItemsManager;
import dorayakiumai.survival_pvpve.customitem.CustomMobsManager;
import dorayakiumai.survival_pvpve.damagearea.damage_area;
import dorayakiumai.survival_pvpve.enchantment.event.Enchantments_event;
import dorayakiumai.survival_pvpve.game.PlayerEventTrack;
import dorayakiumai.survival_pvpve.loot.Track_LootBlock;
import dorayakiumai.survival_pvpve.scoreboard.Survival_PvPvE_scoreboard;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class Survival_PvPvE extends JavaPlugin {

    private static Survival_PvPvE pvpve;
    public Survival_PvPvE() {pvpve= this;}
    public static Survival_PvPvE inst() {return pvpve;}

    public Map<Player, BukkitRunnable> countdownTasks = new HashMap<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        CustomItemsManager.items_initialize();
        CustomMobsManager.mobs_initialize();
        Objects.requireNonNull(getCommand("reset_attribute")).setExecutor(new reset_attribute());
        Objects.requireNonNull(getCommand("start")).setExecutor(new start_PvPvE());
        Objects.requireNonNull(getCommand("end")).setExecutor(new end_PvPvE());
        Objects.requireNonNull(getCommand("lootblock")).setExecutor(new LootBlock());
        Objects.requireNonNull(getCommand("remove_lootblock")).setExecutor(new RemoveLootBlock());
        Objects.requireNonNull(getCommand("set_lootchest")).setExecutor(new Set_LootChest());
        Objects.requireNonNull(getCommand("custom_enchant")).setExecutor(new Custom_enchant());
        Objects.requireNonNull(getCommand("check_players")).setExecutor(new Check_Players());
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new Track_LootBlock(), this);
        pm.registerEvents(new Enchantments_event(), this);
        pm.registerEvents(new ClickCancel_UI(), this);
        pm.registerEvents(new PlayerEventTrack(), this);
        pm.registerEvents(new Survival_PvPvE_scoreboard(), this);
        reload();
    }

    @Override
    public void onDisable() {
        damage_area.remove_bossbar();
        reload();
    }

    public void reload() {
        saveConfig();
        saveDefaultConfig();
        reloadConfig();
    }
}
