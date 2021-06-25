package com.zemoga.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.zemoga.utils.ExtentReportHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.zemoga.utils.GenerateRandom.generateRandom;
import static org.junit.Assert.assertEquals;

public class AuthenticationPage {

    WebDriver driver;
    ExtentTest extentTest;

    public AuthenticationPage(ExtentTest extentTest) {
        this.driver = ExtentReportHelper.getWebDriver();
        this.extentTest = extentTest;
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.CSS, using = "div.center_column.col-xs-12.col-sm-12 h1.page-heading")
    private WebElement lblAuthentication;

    @FindBy(how = How.ID, using = "email_create")
    private WebElement txtbxEmail;

    @FindBy(how = How.ID, using = "SubmitCreate")
    private WebElement btnCreateAccount;

    @FindBy(how = How.ID, using = "id_gender1")
    private WebElement rdbtnGender;

    @FindBy(how = How.ID, using = "customer_firstname")
    private WebElement txtbxFirstName;

    @FindBy(how = How.ID, using = "customer_lastname")
    private WebElement txtbxLastName;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement txtbxPassword;

    @FindBy(how = How.ID, using = "days")
    private WebElement drpdwnDay;

    @FindBy(how = How.ID, using = "months")
    private WebElement drpdwnMonth;

    @FindBy(how = How.ID, using = "years")
    private WebElement drpdwnYear;

    @FindBy(how = How.ID, using = "address1")
    private WebElement txtbxAddress;

    @FindBy(how = How.ID, using = "city")
    private WebElement txtbxCity;

    @FindBy(how = How.ID, using = "id_state")
    private WebElement drpdwnState;

    @FindBy(how = How.ID, using = "postcode")
    private WebElement txtbxZip;

    @FindBy(how = How.ID, using = "phone_mobile")
    private WebElement txtbxMobilePhone;

    @FindBy(how = How.ID, using = "submitAccount")
    private WebElement btnRegister;

    public void signUp(Map<String, List<String>> signupInformation) {
        txtbxEmail.sendKeys(signupInformation.get("email").get(0).replace("@", generateRandom() + "@"));
        btnCreateAccount.click();

        rdbtnGender.click();
        txtbxFirstName.sendKeys(signupInformation.get("firstname").get(0));
        txtbxLastName.sendKeys(signupInformation.get("lastname").get(0));
        txtbxPassword.sendKeys(signupInformation.get("password").get(0));

        Select drpDay = new Select(drpdwnDay);
        drpDay.selectByVisibleText(signupInformation.get("day").get(0) + "  ");
        Select drpMonth = new Select(drpdwnMonth);
        drpMonth.selectByVisibleText(signupInformation.get("month").get(0) + " ");
        Select drpYear = new Select(drpdwnYear);
        drpYear.selectByVisibleText(signupInformation.get("year").get(0) + "  ");

        txtbxAddress.sendKeys(signupInformation.get("address").get(0));
        txtbxCity.sendKeys(signupInformation.get("city").get(0));

        Select drpState = new Select(drpdwnState);
        drpState.selectByVisibleText(signupInformation.get("state").get(0));

        txtbxZip.sendKeys(signupInformation.get("zip").get(0));
        txtbxMobilePhone.sendKeys(signupInformation.get("mobilephone").get(0));
        btnRegister.click();
        extentTest.createNode("Proceed to checkout - Create Account step").pass("User created an account to proceed to checkout order");
    }

    public void validateAuthenticationStep() throws InterruptedException {
        Thread.sleep(3000);
        try {
            assertEquals("AUTHENTICATION", lblAuthentication.getText());
            extentTest.createNode("Validate authentication step").pass("Site is on Authentication view");
        } catch (Exception e) {
            extentTest.createNode("Validate authentication step").fail("Site isn't on Authentication view");
            throw new AssertionError("Site isn't on Authentication view");
        }
    }
}
