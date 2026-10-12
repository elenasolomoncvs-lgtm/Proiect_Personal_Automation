package Pages;

import HelperMethods.ElementMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResults_Page {
    WebDriver driver;
    ElementMethods elementMethods;

    public SearchResults_Page(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".product, ul.products li, .product-title, .woocommerce-loop-product__title")
    List<WebElement> productList;

    @FindBy(css = "h1.page-title, h1.entry-title")
    WebElement pageTitle;

    @FindBy(xpath = "//*[contains(text(),'Nu a fost găsit niciun produs') or contains(text(),'No products were found matching your selection')]")
    WebElement noResultsMessage;

    public boolean hasResults() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.urlContains("s="));
        } catch (Exception ignored) {
            // Dacă URL-ul nu conține s=, continuăm verificarea
        }

        return !productList.isEmpty();
    }

    public int getResultsCount() {
        return productList.size();
    }

    public boolean isNoResultsMessageDisplayed() {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(noResultsMessage));
            return noResultsMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNoResultsMessageText() {
        return noResultsMessage.getText();
    }
}
