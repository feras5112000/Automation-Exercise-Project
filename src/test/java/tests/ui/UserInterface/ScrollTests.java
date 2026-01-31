package tests.ui.UserInterface;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import tests.BaseTests;

@Epic("Automation Exercise")
@Feature("User Interface")
@Owner("Feras Osama")
public class ScrollTests extends BaseTests{

    @Test(groups = {"user-interface", "regression"})
    @Story("User Interface - Navigation")
    @Description("Verify scroll functionality works correctly with the arrow button")
    @Severity(SeverityLevel.MINOR)
    public void scrollUpWithArrowButtonAndDown(){
        homePage
                .verifyHomePageVisible()
                .scrollToSubscription()
                .verifySubscriptionSectionText()
                .clickOnScrollUpButton()
                .verifyFullFledgedText();
    }

    @Test(groups = {"user-interface", "regression"})
    @Story("User Interface - Navigation")
    @Description("Verify natural scroll functionality works correctly throughout the application")
    @Severity(SeverityLevel.MINOR)
    public void scrollUpWithoutArrowButtonAndDown(){
        homePage
                .verifyHomePageVisible()
                .scrollToSubscription()
                .verifySubscriptionSectionText()
                .ScrollToTop()
                .verifyFullFledgedText();
    }

}
