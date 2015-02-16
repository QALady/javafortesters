package com.javafortesters.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by QA_Lady on 1/18/2015.
 */
public class EditGroupTest extends TestBase {

    @Test(dataProvider = "Group Name Provider")
    public void editGroup(int index, String name) {
        appManager.getNavigationHelper().openMainPage();
        appManager.getGroupHelper().goToGroupsPage(true);
        //Save original state
        List<GroupData> originalList = appManager.getGroupHelper().getGroups();
        appManager.getGroupHelper().editGroupName(index, name);
        appManager.getGroupHelper().submitGroupUpdate();
        //return to groups page
        appManager.getGroupHelper().goToGroupsPage(false);
        //Save new state
        List<GroupData> actualList = appManager.getGroupHelper().getGroups();
        //Compare states
        GroupData nameToCompare = new GroupData(name);
        originalList.remove(index);
        originalList.add(nameToCompare);
        //Sort items in the list to appear in the same order as gui shows them
        Collections.sort(originalList);
        System.out.println("expected list" + originalList);
        System.out.println("actual list" + actualList);
        Assert.assertEquals(actualList, originalList);

    }

    @DataProvider(name = "Group Name Provider")
    public static Object[][] nameProvider() {
        Random rnd = new Random();
        appManager.getNavigationHelper().openMainPage();
        appManager.getGroupHelper().goToGroupsPage(true);
        //Save original state
        List<GroupData> groupsList = appManager.getGroupHelper().getGroups();
        int n = groupsList.size() - 1;
        return new Object[][]{{rnd.nextInt(n), generateRandomString("new name")}, {rnd.nextInt(n), generateRandomString("new name")}, {rnd.nextInt(n), generateRandomString("new name")}};

    }
}
