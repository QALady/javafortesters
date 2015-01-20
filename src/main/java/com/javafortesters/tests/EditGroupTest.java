package com.javafortesters.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Created by QA_Lady on 1/18/2015.
 */
public class EditGroupTest extends TestBase {

    @Test(dataProvider = "Group Name Provider")
    public void editGroup(int index, String name) {
        appManager.getNavigationHelper().openMainPage();
        appManager.getGroupHelper().goToGroupsPage(true);
        appManager.getGroupHelper().editGroupName(index, name);
        appManager.getGroupHelper().submitGroupUpdate();
        //return to groups page
        appManager.getGroupHelper().goToGroupsPage(false);

    }

    @DataProvider(name = "Group Name Provider")
    public static Object[][] nameProvider() {

        return new Object[][]{{1, "new name1"}, {4, "new name2"}, {7, "new name3"}};

    }
}
