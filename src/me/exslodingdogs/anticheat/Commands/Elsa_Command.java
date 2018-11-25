package me.exslodingdogs.anticheat.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Elsa_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("elsa")){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/elsa alerts &f- toggles alerts"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/elsa reload &f- reloads the config"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/elsa check <player> &f- Checks flags of a players"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/elsa report <player> &f- reports a player"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7/elsa autoban <true/false> &f- toggles autoban"));
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));

            }
        }else{
            sender.sendMessage("this command is not yet for the console!");
            return true;
        }
        return false;
    }

}
