package net.hdt.vks.init;

import net.hdt.vks.entity.vehicle.EntityC62SteamLocomotive;
import net.hdt.vks.entity.vehicle.EntityC62SteamLocomotiveTender;
import net.hdt.vks.entity.vehicle.EntityDBIceMotorcart;
import net.hdt.vks.entity.vehicle.EntityRbhTE22;
import net.hdt.vks.enums.TrainEngineTypes;
import net.hdt.vks.enums.TrainWheelTypes;
import net.hdt.vks.items.*;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.awt.*;

import static net.hdt.vks.Reference.MOD_ID;

@Mod.EventBusSubscriber(modid = MOD_ID)
public class ModItems {

    public static final Item SUBMARINE_BODY;
    public static final Item PLANE_BODY, PLANE_WING, PLANE_MOTOR;
    public static final Item HELICOPTER_BODY, HELICOPTER_ROTOR, HELICOPTER_WINGS;
    public static final Item UFO_BODY;
    public static final Item ROCKET_BODY, ROCKET_ENGINES;
    public static final Item CAR_TRAILER_FRONT_HOUSE_BODY, CAR_TRAILER_CONTAINER_BODY;
    public static final Item CONTAINER;
    public static final Item RACE_CAR_BODY;
    public static final Item STEERING_WHEEL;
    public static final Item CAR_WHEEL;

    public static final Item BMX_BIKE_BODY, BMX_BIKE_HANDLE_BAR;
    public static final Item SCOOTER_BODY, SCOOTER_HANDLE_BAR, SCOOTER_WHEEL;
    public static final Item MOTORCYCLE_BODY, MOTORCYCLE_HANDLE_BAR;

    public static final Item SNOW_MOBILE_BODY, SNOW_MOBILE_HANDLE_BAR, SNOW_MOBILE_SKI, SNOW_MOBILE_TRACKS_MODULE;

    public static final Item SLEIGHT_BODY, SANTA_SLEIGHT_BODY;

    public static final Item HIGH_BOOSTER_BOARD;
    public static final Item COVER_F_BODY, COVER_F_STEERING_THING;
    public static final Item COVER_P_BODY, COVER_P_STEERING_THING;
    public static final Item COVER_S_BODY, COVER_S_STEERING_THING;

    public static final Item TOY_STEAM_LOCOMOTIVE_BODY, TOY_TRAIN_CARRIAGE_BODY;

    public static final Item[] TRAIN_WHEEL = new Item[3];
    public static final Item[] ENGINE = new Item[4];
    public static final Item DB_ICE_MOTORCART_BODY, C62_STEAM_LOCOMOTIVE_BODY, C62_STEAM_LOCOMOTIVE_TENDER_BODY, RBH_TE_2_2_BODY;
    public static final Item DB_ICE_MOTORCART, C62_STEAM_LOCOMOTIVE, C62_STEAM_LOCOMOTIVE_TENDER, RBH_TE_2_2;

    public static final Item TRAIN_CONTROLLER;

    public static final Item CHROMALUX;

