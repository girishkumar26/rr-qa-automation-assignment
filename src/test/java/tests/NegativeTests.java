package tests;

import base.BaseTest;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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

    @Test(description = "Verify invalid rating input handling")
    public void testInvalidRatingSelection() {
        test = extent.createTest("Negative Test - Invalid Rating Selection");
        HomePage home = new HomePage(driver);
        SoftAssert softAssert = new SoftAssert();

        try {
            logger.info("Attempting to select an invalid rating (6 stars)");
            home.selectStarRating("-1");  // <-- triggers NoSuchElementException
            softAssert.fail("Invalid rating (-1 stars) should not be selectable, but no exception was thrown.");
        } catch (NoSuchElementException e) {
            logger.info("Caught expected exception for invalid rating: " + e.getMessage());
            test.pass(" Application handled invalid rating");
            softAssert.assertFalse(home.isRatingApplied(-1), "Expected behavior: rating beyond 1-5 not present.");
        } catch (Exception e) {
            logger.error("Unexpected error: " + e.getMessage());
            test.fail("Unexpected exception during invalid rating test: " + e.getMessage());
            softAssert.fail("Unexpected exception occurred.");
        } finally {
            softAssert.assertAll();
        }
    }
}
