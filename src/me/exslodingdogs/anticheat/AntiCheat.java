package me.exslodingdogs.anticheat;

import io.netty.channel.*;
import me.exslodingdogs.anticheat.Checks.Check;
import me.exslodingdogs.anticheat.Checks.CheckResault;
import me.exslodingdogs.anticheat.Checks.Combat.KillAura.Average;
import me.exslodingdogs.anticheat.Checks.Movement.Flight_check;
import me.exslodingdogs.anticheat.Checks.Movement.NOFALL_check;
import me.exslodingdogs.anticheat.Checks.Movement.NOSLOW_check;
import me.exslodingdogs.anticheat.Checks.Movement.SPEED_check;
import me.exslodingdogs.anticheat.Checks.Player.REGEN_check;
import me.exslodingdogs.anticheat.Commands.Elsa_Command;
import net.minecraft.server.v1_8_R3.PacketPlayOutKeepAlive;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiCheat extends JavaPlugin implements Listener {
    static AntiCheat instance;
    @Override
    public void onEnable(){

        getCommand("elsa").setExecutor(new Elsa_Command());

        runTimedCheck();
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getPluginManager().registerEvents(new NOFALL_check(), this);
        Bukkit.getPluginManager().registerEvents(new SPEED_check(), this);
        Bukkit.getPluginManager().registerEvents(new NOSLOW_check(), this);
        Bukkit.getPluginManager().registerEvents(new REGEN_check(), this);
        Bukkit.getPluginManager().registerEvents(new me.exslodingdogs.anticheat.Checks.Combat.KillAura.Average(), this);
        Bukkit.getPluginManager().registerEvents(new Flight_check(), this);
    }

    public static AntiCheat getInstance(){
        return instance;
    }


    public void runTimedCheck(){
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(Player op : Bukkit.getOnlinePlayers()){
                    if (REGEN_check.timespersec.containsKey(op)) {
                        // op.sendMessage(REGEN_check.timespersec.get(op) + "");
                        REGEN_check.getInstance().result(op);
                        REGEN_check.timespersec.remove(op);
                    }

                }
            }
        }, 0,20);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(Player op : Bukkit.getOnlinePlayers()){
                    if(Average.cps.containsKey(op) && Average.hps.containsKey(op)){
                        Average.Check(op);
                        Average.cps.remove(op);
                        Average.hps.remove(op);
                    }

                }
            }
        }, 0,20*2);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        injectPlayer(event.getPlayer());
    }
    @EventHandler
    public void onleave(PlayerQuitEvent event){
       removePlayer(event.getPlayer());
    }

    private void removePlayer(Player player) {
        Channel channel = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel;
        channel.eventLoop().submit(() -> {
            channel.pipeline().remove(player.getName());
            return null;
        });
    }

    private void injectPlayer(Player player) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {

            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) throws Exception {
               // Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.YELLOW + "PACKET READ: " + ChatColor.RED + packet.toString());
                super.channelRead(channelHandlerContext, packet);
            }

            @Override
            public void write(ChannelHandlerContext channelHandlerContext, Object packet, ChannelPromise channelPromise) throws Exception {

                Bukkit.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + "PACKET WRITE: " + ChatColor.GREEN + packet.toString());

                super.write(channelHandlerContext, packet, channelPromise);
            }


        };

        ChannelPipeline pipeline = ((CraftPlayer) player).getHandle().playerConnection.networkManager.channel.pipeline();
        pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);

    }

}
