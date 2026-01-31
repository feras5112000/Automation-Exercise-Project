package tests.ui.CheckoutAndOrders;

import Models.CheckoutData;
import Models.LoginData;
import Models.RegisterData;
import Pages.*;
import Utils.Helper;
import io.qameta.allure.*;
import org.openqa.selenium.chrome.ChromeDriver;
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
public class CheckoutTests extends BaseTests {
    CheckoutData checkoutData;
    RegisterData registerData;
    LoginData loginData;


    @Test(groups = {"checkout", "regression"})
    @Story("Checkout Process - Guest to Registered User")
    @Description("Verify users can register during checkout and complete their purchase")
    @Severity(SeverityLevel.CRITICAL)
    public void placeOrderWhileRegisteringAtCheckout() throws IOException {
        homePage
                .verifyHomePageVisible()
                .clickAddToCartOfProduct(checkoutData.getProductID_1())
                .clickContinueShoppingButton()
                .clickAddToCartOfProduct(checkoutData.getProductID_2())
                .clickViewCartButton()
                .clickProceedToCheckoutBeforeLogging()
                .clickRegisterLoginButton()
                .signupNewUser(
                        registerData.getSignupName(),
                        registerData.getSignupEmail().replace("@",generateUniqueValue()+"@"))
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
                .clickContinueButton()
                .verifyHomePageVisible()
                .deleteAccount()
                .clickContinueButton();

    }
    //15
    @Test(groups = {"checkout", "regression"})
    @Story("Checkout Process - Registered User")
    @Description("Verify registered users can successfully place orders")
    @Severity(SeverityLevel.CRITICAL)
    public void placeOrderAfterRegisterBeforeCheckout() throws IOException {
        homePage
                .verifyHomePageVisible()
                .goToLoginPage()
                .signupNewUser(
                        registerData.getSignupName(),
                        registerData.getSignupEmail().replace("@",generateUniqueValue()+"@"))
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
                .clickAddToCartOfProduct(checkoutData.getProductID_1())
                .clickContinueShoppingButton()
                .clickAddToCartOfProduct(checkoutData.getProductID_2())
                .clickViewCartButton()
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
                .clickContinueButton()
                .verifyHomePageVisible()
                .deleteAccount()
                .clickContinueButton();
    }
    //16
    @Test(groups = {"checkout", "regression"})
    @Story("Checkout Process - Returning User")
    @Description("Verify returning users can log in and complete their purchase")
    @Severity(SeverityLevel.CRITICAL)
    public void placeOrderAfterLoginBeforeCheckout() throws IOException {
        homePage
                .verifyHomePageVisible()
                .goToLoginPage()
                .positiveLoginExistingUser(
                        loginData.getEmail(),
                        loginData.getPassword())
                .verifyLoggedInUser(loginData.getName())
                .clickAddToCartOfProduct(checkoutData.getProductID_1())
                .clickContinueShoppingButton()
                .clickAddToCartOfProduct(checkoutData.getProductID_2())
                .clickViewCartButton()
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
                .clickContinueButton()
                .verifyHomePageVisible()
                .LogoutExistingUser();
    }
    //23
    @Test(groups = {"checkout", "regression"})
    @Story("Checkout Process - Address Management")
    @Description("Verify shipping/billing address details are correctly displayed during checkout")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyAddressDetailsOnCheckoutPage() throws IOException {
        homePage
                .verifyHomePageVisible()
                .goToLoginPage()
                .signupNewUser(
                        registerData.getSignupName(),
                        registerData.getSignupEmail().replace("@",generateUniqueValue()+"@"))
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
                .clickAddToCartOfProduct(checkoutData.getProductID_1())
                .clickContinueShoppingButton()
                .clickAddToCartOfProduct(checkoutData.getProductID_2())
                .clickViewCartButton()
                .clickProceedToCheckoutAfterLogging()
                .verifyDeliveryAddress(registerData)
                .verifyBillingAddress(registerData)
                .deleteAccount();
    }

    @BeforeClass(groups = {"checkout", "regression"})
    public void classSetup() throws FileNotFoundException {
        checkoutData = Helper.ReadUser("checkout.json", "registerWhileCheckout", CheckoutData.class);
        registerData = Helper.ReadUser("register.json", "validRegister", RegisterData.class);
        loginData = Helper.ReadUser("users.json","validUser", LoginData.class);
    }

}
