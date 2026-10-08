package Tests;

import Browser.TestBasePage;
import Pages.Gravura_Laser_Page;
import Pages.Home_Page;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import HelperMethods.ElementMethods;

public class Gravura_Laser_Test extends TestBasePage {

    @Test
    public void testAddLaserLensToCart() throws InterruptedException {
        getDriver().manage().window().maximize();

        // Navigation from Home Page
        Home_Page homepage = new Home_Page(getDriver());
        homepage.clickOnGravuraLaser();

        // Interactions specific to Laser Engraving Page
        Gravura_Laser_Page gravuraLaserPage = new Gravura_Laser_Page (getDriver());

        gravuraLaserPage.selectThirdCategory();
        gravuraLaserPage.scrollDown(800);

        gravuraLaserPage.selectLensProduct();
        gravuraLaserPage.scrollDown(200);
        gravuraLaserPage.selectFocusByIndex(3);
        Thread.sleep(3000);
        gravuraLaserPage.addToCart();

        ElementMethods elementMethods = new ElementMethods(getDriver());
        gravuraLaserPage.waitOnCartElement(elementMethods);

        // Click Ok on Alert
        gravuraLaserPage.acceptAlertIfPresent();

        gravuraLaserPage.scrollToTop();

        // Assert
//        Assert.assertTrue(gravuraLaserPage.isViewCartButtonDisplayed(), "Vezi coșul");
//        Assert.assertTrue(gravuraLaserPage.isViewCartButtonDisplayed(), "View Cart button was not displayed.");
    }
}


