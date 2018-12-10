package me.exslodingdogs.anticheat.Checks;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import net.md_5.bungee.api.ChatColor;

public class CheckManager implements Listener{
	
	public static HashMap<Player , Boolean> Enabled_Alerts = new HashMap<>();
			
	
	@EventHandler
	public void onmove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Location from = event.getFrom();
		Location to = event.getTo();
		double speed = from.toVector().distance(to.toVector());
		double jumphight = to.getY()-from.getY();
		//player.sendMessage(ChatColor.GREEN + "SPEED : " + speed + " jumphight : " + jumphight);
		player.sendMessage(ChatColor.GREEN + "PITCH : " + player.getEyeLocation().getPitch());
		//FLY Checks
		if(me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeA.runCheck(player, speed, jumphight) == CheckResult.FAIL) {
			flag(player, "&eFLY(TypeA) &8[&cPosiblity: CERTAIN&8]");
		}
		if(me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeB.runCheck(player, speed, jumphight) == CheckResult.FAIL) {
			flag(player, "&eFLY(TypeB)  &8[&cPosiblity: CERTAIN&8]");
		}
		if(me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeC.runCheck(player, from, to, speed, jumphight) == CheckResult.FAIL) {
			flag(player, "&eFLY(TypeC) &8[&cPosiblity: POSSIBLE&8]");
		}
		if(me.exslodingdogs.anticheat.Checks.Movement.FLY.TypeE.runCheck(player, speed, jumphight) == CheckResult.FAIL) {
			flag(player, "&eFLY(TypeE) &8[&cPosiblity: CERTAIN&8]");
		}
		
		//SPEED
		if(me.exslodingdogs.anticheat.Checks.Movement.SPEED.TypeA.runCheck(player, speed, jumphight) == CheckResult.FAIL && !player.isFlying()) {
			flag(player, "&eSPEED(TypeA) &8[&cPosiblity: CERTAIN&8]");
		}
		if(me.exslodingdogs.anticheat.Checks.Movement.SPEED.TypeB.runcheck(speed, jumphight)== CheckResult.FAIL && !player.isFlying()) {
			flag(player, "&eSPEED(TypeB) &8[&cPosiblity: POSSIBLE&8]");
		}
		if(me.exslodingdogs.anticheat.Checks.Movement.SPEED.TypeC.runcheck(speed, jumphight) == CheckResult.FAIL && !player.isFlying()) {
			flag(player, "&eSPEED(TypeC) &8[&cPosiblity: POSSIBLE&8]");
		}
		if(me.exslodingdogs.anticheat.Checks.Movement.SPEED.TypeD.runCheck(speed, jumphight) == CheckResult.FAIL && !player.isFlying()) {
			flag(player, "&eSPEED(TypeD) &8[&cPosiblity: CERTAIN&8]");
		}
		if(me.exslodingdogs.anticheat.Checks.Movement.SPEED.TypeE.runCheck(speed, jumphight) == CheckResult.FAIL && !player.isFlying()) {
			flag(player, "&eSPEED(TypeE) &8[&cPosiblity: CERTAIN&8]");
		}
		if(me.exslodingdogs.anticheat.Checks.Movement.SPEED.TypeF.runCheck(speed, jumphight) == CheckResult.FAIL && !player.isFlying()) {
			flag(player, "&eSPEED(TypeF) &8[&cPosiblity: CERTAIN&8]");
		}
		//NOFALL 
		if(me.exslodingdogs.anticheat.Checks.Movement.NOFALL.TypeA.runcheck(player, from, to, speed) == CheckResult.FAIL) {
			flag(player, "&eNOFALL(TypeA) &8[&cPosiblity: CERTAIN&8]");
			player.damage(5);
		}
	}
	
	@EventHandler
	public void onhit(EntityDamageByEntityEvent event) {
		Player player = (Player) event.getDamager();
		Entity entity = event.getEntity();
		double distance = player.getLocation().toVector().distance(entity.getLocation().toVector());
		
		if(me.exslodingdogs.anticheat.Checks.Combat.REACH.TypeA.runCheck(player, distance) == CheckResult.FAIL) {
			flag(player, "&eREACH(TypeA) &8[&cPosiblity: CERTAIN&8]");
		}
		if(me.exslodingdogs.anticheat.Checks.Combat.REACH.TypeB.runCheck(player, entity) == CheckResult.FAIL) {
			flag(player, "&eREACH(TypeB) &8[&cPosiblity: CERTAIN&8]");
		}
		
	}
	
	@EventHandler
	public void onblockplace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		
		if(me.exslodingdogs.anticheat.Checks.block.FastPlaceCheck.runCheck(player) == CheckResult.FAIL) {
			flag(player, "&eFASTPLACE(TypeA) &8[&cPosiblity: POSSIBLE&8]");
		}
	}
	
	public void flag(Player player, String message) {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8(&6&lELSA&r&8) &c" + player.getName() + " &7failed " + message));
		for(Player op : Bukkit.getOnlinePlayers()) {
			if(op.hasPermission("elsa.alerts")) {
				if(Enabled_Alerts.get(op) == true) {
					op.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8(&6&lELSA&r&8) &c" + player.getName() + " &7failed " + message));
				}
			}
		}
	}
	
	
}
