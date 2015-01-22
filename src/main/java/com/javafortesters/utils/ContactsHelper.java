package com.javafortesters.utils;

import com.javafortesters.tests.ContactsData;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class ContactsHelper extends HelperBase {

    public ContactsHelper(ApplicationManager manager) {
        super(manager);
    }

    public void initContactCreation() {
        //invoke Contact creation dialog
        manager.getControlInputHelper().clickOnElement(By.linkText("add new"));
    }


    public void enterInContactsForm(ContactsData contactsData) {
        manager.getControlInputHelper().enterText(By.name("firstname"), contactsData.getFirstname());
        manager.getControlInputHelper().enterText(By.name("lastname"), contactsData.getLastname());
        manager.getControlInputHelper().enterText(By.name("address"), contactsData.getAddress());
        manager.getControlInputHelper().enterText(By.name("home"), contactsData.getHomeNumber());
        manager.getControlInputHelper().enterText(By.name("mobile"), contactsData.getPhoneNumber());
        manager.getControlInputHelper().enterText(By.name("email"), contactsData.getEmail());
    }

    public void assignContactToGroup(ContactsData contactsData) {
        manager.getControlInputHelper().selectByText(By.name("new_group"), contactsData.getGroupID());

    }

    public void submitContactCreation() {
        manager.getControlInputHelper().clickOnElement(By.name("submit"));
    }

    public void goToHomePage() {
        manager.getControlInputHelper().clickOnElement(By.xpath("//div[@class='msgbox']/i/a"));

    }


    public void addNextContact() {
        manager.getControlInputHelper().clickOnElement(By.linkText("add next"));
    }

    public void checkContactsPageHeader(By locator, String expectedHeader) {
        Assert.assertEquals(manager.driver.findElement(locator).getText(), expectedHeader);
    }

    public void removeContact(int index) {
        manager.getControlInputHelper().clickOnElement(By.xpath("//tr[" + index + "]" + "//input[@name='selected[]']"));
        //invoke Contact Edit
        manager.getControlInputHelper().clickOnElement(By.xpath("//tr[" + index + "]" + "/td[7]/a/img"));
        //click on Delete button
        manager.getControlInputHelper().clickOnElement(By.xpath("//input[@value='Delete']"));
    }

    public void checkSuccessMessage(By locator, String expectedHeader) {
        Assert.assertEquals(manager.driver.findElement(locator).getText(), expectedHeader);
//    Assert.assertEquals(manager.getControlInputHelper().clickOnElement(By.xpath("//input[@value='Delete']")).getText(), "Record has been deleted from the address book.");
    }

    public void editContactName(int index, String name) {
        manager.getControlInputHelper().clickOnElement(By.xpath("//tr[" + index + "]" + "//input[@name='selected[]']"));
        //invoke Contact Edit
        manager.getControlInputHelper().clickOnElement(By.xpath("//tr[" + index + "]" + "/td[7]/a/img"));
        manager.getControlInputHelper().enterText(By.name("firstname"), name);

    }

    public void submitContactUpdate() {
        manager.getControlInputHelper().clickOnElement(By.xpath("//input[@value='Update']"));
    }
}


