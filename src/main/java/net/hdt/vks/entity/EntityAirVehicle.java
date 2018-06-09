package net.hdt.vks.entity;

import com.mrcrayfish.vehicle.entity.EntityVehicle;
import com.mrcrayfish.vehicle.network.PacketHandler;
import com.mrcrayfish.vehicle.network.message.MessageDrift;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public abstract class EntityAirVehicle extends EntityVehicle {

    private static final DataParameter<Boolean> DRIFTING = EntityDataManager.createKey(EntityVehicle.class, DataSerializers.BOOLEAN);

    public float drifting;
    public float additionalYaw;
    public float prevAdditionalYaw;

    public float prevLeanAngle;
    public float leanAngle;

    protected EntityAirVehicle(World worldIn) {
        super(worldIn);
    }

    @Override
    public void entityInit() {
        super.entityInit();
        this.dataManager.register(DRIFTING, false);
    }

    @Override
    public void onClientUpdate() {
        super.onClientUpdate();
        EntityLivingBase entity = (EntityLivingBase) this.getControllingPassenger();
        if (entity != null && entity.equals(Minecraft.getMinecraft().player)) {
            boolean drifting = Minecraft.getMinecraft().gameSettings.keyBindJump.isKeyDown();
            if (this.isDrifting() != drifting) {
                this.setDrifting(drifting);
                PacketHandler.INSTANCE.sendToServer(new MessageDrift(drifting));
            }
        }
    }

    @Override
    public void onEntityUpdate()
    {
        this.prevLeanAngle = this.leanAngle;
        super.onEntityUpdate();
        this.leanAngle = this.turnAngle / (float) getMaxTurnAngle();
        double speed = posY - lastTickPosY;
        if(speed > 0.0D  && onGround && !world.isRemote) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Exploded!"));
        }
    }

    @Override
    public void updateVehicle() {

        prevAdditionalYaw = additionalYaw;

        for (int i = 0; i < motionY; i++) {
            world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, posX, posY - Math.random() + i, posZ, 0.0d, -1.0d, 0.0d);
            world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, posX, posY - Math.random() + i, posZ + 0.45, 0.0d, -1.0d, 0.0d);
            world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, posX, posY - Math.random() + i, posZ + 0.7, 0.0d, -1.0d, 0.0d);
        }
    }

    private void updateDrifting() {
        TurnDirection turnDirection = this.getTurnDirection();
        if (this.getControllingPassenger() != null && this.isDrifting() && turnDirection != TurnDirection.FORWARD) {
            AccelerationDirection acceleration = this.getAcceleration();
            if (acceleration == AccelerationDirection.FORWARD) {
                this.currentSpeed *= 0.95F;
            }
            this.drifting = Math.min(1.0F, this.drifting + (1.0F / 8.0F));
        } else {
            this.drifting *= 0.85F;
        }
        this.additionalYaw = 35F * (turnAngle / 45F) * drifting;

        //Updates the delta yaw to consider drifting
        this.deltaYaw = this.wheelAngle * (currentSpeed / 30F) / (this.isDrifting() ? 1.5F : 2F);
    }

    @Override
    public void updateVehicleMotion() {
        float f1 = MathHelper.sin(this.rotationYaw * 0.017453292F) / 20F; //Divide by 20 ticks
        float f2 = MathHelper.cos(this.rotationYaw * 0.017453292F) / 20F;
        double minHeightLimitation = 0.0D;
        double maxHeightLimitation = 3.0D;
        this.vehicleMotionX = (-currentSpeed * f1);

        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            this.motionY += 0.5D / 30F;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_V)) {
            this.motionY -= 0.5D / 30F;
        }

        if(this.motionY > maxHeightLimitation && !Keyboard.isKeyDown(Keyboard.KEY_V) || !Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            this.motionY -= 0.03D / 50F;
        }

        this.vehicleMotionZ = (currentSpeed * f2);
    }

    @Override
    protected void removePassenger(Entity passenger) {
        super.removePassenger(passenger);
        if (this.getControllingPassenger() == null) {
            this.rotationYaw -= this.additionalYaw;
            this.additionalYaw = 0;
            this.drifting = 0;
        }
    }

    public boolean isDrifting() {
        return this.dataManager.get(DRIFTING);
    }

    public void setDrifting(boolean drifting) {
        this.dataManager.set(DRIFTING, drifting);
    }

}

