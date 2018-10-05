package net.hdt.vks.client;

import com.mrcrayfish.obfuscate.client.event.ModelPlayerEvent;
import com.mrcrayfish.vehicle.entity.EntityPoweredVehicle;
import com.mrcrayfish.vehicle.entity.EntityVehicle;
import net.hdt.vks.entity.EntityVKSAirVehicle;
import net.hdt.vks.entity.vehicle.EntityHighBoosterBoard;
import net.hdt.vks.entity.vehicle.EntitySantaSleight;
import net.hdt.vks.entity.vehicle.EntitySleight;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.awt.*;
import java.text.DecimalFormat;

/**
 * Author: MrCrayfish
 */
public class ClientEvents {

    @SubscribeEvent
    public void onRenderTick(TickEvent.RenderTickEvent event)
    {
        if(event.phase == TickEvent.Phase.END)
        {
            Minecraft mc = Minecraft.getMinecraft();
            if(mc.inGameHasFocus)
            {
                EntityPlayer player = mc.player;
                if(player != null)
                {
                    Entity entity = player.getRidingEntity();
                    if(entity instanceof EntityVehicle)
                    {
                        String speed = new DecimalFormat("0.0").format(((EntityPoweredVehicle) entity).getKilometersPreHour());
                        mc.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "BPS: " + TextFormatting.YELLOW + speed, 10, 10, Color.WHITE.getRGB());

//                        String motion = new DecimalFormat("0.0").format(((EntityVehicle) entity).motionY);
//                        mc.fontRenderer.drawStringWithShadow(TextFormatting.BOLD + "MotionY: " + TextFormatting.YELLOW + ((EntityVehicle) entity).motionY, 10, 30, Color.WHITE.getRGB());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onPreRender(ModelPlayerEvent.Render.Pre event)
    {
        Entity ridingEntity = event.getEntityPlayer().getRidingEntity();
        if(ridingEntity instanceof EntityVKSAirVehicle)
        {
            EntityVKSAirVehicle vehicle = (EntityVKSAirVehicle) ridingEntity;
            double offset = vehicle.getMountedYOffset() * 3 - 3 * 0.0625;
            GlStateManager.translate(0, offset, 0);
            float currentSpeedNormal = (vehicle.prevCurrentSpeed + (vehicle.currentSpeed - vehicle.prevCurrentSpeed) * event.getPartialTicks()) / vehicle.getMaxSpeed();
            float turnAngleNormal = (vehicle.prevTurnAngle + (vehicle.turnAngle - vehicle.prevTurnAngle) * event.getPartialTicks()) / 45F;
            GlStateManager.rotate(turnAngleNormal * currentSpeedNormal * 20F, 0, 0, 1);
            GlStateManager.translate(0, -offset, 0);
        }
    }

    @SubscribeEvent
    public void onSetupAngles(ModelPlayerEvent.SetupAngles.Post event)
    {
        EntityPlayer player = event.getEntityPlayer();

        if(player.equals(Minecraft.getMinecraft().player) && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0)
            return;

        Entity ridingEntity = player.getRidingEntity();
        ModelPlayer model = event.getModelPlayer();

        if(ridingEntity instanceof EntitySleight)
        {
            model.bipedRightLeg.rotateAngleX = (float) Math.toRadians(-90F);
            model.bipedRightLeg.rotateAngleY = (float) Math.toRadians(15F);
            model.bipedLeftLeg.rotateAngleX = (float) Math.toRadians(-90F);
            model.bipedLeftLeg.rotateAngleY = (float) Math.toRadians(-15F);
            return;
        }

        if(ridingEntity instanceof EntityHighBoosterBoard)
        {
            model.bipedRightArm.rotateAngleX = (float) Math.toRadians(0F);
            model.bipedRightArm.rotateAngleY = (float) Math.toRadians(0F);
            model.bipedLeftArm.rotateAngleX = (float) Math.toRadians(0F);
            model.bipedLeftArm.rotateAngleY = (float) Math.toRadians(0F);
            model.bipedRightLeg.rotateAngleX = (float) Math.toRadians(0F);
            model.bipedRightLeg.rotateAngleY = (float) Math.toRadians(0F);
            model.bipedLeftLeg.rotateAngleX = (float) Math.toRadians(0F);
            model.bipedLeftLeg.rotateAngleY = (float) Math.toRadians(0F);
            return;
        }

        if(ridingEntity instanceof EntitySantaSleight)
        {
            model.bipedRightLeg.rotateAngleX = (float) Math.toRadians(-90F);
            model.bipedRightLeg.rotateAngleY = (float) Math.toRadians(15F);
            model.bipedLeftLeg.rotateAngleX = (float) Math.toRadians(-90F);
            model.bipedLeftLeg.rotateAngleY = (float) Math.toRadians(-15F);
        }

        if(ridingEntity instanceof EntityVKSAirVehicle)
        {
            model.bipedRightLeg.rotateAngleX = (float) Math.toRadians(0);
            model.bipedRightLeg.rotateAngleY = (float) Math.toRadians(15F);
            model.bipedLeftLeg.rotateAngleX = (float) Math.toRadians(0);
            model.bipedLeftLeg.rotateAngleY = (float) Math.toRadians(-15F);
        }
    }

}
