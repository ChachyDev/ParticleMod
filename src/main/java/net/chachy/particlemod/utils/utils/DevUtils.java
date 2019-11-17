package net.chachy.particlemod.utils.utils;

public class DevUtils {
    public static DevUtils INSTANCE = new DevUtils();

    public boolean isMinecraftDevelopmentEnvironment() {
        try {
            return Class.forName("net.minecraft.client.Minecraft") != null;
        } catch (ClassNotFoundException ignored) { }
        return false;
    }
}
