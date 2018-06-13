package net.hdt.vks.blocks;

import net.hdt.huskylib2.blocks.BlockFacing;
import net.minecraft.block.material.Material;

import static net.hdt.vks.Reference.MOD_ID;

public class BlockAsphalt extends BlockFacing {

    public BlockAsphalt(String name) {
        super(Material.ROCK, MOD_ID, name);
    }

}
