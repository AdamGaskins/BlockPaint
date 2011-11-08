package me.entity.paint;

import me.entity.paint.Global;
import me.entity.paint.PaintBlockListener;
import me.entity.paint.PaintCommandExecutor;

import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.plugin.java.JavaPlugin;

public class Paint extends JavaPlugin
{
	Global global = new Global(this);
	
	PaintBlockListener blockListener = new PaintBlockListener(global);
	PaintPlayerListener playerListener = new PaintPlayerListener(global);
	
	PaintCommandExecutor paintExecutor = new PaintCommandExecutor(global);
	@Override
	public void onDisable() 
	{
		System.out.println(this + " is now disabled!");
	}

	@Override
	public void onEnable() 
	{
		getServer().getPluginManager().registerEvent(Type.BLOCK_PLACE, blockListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.BLOCK_BREAK, blockListener, Priority.Normal, this);
		getServer().getPluginManager().registerEvent(Type.PLAYER_INTERACT, playerListener, Priority.Normal, this);
		getCommand("paint").setExecutor(paintExecutor);
		getCommand("p").setExecutor(paintExecutor);
		System.out.println(this + " is now enabled!");
	}
}
