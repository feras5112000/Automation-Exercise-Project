package Pages;

import Utils.Helper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class PaymentPage extends BasePage<PaymentPage>{

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    //locators
    private final By nameOnCardLocator = By.name("name_on_card");
    private final By cardNumberLocator = By.name("card_number");
    private final By cardCvcLocator = By.name("cvc");
    private final By cardMonthExpirationLocator = By.name("expiry_month");
    private final By cardYearExpirationLocator = By.name("expiry_year");
    private final By payButtonLocator = By.id("submit");
    private final By paymentSuccessMessageLocator = By.cssSelector("h2 > b");
    private final By downloadInvoiceButtonLocator = By.cssSelector("a[href='/download_invoice/900']");
    private final By continueButtonLocator = By.cssSelector(".pull-right > a[href='/']");

    //Actions
    @Step ("Fill Payment Details")
    public PaymentPage fillPaymentDetails(String nameOnCard, String cardNumber, String cvc, String month, String year) {
        framework.sendText(nameOnCardLocator, nameOnCard);
        framework.sendText(cardNumberLocator, cardNumber);
        framework.sendText(cardCvcLocator, cvc);
        framework.sendText(cardMonthExpirationLocator, month);
        framework.sendText(cardYearExpirationLocator, year);
        return this;
    }

    @Step ("Click on Pay and Confirm Order Button")
    public PaymentPage clickPayAndConfirmOrderButton() throws IOException {
        framework.click(payButtonLocator);
        Helper.saveScreenshot("Successful Payment" ,driver);
        return this;
    }

    @Step ("Get Payment Success Message")
    public String getPaymentSuccessMessage() {
        return framework.getText(paymentSuccessMessageLocator);
    }

    @Step ("Click on Continue Button after payment")
    public HomePage clickContinueButton(){
        framework.click(continueButtonLocator);
        return new HomePage(this.driver);
    }

    @Step ("Click on Download Invoice Button")
    public PaymentPage clickDownloadInvoiceButton(){
        framework.click(downloadInvoiceButtonLocator);
        return this;
    }
}
