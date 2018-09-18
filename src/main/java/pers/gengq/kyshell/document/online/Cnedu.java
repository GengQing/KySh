package pers.gengq.kyshell.document.online;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Created by Geng Qing on 9/18/2018
 **/
public class Cnedu extends BaseDoc implements PagingOnlineDoc {


    @Override
    public void open(String url) {
        this.startUrl = url;
        this.url = url;
    }

    @Override
    public String getUrl() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public String getPage(int pageNumber) {
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.get(url);
        WebElement webElement = driver.findElementById("fontzoom");
        String content = webElement.getText();
        driver.close();
        return content;
    }

    @Override
    public int getPageCount() {
        return 0;
    }
}
