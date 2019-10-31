package net.chachy.particlemod.handlers.handler.update;

import cc.hyperium.Hyperium;
import cc.hyperium.event.InvokeEvent;
import cc.hyperium.event.network.server.ServerJoinEvent;
import cc.hyperium.event.world.EntityJoinWorldEvent;
import cc.hyperium.utils.ChatColor;
import net.chachy.particlemod.utils.ChachyMod;
import net.chachy.particlemod.ParticleMod;
import net.chachy.particlemod.config.Configuration;
import net.chachy.particlemod.handlers.utils.Handler;
import net.chachy.particlemod.utils.utils.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

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
    // Initialize a variable to get the version of the mod from my API.
    private final String version = ChachyMod.INSTANCE.getVersion(mod);

    /**
     * If the event {@link EntityJoinWorldEvent} is posted, it runs this method
     *
     * @see cc.hyperium.event.EventBus
     */
    @InvokeEvent
    public void onWorldJoin(EntityJoinWorldEvent event) {
        // Check if the entity joined is the player, the update messages option is enabled and isn't the latest version
        if (event.getEntity() == Minecraft.getMinecraft().thePlayer && Configuration.INSTANCE.showUpdateMessages() && !isLatestVersion)  {
            // Run the update message method.
            sendUpdateMessage();
        }

    }

    @InvokeEvent
    public void onServerJoin(ServerJoinEvent event) {
        // Check if update messages are enabled and isn't the latest version.
        if (Configuration.INSTANCE.showUpdateMessages() && !isLatestVersion) {
            // Run the update message method.
            sendUpdateMessage();
        }

    }

    private void sendUpdateMessage() {
        // Create a variable with the download link
        String url = "https://chachy.co.uk/download/ParticleAddon-" + version + ".jar";
        // Create a variable of the component so it becomes clickable.
        IChatComponent urlComponent = new ChatComponentText(ChatColor.RED + "[ParticleMod] " + ChatColor.GRAY + "Download the latest version!");
        // Add a the link when the text is clicked.
        urlComponent.getChatStyle().setChatClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, url));
        /// Set the text when it gets hovered over.
        urlComponent.getChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(ChatColor.GRAY + "Download the newest version here!")));
        // Send the message through the Hyperium ChatHandler.
        Hyperium.INSTANCE.getHandlers().getGeneralChatHandler().sendMessage(urlComponent);
    }
}
