package com.shkethades.narutomod.client.entity.model;

import com.shkethades.narutomod.entities.AlliedNinjaEntity;
import com.shkethades.narutomod.entities.NinjaEntity;
import net.minecraft.client.renderer.entity.model.BipedModel;

public class AlliedNinjaEntityModel<T extends AlliedNinjaEntity> extends BipedModel<T> {
    public AlliedNinjaEntityModel() {
        this(0.0F, false);
    }

    public AlliedNinjaEntityModel(float par1, boolean par2) {
        super(par1, 0.0F, 64, par2 ? 32 : 64);

        this.textureWidth = 64;
        this.textureHeight = 64;

    }
}

