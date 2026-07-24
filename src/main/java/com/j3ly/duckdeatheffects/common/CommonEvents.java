package com.j3ly.duckdeatheffects.common;

import com.j3ly.duckdeatheffects.DeathEffectsMod;
import com.j3ly.duckdeatheffects.config.DeathEffectsConfig;
import com.j3ly.duckdeatheffects.effects.DeathEffectType;
import com.j3ly.duckdeatheffects.effects.EffectPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = DeathEffectsMod.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(player.level() instanceof ServerLevel level)) return;

        if (!DeathEffectsConfig.ENABLE_EFFECTS.get()) return;
        if (player.isCreative() && !DeathEffectsConfig.ENABLE_IN_CREATIVE.get()) return;

        Vec3 pos = player.position();
        DeathEffectType selectedEffect = DeathEffectsConfig.getSelectedEffect();

        if (selectedEffect != null) {
            if (DeathEffectsConfig.isEffectEnabled(selectedEffect)) {
                EffectPlayer.play(level, pos, selectedEffect);
            }
        } else {
            DeathEffectType randomEffect = getRandomEnabledEffect();
            if (randomEffect != null) {
                EffectPlayer.play(level, pos, randomEffect);
            }
        }
    }

    private static DeathEffectType getRandomEnabledEffect() {
        List<DeathEffectType> enabled = new ArrayList<>();
        for (DeathEffectType type : DeathEffectType.values()) {
            if (DeathEffectsConfig.isEffectEnabled(type)) {
                enabled.add(type);
            }
        }
        if (enabled.isEmpty()) return null;
        return enabled.get(ThreadLocalRandom.current().nextInt(enabled.size()));
    }
}
