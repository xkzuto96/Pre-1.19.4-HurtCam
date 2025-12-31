package xkzuto.prehurtcam.config;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public class ConfigScreen extends Screen {
    private final Screen parent;
    private final ModConfig config;

    public ConfigScreen(Screen parent) {
        super(Component.translatable("text.config.pre-1194-hurtcam.title"));
        this.parent = parent;
        this.config = ModConfig.getInstance();
    }

    @Override
    protected void init() {
        super.init();

        // Enable/Disable toggle button
        this.addRenderableWidget(Button.builder(
            getToggleButtonText(),
            button -> {
                config.toggle();
                button.setMessage(getToggleButtonText());
            }
        )
        .bounds(this.width / 2 - 100, this.height / 2 - 10, 200, 20)
        .build());

        // Done button
        this.addRenderableWidget(Button.builder(
            CommonComponents.GUI_DONE,
            button -> {
                if (this.minecraft != null) {
                    this.minecraft.setScreen(parent);
                }
            }
        )
        .bounds(this.width / 2 - 100, this.height / 2 + 20, 200, 20)
        .build());
    }

    private Component getToggleButtonText() {
        String status = config.enablePre1194HurtCam ? "ON" : "OFF";
        return Component.translatable("text.config.pre-1194-hurtcam.option.enable")
            .append(": ")
            .append(status);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        super.render(guiGraphics, mouseX, mouseY, delta);

        // Draw title
        guiGraphics.drawCenteredString(
            this.font,
            this.title,
            this.width / 2,
            20,
            0xFFFFFF
        );

        // Draw tooltip/description
        Component tooltip = Component.translatable("text.config.pre-1194-hurtcam.option.enable.tooltip");
        int tooltipY = this.height / 2 - 35;
        guiGraphics.drawCenteredString(
            this.font,
            tooltip,
            this.width / 2,
            tooltipY,
            0xAAAAAA
        );
    }

    @Override
    public void onClose() {
        if (this.minecraft != null) {
            this.minecraft.setScreen(parent);
        }
    }
}
