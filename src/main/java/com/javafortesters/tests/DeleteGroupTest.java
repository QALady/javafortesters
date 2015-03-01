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
public class DeleteGroupTest extends TestBase {

    @Test(dataProvider = "Group Index Provider")
    public void deleteGroup(int index) {
        appManager.getGroupHelper().goToGroupsPage(true);
        //Save original state
        SortedListOf<GroupData> originalList = appManager.getGroupHelper().getGroups();
        //Remove Group
        appManager.getGroupHelper()
                .removeGroupHighlevel(index);
        //Save new state
        SortedListOf<GroupData> actualList = appManager.getGroupHelper().getGroups();

        //Compare states
        //remove recently deleted group from the original list to create the expected result for comparison
        originalList.remove(index);
        Assert.assertEquals(actualList, originalList);

    }


    @DataProvider(name = "Group Index Provider")
    public static Object[][] indexProvider() {
        Random rnd = new Random();
        appManager.getNavigationHelper().openMainPage();
        appManager.getGroupHelper().goToGroupsPage(true);
        //Save original state
        List<GroupData> groupsList = appManager.getGroupHelper().getGroups();
        int n = groupsList.size() - 1;
        return new Object[][]{{rnd.nextInt(n)}, {rnd.nextInt(n)}, {rnd.nextInt(n)}};

    }


}
