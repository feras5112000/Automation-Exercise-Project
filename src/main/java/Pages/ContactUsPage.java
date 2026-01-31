package Pages;

import Utils.Helper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ContactUsPage extends BasePage<ContactUsPage> {

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By confirmationMessageLocator = By.cssSelector(".contact-form > h2");
    private final By contactNameLocator = By.cssSelector("input[data-qa = 'name']");
    private final By contactEmailLocator = By.cssSelector("input[data-qa = 'email']");
    private final By contactSubjectLocator = By.cssSelector("input[data-qa = 'subject']");
    private final By contactMessageLocator = By.cssSelector("textarea[data-qa = 'message']");
    private final By uploadFileLocator = By.cssSelector("input[name='upload_file']");
    private final By submitButtonLocator = By.cssSelector("input[data-qa='submit-button']");
    private final By successfulSubmitMessageLocator = By.cssSelector("div.status.alert.alert-success");
    private final By goToHomeButtonLocator = By.cssSelector("a.btn.btn-success");


    //Actions
    public ContactUsPage open() {
        framework.navigateTo(Helper.get("contactUrl"));
        return this;
    }

    public ContactUsPage verifyGetInTouchText() {
        verification.assertTrue(
                framework.getText(confirmationMessageLocator).contains("GET IN TOUCH"),
                "'GET IN TOUCH' text is not displayed");
        return this;
    }

    public ContactUsPage fillContactForm (String name, String email, String subject, String message)
    {
        framework.sendText(contactNameLocator, name);
        framework.sendText(contactEmailLocator, email);
        framework.sendText(contactSubjectLocator, subject);
        framework.sendText(contactMessageLocator, message);
        return this;
    }

    public ContactUsPage uploadContactFormFile (String filePath)
    {
        framework.sendText(uploadFileLocator, filePath);
        return this;
    }

    public ContactUsPage clickSubmitButton()
    {
        framework.click(submitButtonLocator);
        return this;
    }

    public ContactUsPage acceptAlert()
    {
        Alert alert = driver.switchTo().alert();
        alert.accept();
        return this;
    }

    public HomePage goToHomePage()
    {
        framework.click(goToHomeButtonLocator);
        return new HomePage(this.driver);
    }

    //verifications
    public ContactUsPage verifySuccessMessage()
    {
        verification.assertEquals(
                framework.getText(successfulSubmitMessageLocator),"Success! Your details have been submitted successfully.",
                "Success Message is not displayed"
        );
        return this;
    }
}
