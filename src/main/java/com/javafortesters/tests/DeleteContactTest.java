package com.javafortesters.tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by QA_Lady on 1/18/2015.
 */
public class DeleteContactTest extends TestBase {

    @Test(dataProvider = "Contact Index Provider")
    public void deleteContact(int index) {
        appManager.getNavigationHelper().openMainPage();
        appManager.getContactsHelper().removeContact(index);
        appManager.getContactsHelper().checkSuccessMessage(By.xpath("//div[@class='msgbox']"), "Record has been deleted from the address book.\n" +
                "return to home page");
        //return to home page
        appManager.getContactsHelper().goToHomePage();

    }


    @DataProvider(name = "Contact Index Provider")
    public static Object[][] indexProvider() {

        return new Object[][]{{2}, {3}, {5}};

    }


}
