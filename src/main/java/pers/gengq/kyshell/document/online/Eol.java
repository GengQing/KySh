package pers.gengq.kyshell.document.online;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.util.Assert;

/**
 * Created by gengqing on 9/4/2018
 **/
public class Eol extends BaseDoc implements PagingOnlineDoc {

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
        generateUrl(pageNumber);
        HtmlUnitDriver driver = new HtmlUnitDriver();
        driver.get(url);
        WebElement webElement = driver.findElementByClassName("article");
        String content = webElement.getText();
        driver.close();
        return EolFormat.format(content);
    }

    private void generateUrl(int pageNumber) {
        if (pageNumber != 1) {
            String suffix = ".shtml";
            url = startUrl.replace(suffix, "_" + (pageNumber - 1) + suffix);
        }
    }

    @Override
    public int getPageCount() {
        return 0;
    }
}
