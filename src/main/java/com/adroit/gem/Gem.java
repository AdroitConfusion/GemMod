package com.adroit.gem;

import com.adroit.gem.util.RegistryHandler;
import com.adroit.gem.world.gen.RubyOreGen;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("gemmod")
@Mod.EventBusSubscriber(modid = Gem.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Gem
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "gemmod";

    public Gem() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        RegistryHandler.init();

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event)
    {
        RubyOreGen.generateOre();
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    }

    private void doClientStuff(final FMLClientSetupEvent event)
    {
    }

    public static final ItemGroup TAB = new ItemGroup("Ruby Mod")
    {
        @Override
        public ItemStack createIcon()
        {
            return new ItemStack(RegistryHandler.RUBY.get());
        }
    };
}
