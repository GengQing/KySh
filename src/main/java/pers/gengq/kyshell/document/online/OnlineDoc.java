package pers.gengq.kyshell.document.online;

import pers.gengq.kyshell.document.Document;

/**
 * Created by gengqing on 8/24/2018
 **/
public interface OnlineDoc extends Document {

    String getUrl();

    String getTitle();

    String getPage(int pageNumber);

    int getPageCount();

}
