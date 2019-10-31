package net.chachy.particlemod.utils;

import com.google.gson.JsonParser;
import net.chachy.particlemod.utils.http.HttpUtils;
import net.chachy.particlemod.utils.utils.DevUtils;
import net.chachy.particlemod.utils.utils.Mod;
import net.chachy.particlemod.ParticleMod;

import java.io.IOException;

public class ChachyMod {
    private String API_URL = "https://api.chachy.co.uk";
    public static ChachyMod INSTANCE = new ChachyMod();

    public final boolean isLatestVersion(String mod, String version) {
        if (DevUtils.INSTANCE.isMinecraftDevelopmentEnvironment()) return true;
        return (Integer.parseInt(getVersion(mod)) < Integer.parseInt(version)) || getVersion(mod).equalsIgnoreCase(ParticleMod.INSTANCE.VERSION);
    }

    public final boolean isLatestVersion(Mod mod, String version) {
        if (DevUtils.INSTANCE.isMinecraftDevelopmentEnvironment()) return true;
        return (Integer.parseInt(getVersion(mod)) < Integer.parseInt(version)) || getVersion(mod).equalsIgnoreCase(ParticleMod.INSTANCE.VERSION);
    }


    public final String getVersion(Mod mod) {
        try {
            return new JsonParser().parse(HttpUtils.get("https://api.chachy.co.uk/get/mod/" + mod.getModName())).getAsJsonObject().get("version").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ParticleMod.INSTANCE.VERSION;
    }

    public final String getVersion(String endpoint, String mod) {
        try {
            return new JsonParser().parse(HttpUtils.get(API_URL + endpoint + mod)).getAsJsonObject().get("version").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ParticleMod.INSTANCE.VERSION;
    }

    private String getVersion(String mod) {
        try {
            return new JsonParser().parse(HttpUtils.get("https://api.chachy.co.uk/get/mod/" + mod)).getAsJsonObject().get("version").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ParticleMod.INSTANCE.VERSION;
    }

    public void setApiUrl(String url) {
        if (url.contains("/")) url = url.replace("/", "");
        this.API_URL = url;
    }
}
