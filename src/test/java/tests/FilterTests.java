package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;

public class FilterTests extends BaseTest {

    @Test(description = "Test filtering by category, type, genre, year and rating")
    public void testFilters() throws InterruptedException {
        test = extent.createTest("Filter Test");
        HomePage home = new HomePage(driver);

        SoftAssert softAssert = new SoftAssert();

        int initialCount = home.getMovieCount();

        // Apply filters one by one and assert after each
        home.selectCategory("Popular");
        softAssert.assertTrue(home.isFilterApplied("Popular"), "Category filter not applied correctly");

        home.selectType("TV Shows");
        softAssert.assertTrue(home.isFilterApplied("TV Shows"), "Type filter not applied correctly");

        home.selectGenre("Comedy");
        softAssert.assertTrue(home.isFilterApplied("Comedy"), "Genre filter not applied correctly");

        home.enterYear("2022");
        softAssert.assertTrue(home.isFilterApplied("2022"), "Year filter not applied correctly");

        home.selectStarRating("4");
        softAssert.assertTrue(home.isRatingApplied(4), "Rating filter not applied correctly");

        home.enterMovieNameInSearch("WAR");
        softAssert.assertTrue(home.isMoviePostersContains("WAR"), "Movie search filter not applied correctly");

        int filteredCount = home.getMovieCount();
        logger.info("Initial count: " + initialCount + ", Filtered count: " + filteredCount);
        softAssert.assertTrue(filteredCount <= initialCount, "Filtered count should be less or equal to initial count");

        // Collate all soft assertions
        softAssert.assertAll();

        test.pass("Filters applied and verified successfully with soft assertions.");
    }
}
