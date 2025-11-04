package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class NegativeTests extends BaseTest {

    @Test(description = "Test invalid year input")
    public void testInvalidYear() {

        HomePage home = new HomePage(driver);
        int initialMovieCount = home.getMovieCount();
        home.enterYear("9999");
        int movieCount = home.getMovieCount();
        logger.info("Movie count for invalid year: " + movieCount);
        Assert.assertTrue(movieCount == initialMovieCount, "No results should be displayed for invalid year");
    }

    @Test(description = "Test invalid URL slug")
    public void testInvalidSlug() {
        try {
            HomePage home = new HomePage(driver);
            driver.get("https://tmdb-discover.surge.sh/xyz");
            Assert.assertFalse(home.pageContentLoads(), "No results should be displayed for invalid slug");
        } catch (NoSuchElementException e) {
            logger.info("No movie cards found as expected for invalid slug.");
        }
    }
}
