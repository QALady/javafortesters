package com.javafortesters.tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by QA_Lady on 1/18/2015.
 */
public class DeleteGroupTest extends TestBase {

    @Test(dataProvider = "Group Index Provider")
    public void deleteGroup(int index) {
        appManager.getNavigationHelper().openMainPage();
        appManager.getControlInputHelper().clickOnElement(By.linkText("groups"));
        appManager.getGroupHelper().removeGroup(index);
        //return to groups page
        appManager.getControlInputHelper().clickOnElement(By.linkText("group page"));

    }


    @DataProvider(name = "Group Index Provider")
    public static Object[][] indexProvider() {

        return new Object[][]{{1}, {3}, {5}};

    }

}
