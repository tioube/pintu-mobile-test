package pages.login;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginScreen {
    @FindBy(id = "com.loginmodule.learning:id/textInputEditTextEmail")
    WebElement email_text;

    @FindBy(id = "com.loginmodule.learning:id/textInputEditTextPassword")
    WebElement pass_text;

    @FindBy(id = "com.loginmodule.learning:id/appCompatButtonLogin")
    WebElement btnLogin;

    @FindBy(id = "com.loginmodule.learning:id/textViewLinkRegister")
    public WebElement register_text;

    @FindBy(id = "com.loginmodule.learning:id/textViewName")
    public WebElement email_info;

    @FindBy(id ="com.loginmodule.learning:id/snackbar_text")
    public WebElement snack_bar_info;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Enter Valid Email\"]")
    public WebElement red_line_error_username;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Enter Valid Email\"]")
    public WebElement red_line_error_pass;

    AndroidDriver driver;

    public LoginScreen(AndroidDriver driver){
        this.driver =driver;
        PageFactory.initElements( new AppiumFieldDecorator(driver) ,this);
    }

    public void validLogin(String email, String pass){
        email_text.click();
        email_text.sendKeys(email);
        pass_text.click();
        pass_text.sendKeys(pass);
        driver.navigate().back();
        btnLogin.click();
    }

}
