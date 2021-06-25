package com.zemoga.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.zemoga.utils.ExtentReportHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShippingPage {

    WebDriver driver;
    ExtentTest extentTest;

    public ShippingPage(ExtentTest extentTest) {
        this.driver = ExtentReportHelper.getWebDriver();
        this.extentTest = extentTest;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "button.btn.btn-default.standard-checkout.button-medium")
    private WebElement btnProceedToCheckout;

    @FindBy(how = How.CSS, using = "div.checker input")
    private WebElement chckbxTerms;

    public void proceedToCheckout() throws InterruptedException {
        chckbxTerms.click();
        btnProceedToCheckout.click();
        extentTest.createNode("Proceed to checkout - Shipping step").pass("User was able to proceed to checkout order");
    }
}
