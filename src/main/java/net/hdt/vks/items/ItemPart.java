package net.hdt.vks.items;

import net.hdt.vks.VehiclesAndTheKitchenSink;
import net.minecraft.item.Item;

import static net.hdt.vks.Reference.MOD_ID;

public class ItemPart extends Item {
    public ItemPart(String id) {
        this.setUnlocalizedName(id);
        this.setRegistryName(MOD_ID, id);
        this.setCreativeTab(VehiclesAndTheKitchenSink.CREATIVE_TAB);
    }
}
