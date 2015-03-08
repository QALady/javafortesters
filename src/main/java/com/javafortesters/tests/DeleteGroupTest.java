package com.javafortesters.tests;

import com.javafortesters.utils.SortedListOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by QA_Lady on 1/18/2015.
 */
public class DeleteGroupTest extends TestBase {

    private static Logger LOG = LoggerFactory.getLogger(DeleteGroupTest.class.getName());

    @Test(dataProvider = "Group Index Provider")
    public void deleteGroup(int index) {
        LOG.info("DeleteGroupTest.deleteGroup() start");
        appManager.getGroupHelper().goToGroupsPage(true);
        //Save original state
        SortedListOf<GroupData> originalList = appManager.getGroupHelper().getGroups(true);
        //Remove Group
        appManager.getGroupHelper()
                .removeGroupHighlevel(index);
        //Save new state
        SortedListOf<GroupData> actualList = appManager.getGroupHelper().getGroups(false);

        //Compare states
//        //remove recently deleted group from the original list to create the expected result for comparison
//        originalList.remove(index);
//        Assert.assertEquals(actualList, originalList);

        //junit with hamcrest and without() from SortedListOf wrapper
        assertThat(actualList, equalTo(originalList.without(index)));
        LOG.info("DeleteGroupTest.deleteGroup() end");

    }


    @DataProvider(name = "Group Index Provider")
    public static Object[][] indexProvider() {
        Random rnd = new Random();
        appManager.getNavigationHelper().openMainPage();
        appManager.getGroupHelper().goToGroupsPage(true);
        //Save original state
        List<GroupData> groupsList = appManager.getGroupHelper().getGroups(true);
        int n = groupsList.size() - 1;
        return new Object[][]{{rnd.nextInt(n)}, {rnd.nextInt(n)}, {rnd.nextInt(n)}};

    }


}
