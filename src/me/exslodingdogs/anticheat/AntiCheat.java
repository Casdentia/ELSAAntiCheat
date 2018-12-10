package me.exslodingdogs.anticheat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.exslodingdogs.anticheat.Checks.CheckManager;
import me.exslodingdogs.anticheat.Commands.Elsa_Command;

public class AntiCheat extends JavaPlugin implements Listener {
    static AntiCheat instance;

    @Override
    public void onEnable() {

        getCommand("elsa").setExecutor(new Elsa_Command());
        Bukkit.getPluginManager().registerEvents(new CheckManager(), this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8(&6&lELSA&r&8) &7You are using Elsa v" + Bukkit.getServer().getPluginManager().getPlugin("ELSA").getDescription().getVersion()));
        for(Player op : Bukkit.getOnlinePlayers()) {
        	if(!op.hasPermission("elsa.alerts")) {
        		CheckManager.Enabled_Alerts.put(op, false);
        	}else {
        		CheckManager.Enabled_Alerts.put(op, true);
        	}
        }
    }
    public static AntiCheat getInstance(){
        return instance;
    }
    
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
    	Player Player = event.getPlayer();
    	if(!event.getPlayer().hasPermission("elsa.alerts")) {
    		CheckManager.Enabled_Alerts.put(Player, false);
    	}else {
    		CheckManager.Enabled_Alerts.put(Player, true);
    	}
    }
    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
    	Player Player = event.getPlayer();
    	if(!Player.hasPermission("elsa.alerts")) {
    		CheckManager.Enabled_Alerts.put(Player, false);
    	}else {
    		CheckManager.Enabled_Alerts.put(Player, true);
    	}
    }
}
