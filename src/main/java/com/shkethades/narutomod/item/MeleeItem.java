package com.shkethades.narutomod.item;

import com.shkethades.narutomod.NarutoMod;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

import java.util.function.Supplier;

public class MeleeItem extends Item {

    public MeleeItem () {
        super(new Item.Properties().group(NarutoMod.GROUP));
    }

    public static enum Tiers implements IItemTier {

        STEEL(2, 600, 9.0f, 2.0f, 16, () -> {
            return Ingredient.fromItems(Items.IRON_INGOT);
        });

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final float attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repairMaterial;

        private Tiers(int harvestLevel,
                     int maxUses,
                     float efficiency,
                     float attackDamage,
                     int enchantability,
                     Supplier<Ingredient> repairMaterial
        ) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = new LazyValue<>(repairMaterial);
        }

        @Override
        public int getMaxUses() {
            return maxUses;
        }

        @Override
        public float getEfficiency() {
            return efficiency;
        }

        @Override
        public float getAttackDamage() {
            return attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return repairMaterial.getValue();
        }
    }
}
