package com.shkethades.narutomod;

import com.shkethades.narutomod.init.RegistryHandler;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(NarutoMod.MOD_ID)
public class NarutoMod
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "narutomod";
    public static NarutoMod instance;

    public NarutoMod() {
        instance = this;
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(this::setupCommon);
        bus.addListener(this::setupClient);

        //RegistryHandler.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setupCommon(final FMLCommonSetupEvent event) { }

    private void setupClient(final FMLClientSetupEvent event) { }
}
