package Pages;
import Utils.Helper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;


public class AccountCreatedPage extends BasePage<AccountCreatedPage> {

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    //variables
    private WebDriverWait wait;

    //Locators
    private final By AccountCreatedMessageLocator = By.cssSelector(".col-sm-offset-1 h2 b");
    private final By ContinueButtonLocator = By.cssSelector("a[data-qa='continue-button']");


    //Actions
    @Step ("Click Continue Button")
    public HomePage clickContinueButton() {
        framework.click(ContinueButtonLocator);
        closeGoogleVignetteIfPresent();
        return new HomePage(this.driver);
    }

    //verifications
    @Step ("Verify Account Created Message")
    public AccountCreatedPage verifyAccountCreatedMessage(String expectedMessage) throws IOException {
        verification.assertEquals(framework.getText(AccountCreatedMessageLocator), expectedMessage, "ACCOUNT CREATED! is not visible");
        Helper.saveScreenshot("Successful Registration" ,driver);
        return this;
    }
}