package me.entity.paint;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PaintCommandExecutor implements CommandExecutor
{
	public Global global;
	
	public PaintCommandExecutor(Global global)
	{
		this.global = global;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] args) 
	{
		Player player = null;
		if(sender instanceof Player)
		{
			 player = (Player) sender;
		}
		
		if(command.getName().equalsIgnoreCase("paint"))
		{
			if(args.length == 0)
			{
				if(player == null)
				{
					sender.sendMessage("This command can only be run by a player.");
				}
				else
				{
					togglePaintMode(sender, player.getName());
					return true;
				}
			}
			else
			{
				togglePaintMode(sender, args[0]);
			}
			
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private void togglePaintMode(CommandSender callback, String player)
	{
		if(global.Painters.contains(player))
		{
			global.Painters.remove(player);
			callback.sendMessage("Paint mode turned off.");
		}
		else
		{
			global.Painters.add(player);
			callback.sendMessage("Paint mode turned on.");
		}
	}
}
