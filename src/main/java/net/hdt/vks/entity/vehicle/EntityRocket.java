package net.hdt.vks.entity.vehicle;

import net.hdt.vks.entity.EntityVKSAirVehicle;
import net.hdt.vks.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityRocket extends EntityVKSAirVehicle {

    public EntityRocket(World worldIn) {
        super(worldIn);
        this.setMaxSpeed(18F);
        this.setTurnSensitivity(12);
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
