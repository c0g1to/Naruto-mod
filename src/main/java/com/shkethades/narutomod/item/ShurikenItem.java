package com.shkethades.narutomod.item;

import com.shkethades.narutomod.NarutoMod;
import com.shkethades.narutomod.entity.ShurikenEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.*;
import net.minecraft.world.World;

public class ShurikenItem extends Item {
    public ShurikenItem() {
        super(new Properties().group(NarutoMod.GROUP).maxStackSize(16));
    }

    public ShurikenItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!worldIn.isRemote) {
            ShurikenEntity projectileEntity = new ShurikenEntity(worldIn, playerIn);
            projectileEntity.setItem(itemStack);
            projectileEntity.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
            worldIn.addEntity(projectileEntity);
        }

        playerIn.addStat(Stats.ITEM_USED.get(this));
        if (!playerIn.abilities.isCreativeMode) {
            itemStack.shrink(1);
        }

        return ActionResult.resultSuccess(itemStack);
    }
}
