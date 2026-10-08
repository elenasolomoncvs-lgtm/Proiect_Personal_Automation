package Pages;

import HelperMethods.ElementMethods;
import Logger.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CommonPage {
    WebDriver driver;
    ElementMethods elementMethods;

    public CommonPage(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this); //fara aceasta linie nu o sa gaseasca elementele

    }
    @FindBy(id = "shipping_method_0_flat_rate15")
    List<WebElement> elements;

    public void clickOnShippingMethod(String text) {
        elementMethods.selectElementFromListByText(elements, text);
        LoggerUtility.infoTestCase("The user clicked on " + text + " submenu");
    }

}
