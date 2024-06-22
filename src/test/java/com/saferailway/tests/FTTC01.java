package com.saferailway.tests;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.saferailway.BookTicketPage;
import pages.saferailway.HomePage;
import pages.saferailway.LoginPage;
import pages.saferailway.MyTicketPage;

import java.util.List;
public class FTTC01 extends TestBase {

    LoginPage loginPage;
    BookTicketPage bookTicketPage;
    MyTicketPage myTicketPage;


    @Override
    protected String getTestClassName() {
        return this.getClass().getSimpleName();
    }

    @Test(description = "User can filter 'Manager ticket' table with Depart Station")
    public void FTTC01() {
        logStep(Status.INFO,"Step #1: Login with a valid account");
        HomePage homePage = new HomePage(driver);
        loginPage = homePage.clickOnLoginLabel(driver);
        homePage = loginPage.loginWithValidAccount(TestBase.getEmail(), TestBase.getPassword());

        logStep(Status.INFO,"Step #2: Book more than 6 tickets with different Depart Stations");
        bookTicketPage = homePage.clickBookTicketLabel(driver);
        bookTicketPage.bookTickets(Integer.parseInt(data.get("Amount")));

        logStep(Status.INFO,"Step #3: Click on 'My ticket' tab");
        myTicketPage = bookTicketPage.clickOnMyTicketLabel(driver);

        logStep(Status.INFO,"Step #4: Select one of booked Depart Station in 'Depart Station' dropdown list");
        myTicketPage.pickAnOptionToFilter("Depart Station", data.get("DepartStation"));

        logStep(Status.INFO,"Step #5: Click 'Apply filter' button");
        myTicketPage.applyFilter();
        List<String> textList = myTicketPage.getFilteredResult();
        for (String text : textList) {
            Assert.assertEquals(data.get("DepartStation"), text);
        }
    }
}