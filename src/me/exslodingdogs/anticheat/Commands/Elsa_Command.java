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
                if(args.length == 0){
                    p.sendMessage("help message!");
                    return true;
                }
                if(args[0].equalsIgnoreCase("check")){
                    if(args.length >= 1){
                        p.sendMessage("/elsa check <name> <check>");
                        return true;
                    }

                    Player t = (Player) Bukkit.getServer().getPlayer(args[1]);
                    if(t== null){
                        p.sendMessage("Player not found!");
                        return true;
                    }
                    String check = args[2].toLowerCase();
                    if(check == null){
                        p.sendMessage("/elsa check <name> <check>");
                        return true;
                    }
                    if(check.equals("REGEN")){
                        t.damage(3);
                        return true;
                    }
                }
            }
        }else{
            sender.sendMessage("this command is not yet for the console!");
            return true;
        }
        return false;
    }

}
