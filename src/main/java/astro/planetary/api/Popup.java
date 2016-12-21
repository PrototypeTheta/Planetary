package astro.planetary.api;

public class Popup {

    public String title = "";
    public String subTitle = "";
    public String datas = "";
    public int ticker;
    public boolean reverse;

    public Popup(String title, String subTitle, String datas) {
        this.title = title;
        this.subTitle = subTitle;
        this.datas = datas;
    }
}