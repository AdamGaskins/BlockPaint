package me.entity.paint;

import java.lang.reflect.Method;
import java.util.Random;
 
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
 
public class BlockHelper {
	private static Random random = new Random();
 
	/**
	 * Get a ItemStack containing what the block drops.
	 * 
	 * @param block The block to get the drop of.
	 * @return The drop of the block or null if the block doesn't drop anything.
	 * @throws RuntimeException If a problem occurred while retrieving the drop.
	 */
	public static ItemStack getDrop(Block block, int count) throws RuntimeException {
		if (block == null) return null;
		int blockTypeId = block.getTypeId();
		if (blockTypeId < 1 || blockTypeId > 255) return null;
		try {
			net.minecraft.server.Block b = net.minecraft.server.Block.byId[blockTypeId];
 
			int typeId = b.a(blockTypeId, BlockHelper.random);
			if (typeId < 1) return null;
 
			int dropCount = b.a(BlockHelper.random);
			if (dropCount < 1) return null;
 
			Method m = BlockHelper.getMethod(b.getClass(), "a_", new Class[] {int.class});
			m.setAccessible(true);
			byte dropData = ((Integer)m.invoke(b, block.getData())).byteValue();
 
			return new ItemStack(typeId, count > 0 ? count : dropCount, dropData);
		} catch (Exception e) {
			throw new RuntimeException("A severe error occured while retreiving the data dropped.", e);
		}
	}
 
	/**
	 * Equivalent to java.lang.Class.getDeclaredMethod, except also searches through the <i>clazz</i>'s superclasses.
	 * 
	 * @param clazz The class to get the method from.
	 * @param methodName the name of the method
	 * @param parameters the parameter array
	 * @return the Method object for the method of this class matching the specified name and parameters
	 * @throws NoSuchMethodException if a matching method is not found
	 * @throws NullPointerException if methodName is null
	 */
	private static Method getMethod(Class<?> clazz, String methodName, Class<?>[] parameters) throws NoSuchMethodException, NullPointerException {
		if (methodName == null) throw new NullPointerException();
		if (clazz == null) throw new NoSuchMethodException();
		try {
			return clazz.getDeclaredMethod(methodName, parameters);
		} catch (Exception e) {
			return getMethod(clazz.getSuperclass(), methodName, parameters);
		}
	}
}
