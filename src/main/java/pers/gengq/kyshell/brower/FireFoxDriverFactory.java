package pers.gengq.kyshell.brower;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * Created by gengqing on 8/24/2018
 **/
public class FireFoxDriverFactory {

    public static FirefoxDriver create() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setHeadless(true);
        return new FirefoxDriver(firefoxOptions);
    }
}
