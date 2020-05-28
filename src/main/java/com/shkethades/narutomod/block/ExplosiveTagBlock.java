package com.shkethades.narutomod.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ExplosiveTagBlock extends Block {
    public ExplosiveTagBlock() {
        super(Properties.from(Blocks.WHITE_CARPET));
    }

//    @Override
//    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
//        //worldIn.createExplosion()
//        super.onEntityWalk(worldIn, pos, entityIn);
//    }
}
