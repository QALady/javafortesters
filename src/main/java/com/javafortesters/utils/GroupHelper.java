package com.javafortesters.utils;

import com.javafortesters.tests.GroupData;
import org.openqa.selenium.By;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
    }

    public void initGroupCreation() {
        manager.getControlInputHelper().clickOnElement(By.name("new"));
    }

    public void fillInForm(GroupData groupData) {
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

    }


    public void submitGroupCreation() {
        manager.getControlInputHelper().clickOnElement(By.name("submit"));
    }
    public void removeGroup(int index) {
        manager.getControlInputHelper().clickOnElement(By.xpath("//input[@name='selected[]'][" + index + "]"));
        manager.getControlInputHelper().clickOnElement(By.name("delete"));
    }


    public void editGroupName(int index, String name) {
        manager.getControlInputHelper().clickOnElement(By.xpath("//input[@name='selected[]'][" + index + "]"));
        manager.getControlInputHelper().clickOnElement(By.name("edit"));
        manager.getControlInputHelper().enterText(By.name("group_name"), name);

    }

    public void goToGroupsPage(boolean goFromMainPage) {
        if (goFromMainPage) {
            manager.getControlInputHelper().clickOnElement(By.linkText("groups"));
        } else
            manager.getControlInputHelper().clickOnElement(By.linkText("group page"));

    }


    public void submitGroupUpdate() {
        manager.getControlInputHelper().clickOnElement(By.name("update"));
    }
}
