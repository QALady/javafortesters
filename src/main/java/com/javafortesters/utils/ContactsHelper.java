package com.javafortesters.utils;

import com.javafortesters.tests.ContactsData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class ContactsHelper extends HelperBase {

    private List<ContactsData> contacts;

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
        manager.getControlInputHelper().clickOnElement(By.xpath("//div[@class='msgbox']/i/a[text()='home page']"));

    }


    public void addNextContact() {
        manager.getControlInputHelper().clickOnElement(By.linkText("add next"));
    }

    public void checkContactsPageHeader(By locator, String expectedHeader) {
        Assert.assertEquals(manager.driver.findElement(locator).getText(), expectedHeader);
    }

    public List<ContactsData> getContacts() {
        List<ContactsData> contacts = new ArrayList<ContactsData>();
        List<WebElement> rows = driver.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String email = cells.get(3).getText();
            String homeNumber = cells.get(4).getText();
            ContactsData contact = new ContactsData(firstName, lastName, null, homeNumber, null, email, null, null, null, null);
            contacts.add(contact);
        }
        return contacts;
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


