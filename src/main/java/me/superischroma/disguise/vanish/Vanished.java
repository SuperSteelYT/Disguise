package me.superischroma.disguise.vanish;

import java.io.File;
import me.superischroma.disguise.Disguise;
import org.bukkit.configuration.file.YamlConfiguration;

public class Vanished extends YamlConfiguration
{
    private static Vanished vanished;

    public static Vanished getConfig()
    {
        if (vanished == null)
        {
            vanished = new Vanished();
        }
        return vanished;
    }

    private Disguise plugin;
    private File configFile;

    public Vanished()
    {
        plugin = Disguise.getPlugin(Disguise.class);
        configFile = new File(plugin.getDataFolder(), "vanished.yml");
        saveDefault();
        reload();
    }

    public void reload()
    {
        try
        {
            super.load(configFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void save()
    {
        try
        {
            super.save(configFile);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void saveDefault()
    {
        plugin.saveResource("vanished.yml", false);
    }
}
