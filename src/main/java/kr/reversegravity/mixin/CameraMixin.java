package kr.reversegravity.mixin;

import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Camera.class)
public abstract class CameraMixin {
    // Client camera inversion will be refined after the 26.3 mappings compile check.
}
