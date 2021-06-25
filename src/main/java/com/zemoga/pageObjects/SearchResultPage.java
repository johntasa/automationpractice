package com.zemoga.pageObjects;

import com.aventstack.extentreports.ExtentTest;
import com.zemoga.utils.ExtentReportHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchResultPage {

    WebDriver driver;
    ExtentTest extentTest;

    public SearchResultPage(ExtentTest extentTest) {
        this.driver = ExtentReportHelper.getWebDriver();
        this.extentTest = extentTest;
        PageFactory.initElements(driver, this);
    }

    @FindAll(@FindBy(how = How.CSS, using = "select.selectProductSort option"))
    private List<WebElement> drpdwnSortProduct;

    @FindBy(how = How.CSS, using = "h1.page-heading span.heading-counter")
    private WebElement lblResultCounter;

    @FindAll(@FindBy(how = How.CSS, using = "ul.product_list a.product-name"))
    private List<WebElement> listProductsName;

    @FindBy(how = How.CSS, using = "ul.product_list div.right-block span.price")
    private List<WebElement> listProductsPrice;

    @FindBy(how = How.CSS, using = "p.buttons_bottom_block.no-print button.exclusive")
    private WebElement btnAddToCart;

    @FindBy(how = How.CSS, using = "div.product-container img.replace-2x.img-responsive")
    private WebElement imgProduct;

    @FindBy(how = How.CSS, using = "iframe.fancybox-iframe")
    private WebElement iframeProduct;

    @FindBy(how = How.CSS, using = "div.layer_cart_product.col-xs-12.col-md-6 h2")
    private WebElement lblProductAdded;

    @FindBy(how = How.CSS, using = "div.layer_cart_cart.col-xs-12.col-md-6 a.btn.btn-default.button.button-medium")
    private WebElement btnProceedToCheckoutFrame;


    public void proceedToCheckoutFrame() throws InterruptedException {
        driver.switchTo().defaultContent();
        btnProceedToCheckoutFrame.click();
    }

    public void sortResults(String sort) {
        for (WebElement sortProduct : drpdwnSortProduct) {
            if (sortProduct.getText().contains(sort)) {
                sortProduct.click();
                extentTest.createNode("Perform lower price sort").pass("User was able to sort price");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                break;
            }
        }
    }

    public void addToCart() throws InterruptedException {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        imgProduct.click();
        driver.switchTo().frame(iframeProduct);
        btnAddToCart.click();
        extentTest.createNode("Add product to cart").pass("User was able to add a product to shopping cart");
    }

    public void validateAddedProduct(String added) {
        try {
            assertEquals(added, lblProductAdded.getText());
            extentTest.createNode("Validate added product").pass("Product successfully added");
        } catch (Exception e) {
            extentTest.createNode("Validate added product").fail("Product unsuccessfully added");
            throw new AssertionError("Product unsuccessfully added");
        }
    }

    public void validateSortPrices() {

        List<Float> priceList = new ArrayList<Float>();
        List<Float> priceListSorted = new ArrayList<Float>();

        for (WebElement productPrice : listProductsPrice) {
            priceListSorted.add(Float.parseFloat(productPrice.getText().replace("$", "")));
            priceList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
        }
        Collections.sort(priceListSorted);

        if (priceList != priceListSorted) {
            extentTest.createNode("Validate sorted prices").fail("Results not properly sorted");
            throw new AssertionError("Results not properly sorted");
        } else {
            extentTest.createNode("Validate sorted prices").pass("Results properly sorted");
        }
    }

    public void validateResults(String search, String results) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            for (WebElement productName : listProductsName) {
                assertTrue(productName.getText().contains(search));
            }
            extentTest.createNode("Validate search results").pass("Results properly show");
        } catch (Exception e) {
            extentTest.createNode("Validate search results").fail("Results not properly show");
            throw new AssertionError("Results not properly show");
        }
        assertEquals(results, lblResultCounter.getText());

    }
}