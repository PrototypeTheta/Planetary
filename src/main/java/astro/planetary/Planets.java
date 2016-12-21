package astro.planetary;

import astro.planetary.api.IPlanet;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DimensionType;

public enum Planets implements IPlanet {
    NO_DATA("No Data", 0.0F, 0.0F, null),
    HOSNIAN_PRIME("Hosnian Prime", 1.0F, 1.0F, DimensionType.OVERWORLD),;

    private float gravity;
    private float oxygen;
    private String name;
    private DimensionType type;

    Planets(String name, float gravity, float oxygen, DimensionType type) {
        this.name = name;
        this.gravity = gravity;
        this.oxygen = oxygen;
        this.type = type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public DimensionType getType() {
        return type;
    }

    @Override
    public float getGravity() {
        return gravity;
    }

    @Override
    public float getOxygen() {
        return oxygen;
    }

    public TextFormatting getFormattingForFloat(float f) {
        if (f < 0.33f)
            return TextFormatting.RED;
        else if (f < 0.66f)
            return TextFormatting.YELLOW;
        else
            return TextFormatting.GREEN;
    }


    public String buildData() {
        return String.format("Oxygen %s%d%s%% | Gravity %s%d%s%%", getFormattingForFloat(getOxygen()), Math.round(getOxygen() * 100), TextFormatting.RESET, getFormattingForFloat(getGravity()), Math.round(getGravity() * 100), TextFormatting.RESET);
    }

    public static Planets getFromID(int dim) {
        for (Planets planet : values())
            if (planet.getType() != null && planet.getType().getId() == dim) return planet;
        return NO_DATA;
    }
}