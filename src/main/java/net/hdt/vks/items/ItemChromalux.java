package net.hdt.vks.items;

import com.mrcrayfish.vehicle.entity.EntityLandVehicle;
import com.mrcrayfish.vehicle.entity.EntitySeaVehicle;
import net.hdt.huskylib2.items.ItemMod;
import net.hdt.huskylib2.utils.math.Vector3;
import net.hdt.vks.entity.EntityVKSAirVehicle;
import net.hdt.vks.entity.EntityVKSMotorcycle;
import net.hdt.vks.utils.IColorHolder;
import net.hdt.vks.utils.ItemNBTUtils;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.List;

import static net.hdt.vks.Reference.MOD_ID;

public class ItemChromalux extends ItemMod {

    private Color color;
    public static final int MAX_SPRAYS = 5;

    public ItemChromalux(Color color) {
        super("chromalux", MOD_ID);
        this.color = color;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
//        tooltip.add(String.format("RGBA: R=%d, G=%d, B=%d, A=%d", color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
        /*if(InputUtils.isShiftDown()) {
            int color = ItemNBTUtils.hasKey(stack, "color") ? ItemNBTUtils.getInteger(stack, "color") : 0xFFFFFFFF;
            this.color = new Color(color);
            tooltip.add(TextFormatting.RED + "R: " + getColor().getRed());
            tooltip.add(TextFormatting.GREEN + "G: " + getColor().getGreen());
            tooltip.add(TextFormatting.BLUE + "B: " + getColor().getBlue());
            tooltip.add(TextFormatting.WHITE + "A: " + getColor().getBlue());
        }*/
        if(GuiScreen.isShiftKeyDown())
        {
            String info = I18n.format("item.chromalux.info");
            tooltip.addAll(Minecraft.getMinecraft().fontRenderer.listFormattedStringToWidth(info, 150));
        }
        else
        {
            if(hasColor(stack))
            {
                tooltip.add(I18n.format("item.color", TextFormatting.DARK_GRAY.toString() + String.format("#%06X", createTagCompound(stack).getInteger("color"))));
            }
            else
            {
                tooltip.add(I18n.format("item.chromalux.empty"));
            }
            tooltip.add(TextFormatting.YELLOW + "Hold SHIFT for Info");
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack stack = player.getHeldItem(hand);
        IBlockState state = world.getBlockState(pos);

        if(state.getBlock() instanceof IColorHolder) {
            IColorHolder holder = (IColorHolder)state.getBlock();
            Vector3 hit = new Vector3(hitX, hitY, hitZ);

            if(player.isSneaking()) {
                ItemNBTUtils.setInteger(stack, "color", holder.getColor(world, pos, state, facing, hit.vec3()));
            }
            else {
                int color = ItemNBTUtils.hasKey(stack, "color") ? ItemNBTUtils.getInteger(stack, "color") : 0xFFFFFFFF;
                holder.setColor(world, pos, state, facing, hit.vec3(), color);
            }

            return EnumActionResult.SUCCESS;
        }

        if(!world.isRemote && player.isSneaking()) {
            for(Entity entity : world.loadedEntityList) {
                if(entity instanceof EntityLandVehicle || entity instanceof EntityVKSMotorcycle || entity instanceof EntitySeaVehicle || entity instanceof EntityVKSAirVehicle) {
                    EntityLandVehicle landVehicle = (EntityLandVehicle) entity;
                    EntityVKSMotorcycle motorcycle = (EntityVKSMotorcycle) entity;
                    EntitySeaVehicle seaVehicle = (EntitySeaVehicle) entity;
                    EntityVKSAirVehicle airVehicle = (EntityVKSAirVehicle) entity;
                    landVehicle.setColor(color.getRGB());
                    motorcycle.setColor(color.getRGB());
                    seaVehicle.setColor(color.getRGB());
                    airVehicle.setColor(color.getRGB());
                }
            }
        }

        return EnumActionResult.PASS;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static NBTTagCompound createTagCompound(ItemStack stack)
    {
        if(!stack.hasTagCompound())
        {
            stack.setTagCompound(new NBTTagCompound());
        }
        NBTTagCompound tagCompound = stack.getTagCompound();
        if(tagCompound != null)
        {
            if(!tagCompound.hasKey("remainingSprays", Constants.NBT.TAG_INT))
            {
                tagCompound.setInteger("remainingSprays", MAX_SPRAYS);
            }
        }
        return tagCompound;
    }

    public boolean hasColor(ItemStack stack)
    {
        NBTTagCompound tagCompound = createTagCompound(stack);
        return tagCompound.hasKey("color", Constants.NBT.TAG_INT);
    }

    public int getColor(ItemStack stack)
    {
        NBTTagCompound tagCompound = createTagCompound(stack);
        return tagCompound.getInteger("color");
    }

    public void setColor(ItemStack stack, Color color)
    {
        NBTTagCompound tagCompound = createTagCompound(stack);
        tagCompound.setInteger("color", color.getRGB());
    }

    @Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        NBTTagCompound tagCompound = createTagCompound(stack);
        int remainingSprays = tagCompound.getInteger("remainingSprays");
        return tagCompound.hasKey("color", Constants.NBT.TAG_INT) && remainingSprays >= 0 && remainingSprays < MAX_SPRAYS;
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack)
    {
        NBTTagCompound tagCompound = createTagCompound(stack);
        return 1.0D - (tagCompound.getInteger("remainingSprays") / (double) MAX_SPRAYS);
    }

    public static float getRemainingSprays(ItemStack stack)
    {
        NBTTagCompound tagCompound = createTagCompound(stack);
        return (tagCompound.getInteger("remainingSprays") / (float) MAX_SPRAYS);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(this.isInCreativeTab(tab))
        {
            ItemStack stack = new ItemStack(this, 1, 0);
            createTagCompound(stack);
            items.add(stack);
        }
    }

}
