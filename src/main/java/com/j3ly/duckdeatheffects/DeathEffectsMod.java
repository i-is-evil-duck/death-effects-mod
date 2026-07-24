package com.j3ly.duckdeatheffects;

import com.j3ly.duckdeatheffects.config.DeathEffectsConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(DeathEffectsMod.MOD_ID)
public class DeathEffectsMod {
    public static final String MOD_ID = "deatheffects";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public DeathEffectsMod() {
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        DeathEffectsConfig.register();
    }
}
