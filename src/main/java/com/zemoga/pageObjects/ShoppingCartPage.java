package com.zemoga.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.zemoga.utils.ExtentReportHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage {

    WebDriver driver;
    ExtentTest extentTest;

    public ShoppingCartPage(ExtentTest extentTest) {
        this.driver = ExtentReportHelper.getWebDriver();
        this.extentTest = extentTest;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "p.cart_navigation.clearfix a.button.btn.btn-default.standard-checkout.button-medium")
    private WebElement btnProceedToCheckout;

    public void proceedToCheckout() throws InterruptedException {
        btnProceedToCheckout.click();
        extentTest.createNode("Proceed to checkout - Shopping cart step").pass("User was able to proceed to checkout order");
    }
}
