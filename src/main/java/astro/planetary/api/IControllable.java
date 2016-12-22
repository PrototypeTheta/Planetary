package astro.planetary.api;


import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public interface IControllable
{
	/**
	 * This is fired every tick.
	 * @param deltaX  change in X of the mouse.
	 * @param deltaY change in Y of the mouse.
	 */
	public void onMouseMoved(int deltaX, int deltaY);

	/**
	 * 
	 * @param key the keycode of the key. see @link:KeyInputHandler
	 * @return boolean to indicate it this key was handled.
	 */
	public boolean pressKey(int key, EntityPlayer player);

	public Entity getControllingEntity();
	
	public boolean isDead();
}