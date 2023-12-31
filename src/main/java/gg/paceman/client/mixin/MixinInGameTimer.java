package gg.paceman.client.mixin;

import com.redlimerl.speedrunigt.timer.InGameTimer;
import gg.paceman.client.PacemanGG;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(InGameTimer.class)
public class MixinInGameTimer {

    @Shadow(remap = false) static @NotNull InGameTimer INSTANCE;

    @Inject(method = "complete(JZ)V", at = @At(value = "INVOKE", target = "Lcom/redlimerl/speedrunigt/therun/TheRunRequestHelper;submitTimerData(Lcom/redlimerl/speedrunigt/timer/InGameTimer;)V", shift = At.Shift.AFTER, remap = false), remap = false)
    private static void onComplete(CallbackInfo ci) {
        PacemanGG.updateTimerData(INSTANCE, false);
    }

    @Inject(method = "leave", at = @At(value = "INVOKE", target = "Lcom/redlimerl/speedrunigt/therun/TheRunRequestHelper;updateTimerData(Lcom/redlimerl/speedrunigt/timer/InGameTimer;Lcom/redlimerl/speedrunigt/therun/TheRunTimer$PacketType;)V", shift = At.Shift.AFTER, remap = false), remap = false)
    private static void onLeave(CallbackInfo ci) {
        PacemanGG.updateTimerData(INSTANCE, true);
    }

    @Inject(method = "setPause(ZLcom/redlimerl/speedrunigt/timer/TimerStatus;Ljava/lang/String;)V", at = @At(value = "INVOKE", target = "Lcom/redlimerl/speedrunigt/therun/TheRunRequestHelper;updateTimerData(Lcom/redlimerl/speedrunigt/timer/InGameTimer;Lcom/redlimerl/speedrunigt/therun/TheRunTimer$PacketType;)V", shift = At.Shift.AFTER, remap = false), remap = false)
    private void onPause(CallbackInfo ci) {
        PacemanGG.updateTimerData(INSTANCE, false);
    }

    @Inject(method = "tryInsertNewAdvancement", at = @At(value = "INVOKE", target = "Lcom/redlimerl/speedrunigt/timer/TimerAdvancementTracker$AdvancementTrack;setComplete(Z)V", shift = At.Shift.AFTER, remap = false), remap = false)
    private void onCompleteAdvancement(String advancementID, String criteriaKey, boolean isAdvancement, CallbackInfo ci) {
        if (isAdvancement) PacemanGG.updateTimerData(INSTANCE, false);
    }

    @Inject(method = "tryInsertNewTimeline(Ljava/lang/String;Z)Z", at = @At("TAIL"), remap = false)
    private void onUpdateTimeline(String name, boolean canSendPacket, CallbackInfoReturnable<Boolean> cir) {
        PacemanGG.updateTimerData(INSTANCE, false);
    }
}
