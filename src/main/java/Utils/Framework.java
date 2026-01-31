package Utils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.*;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

/**
 * Framework Utility Class
 * Author: Feras Osama
 * Description: This class provides a comprehensive wrapper around Selenium WebDriver
 *              with enhanced functionality for test automation.
 */
public class Framework {
    private static WebDriver Driver;
    private final Duration timeout = Duration.ofSeconds(5);

    // ==================== CONSTRUCTORS ====================

    // Default constructor - initializes Chrome driver
    public Framework() {
        Driver = new ChromeDriver();
        Driver.manage().window().maximize();
    }

    // Constructor with existing WebDriver instance
    public Framework(WebDriver driver) {
        Driver = driver;
    }

    // Constructor with driver type selection
    public Framework(String Driver_type) {
        initDriver(Driver_type);
    }

    // ==================== DRIVER MANAGEMENT ====================

    // Static method to set WebDriver instance
    public static void setDriver(WebDriver driver) {
        Driver = driver;
    }

    // Initialize driver based on specified type
    public void initDriver(@org.jetbrains.annotations.NotNull String Driver_type)
    {
        if (Driver_type.equalsIgnoreCase("Edge")) {
            Driver = new EdgeDriver();
            Driver.manage().window().maximize();
        }
        else if(Driver_type.equalsIgnoreCase("Chrome")){
            Driver = new ChromeDriver();
            Driver.manage().window().maximize();
        }
        else if (Driver_type.equalsIgnoreCase("Firefox")) {
            Driver = new FirefoxDriver();
            Driver.manage().window().maximize();
        }
        else {
            System.out.println("Unsupported driver type " + Driver_type);
        }
    }

    // ==================== BROWSER NAVIGATION ====================

    // Navigate to specified URL
    public void navigateTo(String URL){
        System.out.println("Navigating to URL: " + URL);
        Driver.navigate().to(URL);
    }

    // Get current URL
    public String getCurrentUrl(){
        return Driver.getCurrentUrl();
    }

    // Get page title
    public String getTitle(){
        return Driver.getTitle();
    }

    // ==================== BROWSER CONTROL ====================

    // Close current tab
    public void close(){
        Driver.close();
    }

    // Close all tabs and quit browser
    public void quit(){
        Driver.quit();
    }

    // ==================== ELEMENT LOCATION & WAITS ====================

    // Get element with explicit wait for visibility
    public WebElement getElement(By Locator){
        WebDriverWait explicitWait = new WebDriverWait(Driver, timeout);
        return explicitWait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
    }

    // Fluent wait implementation
    public void fluentWait(int timeInSecond , int pollingTimeInMilliSecond , By Locator , String Message) {
        Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(Driver)
                .withTimeout(Duration.ofSeconds(timeInSecond))
                .pollingEvery(Duration.ofMillis(pollingTimeInMilliSecond))
                .withMessage(Message)
                .ignoring(NoSuchElementException.class , TimeoutException.class);
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
    }

    // Implicit wait
    public void implicitWait(int timeInSecond){
        Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSecond));
    }

    // Explicit wait
    public void explicitWait(By locator, int timeoutSeconds) {
        new WebDriverWait(Driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Explicit wait returning element
    public WebElement explicitWaitReturnElement(By locator, int timeoutSeconds) {
        return new WebDriverWait(Driver, Duration.ofSeconds(timeoutSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ==================== ELEMENT ACTIONS ====================

    // Send text to element
    public void sendText(By Locator ,String Text){
        getElement(Locator).sendKeys(Text);
    }

    // Clear element text
    public void clear(By Locator){
        getElement(Locator).clear();
    }

    // Get text from element
    public String getText(By locator) {
        WebDriverWait explicitWait = new WebDriverWait(Driver, timeout);
        String text = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
        return text;
    }

    // Click on element
    public void click(By Locator){
        WebDriverWait explicitWait = new WebDriverWait(Driver, timeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(Locator)).click();
    }

    // ==================== DROPDOWN HANDLING ====================

    // Select dropdown by index
    public void dropdownByIndex(By Locator ,int index){
        Select DropDownMenu = new Select(getElement(Locator));
        DropDownMenu.selectByIndex(index);
    }

    // Select dropdown by value
    public void dropdownByValue(By Locator ,String Value){
        Select DropDownMenu = new Select(getElement(Locator));
        DropDownMenu.selectByValue(Value);
    }

    // Select dropdown by visible text
    public void dropdownByVisibleText(By Locator ,String VisibleText){
        Select DropDownMenu = new Select(getElement(Locator));
        DropDownMenu.selectByVisibleText(VisibleText);
    }

    // ==================== CHECKBOX HANDLING ====================

    // Get checkbox selection status
    public boolean checkBoxStatus(By Locator){
        return getElement(Locator).isSelected();
    }

    // Select checkbox if not already selected
    public void selectCheckBox(By Locator){
        if(!checkBoxStatus(Locator)){
            click(Locator);
        }
        else
        {
            System.out.println("CheckBox is already Selected");
        }
    }

    // Deselect checkbox if currently selected
    public void deselectCheckBox (By Locator){
        if(checkBoxStatus(Locator)){
            click(Locator);
        }
        else
        {
            System.out.println("CheckBox is already NOT Selected");
        }
    }

    // ==================== SCREENSHOTS ====================

    // Take screenshot and save to file
    public static File TakeScreenshot(String filename) throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File src = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
        File Dest = new File("screenshot_" + filename + "_" + timestamp + ".png");
        FileHandler.copy(src, Dest);
        return Dest;
    }

    // Get timestamp for screenshot naming
    public static String getSimpleTimestamp() {
        return Long.toString(System.currentTimeMillis());
    }

    // ==================== ACTIONS & SCROLLING ====================

    // Scroll to element using Actions class
    public void scrollToElement(By locator) {
        WebElement element = explicitWaitReturnElement(locator, 5);
        Actions actions = new Actions(Driver);
        actions.scrollToElement(element).perform();
    }

    // Hover over element and click another element
    public void hoverAndClick(By locatorToHover, By locatorToClick) {
        WebElement hoverElement = new WebDriverWait(Driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(locatorToHover));
        WebElement clickElement = new WebDriverWait(Driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(locatorToClick));
        Actions actions = new Actions(Driver);
        actions.moveToElement(hoverElement)
                .click(clickElement)
                .perform();
    }

    // ==================== ALERT HANDLING ====================

    // Accept alert
    public void acceptAlert() {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent()).accept();
    }

    // Dismiss alert
    public void dismissAlert() {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent()).dismiss();
    }

    // Get alert text
    public String getAlertText() {
        WebDriverWait wait = new WebDriverWait(Driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.alertIsPresent()).getText();
    }
}