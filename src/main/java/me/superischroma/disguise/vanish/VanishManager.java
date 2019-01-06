package me.superischroma.disguise.vanish;

import me.superischroma.disguise.Disguise;
import me.superischroma.disguise.util.Log;
import me.superischroma.disguise.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class VanishManager implements Listener
{
    Disguise plugin;

    public VanishManager(Disguise instance)
    {
        this.plugin = instance;
    }
    
    private static Disguise jplugin = Disguise.getPlugin(Disguise.class);
    
    private static Vanished vanished = Vanished.getConfig();
    
    public static boolean isVanished(Player player)
    {
        return vanished.contains(player.getName().toLowerCase());
    }
    
    public static void vanish(Player player)
    {
        String path = player.getName().toLowerCase();
        for (Player all : Bukkit.getOnlinePlayers())
        {
            all.hidePlayer(player);
        }
        Bukkit.broadcastMessage(Util.getLogoutMessage(player));
        vanished.set(path + ".name", player.getName());
        vanished.save();
        Log.info(player.getName() + " has vanished.");
    }
    
    public static void unvanish(Player player)
    {
        if (!isVanished(player))
        {
            return;
        }
        String path = player.getName().toLowerCase();
        for (Player all : Bukkit.getOnlinePlayers())
        {
            all.showPlayer(player);
        }
        Bukkit.broadcastMessage(Util.getLoginMessage(player));
        vanished.set(path, null);
        vanished.save();
        Log.info(player.getName() + " has unvanished.");
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        if (isVanished(player))
        {
            for (Player all : Bukkit.getOnlinePlayers())
            {
                all.hidePlayer(player);
            }
            e.setJoinMessage(null);
            player.sendMessage(ChatColor.GRAY + "You quietly logged on since you were in vanish!");
        }
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        Player player = e.getPlayer();
        if (isVanished(player))
        {
            e.setQuitMessage(null);
        }
    }
}
