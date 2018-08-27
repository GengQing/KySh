package pers.gengq.kyshell;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import pers.gengq.kyshell.brower.FireFoxDriverFactory;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertFalse;

/**
 * Created by gengqing on 8/22/2018
 **/
public class FirefoxDriverTest {

    @Test
    public void test() throws Exception {

        Properties ps = PropertiesLoaderUtils.loadAllProperties("application.properties");
        String s = ps.getProperty("os.exercises");
        assertNotNull(s);
        File geckodriver = ResourceUtils.getFile("geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", geckodriver.getAbsolutePath());
        FirefoxDriver driver = new FirefoxDriver();
        driver.get(s);
        try {
            waitMoreBtn(driver);

            clickMoreBtn(driver);

            waitPageFour(driver);

            WebElement pageInput = driver.findElement(By.className("page-input"));
            pageInput.clear();
            String pageNumber = "9";
            pageInput.sendKeys(pageNumber, "\n");

            String pageName = "pageNo-9";
            new WebDriverWait(driver, 100).until(webDriver -> {
                        try {
                            return !StringUtils.isEmpty(webDriver.findElement(By.id(pageName)).getText().trim());
                        } catch (NoSuchElementException e) {
                            System.out.println("waiting content----");
                            return false;
                        }
                    }
            );
            WebElement page = driver.findElement(By.id(pageName));

            String text = page.getText().trim();
            assertFalse(StringUtils.isEmpty(text));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }


    }

    private void clickMoreBtn(FirefoxDriver driver) {
        String script = "document.getElementsByClassName(\"moreBtn goBtn\")[0].click();";
        driver.executeScript(script);
    }

    private void waitPageFour(FirefoxDriver driver) {
        new WebDriverWait(driver, 100).until(webDriver -> {
            try {
                WebElement element = webDriver.findElement(By.className("page-input"));
                String value = element.getAttribute("value");
                return value.equals("4");
            } catch (NoSuchElementException e) {
                System.out.println("waiting Page button-----");
                return false;
            }
        });
    }

    private void waitMoreBtn(FirefoxDriver driver) {
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
}
