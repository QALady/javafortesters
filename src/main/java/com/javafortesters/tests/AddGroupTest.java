/**
 * Created by QA_Lady on 1/16/2015.
 */

package com.javafortesters.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AddGroupTest extends TestBase {

    @Test(dataProvider = "Random Group Form Provider")
    public void testGroupCreation(GroupData groupData) throws Exception {
        //open main page
        appManager.getNavigationHelper().openMainPage();
        //go to groups page
        appManager.getGroupHelper().goToGroupsPage(true);
        //Save original state
        List<GroupData> originalList = appManager.getGroupHelper().getGroups();

        //open group creation dialog
        appManager.getGroupHelper().initGroupCreation();
        appManager.getGroupHelper().fillInForm(groupData);
        //submit group creation
        appManager.getGroupHelper().submitGroupCreation();
        //return to groups page
        appManager.getGroupHelper().goToGroupsPage(false);
        //Save new state
        List<GroupData> actualList = appManager.getGroupHelper().getGroups();

        //Compare states
//        Assert.assertEquals(actualList.size(), originalList.size() + 1);

        //add recently added group to the original list to create the expected result for comparison
        originalList.add(groupData);
        //Sort items in the list to appear in the same order as gui shows them
        Collections.sort(originalList);
        Assert.assertEquals(actualList, originalList);
    }


    @Test
    public void testEmptyGroupCreation() throws Exception {
        //open main page
        appManager.getNavigationHelper().openMainPage();
        //go to groups page
        appManager.getControlInputHelper().clickOnElement(By.linkText("groups"));

        //Save original state
        List<GroupData> originalList = appManager.getGroupHelper().getGroups();

        //open group creation dialog
        appManager.getControlInputHelper().clickOnElement(By.name("new"));
        GroupData group = new GroupData("", "", "");
        appManager.getGroupHelper().fillInForm(group);
        //submit group creation
        appManager.getControlInputHelper().clickOnElement(By.name("submit"));
        //return to groups page
        appManager.getControlInputHelper().clickOnElement(By.linkText("group page"));

        //Save new state
        List<GroupData> actualList = appManager.getGroupHelper().getGroups();

        //Compare states

        //add recently added group to the original list to create the expected result for comparison
        originalList.add(group);
        //Sort items in the list to appear in the same order as gui shows them
        Collections.sort(originalList);
        Assert.assertEquals(actualList, originalList);

    }


    @DataProvider(name = "Group Form Provider")
    public static Object[][] text() {

        return new Object[][]{{new GroupData("Group 1", "header 1", "footer 1")}, {new GroupData("Group 2", "header 2", "footer 2")}, {new GroupData("Group 3", "header 3", "footer 3")}};

    }

    @DataProvider(name = "Random Group Form Provider")
    public Iterator<Object[]> randomValidGroupGenerator() {
        List<Object[]> list = new ArrayList<Object[]>();
        for (int i = 0; i < 5; i++) {
            GroupData group = new GroupData();
            group.setGroupName(generateRandomString("Group"));
            group.setHeader(generateRandomString("header"));
            group.setFooter(generateRandomString("footer"));
            list.add(new Object[]{group});
        }
        return list.iterator();
    }

}



