package net.hdt.vks.entity.vehicle;

import com.mrcrayfish.vehicle.init.ModSounds;
import net.hdt.vks.entity.EntityVKSMotorcycle;
import net.hdt.vks.init.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Author: MrCrayfish
 */
public class EntityScooter extends EntityVKSMotorcycle {

    /**
     * ItemStack instances used for rendering
     */
    @SideOnly(Side.CLIENT)
    public ItemStack handleBar;

    public EntityScooter(World worldIn) {
        super(worldIn);
        this.setMaxSpeed(18F);
        this.setTurnSensitivity(12);
        this.setSize(1.5F, 1.0F);
        this.setHeldOffset(new Vec3d(6D, -0.5D, 0D));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onClientInit()
    {
        body = new ItemStack(ModItems.SCOOTER_BODY);
        wheel = new ItemStack(ModItems.SCOOTER_WHEEL);
        handleBar = new ItemStack(ModItems.SCOOTER_HANDLE_BAR);
    }

    @Override
    public SoundEvent getMovingSound() {
        return ModSounds.GO_KART_ENGINE_MONO;
    }

    @Override
    public SoundEvent getRidingSound() {
        return ModSounds.GO_KART_ENGINE_STEREO;
    }

    @Override
    public float getMinEnginePitch() {
        return 0.5F;
    }

    @Override
    public float getMaxEnginePitch() {
        return 1.8F;
    }

    @Override
    public boolean shouldRenderEngine() {
        return false;
    }

    @Override
    public double getMountedYOffset() {
        return 9.5 * 0.0625;
    }

    @Override
    public boolean canBeColored() {
        return true;
    }

}
