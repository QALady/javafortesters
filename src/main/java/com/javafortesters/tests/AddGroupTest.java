/**
 * Created by QA_Lady on 1/16/2015.
 */

package com.javafortesters.tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddGroupTest extends TestBase {

    @Test(dataProvider = "Group Form")
    public void testGroupCreation(GroupData groupData) throws Exception {
        //open main page
        appManager.getNavigationHelper().openMainPage();
        //go to groups page
        appManager.getControlInputHelper().clickOnElement(By.linkText("groups"));
        //open group creation dialog
        appManager.getControlInputHelper().clickOnElement(By.name("new"));
        //complete group creation form
        appManager.getControlInputHelper().enterText(By.name("group_name"), groupData.getGroupName());
        appManager.getControlInputHelper().enterText(By.name("group_header"), groupData.getHeader());
        appManager.getControlInputHelper().enterText(By.name("group_footer"), groupData.getFooter());
        //submit group creation
        appManager.getControlInputHelper().clickOnElement(By.name("submit"));
        //return to groups page
        appManager.getControlInputHelper().clickOnElement(By.linkText("group page"));
    }

    @Test
    public void testEmptyGroupCreation() throws Exception {
        //open main page
        appManager.getNavigationHelper().openMainPage();
        //go to groups page
        appManager.getControlInputHelper().clickOnElement(By.linkText("groups"));
        //open group creation dialog
        appManager.getControlInputHelper().clickOnElement(By.name("new"));
        //complete group creation form
        appManager.getControlInputHelper().enterText(By.name("group_name"), "");
        appManager.getControlInputHelper().enterText(By.name("group_header"), "");
        appManager.getControlInputHelper().enterText(By.name("group_footer"), "");
        //submit group creation
        appManager.getControlInputHelper().clickOnElement(By.name("submit"));
        //return to groups page
        appManager.getControlInputHelper().clickOnElement(By.linkText("group page"));
    }


    @DataProvider(name = "Group Form")
    public static Object[][] text() {

        return new Object[][]{{new GroupData("Group 1", "header 1", "footer 1")}, {new GroupData("Group 2", "header 2", "footer 2")}, {new GroupData("Group 3", "header 3", "footer 3")}};

    }


}
