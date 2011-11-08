package me.entity.paint;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;

public class PaintPlayerListener extends PlayerListener
{
	private Global Global;
	public PaintPlayerListener(Global global)
	{
		this.Global = global;
	}
	
	@Override
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(event.getAction() == Action.LEFT_CLICK_BLOCK)
		{
			Player player = event.getPlayer();
			if(Global.Painters.contains(player.getName()))
			{
				int amount = 1; // creative
				if(player.getGameMode() == GameMode.SURVIVAL)
				{
					amount = 64;
				}
				Block block = player.getTargetBlock(null, 100);
				player.getInventory().setItemInHand(BlockHelper.getDrop(block, amount));
				event.setCancelled(true);
			}
		}
	}
}
