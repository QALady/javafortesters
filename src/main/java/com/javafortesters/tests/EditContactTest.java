package com.javafortesters.tests;

import com.javafortesters.utils.SortedListOf;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

/**
 * Created by QA_Lady on 1/18/2015.
 */
public class EditContactTest extends TestBase {

    @Test(dataProvider = "Contact Name Provider")
    public void editContact(int index, String name) {
        //Save original state
        SortedListOf<ContactsData> originalList = appManager.getContactsHelper().getContacts();

        appManager.getContactsHelper().editContact(index, name);

        //Save actual state
        SortedListOf<ContactsData> actualList = appManager.getContactsHelper().getContacts();
        //Compare states
        originalList.get(index).setFirstname(name);
//        ContactsData nameToCompare = new ContactsData(name);
//        originalList.remove(index);
//        originalList.add(nameToCompare);
        Assert.assertEquals(actualList, originalList);

    }

    @DataProvider(name = "Contact Name Provider")
    public static Object[][] nameProvider() {
        Random rnd = new Random();
        appManager.getNavigationHelper().openMainPage();
        //Save original state
        List<ContactsData> contactsList = appManager.getContactsHelper().getContacts();
        int n = contactsList.size() - 1;

        return new Object[][]{{rnd.nextInt(n), generateRandomString("newName")}, {rnd.nextInt(n), generateRandomString("newName")}, {rnd.nextInt(n), generateRandomString("newName")}};

    }
}
