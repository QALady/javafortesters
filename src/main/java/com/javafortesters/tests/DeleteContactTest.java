package com.javafortesters.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by QA_Lady on 1/18/2015.
 */
public class DeleteContactTest extends TestBase {

    @Test(dataProvider = "Contact Index Provider")
    public void deleteContact(int index) {
        appManager.getNavigationHelper().openMainPage();
        //Save original state
        List<ContactsData> originalList = appManager.getContactsHelper().getContacts();
        appManager.getContactsHelper().removeContact(index);
        appManager.getContactsHelper().checkSuccessMessage(By.xpath("//div[@class='msgbox']"), "Record has been deleted from the address book.\n" +
                "return to home page");
        //return to home page
        appManager.getContactsHelper().goToHomePage();
        //Save actual state
        List<ContactsData> actualList = appManager.getContactsHelper().getContacts();
        //Compare states
        originalList.remove(index);
        //Sort items in the list to appear in the same order as gui shows them
        Collections.sort(originalList);
        Assert.assertEquals(actualList, originalList);

    }


    @DataProvider(name = "Contact Index Provider")
    public static Object[][] indexProvider() {
        Random rnd = new Random();
        appManager.getNavigationHelper().openMainPage();
        //Save original state
        List<ContactsData> contactsList = appManager.getContactsHelper().getContacts();
        int n = contactsList.size() - 1;
        return new Object[][]{{rnd.nextInt(n)}, {rnd.nextInt(n)}, {rnd.nextInt(n)}};

    }


}
