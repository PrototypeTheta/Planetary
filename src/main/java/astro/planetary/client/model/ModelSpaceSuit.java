package astro.planetary.client.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * ModelSpaceSuit - wiiv
 * Created using Tabula 4.1.1
 */
@SideOnly(Side.CLIENT)
public class ModelSpaceSuit extends ModelBiped {

    public static ModelSpaceSuit FEET = new ModelSpaceSuit(EntityEquipmentSlot.FEET);
    public static ModelSpaceSuit CHEST = new ModelSpaceSuit(EntityEquipmentSlot.CHEST);
    public static ModelSpaceSuit HEAD = new ModelSpaceSuit(EntityEquipmentSlot.HEAD);
    public static ModelSpaceSuit LEGS = new ModelSpaceSuit(EntityEquipmentSlot.LEGS);

    public ModelRenderer body;
    public ModelRenderer armR;
    public ModelRenderer armL;
    public ModelRenderer belt;
    public ModelRenderer bootR;
    public ModelRenderer bootL;
    public ModelRenderer body_1;
    public ModelRenderer body_2;
    public ModelRenderer body_3;
    public ModelRenderer body_4;
    public ModelRenderer body_5;
    public ModelRenderer helm;
    public ModelRenderer armr_1;
    public ModelRenderer armr_2;
    public ModelRenderer legL;
    public ModelRenderer legR;
    public ModelRenderer helm_1;
    public ModelRenderer helm_2;
    public EntityEquipmentSlot slot;

    public ModelSpaceSuit(EntityEquipmentSlot slot) {
        this.textureWidth = 128;
        this.textureHeight = 128;
        this.slot=slot;
        this.armr_2 = new ModelRenderer(this, 40, 53);
        this.armr_2.mirror = true;
        this.armr_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.armr_2.addBox(-1.0F, 6.0F, -2.0F, 4, 4, 4, 0.0F);
        this.body_1 = new ModelRenderer(this, 30, 38);
        this.body_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body_1.addBox(-2.5F, 1.5F, -5.0F, 5, 6, 2, 0.0F);
        this.armr_1 = new ModelRenderer(this, 40, 53);
        this.armr_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.armr_1.addBox(-3.0F, 6.0F, -2.0F, 4, 4, 4, 0.0F);
        this.body_4 = new ModelRenderer(this, 64, 30);
        this.body_4.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body_4.addBox(-7.0F, -8.5F, 0.0F, 2, 2, 6, 0.0F);
        this.setRotateAngle(body_4, 0.08726646259971647F, 0.0F, 0.0F);
        this.body_3 = new ModelRenderer(this, 64, 18);
        this.body_3.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body_3.addBox(-6.5F, -6.5F, 2.0F, 13, 6, 6, 0.0F);
        this.setRotateAngle(body_3, 0.08726646259971647F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 38);
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.5F, 0.0F, -3.0F, 9, 9, 6, 0.0F);
        this.body_5 = new ModelRenderer(this, 80, 30);
        this.body_5.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body_5.addBox(5.0F, -8.5F, 0.0F, 2, 2, 6, 0.0F);
        this.setRotateAngle(body_5, 0.08726646259971647F, 0.0F, 0.0F);
        this.armL = new ModelRenderer(this, 20, 53);
        this.armL.setRotationPoint(4.0F, 2.0F, -0.0F);
        this.armL.addBox(-1.0F, -2.0F, -2.5F, 5, 8, 5, 0.0F);
        this.setRotateAngle(armL, 0.0F, 0.0F, -0.17453292519943295F);
        this.armR = new ModelRenderer(this, 0, 53);
        this.armR.setRotationPoint(-4.0F, 2.0F, -0.0F);
        this.armR.addBox(-4.0F, -2.0F, -2.5F, 5, 8, 5, 0.0F);
        this.setRotateAngle(armR, 0.0F, 0.0F, 0.17453292519943295F);
        this.body_2 = new ModelRenderer(this, 64, 0);
        this.body_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.body_2.addBox(-6.5F, -0.5F, 2.0F, 13, 12, 6, 0.0F);
        this.belt = new ModelRenderer(this, 0, 66);
        this.belt.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.belt.addBox(-4.5F, 9.0F, -3.0F, 9, 5, 6, 0.0F);
        this.bootR = new ModelRenderer(this, 0, 91);
        this.bootR.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.bootR.addBox(-2.5F, 8.0F, -3.0F, 4, 4, 5, 0.0F);
        this.setRotateAngle(bootR, 0.0F, 0.0F, 0.0F);
        this.bootL = new ModelRenderer(this, 0, 91);
        this.bootL.mirror = true;
        this.bootL.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.bootL.addBox(-1.5F, 8.0F, -3.0F, 4, 4, 5, 0.0F);
        this.setRotateAngle(bootL, 0.0F, 0.0F, 0.0F);
        this.legL = new ModelRenderer(this, 0, 77);
        this.legL.mirror = true;
        this.legL.setRotationPoint(1.9F, 12.0F, 0.0F);
        this.legL.addBox(-1.5F, -1.0F, -2.55F, 4, 9, 5, 0.0F);
        this.setRotateAngle(legL, 0.0F, 0.0F, 0.0F);
        this.legR = new ModelRenderer(this, 0, 77);
        this.legR.setRotationPoint(-1.9F, 12.0F, 0.0F);
        this.legR.addBox(-2.5F, -1.0F, -2.55F, 4, 9, 5, 0.0F);
        this.setRotateAngle(legR, 0.0F, 0.0F, 0.0F);
        this.helm_2 = new ModelRenderer(this, 0, 0);
        this.helm_2.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helm_2.addBox(-5.0F, -9.5F, -5.0F, 10, 10, 10, 0.0F);
        this.helm = new ModelRenderer(this, 0, 112);
        this.helm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helm.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
        this.setRotateAngle(helm, 0.08726646259971647F, 0.0F, 0.0F);
        this.helm_1 = new ModelRenderer(this, 0, 20);
        this.helm_1.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.helm_1.addBox(-4.5F, -9.0F, -4.5F, 9, 9, 9, 0.0F);
        this.armL.addChild(this.armr_2);
        this.helm_1.addChild(this.helm_2);
        this.armR.addChild(this.armr_1);
        this.helm.addChild(this.helm_1);
        this.body.addChild(this.body_1);
        this.body.addChild(this.body_2);
        this.body.addChild(this.body_3);
        this.body.addChild(this.body_4);
        this.body.addChild(this.body_5);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        if (entity instanceof EntityArmorStand)
            netHeadYaw = 0;

