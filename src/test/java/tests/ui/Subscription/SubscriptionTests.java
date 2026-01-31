package tests.ui.Subscription;
import Models.LoginData;
import Pages.ProductPage;
import Utils.Helper;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTests;
import java.io.FileNotFoundException;

@Epic("Automation Exercise")
@Feature("Subscription")
@Owner("Feras Osama")
public class SubscriptionTests extends BaseTests {
    private ProductPage productPage;
    private LoginData existingUserData;

    @BeforeClass(groups = {"subscription", "regression"})
    public void setupClass() throws FileNotFoundException {
        System.out.println("Starting Subscription tests");
        existingUserData = Helper.ReadUser("users.json","validUser", LoginData.class );
    }


    //10
    @Test(groups = {"subscription", "regression"})
    @Story("Newsletter Subscription")
    @Description("Verify email subscription functionality works correctly on the homepage")
    @Severity(SeverityLevel.NORMAL)
    public void verifySubscriptionInHomePage(){
        homePage
                .verifyHomePageVisible()
                .scrollToSubscription()
                .verifySubscriptionSectionText()
                .subscriptionProcess(existingUserData.getEmail())
                .verifySubscriptionSuccessfulMessage();
    }

    //11
    @Test(groups = {"subscription", "regression"})
    @Story("Newsletter Subscription")
    @Description("Verify email subscription functionality works correctly on the cart page")
    @Severity(SeverityLevel.NORMAL)
    public void verifySubscriptionInCartPage(){
        homePage
                .verifyHomePageVisible()
                .goToCartPage()
                .scrollToSubscription()
                .verifySubscriptionSectionText()
                .subscriptionProcess(existingUserData.getEmail())
                .verifySubscriptionSuccessfulMessage();
       }

}
