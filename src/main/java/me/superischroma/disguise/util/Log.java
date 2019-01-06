package me.superischroma.disguise.util;

import java.util.logging.Logger;

public class Log 
{
    private static final Logger log = Logger.getLogger("Minecraft");
    private static final String prefix = "[Disguise] ";
    public static void info(String l)
    {
        log.info(prefix + l);
    }
    public static void warning(String l)
    {
        log.warning(prefix + l);
    }
    public static void severe(String l)
    {
        log.severe(prefix + l);
    }
    public static void severe(Exception ex)
    {
        log.severe(prefix + ex.toString());
    }
}
