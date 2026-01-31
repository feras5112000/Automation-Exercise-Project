package Verifications;


import org.openqa.selenium.WebDriver;

public abstract class BaseAssertion {
    protected WebDriver driver;

    protected BaseAssertion() {}

    protected BaseAssertion(WebDriver driver) {
        this.driver = driver;
    }

    public abstract void assertTrue(boolean condition, String message);

    public abstract void assertFalse(boolean condition, String message);

    public abstract void assertEquals(String actual, String expected, String message);

    public BaseAssertion Equals(String actual, String expected, String message) {
        assertEquals(actual, expected, message);
        return this;
    }

    // verify page url
    public void assertPageUrl(String expectedUrl) {
        String actualUrl = driver.getCurrentUrl();
        assertEquals(actualUrl, expectedUrl, "URL does not match. Expected: " + expectedUrl + ", Actual: " + actualUrl);
    }

    // verify page title
    public void assertPageTitle(String expectedTitle) {
        String actualTitle = driver.getTitle();
        assertEquals(actualTitle, expectedTitle, "Title does not match. Expected: " + expectedTitle + ", Actual: " + actualTitle);
    }

}
