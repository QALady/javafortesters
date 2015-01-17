package com.javafortesters.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by QA_Lady on 1/16/2015.
 */
public class AddContactsTest extends TestBase {

    @Test(dataProvider = "ContactsInfo")
    public void addContactsTest(ContactsData contactsData) throws Exception {
        openMainPage();
        //invoke Contact creation dialog
        driver.findElement(By.linkText("add new")).click();
        //Fill Contact's form
        enterText(By.name("firstname"), contactsData.getFirstname());
        enterText(By.name("lastname"), contactsData.getLastname());
        enterText(By.name("address"), contactsData.getAddress());
        enterText(By.name("home"), contactsData.getHomeNumber());
        enterText(By.name("mobile"), contactsData.getPhoneNumber());
        enterText(By.name("email"), contactsData.getEmail());
        enterBirthdayDate(contactsData.getDay(), contactsData.getMonth(), contactsData.getYear());
        new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactsData.getGroupID());
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("add next")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("h1")).getText(), "Edit / add address book entry");
        //TODO: add check that it should be impossible to create an empty contact. Current implementation has this issue.
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("home page")).click();
    }


    @Test
    public void homePageTest() {
        //TODO: create home page represantation test assert that correct field value is displayed under correct header.Currently there is a mess. For example column header "Firstname" has lastname value in its column
    }

    @DataProvider(name = "ContactsInfo")
    public static Object[][] text() {

        return new Object[][]{{new ContactsData("firstname1", "lastname1", "address1", "1", "7324678090", "email1@ya.ru", "Group 1", "5", "December", "1975")}, {new ContactsData("firstname2", "lastname2", "address2", "2", "4446632467", "email2@ya.ru", "Group 2", "31", "August", "2005")}, {new ContactsData("firstname3", "lastname3", "address3", "3", "4446632467", "email3@ya.ru", "Group 3", "18", "February", "1967")}};

    }
}
