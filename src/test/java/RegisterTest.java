import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import config.baseSetup;
import util.util;
import pages.login.*;
import pages.register.*;
import util.util;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class RegisterTest extends baseSetup{

    String name = new Faker().name().name();
    String email =new Faker().internet().emailAddress();
    String pass = new Faker().internet().password().toString();

    @Test(priority = 1, description = "Valid Login")
    public void validRegister(){
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.register_text.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        RegisterScreen registerScreen = new RegisterScreen(driver);
        registerScreen.validRegister(name,email,pass);
        Assert.assertEquals(registerScreen.reg_snack_bar.getText(),"Registration Successful");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //will lookup until expectedCondition disappear - will retry after enough
        wait.until(ExpectedConditions.invisibilityOf(registerScreen.reg_snack_bar));
        util util = new util(driver);
        util.scrollToEnd();
        registerScreen.btnLogin.click();
    }


    @Test(priority = 1, description = "Valid Register and Login")
    public void RegisterAndLogin(){
        validRegister();
        LoginScreen loginScreen=new LoginScreen(driver);
        loginScreen.validLogin(email,pass);
        Assert.assertEquals(loginScreen.email_info.getText(),email);
        Allure.description("Valid Login.");
    }

    @Test(priority = 1, description = "Valid Login")
    public void invalidRegister_passMissmatch(){
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.register_text.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        RegisterScreen registerScreen = new RegisterScreen(driver);
        registerScreen.invalidRegister(name,email,pass);
        Assert.assertEquals(registerScreen.password_missmatch.getText(),"Password Does Not Matches");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test(priority = 1, description = "Valid Login")
    public void invalidRegister_invalidEmail(){
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.register_text.click();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        RegisterScreen registerScreen = new RegisterScreen(driver);
        registerScreen.invalidRegister(name,"test@",pass);
        Assert.assertEquals(registerScreen.invalid_email.getText(),"Enter Valid Email");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
}

