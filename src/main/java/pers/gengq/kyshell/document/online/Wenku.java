package pers.gengq.kyshell.document.online;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.StringUtils;
import pers.gengq.kyshell.brower.FireFoxDriverFactory;

import java.io.Closeable;

/**
 * Created by gengqing on 8/24/2018
 **/
public class Wenku implements OnlineDoc, Closeable {

    private String url;

    private Integer pageCount;

    private RemoteWebDriver driver;

    public Wenku(String url) {
        this.url = url;
        prepareWeb();
    }

    private void prepareWeb() {
        this.driver = FireFoxDriverFactory.create();
        driver.get(url);
        waitMoreBtn();
        clickMoreBtn();
        waitPageFour();

    }

    private void waitMoreBtn() {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 100);
        webDriverWait.until(webDriver -> {
            try {
                webDriver.findElement(By.className("moreBtn"));
                return true;
            } catch (NoSuchElementException e) {
                System.out.println("waiting more button...");
                return false;
            }
        });
    }

    private void clickMoreBtn() {
        String script = "document.getElementsByClassName(\"moreBtn goBtn\")[0].click();";
        driver.executeScript(script);
    }

    private void waitPageFour() {
        new WebDriverWait(driver, 100).until(webDriver -> {
            try {
                String value = pageInput().getAttribute("value");
                return value.equals("4");
            } catch (NoSuchElementException e) {
                System.out.println("waiting Page button-----");
                return false;
            }
        });
    }


    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public String getPage(int pageNumber) {
        WebElement pageInput = pageInput();
        pageInput.clear();
        pageInput.sendKeys(String.valueOf(pageNumber), "\n");

        String pageId = "pageNo-" + pageNumber;
        new WebDriverWait(driver, 60).until(webDriver -> {
                    try {
                        return !StringUtils.isEmpty(webDriver.findElement(By.id(pageId)).getText().trim());
                    } catch (NoSuchElementException e) {
                        System.out.println("waiting content----");
                        return false;
                    }
                }
        );
        return driver.findElement(By.id(pageId)).getText();
    }


    @Override
    public int getPageCount() {
        if (this.pageCount != null) {
            return this.pageCount;
        }
        return 0;
    }


    private WebElement pageInput() {
        return driver.findElement(By.className("page-input"));
    }

    @Override
    public void close() {
        driver.close();
    }
}
