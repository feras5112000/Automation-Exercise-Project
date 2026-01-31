package Header;

import Pages.*;
import Utils.Framework;
import Utils.Helper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderComponent {
    private final Framework framework;

    private final By HomeIconLocator = By.cssSelector("li > a[href='/']");
    private final By ProductIconLocator = By.cssSelector("li > a[href='/products']" );
    private final By CartIconLocator = By.cssSelector("li > a[href='/view_cart']" );
    private final By SignupLoginIconLocator = By.cssSelector("li > a[href='/login']" );
    private final By ContactUsIconLocator = By.cssSelector("li > a[href='/contact_us']");
    private final By TestCasesIconLocator = By.cssSelector("li > a[href='/test_cases']");
    private final By scrollUpButtonLocator = By.cssSelector("a[href='#top']");
    private final By deleteAccountLocator = By.cssSelector("a[href='/delete_account']");
    private final By usernameLabelLocator = By.tagName("b");


    public HeaderComponent(WebDriver driver) {
        this.framework = new Framework(driver);
    }

    public By getScrollUpButtonLocator(){
        return scrollUpButtonLocator;
    }

    public By getHomeIconLocator(){
        return HomeIconLocator;
    }

    public void goToHome() {
        framework.click(HomeIconLocator);
    }

    public void goToProducts() {
        framework.click(ProductIconLocator);
    }

    public void goToCart() {
        framework.click(CartIconLocator);
    }

    public void goToSignupLogin() {
        framework.click(SignupLoginIconLocator);
    }

    public void goToContactUs(){
        framework.click(ContactUsIconLocator);
    }

    public void goToTestCasesPage(){
        framework.click(TestCasesIconLocator);
    }

    @Step("Delete Logged in Account")
    public void deleteAccount(WebDriver driver) {
        framework.click(deleteAccountLocator);
    }

    @Step ("Get Logged in with the username")
    public String getLoggedInUser(WebDriver driver) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(usernameLabelLocator));
        return framework.getText(usernameLabelLocator);
    }




}
