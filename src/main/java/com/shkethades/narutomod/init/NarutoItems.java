package com.shkethades.narutomod.init;

import com.shkethades.narutomod.NarutoMod;
import com.shkethades.narutomod.item.ThrowableItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(NarutoMod.MOD_ID)
public class NarutoItems {

    //public static final Item shuriken = null;
    //public static final Item kunai = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ThrowableItem().setRegistryName("shuriken"));
        event.getRegistry().register(new ThrowableItem().setRegistryName("kunai"));
    }

    @SubscribeEvent
    public static void registerBlockItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new BlockItem(NarutoBlocks.BURST_SEAL, new Item.Properties().maxStackSize(16).group(ItemGroup.REDSTONE)).setRegistryName("burst_seal"));
    }
}