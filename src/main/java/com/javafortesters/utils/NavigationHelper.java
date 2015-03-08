package com.javafortesters.utils;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class NavigationHelper extends HelperBase {
    private static Logger LOG = LoggerFactory.getLogger(NavigationHelper.class.getName());

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
