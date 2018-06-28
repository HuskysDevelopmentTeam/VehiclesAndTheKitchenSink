package net.hdt.vks.client.render.item;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

public class TEISRChromalux extends TileEntityItemStackRenderer {

    @Override
    public void renderByItem(ItemStack itemStackIn) {
        GlStateManager.pushMatrix();
        GlStateManager.color(0.0F, 1.0F, 0.0F, 1.0F);
        GlStateManager.popMatrix();
    }

}
