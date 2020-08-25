package com.oldschoolminecraft.pw;

import org.bukkit.plugin.java.JavaPlugin;

public class PoseidonWatchdog extends JavaPlugin
{
    public void onEnable()
    {
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new WatchDogThread(Thread.currentThread()), 20, 20);
        
        System.out.println("PoseidonWatchdog enabled.");
    }
    
    public void onDisable()
    {
        System.out.println("PoseidonWatchdog disabled.");
    }
}
