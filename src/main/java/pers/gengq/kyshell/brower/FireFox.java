package pers.gengq.kyshell.brower;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Created by gengqing on 8/24/2018
 **/
public class FireFox {

    private static FirefoxDriver driver;

    public static synchronized FirefoxDriver firefoxDriver() {
        if (driver == null) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setHeadless(true);
            driver = new FirefoxDriver(firefoxOptions);
        }
        return driver;
    }
}
