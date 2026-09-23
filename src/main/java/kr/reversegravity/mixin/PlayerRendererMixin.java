package kr.reversegravity.mixin;

import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {
    // Player-model inversion will be refined after the 26.3 mappings compile check.
}
