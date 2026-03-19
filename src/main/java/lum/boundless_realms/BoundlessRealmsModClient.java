package lum.boundless_realms;

import lum.boundless_realms.entity.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class BoundlessRealmsModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(
                ModEntities.LUNCH_TICKET_PROJECTILE,
                FlyingItemEntityRenderer::new
        );
    }
}