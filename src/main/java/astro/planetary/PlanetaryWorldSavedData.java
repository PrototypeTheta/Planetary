package astro.planetary;

import com.google.common.collect.Maps;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

import java.util.Map;

public class PlanetaryWorldSavedData extends WorldSavedData {
    private static final String DATA_NAME = Planetary.MOD_ID + "_data";

    Map<Integer, String> DIM_UUID_MAP = Maps.newHashMap();

    public PlanetaryWorldSavedData() {
        super(DATA_NAME);
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        for (String name : nbt.getKeySet()) {
            DIM_UUID_MAP.put(Integer.getInteger(name), nbt.getString(name));
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        for (Integer i : DIM_UUID_MAP.keySet()) {
            compound.setString(String.valueOf(i), DIM_UUID_MAP.get(i));
        }
        return compound;
    }

    public static PlanetaryWorldSavedData getData(World world) {
        MapStorage storage = world.getMapStorage();
        PlanetaryWorldSavedData data = (PlanetaryWorldSavedData) storage.getOrLoadData(PlanetaryWorldSavedData.class, DATA_NAME);
        if (data == null) {
            data = new PlanetaryWorldSavedData();
            storage.setData(DATA_NAME, data);
        }
        return data;
    }
}