package Pages;

import DataBase.Queries.CheckOutTable;
import HelperMethods.ElementMethods;
import Logger.LoggerUtility;
import ObjectData.CheckOutObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.SQLException;
import java.time.Duration;

public class CheckOutPage {

    private WebDriver driver;
    private ElementMethods elementMethods;
    private WebDriverWait wait;
    CheckOutTable checkOutTable;

    // Locators
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

    @FindBy(xpath = "//a[normalize-space()='Materiale consumabile']")
    private WebElement menuElement;

    @FindBy(xpath = "//a[@data-product_id='2389']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//span[@class='cart-value']")
    private WebElement itemAmount;

    @FindBy(xpath = "//a[contains(@href, '/checkout/')]")
    private WebElement checkoutLink;

    @FindBy(css = ".blockUI.blockOverlay")
    private WebElement loadingOverlay;

    @FindBy(xpath = "//*[@id='shipping_method_0_flat_rate15']")
    private WebElement shippingOption;

    // Constructors

    public CheckOutPage(WebDriver driver) throws SQLException {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        this.checkOutTable = new CheckOutTable();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }

    public void clickOnMenu() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", menuElement);
    }

    public void addProductToCart() {

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", addToCartButton);
        elementMethods.clickOnElement(addToCartButton);

        wait.until(driver -> {
            try {
                return Integer.parseInt(itemAmount.getText().trim()) > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        });

        elementMethods.clickOnElement(itemAmount);
    }

    public void selectShippingMethod() {

        try {
            wait.until(ExpectedConditions.invisibilityOf(loadingOverlay));
        } catch (Exception ignored) {
        }

        wait.until(ExpectedConditions.elementToBeClickable(shippingOption));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", shippingOption);
    }

    public void proceedToCheckout() {

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

    public void acceptAlertIfPresent() {

        try {

            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.alertIsPresent());

            alert.accept();

        } catch (TimeoutException e) {

            System.out.println("No alert displayed.");
        }
    }

    public void addEntryInTable(CheckOutObject data) throws SQLException {
        checkOutTable.insertTableRow(data);

    }
}