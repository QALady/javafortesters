package com.javafortesters.tests;

import com.javafortesters.utils.SortedListOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by QA_Lady on 1/18/2015.
 */
public class DeleteContactTest extends TestBase {

    private static Logger LOG = LoggerFactory.getLogger(DeleteContactTest.class.getName());

    @Test(dataProvider = "Contact Index Provider")
    public void deleteContact(int index) {
        LOG.info("DeleteContactTest.deleteContact() start");

        //Save original state
        SortedListOf<ContactsData> originalList = appManager.getContactsHelper().getContacts();
        
        appManager.getContactsHelper().removeContactHighlevel(index);

        //Save actual state
        SortedListOf<ContactsData> actualList = appManager.getContactsHelper().getContacts();

        //Compare states

//        originalList.remove(index);
////        //Sort items in the list to appear in the same order as gui shows them
////        Collections.sort(originalList);
//        Assert.assertEquals(actualList, originalList);

        //junit with hamcrest and without() from SortedListOf wrapper
        assertThat(actualList, equalTo(originalList.without(index)));
        LOG.info("DeleteContactTest.deleteContact() end");



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
