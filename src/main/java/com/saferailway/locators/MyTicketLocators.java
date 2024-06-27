package com.saferailway.locators;

import org.openqa.selenium.By;

public class MyTicketLocators {

    public static final By ERROR_MESSAGE = By.cssSelector(".error.message");
    public static final By DEPART_STATION_COLUMN = By.cssSelector(".MyTable>tbody>tr>td:nth-child(2)");
    public static final By TABLES_ROWS = By.cssSelector(".MyTable>tbody>:not(.TableSmallHeader)");
    public static final By CANCEL_BUTTON = By.cssSelector(".MyTable>tbody>tr:nth-child(2)>td>input[value='Cancel']");
}
