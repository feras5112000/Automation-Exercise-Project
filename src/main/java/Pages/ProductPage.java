package Pages;

import Header.LeftSideBarComponent;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage<ProductPage> {
    //Variables
    private LeftSideBarComponent leftSideBar;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.leftSideBar = new LeftSideBarComponent(driver);
    }

    //Locators
    private final By centerTextLocator = By.cssSelector("h2.title.text-center");
    private final By productNameLocator = By.cssSelector(".productinfo p");
    private final By productListLocator = By.cssSelector("div.features_items");
    private final By singleProductLocator = By.cssSelector("div.single-products");
    private final By continueShoppingButtonLocator = By.cssSelector("button[class='btn btn-success close-modal btn-block']");
    private final By searchProductInputLocator = By.cssSelector("input[id='search_product']");
    private final By searchButtonLocator = By.cssSelector("button[id='submit_search']");
    private final By viewCartButtonLocator = By.cssSelector("p.text-center > a[href='/view_cart']");
    private final By addToCartButtonLocator = By.cssSelector(".productinfo a.add-to-cart");

    //Dynamic Locators
    private By ViewProductIconLocator(String productID){
        return By.cssSelector("a[href='/product_details/"+ productID +"']");
    }

    private By productToHoverLocator(String productID){
        return By.cssSelector(".productinfo.text-center > img[src='/get_product_picture/"+ productID +"']");
    }

    private By productAddToCartButtonLocator(String productID){
        return By.cssSelector(".productinfo.text-center > a[data-product-id='"+ productID +"']");
    }

    //Actions
    @Step ("Click on View Product Icon of product")
    public ProductDetailsPage clickViewProductIcon(String productID){
        framework.click(ViewProductIconLocator(productID));
        return new ProductDetailsPage(this.driver);
    }

    @Step("Search By Product Name: {productName}")
    public ProductPage searchByProductName(String productName){
        framework.sendText(searchProductInputLocator, productName);
        framework.click(searchButtonLocator);
        return this;
    }

    @Step ("Verify that Brands are visible on left side bar ")
    public ProductPage verifyBrandGroupExistence(){
        framework.explicitWait(leftSideBar.getBrandPanelGroupLocator(), 3);
        return this;
    }

    @Step ("Verify that categories are visible on left side bar ")
    public ProductPage verifyCategoryGroupExistence(){
        framework.explicitWait(leftSideBar.getCategoryPanelGroupLocator(), 3);
        return this;
    }

    @Step("Choose Specific Category from left side bar")
    public ProductPage chooseSpecificCategory (String mainCategory, String subCategory){
        switch (mainCategory.toLowerCase()){
            case "women":
                leftSideBar.navigateToWomenSubCategory(subCategory);
                break;
            case "men":
                leftSideBar.navigateToMenSubCategory(subCategory);
                break;
            case "kids":
                leftSideBar.navigateToKidsSubCategory(subCategory);
                break;
            default:
                throw new IllegalArgumentException("Invalid category name: " + mainCategory);
        }

        return this;
    }

    @Step ("Choose Specific Brand from left side bar")
    public ProductPage chooseSpecificBrand(String brandName){
        leftSideBar.navigateToBrand(brandName);
        return this;
    }

    @Step ("Click 'Add to cart' button of product in the list")
    public ProductPage clickAddToCartOfProduct(String productID) {
        framework.hoverAndClick(productToHoverLocator(productID), productAddToCartButtonLocator(productID));
        return this;
    }

    @Step ("Click 'Continue Shopping' button on the modal")
    public ProductPage clickContinueShoppingButton() {
        framework.click(continueShoppingButtonLocator);
        return this;
    }

    @Step ("Click 'View Cart' button")
    public CartPage clickViewCartButton(){
     framework.click(viewCartButtonLocator);
     return new CartPage(this.driver);
    }

    //verification methods
    @Step ("Verify that product list is visible")
    public ProductPage verifyProductList()
    {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement section = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(productListLocator));
        verification.assertTrue(section.isDisplayed() && !section.findElements(singleProductLocator).isEmpty(), "Product list is not visible or empty");
        return this;
    }

    @Step(" Verify that searched products are visible")
    public ProductPage verifySearchedProductsAreVisible(String searchKey) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNameLocator));
        verification.assertFalse(products.isEmpty(), "No products found for search!");
        for (WebElement product : products) {
            String productName = product.getText().toLowerCase();
            verification.assertTrue(productName.contains(searchKey.toLowerCase()), "Product not related to search: " + productName);
        }
        return this;
    }

    @Step(" Verify that searched products are in the cart")
    public ProductPage addAllSearchResultsToCart(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        List<WebElement> products = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(singleProductLocator));

        verification.assertFalse(products.isEmpty(), "No products found to add to cart!");
        for (WebElement product : products) {

            product.findElement(addToCartButtonLocator).click();
            wait.until(
                    ExpectedConditions.elementToBeClickable(continueShoppingButtonLocator)
            ).click();
        }
        return this;
    }
    
    @Step ("Verify Centered Text on Product Page")
    public ProductPage verifyCenteredText(String expectedText , String errorMessage)
    {
        verification.assertTrue(framework.getText(centerTextLocator).contains(expectedText), errorMessage);
        return this;
    }

}
