package tests.ui.CheckoutAndOrders;

import Models.CheckoutData;
import Models.ProductData;
import Models.RegisterData;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductPage;
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
import java.io.IOException;

import static Utils.Helper.generateUniqueValue;

@Epic("Automation Exercise")
@Feature("Checkout and Orders")
@Owner("Feras Osama")
public class PostOrderTests extends BaseTests {
    CheckoutPage checkoutPage;
    CheckoutData checkoutData;
    RegisterData registerData;

    @BeforeClass(groups = {"invoice", "regression"})
    public void classSetup() throws FileNotFoundException {
        checkoutData = Helper.ReadUser("checkout.json", "registerWhileCheckout", CheckoutData.class);
        registerData = Helper.ReadUser("register.json", "validRegister", RegisterData.class);
    }

    //24
    @Test(groups = {"invoice", "regression"})
    @Story("Order Management")
    @Description("Verify users can download invoices after completing purchases")
    @Severity(SeverityLevel.NORMAL)
    public void shouldDownloadInvoiceAfterPurchaseOrder() throws IOException {
        homePage
                .verifyHomePageVisible()
                .clickAddToCartOfProduct(checkoutData.getProductID_1())
                .clickContinueShoppingButton()
                .clickAddToCartOfProduct(checkoutData.getProductID_2())
                .clickViewCartButton()
                .clickProceedToCheckoutBeforeLogging()
                .clickRegisterLoginButton()
                .signupNewUser(registerData.getSignupName(), registerData.getSignupEmail().replace("@",generateUniqueValue()+"@"))
                .fillDetails(
                        registerData.getTitle(),
                        registerData.getName(),
                        registerData.getPassword(),
                        registerData.getDay(),
                        registerData.getMonth(),
                        registerData.getYear())
                .selectNewsletterCheckbox()
                .selectSpecialOffersCheckbox()
                .fillAddressDetails(
                        registerData.getFirstName(),
                        registerData.getLastName(),
                        registerData.getCompany(),
                        registerData.getAddress1(),
                        registerData.getAddress2(),
                        registerData.getCountry(),
                        registerData.getState(),
                        registerData.getCity(),
                        registerData.getZipcode(),
                        registerData.getMobileNumber())
                .clickOnCreateAccountButton()
                .verifyAccountCreatedMessage("ACCOUNT CREATED!")
                .clickContinueButton()
                .verifyLoggedInUser(registerData.getName())
                .goToCartPage()
                .clickProceedToCheckoutAfterLogging()
                .verifyDeliveryAddress(registerData)
                .verifyBillingAddress(registerData)
                .addOrderCommentAndPlaceOrder(checkoutData.getPlaceOrderComment())
                .fillPaymentDetails(
                        checkoutData.getCardName(),
                        checkoutData.getCardNumber(),
                        checkoutData.getCvc(),
                        checkoutData.getExMonth(),
                        checkoutData.getExYear())
                .clickPayAndConfirmOrderButton()
                .clickDownloadInvoiceButton()
                .clickContinueButton()
                .verifyHomePageVisible()
                .deleteAccount()
                .clickContinueButton();
    }

}
