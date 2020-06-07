package com.shkethades.narutomod.init;

import com.shkethades.narutomod.NarutoMod;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class NarutoEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, NarutoMod.MOD_ID);

    // TODO: 6/7/20  public static final RegistryObject<EntityType<>> SHURIKEN = ENTITY_TYPES.register(); 
}
