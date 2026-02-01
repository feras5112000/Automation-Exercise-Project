package Pages;
import Header.LeftSideBarComponent;
import Utils.Helper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage<HomePage>{

    public HomePage(WebDriver driver) {
        super(driver);
        this.leftSideBar = new LeftSideBarComponent(driver);
    }

    //Locators
    private LeftSideBarComponent leftSideBar;
    private final By logoutLocator = By.cssSelector("a[href='/logout']");
    private final By fullFledgedTextLocator = By.cssSelector(".col-sm-6 > h2");
    private final By viewCartButtonLocator = By.cssSelector("p.text-center > a[href='/view_cart']");
    private final By continueShoppingButtonLocator = By.cssSelector("button[class='btn btn-success close-modal btn-block']");
    private final By recommendedItemsTextLocator = By.cssSelector(".recommended_items > h2 ");


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
    private By recommendedItemAddToCartButton(String productID){
        return By.cssSelector(".carousel.slide div.productinfo a[data-product-id='"+productID+"']");
    }

    //Actions
    @Step("Go to Home Page")
    public HomePage open() {
        framework.navigateTo(Helper.get("baseUrl"));
        return this;
    }

    @Step ("Logout Existing User")
    public LoginPage LogoutExistingUser (){
        framework.click(logoutLocator);
        return new LoginPage(this.driver);
    }

    @Step ("Verify that categories are visible on left side bar ")
    public HomePage verifyCategoryGroupExistence(){
        framework.explicitWait(leftSideBar.getCategoryPanelGroupLocator(), 3);
        return this;
    }

    @Step ("Choose Specific Category from left side bar")
    public ProductPage chooseSpecificCategory (String mainCategory ,String subCategory){
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

    @Step("Choose specific Brand")
    public ProductPage chooseSpecificBrand(String brandName){
        leftSideBar.navigateToBrand(brandName);
        return new ProductPage(this.driver);
    }

    @Step ("Verify that Brands are visible on left side bar ")
    public HomePage verifyBrandGroupExistence(){
        framework.explicitWait(leftSideBar.getBrandPanelGroupLocator(), 3);
        return this;
    }

    @Step ("Click on View Product Icon of product")
    public ProductDetailsPage clickViewProductIcon(String productID){
        framework.click(ViewProductIconLocator(productID));
        return new ProductDetailsPage(this.driver);
    }

    @Step ("Click 'Add to cart' button of product in the list")
    public HomePage clickAddToCartOfProduct(String productID) {
        framework.hoverAndClick(productToHoverLocator(productID), productAddToCartButtonLocator(productID));
        return this;
    }

    @Step ("Click 'View Cart' button")
    public CartPage clickViewCartButton(){
        framework.click(viewCartButtonLocator);
        return new CartPage(this.driver);
    }

    @Step ("Click 'Continue Shopping' button on the modal")
    public HomePage clickContinueShoppingButton() {
        framework.click(continueShoppingButtonLocator);
        return this;
    }


    @Step ("Click 'Add to cart' button of product in the Recommended Items")
    public HomePage clickAddToCartOfRecommendedItem(String productID) {
        framework.hoverAndClick(recommendedItemAddToCartButton(productID),recommendedItemAddToCartButton(productID));
        return this;
    }

    //verifications
    @Step ("Verify Home Page is Visible")
    public HomePage verifyHomePageVisible() {
        verification.assertPageTitle("Automation Exercise");
        return this;
    }

    @Step ("Verify Recommended Items text is visible")
    public HomePage verifyRecommendedItemsText() {
        verification.assertEquals(
                framework.getText(recommendedItemsTextLocator), "RECOMMENDED ITEMS",
                "'RECOMMENDED ITEMS' text is not visible"
        );
        return this;
    }

    @Step ("verify 'Full-Fledged practice website for Automation Engineers' text is visible on screen")
    public HomePage verifyFullFledgedText() {
        verification.assertEquals(framework.getText(fullFledgedTextLocator),"Full-Fledged practice website for Automation Engineers",
                "'Full-Fledged practice website for Automation Engineers' text is not visible");
        return this;
    }
}
