package net.hdt.vks.items;

import net.hdt.huskylib2.items.ItemMod;
import net.hdt.vks.VehiclesAndTheKitchenSink;

import static net.hdt.vks.Reference.MOD_ID;

public class ItemColoredPart extends ItemMod {

    public ItemColoredPart(String id) {
        super(id, MOD_ID);
        this.setCreativeTab(VehiclesAndTheKitchenSink.CREATIVE_TAB);
    }

}