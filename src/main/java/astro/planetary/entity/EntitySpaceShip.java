package astro.planetary.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

import astro.planetary.RotatedAxes;
import astro.planetary.api.IControllable;
import astro.planetary.client.vector.Vector3f;

import java.util.List;

public abstract class EntitySpaceShip extends Entity implements IControllable{
    private float momentum;
    //Replace yaw, roll and pitch with one set of rotated axes
    public RotatedAxes axes;
    //Additional set of axes for smoothness
    public RotatedAxes prevAxes;
    private double lerpY;
    private double lerpZ;
    private double lerpXRot;
    private boolean leftInputDown;
    private boolean rightInputDown;
    private boolean forwardInputDown;
    private boolean backInputDown;
    private float deltaRotation;
    
    //Control variables;
    public float throttle = 0;
    public Vector3f turnSpeed = new Vector3f(0,0,0); //Yaw, Pitch, Roll
    public float prevRotationRoll = 0;
    
    //Control boundaries
    public float maxThrottle = 1; 
    public Vector3f maxTurnSpeed = new Vector3f(1,1,1); //Yaw, Pitch, Roll
    public float acceleration = 0.01F;
    public Vector3f turnRates = new Vector3f (0.01F,0.01F,0.01F); //Yaw, Pitch, Roll
    
    //Additional variables for translation in space
    public Vector3f translate = new Vector3f(0,0,0);

