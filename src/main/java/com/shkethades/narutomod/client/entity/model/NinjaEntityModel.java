package com.shkethades.narutomod.client.entity.model;

import com.shkethades.narutomod.entity.NinjaEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;

public class NinjaEntityModel<T extends NinjaEntity> extends BipedModel<T> {
    public NinjaEntityModel() {
        this(0.0F, false);
    }

    public NinjaEntityModel(float par1, boolean par2) {
        super(par1, 0.0F, 64, par2 ? 32 : 64);

        this.textureWidth = 64;
        this.textureHeight = 64;

    }
}
