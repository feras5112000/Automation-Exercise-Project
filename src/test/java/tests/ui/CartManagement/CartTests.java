package tests.ui.CartManagement;

import Models.CartData;
import Pages.CartPage;
import Pages.ProductDetailsPage;
import Utils.Helper;
import io.qameta.allure.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tests.BaseTests;
import java.io.FileNotFoundException;
import java.io.IOException;


@Epic("Automation Exercise")
@Feature("UI Cart Management")
@Owner("Feras Osama")

public class CartTests extends BaseTests{
    private ProductDetailsPage productDetailsPage;
    private CartPage cartPage;
    private CartData cartData1;
    private CartData cartData2;
    private CartData cartData3;
    private CartData cartData4;

    @BeforeClass(groups = {"cart", "regression"})
    public void classSetup() throws FileNotFoundException {
        cartData1 = Helper.ReadUser("cart.json", "product_1", CartData.class);
        cartData2 = Helper.ReadUser("cart.json", "product_2", CartData.class);
        cartData3 = Helper.ReadUser("cart.json", "verifyQuantity", CartData.class);
        cartData4 = Helper.ReadUser("cart.json", "recommendedItems", CartData.class);
    }


    @Test(groups = {"cart", "regression"})
    @Story("Shopping Cart Management")
    @Description("Verify users can add products to their shopping cart without login")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldAddProductsToCartWithoutLogin(){
        homePage
                .verifyHomePageVisible()
                .goToProductPage()
                .clickAddToCartOfProduct("1")
                .clickContinueShoppingButton()
                .clickAddToCartOfProduct("2")
                .clickViewCartButton()
                .verifyProductDetailsInCart(
                        "1",
                        cartData1)
                .verifyProductDetailsInCart(
                        "2",
                        cartData2);
    }

    //13
    @Test(groups = {"cart", "regression"})
    @Story("Shopping Cart Management")
    @Description("Verify that product quantities in the cart are displayed and calculated correctly")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyProductQuantityInCart(){
        homePage
                .verifyHomePageVisible()
                .clickViewProductIcon(cartData3.getProductID())
                .verifyProductDetailsPageVisible()
                .setProductQuantity(cartData3.getQuantity())
                .clickAddToCartButton()
                .clickViewCartButton()
                .verifyProductQuantityInCart(
                            cartData3.getProductID(),
                            cartData3.getQuantity()
                    );
    }

    //17
    @Test(groups = {"cart", "regression"})
    @Story("Shopping Cart Management")
    @Description("Verify users can remove items from their shopping cart")
    @Severity(SeverityLevel.CRITICAL)
    public void shouldRemoveProductFromCart(){
        homePage
                .verifyHomePageVisible()
                .clickAddToCartOfProduct(cartData3.getProductID())
                .clickContinueShoppingButton()
                .clickAddToCartOfProduct(cartData1.getProductID())
                .clickViewCartButton()
                .clickDeleteFromCart(cartData3.getProductID())
                .clickDeleteFromCart(cartData1.getProductID())
                .verifyProductIsRemoved(cartData3.getProductID())
                .verifyProductIsRemoved(cartData1.getProductID());
    }

    //22
    @Test(groups = {"cart", "regression"})
    @Story("Product Recommendations")
    @Description("Verify users can add recommended products directly to their cart")
    @Severity(SeverityLevel.NORMAL)
    public void shouldAddToCartFromRecommendedItems() throws IOException {
        homePage
                .verifyHomePageVisible()
                .scrollToSubscription()
                .verifyRecommendedItemsText()
                .clickAddToCartOfRecommendedItem(cartData4.getProductID())
                .clickViewCartButton()
                .verifyProductDetailsInCart(
                            cartData4.getProductID(),
                            cartData4
                );
        Helper.saveScreenshot("Verified Recommended Items Details", driver);
    }

}
