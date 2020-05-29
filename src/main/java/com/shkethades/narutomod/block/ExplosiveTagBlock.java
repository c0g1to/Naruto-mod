package com.shkethades.narutomod.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.Entity;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ExplosiveTagBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

    public ExplosiveTagBlock() {
        super(Properties.from(Blocks.WHITE_WOOL));
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        createExplosion(worldIn, pos);
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        createExplosion(worldIn, pos);
        super.onExplosionDestroy(worldIn, pos, explosionIn);
    }

    private void createExplosion(World worldIn, BlockPos pos) {
        if(!worldIn.isRemote()) {
            ServerWorld serverWorld = (ServerWorld) worldIn;
            serverWorld.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 4.0f, true, Explosion.Mode.DESTROY);
        }
    }
}
