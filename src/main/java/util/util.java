package util;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.login.LoginScreen;
import pages.register.RegisterScreen;
import util.util;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class util {
    AndroidDriver driver;

    public util(AndroidDriver driver){
        this.driver = driver;
        PageFactory.initElements( new AppiumFieldDecorator(driver) ,this);
    }

    public  void scrollUp() {
        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();

        int startX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.8); // Start from bottom
        int endY = (int) (screenHeight * 0.2); // End at top

        TouchAction<?> touchAction = new TouchAction<>(driver);
        touchAction.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    public void scrollToEnd() {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollToEnd(10);"));
    }

    public void scrollToTop() {
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollToBeginning(10);"));
    }

    public void register(String name, String email,String pass){
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.register_text.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        RegisterScreen registerScreen = new RegisterScreen(driver);
        registerScreen.validRegister(name,email,pass);
        Assert.assertEquals(registerScreen.reg_snack_bar.getText(),"Registration Successful");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(registerScreen.reg_snack_bar));
        util util = new util(driver);
        util.scrollToEnd();
        registerScreen.btnLogin.click();
    }

}
