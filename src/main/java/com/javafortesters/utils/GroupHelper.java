package com.javafortesters.utils;

import com.javafortesters.tests.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    private SortedListOf<GroupData> cachedGroups;

    public SortedListOf<GroupData> getGroups() {
        if (cachedGroups == null) {
            rebuildCache();
        }
        return new SortedListOf<GroupData>(cachedGroups);
    }

    private void rebuildCache() {
        cachedGroups = new SortedListOf<GroupData>();

        List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
        for (WebElement checkbox : checkboxes) {
            String title = checkbox.getAttribute("title");
            GroupData group = new GroupData(title.substring("Select (".length(), title.length() - ")".length()));

            cachedGroups.add(group);

        }

    }

    public GroupHelper createGroup(GroupData groupData) {
        goToGroupsPage(true);
        //open group creation dialog
        initGroupCreation();
        fillInForm(groupData);
        //submit group creation
        submitGroupCreation();
        //return to groups page
        goToGroupsPage(false);
        rebuildCache();
        return this;
    }


    public GroupHelper editGroup(int index, String name) {
        goToGroupsPage(true);
        editGroupName(index, name);
        submitGroupUpdate();
        goToGroupsPage(false);
        rebuildCache();
        return this;
    }

    public GroupHelper removeGroupHighlevel(int index) {
        goToGroupsPage(true);
        removeGroup(index);
        goToGroupsPage(false);
        rebuildCache();
        return this;
    }

    public GroupHelper initGroupCreation() {
        manager.getControlInputHelper().clickOnElement(By.name("new"));
        return this;
    }

    public GroupHelper fillInForm(GroupData groupData) {
        if (groupData != null) {
            //complete group creation form
            manager.getControlInputHelper().enterText(By.name("group_name"), groupData.getGroupName());
            manager.getControlInputHelper().enterText(By.name("group_header"), groupData.getHeader());
            manager.getControlInputHelper().enterText(By.name("group_footer"), groupData.getFooter());
        } else {//complete form with empty Strings
            manager.getControlInputHelper().enterText(By.name("group_name"), "");
            manager.getControlInputHelper().enterText(By.name("group_header"), "");
            manager.getControlInputHelper().enterText(By.name("group_footer"), "");
        }
        return this;
    }


    public GroupHelper submitGroupCreation() {
        manager.getControlInputHelper().clickOnElement(By.name("submit"));
        cachedGroups = null;
        return this;
    }


    public GroupHelper removeGroup(int index) {
        manager.getControlInputHelper().clickOnElement(By.xpath("//input[@name='selected[]'][" + (index + 1) + "]"));
        manager.getControlInputHelper().clickOnElement(By.name("delete"));
        cachedGroups = null;
        return this;
    }


    public GroupHelper editGroupName(int index, String name) {
        manager.getControlInputHelper().clickOnElement(By.xpath("//input[@name='selected[]'][" + (index + 1) + "]"));
        manager.getControlInputHelper().clickOnElement(By.name("edit"));
        manager.getControlInputHelper().enterText(By.name("group_name"), name);
        return this;

    }

    public GroupHelper goToGroupsPage(boolean goFromMainPage) {
        if (!onGroupsPage()) {
            if (goFromMainPage) {
                manager.getControlInputHelper().clickOnElement(By.linkText("groups"));
            } else {
                manager.getControlInputHelper().clickOnElement(By.linkText("group page"));
            }
        }
        return this;

    }

    private boolean onGroupsPage() {
        if (driver.getCurrentUrl().contains("/group.php") && driver.findElements(By.name("new")).size() > 0) {
            return true;
        }
        return false;
    }

    public GroupHelper submitGroupUpdate() {
        manager.getControlInputHelper().clickOnElement(By.name("update"));
        cachedGroups = null;
        return this;
    }


}
