package astro.planetary;

import astro.planetary.api.ToolMode;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemMultiTool extends Item {
    public ItemMultiTool() {
        setRegistryName(Planetary.MOD_ID, "multitool");
        setUnlocalizedName(Planetary.MOD_ID + ".multitool");
        setHasSubtypes(true);
        setCreativeTab(CreativeTabs.TOOLS);
        GameRegistry.register(this);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return String.format("%s (%s)", super.getItemStackDisplayName(stack), ToolMode.from(stack.getMetadata()).getLocalized());
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {
        Vec3d vec3d = playerIn.getPositionVector();
        Vec3d vec3d1 = playerIn.getLookVec();
        int blockReachDistance = 10;
        Vec3d vec3d2 = vec3d.addVector(vec3d1.xCoord * blockReachDistance, vec3d1.yCoord * blockReachDistance, vec3d1.zCoord * blockReachDistance);
        RayTraceResult traceResult = worldIn.rayTraceBlocks(vec3d, vec3d2);
        switch (traceResult.typeOfHit) {
            case BLOCK:

                break;
            case ENTITY:
                traceResult.entityHit.attackEntityFrom(DamageSource.causePlayerDamage(playerIn), 1.0F);
                break;
        }
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < ToolMode.values().length; i++)
            subItems.add(new ItemStack(itemIn, 1, i));
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new CapSer(new EnergyStorage(100000));
    }

    public static class CapSer implements ICapabilitySerializable<NBTTagCompound> {

        EnergyStorage storage;

        public CapSer(@Nonnull EnergyStorage storage) {
            this.storage = storage;
        }

        @Override
        public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
            return capability == CapabilityEnergy.ENERGY;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
            return hasCapability(capability, facing) ? (T) storage : null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return null;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {

        }
    }
}