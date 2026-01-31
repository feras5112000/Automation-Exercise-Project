package tests.ui.Authentication;

import Models.LoginData;
import Pages.HomePage;
import Utils.Helper;
import io.qameta.allure.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTests;

import java.io.FileNotFoundException;
import java.io.IOException;

import static Utils.Helper.generateUniqueValue;


@Epic("Automation Exercise")
@Feature("Authentication")
@Owner("Feras Osama")
public class LoginTests extends BaseTests {
    private LoginData existingUserData;

    @BeforeClass(groups = {"authentication" ,"regression"})
    public void setupClass() throws FileNotFoundException {
        System.out.println("Starting PositiveLogin tests");
        existingUserData = Helper.ReadUser("users.json","validUser", LoginData.class );
    }
    //2
    @Test(groups = {"authentication" ,"regression"})
    @Story("User Authentication")
    @Description("Verify that existing users can log in successfully with correct credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void positiveLoginTest() throws IOException {
        homePage
                .verifyHomePageVisible()
                .goToLoginPage()
                .verifyLoginToYourAccountVisible()
                .positiveLoginExistingUser(existingUserData.getEmail(), existingUserData.getPassword())
                .verifyLoggedInUser(existingUserData.getName());
        Helper.saveScreenshot("Successful Login" ,driver);
    }
    //3
    @Test(groups = {"authentication" ,"regression"})
    @Story("User Authentication - Negative Testing")
    @Description("Verify system behavior when users attempt to login with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void negativeLoginTest() throws IOException {
        homePage
                .verifyHomePageVisible()
                .goToLoginPage()
                .verifyLoginToYourAccountVisible()
                .negativeLoginExistingUser(
                        existingUserData.getEmail().replace("@", generateUniqueValue()+"@"),
                        existingUserData.getPassword() + generateUniqueValue())
                .verifyIncorrectLoginCredential("Your email or password is incorrect!");
        Helper.saveScreenshot("Successful not Login" ,driver);

    }

}