    public EntitySpaceShip(World worldIn) {
        super(worldIn);
        axes = new RotatedAxes(0,0,0);
        prevAxes = axes.clone();
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox() {
        return super.getCollisionBoundingBox();
    }

    @SideOnly(Side.CLIENT)
    public abstract void render(float f1);

    @Override
    public AxisAlignedBB getEntityBoundingBox() {
        return super.getEntityBoundingBox();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return getEntityBoundingBox();
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBox(Entity entityIn) {
        return entityIn.getEntityBoundingBox();
    }

    @Override
    protected void entityInit() {

    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {

    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
    	
    }
    
    @Override
    public void onUpdate()
    {
    	this.prevRotationPitch = axes.getPitch();
    	this.prevRotationYaw = axes.getYaw();
    	this.prevRotationRoll = axes.getRoll();
    	prevAxes = axes.clone();
    	prevPosX = posX;
    	prevPosY = posY;
    	prevPosZ = posZ;
    	
    	/**
    	//Change throttle
    	throttle = 0F;
    	
    	//Change turn speeds
    	turnSpeed.x = 0F;
    	turnSpeed.y = 0F;
    	turnSpeed.z = 0F;
    	
    	//Translational thrusters
    	translate = new Vector3f(0,0,0);
    	
    	//Reset orientation
    	axes = new RotatedAxes(0,0,0);
    	*/
    	
    	fly();
    }
    
    public void fly()
    {
    	//Apply rotations. Always rotate Yaw, then Pitch, then Roll.
    	axes.rotateLocalYaw(turnSpeed.x);
    	axes.rotateLocalPitch(-turnSpeed.y);
    	axes.rotateLocalRoll(turnSpeed.z);
    	axes.rotateGlobalRoll(180F); //Rotate calculation by 180 degrees to account for upside down model
    	Vector3f move = new Vector3f(throttle,0,0); //Velocity vector   
    	Vector3f.add(move, translate, move); //Apply translational thrust
    	move = axes.findLocalVectorGlobally(move); //Take our velocity vector and rotate it in line with our model
    	this.motionX = move.x;
    	this.motionY = move.y;
    	this.motionZ = move.z;
    	axes.rotateGlobalRoll(-180F); //Rotate calculation back
    	//Apply motion
    	this.posX += motionX;
    	this.posY += motionY;
    	this.posZ += motionZ;
    	spawnExhaust(); //Spawn exhaust particles. May or may not need to reset motion vector
    	this.motionX = 0;
    	this.motionY = 0;
    	this.motionZ = 0;
    }
    
    @SideOnly(Side.CLIENT)
    public void spawnExhaust()
    {
    	if(throttle == 0) return;
    	axes.rotateGlobalRoll(180F); //Rotate calculation again
    	//Define engine positions
    	Vector3f engine1 = new Vector3f(-150/16F, -24/16F, 24/16F);
    	Vector3f engine2 = new Vector3f(-150/16F, -24/16F, -24/16F);
    	//Rotate engine positions in line with model orientation
    	engine2 = axes.findLocalVectorGlobally(engine2);
    	engine1 = axes.findLocalVectorGlobally(engine1);
    	//Define particle velocity
    	Vector3f a = new Vector3f(-throttle,(float)Math.random()*0.1, (float)Math.random()*0.1);
    	a = axes.findLocalVectorGlobally(a);
    	axes.rotateGlobalRoll(-180F); //Rotate everything back
    	//Spawn!
    	this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, posX + engine1.x, posY + engine1.y, posZ + engine1.z, a.x, a.y, a.z);
       	this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, posX + engine2.x, posY + engine2.y, posZ + engine2.z, a.x, a.y, a.z);

    }

    //Don't bother with this, we want to use the rotation matrix to handle orientation
    /**
    @SideOnly(Side.CLIENT)
    @Override
    public void applyOrientationToEntity(Entity entityToUpdate) {
        this.applyYawToEntity(entityToUpdate);
    }
   
    protected void applyYawToEntity(Entity entityToUpdate) {
        entityToUpdate.setRenderYawOffset(this.rotationYaw);
        float f = MathHelper.wrapDegrees(entityToUpdate.rotationYaw - this.rotationYaw);
        float f1 = clamp(f, -105.0F, 105.0F);
        entityToUpdate.prevRotationYaw += f1 - f;
        entityToUpdate.rotationYaw += f1 - f;
        entityToUpdate.setRotationYawHead(entityToUpdate.rotationYaw);
    }
    */

    
    //I reject your boat physics and substitute my own
    /**
    private void controlBoat() {
        if (this.isBeingRidden()) {
            float f = 0.0F;

            if (this.leftInputDown) {
                this.deltaRotation += -1.0F;
            }

            if (this.rightInputDown) {
                ++this.deltaRotation;
            }

            if (this.rightInputDown != this.leftInputDown && !this.forwardInputDown && !this.backInputDown) {
                f += 0.005F;
            }

            this.rotationYaw += this.deltaRotation;

            if (this.forwardInputDown) {
                f += 0.04F;
            }

            if (this.backInputDown) {
                f -= 0.005F;
            }

            this.motionX += (double) (MathHelper.sin(-this.rotationYaw * 0.017453292F) * f);
            this.motionZ += (double) (MathHelper.cos(this.rotationYaw * 0.017453292F) * f);
        }
    }
    
    */
    //For some reason math.clamp won't work, so I made my own function with blackjack and hookers. -Proto
    public float clamp(float var, float min, float max)
    {
    	if(var > max) var = max;
    	if(var < min) var = min;
    	return var;
    }

    @Override
    protected boolean canFitPassenger(Entity passenger) {
        return this.getPassengers().size() < 1;
    }

    @Nullable
    @Override
    public Entity getControllingPassenger() {
        List<Entity> list = this.getPassengers();
        return list.isEmpty() ? null : list.get(0);
    }

    //Commenting this out so it can be replaced with something more substantial
    /**
    @SideOnly(Side.CLIENT)
    public void updateInputs(boolean leftInputDown, boolean rightInputDown, boolean forwardInputDown, boolean backInputDown) {
        this.leftInputDown = leftInputDown;
        this.rightInputDown = rightInputDown;
        this.forwardInputDown = forwardInputDown;
        this.backInputDown = backInputDown;
    }
    */
    
    @Override
	public boolean pressKey(int key, EntityPlayer player)
	{

		switch(key)
		{
			case 0 : //Accelerate : Increase the throttle, up to 1.
			{
				if(throttle < maxThrottle) throttle += 0.01F;
				return true;
			}
			case 1 : //Decelerate : Decrease the throttle, down to -1, or 0 if the vehicle cannot reverse
			{
				if(throttle > -maxThrottle) throttle -= 0.01F;
				return true;
			}
			case 2 : //Left : Yaw the craft left
			{
				if(turnSpeed.x > -maxTurnSpeed.x) turnSpeed.x -= turnRates.x;
				return true;
			}
			case 3 : //Right : Yaw the craft right
			{
				if(turnSpeed.x < maxTurnSpeed.x) turnSpeed.x += turnRates.x;
				return true;
			}
			case 4 : //Up : Pitch up
			{
				if(turnSpeed.y < maxTurnSpeed.y) turnSpeed.y += turnRates.y;				
				return true;
			}
			case 5 : //Down : Pitch down
			{
				if(turnSpeed.y > -maxTurnSpeed.y) turnSpeed.y -= turnRates.y;
				return true;
			}
			case 6 : //Exit : Get out
			{
				player.dismountRidingEntity();
          		return true;
			}
			case 7 : //Inventory
			{
				return true;
			}
			case 8 : //Change control mode : Do nothing
			{
				return true;
			}
			case 9 : //Roll left : Do nothing
			{
				if(turnSpeed.z > -maxTurnSpeed.z) turnSpeed.z -= turnRates.z;
				return true;
			}
			case 10 : //Roll right : Do nothing
			{
				if(turnSpeed.z < maxTurnSpeed.z) turnSpeed.z += turnRates.z;	
				return true;
			}
			case 11 : // Gear : Do nothing
			{
				return true;
			}

		}
		return false;
		

	}


    @Override
    public boolean processInitialInteract(EntityPlayer player, @Nullable ItemStack stack, EnumHand hand) {
        if (!worldObj.isRemote && !player.isSneaking())
            player.startRiding(this);
        return true;
    }

    public enum Status {
        LANDED,
        CRASHED,
        IN_WATER,
        TAKING_OFF,
        LANDING,
        IN_FLIGHT
    }
    
    //Possible flight models. Conventional and VTOL for atmospheric flight, SIDDOF for spaceflight
    public enum FlightMode {
    	CONVENTIONAL,
    	VTOL,
    	SIXDOF
    }

    @Override
    public boolean shouldRiderSit() {
        return true;
    }
}