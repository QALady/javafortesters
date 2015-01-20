/**
 * Created by QA_Lady on 1/16/2015.
 */

package com.javafortesters.tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddGroupTest extends TestBase {

    @Test(dataProvider = "Group Form Provider")
    public void testGroupCreation(GroupData groupData) throws Exception {
        //open main page
        appManager.getNavigationHelper().openMainPage();
        //go to groups page
        appManager.getGroupHelper().goToGroupsPage(true);
        //open group creation dialog
        appManager.getGroupHelper().initGroupCreation();
        appManager.getGroupHelper().fillInForm(groupData);
        //submit group creation
        appManager.getGroupHelper().submitGroupCreation();
        //return to groups page
        appManager.getGroupHelper().goToGroupsPage(false);
    }


    @Test
    public void testEmptyGroupCreation() throws Exception {
        //open main page
        appManager.getNavigationHelper().openMainPage();
        //go to groups page
        appManager.getControlInputHelper().clickOnElement(By.linkText("groups"));
        //open group creation dialog
        appManager.getControlInputHelper().clickOnElement(By.name("new"));
        appManager.getGroupHelper().fillInForm(null);
        //submit group creation
        appManager.getControlInputHelper().clickOnElement(By.name("submit"));
        //return to groups page
        appManager.getControlInputHelper().clickOnElement(By.linkText("group page"));
    }


    @DataProvider(name = "Group Form Provider")
    public static Object[][] text() {

        return new Object[][]{{new GroupData("Group 1", "header 1", "footer 1")}, {new GroupData("Group 2", "header 2", "footer 2")}, {new GroupData("Group 3", "header 3", "footer 3")}};

    }


}
