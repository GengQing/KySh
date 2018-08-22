package pers.gengq.kyshell.os;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

/**
 * Created by gengqing on 8/21/2018
 **/

@Component
public class BdExercises implements Exercises {

    private String url;

    public BdExercises(@Value("${os.exercises}") String url) {
        this.url = url;
        File geckodriver = null;
        try {
            geckodriver = ResourceUtils.getFile("classpath:geckodriver.exe");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setProperty("webdriver.gecko.driver", geckodriver.getAbsolutePath());
    }


    @Override
    public String getContent(int pageNumber) throws Exception {

        FirefoxDriver driver = new FirefoxDriver();
        driver.get(url);

        WebDriverWait webDriverWait = new WebDriverWait(driver, 100);
        webDriverWait.until((ExpectedCondition<Boolean>) d -> {
            try {
                d.findElement(By.className("fc2e"));
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        });

        String script = "document.getElementsByClassName(\"moreBtn goBtn\")[0].click();";
        driver.executeScript(script);

        Thread.sleep(5 * 1000);
        WebElement pageInput = driver.findElement(By.className("page-input"));
        pageInput.clear();
        pageInput.sendKeys(String.valueOf(pageNumber), "\n");

        Thread.sleep(3000);
        WebElement page = driver.findElement(By.id(getPageId(pageNumber)));
        String text = page.getText().trim();
        return text;
    }

    private String getPageId(int pageNumber) {
        return "pageNo-" + pageNumber;
    }


}
