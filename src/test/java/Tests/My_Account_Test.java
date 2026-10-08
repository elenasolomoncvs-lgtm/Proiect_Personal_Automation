package Tests;

import Browser.TestBasePage;
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

        authentificationOblectMap = XmlDataLoader.loadData("src/test/resources/My_Account_Data.xml", My_Account_Object.class);
        My_Account_Object data = authentificationOblectMap.get("dataSet_1");

        Home_Page homePage = new Home_Page(getDriver());
        homePage.clickOnMyAccount();

        My_Account_Page authentification = new My_Account_Page(getDriver());
        authentification.addEntry(data);
        authentification.clickOnLoginButton();

    }
}
