package Tests;

import Browser.TestBasePage;
import HelperMethods.ElementMethods;
import Logger.LoggerUtility;
import Pages.Gravura_Laser_Page;
import Pages.Home_Page;
import org.testng.annotations.Test;

public class Gravura_Laser_Test extends TestBasePage {

    @Test
    public void testAddLaserLensToCart() throws InterruptedException {

        LoggerUtility.infoTestCase("Starting Laser Lens Add To Cart test");

        getDriver().manage().window().maximize();
        LoggerUtility.infoTestCase("Browser window maximized");

        Home_Page homepage = new Home_Page(getDriver());

        LoggerUtility.infoTestCase("Navigating to Gravura Laser category");
        homepage.clickOnGravuraLaser();

        Gravura_Laser_Page gravuraLaserPage =
                new Gravura_Laser_Page(getDriver());

        LoggerUtility.infoTestCase("Selecting third Gravura Laser category");
        gravuraLaserPage.selectThirdCategory();

        LoggerUtility.infoTestCase("Scrolling down to products section");
        gravuraLaserPage.scrollDown(800);

        LoggerUtility.infoTestCase("Selecting laser lens product");
        gravuraLaserPage.selectLensProduct();

        LoggerUtility.infoTestCase("Scrolling to product options");
        gravuraLaserPage.scrollDown(200);

        LoggerUtility.infoTestCase("Selecting focus option with index 3");
        gravuraLaserPage.selectFocusByIndex(3);

        Thread.sleep(3000);

        LoggerUtility.infoTestCase("Adding product to cart");
        gravuraLaserPage.addToCart();

        ElementMethods elementMethods = new ElementMethods(getDriver());

        LoggerUtility.infoTestCase("Waiting for cart value to update");
        gravuraLaserPage.waitOnCartElement(elementMethods);

        LoggerUtility.infoTestCase("Accepting alert if present");
        gravuraLaserPage.acceptAlertIfPresent();

        LoggerUtility.infoTestCase("Scrolling back to top");
        gravuraLaserPage.scrollToTop();

        LoggerUtility.infoTestCase("Laser Lens Add To Cart test completed successfully");
    }
}