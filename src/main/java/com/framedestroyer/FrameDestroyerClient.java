package com.framedestroyer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class FrameDestroyerClient implements ClientModInitializer {
    public static boolean hideFrames = true;
    private static KeyBinding toggleKey;

    @Override
    public void onInitializeClient() {
        toggleKey = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.frame_destroyer.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                key.categories.misc
            )
        );

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (toggleKey.wasPressed()) {
                hideFrames = !hideFrames;
                if (client.player != null) {
                    client.player.sendMessage(
                        net.minecraft.text.Text.literal(
                            "FrameDestroyer: " +
                            (hideFrames ? "HIDDEN" : "VISIBLE")
                        ),
                        true
                    );
                }
            }
        });
    }
}
