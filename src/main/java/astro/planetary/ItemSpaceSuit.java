package astro.planetary;

import astro.planetary.client.model.ModelSpaceSuit;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagLong;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSpaceSuit extends ItemArmor {

    public static final ArmorMaterial SPACE_SUIT = EnumHelper.addArmorMaterial("space_suit", "space_suit", 5, new int[]{1, 2, 3, 1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0F);

    public ItemSpaceSuit(int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(SPACE_SUIT, renderIndexIn, equipmentSlotIn);
        setRegistryName(Planetary.MOD_ID, "spacesuit_" + equipmentSlotIn.name().toLowerCase());
        setUnlocalizedName(Planetary.MOD_ID + ".spacesuit_" + equipmentSlotIn.name().toLowerCase());
        GameRegistry.register(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        switch (armorSlot) {
            case FEET:
                return ModelSpaceSuit.FEET;
            case CHEST:
                return ModelSpaceSuit.CHEST;
            case LEGS:
                return ModelSpaceSuit.LEGS;
            case HEAD:
                return ModelSpaceSuit.HEAD;
            default:
                return null;
        }
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return "planetary:textures/models/armor/space_suit_" + type + ".png";
    }

    @Override
    public int getColor(ItemStack stack) {
        return getColorFromStack(stack);
    }

    public static int getColorFromStack(ItemStack stack) {
        if (stack == null || !(stack.getItem() instanceof ItemSpaceSuit))
            return 0;

        NBTTagCompound tag = stack.getTagCompound();

        if (tag != null && tag.hasKey("display")) {
            NBTTagCompound displayTag = tag.getCompoundTag("display");
            if (displayTag.hasKey("color", 3)) return displayTag.getInteger("color");
        }
        return 0xFEFEFE;
    }

    @Override
    public boolean hasColor(ItemStack stack) {
        return true;
    }

    @Override
    public void removeColor(ItemStack stack) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag != null && tag.hasKey("display")) {
            NBTTagCompound displayTag = tag.getCompoundTag("display");

            if (displayTag.hasKey("color")) {
                displayTag.removeTag("color");
            }
        }
    }

    @Override
    public void setColor(ItemStack stack, int color) {
        NBTTagCompound tag = stack.getTagCompound();
        if (tag == null) {
            tag = new NBTTagCompound();
            stack.setTagCompound(tag);
        }
        NBTTagCompound displayTag;
        if (tag.hasKey("display"))
            displayTag = tag.getCompoundTag("display");
        else
            displayTag = new NBTTagCompound();
        displayTag.setInteger("color", color);
    }
}