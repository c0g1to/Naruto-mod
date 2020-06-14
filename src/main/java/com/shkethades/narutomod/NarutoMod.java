package com.shkethades.narutomod;

import com.shkethades.narutomod.client.entity.render.AlliedNinjaEntityRender;
import com.shkethades.narutomod.client.entity.render.NinjaEntityRender;
import com.shkethades.narutomod.init.NarutoBlocks;
import com.shkethades.narutomod.init.NarutoEntities;
import com.shkethades.narutomod.init.NarutoItems;
import com.shkethades.narutomod.item.FoodItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(NarutoMod.MOD_ID)
@Mod.EventBusSubscriber(modid = NarutoMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NarutoMod {
    public static NarutoMod instance;
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "narutomod";
    public static final ItemGroup GROUP = new ItemGroup(NarutoMod.MOD_ID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(FoodItem.RAMEN);
        }
    };

    public NarutoMod() {
        instance = this;
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        NarutoBlocks.BLOCKS.register(bus);
        NarutoItems.ITEMS.register(bus);
        NarutoEntities.ENTITIES.register(bus);

        bus.addListener(this::setupCommon);
        bus.addListener(this::setupClient);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setupCommon(final FMLCommonSetupEvent event) { }

    private void setupClient(final FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(NarutoEntities.NINJA_ENTITY.get(), NinjaEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(NarutoEntities.ALLIED_NINJA_ENTITY.get(), AlliedNinjaEntityRender::new);
        RenderingRegistry.registerEntityRenderingHandler(NarutoEntities.SHURIKEN_ENTITY.get(), manager -> new SpriteRenderer<>(manager, Minecraft.getInstance().getItemRenderer()));
    }

    public void serverStart(FMLServerStartingEvent event) { }
}
