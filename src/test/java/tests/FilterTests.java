package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class FilterTests extends BaseTest {

    @Test(description = "Test filtering by category, type, genre, year and rating")
    public void testFilters() {
        test = extent.createTest("Filter Test");
        HomePage home = new HomePage(driver);

        int initialCount = home.getMovieCount();

        home.selectCategory("Popular");
        home.selectType("TV Shows");
        home.selectGenre("Comedy");
        home.enterYear("2022");
        home.selectStarRating("4");

        int filteredCount = home.getMovieCount();
        logger.info("Initial count: " + initialCount + ", Filtered count: " + filteredCount);
        Assert.assertTrue(filteredCount <= initialCount, "Filtered count should be less or equal to initial count");

        test.pass("Filters applied and verified successfully.");
    }
}
