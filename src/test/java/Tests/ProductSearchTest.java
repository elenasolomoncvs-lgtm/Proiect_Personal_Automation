package Tests;

import Browser.TestBasePage;
import Pages.Home_Page;
import Pages.SearchResults_Page;
import Logger.LoggerUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductSearchTest extends TestBasePage {

    @Test
    public void testProductSearch() {
        String searchTerm = "tablou";

        Home_Page homePage = new Home_Page(getDriver());
        LoggerUtility.infoTestCase("Searching for product: " + searchTerm);

        SearchResults_Page searchResultsPage = homePage.searchProduct(searchTerm);

        Assert.assertTrue(searchResultsPage.hasResults(), "Expected search results to be displayed!");
        LoggerUtility.infoTestCase("Found " + searchResultsPage.getResultsCount() + " results for term: " + searchTerm);
    }
}
