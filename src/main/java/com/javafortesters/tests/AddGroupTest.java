/**
 * Created by QA_Lady on 1/16/2015.
 */

package com.javafortesters.tests;

import org.openqa.selenium.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddGroupTest extends TestBase {

    @Test(dataProvider = "Group Form")
    public void testGroupCreation(GroupData groupData) throws Exception {
        //open main page
        openMainPage();
        //go to groups page
        driver.findElement(By.linkText("groups")).click();
        //open group creation dialog
        driver.findElement(By.name("new")).click();
        //complete group creation form
        enterText(By.name("group_name"), groupData.getGroupName());
        enterText(By.name("group_header"), groupData.getHeader());
        enterText(By.name("group_footer"), groupData.getFooter());
        //submit group creation
        driver.findElement(By.name("submit")).click();
        //return to groups page
        driver.findElement(By.linkText("group page")).click();
    }

    @Test
    public void testEmptyGroupCreation() throws Exception {
        //open main page
        openMainPage();
        //go to groups page
        driver.findElement(By.linkText("groups")).click();
        //open group creation dialog
        driver.findElement(By.name("new")).click();
        //complete group creation form
        enterText(By.name("group_name"), "");
        enterText(By.name("group_header"), "");
        enterText(By.name("group_footer"), "");
        //submit group creation
        driver.findElement(By.name("submit")).click();
        //return to groups page
        driver.findElement(By.linkText("group page")).click();
    }


    @DataProvider(name = "Group Form")
    public static Object[][] text() {

        return new Object[][]{{new GroupData("Group 1", "header 1", "footer 1")}, {new GroupData("Group 2", "header 2", "footer 2")}, {new GroupData("Group 3", "header 3", "footer 3")}};

    }


}
