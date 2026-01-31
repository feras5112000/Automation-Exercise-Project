package Pages;

import Header.LeftSideBarComponent;
import Models.ProductData;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage extends BasePage<ProductPage>{
    //variables
    private LeftSideBarComponent leftSideBar;

    public ProductDetailsPage (WebDriver driver){
        super(driver);
        this.leftSideBar = new LeftSideBarComponent(driver);
    }

    //Locators
    private final By productNameLocator = By.cssSelector(".product-information h2");
    private final By productQuantityLocator = By.cssSelector("input[name='quantity']");
    private final By productCategoryLocator = By.cssSelector(".product-information p");
    private final By productAvailabilityLocator = By.cssSelector("p:nth-of-type(2) b");
    private final By productConditionLocator = By.cssSelector("p:nth-of-type(3) b");
    private final By productBrandLocator = By.cssSelector("p:nth-of-type(4) b");
    private final By addToCartButtonLocator = By.cssSelector("button[type='button']");
    private final By viewCartButtonLocator = By.cssSelector("p.text-center > a[href='/view_cart']");
    private final By writeYourReviewLocator = By.cssSelector("li > a[href='#reviews']");
    private final By reviewNameInputLocator = By.id("name");
    private final By reviewEmailInputLocator = By.id("email");
    private final By reviewTextAreaLocator = By.id("review");
    private final By submitReviewButtonLocator = By.id("button-review");
    private final By reviewSubmissionMessageLocator = By.cssSelector(".alert-success.alert > span");

    //Actions
    @Step ("Verify that Brands are visible on left side bar ")
    public ProductDetailsPage verifyBrandGroupExistence(){
        framework.explicitWait(leftSideBar.getBrandPanelGroupLocator(), 3);
        return this;
    }

    @Step ("Verify that categories are visible on left side bar ")
    public ProductDetailsPage verifyCategoryGroupExistence(){
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

        return new ProductPage(this.driver);
    }

    @Step ("Choose Specific Brand from left side bar")
    public ProductPage chooseSpecificBrand(String brandName){
        leftSideBar.navigateToBrand(brandName);
        return new ProductPage(this.driver);
    }

    @Step ("Fill Product Review Form")
    public ProductDetailsPage fillProductReviewForm(String name, String email, String review){
        framework.sendText(reviewNameInputLocator, name);
        framework.sendText(reviewEmailInputLocator, email);
        framework.sendText(reviewTextAreaLocator, review);
        return this;
    }

    @Step ("Submit Product Review")
    public ProductDetailsPage submitProductReview() {
        framework.click(submitReviewButtonLocator);
        return this;
    }

    @Step ("Set 'Quantity' of the product")
    public ProductDetailsPage setProductQuantity(String quantity){
        framework.clear(productQuantityLocator);
        framework.sendText(productQuantityLocator, quantity);
        return this;
    }

    @Step ("Click 'Add to Cart' button")
    public ProductDetailsPage clickAddToCartButton (){
        framework.click(addToCartButtonLocator);
        return this;
    }

    @Step ("Click 'View Cart' button")
    public CartPage clickViewCartButton(){
        framework.click(viewCartButtonLocator);
        return new CartPage(this.driver);
    }

    //verifications
    @Step ("Verify Home Page is Visible")
    public ProductDetailsPage verifyProductDetailsPageVisible() {
        verification.assertPageTitle("Automation Exercise - Product Details");
        return this;
    }

    @Step ("Verify 'Write Your Review' Text")
    public ProductDetailsPage verifyWriteYourReviewText(){
        verification.assertEquals(framework.getText(writeYourReviewLocator), "WRITE YOUR REVIEW", "'WRITE YOUR REVIEW' is not visible");
        return this;
    }

    @Step ("Verify Thanks message for review submission")
    public ProductDetailsPage verifyReviewSubmissionMessage() {
        verification.assertEquals(framework.getText(reviewSubmissionMessageLocator), "Thank you for your review.", "Review submission failed");
        return this;
    }

    @Step("Verify all product details are visible")
    public ProductDetailsPage verifyProductDetailsAreVisible() {
        By[] locators = {
                productNameLocator,
                productCategoryLocator,
                productAvailabilityLocator,
                productConditionLocator,
                productBrandLocator
        };

        for (By locator : locators) {
            try {
                WebElement element = framework.getElement(locator);

                verification.assertTrue(
                        element.isDisplayed(),
                        "Element not visible: " + locator.toString()
                );

            } catch (NoSuchElementException e) {

                verification.assertTrue(
                        false,
                        "Element not found: " + locator.toString()
                );
            }
        }
        return this;
    }


}
