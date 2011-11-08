package me.entity.paint;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;

public class PermissionsHelper
{
	public Global global;
	public PermissionHandler permissions3;
	public PermissionsType type;
	
	public PermissionsHelper(Global global)
	{
		this.global = global;
	}
	
	public void initialize()
	{
		global.parent.reloadConfig();
		if(!global.parent.getConfig().contains("permissions") || global.parent.getConfig().getString("permissions").equalsIgnoreCase("permissions3"))
		{
			type = PermissionsType.PERMS3;
			setupPermissions();
		}
		else if(global.parent.getConfig().getString("permissions").equalsIgnoreCase("bukkit"))
		{
			type = PermissionsType.BUKKIT;
		}
		else
		{
			type = PermissionsType.OP;
		}		
	}
	
	public boolean hasPermission(Player player, String permission)
	{
		switch(type)
		{
			case PERMS3:
				return permissions3.has(player, permission);
			case BUKKIT:
				return player.hasPermission(permission);
			case OP:
			default:
				return player.isOp();
		}
	}
	
	private void setupPermissions() 
	{
		Logger log = global.parent.getServer().getLogger();
		
	    if (permissions3 != null) {
	        return;
	    }
	    
	    Plugin permissionsPlugin = global.parent.getServer().getPluginManager().getPlugin("Permissions");
	    
	    if (permissionsPlugin == null) {
	        log.log(Level.WARNING, "[BlockPaint] Permission system not detected, defaulting to OP");
	        return;
	    }
	    
	    permissions3 = ((Permissions) permissionsPlugin).getHandler();
	    log.info("Found and will use plugin "+((Permissions)permissionsPlugin).getDescription().getFullName());
	}		

}
