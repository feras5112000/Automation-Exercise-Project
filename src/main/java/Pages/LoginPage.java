package Pages;

import Utils.Helper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage<LoginPage>{

    private final By loginEmailLocator = By.cssSelector("input[data-qa='login-email']");
    private final By loginPasswordLocator = By.cssSelector("input[type='password']");
    private final By loginButtonLocator = By.cssSelector("button[data-qa='login-button']");
    private final By signupNameLocator = By.cssSelector("input[name='name']");
    private final By signupEmailLocator = By.cssSelector("input[data-qa='signup-email']");
    private final By signupButtonLocator = By.cssSelector("button[data-qa='signup-button']");
    private final By emailExistTextLocator = By.cssSelector("p[style= 'color: red;']");
    private final By visibleNewSignupText = By.cssSelector(".signup-form > h2");
    private final By visibleLoginToYourAccountText = By.cssSelector(".login-form > h2");
    private final By incorrectEmailOrPassText = By.cssSelector(".login-form p");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        framework.navigateTo(Helper.get("loginUrl"));
        return this;
    }

    @Step("sign up a NewUser")
    public SignupPage signupNewUser(String signupName, String signupEmail) {
        framework.sendText(signupNameLocator, signupName);
        framework.sendText(signupEmailLocator, signupEmail);
        framework.click(signupButtonLocator);
        return new SignupPage(this.driver);
    }

    @Step("sign up an Existing User")
    public LoginPage signupExistingUser(String signupName, String signupEmail) {
        framework.sendText(signupNameLocator, signupName);
        framework.sendText(signupEmailLocator, signupEmail);
        framework.click(signupButtonLocator);
        return this;
    }

    @Step("login Existing User")
    public HomePage positiveLoginExistingUser(String loginEmail, String loginPassword) {
        framework.sendText(loginEmailLocator, loginEmail);
        framework.sendText(loginPasswordLocator, loginPassword);
        framework.click(loginButtonLocator);
        return new HomePage(this.driver);
    }

    @Step("login Existing User with wrong data")
    public LoginPage negativeLoginExistingUser(String loginEmail, String loginPassword){
        framework.sendText(loginEmailLocator, loginEmail);
        framework.sendText(loginPasswordLocator, loginPassword);
        framework.click(loginButtonLocator);
        return this;
    }

    //Verification
    public LoginPage verifyIncorrectLoginCredential(String expectedText){
        verification.assertEquals(framework.getText(incorrectEmailOrPassText), expectedText,"Incorrect email or password text is not visible");
        return this;
    }

    // Method to Verify "Login to your account" is visible
    public LoginPage verifyLoginToYourAccountVisible() {
        verification.assertEquals(framework.getText(visibleLoginToYourAccountText), "Login to your account", "Login Page is not loaded successfully");
        return this;
    }

    // Method to Verify "New User Signup!" is visible
    public LoginPage verifyNewUserSignupVisible() {
        verification.assertEquals(framework.getText(visibleNewSignupText),"New User Signup!" , "Signup Page is not loaded successfully");
        return this;
    }

    // Method to Verify "Email Address already exist!" is visible
    public LoginPage verifyEmailExistText() {
        verification.assertEquals(framework.getText(emailExistTextLocator), "Email Address already exist!", "'Email Address already exist! ' is not visible");
        return this;
    }

}
