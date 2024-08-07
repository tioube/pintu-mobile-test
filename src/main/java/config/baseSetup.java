package config;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class baseSetup {
    public AndroidDriver driver;

    @BeforeMethod
    public AndroidDriver setup() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName","Android");
        caps.setCapability("platformVersion","13");
        caps.setCapability("udid","9dded5e0");
        caps.setCapability("automationName","UIAutomator2");
        caps.setCapability("appPackage","com.loginmodule.learning");
        caps.setCapability("appActivity","com.loginmodule.learning.activities.LoginActivity");


        URL url = new URL("http://127.0.0.1:4723");
        driver = new AndroidDriver(url, caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        return driver;
    }

    @AfterMethod
    public void closeApp(){
        driver.quit();
    }
}
