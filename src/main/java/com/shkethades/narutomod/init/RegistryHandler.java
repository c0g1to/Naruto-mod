package com.shkethades.narutomod.init;

import com.shkethades.narutomod.NarutoMod;
import com.shkethades.narutomod.block.BurstSealBlock;
import com.shkethades.narutomod.item.ThrowableItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(NarutoMod.MOD_ID)
public class RegistryHandler {

    public static final Item shuriken = null;
    public static final Item kunai = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ThrowableItem().setRegistryName("shuriken"));
        event.getRegistry().register(new ThrowableItem().setRegistryName("kunai"));
    }
}

//public class RegistryHandler {
//    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, NarutoMod.MOD_ID);
//    public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, NarutoMod.MOD_ID);
//
//    public static void init() {
//        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
//
//        ITEMS.register("shuriken", ThrowableItem::new);
//
//        ITEMS.register("kunai", ThrowableItem::new);
//
//        BLOCKS.register("burst_seal", BurstSealBlock::new);
//        //ITEMS.register("burst_seal", () -> new BlockItem(BURST_SEAL, new Item.Properties().group(ItemGroup.COMBAT)));//TODO
//
//        ITEMS.register(bus);
//        BLOCKS.register(bus);
//    }
//
//    //public static final Block BURST_SEAL = null;//TODO
//}