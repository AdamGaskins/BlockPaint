package me.entity.paint;

import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PaintBlockListener extends BlockListener
{
	public Global Global;
	public PaintBlockListener(Global global)
	{
		this.Global = global;
	}
	
	@Override
	public void onBlockPlace(BlockPlaceEvent event) 
	{
		if(Global.painters.contains(event.getPlayer().getName()))
		{
//			Block against = event.getBlockAgainst();
//			Block placed = event.getBlockPlaced();
//			against.setType(placed.getType());
//			against.setData(placed.getData());
			event.setCancelled(true);
		}
	}

	@Override
	public void onBlockBreak(BlockBreakEvent event)
	{
		Player player = event.getPlayer();
		if(Global.painters.contains(player.getName()))
		{
//			int amount = 1; // creative
//			if(player.getGameMode() == GameMode.SURVIVAL)
//			{
//				amount = 64;
//			}
//			player.getInventory().setItemInHand(BlockHelper.getDrop(event.getBlock(), amount));
			event.setCancelled(true);
		}
	}	
}
