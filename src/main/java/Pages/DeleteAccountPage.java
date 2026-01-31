package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeleteAccountPage extends BasePage<DeleteAccountPage> {

    public DeleteAccountPage(WebDriver driver){
        super(driver);
    }

    //Locators
    private final By AccountDeletedLocator = By.tagName("b");
    private final By ContinueButtonLocator = By.cssSelector("a.btn.btn-primary");

    //Actions
    public HomePage clickContinueButton(){
        WebDriverWait explicitWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(5));
        explicitWait.until(ExpectedConditions.titleIs("Automation Exercise - Account Created"));
        framework.click(ContinueButtonLocator);
        return new HomePage(this.driver);
    }

    //verification
    public DeleteAccountPage verifyAccountDeletedMessage(){
        verification.assertEquals(framework.getText(AccountDeletedLocator),"ACCOUNT DELETED!", "ACCOUNT DELETED! is not visible");
        return this;
    }

}
