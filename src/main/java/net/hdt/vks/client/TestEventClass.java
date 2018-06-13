package net.hdt.vks.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderTooltipEvent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TestEventClass {

    private static final String TAG_DYE = "vks:chromalux";

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void makeTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if(!stack.isEmpty() && stack.hasTagCompound() && stack.getTagCompound().hasKey(TAG_DYE, Constants.NBT.TAG_INT)) {
            FontRenderer font = Minecraft.getMinecraft().fontRenderer;
            int len = font.getStringWidth(stack.getDisplayName());
            String spaces = "";
            while(font.getStringWidth(spaces) < len)
                spaces += " ";

            event.getToolTip().set(0, spaces);
        }
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void renderTooltip(RenderTooltipEvent.PostText event) {
        ItemStack stack = event.getStack();
        if(!stack.isEmpty() && stack.hasTagCompound() && stack.getTagCompound().hasKey(TAG_DYE, Constants.NBT.TAG_INT)) {
            int color = stack.getTagCompound().getInteger(TAG_DYE);
            String name = stack.getDisplayName();

            if(stack.hasDisplayName())
                name = TextFormatting.ITALIC + name;

            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(name, event.getX(), event.getY(), color);
        }

    }

}
