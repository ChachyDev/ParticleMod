package net.chachy.particlemod.utils;

import com.google.gson.JsonParser;
import net.chachy.httpclient.request.Request;
import net.chachy.httpclient.request.RequestBuilder;
import net.chachy.httpclient.request.type.MethodType;
import net.chachy.particlemod.ParticleMod;
import net.chachy.particlemod.utils.utils.DevUtils;
import net.chachy.particlemod.utils.utils.Mod;

public class ChachyMod {
    private String API_URL = "https://api.chachy.co.uk";
    public static ChachyMod INSTANCE = new ChachyMod();

    public final boolean isLatestVersion(String mod, String version) {
        if (DevUtils.INSTANCE.isMinecraftDevelopmentEnvironment()) return true;
        return (Float.parseFloat(getVersion(mod)) < Float.parseFloat(version)) || getVersion(mod).equalsIgnoreCase(ParticleMod.INSTANCE.VERSION);
    }

    public final boolean isLatestVersion(Mod mod, String version) {
        if (DevUtils.INSTANCE.isMinecraftDevelopmentEnvironment()) return true;
        return (Float.parseFloat(getVersion(mod)) < Float.parseFloat(version)) || getVersion(mod).equalsIgnoreCase(ParticleMod.INSTANCE.VERSION);
    }


    public final String getVersion(Mod mod) {
        final Request request = new RequestBuilder("https://api.chachy.co.uk/get/mod/" + mod.getModName(), MethodType.GET).build();
        try {
            return new JsonParser().parse(request.getGETResponse()).getAsJsonObject().get("version").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ParticleMod.INSTANCE.VERSION;
    }

    public final String getVersion(String endpoint, String mod) {
        final Request request = new RequestBuilder(API_URL + endpoint + mod, MethodType.GET).build();
        try {
            return new JsonParser().parse(request.getGETResponse()).getAsJsonObject().get("version").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ParticleMod.INSTANCE.VERSION;
    }

    private String getVersion(String mod) {
        final Request request = new RequestBuilder("https://api.chachy.co.uk/get/mod/" + mod, MethodType.GET).build();
        try {
            return new JsonParser().parse(request.getGETResponse()).getAsJsonObject().get("version").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ParticleMod.INSTANCE.VERSION;
    }

    public void setApiUrl(String url) {
        if (url.contains("/")) url = url.replace("/", "");
        this.API_URL = url;
    }
}
