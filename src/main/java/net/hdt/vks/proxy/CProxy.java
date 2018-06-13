package net.hdt.vks.proxy;

import com.mrcrayfish.vehicle.item.ItemSprayCan;
import json_generator.JsonGenerator;
import net.hdt.huskylib2.blocks.BlockMod;
import net.hdt.huskylib2.items.ItemMod;
import net.hdt.vks.client.ClientEvents;
import net.hdt.vks.client.render.vehicle.*;
import net.hdt.vks.entity.vehicle.*;
import net.hdt.vks.items.ItemChromalux;
import net.hdt.vks.items.ItemPart;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.lwjgl.input.Keyboard;

import static net.hdt.vks.Reference.MOD_ID;

public class CProxy extends SProxy {

    public static final KeyBinding KEY_HOVERBOARD_LOWERING = new KeyBinding("key.hoverboard_lowering", Keyboard.KEY_X, "key.categories.movement");

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        RenderingRegistry.registerEntityRenderingHandler(EntityRaceCar.class, RenderRacingCar::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityDBIceMotorcart.class, RenderDBIceMotorcart::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityRbhTE22.class, RenderRbhTE22::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityC62SteamLocomotive.class, RenderC62SteamLocomotive::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityC62SteamLocomotiveTender.class, RenderC62SteamLocomotiveTender::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityBMXBike.class, RenderBMXBike::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityScooter.class, RenderScooter::new);
        RenderingRegistry.registerEntityRenderingHandler(EntityMotorcycle.class, RenderMotorcycle::new);

        RenderingRegistry.registerEntityRenderingHandler(EntitySleight.class, RenderSleight::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySantaSleight.class, RenderSantaSleight::new);
        RenderingRegistry.registerEntityRenderingHandler(EntitySnowMobile.class, RenderSnowMobile::new);

        RenderingRegistry.registerEntityRenderingHandler(EntityHighBoosterBoard.class, RenderHighBoosterBoard::new);

        ClientRegistry.registerKeyBinding(KEY_HOVERBOARD_LOWERING);

        MinecraftForge.EVENT_BUS.register(new ClientEvents());
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        IItemColor color = (stack, index) -> {
            if (stack.getTagCompound() != null && index == 1 && stack.hasTagCompound() && stack.getTagCompound().hasKey("color", Constants.NBT.TAG_INT)) {
                return stack.getTagCompound().getInteger("color");
            }
            return 0x7f0000; // Red
        };
        ForgeRegistries.ITEMS.forEach((item) -> {
            if(item instanceof ItemChromalux || (item instanceof ItemPart && ((ItemPart) item).isColored())) Minecraft.getMinecraft().getItemColors().registerItemColorHandler(color, item);
        });
        if(Loader.isModLoaded("vehicle")) {
            ForgeRegistries.ITEMS.forEach((item) -> {
                if(item instanceof ItemSprayCan || (item instanceof com.mrcrayfish.vehicle.item.ItemPart && ((com.mrcrayfish.vehicle.item.ItemPart) item).isColored())) Minecraft.getMinecraft().getItemColors().registerItemColorHandler(color, item);
            });
        }
        ForgeRegistries.ITEMS.forEach((item) -> {
            if(item instanceof ItemMod) JsonGenerator.genLangFile(MOD_ID, item.getUnlocalizedName(), item.getRegistryName().getResourcePath(), "items", "Item");
        });
        ForgeRegistries.BLOCKS.forEach((block) -> {
            if(block instanceof BlockMod) {
                JsonGenerator.genLangFile(MOD_ID, block.getUnlocalizedName(), block.getRegistryName().getResourcePath(), "blocks", "Block");
            }
        });
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

}
