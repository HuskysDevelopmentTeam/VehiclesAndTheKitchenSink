package net.hdt.vks.items;

import net.hdt.vks.VehiclesAndTheKitchenSink;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

import static net.hdt.vks.Reference.MOD_ID;

public class ItemVehicleBlueprint extends Item {

    public ItemVehicleBlueprint(String vehicleName) {
        this.setUnlocalizedName(vehicleName + "_blueprint");
        this.setRegistryName(MOD_ID, vehicleName + "_blueprint");
        this.setCreativeTab(VehiclesAndTheKitchenSink.CREATIVE_TAB);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if(!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

    }

}
