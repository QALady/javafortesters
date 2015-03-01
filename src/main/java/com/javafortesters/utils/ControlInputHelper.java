package com.javafortesters.utils;

import com.javafortesters.tests.ContactsData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class ControlInputHelper extends HelperBase {

    public ControlInputHelper(ApplicationManager manager) {
        super(manager);
    }

    public ControlInputHelper enterText(By locator, String text) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
        return this;
    }

    public ControlInputHelper enterBirthdayDate(ContactsData contactsData) {
        enterBirthdayDate(contactsData.getDay(), contactsData.getMonth(), contactsData.getYear());
        return this;
    }

    public ControlInputHelper enterBirthdayDate(String day, String month, String year) {
        selectByText(By.name("bday"), day);
        selectByText(By.name("bmonth"), month);
        enterText(By.name("byear"), year);
        return this;
    }

    public WebElement clickOnElement(By locator) {
        if (manager.getElementHelper().isElementPresent(locator)) {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            WebElement elem = wait.until(ExpectedConditions.elementToBeClickable(locator));
            elem.click();
            return elem;
        }
        return null;
    }


    public ControlInputHelper selectByText(By locator, String text) {
        new Select(driver.findElement(locator)).selectByVisibleText(text);
        return this;
    }

}
