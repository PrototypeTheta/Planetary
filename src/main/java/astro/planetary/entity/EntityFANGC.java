package astro.planetary.entity;

import astro.planetary.Planetary;
import astro.planetary.client.model.ship.ModelFangC;
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
    public void render() {
        GlStateManager.pushMatrix();
        GlStateManager.translate(posX, posY, posZ);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Planetary.MOD_ID, "textures/models/spaceship/FANGC.png"));
        GlStateManager.translate(0, 0.5, -2);
        GlStateManager.scale(0.1, 0.1, 0.1);
        GlStateManager.rotate(180, 1, 0, 0);
        GlStateManager.rotate(-90, 0, 1, 0);
        GlStateManager.rotate(rotationYaw, 0, 1, 0);
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
