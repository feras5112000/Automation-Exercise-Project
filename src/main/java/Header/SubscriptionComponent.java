package Header;

import Utils.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SubscriptionComponent {
    private Framework framework;
    private final By subscriptionInputLocator = By.cssSelector("input[id='susbscribe_email']");
    private final By subscriptionButtonLocator = By.cssSelector("button[id='subscribe']");
    private final By successfullySubscribeText = By.cssSelector("div#success-subscribe > div");
    private final By subscriptionText = By.cssSelector(".single-widget > h2");

    public SubscriptionComponent (WebDriver driver){
        this.framework = new Framework(driver);
    }

    public By getSubscriptionInputLocator() {
        return subscriptionInputLocator;
    }
    public SubscriptionComponent scrollToSubscriptionSection(){
        framework.scrollToElement(subscriptionInputLocator);
        return this;
    }

    public String getSubscriptionText(){
        framework.explicitWait(subscriptionText, 3);
        return framework.getText(subscriptionText);
    }

    public SubscriptionComponent enterSubscriptionEmail(String email){
        framework.sendText(subscriptionInputLocator, email);
        return this;
    }

    public SubscriptionComponent clickOnSubscriptionButton(){
        framework.click(subscriptionButtonLocator);
        return this;
    }

    public String getSuccessfulSubscriptionMessage(){
        framework.explicitWait(successfullySubscribeText, 3);
        return framework.getText(successfullySubscribeText);
    }
}
