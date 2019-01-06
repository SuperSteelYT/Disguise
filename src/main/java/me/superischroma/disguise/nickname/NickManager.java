package me.superischroma.disguise.nickname;

import java.util.HashMap;
import java.util.Map;
import me.superischroma.disguise.Disguise;
import me.superischroma.disguise.util.Log;
import me.superischroma.disguise.util.Util;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class NickManager implements Listener
{
    Disguise plugin;

    public NickManager(Disguise instance)
    {
        this.plugin = instance;
    }
    
    private static Nicks nicks = Nicks.getConfig();
    
    private static Disguise jplugin = Disguise.getPlugin(Disguise.class);
    
    public static boolean isNicked(Player player)
    {
        return nicks.contains(player.getName().toLowerCase());
    }
    
    public static void nick(Player player, String nick)
    {
        String path = player.getName().toLowerCase();
        if (isNicked(player))
        {
            return;
        }
        nicks.set(path + ".name", player.getName());
        nicks.set(path + ".nick", nick);
        nicks.save();
        player.setPlayerListName(Util.decolorize(nick));
    }
    
    public static void unnick(Player player)
    {
        if (!isNicked(player))
        {
            return;
        }
        nicks.set(player.getName().toLowerCase(), null);
        nicks.save();
        player.setPlayerListName(player.getName());
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        Player player = e.getPlayer();
        String path = player.getName().toLowerCase();
        if (isNicked(player))
        {
            player.setPlayerListName(Util.decolorize(nicks.getString(path + ".nick")));
            Log.info("NOTICE: " + player.getName() + " is currently nicked as \"" + Util.decolorize(nicks.getString(path + ".nick")) + "\"");
        }
        String login = jplugin.getConfig().getString("server.login_format")
                .replace("%name%", isNicked(player) ? Util.decolorize(nicks.getString(path + ".nick")) : player.getName());
        login = Util.colorize(login);
        e.setJoinMessage(login);
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e)
    {
        Player player = e.getPlayer();
        String path = player.getName().toLowerCase();
        String logout = jplugin.getConfig().getString("server.logout_format")
                .replace("%name%", isNicked(player) ? Util.decolorize(nicks.getString(path + ".nick")) : player.getName());
        logout = Util.colorize(logout);
        e.setQuitMessage(logout);
    }
    
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e)
    {
        Player player = e.getPlayer();
        String path = player.getName().toLowerCase();
        String format = jplugin.getConfig().getString("server.chat_format")
                .replace("%msg%", e.getMessage())
                .replace("%display%", isNicked(player) ? nicks.getString(path + ".nick") : player.getDisplayName());
        format = Util.colorize(format);
        e.setFormat(format);
    }
}
