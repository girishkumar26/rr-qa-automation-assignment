package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class PaginationPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By movieCards = By.xpath("//img[@alt='Movie Posters']");
    private By nextButton = By.xpath("//div[@id='react-paginate']//li[@class='next']/a");
    private By prevButton = By.xpath("//div[@id='react-paginate']//li[@class='prev']/a");
    private By pagination = By.xpath("//*[@id=\"react-paginate\"]/ul/li[9]/a");


    public PaginationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getCurrentPage() {
        WebElement currentPage = driver.findElement(By.xpath("//div[@id='react-paginate']//li[@class='selected']/a"));
        return currentPage.getText();
    }

    public void goToNextPage() {
        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        next.click();
    }

    public void waitForResults() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(pagination));
//            wait.until(ExpectedConditions.visibilityOfElementLocated(prevButton));
        }
        catch (Exception e){
            throw new WebDriverException(e.getMessage());
        }
    }


    public void goToPreviousPage() {
        WebElement prev = wait.until(ExpectedConditions.elementToBeClickable(prevButton));
        prev.click();
    }
}
