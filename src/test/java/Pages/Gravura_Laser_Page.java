package Pages;

import HelperMethods.ElementMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Gravura_Laser_Page {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Locators
    @FindBy(xpath = "//a[normalize-space()='Gravură Laser']")
    private List<WebElement> laserEngravingCategories;

    @FindBy(xpath = "//a[.//img[@alt='Lentilă focalizare laser']]")
    private WebElement lensProduct;

    @FindBy(xpath = "//select[@id='focalizare' and @name='attribute_focalizare']")
    private WebElement focusDropdown;

    @FindBy(xpath = "//button[contains(@class,'single_add_to_cart_button')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//*[contains(text(), 'adăugat în coș') or contains(text(), 'added to your cart')]")
    private WebElement successMessage;

    @FindBy(css = "a.wc-forward")
    private WebElement viewCartButton;

    // Constructors
    public Gravura_Laser_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;

        PageFactory.initElements(driver, this);
    }

    public void selectThirdCategory() {

        if (laserEngravingCategories.size() >= 3) {
            laserEngravingCategories.get(2).click();
        } else {
            throw new RuntimeException(
                    "Fewer than 3 categories were found for 'Gravură Laser'");
        }
    }

    public void waitOnCartElement(ElementMethods elementMethods) {
        elementMethods.waitOnCartElement();
    }

    public void scrollDown(int pixels) {
        js.executeScript("window.scrollBy(0, arguments[0]);", pixels);
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    public void selectLensProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(lensProduct));
        lensProduct.click();
    }

    public void selectFocusByIndex(int index) {
        wait.until(ExpectedConditions.visibilityOf(focusDropdown));
        Select select = new Select(focusDropdown);
        select.selectByIndex(index);
    }

    public void addToCart() {

        WebDriverWait customWait =
                new WebDriverWait(driver, Duration.ofSeconds(5));

        try {

            customWait.until(
                    ExpectedConditions.elementToBeClickable(addToCartButton));

            addToCartButton.click();

        } catch (StaleElementReferenceException e) {

            PageFactory.initElements(driver, this);

            customWait.until(
                    ExpectedConditions.elementToBeClickable(addToCartButton));

            addToCartButton.click();
        }
    }

    public void acceptAlertIfPresent() {

        try {

            Alert alert = new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.alertIsPresent());

            alert.accept();

        } catch (TimeoutException e) {
            System.out.println("Alerta nu a apărut, se continuă execuția.");
        }
    }

    public boolean isSuccessMessageDisplayed() {

        wait.until(ExpectedConditions.visibilityOf(successMessage));

        return successMessage.isDisplayed();
    }

    public boolean isViewCartButtonDisplayed() {

        wait.until(ExpectedConditions.visibilityOf(viewCartButton));

        return viewCartButton.isDisplayed();
    }
}