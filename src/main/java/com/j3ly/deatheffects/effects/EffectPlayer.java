package com.j3ly.deatheffects.effects;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;

import java.util.concurrent.ThreadLocalRandom;

public class EffectPlayer {

    public static void play(ServerLevel level, Vec3 pos, DeathEffectType type) {
        switch (type) {
            case LIGHTNING -> playLightning(level, pos);
            case EXPLOSION -> playExplosion(level, pos);
            case FIRE_BURST -> playFireBurst(level, pos);
            case SOUL_ESCAPE -> playSoulEscape(level, pos);
            case SMOKE_CLOUD -> playSmokeCloud(level, pos);
        }
    }

    private static void playLightning(ServerLevel level, Vec3 pos) {
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.HOSTILE, 2.0f, 0.8f);
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.AMBIENT, 1.0f, 1.2f);

        for (int i = 0; i < 30; i++) {
            double x = pos.x + ThreadLocalRandom.current().nextDouble(-0.5, 0.5);
            double y = pos.y + i * 0.3;
            double z = pos.z + ThreadLocalRandom.current().nextDouble(-0.5, 0.5);
            level.sendParticles(ParticleTypes.ELECTRIC_SPARK, x, y, z, 3, 0.1, 0.1, 0.1, 0.05);
        }

        for (int i = 0; i < 20; i++) {
            double x = pos.x + ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
            double y = pos.y + ThreadLocalRandom.current().nextDouble(0, 0.5);
            double z = pos.z + ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
            level.sendParticles(ParticleTypes.END_ROD, x, y, z, 2, 0.05, 0.2, 0.05, 0.02);
        }

        level.sendParticles(ParticleTypes.FLASH, pos.x, pos.y + 0.5, pos.z, 1, 0, 0, 0, 0);
    }

    private static void playExplosion(ServerLevel level, Vec3 pos) {
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.GENERIC_EXPLODE, SoundSource.HOSTILE, 3.0f, 0.7f);
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.GENERIC_EXPLODE, SoundSource.AMBIENT, 2.0f, 1.1f);

        level.sendParticles(ParticleTypes.EXPLOSION_EMITTER, pos.x, pos.y + 0.5, pos.z, 1, 0, 0, 0, 0);
        level.sendParticles(ParticleTypes.EXPLOSION, pos.x, pos.y + 0.5, pos.z, 3, 0.5, 0.5, 0.5, 0.1);

        for (int i = 0; i < 40; i++) {
            double angle = ThreadLocalRandom.current().nextDouble(0, Math.PI * 2);
            double radius = ThreadLocalRandom.current().nextDouble(0.5, 3.0);
            double x = pos.x + Math.cos(angle) * radius;
            double y = pos.y + ThreadLocalRandom.current().nextDouble(0, 1.5);
            double z = pos.z + Math.sin(angle) * radius;
            double dx = Math.cos(angle) * 0.15;
            double dy = ThreadLocalRandom.current().nextDouble(0.05, 0.2);
            double dz = Math.sin(angle) * 0.15;
            level.sendParticles(ParticleTypes.SMOKE, x, y, z, 2, dx, dy, dz, 0.02);
        }

        for (int i = 0; i < 20; i++) {
            double x = pos.x + ThreadLocalRandom.current().nextDouble(-2, 2);
            double y = pos.y + ThreadLocalRandom.current().nextDouble(0, 2);
            double z = pos.z + ThreadLocalRandom.current().nextDouble(-2, 2);
            level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 1, 0, 0.05, 0, 0.02);
        }
    }

    private static void playFireBurst(ServerLevel level, Vec3 pos) {
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.FIRECHARGE_USE, SoundSource.HOSTILE, 2.0f, 0.6f);
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.BLAZE_SHOOT, SoundSource.AMBIENT, 1.5f, 1.0f);

        for (int i = 0; i < 24; i++) {
            double angle = (i / 24.0) * Math.PI * 2;
            double radius = 1.5;
            double x = pos.x + Math.cos(angle) * radius;
            double y = pos.y + 0.5;
            double z = pos.z + Math.sin(angle) * radius;
            double dx = Math.cos(angle) * 0.1;
            double dz = Math.sin(angle) * 0.1;
            level.sendParticles(ParticleTypes.FLAME, x, y, z, 5, dx, 0.15, dz, 0.03);
        }

        for (int i = 0; i < 30; i++) {
            double x = pos.x + ThreadLocalRandom.current().nextDouble(-0.8, 0.8);
            double y = pos.y + ThreadLocalRandom.current().nextDouble(0, 1.5);
            double z = pos.z + ThreadLocalRandom.current().nextDouble(-0.8, 0.8);
            level.sendParticles(ParticleTypes.LAVA, x, y, z, 2, 0, 0.05, 0, 0.02);
        }

        for (int i = 0; i < 15; i++) {
            double x = pos.x + ThreadLocalRandom.current().nextDouble(-1, 1);
            double y = pos.y + ThreadLocalRandom.current().nextDouble(0.5, 2.0);
            double z = pos.z + ThreadLocalRandom.current().nextDouble(-1, 1);
            level.sendParticles(ParticleTypes.SMOKE, x, y, z, 3, 0, 0.03, 0, 0.01);
        }
    }

    private static void playSoulEscape(ServerLevel level, Vec3 pos) {
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.SOUL_ESCAPE, SoundSource.HOSTILE, 2.0f, 0.5f);
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.WITHER_DEATH, SoundSource.AMBIENT, 0.5f, 1.5f);

        for (int i = 0; i < 50; i++) {
            double x = pos.x + ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
            double y = pos.y + ThreadLocalRandom.current().nextDouble(0, 0.5);
            double z = pos.z + ThreadLocalRandom.current().nextDouble(-1.0, 1.0);
            level.sendParticles(ParticleTypes.SOUL, x, y, z, 2, 0, 0.08, 0, 0.01);
        }

        for (int i = 0; i < 30; i++) {
            double x = pos.x + ThreadLocalRandom.current().nextDouble(-0.5, 0.5);
            double y = pos.y + ThreadLocalRandom.current().nextDouble(0, 0.3);
            double z = pos.z + ThreadLocalRandom.current().nextDouble(-0.5, 0.5);
            level.sendParticles(ParticleTypes.SOUL_FIRE_FLAME, x, y, z, 1, 0, 0.05, 0, 0.005);
        }

        for (int i = 0; i < 20; i++) {
            double x = pos.x + ThreadLocalRandom.current().nextDouble(-1.5, 1.5);
            double y = pos.y + ThreadLocalRandom.current().nextDouble(1.0, 3.0);
            double z = pos.z + ThreadLocalRandom.current().nextDouble(-1.5, 1.5);
            level.sendParticles(ParticleTypes.ASH, x, y, z, 2, 0, -0.02, 0, 0.01);
        }
    }

    private static void playSmokeCloud(ServerLevel level, Vec3 pos) {
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.LAVA_POP, SoundSource.HOSTILE, 2.0f, 0.4f);
        level.playSound(null, pos.x, pos.y, pos.z, SoundEvents.CAMPFIRE_CRACKLE, SoundSource.AMBIENT, 1.5f, 0.7f);

        for (int i = 0; i < 60; i++) {
            double x = pos.x + ThreadLocalRandom.current().nextDouble(-2.0, 2.0);
            double y = pos.y + ThreadLocalRandom.current().nextDouble(-0.5, 1.5);
            double z = pos.z + ThreadLocalRandom.current().nextDouble(-2.0, 2.0);
            level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 2, 0, 0.04, 0, 0.01);
        }

        for (int i = 0; i < 40; i++) {
            double x = pos.x + ThreadLocalRandom.current().nextDouble(-1.5, 1.5);
            double y = pos.y + ThreadLocalRandom.current().nextDouble(0, 1.0);
            double z = pos.z + ThreadLocalRandom.current().nextDouble(-1.5, 1.5);
            level.sendParticles(ParticleTypes.LARGE_SMOKE, x, y, z, 2, 0, 0.03, 0, 0.01);
        }

        for (int i = 0; i < 15; i++) {
            double x = pos.x + ThreadLocalRandom.current().nextDouble(-1, 1);
            double y = pos.y + ThreadLocalRandom.current().nextDouble(0, 0.5);
            double z = pos.z + ThreadLocalRandom.current().nextDouble(-1, 1);
            level.sendParticles(ParticleTypes.ASH, x, y, z, 3, 0, 0.02, 0, 0.005);
        }
    }
}
