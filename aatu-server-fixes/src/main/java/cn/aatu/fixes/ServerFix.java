package cn.aatu.fixes;

import net.neoforged.bus.api.IEventBus;

/** Each fix owns its hooks and resources so future fixes can be enabled independently. */
public interface ServerFix {
    String id();
    void register(IEventBus modBus, IEventBus gameBus);
}
