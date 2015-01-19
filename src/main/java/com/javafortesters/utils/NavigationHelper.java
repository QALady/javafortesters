package com.javafortesters.utils;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openMainPage() {
        driver.get(manager.baseUrl + "/addressbookv4.1.4/");
    }
}
