package net.hdt.vks.items;

import net.hdt.huskylib2.items.ItemMod;
import net.hdt.vks.VehiclesAndTheKitchenSink;
import net.hdt.vks.utils.ItemNBTHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
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
                tooltip.add(I18n.format("item.color", TextFormatting.fromColorIndex(new Color(getColor(stack)).getRGB()) + String.format("#%06X", createTagCompound(stack).getInteger("color"))));
                ItemNBTHelper.setInt(new ItemStack(this), "vks:chromalux", 15);
            }
            else
            {
                tooltip.add(I18n.format("item.chromalux.empty"));
            }
            tooltip.add(TextFormatting.YELLOW + "Hold SHIFT for Info");
        }
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

        if(!worldIn.isRemote && player.isSneaking()) {
            player.openGui(VehiclesAndTheKitchenSink.instance, 1, worldIn, pos.getX(), pos.getY(), pos.getZ());
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
