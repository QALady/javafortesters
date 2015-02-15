package com.javafortesters.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

/**
 * Created by QA_Lady on 1/18/2015.
 */
public class EditContactTest extends TestBase {

    @Test(dataProvider = "Contact Name Provider")
    public void editContact(int index, String name) {
        appManager.getNavigationHelper().openMainPage();
        //Save original state
        List<ContactsData> originalList = appManager.getContactsHelper().getContacts();
        appManager.getContactsHelper().editContactName(index, name);
        appManager.getContactsHelper().submitContactUpdate();
        appManager.getContactsHelper().checkSuccessMessage(By.xpath("//div[@class='msgbox']"), "Address book UPDATED\nreturn to home page");
        //return to home page
        appManager.getContactsHelper().goToHomePage();
        //Save actual state
        List<ContactsData> actualList = appManager.getContactsHelper().getContacts();
        //Compare states
        ContactsData nameToCompare = new ContactsData(name);
        originalList.remove(index);
        originalList.add(nameToCompare);
        //Sort items in the list to appear in the same order as gui shows them
        Collections.sort(originalList);
        Assert.assertEquals(actualList, originalList);

    }

    @DataProvider(name = "Contact Name Provider")
    public static Object[][] nameProvider() {

        return new Object[][]{{3, "new name1"}, {5, "new name2"}, {8, "new name3"}};

    }
}
