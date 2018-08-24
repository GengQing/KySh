package pers.gengq.kyshell.document.online;

/**
 * Created by gengqing on 8/24/2018
 **/
public class Wenku implements OnlineDoc {

    private String url;

    public Wenku(String url) {
        this.url = url;

    }

    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getPage(int pageNumber) {
        return null;
    }

    @Override
    public int getPageCount() {
        return 0;
    }
}
