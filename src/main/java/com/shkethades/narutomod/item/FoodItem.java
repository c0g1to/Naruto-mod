package com.shkethades.narutomod.item;

import com.shkethades.narutomod.NarutoMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FoodItem extends Item {

    public FoodItem() {
        super(new Properties().group(NarutoMod.GROUP));
    }

    public static Item RAMEN = new Item(new Item.Properties().group(NarutoMod.GROUP)
            .food(new Food.Builder().hunger(8).saturation(8.0f)
                    .effect(() -> new EffectInstance(Effects.REGENERATION, 6000, 0), 0.5f)
                    .effect(() -> new EffectInstance(Effects.SPEED, 6000, 0), 0.5f)
                    .effect(() -> new EffectInstance(Effects.HASTE, 6000, 0), 0.5f)
                    .build()));
    public static Item BATTLE_PILL = new Item(new Item.Properties().group(NarutoMod.GROUP)
            .food(new Food.Builder().hunger(1).saturation(0.2f).setAlwaysEdible().fastToEat()
                    .effect(() -> new EffectInstance(Effects.SPEED, 1600, 2), 0.8f)
                    .effect(() -> new EffectInstance(Effects.JUMP_BOOST, 1200, 2), 0.8f)
                    .effect(() -> new EffectInstance(Effects.STRENGTH, 800, 1), 0.8f)
                    .effect(() -> new EffectInstance(Effects.HUNGER, 1200, 1), 0.6f)
                    .effect(() -> new EffectInstance(Effects.WITHER, 200, 1), 0.2f)
                    .build()));
}
