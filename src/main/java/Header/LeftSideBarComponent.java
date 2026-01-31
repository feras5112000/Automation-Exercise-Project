package Header;

import Utils.Framework;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class LeftSideBarComponent {
    private final Framework framework;

    //Category Locators
    private final By categoryPanelGroupLocator = By.cssSelector(".left-sidebar > div#accordian");
    private final By womenCategoryLocator = By.cssSelector("a[href='#Women']");
    private final By womenDressSubCategoryLocator = By.cssSelector("a[href='/category_products/1']");
    private final By womenTopsSubCategoryLocator = By.cssSelector("a[href='/category_products/2']");
    private final By womenSareeSubCategoryLocator = By.cssSelector("a[href='/category_products/7']");
    private final By menCategoryLocator = By.cssSelector("a[href='#Men']");
    private final By menTshirtsSubCategoryLocator = By.cssSelector("a[href='/category_products/3']");
    private final By menJeansSubCategoryLocator = By.cssSelector("a[href='/category_products/6']");
    private final By kidsCategoryLocator = By.cssSelector("a[href='#Kids']");
    private final By kidsDressSubCategoryLocator = By.cssSelector("a[href='/category_products/4']");
    private final By kidsTopsAndTshirtsSubCategoryLocator = By.cssSelector("a[href='/category_products/5']");

    //Brand Locators
    private final By brandPanelGroupLocator = By.cssSelector(".left-sidebar > .brands_products");
    private final By poloBrandLocator = By.cssSelector("a[href='/brand_products/Polo']");
    private final By hmBrandLocator = By.cssSelector("a[href='/brand_products/H&M']");
    private final By madameBrandLocator = By.cssSelector("a[href='/brand_products/Madame']");
    private final By mastBrandLocator = By.cssSelector("a[href='/brand_products/Mast & Harbour']");
    private final By babyhugBrandLocator = By.cssSelector("a[href='/brand_products/Babyhug']");
    private final By allenSollyBrandLocator = By.cssSelector("a[href='/brand_products/Allen Solly Junior']");
    private final By kookieBrandLocator = By.cssSelector("a[href='/brand_products/Kookie Kids']");
    private final By bibaBrandLocator = By.cssSelector("a[href='/brand_products/Biba']");


    public LeftSideBarComponent(WebDriver driver) {
        this.framework = new Framework(driver);
    }

    public By getCategoryPanelGroupLocator() {
        return categoryPanelGroupLocator;
    }

    public By getBrandPanelGroupLocator() {
        return brandPanelGroupLocator;
    }

    private  final Map<String, By> womenLocator = Map.of(
            "dress", womenDressSubCategoryLocator,
            "saree", womenSareeSubCategoryLocator,
            "tops", womenTopsSubCategoryLocator
    );

    private  final Map<String, By> menLocator = Map.of(
            "tshirt", menTshirtsSubCategoryLocator,
            "jeans", menJeansSubCategoryLocator
    );

    private final Map<String, By> kidsLocator = Map.of(
            "dress", kidsDressSubCategoryLocator,
            "tops&tshirts", kidsTopsAndTshirtsSubCategoryLocator
    );

    private final Map<String, By> brandLocator = Map.of(
            "polo", poloBrandLocator,
            "h&m", hmBrandLocator,
            "madame", madameBrandLocator,
            "mast & harbour", mastBrandLocator,
            "babyhug", babyhugBrandLocator,
            "allen solly junior", allenSollyBrandLocator,
            "kookie kids", kookieBrandLocator,
            "biba", bibaBrandLocator
    );

    public LeftSideBarComponent clickOnWomenCategory(){
        framework.click(womenCategoryLocator);
        return this;
    }

    public LeftSideBarComponent clickOnMenCategory(){
        framework.click(menCategoryLocator);
        return this;
    }

    public LeftSideBarComponent clickOnKidsCategory(){
        framework.click(kidsCategoryLocator);
        return this;
    }

    public void navigateToWomenSubCategory(String subCategoryName){
        By CurrentLocator =  womenLocator.get(subCategoryName.toLowerCase());
        if (CurrentLocator!= null){
            clickOnWomenCategory()
                    .framework.click(CurrentLocator);
        }
        else
        {
            System.out.println("Unknown sub-category: " + subCategoryName);
        }
    }

    public void navigateToMenSubCategory(String subCategoryName){
        By CurrentLocator =  menLocator.get(subCategoryName.toLowerCase());
        if (CurrentLocator!= null){
            clickOnMenCategory()
                    .framework.click(CurrentLocator);
        }
        else
        {
            System.out.println("Unknown sub-category: " + subCategoryName);
        }
    }

    public void navigateToKidsSubCategory(String subCategoryName){
        By CurrentLocator =  kidsLocator.get(subCategoryName.toLowerCase());
        if (CurrentLocator!= null){
            clickOnKidsCategory()
                    .framework.click(CurrentLocator);
        }
        else
        {
            System.out.println("Unknown sub-category: " + subCategoryName);
        }
    }

    public void navigateToBrand(String brandName){
        By currentLocator =  brandLocator.get(brandName.toLowerCase());
        if (currentLocator!= null){
            framework.click(currentLocator);
        }
        else
        {
            System.out.println("Unknown brand: " + brandName);
        }
    }
}
