package Tests;

import Browser.TestBasePage;
import Pages.Home_Page;
import org.testng.annotations.Test;


public class Home_Page_Test extends TestBasePage {

    @Test
    public void metodaTest() {
        Home_Page homePage = new Home_Page(getDriver());

    }
}
