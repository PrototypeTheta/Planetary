package astro.planetary.api;

import net.minecraft.world.DimensionType;

public interface IPlanet {

    float getGravity();

    float getOxygen();

    String getName();

    DimensionType getType();
}