    static {
        SUBMARINE_BODY = new ItemPart("submarine_body").setColored();
        PLANE_BODY = new ItemPart("plane_body").setColored();
        PLANE_MOTOR = new ItemPart("plane_engine");
        PLANE_WING = new ItemPart("plane_wing").setColored();
        HELICOPTER_BODY = new ItemPart("helicopter_body").setColored();
        HELICOPTER_ROTOR = new ItemPart("helicopter_rotor");
        HELICOPTER_WINGS = new ItemPart("helicopter_rotor_wings");
        UFO_BODY = new ItemPart("ufo_body").setColored();
        ROCKET_BODY = new ItemPart("rocket_body").setColored();
        ROCKET_ENGINES = new ItemPart("rocket_engine");
        CAR_TRAILER_FRONT_HOUSE_BODY = new ItemPart("car_trailer_front_house_body").setColored();
        CAR_TRAILER_CONTAINER_BODY = new ItemPart("car_trailer_container_holder").setColored();
        CONTAINER = new ItemPart("container").setColored();
        RACE_CAR_BODY = new ItemPart("racing_car_body").setColored();
        STEERING_WHEEL = new ItemPart("steering_wheel").setColored();
        CAR_WHEEL = new ItemPart("car_wheel");

        BMX_BIKE_BODY = new ItemPart("bmx_bike_body").setColored();
        BMX_BIKE_HANDLE_BAR = new ItemPart("bmx_bike_handle_bar").setColored();
        SCOOTER_BODY = new ItemPart("scooter_body").setColored();
        SCOOTER_WHEEL = new ItemPart("scooter_wheel").setColored();
        SCOOTER_HANDLE_BAR = new ItemPart("scooter_handle_bar").setColored();
        MOTORCYCLE_BODY = new ItemPart("motorcycle_body").setColored();
        MOTORCYCLE_HANDLE_BAR = new ItemPart("motorcycle_handle_bar").setColored();

        SNOW_MOBILE_BODY = new ItemPart("snow_mobile").setColored();
        SNOW_MOBILE_HANDLE_BAR = new ItemPart("snow_mobile_handle_bar").setColored();
        SNOW_MOBILE_SKI = new ItemPart("snow_mobile_ski");
        SNOW_MOBILE_TRACKS_MODULE = new ItemPart("snow_mobile_tracks_module");

        SLEIGHT_BODY = new ItemPart("sleight");
        SANTA_SLEIGHT_BODY = new ItemPart("santa_sleight").setColored();

        HIGH_BOOSTER_BOARD = new ItemPart("high_booster_board").setColored();

        COVER_F_BODY = new ItemPart("cover_f_body").setColored();
        COVER_F_STEERING_THING = new ItemPart("cover_f_steering_thing").setColored();
        COVER_P_BODY = new ItemPart("cover_p_body").setColored();
        COVER_P_STEERING_THING = new ItemPart("cover_p_steering_thing").setColored();
        COVER_S_BODY = new ItemPart("cover_s_body").setColored();
        COVER_S_STEERING_THING = new ItemPart("cover_s_steering_thing").setColored();

        TOY_STEAM_LOCOMOTIVE_BODY = new ItemPart("toy_steam_locomotive_body").setColored();
        TOY_TRAIN_CARRIAGE_BODY = new ItemPart("toy_train_carriage_body").setColored();

        for (TrainWheelTypes wheelTypes : TrainWheelTypes.values()) {
            TRAIN_WHEEL[wheelTypes.getId()] = new ItemPart(wheelTypes.getName() + "_wheel");
        }
        DB_ICE_MOTORCART_BODY = new ItemPart("db_ice_motorcart_electric_body").setColored();
        C62_STEAM_LOCOMOTIVE_BODY = new ItemPart("C62_steam_locomotive_body").setColored();
        C62_STEAM_LOCOMOTIVE_TENDER_BODY = new ItemPart("C62_steam_locomotive_tender_body").setColored();
        RBH_TE_2_2_BODY = new ItemPart("rhb_te_2_2_body").setColored();
        DB_ICE_MOTORCART = new ItemModelTrain("db_ice_motorcart_electric", EntityDBIceMotorcart.class);
        RBH_TE_2_2 = new ItemModelTrain("rhb_te_2_2", EntityRbhTE22.class);
        C62_STEAM_LOCOMOTIVE = new ItemModelTrain("C62_steam_locomotive", EntityC62SteamLocomotive.class);
        C62_STEAM_LOCOMOTIVE_TENDER = new ItemModelTrain("C62_steam_locomotive_tender", EntityC62SteamLocomotiveTender.class);
        TRAIN_CONTROLLER = new ItemController();
        for (TrainEngineTypes type : TrainEngineTypes.values()) {
            ENGINE[type.getId()] = new ItemEngine(type);
        }

        CHROMALUX = new ItemChromalux(Color.RED);
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Item> event) {

    }

}
