import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.login.LoginScreen;
import config.baseSetup;
import util.util;
import pages.login.*;

public class LoginTest extends baseSetup{

    String name = new Faker().name().name();
    String email =new Faker().internet().emailAddress();
    String pass = new Faker().internet().password().toString();

    @Test(priority = 1, description = "Valid Login")
    public void validLogin(){
        util util = new util(driver);
        util.register(name,email,pass);
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.validLogin(email,pass);
        Assert.assertEquals(loginScreen.email_info.getText(),email);
        Allure.description("Valid Login.");

    }

    @Test(priority = 1, description = "inValid Login")
    public void invalidLogin(){
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.validLogin(email,pass);
        Assert.assertEquals(loginScreen.snack_bar_info.getText(),"Wrong Email or Password");
        Allure.description("inValid Login.");
    }

    @Test(priority = 1, description = "Missing Email")
    public void missingEmail(){
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.validLogin("",pass);
        Assert.assertEquals(loginScreen.red_line_error_username.getText(),"Enter Valid Email");
        Allure.description("Missing Email.");
    }

    @Test(priority = 1, description = "Missing Pass")
    public void missingPassword(){
        LoginScreen loginScreen = new LoginScreen(driver);
        loginScreen.validLogin("",pass);
        Assert.assertEquals(loginScreen.red_line_error_pass.getText(),"Enter Valid Email");
        Allure.description("Missing Password.");
    }
}
