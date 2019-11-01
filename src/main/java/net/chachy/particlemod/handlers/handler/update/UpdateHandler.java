package net.chachy.particlemod.handlers.handler.update;

import cc.hyperium.Hyperium;
import cc.hyperium.event.InvokeEvent;
import cc.hyperium.event.network.server.ServerJoinEvent;
import cc.hyperium.event.network.server.SingleplayerJoinEvent;
import cc.hyperium.event.world.EntityJoinWorldEvent;
import net.chachy.particlemod.ParticleMod;
import net.chachy.particlemod.config.Configuration;
import net.chachy.particlemod.handlers.utils.Handler;
import net.chachy.particlemod.utils.ChachyMod;
import net.chachy.particlemod.utils.utils.Mod;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class UpdateHandler implements Handler {
    /**
     * Class used for handling update checks.
     */

    @Override
    public String getHandlerName() {
        return "UpdateHandler";
    }

    // Initialize a variable to use in "isLatestVersion" when checking for the version.
    private final Mod mod = () -> "ParticleAddon";
    // Initialize a final boolean to check if the mod is at it's latest version.
    private final boolean isLatestVersion = ChachyMod.INSTANCE.isLatestVersion(mod, ParticleMod.INSTANCE.VERSION);

    /**
     * If the event {@link ServerJoinEvent} is posted, it runs this method
     *
     * @see cc.hyperium.event.EventBus
     */

    // Check if the user joins a server
    @InvokeEvent
    public void onServerJoin(ServerJoinEvent event) {
        // Check if update messages are enabled and isn't the latest version run the update message method.
        if (Configuration.INSTANCE.showUpdateMessages() && !isLatestVersion) sendUpdateMessage();
    }

    /**
     * If the event {@link SingleplayerJoinEvent} is posted, it runs this method
     *
     * @see cc.hyperium.event.EventBus
     */

    // Check if the user joins a singleplayer world
    @InvokeEvent
    public void onSingleplayerJoin(SingleplayerJoinEvent event) {
        // Check if update messages are enabled and isn't the latest version run the update message method.
        if (Configuration.INSTANCE.showUpdateMessages() && !isLatestVersion) sendUpdateMessage();
    }

    private void sendUpdateMessage() {
        Hyperium.INSTANCE.getNotification().display(
                "There is an update ready.",
                "Particle Addon has an update. " +
                        "Click to disable these notifications.",
                5F,
                null,
                () -> Configuration.INSTANCE.setUpdateMessages(false),
                new Color(128, 226, 126));
    }
}
