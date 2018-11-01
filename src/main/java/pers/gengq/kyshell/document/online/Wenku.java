package pers.gengq.kyshell.document.online;

import lombok.Synchronized;
import org.apache.http.util.Asserts;
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
public class Wenku implements PagingOnlineDoc, Closeable {

    private String url;

    private Integer pageCount;

    private RemoteWebDriver driver;

    private Wenku() {
        this.driver = FireFoxDriverFactory.create();
    }

    private static Wenku WENKU;

    private boolean isClickMore;

    @Synchronized
    public static Wenku instance() {
        if (WENKU == null) {
            WENKU = new Wenku();
        }
        return WENKU;
    }

    @Override
    public void open(String url) {
        isClickMore = false;
        Asserts.notNull(url, "url require not null");
        if (url.equals(this.url)) {
            return;
        }
        this.url = url;
        driver.get(url);
        try {
            Thread.sleep(3*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void waitMany() {
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
        if (pageNumber > 3) {
            if (!isClickMore) {
                waitMany();
                isClickMore = true;
            }
        }
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
        String string = driver.findElement(By.id(pageId)).getText();
        return WenkuFormat.format(string);
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
        driver = null;
    }
}
