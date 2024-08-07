package pages.register;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterScreen {

    @FindBy(id = "com.loginmodule.learning:id/textInputEditTextName")
WebElement name_text;

    @FindBy(id = "com.loginmodule.learning:id/textInputEditTextEmail")
    WebElement email_text_reg;

    @FindBy(id = "com.loginmodule.learning:id/textInputEditTextPassword")
    WebElement pass_text_reg;

    @FindBy(id = "com.loginmodule.learning:id/textInputEditTextConfirmPassword")
    WebElement pass_conf_text_reg;

    @FindBy(id = "com.loginmodule.learning:id/appCompatButtonRegister")
    WebElement btnRegister;

    @FindBy(id ="com.loginmodule.learning:id/appCompatTextViewLoginLink")
    public WebElement btnLogin;

    @FindBy(id="com.loginmodule.learning:id/snackbar_text")
    public WebElement reg_snack_bar;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Password Does Not Matches\"]")
    public WebElement password_missmatch;

    @FindBy(xpath = "//android.widget.TextView[@text=\"Enter Valid Email\"]")
    public WebElement invalid_email;



    AndroidDriver driver;

    public RegisterScreen(AndroidDriver driver){
        this.driver =driver;
        PageFactory.initElements( new AppiumFieldDecorator(driver) ,this);
    }

    public void validRegister(String name,String email,String pass){
        name_text.click();
        name_text.sendKeys(name);
        email_text_reg.click();
        email_text_reg.sendKeys(email);
        pass_text_reg.click();
        pass_text_reg.sendKeys(pass);
        driver.navigate().back();
        pass_conf_text_reg.click();
        pass_conf_text_reg.sendKeys(pass);
        driver.navigate().back();
        btnRegister.click();
    }

    public void invalidRegister(String name,String email,String pass){
        name_text.click();
        name_text.sendKeys(name);
        email_text_reg.click();
        email_text_reg.sendKeys(email);
        pass_text_reg.click();
        pass_text_reg.sendKeys(pass);
        driver.navigate().back();
        pass_conf_text_reg.click();
        pass_conf_text_reg.sendKeys(pass+1);
        driver.navigate().back();
        btnRegister.click();
    }
}

