package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    public static WebDriver getDriver() {
        ChromeOptions options = new ChromeOptions();

//        options.addArguments("--headless=new");
        WebDriver driver;
        try {
            driver = new ChromeDriver(options);
        } catch (Exception e) {
            throw new RuntimeException("ChromeDriver initialization failed: " + e.getMessage(), e);
        }
        return driver;
    }
}
