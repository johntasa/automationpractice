package com.zemoga.pageobjects;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.zemoga.utils.ExtentReportHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

import static com.zemoga.utils.GetScreenshot.getScreenshot;
import static org.junit.Assert.assertEquals;

public class PaymentPage {

    WebDriver driver;
    ExtentTest extentTest;

    public PaymentPage(ExtentTest extentTest) {
        this.driver = ExtentReportHelper.getWebDriver();
        this.extentTest = extentTest;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "a.bankwire")
    private WebElement btnBankWire;

    @FindBy(how = How.CSS, using = "p.cheque-indent")
    private WebElement lblOrderSummary;

    public void proceedToCheckout() {
        btnBankWire.click();
        extentTest.createNode("Proceed to checkout - Payment step").pass("User was able to proceed to checkout order");
    }

    public void validateOrderSummary(String msg) throws IOException {
        try {
            assertEquals(msg, lblOrderSummary.getText());
            extentTest.createNode("Validate Order Summary").pass("Successfully order");
        } catch (Exception e) {
            String screenshotPath = getScreenshot(driver, "screenshot");
            extentTest.createNode("Validate Order Summary").fail("Unsuccessfully order",
                    MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            throw new AssertionError("Unsuccessfully order");
        }
    }
}
