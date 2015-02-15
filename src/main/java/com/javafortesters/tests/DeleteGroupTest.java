package com.javafortesters.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

/**
 * Created by QA_Lady on 1/18/2015.
 */
public class DeleteGroupTest extends TestBase {

    @Test(dataProvider = "Group Index Provider")
    public void deleteGroup(int index) {
        appManager.getNavigationHelper().openMainPage();
        appManager.getGroupHelper().goToGroupsPage(true);
        //Save original state
        List<GroupData> originalList = appManager.getGroupHelper().getGroups();
        //Remove Group
        appManager.getGroupHelper().removeGroup(index);
        //return to groups page
        appManager.getGroupHelper().goToGroupsPage(false);

        //Save new state
        List<GroupData> actualList = appManager.getGroupHelper().getGroups();

        //Compare states
        //remove recently deleted group from the original list to create the expected result for comparison
        originalList.remove(index);
        //Sort items in the list to appear in the same order as gui shows them
        Collections.sort(originalList);
        Assert.assertEquals(actualList, originalList);

    }


    @DataProvider(name = "Group Index Provider")
    public static Object[][] indexProvider() {

        return new Object[][]{{1}, {3}, {5}};

    }


}
