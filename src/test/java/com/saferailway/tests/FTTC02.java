package com.saferailway.tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.saferailway.BookTicketPage;
import pages.saferailway.HomePage;
import pages.saferailway.LoginPage;
import pages.saferailway.MyTicketPage;

import java.util.Map;

public class FTTC02 extends TestBase {

    LoginPage loginPage;
    BookTicketPage bookTicketPage;
    MyTicketPage myTicketPage;


    @Test(dataProvider = "dataProvider", description = "Error displays when user applies filter with un-existed value of 'Status' in 'Manage ticket' table")
    public void FTTC02(Map<String, String> data) {
        logStep(Status.INFO,"Step #1: Login with a valid account");
        HomePage homePage = new HomePage(driver);
        loginPage = homePage.clickOnLoginLabel(driver);
        homePage = loginPage.loginWithValidAccount(data.get("Email"), data.get("Password"));

        logStep(Status.INFO,"Step #2: Book more than 6 tickets");
        bookTicketPage = homePage.clickBookTicketLabel(driver);
        bookTicketPage.bookTickets(Integer.parseInt(data.get("Amount")));

        logStep(Status.INFO,"Step #3: Click on 'My ticket' tab");
        myTicketPage = bookTicketPage.clickOnMyTicketLabel(driver);

        logStep(Status.INFO,"Step #4: Select 'Paid' for 'Status'");
        myTicketPage.pickAnOptionToFilter("Status", data.get("Status"));

        logStep(Status.INFO,"Step #5: Click 'Apply filter' button");
        myTicketPage.applyFilter();
        Assert.assertEquals(myTicketPage.getErrorMessage(), "Sorry, can't find any results that match your filters.\n" +
                "Please change the filters and try again.");
    }
}

