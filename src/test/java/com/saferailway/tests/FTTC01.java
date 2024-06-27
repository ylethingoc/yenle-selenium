package com.saferailway.tests;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.saferailway.BookTicketPage;
import pages.saferailway.HomePage;
import pages.saferailway.LoginPage;
import pages.saferailway.MyTicketPage;

import java.util.List;
import java.util.Map;

public class FTTC01 extends TestBase {

    LoginPage loginPage;
    BookTicketPage bookTicketPage;
    MyTicketPage myTicketPage;


    @Test(dataProvider = "dataProvider", description = "User can filter 'Manager ticket' table with Depart Station")
    public void FTTC01(Map<String, String> data) {
        logStep(Status.INFO,"Step #1: Login with a valid account");
        WebDriver driver = getDriver();
        driver.get(globalVars.getBaseUrl());
        HomePage homePage = new HomePage(driver);
        loginPage = homePage.clickOnLoginLabel(driver);
        homePage = loginPage.loginWithAValidAccount(data.get("Email"), data.get("Password"));

        logStep(Status.INFO, "Step #2: Book more than 6 tickets with different Depart Stations");
        bookTicketPage = homePage.clickBookTicketLabel(driver);
        bookTicketPage.bookTickets(Integer.parseInt(data.get("Amount")));

        logStep(Status.INFO, "Step #3: Click on 'My ticket' tab");
        myTicketPage = bookTicketPage.clickOnMyTicketLabel(driver);

        logStep(Status.INFO, "Step #4: Select one of booked Depart Station in 'Depart Station' dropdown list");
        myTicketPage.pickAnOptionToFilter("Depart Station", data.get("DepartStation"));

        logStep(Status.INFO, "Step #5: Click 'Apply filter' button");
        myTicketPage.applyFilter();
        List<String> textList = myTicketPage.getFilteredResult();
        for (String text : textList) {
            Assert.assertEquals(data.get("DepartStation"), text);
        }
    }
}
