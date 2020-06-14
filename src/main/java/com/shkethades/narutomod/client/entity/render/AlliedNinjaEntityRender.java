package com.shkethades.narutomod.client.entity.render;

import com.shkethades.narutomod.NarutoMod;
import com.shkethades.narutomod.client.entity.model.AlliedNinjaEntityModel;
import com.shkethades.narutomod.entity.AlliedNinjaEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class AlliedNinjaEntityRender extends MobRenderer<AlliedNinjaEntity, AlliedNinjaEntityModel<AlliedNinjaEntity>> {
    protected static final ResourceLocation TEXTURE = new ResourceLocation(NarutoMod.MOD_ID,
            "textures/entity/allied_ninja_entity.png");

    public AlliedNinjaEntityRender(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new AlliedNinjaEntityModel<AlliedNinjaEntity>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(AlliedNinjaEntity entity) { return TEXTURE; }
}
