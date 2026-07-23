package com.j3ly.deatheffects.common;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.j3ly.deatheffects.DeathEffectsMod;
import com.j3ly.deatheffects.effects.DeathEffectType;
import com.j3ly.deatheffects.effects.EffectPlayer;
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
            .then(Commands.argument("type", IntegerArgumentType.integer(0, DeathEffectType.values().length - 1))
                .executes(ctx -> {
                    int typeIndex = IntegerArgumentType.getInteger(ctx, "type");
                    DeathEffectType type = DeathEffectType.fromIndex(typeIndex);
                    ServerPlayer player = ctx.getSource().getPlayerOrException();
                    ServerLevel level = player.serverLevel();

                    EffectPlayer.play(level, player.position(), type);

                    ctx.getSource().sendSuccess(
                        () -> Component.literal("Played " + type.getDisplayName() + " effect"), true
                    );
                    return 1;
                })
            )
            .then(Commands.literal("list")
                .executes(ctx -> {
                    StringBuilder sb = new StringBuilder("Available effects:\n");
                    for (int i = 0; i < DeathEffectType.values().length; i++) {
                        sb.append(i).append(" = ").append(DeathEffectType.values()[i].getDisplayName()).append("\n");
                    }
                    ctx.getSource().sendSuccess(() -> Component.literal(sb.toString()), false);
                    return 1;
                })
            )
            .executes(ctx -> {
                ctx.getSource().sendSuccess(
                    () -> Component.literal("Usage: /deatheffect <type> or /deatheffect list"), false
                );
                return 0;
            })
        );
    }
}
