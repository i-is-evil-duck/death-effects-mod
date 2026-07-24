package com.j3ly.deatheffects.config;

import com.j3ly.deatheffects.effects.DeathEffectType;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class DeathEffectsConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    public static final ForgeConfigSpec.BooleanValue ENABLE_EFFECTS = BUILDER
        .comment("Enable or disable all death effects")
        .define("enableEffects", true);

    public static final ForgeConfigSpec.IntValue EFFECT_MODE = BUILDER
        .comment("Effect selection mode: 0=Random, 1=Lightning, 2=Explosion, 3=Fire Burst, 4=Soul Escape, 5=Smoke Cloud")
        .defineInRange("effectMode", 1, 0, 5);

    public static final ForgeConfigSpec.BooleanValue ENABLE_LIGHTNING = BUILDER
        .comment("Enable Lightning Strike effect (used in random mode)")
        .define("enableLightning", true);

    public static final ForgeConfigSpec.BooleanValue ENABLE_EXPLOSION = BUILDER
        .comment("Enable Explosion effect (used in random mode)")
        .define("enableExplosion", true);

    public static final ForgeConfigSpec.BooleanValue ENABLE_FIRE_BURST = BUILDER
        .comment("Enable Fire Burst effect (used in random mode)")
        .define("enableFireBurst", true);

    public static final ForgeConfigSpec.BooleanValue ENABLE_SOUL_ESCAPE = BUILDER
        .comment("Enable Soul Escape effect (used in random mode)")
        .define("enableSoulEscape", true);

    public static final ForgeConfigSpec.BooleanValue ENABLE_SMOKE_CLOUD = BUILDER
        .comment("Enable Smoke Cloud effect (used in random mode)")
        .define("enableSmokeCloud", true);

    public static final ForgeConfigSpec.BooleanValue ENABLE_IN_CREATIVE = BUILDER
        .comment("Enable death effects for creative mode players")
        .define("enableInCreative", false);

    public static final ForgeConfigSpec.DoubleValue EFFECT_RADIUS = BUILDER
        .comment("Radius around the death location to play effects (in chunks)")
        .defineInRange("effectRadius", 4.0, 1.0, 16.0);

    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static void register() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SPEC, "deatheffects.toml");
    }

    public static DeathEffectType getSelectedEffect() {
        int mode = EFFECT_MODE.get();
        if (mode == 0) return null;
        return DeathEffectType.fromIndex(mode - 1);
    }

    public static boolean isEffectEnabled(DeathEffectType type) {
        return switch (type) {
            case LIGHTNING -> ENABLE_LIGHTNING.get();
            case EXPLOSION -> ENABLE_EXPLOSION.get();
            case FIRE_BURST -> ENABLE_FIRE_BURST.get();
            case SOUL_ESCAPE -> ENABLE_SOUL_ESCAPE.get();
            case SMOKE_CLOUD -> ENABLE_SMOKE_CLOUD.get();
        };
    }
}
