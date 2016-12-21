package astro.planetary.client.popup;

import astro.planetary.api.Popup;
import com.google.common.collect.Lists;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

@SideOnly(Side.CLIENT)
public enum PopupHandler {
    INSTANCE;

    List<Popup> toRender = Lists.newArrayList();

    public void addToRenderQueue(Popup popup) {
        toRender.add(popup);
    }
}