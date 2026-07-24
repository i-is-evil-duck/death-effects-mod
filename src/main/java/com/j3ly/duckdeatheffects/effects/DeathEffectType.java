package com.j3ly.duckdeatheffects.effects;

public enum DeathEffectType {
    LIGHTNING("Lightning Strike"),
    EXPLOSION("Explosion"),
    FIRE_BURST("Fire Burst"),
    SOUL_ESCAPE("Soul Escape"),
    SMOKE_CLOUD("Smoke Cloud");

    private final String displayName;

    DeathEffectType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static DeathEffectType fromIndex(int index) {
        DeathEffectType[] values = values();
        if (index >= 0 && index < values.length) {
            return values[index];
        }
        return LIGHTNING;
    }
}
