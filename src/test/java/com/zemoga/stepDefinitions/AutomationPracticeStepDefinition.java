package com.zemoga.stepDefinitions;

import com.aventstack.extentreports.ExtentTest;
import com.zemoga.pageobjects.*;
import com.zemoga.utils.ExtentReportHelper;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AutomationPracticeStepDefinition {
    WebDriver driver;
    HomePage home;
    SearchResultPage searchResult;
    ShoppingCartPage shoppingCart;
    AuthenticationPage authentication;
    AddressesPage addresses;
    ShippingPage shipping;
    PaymentPage payment;
    Scenario scenario;
    ExtentTest extentTest;

    @Before
    public void testStart(Scenario scenario) throws Throwable {
        driver = ExtentReportHelper.getWebDriver();
        ExtentReportHelper.getExtentReport();
        this.scenario = scenario;
    }

    @After
    public void testEnd() throws Throwable {
        ExtentReportHelper.getExtentReport().flush();
        ExtentReportHelper.quitWebDriver();
    }

    @Given("^user is on home page$")
    public void userIsOnHomePage() {
        driver.get("http://automationpractice.com/index.php");
        extentTest = ExtentReportHelper.getExtentReport().createTest(scenario.getName());
    }

    @Given("^user is on authentication site$")
    public void userIsOnAuthenticationSite() throws Throwable {
        userIsOnHomePage();
        userTypesIntoTheTextBoxThe("Blouse");
        userAddAProductToShoppingCart();
        goesToShoppingCartToProceedWithPurchase();
    }

    @When("^user types into the text box the (.+)$")
    public void userTypesIntoTheTextBoxThe(String word) {
        home = new HomePage(extentTest);
        home.performSearch(word);
    }

    @When("^user creates an account$")
    public void userCreatesAnAccount(Map<String, List<String>> signupInformation) throws Throwable {
        authentication = new AuthenticationPage(extentTest);
        authentication.signUp(signupInformation);
    }

    @And("^(.+) products by lower price$")
    public void productsByLowerPrice(String sort) throws Throwable {
        searchResult = new SearchResultPage(extentTest);
        searchResult.sortResults(sort);
    }

    @And("^user add a product to shopping cart$")
    public void userAddAProductToShoppingCart() throws InterruptedException {
        searchResult = new SearchResultPage(extentTest);
        searchResult.addToCart();
    }

    @And("^goes to shopping cart to proceed with purchase$")
    public void goesToShoppingCartToProceedWithPurchase() throws Throwable {
        shoppingCart = new ShoppingCartPage(extentTest);
        searchResult.proceedToCheckoutFrame();
        shoppingCart.proceedToCheckout();
    }

    @And("^user proceed to checkout$")
    public void userProceedToCheckout() throws Throwable {
        addresses = new AddressesPage(extentTest);
        addresses.proceedToCheckout();
        shipping = new ShippingPage(extentTest);
        shipping.proceedToCheckout();
        payment = new PaymentPage(extentTest);
        payment.proceedToCheckout();
    }

    @Then("^user should see (.+) related to the (.+)$")
    public void userShouldSeeRelatedToTheSearch(String results, String word) throws InterruptedException, IOException {
        searchResult = new SearchResultPage(extentTest);
        searchResult.validateResults(word, results);
    }

    @Then("^user should see results ordered lowest to highest price$")
    public void userShouldSeeResultsOrderedLowestToHighestPrice() throws IOException {
        searchResult.validateSortPrices();
    }

    @Then("^user should see the product (.+) to shopping cart$")
    public void userShouldSeeTheProductToShoppingCart(String added) throws IOException {
        searchResult.validateAddedProduct(added);
    }

    @Then("^user should see the authentication step$")
    public void userShouldSeeTheAuthenticationStep() throws Throwable {
        authentication = new AuthenticationPage(extentTest);
        authentication.validateAuthenticationStep();
    }

    @Then("^user should see notification (.+) to confirm the order$")
    public void userShouldSeeNotificationMessageToConfirmTheOrder(String msg) throws Throwable {
        payment.validateOrderSummary(msg);
    }
}