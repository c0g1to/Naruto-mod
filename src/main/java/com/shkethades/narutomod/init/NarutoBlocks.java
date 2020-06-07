package com.shkethades.narutomod.init;

import com.shkethades.narutomod.NarutoMod;
import com.shkethades.narutomod.block.ExplosiveTagBlock;
import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NarutoBlocks {

    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, NarutoMod.MOD_ID);

    public static final RegistryObject<Block> EXPLOSIVE_TAG = BLOCKS.register("explosive_tag", ExplosiveTagBlock::new);
}
