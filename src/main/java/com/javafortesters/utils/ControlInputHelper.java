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

    public void enterBirthdayDate(ContactsData contactsData) {
        enterBirthdayDate(contactsData.getDay(), contactsData.getMonth(), contactsData.getYear());
    }

    public void enterBirthdayDate(String day, String month, String year) {
        selectByText(By.name("bday"), day);
        selectByText(By.name("bmonth"), month);
        enterText(By.name("byear"), year);
    }

    public void clickOnElement(By locator) {
        if (manager.getElementHelper().isElementPresent(locator)) {
            driver.findElement(locator).click();
        }
    }


    public void selectByText(By locator, String text) {
        new Select(driver.findElement(locator)).selectByVisibleText(text);
    }

}
