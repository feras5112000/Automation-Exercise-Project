package Pages;

import Utils.Helper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage<SignupPage> {

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By validateEnterAccountInformatiomText = By.cssSelector(".login-form h2.text-center:first-child b");
    private final By MrRadioButtonLocator = By.id("id_gender1");
    private final By MrsRadioButtonLocator = By.id("id_gender2");
    private final By nameLocator = By.id("name");
    private final By passwordLocator = By.id("password");
    private final By dayDropdownLocator = By.id("days");
    private final By monthDropdownLocator = By.id("months");
    private final By yearDropdownLocator = By.id("years");
    private final By newsletterCheckboxLocator = By.id("newsletter");
    private final By offersCheckboxLocator = By.id("optin");
    private final By firstNameLocator = By.id("first_name");
    private final By lastNameLocator = By.id("last_name");
    private final By companyLocator = By.id("company");
    private final By address1Locator = By.id("address1");
    private final By address2Locator = By.id("address2");
    private final By countryDropdownLocator = By.id("country");
    private final By stateLocator = By.id("state");
    private final By cityLocator = By.id("city");
    private final By zipcodeLocator = By.id("zipcode");
    private final By mobileNumberLocator = By.id("mobile_number");
    private final By createAccountButtonLocator = By.cssSelector("button[data-qa='create-account']");

    //Actions
    public SignupPage open() {
        framework.navigateTo(Helper.get("signupUrl"));
        return this;
    }

    @Step("Fill Details: Title, Name, Email, Password, Date of birth ")
    public SignupPage fillDetails(String title, String Name, String Password, String Day, String Month, String Year) {
        /* Select Title */
        if(title.equalsIgnoreCase("Mrs")) {
            framework.click(MrsRadioButtonLocator);
        } else if (title.equalsIgnoreCase("Mr")) {
            framework.click(MrRadioButtonLocator);
        }
        else {
            throw new IllegalArgumentException("Invalid title: " + title);
        }

        /* Fill Name */
        framework.clear(nameLocator);
        framework.sendText(nameLocator, Name);

        /* Fill Password */
        framework.sendText(passwordLocator, Password);

        /* Fill Date of Birth */
        framework.dropdownByValue(dayDropdownLocator, Day);
        framework.dropdownByValue(monthDropdownLocator, Month);
        framework.dropdownByValue(yearDropdownLocator, Year);

        return this;
    }

    @Step ("Select Newsletter Checkbox")
    public SignupPage selectNewsletterCheckbox() {
        framework.click(newsletterCheckboxLocator);
        return this;
    }

    @Step ("Select Special Offers Checkbox")
    public SignupPage selectSpecialOffersCheckbox() {
        framework.click(offersCheckboxLocator);
        return this;
    }

    @Step ("Fill Address Details: First Name, Last Name, Company, Address1, Address2, Country, State, City, Zipcode, Mobile Number")
    public SignupPage fillAddressDetails(String FirstName, String LastName, String Company, String Address1, String Address2, String Country, String State, String City, String Zipcode, String MobileNumber) {
        /* Fill First Name */
        framework.sendText(firstNameLocator, FirstName);
        /* Fill Last Name */
        framework.sendText(lastNameLocator, LastName);
        /* Fill Company */
        framework.sendText(companyLocator, Company);
        /* Fill Address1 */
        framework.sendText(address1Locator, Address1);
        /* Fill Address2 */
        framework.sendText(address2Locator, Address2);
        /* Select Country */
        framework.dropdownByValue(countryDropdownLocator, Country);
        /* Fill State */
        framework.sendText(stateLocator, State);
        /* Fill City */
        framework.sendText(cityLocator, City);
        /* Fill Zipcode */
        framework.sendText(zipcodeLocator, Zipcode);
        /* Fill Mobile Number */
        framework.sendText(mobileNumberLocator, MobileNumber);
        return this;
    }

    @Step ("Click on Create Account Button")
    public AccountCreatedPage clickOnCreateAccountButton() {
        framework.click(createAccountButtonLocator);
        return new AccountCreatedPage(this.driver);
    }

    //verifications
    @Step ("Verify 'ENTER ACCOUNT INFORMATION' is visible")
    public SignupPage verifyEnterAccountInformationVisible() {
        verification.assertTrue(
                framework.getText(validateEnterAccountInformatiomText).contains("ENTER ACCOUNT INFORMATION"),
                "'ENTER ACCOUNT INFORMATION' is not visible"
        );
        return this;

    }

}
