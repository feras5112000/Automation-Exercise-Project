package tests.ui.Products;

import Models.ProductData;
import Pages.HomePage;
import Pages.ProductDetailsPage;
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

@Epic("Automation Exercise")
@Feature("Products")
@Owner("Feras Osama")
public class ProductSearchAndDisplayTests extends BaseTests{
    private ProductData productData1;
    private ProductData productData2;

    //8
    @Test(groups = {"products", "regression"})
    @Story("Product Catalog")
    @Description("Verify users can browse all products and view detailed product information")
    @Severity(SeverityLevel.CRITICAL)
    public void verifyProductsAndProductDetails(){
        homePage
                .verifyHomePageVisible()
                .goToProductPage()
                .verifyCenteredText("ALL PRODUCTS", "'ALL PRODUCTS' is not visible")
                .verifyProductList()
                .clickViewProductIcon("1")
                .verifyProductDetailsAreVisible();
    }
    //9
    @Test(groups = {"products", "regression"})
    @Story("Product Discovery")
    @Description("Verify users can search for products using the search functionality")
    @Severity(SeverityLevel.CRITICAL)
    public void searchForProduct (){
        homePage
                .verifyHomePageVisible()
                .goToProductPage()
                .verifyCenteredText("ALL PRODUCTS", "'ALL PRODUCTS' is not visible")
                .verifyProductList()
                .searchByProductName(
                        productData1.getProductNameForSearch())
                .verifyCenteredText("SEARCHED PRODUCTS", "'SEARCHED PRODUCTS' is not visible");
    }

    //18
    @Test(groups = {"products", "regression"})
    @Story("Product Catalog - Navigation")
    @Description("Verify users can browse products by category")
    @Severity(SeverityLevel.NORMAL)
    public void viewCategoryProducts(){
        homePage
                .verifyHomePageVisible()
                .verifyCategoryGroupExistence()
                .chooseSpecificCategory(
                        productData1.getCategory(),
                        productData1.getSubCategory()
                )
                .verifyCenteredText("WOMEN - DRESS PRODUCTS", "'WOMEN - DRESS PRODUCTS' is not visible")
                .chooseSpecificCategory(
                        productData2.getCategory(),
                        productData2.getSubCategory())
                .verifyCenteredText("MEN - JEANS PRODUCTS", "'MEN - JEANS PRODUCTS' is not visible");
    }

    @BeforeClass(groups = {"products", "regression"})
    public void classSetup() throws FileNotFoundException {
        productData1 = Helper.ReadUser("product.json","womenCat", ProductData.class);
        productData2 = Helper.ReadUser("product.json","menCat", ProductData.class);
    }

}
