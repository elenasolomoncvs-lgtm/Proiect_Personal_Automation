package Tests;

import Browser.TestBasePage;
import Logger.LoggerUtility;
import ObjectData.My_Account_Object;
import Pages.My_Account_Page;
import Pages.Home_Page;
import XmlReader.XmlDataLoader;
import org.testng.annotations.Test;

import java.util.Map;

public class My_Account_Test extends TestBasePage {

    private Map<String, My_Account_Object> authentificationOblectMap;

    @Test
    public void metodaTest() throws InterruptedException {

        LoggerUtility.infoTestCase("Loading My Account test data");

        authentificationOblectMap = XmlDataLoader.loadData("src/test/resources/My_Account_Data.xml", My_Account_Object.class);

        My_Account_Object data = authentificationOblectMap.get("dataSet_1");

        LoggerUtility.infoTestCase("Test data loaded successfully");

        Home_Page homePage = new Home_Page(getDriver());

        LoggerUtility.infoTestCase("Navigating to My Account page");
        homePage.clickOnMyAccount();

        My_Account_Page authentification = new My_Account_Page(getDriver());

        LoggerUtility.infoTestCase("Entering login credentials");
        authentification.addEntry(data);

        LoggerUtility.infoTestCase("Clicking Login button");
        authentification.clickOnLoginButton();

        LoggerUtility.infoTestCase("Login test completed successfully");
    }
}