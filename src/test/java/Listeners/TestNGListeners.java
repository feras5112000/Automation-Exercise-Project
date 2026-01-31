package Listeners;

import Utils.Framework;
import Utils.Helper;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import tests.BaseTests;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class TestNGListeners implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        Allure.step("✓ TEST PASSED: " + result.getName());
        System.out.println("✓ TEST PASSED: " + result.getName());

    }

    @Override
    public void onTestFailure(ITestResult result) {
        Allure.step("✗ TEST FAILED: " + result.getName());
        System.out.println("✗ TEST FAILED: " + result.getName());
        try {
            Helper.saveScreenshot("FAILED_"+ result.getName()+ "_", BaseTests.driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}