package Pages;

import HelperMethods.ElementMethods;
import Logger.LoggerUtility;
import ObjectData.CheckOutObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class CheckOutPage {

    private WebDriver driver;
    private ElementMethods elementMethods;
    private WebDriverWait wait;

    // Locatori @FindBy cu ID-urile reale WooCommerce din HTML
    @FindBy(id = "billing_first_name")
    private WebElement firstNameField;

    @FindBy(id = "billing_last_name")
    private WebElement lastNameField;

    @FindBy(id = "billing_email")
    private WebElement emailAddressField;

    @FindBy(id = "billing_phone")
    private WebElement phoneNumberField;

    @FindBy(id = "billing_address_1")
    private WebElement addressField;

    @FindBy(id = "billing_city")
    private WebElement cityField;

    @FindBy(id = "select2-billing_state-container")
    private WebElement stateField;

    @FindBy(id = "billing_postcode")
    private WebElement postCodeField;

    // Locatori By pentru acțiuni speciale sau elemente dinamice
    private By menuBy = By.xpath("//a[normalize-space()='Materiale consumabile']");
    private By addToCartBy = By.xpath("//a[@data-product_id='2389']");
    private By checkoutLinkBy = By.xpath("//a[contains(@href, '/checkout/')]");

    // Constructor
    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }

    public void clickOnMenu() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement menuElement = driver.findElement(menuBy);
        js.executeScript("arguments[0].click();", menuElement);
    }

    public void addProductToCart() {
        WebElement addToCartButton = driver.findElement(addToCartBy);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", addToCartButton);

        elementMethods.clickOnElement(addToCartButton);
        wait.until(driver -> {
            WebElement itemAmount = driver.findElement(By.xpath("//span[@class='cart-value']"));

            try {
                return Integer.parseInt(itemAmount.getText().trim()) > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        });

        WebElement itemAmount = driver.findElement(By.xpath("//span[@class='cart-value']"));
        elementMethods.clickOnElement(itemAmount);
    }

    public void selectShippingMethod() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".blockUI.blockOverlay")));
        WebElement shippingOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\'shipping_method_0_flat_rate15\']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", shippingOption);
    }

    public void proceedToCheckout() {
        WebElement checkoutLink = driver.findElement(checkoutLinkBy);
        elementMethods.clickOnElement(checkoutLink);
    }

    public void completeDeliveryDetails(CheckOutObject data) {
        elementMethods.enterText(firstNameField, data.getFirstName());
        elementMethods.enterText(lastNameField, data.getLastName());
        elementMethods.enterText(emailAddressField, data.getEmailAddress());
        elementMethods.enterText(phoneNumberField, data.getPhoneNumber());
        elementMethods.enterText(addressField, data.getAddress());
        elementMethods.enterText(cityField, data.getCity());
        elementMethods.enterText(postCodeField, data.getPostCode());
    }

    public void completeState(CheckOutObject data) {
        elementMethods.clickOnElement(stateField);
        elementMethods.enterTextUsingActions(stateField, data.getState());
    }

    public void verifyEntry(CheckOutObject data) {
        Assert.assertEquals(firstNameField.getAttribute("value"), data.getFirstName(), "Prenumele nu a fost corect");
        LoggerUtility.infoTestCase("User verified First Name");

        Assert.assertEquals(lastNameField.getAttribute("value"), data.getLastName(), "Numele nu a fost corect");
        LoggerUtility.infoTestCase("User verified Last Name");

        Assert.assertEquals(emailAddressField.getAttribute("value"), data.getEmailAddress(), "Email-ul nu a fost corect");
        LoggerUtility.infoTestCase("User verified the Email");

        Assert.assertEquals(phoneNumberField.getAttribute("value"), data.getPhoneNumber(), "Numarul de telefon nu a fost corect");
        LoggerUtility.infoTestCase("User verified the Phone Number");

        Assert.assertEquals(addressField.getAttribute("value"), data.getAddress(), "Adresa nu a fost corecta");
        LoggerUtility.infoTestCase("User verified the Address");

        Assert.assertEquals(cityField.getAttribute("value"), data.getCity(), "Orasul nu a fost corect");
        LoggerUtility.infoTestCase("User verified the City");

        Assert.assertEquals(postCodeField.getAttribute("value"), data.getPostCode(), "Codul Postal nu a fost corect");
        LoggerUtility.infoTestCase("User verified the Postcode");
    }
}