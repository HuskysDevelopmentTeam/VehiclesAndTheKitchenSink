package net.hdt.vks;

import com.mrcrayfish.vehicle.entity.EntityVehicle;
import net.hdt.vks.client.TestEventClass;
import net.hdt.vks.entity.vehicle.*;
import net.hdt.vks.init.GuiHandler;
import net.hdt.vks.init.ModItems;
import net.hdt.vks.init.RegistrationHandler;
import net.hdt.vks.proxy.SProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import org.apache.logging.log4j.Logger;

import static net.hdt.vks.Reference.*;

@Mod(modid = MOD_ID, name = NAME, version = MOD_VERSION, acceptedMinecraftVersions = MC_VERSION, dependencies = DEPENDENCIES, useMetadata = true)
public class VehiclesAndTheKitchenSink {

    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs("tab_hva") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ModItems.CAR_WHEEL);
        }
    };
    @Mod.Instance
    public static VehiclesAndTheKitchenSink instance;
    @SidedProxy(clientSide = C_PROXY, serverSide = S_PROXY)
    public static SProxy proxy;

    public static Logger logger;
    public int nextEntityId;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        RegistrationHandler.init();
        MinecraftForge.EVENT_BUS.register(TestEventClass.class);
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
        registerVehicles();
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    private void registerVehicles() {
        registerVehicle("race_car", EntityRaceCar.class);
        registerVehicle("submarine", EntitySubmarine.class);
        registerVehicle("bmx_bike", EntityBMXBike.class);
        registerVehicle("scooter", EntityScooter.class);
        registerVehicle("motorcycle", EntityMotorcycle.class);
        registerVehicle("sleight", EntitySleight.class);
        registerVehicle("santa_sleight", EntitySantaSleight.class);
        registerVehicle("snow_mobile", EntitySnowMobile.class);
        registerVehicle("high_booster_board", EntityHighBoosterBoard.class);

        //TODO: Add Small Truck, UFO, Plane, Helicopter, Rocket, Car Trailer, Mini Van, Basic Car, Lamborghini, Bus, Metro, Small Plane, Tanks, Bomb Planes
    }

    private void registerVehicle(String id, Class<? extends EntityVehicle> clazz) {
        EntityRegistry.registerModEntity(new ResourceLocation(MOD_ID, id), clazz, id, nextEntityId++, this, 64, 1, true);
    }

}
