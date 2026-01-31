package tests.ui.Authentication;

import Models.LoginData;
import Pages.HomePage;
import Pages.LoginPage;
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


@Epic("Automation Exercise")
@Feature("Authentication")
@Owner("Feras Osama")
public class LogoutTests extends BaseTests {
    private LoginPage loginPage;
    private LoginData validCredential;

    @BeforeClass(groups = {"authentication" ,"regression"})
    public void setupClass() throws FileNotFoundException {
        validCredential = Helper.ReadUser("users.json","validUser",LoginData.class);
    }
    //4
    @Test(groups = {"authentication", "regression"})
    @Story("User Session Management")
    @Description("Verify that users can successfully log out and their session is terminated")
    @Severity(SeverityLevel.CRITICAL)
    public void positiveLogout() throws IOException {
        homePage
                .verifyHomePageVisible()
                .goToLoginPage()
                .verifyLoginToYourAccountVisible()
                .positiveLoginExistingUser(
                        validCredential.getEmail(),
                        validCredential.getPassword()).verifyLoggedInUser(validCredential.getName())
                .LogoutExistingUser().verifyLoginToYourAccountVisible();
        Helper.saveScreenshot("Successful Logout" ,driver);
        }

}
