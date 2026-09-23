package kr.reversegravity.mixin;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(method = "aiStep", at = @At("TAIL"))
    private void reverseGravity$tick(CallbackInfo ci) {
        Player self = (Player) (Object) this;
        if (self.isSpectator() || self.getAbilities().flying) return;

        Vec3 velocity = self.getDeltaMovement();

        // Do not use the full air acceleration in fluids. Vanilla fluid movement already
        // applies drag/buoyancy, so only counter/reverse a much smaller gravity component.
        double upwardCorrection;
        if (self.isInWater() || self.isEyeInFluid(FluidTags.WATER)) {
            upwardCorrection = 0.025D;
        } else if (self.isInLava() || self.isEyeInFluid(FluidTags.LAVA)) {
            upwardCorrection = 0.015D;
        } else {
            upwardCorrection = 0.16D;
        }

        self.setDeltaMovement(velocity.x, velocity.y + upwardCorrection, velocity.z);
        self.resetFallDistance();
    }
}
