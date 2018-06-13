package net.hdt.vks.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/**
 * Implement this interface in blocks that should be able to interact with the Color Scanner.
 */
public interface IColorHolder {

    /**
     * Get's called when the block this interface is implemented on gets right-clicked with the Color Scanner.
     *
     * @param world the World
     * @param pos   the BlockPos
     * @param state the IBlockState
     * @param side  the side of the block that was clicked
     * @param hit   the hit vector of the side that was clicked
     * @param color the color that the Color Scanner currently holds
     */
    void setColor(World world, BlockPos pos, IBlockState state, EnumFacing side, Vec3d hit, int color);

    /**
     * Get's called when the block this interface is implemented on gets shift-right-clicked with the Color Scanner.
     *
     * @param world the World
     * @param pos   the Blockpos
     * @param state the IBlockState
     * @param side  the side of the block that was clicked
     * @param hit   the hit vector of the side that was clicked
     * @return the color to save in the Color Scanner
     */
    int getColor(World world, BlockPos pos, IBlockState state, EnumFacing side, Vec3d hit);

}