package cn.aatu.fixes;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.NeoForge;

@Mod(ServerFixesMod.MOD_ID)
public final class ServerFixesMod {
    public static final String MOD_ID = "aatu_server_fixes";

    public ServerFixesMod(IEventBus modBus, ModContainer container) {
        container.registerConfig(ModConfig.Type.COMMON, FixesConfig.SPEC, "aatu-server-fixes.toml");
        modBus.addListener(this::loadConfig);
        if (FMLEnvironment.dist == Dist.DEDICATED_SERVER) {
            new SnowyShepherdFix().register(modBus, NeoForge.EVENT_BUS);
        }
    }

    private void loadConfig(ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() == FixesConfig.SPEC) FixesConfig.load();
    }
}
