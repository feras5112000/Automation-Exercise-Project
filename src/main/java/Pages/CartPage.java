package Pages;

import Models.CartData;
import Utils.Helper;
import Verifications.Verification;
import io.qameta.allure.Step;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage<CartPage> {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By proceedToCheckoutLocator = By.cssSelector("a[class='btn btn-default check_out']");
    private final By continueOnCartLocator = By.cssSelector("button[class='btn btn-success close-checkout-modal btn-block']");
    private final By registerLoginButtonLocator = By.cssSelector("p.text-center > a[href='/login']");
    private final By cartProductsLocator = By.cssSelector(".cart_info tbody tr");

    //Dynamic Locators
    private By productNameLocator (String productID){
        return By.cssSelector("a[href='/product_details/" + productID + "']");
    }
    private By productPriceLocator (String productID) {
        return By.cssSelector("tr#product-" + productID + " > td[class='cart_price'] > p");
    }
    private By productQuantityLocator (String productID) {
        return By.cssSelector("tr#product-" + productID + " > td[class='cart_quantity'] > button");
    }
    private By productTotalLocator (String productID) {
        return By.cssSelector("tr#product-" + productID + " > td[class='cart_total'] > p");
    }

    private By deleteFromCartButton(String productID){
        return By.cssSelector("tr#product-" + productID + " > td > a[class='cart_quantity_delete']");
    }


    //getters
    public String getProductName(String productID) {
        return framework.getText(productNameLocator(productID));
    }

    public String getProductPrice(String productID) {
        return framework.getText(productPriceLocator(productID));
    }

    public String getProductQuantity(String productID) {
        return framework.getText(productQuantityLocator(productID));
    }

    public String getProductTotal(String productID) {
        return framework.getText(productTotalLocator(productID));
    }

    //Actions
    @Step ("Click on Delete from cart button")
    public CartPage clickDeleteFromCart(String productID) {
        framework.click(deleteFromCartButton(productID));
        return this;
    }

    public CartPage open() {
        framework.navigateTo(Helper.get("cartUrl"));
        return this;
    }

    public boolean cartIsEmptyFromProduct(String productID){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(productNameLocator(productID)));
    }

    @Step ("Click on Proceed to Checkout button 'before register/login'")
    public CartPage clickProceedToCheckoutBeforeLogging(){
        framework.click(proceedToCheckoutLocator);
        return this;
    }

    @Step ("Click on 'Register / Login' button")
    public LoginPage clickRegisterLoginButton(){
        framework.click(registerLoginButtonLocator);
        return new LoginPage(this.driver);
    }

    @Step("Click on Proceed to Checkout button 'after register/login'")
    public CheckoutPage clickProceedToCheckoutAfterLogging(){
        framework.click(proceedToCheckoutLocator);
        return new CheckoutPage(this.driver);
    }

    //Verifications
    @Step("Verify product details in cart")
    public CartPage verifyProductDetailsInCart(String productID, CartData expectedProduct) {
        verification.assertEquals(this.getProductName(productID), expectedProduct.getName(),"Product name mismatch for: " + expectedProduct.getName());
        verification.assertEquals(this.getProductPrice(productID), expectedProduct.getPrice(),"Product price mismatch for: " + expectedProduct.getName());
        verification.assertEquals(this.getProductQuantity(productID), expectedProduct.getQuantity(),"Product quantity mismatch for: " + expectedProduct.getName());
        verification.assertEquals(this.getProductTotal(productID), expectedProduct.getTotal(),"Product total mismatch for: " + expectedProduct.getName());
        return this;
    }

    @Step("Verify product quantity in cart")
    public CartPage verifyProductQuantityInCart(String productID, String quantity)
    {
        verification.assertEquals(this.getProductQuantity(productID), quantity ,"Product quantity mismatch");
        return this;
    }
    @Step ("Verify product is removed from cart")
    public CartPage verifyProductIsRemoved(String productID)
    {
        verification.assertTrue(this.cartIsEmptyFromProduct(productID),"Product is still in cart!");
        return this;
    }


    @Step("Verify that products are visible in cart ")
    public CartPage verifyProductsAreVisibleInCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        List<WebElement> cartProducts = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(cartProductsLocator)
        );
        verification.assertFalse(cartProducts.isEmpty(), "Cart is empty!");

        for (WebElement product : cartProducts) {
            verification.assertTrue(
                    product.isDisplayed(),
                    "One of the products is not visible in the cart!"
            );
        }
        return this;
    }
}

