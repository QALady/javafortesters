package com.javafortesters.tests;

import com.javafortesters.utils.SortedListOf;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by QA_Lady on 1/18/2015.
 */
public class EditGroupTest extends TestBase {

    @Test(dataProvider = "Group Name Provider")
    public void editGroup(int index, String name) {
        //Save original state
        SortedListOf<GroupData> originalList = appManager.getGroupHelper().getGroups(true);

        appManager.getGroupHelper().editGroup(index, name);

        //Save new state
        SortedListOf<GroupData> actualList = appManager.getGroupHelper().getGroups(false);
        //Compare states
        GroupData nameToCompare = new GroupData(name);
//        originalList.remove(index);
//        originalList.add(nameToCompare);
//        System.out.println("expected list" + originalList);
//        System.out.println("actual list" + actualList);
//        Assert.assertEquals(actualList, originalList);

        //junit with hamcrest and withAdded() from SortedListOf wrapper
        assertThat(actualList, equalTo(originalList.without(index).withAdded(nameToCompare)));

    }

    @DataProvider(name = "Group Name Provider")
    public static Object[][] nameProvider() {
        Random rnd = new Random();
        appManager.getNavigationHelper().openMainPage();
        appManager.getGroupHelper().goToGroupsPage(true);
        //Save original state
        List<GroupData> groupsList = appManager.getGroupHelper().getGroups(true);
        int n = groupsList.size() - 1;
        return new Object[][]{{rnd.nextInt(n), generateRandomString("new name")}, {rnd.nextInt(n), generateRandomString("new name")}, {rnd.nextInt(n), generateRandomString("new name")}};

    }
}
