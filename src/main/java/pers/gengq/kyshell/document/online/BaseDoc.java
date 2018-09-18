package pers.gengq.kyshell.document.online;

import org.springframework.util.Assert;

/**
 * Created by Geng Qing on 9/18/2018
 **/
public class BaseDoc {
    /**
     * current url
     */
    protected String url;

    /**
     * start url
     */
    protected String startUrl;

    public void open(String url) {
        this.startUrl = url;
        this.url = url;
        Assert.notNull(url, "url is required");

    }
}
