package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.PaginationPage;

public class PaginationTests extends BaseTest {

    @Test(description = "Verify pagination next and previous buttons")
    public void testPagination() {
        PaginationPage pagination = new PaginationPage(driver);
        HomePage home = new HomePage(driver);
        pagination.waitForResults();
        home.scrollToEnd();
        int firstSelectedPage = Integer.parseInt(pagination.getCurrentPage());
        System.out.println("Current Page: " + firstSelectedPage);

        pagination.goToNextPage();

        pagination.waitForResults();
        int currentSelectedPage = Integer.parseInt(pagination.getCurrentPage());
        System.out.println("New Selected Page: " + currentSelectedPage);
        Assert.assertTrue(firstSelectedPage < currentSelectedPage);
    }
}
