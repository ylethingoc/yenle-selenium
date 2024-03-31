package pages.saferailway;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getWelcomeMessage() {
        return driver.findElement(By.cssSelector("h1")).getText().trim();
    }

    public TimeTablePage clickOnTimetable(WebDriver driver) {
        driver.findElement(By.xpath("//span[text()='Timetable']")).click();
        return new TimeTablePage(driver);
    }

}
