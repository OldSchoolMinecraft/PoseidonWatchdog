package com.oldschoolminecraft.pw;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

@SuppressWarnings("unused")
public class PoseidonWatchdog extends JavaPlugin
{
    // Basic Plugin Info
    private static PoseidonWatchdog plugin;
    private Logger log;
    private String pluginName;
    private PluginDescriptionFile pdf;
    // Plugin Specific
    private WatchDogThread watchdog;

    public void onEnable()
    {
        plugin = this;
        log = this.getServer().getLogger();
        pdf = this.getDescription();
        pluginName = pdf.getName();
        log.info("[" + pluginName + "] Is Loading, Version: " + pdf.getVersion());
        // Schedule watchdog creation for once the server starts ticking.
        // Prevents the watchdog from killing the server if plugins take too long to
        // load.
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, () ->
        {
            log.info("[" + pluginName + "] Starting Watchdog Thread");
            watchdog = new WatchDogThread(Thread.currentThread());
            watchdog.start();
            getServer().getScheduler().scheduleSyncRepeatingTask(this, () ->
            {
                watchdog.tickUpdate();
            }, 20, 20);
        }, 0L);
    }

    public void onDisable()
    {
        log.info("[" + pluginName + "] Disabling");
        if (watchdog != null)
        {
            log.info("[" + pluginName + "] Watchdog Thread Disabled");
            watchdog.interrupt();
        }
    }
}
