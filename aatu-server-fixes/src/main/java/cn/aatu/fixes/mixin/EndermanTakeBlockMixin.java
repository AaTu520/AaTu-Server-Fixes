package cn.aatu.fixes.mixin;

import cn.aatu.fixes.FixesConfig;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.world.entity.monster.EnderMan$EndermanTakeBlockGoal")
public abstract class EndermanTakeBlockMixin {
    @WrapOperation(method = "tick", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z"))
    private boolean aatuFixes$allowedBlock(BlockState state, TagKey<Block> tag, Operation<Boolean> original) {
        boolean allowed = original.call(state, tag);
        if (!allowed || tag != BlockTags.ENDERMAN_HOLDABLE || !FixesConfig.restrictEndermanBlocks()) return allowed;
        // Filter before removal; changing only the carried state would still destroy the terrain.
        return state.is(Blocks.MELON) || state.is(Blocks.PUMPKIN);
    }
}
