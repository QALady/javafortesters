package com.javafortesters.utils;

import com.javafortesters.tests.ContactsData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class ControlInputHelper extends HelperBase {

    public ControlInputHelper(ApplicationManager manager) {
        super(manager);
    }

    public void enterText(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    protected void enterBirthdayDate(ContactsData contactsData) {
        enterBirthdayDate(contactsData.getDay(), contactsData.getMonth(), contactsData.getYear());
    }

    public void enterBirthdayDate(String day, String month, String year) {
        new Select(driver.findElement(By.name("bday"))).selectByVisibleText(day);
        new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText(month);
        enterText(By.name("byear"), year);
    }

    public void clickOnElement(By locator) {
        driver.findElement(locator).click();
    }
}
