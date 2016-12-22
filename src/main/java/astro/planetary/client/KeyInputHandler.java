package astro.planetary.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.input.Keyboard;

import astro.planetary.api.IControllable;


import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value = Side.CLIENT)
public class KeyInputHandler
{
	public static KeyBinding downKey = new KeyBinding("Down key", Keyboard.KEY_LCONTROL, "Planetary");
	public static KeyBinding inventoryKey = new KeyBinding("Inventory key", Keyboard.KEY_R, "Planetary");
	public static KeyBinding controlSwitchKey = new KeyBinding("Control Switch key", Keyboard.KEY_C, "Planetary");
	public static KeyBinding leftRollKey = new KeyBinding("Roll Left Key", Keyboard.KEY_Z, "Planetary");
	public static KeyBinding rightRollKey = new KeyBinding("Roll Right Key", Keyboard.KEY_X, "Planetary");
    public static KeyBinding gearKey = new KeyBinding("Gear Up / Down Key", Keyboard.KEY_L, "Planetary");


	Minecraft mc;

	public KeyInputHandler()
	{
		ClientRegistry.registerKeyBinding(downKey);
		ClientRegistry.registerKeyBinding(inventoryKey);
		ClientRegistry.registerKeyBinding(controlSwitchKey);
		ClientRegistry.registerKeyBinding(leftRollKey);
		ClientRegistry.registerKeyBinding(rightRollKey);
		ClientRegistry.registerKeyBinding(gearKey);
	}

	@SubscribeEvent
	public void onKeyInput(KeyInputEvent event)
	{
		if(FMLClientHandler.instance().isGUIOpen(GuiChat.class) || mc.currentScreen != null)
			return;

		EntityPlayer player = mc.thePlayer;
		Entity ridingEntity = player.getRidingEntity();

		//Handle driving keys
		if(ridingEntity instanceof IControllable)
		{
			IControllable riding = (IControllable)ridingEntity;
			if(mc.gameSettings.keyBindForward.isPressed())
				riding.pressKey(0, player);
			if(mc.gameSettings.keyBindBack.isPressed())
				riding.pressKey(1, player);
			if(mc.gameSettings.keyBindLeft.isPressed())
				riding.pressKey(2, player);
			if(mc.gameSettings.keyBindRight.isPressed())
				riding.pressKey(3, player);
			if(mc.gameSettings.keyBindJump.isPressed())
				riding.pressKey(4, player);
			if(downKey.isPressed())
				riding.pressKey(5, player);
			if(mc.gameSettings.keyBindSneak.isPressed())
				riding.pressKey(6, player);
			if(mc.gameSettings.keyBindInventory.isPressed() || inventoryKey.isPressed())
				riding.pressKey(7, player);
			if(controlSwitchKey.isPressed())
				riding.pressKey(8, player);
			if(leftRollKey.isPressed())
				riding.pressKey(9, player);
			if(rightRollKey.isPressed())
				riding.pressKey(10, player);
			if(gearKey.isPressed())
				riding.pressKey(11, player);
		}
	}
}
