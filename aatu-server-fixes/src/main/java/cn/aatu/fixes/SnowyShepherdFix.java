package cn.aatu.fixes;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.AddPackFindersEvent;

public final class SnowyShepherdFix implements ServerFix {
    @Override
    public String id() { return "snowy_shepherd_house"; }

    @Override
    public void register(IEventBus modBus, IEventBus gameBus) {
        modBus.addListener(this::addPack);
    }

    private void addPack(AddPackFindersEvent event) {
        if (event.getPackType() == PackType.SERVER_DATA && FixesConfig.SNOWY_SHEPHERD.get()) {
            event.addPackFinders(
                    ResourceLocation.fromNamespaceAndPath(ServerFixesMod.MOD_ID, "packs/snowy_shepherd"),
                    PackType.SERVER_DATA,
                    Component.literal("Aatu: Snowy Shepherd House Fix"),
                    PackSource.BUILT_IN, true, Pack.Position.TOP);
        }
    }
}
