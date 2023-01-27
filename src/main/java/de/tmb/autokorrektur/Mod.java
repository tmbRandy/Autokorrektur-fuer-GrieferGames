package de.tmb.autokorrektur;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

// The value here should match an entry in the META-INF/mods.toml file
@net.minecraftforge.fml.common.Mod("autokorrektur")
public class Mod
{
    private final Map<String, String> replacements = new HashMap<String, String>() {
        {
            put("7r", "/r");
            put("(r", "/r");
            put("t/r", "/r");

            put("7ec", "/ec");
            put("(ec", "/ec");
            put("t/ec", "/ec");

            put("7msg", "/msg");
            put("(msg", "/msg");
            put("t/msg", "/msg");

            put("7craft", "/craft");
            put("(craft", "/craft");
            put("t/craft", "/craft");

            put("7invsee", "/invsee");
            put("(invsee", "/invsee");
            put("t/invsee", "/invsee");

            put("7booster", "/booster");
            put("(booster", "/booster");
            put("t/booster", "/booster");

            put("7cooldowns", "/cooldowns");
            put("(cooldowns", "/cooldowns");
            put("t/cooldowns", "/cooldowns");

            put("version", "v3rsion");
            put("Version", "V3rsion");

            put("/p t ", "/p trust ");

            put("/cb1", "/switch cb1");
            put("/cb2", "/switch cb2");
            put("/cb3", "/switch cb3");
            put("/cb4", "/switch cb4");
            put("/cb5", "/switch cb5");
            put("/cb6", "/switch cb6");
            put("/cb7", "/switch cb7");
            put("/cb8", "/switch cb8");
            put("/cb9", "/switch cb9");
            put("/cb10", "/switch cb10");
            put("/cb11", "/switch cb11");
            put("/cb12", "/switch cb12");
            put("/cb13", "/switch cb13");
            put("/cb14", "/switch cb14");
            put("/cb15", "/switch cb15");
            put("/cb16", "/switch cb16");
            put("/cb17", "/switch cb17");
            put("/cb18", "/switch cb18");
            put("/cb19", "/switch cb19");
            put("/cb20", "/switch cb20");
            put("/cb21", "/switch cb21");
            put("/cb22", "/switch cb22");
            put("/nature", "/switch nature");
            put("/evil", "/switch cbevil");
            put("/extreme", "/switch extreme");
        }
    };

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Mod() {
        // Register the setup method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }



    @SubscribeEvent
    public void clientChatEvent(ClientChatEvent e) {

        String message = e.getMessage();

        if(message.length() > 100 && (message.toLowerCase().startsWith("/msg ") || message.toLowerCase().startsWith("/r "))) {
            String[] messageArray;

            messageArray = message.split("(?<=\\G.{" + 97 + "})");

            for (int i = 1; i < messageArray.length; i++) {
                messageArray[i] = "/r " + messageArray[i];
            }


            for (int i = 0; i < messageArray.length; i++) {
                Minecraft.getInstance().screen.sendMessage(messageArray[i]);
            }

            message = "";
            e.setCanceled(true);
        }

        for (Map.Entry<String, String> entry : replacements.entrySet()) {
            message = message.replace(entry.getKey(), entry.getValue());
        }


        if(!e.isCanceled()) {
            e.setMessage(message);
        } else {
            e.setMessage("");
        }
    }

    private void setup(final FMLCommonSetupEvent event)
    { }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        InterModComms.sendTo("autokorrektur", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void processIMC(final InterModProcessEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    @net.minecraftforge.fml.common.Mod.EventBusSubscriber(bus= net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {

        }
    }
}
