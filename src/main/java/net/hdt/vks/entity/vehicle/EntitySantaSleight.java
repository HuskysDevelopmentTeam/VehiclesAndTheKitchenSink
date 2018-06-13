package net.hdt.vks.entity.vehicle;

import com.mrcrayfish.vehicle.common.CommonEvents;
import net.hdt.vks.entity.EntityVKSLandVehicle;
import net.hdt.vks.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntitySantaSleight extends EntityVKSLandVehicle {
    private EntityPlayer pusher;

    public EntitySantaSleight(World worldIn) {
        super(worldIn);
    }

    @Override
    public void onClientInit() {
        body = new ItemStack(ModItems.SANTA_SLEIGHT_BODY);
        wheel = ItemStack.EMPTY;
    }

    @Override
    public void onUpdate() {
        if (pusher != null) {
            this.prevRotationYaw = this.rotationYaw;
            this.prevPosX = this.posX;
            this.prevPosY = this.posY;
            this.prevPosZ = this.posZ;
            float x = MathHelper.sin(-pusher.rotationYaw * 0.017453292F) * 1.3F;
            float z = MathHelper.cos(-pusher.rotationYaw * 0.017453292F) * 1.3F;
            this.posX = pusher.posX + x;
            this.posY = pusher.posY;
            this.posZ = pusher.posZ + z;
            this.lastTickPosX = this.posX;
            this.lastTickPosY = this.posY;
            this.lastTickPosZ = this.posZ;
            this.rotationYaw = pusher.rotationYaw;
        } else {
            super.onUpdate();
        }
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand) {
        if (!world.isRemote) {
            if (player.isSneaking()) {
                if (pusher == player) {
                    pusher = null;
                    player.getDataManager().set(CommonEvents.PUSHING_CART, false);
                    return true;
                } else if (pusher == null) {
                    pusher = player;
                    player.getDataManager().set(CommonEvents.PUSHING_CART, true);
                }
            } else if (pusher != player) {
                super.processInitialInteract(player, hand);
            }

        }
        return true;
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
        return 0.0625 * 7.5;
    }

    @Override
    public void dismountRidingEntity() {
        super.dismountRidingEntity();
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
