package Pages;

import Header.HeaderComponent;
import Header.SubscriptionComponent;
import Utils.Framework;
import Utils.Helper;
import Verifications.Verification;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage<T> {

    //Variables
    private static Helper tdriver;
    protected WebDriver driver;
    protected Framework framework;
    protected Verification verification;
    private final SubscriptionComponent subscription;
    private final HeaderComponent header;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.framework = new Framework(driver);
        this.verification = new Verification(driver);
        this.header = new HeaderComponent(driver);
        this.subscription = new SubscriptionComponent(driver);
    }

    //Actions
    @SuppressWarnings("unchecked")
    protected T self() {
        return (T) this;
    }

    public void closeAutomationExerciseAds() {
        try {
            // Try to find and click close button
            List<WebElement> closeButtons = driver.findElements(
                    By.cssSelector("div[aria-label='Close ad'], #dismiss-button, div.close-button")
            );

            for (WebElement btn : closeButtons) {
                if (btn.isDisplayed()) {
                    btn.click();
                    Thread.sleep(1000);
                    break;
                }
            }

            // If no close button found, remove ad containers
            if (closeButtons.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript(
                        "document.querySelectorAll('ins.adsbygoogle, .google-auto-placed').forEach(ad => ad.remove());"
                );
            }

        } catch (Exception e) {
            // Silently ignore
        }
    }

    public String getURL(){
        return framework.getCurrentUrl();
    }

    public HomePage goToHome() {
        header.goToHome();
        return new HomePage(this.driver);
    }

    public CartPage goToCartPage() {
        header.goToCart();
        return new CartPage(this.driver);
    }

    public ProductPage goToProductPage() {
        header.goToProducts();
        return new ProductPage(this.driver);
    }

    public LoginPage goToLoginPage() {
        header.goToSignupLogin();
        return new LoginPage(this.driver);
    }

    public SignupPage goToSignupPage() {
        header.goToSignupLogin();
        return new SignupPage(this.driver);
    }

    public ContactUsPage goToContactUsPage() {
        header.goToContactUs();
        return new ContactUsPage(this.driver);
    }

    public TestCasesPage goToTestCasesPage() {
        header.goToTestCasesPage();
        return new TestCasesPage(this.driver);
    }

    @Step ("Delete Logged in Account")
    public DeleteAccountPage deleteAccount() {
        header.deleteAccount(this.driver);
        return new DeleteAccountPage(this.driver);
    }

    public T scrollToSubscription() {
        subscription.scrollToSubscriptionSection();
        return self();
    }

    public T ScrollToTop() {
        framework.scrollToElement(header.getHomeIconLocator());
        return self();
    }

    public T clickOnScrollUpButton() {
        framework.click(header.getScrollUpButtonLocator());
        return self();
    }

    public T subscriptionProcess(String email) {
        subscription.enterSubscriptionEmail(email);
        subscription.clickOnSubscriptionButton();
        return self();
    }

    //verification

    @Step ("Verify 'SUBSCRIPTION' text is visible")
    public T verifySubscriptionSectionText() {
        verification.assertTrue(
                subscription.getSubscriptionText().contains("SUBSCRIPTION"),
                "'SUBSCRIPTION' text is not visible"
        );
        return self();
    }

    @Step ("Verify successful subscription message is visible")
    public T verifySubscriptionSuccessfulMessage() {
        verification.assertEquals(
                subscription.getSuccessfulSubscriptionMessage(), "You have been successfully subscribed!" ,
                "Subscription process failed"
        );
        return self();
    }

    @Step ("Verify that Logged in with the username")
    public T verifyLoggedInUser(String expectedUsername) {
        String actualUsername = header.getLoggedInUser(this.driver);
        verification.assertEquals(actualUsername, expectedUsername, "Logged in username does not match expected.");
        return self();
    }
}
