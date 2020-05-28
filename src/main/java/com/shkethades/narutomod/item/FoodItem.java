package com.shkethades.narutomod.item;

import com.shkethades.narutomod.NarutoMod;
import com.shkethades.narutomod.util.KeyboardHelper;
import net.java.games.input.Keyboard;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import java.util.List;

public class FoodItem extends Item {

    protected String description;

    public FoodItem(Item.Properties properties, String descriptionIn) {
        super(properties.group(NarutoMod.GROUP));
        this.description = descriptionIn;
    }

    public static Item RAMEN = new FoodItem(new Item.Properties().group(NarutoMod.GROUP)
            .food(new Food.Builder().hunger(8).saturation(8.0f)
                    .effect(() -> new EffectInstance(Effects.REGENERATION, 6000, 0), 0.5f)
                    .effect(() -> new EffectInstance(Effects.SPEED, 6000, 0), 0.5f)
                    .effect(() -> new EffectInstance(Effects.HASTE, 6000, 0), 0.5f)
                    .build()),"Regeneration (5:00)\nSpeed (5:00)\nHaste (5:00)");
    public static Item BATTLE_PILL = new FoodItem(new Item.Properties().group(NarutoMod.GROUP)
            .food(new Food.Builder().hunger(1).saturation(0.2f).setAlwaysEdible().fastToEat()
                    .effect(() -> new EffectInstance(Effects.SPEED, 1600, 2), 0.8f)
                    .effect(() -> new EffectInstance(Effects.JUMP_BOOST, 1200, 2), 0.8f)
                    .effect(() -> new EffectInstance(Effects.STRENGTH, 800, 1), 0.8f)
                    .effect(() -> new EffectInstance(Effects.HUNGER, 1200, 1), 0.6f)
                    .effect(() -> new EffectInstance(Effects.WITHER, 200, 1), 0.2f)
                    .build()), "Speed III (1:20)\nJump boost III (1:00)\nStrength II (0:40)\nHunger II (1:00)\nWither II (0:10)");

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if(KeyboardHelper.isHoldingShift()) {
            tooltip.add(new StringTextComponent("§9" + description));
        }
        else {
            tooltip.add(new StringTextComponent("§9Hold §l§o§5Shift §r§9for more details"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
