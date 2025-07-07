package tests;

import base.BaseTest;
import pages.*;
import utils.CSVReaderUtil;
import com.aventstack.extentreports.ExtentTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParkingCheckoutTest extends BaseTest {

    @Test(dataProvider = "loginAndSearchData")
    public void completeCheckoutFlow(String email, String password, String searchUrl) throws Exception {
        ExtentTest logger = extent.createTest("Test - " + email);
        test.set(logger);

        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(email, password);

        SearchResultsPage searchPage = new SearchResultsPage(getDriver());
        searchPage.searchAndSelectParking(searchUrl);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.completeCheckout();
    }

    @DataProvider(name = "loginAndSearchData")
    public Object[][] getLoginSearchData() {
        return CSVReaderUtil.combineLoginAndSearch("logincreds.csv", "airportsearchurls.csv");
    }
}
