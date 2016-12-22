package astro.planetary.client.popup;

import astro.lib.tmt.ModelRendererTurbo;
import astro.planetary.ItemSpaceSuit;
import astro.planetary.Planetary;
import astro.planetary.Planets;
import astro.planetary.api.Popup;
import astro.planetary.client.model.ship.ModelFangB;
import astro.planetary.client.model.ship.ModelFangC;
import astro.planetary.client.popup.PopupBuilder;
import astro.planetary.client.popup.PopupHandler;
import astro.planetary.entity.EntitySpaceShip;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;

public enum OverlayHandler {
    INSTANCE;

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void overlayPre(RenderGameOverlayEvent.Pre event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            if (!PopupHandler.INSTANCE.toRender.isEmpty()) {
                GlStateManager.pushMatrix();
                Popup popup = PopupHandler.INSTANCE.toRender.get(0);
                ScaledResolution res = event.getResolution();
                final int width = res.getScaledWidth();
                final int height = res.getScaledHeight();
                final int scaleFactor = res.getScaleFactor();
                String text = popup.title;
                int textColor = new Color(0xE2E2E2).getRGB();
                FontRenderer fontRendererIn = Minecraft.getMinecraft().fontRendererObj;
                int bg = new Color(0x191919).getRGB();
                if (!popup.title.equals("")) {
                    int test = (popup.ticker * 60) / 50;
                    if (test > fontRendererIn.getStringWidth(text) / 2 + 5)
                        test = fontRendererIn.getStringWidth(text) / 2 + 5;
                    Gui.drawRect(width / 2 - test, height / 2 - 40, width / 2 + test, height / 2 - 25, bg);
                    GL11.glEnable(GL11.GL_SCISSOR_TEST);
                    GL11.glScissor((width / 2 - test) * scaleFactor, (height / 2 - 40) * scaleFactor, (test * 2) * scaleFactor, (height / 2 - 25) * scaleFactor);
                    fontRendererIn.drawString(text, (width / 2 - fontRendererIn.getStringWidth(text) / 2), height / 2 - 36,
                            textColor);
                    GL11.glDisable(GL11.GL_SCISSOR_TEST);
                }
                int test = (popup.ticker * 60) / 50;
                if (test > 90)
                    test = 90;
                text = popup.subTitle;

                Gui.drawRect(width / 2 - test, height / 2 - 20, width / 2 + test, height / 2 + 35, bg);
                GL11.glEnable(GL11.GL_SCISSOR_TEST);
                GL11.glScissor((width / 2 - test) * scaleFactor, (height / 2 - 40) * scaleFactor, (test * 2) * scaleFactor, (height / 2 - 25) * scaleFactor);
                fontRendererIn.drawString(text, (width / 2 - fontRendererIn.getStringWidth(text) / 2), (height / 2 - 14), textColor);
                Gui.drawRect(width / 2 - 60, height / 2 - 1, width / 2 + 60, height / 2 + 1, textColor);
                String[] array = popup.datas.split("\n");
                for (int i = 0; i < array.length; i++) {
                    text = array[i];
                    fontRendererIn.drawString(text, (width / 2 - fontRendererIn.getStringWidth(text) / 2), (height / 2 + 8 + ((fontRendererIn.FONT_HEIGHT + 2) * i)), textColor);
                }
                GL11.glDisable(GL11.GL_SCISSOR_TEST);
                if (popup.reverse && popup.ticker == 0)
                    PopupHandler.INSTANCE.toRender.remove(popup);
                else if (!popup.reverse && popup.ticker >= 300)
                    popup.reverse = true;
                if (!popup.reverse)
                    popup.ticker++;
                else
                    popup.ticker--;
                GlStateManager.popMatrix();
            }
        }

    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void keyPress(InputEvent.KeyInputEvent event) {
        if (Keyboard.isKeyDown(Keyboard.KEY_N)) {
            displayPlanetInfo(0, "The_CodedOne", true);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void overlayPost(RenderGameOverlayEvent.Post event) {
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void renderTick(TickEvent.RenderTickEvent event) {

    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void renderTick(RenderPlayerEvent.Pre event) {
        EntityPlayer player = event.getEntityPlayer();
        for (ItemStack armor : player.getArmorInventoryList()) {
            if (armor != null && armor.getItem() instanceof ItemSpaceSuit) {
                switch (((ItemSpaceSuit) armor.getItem()).getEquipmentSlot()) {
                    case LEGS:
                        event.getRenderer().getMainModel().bipedLeftLeg.isHidden = true;
                        event.getRenderer().getMainModel().bipedRightLeg.isHidden = true;
                        event.getRenderer().getMainModel().bipedLeftLegwear.isHidden = true;
                        event.getRenderer().getMainModel().bipedRightLegwear.isHidden = true;
                        break;
                    case CHEST:
                        event.getRenderer().getMainModel().bipedLeftArm.isHidden = true;
                        event.getRenderer().getMainModel().bipedRightArm.isHidden = true;
                        event.getRenderer().getMainModel().bipedLeftArmwear.isHidden = true;
                        event.getRenderer().getMainModel().bipedRightArmwear.isHidden = true;
                        break;
                }
            }
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void renderTick(RenderPlayerEvent.Post event) {
        EntityPlayer player = event.getEntityPlayer();

        for (ItemStack armor : player.getArmorInventoryList()) {
            if (armor != null && armor.getItem() instanceof ItemSpaceSuit) {
                switch (((ItemSpaceSuit) armor.getItem()).getEquipmentSlot()) {
                    case LEGS:
                        event.getRenderer().getMainModel().bipedLeftLeg.isHidden = false;
                        event.getRenderer().getMainModel().bipedRightLeg.isHidden = false;
                        event.getRenderer().getMainModel().bipedLeftLegwear.isHidden = false;
                        event.getRenderer().getMainModel().bipedRightLegwear.isHidden = false;
                        break;
                    case CHEST:
                        event.getRenderer().getMainModel().bipedLeftArm.isHidden = false;
                        event.getRenderer().getMainModel().bipedRightArm.isHidden = false;
                        event.getRenderer().getMainModel().bipedLeftArmwear.isHidden = false;
                        event.getRenderer().getMainModel().bipedRightArmwear.isHidden = false;
                        break;
                }
            }
        }
    }


    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void renderWorld(RenderWorldLastEvent event) {
        World world = Minecraft.getMinecraft().theWorld;
        if(world == null)
            return;
        Entity camera = Minecraft.getMinecraft().getRenderViewEntity();
        double x = camera.lastTickPosX + (camera.posX - camera.lastTickPosX) * event.getPartialTicks();
        double y = camera.lastTickPosY + (camera.posY - camera.lastTickPosY) * event.getPartialTicks();
        double z = camera.lastTickPosZ + (camera.posZ - camera.lastTickPosZ) * event.getPartialTicks();

        GL11.glPushMatrix();
        Minecraft.getMinecraft().entityRenderer.enableLightmap();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glDisable(GL11.GL_BLEND);
        RenderHelper.enableStandardItemLighting();
        GL11.glTranslatef(-(float)x, -(float)y, -(float)z);
        for(Entity entity : world.loadedEntityList) {
            if (entity instanceof EntitySpaceShip) {
            	EntitySpaceShip entityShip = (EntitySpaceShip)entity;
                ((EntitySpaceShip) entity).render(event.getPartialTicks());
            }
        }
        Minecraft.getMinecraft().entityRenderer.disableLightmap();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void renderBlockOverlay(RenderBlockOverlayEvent event) {

    }

    @SubscribeEvent
    public void entityJoinWorld(EntityJoinWorldEvent event) {
        if (!event.getWorld().isRemote) {

        }
    }

    @SideOnly(Side.CLIENT)
    public void displayPlanetInfo(int dim, String playerName, boolean newDiscovery) {
        PopupBuilder builder = new PopupBuilder();
        Planets planet = Planets.getFromID(dim);
        if (newDiscovery)
            builder.withTitle("New Planet Discovered");
        builder.withSubTitle(planet.getName());
        builder.withData(planet.buildData() + "\nDiscovered by " + TextFormatting.YELLOW + playerName);
        PopupHandler.INSTANCE.addToRenderQueue(builder.build());
    }
}