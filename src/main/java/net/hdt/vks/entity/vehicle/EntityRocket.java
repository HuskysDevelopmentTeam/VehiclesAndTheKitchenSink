package net.hdt.vks.entity.vehicle;

import com.mrcrayfish.vehicle.entity.EntityAirVehicle;
import net.hdt.vks.entity.EntityVKSAirVehicle;
import net.hdt.vks.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;

public class EntityRocket extends EntityAirVehicle {

    public float engineSpeed;

    public EntityRocket(World worldIn) {
        super(worldIn);
        this.setAccelerationSpeed(0.5F);
        this.setMaxSpeed(25F);
        this.setMaxTurnAngle(25);
        this.setTurnSensitivity(2);
        this.setSize(3F, 1.6875F);
    }

    @Override
    public void onClientInit() {
        body = new ItemStack(ModItems.ROCKET_BODY);
        engine = new ItemStack(ModItems.ROCKET_ENGINES);
        wheel = ItemStack.EMPTY;
    }

    @Override
    public SoundEvent getMovingSound() {
        return null;
    }

    @Override
    public SoundEvent getRidingSound() {
        return null;
    }

    @Override
    public void updateVehicle()
    {
        super.updateVehicle();
        if(this.onGround)
        {
            engineSpeed = currentSpeed / 30F;
        }
        else
        {
            engineSpeed *= 0.95F;
        }

        if(this.getControllingPassenger() != null)
        {
            engineSpeed += 1F;
            if(engineSpeed > 120F)
            {
                engineSpeed = 120F;
            }
        }
        else
        {
            engineSpeed *= 0.95F;
        }

        /*if(this.motionY > 0.0F) {
            for (int i = 0; i < motionY; i++) {
                world.spawnParticle(EnumParticleTypes.FLAME, posX, posY - Math.random() + i, posZ, 0.0d, -1.0d, 0.0d);
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY - Math.random() + i - 30, posZ, 0.0d, -1.0d, 0.0d);
            }
        }
        if(this.motionY > -0.1) {
            for (double i = 0.0; i > -motionY; i++) {
                world.spawnParticle(EnumParticleTypes.FLAME, posX, posY - Math.random() + i, posZ, 0.0d, -1.0d, 0.0d);
                world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, posX, posY - Math.random() + i - 30, posZ, 0.0d, -1.0d, 0.0d);
            }
        }*/
    }

    @Override
    public double getMountedYOffset() {
        return 110 * 0.0625;
    }

    @Override
    public boolean shouldRenderEngine() {
        return false;
    }

    @Override
    public boolean canBeColored() {
        return true;
    }
}
