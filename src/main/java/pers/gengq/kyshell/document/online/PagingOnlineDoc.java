package pers.gengq.kyshell.document.online;

import pers.gengq.kyshell.document.Document;

import java.io.IOException;

/**
 * Created by gengqing on 8/24/2018
 **/
public interface PagingOnlineDoc extends Document {

    void open(String url);


    String getUrl();

    String getTitle();

    String getPage(int pageNumber);

    int getPageCount();

}
