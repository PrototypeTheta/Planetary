package astro.planetary.api;

import astro.planetary.Planetary;
import net.minecraft.util.ResourceLocation;

public enum MaterialGroup {
    OXIDE,
    SILICATE,
    ISOTOPE,
    NEUTRAL,
    EXOTIC,;

    public final ResourceLocation icon;

    MaterialGroup() {
        this.icon = new ResourceLocation(Planetary.MOD_ID, String.format("textures/icon/%s.png", name().toLowerCase()));
    }

}