package net.hdt.vks.recipes;

import com.google.common.collect.Lists;
import com.mrcrayfish.vehicle.Reference;
import net.hdt.vks.items.ItemChromalux;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.awt.*;
import java.util.List;

/**
 * Author: MrCrayfish
 */
public class RecipeColorChromalux extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe
{
    public RecipeColorChromalux()
    {
        this.setRegistryName(new ResourceLocation(Reference.MOD_ID, "color_chromalux"));
    }

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn)
    {
        ItemStack sprayCan = ItemStack.EMPTY;
        List<ItemStack> dyes = Lists.newArrayList();

        for (int i = 0; i < inv.getSizeInventory(); ++i)
        {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty())
            {
                if (stack.getItem() instanceof ItemChromalux)
                {
                    if (!sprayCan.isEmpty())
                    {
                        return false;
                    }
                    sprayCan = stack;
                }
                else
                {
                    if (!net.minecraftforge.oredict.DyeUtils.isDye(stack))
                    {
                        return false;
                    }
                    dyes.add(stack);
                }
            }
        }

        return !sprayCan.isEmpty() && !dyes.isEmpty();
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv)
    {
        ItemStack sprayCanStack = ItemStack.EMPTY;
        ItemChromalux sprayCan = null;
        int[] combinedValues = new int[3];
        int combinedColor = 0;
        int colorCount = 0;

        for (int k = 0; k < inv.getSizeInventory(); ++k)
        {
            ItemStack stack = inv.getStackInSlot(k);
            if (!stack.isEmpty())
            {
                if (stack.getItem() instanceof ItemChromalux)
                {
                    sprayCan = (ItemChromalux) stack.getItem();
                    if (!sprayCanStack.isEmpty())
                    {
                        return ItemStack.EMPTY;
                    }
                    sprayCanStack = stack.copy();
                    sprayCanStack.setCount(1);

                    if (sprayCan.hasColor(stack))
                    {
                        int color = sprayCan.getColor(sprayCanStack);
                        float red = (float)(color >> 16 & 255) / 255.0F;
                        float green = (float)(color >> 8 & 255) / 255.0F;
                        float blue = (float)(color & 255) / 255.0F;
                        combinedColor = (int)((float)combinedColor + Math.max(red, Math.max(green, blue)) * 255.0F);
                        combinedValues[0] = (int)((float)combinedValues[0] + red * 255.0F);
                        combinedValues[1] = (int)((float)combinedValues[1] + green * 255.0F);
                        combinedValues[2] = (int)((float)combinedValues[2] + blue * 255.0F);
                        colorCount++;
                    }
                }
                else
                {
                    if (!net.minecraftforge.oredict.DyeUtils.isDye(stack))
                    {
                        return ItemStack.EMPTY;
                    }

                    float[] color = net.minecraftforge.oredict.DyeUtils.colorFromStack(stack).get().getColorComponentValues();
                    int red = (int)(color[0] * 255.0F);
                    int green = (int)(color[1] * 255.0F);
                    int blue = (int)(color[2] * 255.0F);
                    combinedColor += Math.max(red, Math.max(green, blue));
                    combinedValues[0] += red;
                    combinedValues[1] += green;
                    combinedValues[2] += blue;
                    colorCount++;
                }
            }
        }

        if (sprayCan == null)
        {
            return ItemStack.EMPTY;
        }
        else
        {
            int red = combinedValues[0] / colorCount;
            int green = combinedValues[1] / colorCount;
            int blue = combinedValues[2] / colorCount;
            float averageColor = (float)combinedColor / (float)colorCount;
            float maxValue = (float)Math.max(red, Math.max(green, blue));
//            red = (int)((float)red * averageColor / maxValue);
//            green = (int)((float)green * averageColor / maxValue);
//            blue = (int)((float)blue * averageColor / maxValue);
            Color finalColor = new Color(red, green, blue);
            sprayCan.setColor(sprayCanStack, finalColor);
            return sprayCanStack;
        }
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return width * height >= 2;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return ItemStack.EMPTY;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv)
    {
        NonNullList<ItemStack> remainingItems = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        for (int i = 0; i < remainingItems.size(); ++i)
        {
            ItemStack stack = inv.getStackInSlot(i);
            remainingItems.set(i, net.minecraftforge.common.ForgeHooks.getContainerItem(stack));
        }
        return remainingItems;
    }

    @Override
    public boolean isDynamic()
    {
        return true;
    }
}
