package com.shkethades.narutomod.item;

import com.shkethades.narutomod.NarutoMod;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ThrowableItem extends Item {
    public ThrowableItem() {
        super(new Properties().group(NarutoMod.GROUP).maxStackSize(16));
    }

    public ThrowableItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return true;
    }

    @Nullable
    @Override
    public Entity createEntity(World world, Entity location, ItemStack itemstack) {
        return null;    // TODO: 6/7/20       
    }
}
