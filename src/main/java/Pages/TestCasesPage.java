package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends BasePage<TestCasesPage>{
    //Locators
    private final By TestCasesLocator = By.tagName("b");

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    //verifications
    @Step("Verify TestCases test visibility")
    public TestCasesPage verifyTestCasesText() {
        verification.assertTrue(framework.getText(TestCasesLocator).contains("TEST CASES"),"Test Cases text is not visible");
        return this;
    }

}
