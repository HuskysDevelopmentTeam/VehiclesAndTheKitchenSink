package net.hdt.vks.entity;

import com.mrcrayfish.vehicle.entity.EntityMotorcycle;
import com.mrcrayfish.vehicle.entity.EntityPoweredVehicle;
import com.mrcrayfish.vehicle.init.ModSounds;
import com.mrcrayfish.vehicle.item.ItemSprayCan;
import net.hdt.vks.items.ItemChromalux;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public abstract class EntityVKSMotorcycle extends EntityMotorcycle {

    public EntityVKSMotorcycle(World worldIn) {
        super(worldIn);
    }

    @Override
    public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
    {
        if(!world.isRemote && !player.isSneaking())
        {
            ItemStack heldItem = player.getHeldItem(hand);
            if(!heldItem.isEmpty() && heldItem.getItem() instanceof ItemSprayCan)
            {
                if(canBeColored())
                {
                    NBTTagCompound tagCompound = ItemSprayCan.createTagCompound(heldItem);
                    int remainingSprays = tagCompound.getInteger("remainingSprays");
                    if(tagCompound.hasKey("color", Constants.NBT.TAG_INT) && remainingSprays > 0)
                    {
                        int color = tagCompound.getInteger("color");
                        if(this.getColor() != color)
                        {
                            this.setColor(tagCompound.getInteger("color"));
                            player.world.playSound(null, posX, posY, posZ, ModSounds.SPRAY_CAN_SPRAY, SoundCategory.PLAYERS, 1.0F, 1.0F);
                            tagCompound.setInteger("remainingSprays", remainingSprays - 1);
                        }
                    }
                }
            } else if(!heldItem.isEmpty() && heldItem.getItem() instanceof ItemChromalux)
            {
                if(canBeColored())
                {
                    NBTTagCompound tagCompound = ItemChromalux.createTagCompound(heldItem);
                    int remainingSprays = tagCompound.getInteger("remainingSprays");
                    if(tagCompound.hasKey("color", Constants.NBT.TAG_INT) && remainingSprays > 0)
                    {
                        int color = tagCompound.getInteger("color");
                        if(this.getColor() != color)
                        {
                            this.setColor(tagCompound.getInteger("color"));
//                            player.world.playSound(null, posX, posY, posZ, ModSounds.SPRAY_CAN_SPRAY, SoundCategory.PLAYERS, 1.0F, 1.0F);
                            tagCompound.setInteger("remainingSprays", remainingSprays - 1);
                        }
                    }
                }
            }
            else
            {
                player.startRiding(this);
            }
        }
        return true;
    }

}
