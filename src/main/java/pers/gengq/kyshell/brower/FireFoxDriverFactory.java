package pers.gengq.kyshell.brower;

import lombok.Synchronized;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Created by gengqing on 8/24/2018
 **/
public class FireFoxDriverFactory {

    private static FirefoxDriver driver;

    private static Thread closeDriver = new Thread(() -> driver.quit());

    @Synchronized
    public static FirefoxDriver create() {
        if (driver == null) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setHeadless(true);
            driver = new FirefoxDriver(firefoxOptions);
            Runtime.getRuntime().addShutdownHook(closeDriver);
        }
        return driver;
    }
}
