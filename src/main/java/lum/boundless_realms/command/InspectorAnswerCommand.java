package lum.boundless_realms.command;

import com.mojang.brigadier.CommandDispatcher;
import lum.boundless_realms.entity.FakeTicketInspectorEntity;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;

import static net.minecraft.server.command.CommandManager.literal;

public class InspectorAnswerCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                literal("inspector_answer")
                        .then(literal("yes")
                                .executes(context -> {
                                    ServerPlayerEntity player = context.getSource().getPlayerOrThrow();

                                    List<FakeTicketInspectorEntity> inspectors =
                                            player.getEntityWorld().getEntitiesByClass(
                                                    FakeTicketInspectorEntity.class,
                                                    player.getBoundingBox().expand(12.0),
                                                    inspector -> inspector.isWaitingFor(player)
                                            );

                                    if (!inspectors.isEmpty()) {
                                        inspectors.get(0).onPlayerAnsweredYes(player);
                                        return 1;
                                    }

                                    return 0;
                                }))
                        .then(literal("no")
                                .executes(context -> {
                                    ServerPlayerEntity player = context.getSource().getPlayerOrThrow();

                                    List<FakeTicketInspectorEntity> inspectors =
                                            player.getEntityWorld().getEntitiesByClass(
                                                    FakeTicketInspectorEntity.class,
                                                    player.getBoundingBox().expand(12.0),
                                                    inspector -> inspector.isWaitingFor(player)
                                            );

                                    if (!inspectors.isEmpty()) {
                                        inspectors.get(0).onPlayerAnsweredNo(player);
                                        return 1;
                                    }

                                    return 0;
                                }))
        );
    }
}

