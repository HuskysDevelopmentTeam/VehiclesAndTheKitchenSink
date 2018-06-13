package net.hdt.vks.blocks;

import net.hdt.huskylib2.blocks.BlockMod;
import net.hdt.vks.VehiclesAndTheKitchenSink;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;

import static net.hdt.vks.Reference.MOD_ID;

/**
 * Author: MrCrayfish
 */
public class BlockObject extends BlockMod {

    public BlockObject(Material material, String id) {
        super(material, MOD_ID, id);
        this.setCreativeTab(VehiclesAndTheKitchenSink.CREATIVE_TAB);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

}
