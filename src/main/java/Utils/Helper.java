package Utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
    private static final String TestPrjRoot = "src/test/";
    private static final String TestDataFolder = "resources/TestData/";

    private static final Properties properties = new Properties();

    static {
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/config/url.properties");
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load url.properties file!");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static String ReadFromFile(String fileName, String Key) throws FileNotFoundException
    {
        FileReader reader = new FileReader(TestPrjRoot+TestDataFolder+fileName);
        JsonElement element = JsonParser.parseReader(reader);
        return element.getAsJsonObject().get(Key).getAsString();
    }

    public static <T> T ReadUser(String fileName ,String key ,Class<T> className ) throws FileNotFoundException {
        FileReader reader = new FileReader(TestPrjRoot + TestDataFolder + fileName);
        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();

        JsonElement element = jsonObject.get(key);
        return gson.fromJson(element, className);
    }

    public static String generateUniqueValue() {
        return Framework.getSimpleTimestamp();
    }

    public static void saveScreenshot(String fileName, WebDriver driver) throws IOException {
        if (driver == null) {
            throw new IOException("WebDriver cannot be null");
        }

        System.out.println("=== Taking screenshot: " + fileName + " ===");

        // Create directory
        Path screenshotsDir = Paths.get("screenshots");
        if (!Files.exists(screenshotsDir)) {
            Files.createDirectories(screenshotsDir);
        }

        // Generate filename
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fullFileName = fileName + "_" + timestamp + ".png";
        Path filePath = screenshotsDir.resolve(fullFileName);

        // Take and save screenshot
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenshot.toPath(), filePath);

        System.out.println("Screenshot saved: " + filePath.toAbsolutePath());

        // Add to Allure
        Allure.addAttachment(fileName, Files.newInputStream(filePath));

        System.out.println("✓ Screenshot added to Allure");
    }
}
