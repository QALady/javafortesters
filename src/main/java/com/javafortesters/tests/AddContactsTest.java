package com.javafortesters.tests;

import com.javafortesters.utils.ContactsHelper;
import com.javafortesters.utils.SortedListOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.javafortesters.tests.ContactDataGenerator.loadContactsFromXmlFile;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by QA_Lady on 1/16/2015.
 */
public class AddContactsTest extends TestBase {

    private static Logger LOG = LoggerFactory.getLogger(AddContactsTest.class.getName());

    @Test(dataProvider = "Contacts From File")
    public void addContactsTest(ContactsData contactsData) throws Exception {
        LOG.info("AddContactsTest.addContactsTest() start");
        ContactsHelper contactsHelper = appManager.getContactsHelper();
        //Save original state
        SortedListOf<ContactsData> originalList = contactsHelper.getContacts();

        contactsHelper.createContact(contactsData, true);

        //Save actual state
        SortedListOf<ContactsData> actualList = contactsHelper.getContacts();

        //Compare states

//        //add recently added contact to the original list to create the expected result for comparison
//        originalList.add(contactsData);
//        //Sort items in the list to appear in the same order as gui shows them
//        Collections.sort(originalList);
//        Assert.assertEquals(actualList, originalList);

        //junit with hamcrest and withAdded() from SortedListOf wrapper
        assertThat(actualList, equalTo(originalList.withAdded(contactsData)));
        LOG.info("AddContactsTest.addContactsTest() end");
    }

//    @Test(dataProvider = "ContactsInfo")
//    public void checkEmptyContactTest(ContactsData contactsData) throws Exception {
//        ContactsHelper contactsHelper = appManager.getContactsHelper();
//        //Save original state
//        List<ContactsData> originalList = contactsHelper.getContacts();
//
//        contactsHelper.createContact(contactsData, false)
//                .addNextContact()
//                .checkContactsPageHeader(By.cssSelector("h1"), "Edit / add address book entry")
////        TODO: add check that it should be impossible to create an empty contact. Current implementation has this issue.
//                .submitContactCreation()
//                .goToHomePage();
//        //Save actual state
//        List<ContactsData> actualList = contactsHelper.getContacts();
//        //Compare states
//        //add recently added contact to the original list to create the expected result for comparison
//        originalList.add(contactsData);
//        originalList.add(new ContactsData());
//        //Sort items in the list to appear in the same order as gui shows them
//        Collections.sort(originalList);
//        Assert.assertEquals(actualList, originalList);
//    }

    //    @Test
    public void homePageTest() {
        //TODO: create home page represantation test assert that correct field value is displayed under correct header.Currently there is a mess. For example column header "Firstname" has lastname value in its column
    }

    @DataProvider(name = "ContactsInfo")
    public static Object[][] text() {

        return new Object[][]{{new ContactsData("firstname1", "lastname1", "address1", "1", "7324678090", "email1@ya.ru", "Group 1", "5", "December", "1975")}, {new ContactsData("firstname2", "lastname2", "address2", "2", "4446632467", "email2@ya.ru", "Group 2", "31", "August", "2005")}, {new ContactsData("firstname3", "lastname3", "address3", "3", "4446632467", "email3@ya.ru", "Group 3", "18", "February", "1967")}};

    }

    @DataProvider(name = "Contacts From File")
    public Iterator<Object[]> contactsFromFile() throws IOException {
//        return wrapContactsForDataProvider(loadContactsFromCsvFile(new File("contacts.csv"))).iterator();
        return wrapContactsForDataProvider(loadContactsFromXmlFile(new File("contacts.xml"))).iterator();

    }

    protected List<Object[]> wrapContactsForDataProvider(List<ContactsData> contacts) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (ContactsData contact : contacts) {
            list.add(new Object[]{contact});
        }
        return list;

    }
}
