package tests.ui.Products;

import Models.ProductData;
import Pages.HomePage;
import Utils.Helper;
import io.qameta.allure.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTests;

import java.io.FileNotFoundException;


@Epic("Automation Exercise")
@Feature("Products")
@Owner("Feras Osama")
public class ProductInteractionTests extends BaseTests{
    private ProductData brandData;
    private ProductData reviewData;


    @Test(groups = {"products", "regression"})
    @Story("Product Catalog - Brand Navigation")
    @Description("Verify users can browse products by brand and add them to cart")
    @Severity(SeverityLevel.NORMAL)
    public void filterProductsByBrand(){
        homePage
                .verifyHomePageVisible()
                .goToProductPage()
                .verifyBrandGroupExistence()
                .chooseSpecificBrand(brandData.getBrandName1())
                .verifyCenteredText(
                        "BRAND - " + brandData.getBrandName1().toUpperCase() + " PRODUCTS",
                        "Brand filtering did not work as expected"
                )
                .chooseSpecificBrand(brandData.getBrandName2())
                .verifyCenteredText(
                        "BRAND - " + brandData.getBrandName2().toUpperCase() + " PRODUCTS",
                        "Brand filtering did not work as expected"
                );
    }

    @Test(groups = {"products", "regression"})
    @Story("User Engagement - Product Reviews")
    @Description("Verify users can submit product reviews")
    @Severity(SeverityLevel.NORMAL)
    public void addReviewToProduct(){
        homePage
                .verifyHomePageVisible()
                .goToProductPage()
                .verifyProductList()
                .verifyCenteredText("ALL PRODUCTS","'ALL PRODUCTS' is not visible")
                .clickViewProductIcon("1")
                .verifyWriteYourReviewText()
                .fillProductReviewForm(
                    reviewData.getReviewName(),
                    reviewData.getReviewEmail(),
                    reviewData.getReviewMessage())
                .submitProductReview()
                .verifyReviewSubmissionMessage();
        }


    @BeforeClass(groups = {"products", "regression"})
    public void classSetup() throws FileNotFoundException {
        brandData = Helper.ReadUser("product.json","brand",  ProductData.class);
        reviewData = Helper.ReadUser("product.json","review",  ProductData.class);
    }

}

