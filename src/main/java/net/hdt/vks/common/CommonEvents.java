package net.hdt.vks.common;

import net.hdt.vks.utils.ItemNBTHelper;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class CommonEvents {

    private static final String TAG_DYE = "vks:chromalux";

    @SubscribeEvent
    public void onAnvilUpdate(AnvilUpdateEvent event) {
        ItemStack left = event.getLeft();
        ItemStack right = event.getRight();

        if (!left.isEmpty() && !right.isEmpty() && right.getItem() == Items.DYE) {
            ItemStack out = left.copy();
            String name = event.getName();
            if(!name.trim().isEmpty())
                out.setStackDisplayName(name.trim());

            ItemNBTHelper.setInt(out, TAG_DYE, right.getItemDamage());
            event.setOutput(out);
            event.setCost(3);
        }
    }

}
