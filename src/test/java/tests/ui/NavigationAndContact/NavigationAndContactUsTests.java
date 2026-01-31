package tests.ui.NavigationAndContact;

import Models.ContactData;
import Pages.ContactUsPage;
import Pages.HomePage;
import Pages.TestCasesPage;
import Utils.Helper;
import io.qameta.allure.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTests;

import java.io.FileNotFoundException;

@Epic("Automation Exercise")
@Feature("Navigation and Contact")
@Owner("Feras Osama")
public class NavigationAndContactUsTests extends BaseTests {
    private ContactData contactUsData;


    //6
    @Test(groups = {"contact", "regression"})
    @Story("Customer Support")
    @Description("Verify that users can successfully submit inquiries through the contact form")
    @Severity(SeverityLevel.NORMAL)
    public void SubmitContactUsFormSuccessfully (){
        homePage
                .verifyHomePageVisible()
                .goToContactUsPage()
                .verifyGetInTouchText()
                .fillContactForm(
                        contactUsData.getContactName(),
                        contactUsData.getContactEmail(),
                        contactUsData.getContactSubject(),
                        contactUsData.getMessage()
                )
                .uploadContactFormFile(contactUsData.getFilePath())
                .clickSubmitButton()
                .acceptAlert()
                .verifySuccessMessage()
                .goToHomePage()
                .verifyHomePageVisible();
    }

    //7
    @Test(groups = {"navigation", "regression"})
    @Story("Content Validation")
    @Description("Verify the test cases page loads correctly with all intended content")
    @Severity(SeverityLevel.MINOR)
    public void verifyTestCasesPageTest(){
        homePage
                .verifyHomePageVisible()
                .goToTestCasesPage()
                .verifyTestCasesText();
        }

    @BeforeClass(groups = {"navigation", "regression"})
    public void setupClass() throws FileNotFoundException {
        contactUsData = Helper.ReadUser("contact.json","validContactData", ContactData.class);
    }

}
