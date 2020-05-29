package com.shkethades.narutomod.init;

import com.shkethades.narutomod.NarutoMod;
import com.shkethades.narutomod.item.NarutoArmorItem;
import com.shkethades.narutomod.item.FoodItem;
import com.shkethades.narutomod.item.MeleeItem;
import com.shkethades.narutomod.item.ThrowableItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(NarutoMod.MOD_ID)
public class NarutoItems {

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ThrowableItem().setRegistryName("shuriken"));
        event.getRegistry().register(new ThrowableItem().setRegistryName("kunai"));

        event.getRegistry().register(new SwordItem(MeleeItem.Tiers.STEEL, 3, -1.6f,
                new MeleeItem.Properties().group(NarutoMod.GROUP)).setRegistryName("katana"));

        event.getRegistry().register(new ArmorItem(NarutoArmorItem.MaterialTiers.SHINOBI, EquipmentSlotType.HEAD,
                new NarutoArmorItem.Properties().group(NarutoMod.GROUP)).setRegistryName("headband"));
        event.getRegistry().register(new ArmorItem(NarutoArmorItem.MaterialTiers.SHINOBI, EquipmentSlotType.CHEST,
                new NarutoArmorItem.Properties().group(NarutoMod.GROUP)).setRegistryName("flak_jacket"));
        event.getRegistry().register(new ArmorItem(NarutoArmorItem.MaterialTiers.ANBU, EquipmentSlotType.HEAD,
                new NarutoArmorItem.Properties().group(NarutoMod.GROUP)).setRegistryName("anbu_mask"));
        event.getRegistry().register(new ArmorItem(NarutoArmorItem.MaterialTiers.ANBU, EquipmentSlotType.CHEST,
                new NarutoArmorItem.Properties().group(NarutoMod.GROUP)).setRegistryName("anbu_vest"));

        event.getRegistry().register(FoodItem.RAMEN.setRegistryName("ramen"));
        event.getRegistry().register(FoodItem.BATTLE_PILL.setRegistryName("battle_pill"));
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new BlockItem(NarutoBlocks.EXPLOSIVE_TAG, new Item.Properties().maxStackSize(16).group(NarutoMod.GROUP)).setRegistryName("explosive_tag"));
    }
}