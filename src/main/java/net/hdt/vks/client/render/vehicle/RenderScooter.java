package net.hdt.vks.client.render.vehicle;

import net.hdt.vks.client.render.RenderLandVehicle;
import net.hdt.vks.client.render.Wheel;
import net.hdt.vks.entity.vehicle.EntityScooter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;

/**
 * Author: MrCrayfish
 */
public class RenderScooter extends RenderLandVehicle<EntityScooter> {
    public RenderScooter(RenderManager renderManager) {
        super(renderManager);
        this.wheels.add(new Wheel(Wheel.Side.NONE, Wheel.Position.REAR, 0F, -6.7F, 1.65F));
    }

    @Override
    public void doRender(EntityScooter entity, double x, double y, double z, float currentYaw, float partialTicks) {
        RenderHelper.enableStandardItemLighting();

        float additionalYaw = entity.prevAdditionalYaw + (entity.additionalYaw - entity.prevAdditionalYaw) * partialTicks;

        EntityLivingBase entityLivingBase = (EntityLivingBase) entity.getControllingPassenger();
        if (entityLivingBase != null) {
            entityLivingBase.renderYawOffset = currentYaw - additionalYaw;
            entityLivingBase.prevRenderYawOffset = currentYaw - additionalYaw;
        }

        GlStateManager.pushMatrix();
        {
            GlStateManager.translate(x, y, z);
            GlStateManager.rotate(-currentYaw, 0, 1, 0);
            GlStateManager.rotate(additionalYaw, 0, 1, 0);
            GlStateManager.scale(1.5, 1.5, 1.5);
            GlStateManager.translate(0, 0.15, 0.15);

            float currentSpeedNormal = (entity.prevCurrentSpeed + (entity.currentSpeed - entity.prevCurrentSpeed) * partialTicks) / entity.getMaxSpeed();
            float turnAngleNormal = (entity.prevTurnAngle + (entity.turnAngle - entity.prevTurnAngle) * partialTicks) / 45F;
            GlStateManager.rotate(turnAngleNormal * currentSpeedNormal * -20F, 0, 0, 1);

            this.setupBreakAnimation(entity, partialTicks);

            GlStateManager.pushMatrix();
            {
                GlStateManager.translate(0, 1.7 * 0.0625, 0);
                super.doRender(entity, x, y, z, currentYaw, partialTicks);
            }
            GlStateManager.popMatrix();

            //Render the body
            GlStateManager.pushMatrix();
            {
                GlStateManager.translate(0, 0.5, 0);
                Minecraft.getMinecraft().getRenderItem().renderItem(entity.body, ItemCameraTransforms.TransformType.NONE);
            }
            GlStateManager.popMatrix();

            //Render the handles bars
            GlStateManager.pushMatrix();
            {
                GlStateManager.translate(0, 0.5, 10.5 * 0.0625);
                GlStateManager.rotate(-22.5F, 1, 0, 0);

                float wheelAngle = entity.prevWheelAngle + (entity.wheelAngle - entity.prevWheelAngle) * partialTicks;
                float wheelAngleNormal = wheelAngle / 45F;
                float turnRotation = wheelAngleNormal * 25F;

                GlStateManager.rotate(turnRotation, 0, 1, 0);
                GlStateManager.rotate(22.5F, 1, 0, 0);
                GlStateManager.translate(0, 0, -10.5 * 0.0625);

                Minecraft.getMinecraft().getRenderItem().renderItem(entity.handleBar, ItemCameraTransforms.TransformType.NONE);

                float frontWheelSpin = entity.prevFrontWheelRotation + (entity.frontWheelRotation - entity.prevFrontWheelRotation);
                float rearWheelSpin = entity.prevRearWheelRotation + (entity.rearWheelRotation - entity.prevRearWheelRotation);

                GlStateManager.pushMatrix();
                {
                    GlStateManager.translate(0, 0, 0);
                    if(entity.isMoving())
                    {
//                        GlStateManager.rotate(-rearWheelSpin, 0, 0, 0);
                    }
                    Minecraft.getMinecraft().getRenderItem().renderItem(entity.wheel, ItemCameraTransforms.TransformType.NONE);
                }
                GlStateManager.popMatrix();

                GlStateManager.pushMatrix();
                {
                    GlStateManager.translate(0, -0.02, -0.99);
                    if(entity.isMoving())
                    {
//                        GlStateManager.rotate(-frontWheelSpin, 0, 0, 0);
                    }
                    Minecraft.getMinecraft().getRenderItem().renderItem(entity.wheel, ItemCameraTransforms.TransformType.NONE);
                }
                GlStateManager.popMatrix();
            }
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();
    }
}
