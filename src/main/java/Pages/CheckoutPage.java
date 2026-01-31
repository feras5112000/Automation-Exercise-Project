package Pages;

import Models.RegisterData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class CheckoutPage extends BasePage<CheckoutPage> {

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By placeOrderButtonLocator = By.cssSelector("a[href='/payment']");
    private final By orderCommentTextAreaLocator = By.cssSelector("textarea[name='message']");

    //Delivery Address Locators
    private final By deliveryNameLocator = By.cssSelector("#address_delivery li.address_firstname.address_lastname");
    private final By deliveryCompanyLocator = By.cssSelector("#address_delivery li.address_address1.address_address2:nth-of-type(3)");
    private final By deliveryAddress1Locator = By.cssSelector("#address_delivery li.address_address1.address_address2:nth-of-type(4)");
    private final By deliveryAddress2Locator = By.cssSelector("#address_delivery li.address_address1.address_address2:nth-of-type(5)");
    private final By deliveryCityStateZipLocator = By.cssSelector("#address_delivery li.address_city.address_state_name.address_postcode");
    private final By deliveryCountryLocator = By.cssSelector("#address_delivery li.address_country_name");
    private final By deliveryPhoneLocator = By.cssSelector("#address_delivery li.address_phone");

    //Billing Address Locators
    private final By billingNameLocator = By.cssSelector("#address_invoice li.address_firstname.address_lastname");
    private final By billingCompanyLocator = By.cssSelector("#address_invoice li.address_address1.address_address2:nth-of-type(3)");
    private final By billingAddress1Locator = By.cssSelector("#address_invoice li.address_address1.address_address2:nth-of-type(4)");
    private final By billingAddress2Locator = By.cssSelector("#address_invoice li.address_address1.address_address2:nth-of-type(5)");
    private final By billingCityStateZipLocator = By.cssSelector("#address_invoice li.address_city.address_state_name.address_postcode");
    private final By billingCountryLocator = By.cssSelector("#address_invoice li.address_country_name");
    private final By billingPhoneLocator = By.cssSelector("#address_invoice li.address_phone");


    //Delivery Address Getters
    public String getDeliveryName() {
        return framework.getText(deliveryNameLocator);
    }
    public String getDeliveryCompany() {
        return framework.getText(deliveryCompanyLocator);
    }
    public String getDeliveryAddress1() {
        return framework.getText(deliveryAddress1Locator);
    }
    public String getDeliveryAddress2() {
        return framework.getText(deliveryAddress2Locator);
    }
    public String getDeliveryCityStateZip() {
        return framework.getText(deliveryCityStateZipLocator);
    }
    public String getDeliveryCountry() {
        return framework.getText(deliveryCountryLocator);
    }
    public String getDeliveryPhone() {
        return framework.getText(deliveryPhoneLocator);
    }

    // Billing Address Getters
    public String getBillingName() {
        return framework.getText(billingNameLocator);
    }
    public String getBillingCompany() {
        return framework.getText(billingCompanyLocator);
    }
    public String getBillingAddress1() {
        return framework.getText(billingAddress1Locator);
    }
    public String getBillingAddress2() {
        return framework.getText(billingAddress2Locator);
    }
    public String getBillingCityStateZip() {
        return framework.getText(billingCityStateZipLocator);
    }
    public String getBillingCountry() {
        return framework.getText(billingCountryLocator);
    }
    public String getBillingPhone() {
        return framework.getText(billingPhoneLocator);
    }

    @Step ("Add Order Comment and Place Order")
    public PaymentPage addOrderCommentAndPlaceOrder(String comment) {
        framework.sendText(orderCommentTextAreaLocator, comment);
        framework.click(placeOrderButtonLocator);
        return new PaymentPage(this.driver);
    }

    //Verifications
    public CheckoutPage verifyDeliveryAddress(RegisterData expectedProduct){
        verification.assertEquals(
                this.getDeliveryName(),expectedProduct.getTitle()+". "+expectedProduct.getFirstName()+" "+expectedProduct.getLastName(),
                "Delivery Name is not matched");
        verification.assertEquals(
                this.getDeliveryCompany(), expectedProduct.getCompany(),"Company is not matched");
        verification.assertEquals(
                this.getDeliveryAddress1(), expectedProduct.getAddress1(),"Address1 is not matched");
        verification.assertEquals(
                this.getDeliveryAddress2(), expectedProduct.getAddress2(),"Address2 is not matched");
        verification.assertEquals(
                this.getDeliveryCityStateZip(),expectedProduct.getCity()+" "+expectedProduct.getState()+" "+expectedProduct.getZipcode(),
                "City, State and Zipcode is not matched");
        verification.assertEquals(
                this.getDeliveryCountry(), expectedProduct.getCountry(),"Country is not matched");
        verification.assertEquals(
                this.getDeliveryPhone(), expectedProduct.getMobileNumber(),"Mobile Number is not matched");
        return this;
    }

    public CheckoutPage verifyBillingAddress(RegisterData expectedProduct){
        verification.assertEquals(
                this.getBillingName(),expectedProduct.getTitle()+". "+expectedProduct.getFirstName()+" "+expectedProduct.getLastName(),
                "Billing Name is not matched");
        verification.assertEquals(
                this.getBillingCompany(), expectedProduct.getCompany(),"Company is not matched");
        verification.assertEquals(
                this.getBillingAddress1(), expectedProduct.getAddress1(),"Address1 is not matched");
        verification.assertEquals(
                this.getBillingAddress2(), expectedProduct.getAddress2(),"Address2 is not matched");
        verification.assertEquals(
                this.getBillingCityStateZip(), expectedProduct.getCity()+" "+expectedProduct.getState()+" "+expectedProduct.getZipcode(),
                "City, State and Zipcode is not matched");
        verification.assertEquals(
                this.getBillingCountry(), expectedProduct.getCountry(),"Country is not matched");
        verification.assertEquals(
                this.getBillingPhone(), expectedProduct.getMobileNumber(),"Mobile Number is not matched");
        return this;
    }


}
