package me.superischroma.disguise.nickname;

import java.io.File;
import me.superischroma.disguise.Disguise;
import org.bukkit.configuration.file.YamlConfiguration;

public class Nicks extends YamlConfiguration
{
    private static Nicks nicks;

    public static Nicks getConfig()
    {
        if (nicks == null)
        {
            nicks = new Nicks();
        }
        return nicks;
    }

    private Disguise plugin;
    private File configFile;

    public Nicks()
    {
        plugin = Disguise.getPlugin(Disguise.class);
        configFile = new File(plugin.getDataFolder(), "nicks.yml");
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
        plugin.saveResource("nicks.yml", false);
    }
}
