package cn.aatu.fixes;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class FixesConfig {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.BooleanValue SNOWY_SHEPHERD;
    private static final ModConfigSpec.BooleanValue ENDERMAN_MELON_PUMPKIN_ONLY;
    private static volatile boolean restrictEndermanBlocks = true;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.comment("每个修复可独立开关；修改后重启生效。")
                .push("fixes");
        SNOWY_SHEPHERD = builder.comment("修复雪原村庄牧羊人小屋的拼图入口标记；仅影响此后生成的结构。")
                .worldRestart().define("snowyShepherdHouse", true);
        ENDERMAN_MELON_PUMPKIN_ONLY = builder.comment("末影人只能拾起西瓜和普通南瓜，仍遵循生物破坏规则。")
                .worldRestart().define("endermanMelonPumpkinOnly", true);
        builder.pop();
        SPEC = builder.build();
    }

    private FixesConfig() {}

    static void load() { restrictEndermanBlocks = ENDERMAN_MELON_PUMPKIN_ONLY.get(); }

    public static boolean restrictEndermanBlocks() { return restrictEndermanBlocks; }
}
