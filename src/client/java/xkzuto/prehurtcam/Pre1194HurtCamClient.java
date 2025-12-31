package xkzuto.prehurtcam;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;
import xkzuto.prehurtcam.config.ModConfig;

public class Pre1194HurtCamClient implements ClientModInitializer {
	private static KeyMapping toggleKeybind;
	private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(
		ResourceLocation.fromNamespaceAndPath("pre-1194-hurtcam", "main")
	);

	@Override
	public void onInitializeClient() {
		// Load config
		ModConfig.getInstance();

		// Register keybind
		toggleKeybind = KeyBindingHelper.registerKeyBinding(new KeyMapping(
			"key.pre-1194-hurtcam.toggle",
			InputConstants.Type.KEYSYM,
			GLFW.GLFW_KEY_H, // Default key: H
			CATEGORY
		));

		// Register tick event to handle keybind press
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (toggleKeybind.consumeClick()) {
				ModConfig config = ModConfig.getInstance();
				config.toggle();

				if (client.player != null) {
					String status = config.enablePre1194HurtCam ? "enabled" : "disabled";
					client.player.displayClientMessage(
						Component.literal("Pre-1.19.4 HurtCam: " + status),
						true // Show as action bar message
					);
				}
			}
		});
	}
}