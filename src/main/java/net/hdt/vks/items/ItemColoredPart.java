package net.hdt.vks.items;

import net.hdt.vks.VehiclesAndTheKitchenSink;
import net.minecraft.item.Item;

import static net.hdt.vks.Reference.MOD_ID;

public class ItemColoredPart extends Item {
    public ItemColoredPart(String id) {
        this.setUnlocalizedName(id);
        this.setRegistryName(MOD_ID, id);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setCreativeTab(VehiclesAndTheKitchenSink.CREATIVE_TAB);
    }

}
