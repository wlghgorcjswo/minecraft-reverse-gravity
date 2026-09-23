package kr.reversegravity.mixin;

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
        // Vanilla gravity has already pulled downward. Add enough upward acceleration
        // to cancel it and produce the same acceleration in the opposite direction.
        self.setDeltaMovement(velocity.x, velocity.y + 0.16D, velocity.z);

        // Ceiling contact acts as our inverted ground for the first prototype.
        if (self.horizontalCollision) {
            self.resetFallDistance();
        }
    }
}
