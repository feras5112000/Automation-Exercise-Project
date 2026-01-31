package tests.ui.Authentication;

import Models.RegisterData;
import Models.LoginData;
import Pages.*;
import Utils.Helper;
import io.qameta.allure.*;
import org.testng.annotations.*;
import tests.BaseTests;
import java.io.FileNotFoundException;
import java.io.IOException;
import static Utils.Helper.generateUniqueValue;


@Epic("Automation Exercise")
@Feature("Authentication")
@Owner("Feras Osama")
public class RegisterTests extends BaseTests {
    private RegisterData registerDataTC1;
    private LoginData registerDataTC2;

    @BeforeClass(groups = {"authentication" ,"regression"})
    public void setupClass() throws FileNotFoundException {
        System.out.println("Starting RegisterUserTest tests");
        registerDataTC1 = Helper.ReadUser("register.json" ,"validRegister", RegisterData.class);
        registerDataTC2 = Helper.ReadUser("users.json","validUser", LoginData.class);
    }

    //1
    @Test(groups = {"authentication", "regression"})
    @Story("User Registration")
    @Description("Verify that a new user can successfully register in the system with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    public void newUserRegistrationTillDeleting() throws IOException {
        homePage
                .verifyHomePageVisible()
                .goToLoginPage()
                .verifyNewUserSignupVisible()
                .signupNewUser(
                    registerDataTC1.getSignupName(),
                    registerDataTC1.getSignupEmail().replace("@",generateUniqueValue()+"@")
                )
                .verifyEnterAccountInformationVisible()
                .fillDetails(
                        registerDataTC1.getTitle(),
                        registerDataTC1.getName(),
                        registerDataTC1.getPassword(),
                        registerDataTC1.getDay(),
                        registerDataTC1.getMonth(),
                        registerDataTC1.getYear())
                .selectNewsletterCheckbox()
                .selectSpecialOffersCheckbox()
                .fillAddressDetails(
                        registerDataTC1.getFirstName(),
                        registerDataTC1.getLastName(),
                        registerDataTC1.getCompany(),
                        registerDataTC1.getAddress1(),
                        registerDataTC1.getAddress2(),
                        registerDataTC1.getCountry(),
                        registerDataTC1.getState(),
                        registerDataTC1.getCity(),
                        registerDataTC1.getZipcode(),
                        registerDataTC1.getMobileNumber())
                .clickOnCreateAccountButton()
                .verifyAccountCreatedMessage("ACCOUNT CREATED!")
                .clickContinueButton()
                .verifyLoggedInUser(registerDataTC1.getName())
                .deleteAccount()
                .verifyAccountDeletedMessage()
                .clickContinueButton();
    }

    //5
    @Story("User Registration - Negative Testing")
    @Description("Verify system prevents duplicate registration with existing email addresses")
    @Severity(SeverityLevel.CRITICAL)
    @Test(groups = {"authentication", "regression"})
    public void existingUserRegistration() {
     homePage
             .verifyHomePageVisible()
             .goToLoginPage()
             .verifyNewUserSignupVisible()
             .signupExistingUser(
                        registerDataTC2.getName(),
                        registerDataTC2.getEmail())
             .verifyEmailExistText();
     }

}