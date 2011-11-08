package me.entity.paint;

import java.util.logging.Logger;

import me.entity.paint.Global;
import me.entity.paint.PaintBlockListener;
import me.entity.paint.PaintCommandExecutor;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

public class Paint extends JavaPlugin
{
	Global global = new Global(this);
	
	PermissionsHelper permissions = new PermissionsHelper(global);
	
	PaintBlockListener blockListener = new PaintBlockListener(global);
	PaintPlayerListener playerListener = new PaintPlayerListener(global);
	
	PaintCommandExecutor paintExecutor = new PaintCommandExecutor(global);
	
	@Override
	public void onDisable() 
	{
		Logger log = this.getServer().getLogger();
		log.info(this+" is now disabled!");
	}

	@Override
	public void onEnable() 
	{
		reloadConfig();
		FileConfiguration config = getConfig();
		if(!config.contains("permissions"))
		{
			config.set("permissions", "OP");
		}
		saveConfig();
		
		permissions.initialize();
		
		getServer().getPluginManager().registerEvent(Type.BLOCK_PLACE, blockListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.BLOCK_BREAK, blockListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
		getCommand("paint").setExecutor(paintExecutor);		
		
		Logger log = this.getServer().getLogger();
		log.info(this+" is now enabled!");
	}
}
