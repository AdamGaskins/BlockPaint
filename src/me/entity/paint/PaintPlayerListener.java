package me.entity.paint;

import org.bukkit.GameMode;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.inventory.ItemStack;

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
			if(Global.painters.contains(player.getName()))
			{
				int amount = 1; // creative
				if(player.getGameMode() == GameMode.SURVIVAL)
				{
					amount = 64;
				}
				Block block = player.getTargetBlock(null, 100);
				player.getInventory().setItemInHand(new ItemStack(block.getType(), amount, (short)0, block.getData()));
				event.setCancelled(true);
			}
		}
		else if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			Player player = event.getPlayer();
			if(Global.painters.contains(player.getName()))
			{			
				Block block = player.getTargetBlock(null, 100);
				ItemStack inHand = player.getInventory().getItemInHand();
				block.setType(inHand.getType());
				block.setData(inHand.getData().getData());
				event.setCancelled(true);
			}
		}
	}
}
