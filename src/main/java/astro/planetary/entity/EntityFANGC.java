package astro.planetary.entity;

import org.lwjgl.opengl.GL11;

import astro.planetary.Planetary;
import astro.planetary.client.model.ship.ModelFangC;
import astro.planetary.client.vector.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFANGC extends EntitySpaceShip {
    public EntityFANGC(World worldIn) {
        super(worldIn);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void render(float f1) {
        GlStateManager.pushMatrix();
        Vector3f diff = new Vector3f(posX - prevPosX, posY - prevPosY, posZ - prevPosZ);
        Vector3f newPos = new Vector3f(prevPosX + (diff.x*f1),prevPosY + (diff.y*f1),prevPosZ + (diff.z*f1));
        GlStateManager.translate(newPos.x, newPos.y, newPos.z);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Planetary.MOD_ID, "textures/models/spaceship/FANGC.png"));
        GlStateManager.translate(0, 0.5, -2);
        GlStateManager.scale(0.1, 0.1, 0.1);
        GlStateManager.rotate(180F, 1, 0, 0);
        float dYaw = (axes.getYaw() - prevAxes.getYaw());
        for(; dYaw > 180F; dYaw -= 360F) {}
        for(; dYaw <= -180F; dYaw += 360F) {}
        float dPitch = (axes.getPitch() - prevAxes.getPitch());
        for(; dPitch > 180F; dPitch -= 360F) {}
        for(; dPitch <= -180F; dPitch += 360F) {}
        float dRoll = (axes.getRoll() - prevAxes.getRoll());
        for(; dRoll > 180F; dRoll -= 360F) {}
        for(; dRoll <= -180F; dRoll += 360F) {}
        GL11.glRotatef(180F - prevAxes.getYaw() - dYaw * f1, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(prevAxes.getPitch() + dPitch * f1, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(prevAxes.getRoll() + dRoll * f1, 1.0F, 0.0F, 0.0F);
        ModelFangC.MODEL.render(this, 0, 0, 0, 0, 0, 1.0f);
        GlStateManager.popMatrix();
    }

	@Override
	public void onMouseMoved(int deltaX, int deltaY) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean pressKey(int key, EntityPlayer player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Entity getControllingEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}
}
