package me.exslodingdogs.anticheat.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.exslodingdogs.anticheat.Checks.CheckManager;

public class Elsa_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(args.length == 0 || args[0].equalsIgnoreCase("help")) {
	            if(p.hasPermission("elsa.help")){
	                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
	                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/elsa alerts &f- toggles alerts"));
	                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/elsa reload &f- reloads the config"));
	                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/elsa check <player> &f- Checks flags of a players"));
	                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/elsa report <player> &f- reports a player"));
	                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/elsa autoban <true/false> &f- toggles autoban"));
	                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
	                return true;
	            }else {
	            	p.sendMessage(ChatColor.RED + "You dont have permissions to do this command!");
	            	return true;
	            }
            }
            if(args[0].equalsIgnoreCase("alerts")) {
            	if(p.hasPermission("elsa.alerts")) {
            		if(!CheckManager.Enabled_Alerts.containsKey(p)) {
            			CheckManager.Enabled_Alerts.put(p, true);
            			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8(&6&lELSA&r&8) &7Alerts have been enabled!"));
            			return true;
            		}
            		if(CheckManager.Enabled_Alerts.get(p) == true) {
            			CheckManager.Enabled_Alerts.put(p, false);
            			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8(&6&lELSA&r&8) &7Alerts have been disabled!"));
            			return true;
            		}else {
            			CheckManager.Enabled_Alerts.put(p, true);
            			p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8(&6&lELSA&r&8) &7Alerts have been enabled!"));
            			return true;
            		}
            	}else {
            		p.sendMessage(ChatColor.RED + "You dont have permissions to do this command!");
                	return true;
            	}
            }
            
        }else{
            sender.sendMessage("this command is not yet for the console!");
            return true;
        }
		return false;
    }

}