        this.bootR.showModel = slot == EntityEquipmentSlot.FEET;
        this.bootL.showModel = slot == EntityEquipmentSlot.FEET;

        this.body_1.showModel = slot == EntityEquipmentSlot.CHEST;
        this.body_4.showModel = slot == EntityEquipmentSlot.CHEST;
        this.body_3.showModel = slot == EntityEquipmentSlot.CHEST;
        this.body.showModel = slot == EntityEquipmentSlot.CHEST;
        this.body_5.showModel = slot == EntityEquipmentSlot.CHEST;
        this.armL.showModel = slot == EntityEquipmentSlot.CHEST;
        this.armR.showModel = slot == EntityEquipmentSlot.CHEST;
        this.body_2.showModel = slot == EntityEquipmentSlot.CHEST;

        this.belt.showModel = slot == EntityEquipmentSlot.LEGS;

        this.helm.showModel = slot == EntityEquipmentSlot.HEAD;

        bipedHeadwear.showModel = false;

        bipedHead = helm;
        bipedBody = body;
        bipedRightArm = armR;
        bipedLeftArm = armL;

        if (slot == EntityEquipmentSlot.LEGS) {
            bipedRightLeg = legR;
            bipedLeftLeg = legL;
        } else {
            bipedRightLeg = bootR;
            bipedLeftLeg = bootL;
        }

        super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        GlStateManager.pushMatrix();
        if (isSneak)
            GlStateManager.translate(0, 0, 0.25);

        this.belt.render(scale);
        GlStateManager.popMatrix();
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
    }

    @Override
    public void postRenderArm(float scale, EnumHandSide side) {
        super.postRenderArm(scale, side);
    }

    @Override
    protected ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? armL : armR;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }


}