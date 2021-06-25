package com.zemoga.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.zemoga.utils.ExtentReportHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    WebDriver driver;
    ExtentTest extentTest;

    public HomePage(ExtentTest extentTest) {
        this.driver = ExtentReportHelper.getWebDriver();
        this.extentTest = extentTest;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "search_query_top")
    private WebElement txtbxSearch;

    @FindBy(how = How.CSS, using = "button.button-search")
    private WebElement btnSearch;


    public void performSearch(String search) {
        txtbxSearch.sendKeys(search);
        btnSearch.click();
        extentTest.createNode("Perform a search").pass("User was able to search " + search);
    }
}