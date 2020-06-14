package com.shkethades.narutomod.init;

import com.shkethades.narutomod.NarutoMod;
import com.shkethades.narutomod.entity.AlliedNinjaEntity;
import com.shkethades.narutomod.entity.NinjaEntity;
import com.shkethades.narutomod.entity.ShurikenEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.SnowballEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;



public class NarutoEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = new DeferredRegister<>(ForgeRegistries.ENTITIES, NarutoMod.MOD_ID);

    public static final RegistryObject<EntityType<NinjaEntity>> NINJA_ENTITY = ENTITIES.register("ninja_entity", () -> EntityType.Builder.<NinjaEntity>create(NinjaEntity::new, EntityClassification.CREATURE).size(0.6F, 1.9F).build("ninja_entity"));
    public static final RegistryObject<EntityType<AlliedNinjaEntity>> ALLIED_NINJA_ENTITY = ENTITIES.register("allied_ninja_entity", () -> EntityType.Builder.<AlliedNinjaEntity>create(AlliedNinjaEntity::new, EntityClassification.CREATURE).size(0.6F, 1.9F).build("allied_ninja_entity"));

    public static final RegistryObject<EntityType<ShurikenEntity>> SHURIKEN_ENTITY = ENTITIES.register("shuriken", () -> EntityType.Builder.<ShurikenEntity>create(ShurikenEntity::new, EntityClassification.MISC).size(0.25F, 0.25F).build("shuriken"));
}