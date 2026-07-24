package com.j3ly.duckdeatheffects.common;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.j3ly.duckdeatheffects.DeathEffectsMod;
import com.j3ly.duckdeatheffects.config.DeathEffectsConfig;
import com.j3ly.duckdeatheffects.effects.DeathEffectType;
import com.j3ly.duckdeatheffects.effects.EffectPlayer;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = DeathEffectsMod.MOD_ID)
public class DeathEffectCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal("deatheffect")
            .then(Commands.literal("set")
                .then(Commands.literal("random")
                    .requires(src -> src.hasPermission(2))
                    .executes(ctx -> {
                        DeathEffectsConfig.EFFECT_MODE.set(0);
                        ctx.getSource().sendSuccess(
                            () -> Component.literal("Death effect set to Random"), true
                        );
                        return 1;
                    })
                )
                .then(Commands.literal("lightning")
                    .requires(src -> src.hasPermission(2))
                    .executes(ctx -> {
                        DeathEffectsConfig.EFFECT_MODE.set(1);
                        ctx.getSource().sendSuccess(
                            () -> Component.literal("Death effect set to Lightning Strike"), true
                        );
                        return 1;
                    })
                )
                .then(Commands.literal("explosion")
                    .requires(src -> src.hasPermission(2))
                    .executes(ctx -> {
                        DeathEffectsConfig.EFFECT_MODE.set(2);
                        ctx.getSource().sendSuccess(
                            () -> Component.literal("Death effect set to Explosion"), true
                        );
                        return 1;
                    })
                )
                .then(Commands.literal("fire")
                    .requires(src -> src.hasPermission(2))
                    .executes(ctx -> {
                        DeathEffectsConfig.EFFECT_MODE.set(3);
                        ctx.getSource().sendSuccess(
                            () -> Component.literal("Death effect set to Fire Burst"), true
                        );
                        return 1;
                    })
                )
                .then(Commands.literal("souls")
                    .requires(src -> src.hasPermission(2))
                    .executes(ctx -> {
                        DeathEffectsConfig.EFFECT_MODE.set(4);
                        ctx.getSource().sendSuccess(
                            () -> Component.literal("Death effect set to Soul Escape"), true
                        );
                        return 1;
                    })
                )
                .then(Commands.literal("smoke")
                    .requires(src -> src.hasPermission(2))
                    .executes(ctx -> {
                        DeathEffectsConfig.EFFECT_MODE.set(5);
                        ctx.getSource().sendSuccess(
                            () -> Component.literal("Death effect set to Smoke Cloud"), true
                        );
                        return 1;
                    })
                )
            )
            .then(Commands.literal("preview")
                .then(Commands.literal("lightning")
                    .executes(ctx -> previewEffect(ctx.getSource(), DeathEffectType.LIGHTNING))
                )
                .then(Commands.literal("explosion")
                    .executes(ctx -> previewEffect(ctx.getSource(), DeathEffectType.EXPLOSION))
                )
                .then(Commands.literal("fire")
                    .executes(ctx -> previewEffect(ctx.getSource(), DeathEffectType.FIRE_BURST))
                )
                .then(Commands.literal("souls")
                    .executes(ctx -> previewEffect(ctx.getSource(), DeathEffectType.SOUL_ESCAPE))
                )
                .then(Commands.literal("smoke")
                    .executes(ctx -> previewEffect(ctx.getSource(), DeathEffectType.SMOKE_CLOUD))
                )
            )
            .then(Commands.literal("current")
                .executes(ctx -> {
                    int mode = DeathEffectsConfig.EFFECT_MODE.get();
                    String name = mode == 0 ? "Random" : DeathEffectType.fromIndex(mode - 1).getDisplayName();
                    ctx.getSource().sendSuccess(
                        () -> Component.literal("Current death effect: " + name + " (mode " + mode + ")"), false
                    );
                    return 1;
                })
            )
            .then(Commands.literal("list")
                .executes(ctx -> {
                    ctx.getSource().sendSuccess(() -> Component.literal("--- Death Effects ---"), false);
                    ctx.getSource().sendSuccess(() -> Component.literal("0 = Random (pick from enabled pool)"), false);
                    for (int i = 0; i < DeathEffectType.values().length; i++) {
                        final int index = i;
                        DeathEffectType type = DeathEffectType.values()[i];
                        boolean enabled = DeathEffectsConfig.isEffectEnabled(type);
                        String status = enabled ? "\u00a7aON" : "\u00a7cOFF";
                        String finalStatus = status;
                        String typeName = type.getDisplayName();
                        ctx.getSource().sendSuccess(
                            () -> Component.literal((index + 1) + " = " + typeName + " " + finalStatus), false
                        );
                    }
                    return 1;
                })
            )
            .executes(ctx -> {
                ctx.getSource().sendSuccess(
                    () -> Component.literal("--- Death Effects Usage ---"), false
                );
                ctx.getSource().sendSuccess(
                    () -> Component.literal("/deatheffect set <random|lightning|explosion|fire|souls|smoke>"), false
                );
                ctx.getSource().sendSuccess(
                    () -> Component.literal("/deatheffect preview <lightning|explosion|fire|souls|smoke>"), false
                );
                ctx.getSource().sendSuccess(
                    () -> Component.literal("/deatheffect current"), false
                );
                ctx.getSource().sendSuccess(
                    () -> Component.literal("/deatheffect list"), false
                );
                return 0;
            })
        );
    }

    private static int previewEffect(CommandSourceStack source, DeathEffectType type) throws CommandSyntaxException {
        ServerPlayer player = source.getPlayerOrException();
        ServerLevel level = player.serverLevel();
        EffectPlayer.play(level, player.position(), type);
        source.sendSuccess(
            () -> Component.literal("Previewing " + type.getDisplayName() + " effect"), true
        );
        return 1;
    }
}
