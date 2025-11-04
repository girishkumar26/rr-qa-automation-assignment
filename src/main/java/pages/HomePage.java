package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By pageNotFound = By.xpath("//title[text()='page not found']");
    private By moviePosters = By.xpath("//img[@alt='Movie Posters']");
    private String categoryXpath = "//li[a[text()='%s']]";
    private By typeDropdown = By.xpath("//div[contains(@class, 'css-1uccc91-singleValue')]");
    private By genreDropdown = By.xpath("(//div[@class=' css-1hwfws3'])[2]");
    private By genreTextBox = By.xpath("//input[@id='react-select-3-input']");
    private By yearInput = By.xpath("//input[@id='react-select-5-input']");
    private By pagination = By.xpath("//*[@id=\"react-paginate\"]/ul/li[9]/a");
    private By loadingSpinner = By.cssSelector(".loading");
    private String ratingStar = "//ul[@class='rc-rate']//div[@role='radio' and @aria-posinset='%s']";


    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void waitForResults() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loadingSpinner));
            wait.until(ExpectedConditions.visibilityOfElementLocated(pagination));
        } catch (Exception e) {
            throw new WebDriverException(e.getMessage());
        }
    }

    public Boolean pageContentLoads() {
        return driver.findElements(pageNotFound).isEmpty() && driver.findElements(moviePosters).isEmpty();
    }

    public void selectCategory(String categoryName) {
        String categoriesXpath = String.format(categoryXpath, categoryName);
        WebElement categoriesBtn = driver.findElement(By.xpath(categoriesXpath));
        categoriesBtn.click();
        waitForResults();
    }

    public void selectStarRating(String rating) {
        String ratingsXpath = String.format(ratingStar, rating);
        WebElement selectRating = driver.findElement(By.xpath(ratingsXpath));
        selectRating.click();
        waitForResults();
    }


    public void selectType(String type) {
        WebElement dropdown = driver.findElement(typeDropdown);
        dropdown.click();
        WebElement option = dropdown.findElement(By.xpath(String.format("//div[text()='%s']", type)));
        option.click();
//        option.sendKeys(Keys.ENTER);
        waitForResults();
    }

    public void selectGenre(String genre) {
        WebElement dropdown = driver.findElement(genreDropdown);
        dropdown.click();
        WebElement textBox = dropdown.findElement(genreTextBox);
//        textBox.click();
        textBox.sendKeys(genre);
        textBox.sendKeys(Keys.ENTER);
        waitForResults();
    }

    public void enterYear(String year) {
        WebElement input = driver.findElement(yearInput);
        input.clear();
        input.sendKeys(year);
        input.sendKeys(Keys.ENTER);
        waitForResults();
    }

    public int getMovieCount() {
        waitForResults();
//        System.out.println("Number of movies: " + driver.findElement(pagination).getText());
        return Integer.parseInt(driver.findElement(pagination).getText());
    }

    public void scrollToEnd() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.tagName("body"))).sendKeys(Keys.END).perform();
    }

    public boolean isFilterApplied(String filterText) {
        return driver.getPageSource().contains(filterText);
    }

    public boolean isRatingApplied(int stars) {
        try {
            WebElement selectedStar = driver.findElement(
                    By.xpath("//ul[@class='rc-rate']//div[@role='radio' and @aria-posinset='" + stars + "' and @aria-checked='true']")
            );
            return selectedStar.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
