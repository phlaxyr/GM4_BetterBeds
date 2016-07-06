package co.gm4.GM4_BetterBeds;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBedLeaveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class BetterBeds extends JavaPlugin implements Listener {
		
	private static long WAKETIME;
	private static long SLEEPDELAY;
	private boolean cancelSleep = false;
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		WAKETIME = getConfig().getLong("wake-time");
		SLEEPDELAY = getConfig().getLong("sleep-delay");
	}
	
	@EventHandler
	public void enterBed(PlayerBedEnterEvent event) {
		getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
			public void run(){
				cancelSleep=false;
				World world = event.getBed().getWorld();
				while (cancelSleep=false && !event.isCancelled()) {
					world.setTime(WAKETIME);
					world.setStorm(false);
					world.setThundering(false);
				}				
			}
		}, SLEEPDELAY);

	}
	
	public void exitBed(PlayerBedLeaveEvent event) {
		cancelSleep = true;
	}

	
}
