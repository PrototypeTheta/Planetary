package astro.planetary;

import astro.planetary.entity.EntityFANGC;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class PlanetaryContent {

    public static ItemSpaceSuit HELM;
    public static ItemSpaceSuit CHEST;
    public static ItemSpaceSuit LEGS;
    public static ItemSpaceSuit FEET;

    public static void register() {
        HELM = new ItemSpaceSuit(0, EntityEquipmentSlot.HEAD);
        CHEST = new ItemSpaceSuit(0, EntityEquipmentSlot.CHEST);
        LEGS = new ItemSpaceSuit(0, EntityEquipmentSlot.LEGS);
        FEET = new ItemSpaceSuit(0, EntityEquipmentSlot.FEET);
        EntityRegistry.registerModEntity(EntityFANGC.class,"fangc", 0, Planetary.INSTANCE, 256, 1, true);
    }

}