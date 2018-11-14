package me.exslodingdogs.anticheat.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Elsa_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lable, String[] args) {
        sender.sendMessage(ChatColor.RED + "This command is disabled!");
        return false;
    }

}
