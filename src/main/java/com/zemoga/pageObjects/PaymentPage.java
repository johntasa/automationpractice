package com.zemoga.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.zemoga.utils.ExtentReportHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.seleniumhq.jetty9.server.handler.HandlerWrapper;

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

    public void proceedToCheckout() throws InterruptedException {
        btnBankWire.click();
        extentTest.createNode("Proceed to checkout - Payment step").pass("User was able to proceed to checkout order");
    }

    public void validateOrderSummary(String msg) {
        try {
            assertEquals(msg, lblOrderSummary.getText());
            extentTest.createNode("Validate Order Summary").pass("Successfully order");
        } catch (Exception e) {
            extentTest.createNode("Validate Order Summary").fail("Unsuccessfully order");
            throw new AssertionError("Unsuccessfully order");
        }
    }
}
