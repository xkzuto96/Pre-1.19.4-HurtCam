package xkzuto.prehurtcam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path CONFIG_PATH = FabricLoader.getInstance().getConfigDir().resolve("pre-1194-hurtcam.json");

    private static ModConfig INSTANCE;

    public boolean enablePre1194HurtCam = true; // Enabled by default

    public static ModConfig getInstance() {
        if (INSTANCE == null) {
            INSTANCE = load();
        }
        return INSTANCE;
    }

    public static ModConfig load() {
        if (Files.exists(CONFIG_PATH)) {
            try {
                String json = Files.readString(CONFIG_PATH);
                return GSON.fromJson(json, ModConfig.class);
            } catch (IOException e) {
                System.err.println("[Pre-1.19.4 HurtCam] Failed to load config, using defaults: " + e.getMessage());
            }
        }
        ModConfig config = new ModConfig();
        config.save();
        return config;
    }

    public void save() {
        try {
            Files.createDirectories(CONFIG_PATH.getParent());
            String json = GSON.toJson(this);
            Files.writeString(CONFIG_PATH, json);
        } catch (IOException e) {
            System.err.println("[Pre-1.19.4 HurtCam] Failed to save config: " + e.getMessage());
        }
    }

    public void toggle() {
        enablePre1194HurtCam = !enablePre1194HurtCam;
        save();
    }
}
