package net.hdt.vks.client.render.vehicle;

import com.mrcrayfish.vehicle.client.render.RenderPoweredVehicle;
import com.mrcrayfish.vehicle.client.render.RenderVehicle;
import com.mrcrayfish.vehicle.client.render.Wheel;
import com.mrcrayfish.vehicle.client.render.vehicle.RenderSportsPlane;
import net.hdt.vks.entity.vehicle.EntityRocket;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;

public class RenderRocket extends RenderPoweredVehicle<EntityRocket> {

    public RenderRocket(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(EntityRocket entity, double x, double y, double z, float currentYaw, float partialTicks) {
        RenderHelper.enableStandardItemLighting();

        EntityLivingBase entityLivingBase = (EntityLivingBase) entity.getControllingPassenger();
        if(entityLivingBase != null)
        {
            entityLivingBase.renderYawOffset = currentYaw;
            entityLivingBase.prevRenderYawOffset = currentYaw;
        }

        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(x, y, z);
            GlStateManager.rotate(-currentYaw, 0, 1, 0);
            GlStateManager.translate(0, 0, 0.15);

            float currentSpeedNormal = (entity.prevCurrentSpeed + (entity.currentSpeed - entity.prevCurrentSpeed) * partialTicks) / entity.getMaxSpeed();
            float turnAngleNormal = (entity.prevTurnAngle + (entity.turnAngle - entity.prevTurnAngle) * partialTicks) / 45F;
            GlStateManager.rotate(turnAngleNormal * currentSpeedNormal * -20F, 0, 0, 1);

            this.setupBreakAnimation(entity, partialTicks);

            GlStateManager.pushMatrix();
            {
                GlStateManager.translate(0, 0, 0);
                super.doRender(entity, x, y, z, currentYaw, partialTicks);
            }
            GlStateManager.popMatrix();

            //Render the body
            GlStateManager.pushMatrix();
            {
                GlStateManager.translate(0.53, 1.6, 0);
                GlStateManager.scale(2.2, 1.5, 2.2);
                GlStateManager.rotate(-90, 0, 0, 1);
                Minecraft.getMinecraft().getRenderItem().renderItem(entity.body, ItemCameraTransforms.TransformType.NONE);
            }
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            {
                GlStateManager.translate(0, 3.6, 0);
                GlStateManager.scale(1.5, 1.05, 1.5);
                GlStateManager.rotate(-90, 0, 0, 1);
                Minecraft.getMinecraft().getRenderItem().renderItem(entity.body, ItemCameraTransforms.TransformType.NONE);
            }
            GlStateManager.popMatrix();

            GlStateManager.pushMatrix();
            {
                GlStateManager.translate(0, 5.7, 0);
                GlStateManager.scale(1.5, 1.05, 1.5);
                GlStateManager.rotate(-90, 0, 0, 1);
                Minecraft.getMinecraft().getRenderItem().renderItem(entity.body, ItemCameraTransforms.TransformType.NONE);
            }
            GlStateManager.popMatrix();

            PartPosition enginePosition = new PartPosition(0, 8.0f, 0, -90, 1.0F);

            GlStateManager.pushMatrix();
            {
                GlStateManager.scale(0.6, 1.05, 0.6);
                GlStateManager.translate(enginePosition.x * 0.0625, enginePosition.y * 0.0625, enginePosition.z * 0.0625);
                GlStateManager.translate(0, -0.5, 0);
                GlStateManager.scale(enginePosition.scale, enginePosition.scale, enginePosition.scale);
                GlStateManager.translate(0, 0.5, 0);
                GlStateManager.rotate(enginePosition.rotation, 0, 0, 1);
                Minecraft.getMinecraft().getRenderItem().renderItem(entity.engine, ItemCameraTransforms.TransformType.NONE);
            }
            GlStateManager.popMatrix();

        }
        GlStateManager.popMatrix();
    }

    private static class PartPosition
    {
        private float x, y, z;
        private float rotation;
        private float scale;

        public PartPosition(float x, float y, float z, float rotation, float scale)
        {
            this.x = x;
            this.y = y;
            this.z = z;
            this.rotation = rotation;
            this.scale = scale;
        }
    }

}
