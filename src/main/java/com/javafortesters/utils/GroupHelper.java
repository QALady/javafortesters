package com.javafortesters.utils;

import org.openqa.selenium.By;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        super(manager);
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

}
