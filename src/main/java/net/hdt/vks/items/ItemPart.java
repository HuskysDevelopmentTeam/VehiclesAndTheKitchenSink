package net.hdt.vks.items;

import net.hdt.huskylib2.items.ItemMod;
import net.hdt.vks.VehiclesAndTheKitchenSink;

import static net.hdt.vks.Reference.MOD_ID;

public class ItemPart extends ItemMod {

    private boolean colored = false;

    public ItemPart(String id) {
        super(id, MOD_ID);
        this.setCreativeTab(VehiclesAndTheKitchenSink.CREATIVE_TAB);
    }

    public ItemPart setColored()
    {
        this.colored = true;
        return this;
    }

    public boolean isColored()
    {
        return colored;
    }

}
