package net.rngk.mushncav.datagen;

/**
 * Model provider disabled - item models are loaded directly from JSON files in resources.
 * In 1.21.11, Minecraft automatically loads model JSON files from:
 * assets/mushncav/models/item/*.json
 * assets/mushncav/models/block/*.json
 *
 * The texture files are automatically resolved from:
 * assets/mushncav/textures/item/*.png
 * assets/mushncav/textures/block/*.png
 */
public class ModModelProvider {
    private ModModelProvider() {
        // Utility class - models loaded from resources
    }
}
