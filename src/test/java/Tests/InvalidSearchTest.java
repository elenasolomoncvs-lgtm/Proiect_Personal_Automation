package Tests;

import Browser.TestBasePage;
import Logger.LoggerUtility;
import Pages.Home_Page;
import Pages.SearchResults_Page;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InvalidSearchTest extends TestBasePage {

    @Test
    public void testInvalidProductSearch() {

        String invalidProduct = "RANDOM_PRODUCT";

        LoggerUtility.infoTestCase("Searching for invalid product: " + invalidProduct);

        Home_Page homePage = new Home_Page(getDriver());

        SearchResults_Page searchResultsPage = homePage.searchProduct(invalidProduct);

        LoggerUtility.infoTestCase("Verifying No Products Found message");

        Assert.assertTrue(searchResultsPage.isNoResultsMessageDisplayed(), "'No products found' message was not displayed.");

        LoggerUtility.infoTestCase("Displayed message: " + searchResultsPage.getNoResultsMessageText());
    }
}
