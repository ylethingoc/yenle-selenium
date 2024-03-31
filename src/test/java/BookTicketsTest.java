import helpers.WebDriverHelper;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.saferailway.BookTicketPage;
import pages.saferailway.HomePage;
import pages.saferailway.LoginPage;
import pages.saferailway.MainPage;
import pages.saferailway.TicketPricePage;
import pages.saferailway.TimeTablePage;

import static helpers.Constants.PASSWORD;
import static helpers.Constants.USER_NAME;

public class BookTicketsTest {
    WebDriver driver;
    MainPage mainPage;

    @BeforeMethod
    public void setup() {
        driver = WebDriverHelper.getWebDriver();
        mainPage = new MainPage(driver);
    }

    @Test(description = "Validate that user is able to book the tickets successfully")
    public void bookTwoTickets() {
        // navigate to http://saferailway.somee.com/, then click on the Login label
        driver.get("http://saferailway.somee.com/");
        LoginPage loginPage = mainPage.clickOnLoginLabel(driver);

        // login into home page
        HomePage homePage = loginPage.loginWithAccount(USER_NAME, PASSWORD);
        Assert.assertEquals("Welcome to Safe Railway", homePage.getWelcomeMessage());

        // click on Timetable, then check price for a trip
        TimeTablePage timeTablePage = homePage.clickOnTimetable(driver);
        TicketPricePage ticketPricePage = timeTablePage.checkPriceForATrip("Sài Gòn", "Đà Nẵng");

        // select Soft seat, then book a ticket
        BookTicketPage bookTicketPage = ticketPricePage.selectASeatType("Soft seat");
        bookTicketPage.bookTickets(7, 2);
        Assert.assertEquals("Ticket booked successfully!", bookTicketPage.getSuccessMessage());
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
