package Tests;

import Browser.TestBasePage;
import Logger.LoggerUtility;
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

        LoggerUtility.infoTestCase("Loading checkout test data");

        CheckOutObjectMap = XmlDataLoader.loadData("src/test/resources/CheckOutData.xml", CheckOutObject.class);

        CheckOutObject data = CheckOutObjectMap.get("dataSet_1");

        LoggerUtility.infoTestCase("Test data loaded successfully");

        CheckOutPage checkOutPage = new CheckOutPage(getDriver());

        LoggerUtility.infoTestCase("Opening Consumabile Laser menu");
        checkOutPage.clickOnMenu();

        LoggerUtility.infoTestCase("Adding product to cart");
        checkOutPage.addProductToCart();

        Home_Page homepage = new Home_Page(getDriver());

        LoggerUtility.infoTestCase("Navigating to shopping cart");
        homepage.cosCumparaturi();

        CommonPage commonPage = new CommonPage(getDriver());

        LoggerUtility.infoTestCase("Selecting shipping method: Livrare");
        commonPage.clickOnShippingMethod("Livrare");

        LoggerUtility.infoTestCase("Confirming shipping option");
        checkOutPage.selectShippingMethod();

        LoggerUtility.infoTestCase("Proceeding to checkout");
        checkOutPage.proceedToCheckout();

        LoggerUtility.infoTestCase("Completing delivery details");
        checkOutPage.completeDeliveryDetails(data);

        LoggerUtility.infoTestCase("Completing county/state information");
        checkOutPage.completeState(data);

        LoggerUtility.infoTestCase("Verifying entered checkout data");
        checkOutPage.verifyEntry(data);

        LoggerUtility.infoTestCase("Checkout test completed successfully");
    }
}