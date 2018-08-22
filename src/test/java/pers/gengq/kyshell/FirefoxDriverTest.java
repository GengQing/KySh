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
        File geckodriver = ResourceUtils.getFile("classpath:geckodriver.exe");
        System.setProperty("webdriver.gecko.driver", geckodriver.getAbsolutePath());
        FirefoxDriver driver = new FirefoxDriver();
        driver.get(s);

        WebDriverWait webDriverWait = new WebDriverWait(driver, 100);
        webDriverWait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                try {
                    WebElement element = d.findElement(By.className("fc2e"));
                    return true;
                } catch (NoSuchElementException e) {
                    return false;
                }
            }
        });
        String script = "document.getElementsByClassName(\"moreBtn goBtn\")[0].click();";
        driver.executeScript(script);
        Thread.sleep(5 * 1000);
        WebElement pageInput = driver.findElement(By.className("page-input"));
        assertNotNull(pageInput);
        pageInput.clear();
        pageInput.sendKeys("2", "\n");

        Thread.sleep(3000);
        WebElement page = driver.findElement(By.id("pageNo-2"));
        String text = page.getText().trim();
        assertFalse(StringUtils.isEmpty(text));
        System.out.println(text);
    }
}
