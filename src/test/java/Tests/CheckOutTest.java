package Tests;

import Browser.TestBasePage;
import ObjectData.CheckOutObject;
import Pages.CheckOutPage;
import Pages.CommonPage;
import Pages.Home_Page;
import XmlReader.XmlDataLoader;
import org.testng.annotations.Test;

import java.util.Map;

public class CheckOutTest extends TestBasePage {

    private Map<String, CheckOutObject> CheckOutObjectMap;

    @Test
    public void metodaTest() {

        CheckOutObjectMap = XmlDataLoader.loadData("src/test/resources/CheckOutData.xml", CheckOutObject.class);
        CheckOutObject data = CheckOutObjectMap.get("dataSet_1");

        CheckOutPage checkOutPage = new CheckOutPage(getDriver());
        checkOutPage.clickOnMenu();
        checkOutPage.addProductToCart();

        Home_Page homepage = new Home_Page(getDriver());
        homepage.cosCumparaturi();

        CommonPage commonPage = new CommonPage(getDriver());
        commonPage.clickOnShippingMethod("Livrare");

        checkOutPage.selectShippingMethod();
        checkOutPage.proceedToCheckout();
        checkOutPage.completeDeliveryDetails(data);
        checkOutPage.completeState(data);
        checkOutPage.verifyEntry(data);
    }
}