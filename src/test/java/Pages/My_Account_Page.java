package Pages;

import HelperMethods.ElementMethods;
import ObjectData.My_Account_Object;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

@Listeners(ChainTestListener.class)
public class My_Account_Page {
    WebDriver driver;
    ElementMethods elementMethods;

    public My_Account_Page(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(id = "username")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(xpath = "//button[@name='login']")
    WebElement loginButton;

    // Constructors
    public void addEntry(My_Account_Object data) {
        enterUsenameField(data.getUsename());
        enterPasswordField(data.getPassword());
    }

    public void enterUsenameField(String username) {
        elementMethods.enterText(usernameField, username);
    }

    public void enterPasswordField(String password) {
        elementMethods.enterText(passwordField, password);
    }

    public void clickOnLoginButton() {
        loginButton.click();
    }

}
