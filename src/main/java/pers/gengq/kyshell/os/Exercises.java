package pers.gengq.kyshell.os;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by gengqing on 8/21/2018
 **/

@Component
public class Exercises {

    public static final String CSS_CLASS = "fc2e";
    private String url;

    public Exercises(@Value("${os.exercises}") String url) {
        this.url = url;
    }

    public String getExercises() throws IOException {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage(url);
            DomNodeList<DomNode> list = page.querySelector(".fc2e");
            if (list != null) {
                DomElement domElement = (DomElement) list.get(0);
                domElement.click();
            }
            final String pageAsText = page.asText();
            return pageAsText;
        }
    }

    public String getContent() {
        HtmlUnitDriver driver = new HtmlUnitDriver(true);
        driver.get(url);


        WebDriverWait webDriverWait = new WebDriverWait(driver, 100);
        webDriverWait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                try {
                    d.findElement(By.className(CSS_CLASS));
                    return true;
                } catch (NoSuchElementException e) {
                    return false;
                }
            }
        });


        String script = "document.getElementsByClassName(\"moreBtn goBtn\")[0].click();";
        driver.executeScript(script);
        
        webDriverWait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                try {
                    WebElement webElement = d.findElement(By.className(".page-input"));
                    return webElement.getText().equals("4");
                } catch (NoSuchElementException e) {
                    return false;
                }
            }
        });


//        .page-input

        System.out.println("Page title is: " + driver.getPageSource());

        //Close the browser
//        driver.quit();
        return driver.getPageSource();

    }
}
