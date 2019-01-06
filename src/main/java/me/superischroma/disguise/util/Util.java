package me.superischroma.disguise.util;

import me.superischroma.disguise.Disguise;
import me.superischroma.disguise.nickname.NickManager;
import static me.superischroma.disguise.nickname.NickManager.isNicked;
import me.superischroma.disguise.nickname.Nicks;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Util 
{
    private static Nicks nicks = Nicks.getConfig();
    
    private static Disguise plugin = Disguise.getPlugin(Disguise.class);
    
    public static String colorize(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    public static String decolorize(String s)
    {
        return ChatColor.stripColor(s);
    }
    
    public static String getLoginMessage(Player player)
    {
        String login = plugin.getConfig().getString("server.login_format")
                .replace("%name%", NickManager.isNicked(player) ? Util.decolorize(nicks.getString(player.getName().toLowerCase() + ".nick")) : player.getName());
        login = colorize(login);
        return login;
    }
    
    public static String getLogoutMessage(Player player)
    {
        String logout = plugin.getConfig().getString("server.logout_format")
                .replace("%name%", NickManager.isNicked(player) ? Util.decolorize(nicks.getString(player.getName().toLowerCase() + ".nick")) : player.getName());
        logout = colorize(logout);
        return logout;
    }
    
    public static String getChatFormat(Player player, String message)
    {
        String format = plugin.getConfig().getString("server.chat_format")
                .replace("%msg%", message)
                .replace("%display%", NickManager.isNicked(player) ? nicks.getString(player.getName().toLowerCase() + ".nick") : player.getDisplayName());
        format = colorize(format);
        return format;
    }
}
