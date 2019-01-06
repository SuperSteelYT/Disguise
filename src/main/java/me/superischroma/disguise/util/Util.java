package me.superischroma.disguise.util;

import org.bukkit.ChatColor;

public class Util 
{
    public static String colorize(String s)
    {
        return ChatColor.translateAlternateColorCodes('&', s);
    }
    
    public static String decolorize(String s)
    {
        return ChatColor.stripColor(s);
    }
}
