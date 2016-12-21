package astro.planetary.api;

import net.minecraft.util.text.TextFormatting;

public enum MaterialRarity {
    COMMON(TextFormatting.YELLOW),
    UNCOMMON(TextFormatting.GREEN),
    RARE(TextFormatting.AQUA),
    VERY_RARE(TextFormatting.RED);

    public final TextFormatting rarityColor;

    MaterialRarity(TextFormatting rarityColor) {
        this.rarityColor = rarityColor;
    }
}