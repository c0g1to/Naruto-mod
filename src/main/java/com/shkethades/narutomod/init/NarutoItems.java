package com.shkethades.narutomod.init;

import com.shkethades.narutomod.NarutoMod;
import com.shkethades.narutomod.item.FoodItem;
import com.shkethades.narutomod.item.MeleeItem;
import com.shkethades.narutomod.item.ThrowableItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
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
        event.getRegistry().register(FoodItem.RAMEN.setRegistryName("ramen"));
        event.getRegistry().register(FoodItem.BATTLE_PILL.setRegistryName("battle_pill"));
        event.getRegistry().register(new SwordItem(MeleeItem.Tiers.STEEL, 3, -1.6f,
                new Item.Properties().group(NarutoMod.GROUP)).setRegistryName("katana"));
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new BlockItem(NarutoBlocks.EXPLOSIVE_TAG, new Item.Properties().maxStackSize(16).group(NarutoMod.GROUP)).setRegistryName("explosive_tag"));
    }
}