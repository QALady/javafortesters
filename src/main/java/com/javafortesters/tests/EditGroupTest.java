package com.javafortesters.tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by QA_Lady on 1/18/2015.
 */
public class EditGroupTest extends TestBase {

    @Test(dataProvider = "Group Name Provider")
    public void editGroup(int index, String name) {
        appManager.getNavigationHelper().openMainPage();
        appManager.getControlInputHelper().clickOnElement(By.linkText("groups"));
        appManager.getGroupHelper().editGroupName(index, name);
        appManager.getControlInputHelper().clickOnElement(By.name("update"));
        //return to groups page
        appManager.getControlInputHelper().clickOnElement(By.linkText("group page"));

    }


    @DataProvider(name = "Group Name Provider")
    public static Object[][] nameProvider() {

        return new Object[][]{{1, "new name1"}, {4, "new name2"}, {7, "new name3"}};

    }
}
