package HelperMethods;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class ElementMethods {
    WebDriver driver;
    WebDriverWait wait;


    public ElementMethods(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void waitVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickOnElement(WebElement element) {
        waitVisibility(element);
        element.click();
    }

    public void enterTextUsingActions(WebElement element, String text) {
        Actions actions = new Actions(driver);
        actions.sendKeys(text).perform();
        waitVisibility(element);
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void selectElementFromListByText(List<WebElement> listWebElement, String text) {
        for (int i = 0; i < listWebElement.size(); i++) {
            if (listWebElement.get(i).getText().equals(text)) {
                clickOnElement(listWebElement.get(i));
                break;
            }
        }

    }

    public void enterText(WebElement element, String text) {
        waitVisibility(element);
        element.clear(); // sterge text din field
        element.sendKeys(text);
    }

    public void enterTextWithEnter(WebElement element, String text) {
        waitVisibility(element);
        element.clear(); // sterge text din field
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }

    public void waitOnCartElement() {
        wait.until(driver -> {
            WebElement itemAmount = driver.findElement(By.xpath("//span[@class='cart-value']"));

            try {
                return Integer.parseInt(itemAmount.getText().trim()) > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        });
    }

    public void fillElement(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    public void waitForElementVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
