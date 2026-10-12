package Pages;

import HelperMethods.ElementMethods;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Home_Page {
    WebDriver driver;
    ElementMethods elementMethods;
    JavascriptExecutor js;

    public Home_Page(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this); //fara aceasta linie nu o sa gaseasca elementele

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement elementsButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cky-btn.cky-btn-accept")));
        elementsButton.click();
    }

    // Locators
    @FindBy(id = "Consumabile laser")
    WebElement consumabileLaserElement;

    @FindBy(xpath = "//a[normalize-space()='Gravură Laser']")
    WebElement gravuraLaserButton;

    @FindBy(xpath = "//a[@href='https://creativprintero.ro/cart/']")
    WebElement cosCumparaturiButton;

    @FindBy(xpath = "//a[text()='Contul Meu']")
    WebElement clickOnMyAccount;

    @FindBy(css = "input[type='search'], input[name='s']")
    WebElement searchInput;

    @FindBy(css = "button[type='submit'], .search-submit, form.search-form button, .search-button")
    WebElement searchButton;

    // Constructors
    public void clickOnMenu() {
        elementMethods.clickOnElement(consumabileLaserElement);
    }

    public void clickOnGravuraLaser() {
        elementMethods.clickOnElement(gravuraLaserButton);
    }

    public void cosCumparaturi() {
        WebElement cartButton = driver.findElement(By.xpath("//a[@href='https://creativprintero.ro/cart/']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", cartButton);
    }

    public void clickOnMyAccount() {
        elementMethods.clickOnElement(clickOnMyAccount);
    }

    public SearchResults_Page searchProduct(String productName) {
        elementMethods.fillElement(searchInput, productName);

        try {
            searchInput.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            searchInput.submit();
        }

        return new SearchResults_Page(driver);
    }

}


