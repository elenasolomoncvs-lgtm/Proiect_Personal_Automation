package Pages;

import HelperMethods.ElementMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Gravura_Laser_Page {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Locators (By)
    private By laserEngravingCategories = By.xpath("//a[normalize-space()='Gravură Laser']");
    private By lensProduct = By.xpath("//a[.//img[@alt='Lentilă focalizare laser']]");
    private By focusDropdown = By.xpath("//select[@id='focalizare' and @name='attribute_focalizare']");
    private By addToCartButton = By.xpath("//button[contains(text(),'Adaugă în coș')]");
    private By successMessage = By.xpath("//*[contains(text(), 'adăugat în coș') or contains(text(), 'added to your cart')]");
    private By viewCartButton = By.cssSelector("a.wc-forward");

    // Constructor
    public Gravura_Laser_Page(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    // Action Methods
    public void selectThirdCategory() {
        List<WebElement> categoryList = driver.findElements(laserEngravingCategories);
        if (categoryList.size() >= 3) {
            categoryList.get(2).click();
        } else {
            throw new RuntimeException("Fewer than 3 categories were found for 'Gravură Laser'");
        }
    }

    public void waitOnCartElement(ElementMethods elementMethods) {
        elementMethods.waitOnCartElement();
    }

    public void scrollDown(int pixels) {
        js.executeScript("window.scrollBy(0, " + pixels + ");");
    }

    public void scrollToTop() {
        js.executeScript("window.scrollTo(0, 0);");
    }

    public void selectLensProduct() {
        WebElement product = wait.until(ExpectedConditions.elementToBeClickable(lensProduct));
        product.click();
    }

    public void selectFocusByIndex(int index) {
        WebElement dropdownElement = wait.until(ExpectedConditions.presenceOfElementLocated(focusDropdown));
        Select select = new Select(dropdownElement);
        select.selectByIndex(index);
    }


    public void addToCart() {

        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        try {
            WebElement button = customWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'single_add_to_cart_button')]")));
            button.click();

        } catch (StaleElementReferenceException e) {WebElement button = customWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(@class,'single_add_to_cart_button')]")));
            button.click();
        }
    }

    public void acceptAlertIfPresent() {
        // Așteaptă până la 5 secunde să apară alerta și apoi comută pe ea
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // un timp mai scurt
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (TimeoutException e) {
            System.out.println("Alerta nu a apărut, se continuă execuția.");
        }
    }

//    public String getConfirmationMessageText() {
//        //By viewCartButton = By.xpath("//*[contains(text(),'a fost adăugat în coș')]");
//        By viewCartButton = By.xpath("//a[normalize-space()='Vezi coșul']");
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartButton)).getText();
//    }

//    public boolean isViewCartButtonDisplayed() {
//        By viewCartButton = By.cssSelector("a.wc-forward");;
//
//        return wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartButton)).isDisplayed();
//    }

    public boolean isViewCartButtonDisplayed() {

        return wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartButton)).isDisplayed();
    }
}
