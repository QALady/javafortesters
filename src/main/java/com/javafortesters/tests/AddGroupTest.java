/**
 * Created by QA_Lady on 1/16/2015.
 */

package com.javafortesters.tests;

import com.javafortesters.utils.GroupHelper;
import com.javafortesters.utils.SortedListOf;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.javafortesters.tests.GroupDataGenerator.generateRandomGroups;
import static com.javafortesters.tests.GroupDataGenerator.loadGroupsFromXmlFile;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;


public class AddGroupTest extends TestBase {

    @Test(dataProvider = "Groups From File")
    public void testGroupCreation(GroupData groupData) throws Exception {
        //go to groups page
        GroupHelper groupHelper = appManager.getGroupHelper();
        //Save original state
        SortedListOf<GroupData> originalList = groupHelper.getGroups(true);
        groupHelper.createGroup(groupData);
        //Save new state
        SortedListOf<GroupData> actualList = groupHelper.getGroups(false);

        //Compare states
//        Assert.assertEquals(actualList.size(), originalList.size() + 1);
        //add recently added group to the original list to create the expected result for comparison
//        originalList.add(groupData);
//        Assert.assertEquals(actualList, originalList);

        //junit with hamcrest and withAdded() from SortedListOf wrapper
        assertThat(actualList, equalTo(originalList.withAdded(groupData)));

    }


//    @Test
//    public void testEmptyGroupCreation() throws Exception {
//        //open main page
//        appManager.getNavigationHelper().openMainPage();
//        //go to groups page
//        appManager.getControlInputHelper().clickOnElement(By.linkText("groups"));
//
//        //Save original state
//        List<GroupData> originalList = appManager.getGroupHelper().getGroups();
//
//        //open group creation dialog
//        appManager.getControlInputHelper().clickOnElement(By.name("new"));
//        GroupData group = new GroupData("", "", "");
//        appManager.getGroupHelper().fillInForm(group);
//        //submit group creation
//        appManager.getControlInputHelper().clickOnElement(By.name("submit"));
//        //return to groups page
//        appManager.getControlInputHelper().clickOnElement(By.linkText("group page"));
//
//        //Save new state
//        List<GroupData> actualList = appManager.getGroupHelper().getGroups();
//
//        //Compare states
//
//        //add recently added group to the original list to create the expected result for comparison
//        originalList.add(group);
//        //Sort items in the list to appear in the same order as gui shows them
//        Collections.sort(originalList);
//        Assert.assertEquals(actualList, originalList);
//
//    }


    @DataProvider(name = "Group Form Provider")
    public static Object[][] text() {

        return new Object[][]{{new GroupData("Group 1", "header 1", "footer 1")}, {new GroupData("Group 2", "header 2", "footer 2")}, {new GroupData("Group 3", "header 3", "footer 3")}};

    }

    @DataProvider(name = "Random Group Form Provider")
    public Iterator<Object[]> randomValidGroupGenerator() {
        return wrapGroupsForDataProvider(generateRandomGroups(5)).iterator();

//        List<Object[]> list = new ArrayList<Object[]>();
//        for (int i = 0; i < 5; i++) {
//            GroupData group = new GroupData();
//            group.setGroupName(generateRandomString("Group"));
//            group.setHeader(generateRandomString("header"));
//            group.setFooter(generateRandomString("footer"));
//            list.add(new Object[]{group});
//        }
//        return list.iterator();
    }

    protected List<Object[]> wrapGroupsForDataProvider(List<GroupData> groups) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (GroupData group : groups) {
            list.add(new Object[]{group});
        }
        return list;

    }

    @DataProvider(name = "Groups From File")
    public Iterator<Object[]> groupsFromFile() throws IOException {
        return wrapGroupsForDataProvider(loadGroupsFromXmlFile(new File("groups.xml"))).iterator();
    }


}



