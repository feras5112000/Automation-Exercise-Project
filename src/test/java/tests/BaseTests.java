package tests;

import Pages.HomePage;
import Listeners.TestNGListeners;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;


@Listeners({TestNGListeners.class})
public class BaseTests {
    public static WebDriver driver;
    protected HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void methodSetup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomePage(driver).open();
        System.out.println("HomePage loaded successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver quit successfully");
        }
    }
}
