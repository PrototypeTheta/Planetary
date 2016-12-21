package astro.planetary.client.popup;

import astro.planetary.api.Popup;

public class PopupBuilder {

    public String title;
    public boolean doTitle = true;
    public String subTitle;
    public String datas;

    public PopupBuilder() {
    }

    public void withTitle(String title) {
        this.title = title;
    }

    public void setNoTitle() {
        this.doTitle = false;
    }

    public void withSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public void withData(String datas) {
        this.datas = datas;
    }

    public Popup build() {
        return new Popup(doTitle ? title : "", subTitle, datas);
    }
}