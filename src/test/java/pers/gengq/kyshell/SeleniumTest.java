package pers.gengq.kyshell;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.test.web.servlet.htmlunit.webdriver.WebConnectionHtmlUnitDriver;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by gengqing on 8/21/2018
 **/
public class SeleniumTest {
    private static final String HTTP_WWW_BAIDU_COM = "http://www.baidu.com";
    public static String str = "http://nexus.oa.yitopapp.com/#nexus-search;quick~cxy";

    @Test
    public void resynchronizinresynchronizingTestgTest() {

        WebDriver driver = new HtmlUnitDriver(true);
        driver.get(str);
        System.out.println("等等js 加载-------------");
//        driver.waitForBackgroundJavaScript(3*60*1000);
        final By repositoryPath = By.name("repositoryPath");
        WebDriverWait webDriverWait = new WebDriverWait(driver, 60 * 2);
        webDriverWait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver input) {
                WebElement element = input.findElement(By.xpath("//div[name='repositoryPath']"));
                return element != null;
//                return input.findElement(By.partialLinkText("cxydevice-redis")) != null;
            }
        });
        System.out.println("js 加载完成-------------");
        System.out.println(driver.getPageSource());
        WebElement element = driver.findElement(repositoryPath);
        System.out.println(element.getClass());
        WebElement a = element.findElement(By.tagName("a"));
        System.out.println("可以下载的jar=" + a.getAttribute("href"));
        driver.quit();

    }

    @Test
    public void test() throws InterruptedException {
        WebConnectionHtmlUnitDriver driver = new WebConnectionHtmlUnitDriver(true);
        WebClient webClient = driver.getWebClient();
        webClient.getOptions().setCssEnabled(false);//设置css是否生效
        webClient.getOptions().setJavaScriptEnabled(true);//设置js是否生效
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置ajax请求
        webClient.getOptions().setTimeout(10000);

        driver.get(str);
        webClient.waitForBackgroundJavaScript(60 * 1000);

        System.out.println("page=" + driver.getPageSource());
        driver.getWindowHandles().forEach(s -> System.out.println("windows=" + s));
        WebElement element = driver.findElement(By.id("x-history-frame"));
        System.out.println("frame=" + element.getTagName() + " ----" + element.getText());
        driver.quit();
    }

    @Test
    public void testcss() {
        WebConnectionHtmlUnitDriver driver = new WebConnectionHtmlUnitDriver(true);
        driver.get("http://www.baidu.com");
        driver.getWebClient().waitForBackgroundJavaScript(60 * 1000);

        System.out.println("Page title is: " + driver.getTitle());

        WebElement element = driver.findElement(By.cssSelector("#kw"));
        element.sendKeys("java");

        WebElement element1 = driver.findElement(By.cssSelector("#su"));
        element1.submit();


        // Check the title of the page

//
//        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver d) {
//                return d.getCurrentUrl().contains("java");
//            }
//        });

        System.out.println("Page title is: " + driver.getPageSource());
        //Close the browser
        driver.quit();
    }


    @Test
    public void google() throws Exception {
        WebDriver driver = new FirefoxDriver();

        // And now use this to visit Google
        driver.get("https://www.baidu.com");
//        // Alternatively the same thing can be done like this
//        // driver.navigate().to("http://www.google.com");
//
//        // Find the text input element by its name
//        WebElement element = driver.findElement(By.name("q"));
//
//        // Enter something to search for
//        element.sendKeys("Cheese!");
//
//        // Now submit the form. WebDriver will find the form for us from the element
//        element.submit();
//
//        // Check the title of the page
//        System.out.println("Page title is: " + driver.getTitle());
//
//        // Google's search is rendered dynamically with JavaScript.
//        // Wait for the page to load, timeout after 10 seconds
//        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
//            public Boolean apply(WebDriver d) {
//                return d.getTitle().toLowerCase().startsWith("cheese!");
//            }
//        });
//
//        // Should see: "cheese! - Google Search"
//        System.out.println("Page title is: " + driver.getTitle());

        //Close the browser
        driver.quit();
    }


    @Test
    public void htmlPage() throws Exception {
        WebClient webClient = new WebClient();//设置浏览器
        webClient.getOptions().setCssEnabled(false);//设置css是否生效
        webClient.getOptions().setJavaScriptEnabled(true);//设置js是否生效
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置ajax请求
        webClient.getOptions().setTimeout(10000);
        HtmlPage htmlPage = webClient.getPage("https://www.baidu.com");//访问路径设置
        webClient.waitForBackgroundJavaScript(60 * 1000);
        System.out.println(htmlPage.asXml());
        webClient.close();
    }

    @Test
    public void homePage() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage(HTTP_WWW_BAIDU_COM);
            System.out.println(page.getTitleText());
            System.out.println("网页内容是：");
            System.out.println(page.asXml());
        }
    }

    @Test
    public void getElements() throws Exception {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage(HTTP_WWW_BAIDU_COM);
            page.getHtmlElementById("kw");
        }
    }

    @Test
    public void ftest() throws Exception {
        WebDriver driver = new HtmlUnitDriver();

        //Launch the Online Store Website
        driver.get("http://www.store.demoqa.com");

        // Print a Log In message to the screen
        System.out.println("Successfully opened the website www.Store.Demoqa.com");

        //Wait for 5 Sec
        Thread.sleep(5);

        // Close the driver
        driver.quit();

    }

    @Test
    public void testbaidu() {
        WebConnectionHtmlUnitDriver driver = new WebConnectionHtmlUnitDriver(true);
        WebClient webClient = driver.getWebClient();
        webClient.getOptions().setCssEnabled(false);//设置css是否生效
        webClient.getOptions().setJavaScriptEnabled(true);//设置js是否生效
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());//设置ajax请求
        webClient.getOptions().setTimeout(10000);

        driver.get("https://www.baidu.com/");

        final By input = By.name("wd");
        final By submit = By.id("su");

        Wait<WebConnectionHtmlUnitDriver> wait = new FluentWait(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement element = wait.until(webConnectionHtmlUnitDriver -> webConnectionHtmlUnitDriver.findElement(input));

        element.sendKeys("java");
        WebElement element2 = driver.findElement(submit);
        element2.submit();

        Wait<WebConnectionHtmlUnitDriver> nextPage = new FluentWait(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class);
        Object o = nextPage.until(webConnectionHtmlUnitDriver ->
                webConnectionHtmlUnitDriver.findElement(By.className("n")));

        System.out.println(o.getClass());
        System.out.println("source ------");
        System.out.println("source ------");
        System.out.println("source ------");
        System.out.println("source ------");
        System.out.println(driver.getPageSource());


//        System.out.println("page=" + driver.getPageSource());
//        driver.getWindowHandles().forEach(s -> System.out.println("windows=" + s));
//        WebElement element = driver.findElement(By.id("x-history-frame"));
//        System.out.println("frame=" + element.getTagName() + " ----" + element.getText());
        System.out.println("文件原码是--------------");
        System.out.println("文件原码是--------------");
        System.out.println("文件原码是--------------");
        System.out.println(driver.getPageSource());
        driver.quit();
    }

    public void testWait() {
        WebConnectionHtmlUnitDriver driver = new WebConnectionHtmlUnitDriver(true);
        Wait wait = new FluentWait(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(5, SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = (WebElement) wait.until(o -> null);
    }


}
