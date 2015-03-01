package com.javafortesters.utils;

import org.openqa.selenium.By;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openMainPage() {
        if (!onMainPage()) {
            manager.getControlInputHelper().clickOnElement(By.linkText("home"));
        }
    }

    private boolean onMainPage() {
        return driver.findElements(By.id("maintable")).size() > 0;
    }
}
