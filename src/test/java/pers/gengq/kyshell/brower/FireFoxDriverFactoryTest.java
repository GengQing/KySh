package pers.gengq.kyshell.brower;

import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FireFoxDriverFactoryTest {

    @Test
    public void create() {
        FirefoxDriver firefoxDriver = FireFoxDriverFactory.create();
    }
}