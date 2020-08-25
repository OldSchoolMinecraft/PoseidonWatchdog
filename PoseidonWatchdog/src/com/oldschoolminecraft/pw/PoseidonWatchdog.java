package com.oldschoolminecraft.pw;

import org.bukkit.plugin.java.JavaPlugin;

public class PoseidonWatchdog extends JavaPlugin
{
    private WatchDogThread watchdog;
    
    public void onEnable()
    {
        watchdog = new WatchDogThread(Thread.currentThread());
        
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable()
        {
            public void run() { watchdog.tickUpdate(); }
        }, 20, 20);
        
        System.out.println("PoseidonWatchdog enabled.");
    }
    
    public void onDisable()
    {
        System.out.println("PoseidonWatchdog disabled.");
    }
}
