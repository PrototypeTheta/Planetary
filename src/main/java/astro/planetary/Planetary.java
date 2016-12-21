package astro.planetary;

import astro.planetary.client.popup.OverlayHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(
        modid = Planetary.MOD_ID,
        name = Planetary.MOD_NAME,
        version = Planetary.VERSION
)
public class Planetary {

    public static final String MOD_ID = "planetary";
    public static final String MOD_NAME = "Planetary";
    public static final String VERSION = "1.0.0";

    public static SimpleNetworkWrapper NETWORK;

    @Instance
    public static Planetary INSTANCE;


    public Planetary() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(OverlayHandler.INSTANCE);
        NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel("planetary");
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        PlanetaryContent.register();
    }

}