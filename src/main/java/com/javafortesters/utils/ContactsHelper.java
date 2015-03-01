package com.javafortesters.utils;

import com.javafortesters.tests.ContactsData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class ContactsHelper extends HelperBase {

    private SortedListOf<ContactsData> contacts;

    public ContactsHelper(ApplicationManager manager) {
        super(manager);
    }

    private SortedListOf<ContactsData> cachedContacts;

    public SortedListOf<ContactsData> getContacts() {
        if (cachedContacts == null) {
            rebuildCache();
        }
        return new SortedListOf<ContactsData>(cachedContacts);
    }

    private void rebuildCache() {
        cachedContacts = new SortedListOf<ContactsData>();

        SortedListOf<ContactsData> contacts = new SortedListOf<ContactsData>();
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String email = cells.get(3).getText();
            String homeNumber = cells.get(4).getText();
            ContactsData contact = new ContactsData(firstName, lastName, null, homeNumber, null, email, null, null, null, null);
            cachedContacts.add(contact);
        }

    }


    public ContactsHelper createContact(ContactsData contactsData, boolean goToHomePage) {
        //invoke Contact creation dialog
        initContactCreation();
        //Fill Contact's form
        enterInContactsForm(contactsData);
        ControlInputHelper controlInputHelper = manager.getControlInputHelper();
        controlInputHelper.enterBirthdayDate(contactsData.getDay(), contactsData.getMonth(), contactsData.getYear());
        assignContactToGroup(contactsData);
        submitContactCreation();
        if (goToHomePage) {
            //return to home page
            goToHomePage();
            rebuildCache();
        }
        return this;
    }


    public ContactsHelper editContact(int index, String name) {
        editContactName(index, name);
        submitContactUpdate();
        checkSuccessMessage(By.xpath("//div[@class='msgbox']"), "Address book UPDATED\nreturn to home page");
        goToHomePage();
        rebuildCache();
        return this;
    }

    //
    public ContactsHelper removeContactHighlevel(int index) {
        removeContact(index);
        checkSuccessMessage(By.xpath("//div[@class='msgbox']"), "Record has been deleted from the address book.\n" +
                "return to home page");
        goToHomePage();
        rebuildCache();
        return this;
    }

    public ContactsHelper initContactCreation() {
        //invoke Contact creation dialog
        manager.getControlInputHelper().clickOnElement(By.linkText("add new"));
        return this;
    }


    public ContactsHelper enterInContactsForm(ContactsData contactsData) {
        manager.getControlInputHelper().enterText(By.name("firstname"), contactsData.getFirstname());
        manager.getControlInputHelper().enterText(By.name("lastname"), contactsData.getLastname());
        manager.getControlInputHelper().enterText(By.name("address"), contactsData.getAddress());
        manager.getControlInputHelper().enterText(By.name("home"), contactsData.getHomeNumber());
        manager.getControlInputHelper().enterText(By.name("mobile"), contactsData.getPhoneNumber());
        manager.getControlInputHelper().enterText(By.name("email"), contactsData.getEmail());
        return this;
    }

    public ContactsHelper assignContactToGroup(ContactsData contactsData) {
        manager.getControlInputHelper().selectByText(By.name("new_group"), contactsData.getGroupID());
        return this;
    }

    public ContactsHelper submitContactCreation() {
        manager.getControlInputHelper().clickOnElement(By.name("submit"));
        cachedContacts = null;
        return this;
    }

    public ContactsHelper goToHomePage() {
        if (!onHomePage()) {
            manager.getControlInputHelper().clickOnElement(By.xpath("//div[@class='msgbox']/i/a[text()='home page']"));
        }
        return this;
    }

    private boolean onHomePage() {
        if (driver.getCurrentUrl().endsWith("/addressbookv4.1.4/") && driver.findElement(By.xpath("//*[@type='button'][@value='Send e-Mail']")).getAttribute("value").contains("Send e-Mail") && driver.findElements(By.xpath("//*[@type='submit'][@value='Add to']")).size() > 0) {
            return true;
        }
        return false;
    }


    public ContactsHelper addNextContact() {
        manager.getControlInputHelper().clickOnElement(By.linkText("add next"));
        return this;
    }

    public ContactsHelper checkContactsPageHeader(By locator, String expectedHeader) {
        Assert.assertEquals(manager.driver.findElement(locator).getText(), expectedHeader);
        return this;
    }

    public ContactsHelper removeContact(int index) {
        manager.getControlInputHelper().clickOnElement(By.xpath("//tr[" + index + "]" + "//input[@name='selected[]']"));
        //invoke Contact Edit
        manager.getControlInputHelper().clickOnElement(By.xpath("//tr[@name='entry'][" + (index + 1) + "]" + "/td[7]/a/img"));
        //click on Delete button
        manager.getControlInputHelper().clickOnElement(By.xpath("//input[@value='Delete']"));
        cachedContacts = null;
        return this;
    }

    public ContactsHelper checkSuccessMessage(By locator, String expectedHeader) {
        Assert.assertEquals(manager.getControlInputHelper().clickOnElement(locator).getText(), expectedHeader);
        return this;
    }

    public ContactsHelper editContactName(int index, String name) {
        manager.getControlInputHelper().clickOnElement(By.xpath("//tr[@name='entry'][" + (index + 1) + "]" + "//input[@name='selected[]']"));
        //invoke Contact Edit
        manager.getControlInputHelper().clickOnElement(By.xpath("//tr[@name='entry'][" + (index + 1) + "]" + "/td[7]/a/img"));
        manager.getControlInputHelper().enterText(By.name("firstname"), name);
        return this;

    }

    public ContactsHelper submitContactUpdate() {
        manager.getControlInputHelper().clickOnElement(By.xpath("//input[@value='Update']"));
        cachedContacts = null;
        return this;
    }


}


