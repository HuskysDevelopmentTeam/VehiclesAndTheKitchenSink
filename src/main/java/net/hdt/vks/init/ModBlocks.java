package net.hdt.vks.init;

import net.hdt.vks.VehiclesAndTheKitchenSink;
import net.hdt.vks.blocks.BlockObject;
import net.hdt.vks.blocks.BlockVehicleCreator;
import net.hdt.vks.enums.RoadTypes;
import net.hdt.vks.enums.TrackType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

/**
 * Author: MrCrayfish
 */
public class ModBlocks {
    public static final Block VEHICLE_CREATOR;

    public static final Block[] ROAD = new Block[28];
    public static final Block ASPHALT;
    public static final Block[] TRACK = new Block[3];

    static {
        VEHICLE_CREATOR = new BlockVehicleCreator();
        ASPHALT = new BlockObject(Material.ROCK, "asphalt");

        for (RoadTypes roadTypes : RoadTypes.values()) {
            ROAD[roadTypes.getID()] = new BlockObject(Material.ROCK, roadTypes.getName() + "_asphalt");
        }

        for (TrackType trackType : TrackType.values()) {
            TRACK[trackType.getID()] = new BlockObject(Material.IRON, trackType.getName() + "_track");
        }

        //TODO: Add hyperloop-tunnel, metro tunnels
    }

    public static void register() {
        registerBlock(VEHICLE_CREATOR);
        registerBlock(ASPHALT);

        for (RoadTypes roadTypes : RoadTypes.values()) {
            registerBlock(ROAD[roadTypes.getID()]);
        }

        for (TrackType trackType : TrackType.values()) {
            registerBlock(TRACK[trackType.getID()]);
        }
    }

    private static void registerBlock(Block block) {
        registerBlock(block, new ItemBlock(block));
    }

    private static void registerBlock(Block block, ItemBlock item) {
        if (block.getRegistryName() == null)
            throw new IllegalArgumentException("A block being registered does not have a registry name and could be successfully registered.");

        block.setCreativeTab(VehiclesAndTheKitchenSink.CREATIVE_TAB);
        RegistrationHandler.Blocks.add(block);
        item.setRegistryName(block.getRegistryName());
        RegistrationHandler.Items.add(item);
    }
}
