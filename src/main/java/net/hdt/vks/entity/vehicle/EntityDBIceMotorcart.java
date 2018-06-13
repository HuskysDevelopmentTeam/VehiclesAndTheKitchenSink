package net.hdt.vks.entity.vehicle;

import net.hdt.vks.entity.EntityVKSLandVehicle;
import net.hdt.vks.init.ModItems;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityDBIceMotorcart extends EntityVKSLandVehicle {

    public EntityDBIceMotorcart(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onClientInit() {
        body = new ItemStack(ModItems.DB_ICE_MOTORCART_BODY);
        wheel = new ItemStack(ModItems.TRAIN_WHEEL[1]);
        engine = new ItemStack(ModItems.ENGINE[0]);
    }

    @Override
    public SoundEvent getMovingSound() {
        return SoundEvents.BLOCK_ANVIL_BREAK;
    }

    @Override
    public SoundEvent getRidingSound() {
        return null;
    }

    @Override
    public double getMountedYOffset() {
        return 0;
    }

}
