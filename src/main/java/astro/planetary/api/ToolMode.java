package astro.planetary.api;

import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.translation.I18n;

public enum ToolMode implements IStringSerializable {
    MINE,
    SCAN,
    BLASTER,
    TURBO,
    GRENADE,
    PUSH,
    PULL
    ;

    @Override
    public String getName() {
        return name().toLowerCase();
    }

    public String getLocalized() {
        return I18n.translateToLocal("planetary.tool.mode." + getName() + ".name");
    }

    public static ToolMode from(int metadata) {
        for (ToolMode mode : values())
            if (mode.ordinal() == metadata)
                return mode;
        return from(0);
    }
}