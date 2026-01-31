package tests.ui.CheckoutAndOrders;

import Models.ProductData;
import Pages.HomePage;
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

@Epic("Automation Exercise")
@Feature("Checkout and Orders")
@Owner("Feras Osama")

public class OrdersTests extends BaseTests {
    ProductPage productpage;
    ProductData productData;


    @Test(groups = {"orders", "regression"})
    @Story("User Experience - Session Persistence")
    @Description("Verify cart contents persist after user login")
    @Severity(SeverityLevel.CRITICAL)
    public void searchProductsAndVerifyCartAfterLogin() throws IOException {
        homePage
                .verifyHomePageVisible()
                .goToProductPage()
                .verifyProductList()
                .searchByProductName(productData.getProductNameForSearch())
                .verifyCenteredText("SEARCHED PRODUCTS" , "'SEARCHED PRODUCTS' is not visible")
                .verifySearchedProductsAreVisible(productData.getProductNameForSearch())
                .addAllSearchResultsToCart()
                .goToCartPage()
                .verifyProductsAreVisibleInCart()
                .clickProceedToCheckoutBeforeLogging()
                .clickRegisterLoginButton()
                .goToCartPage()
                .verifyProductsAreVisibleInCart();
        Helper.saveScreenshot("Verified visibility of Product in Cart" ,driver);
    }

    @BeforeClass(groups = {"orders", "regression"})
    public void classSetup() throws FileNotFoundException {
        productData = Helper.ReadUser("product.json","validProductSearch",  ProductData.class);
    }

}
