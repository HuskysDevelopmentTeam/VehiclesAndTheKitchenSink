package net.hdt.vks.init;

import net.hdt.vks.blocks.BlockAsphalt;
import net.hdt.vks.blocks.BlockObject;
import net.hdt.vks.blocks.BlockTrack;
import net.hdt.vks.blocks.BlockVehicleCreator;
import net.hdt.vks.enums.RoadTypes;
import net.hdt.vks.enums.TrackType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import static net.hdt.vks.Reference.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ModBlocks {

    public static final Block VEHICLE_CREATOR;
    public static final Block ASPHALT;

    public static final Block[] ROAD = new Block[28];
    public static final Block[] TRACK = new Block[3];

    static {
        VEHICLE_CREATOR = new BlockVehicleCreator();
        ASPHALT = new BlockObject(Material.ROCK, "asphalt");

        for (RoadTypes roadTypes : RoadTypes.values()) {
            ROAD[roadTypes.getID()] = new BlockAsphalt(roadTypes.getName() + "_asphalt");
        }

        for (TrackType trackType : TrackType.values()) {
            TRACK[trackType.getID()] = new BlockTrack(trackType.getName() + "_track");
        }

        //TODO: Add hyperloop-tunnel, metro tunnels
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {

    }

}
