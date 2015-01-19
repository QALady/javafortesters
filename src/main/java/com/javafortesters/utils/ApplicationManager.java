package com.javafortesters.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class ApplicationManager {

    public WebDriver driver;
    protected String baseUrl;


    private NavigationHelper navigationHelper;
    private ControlInputHelper controlInputHelper;
    private AlertHelper alertHelper;
    private ElementHelper elementHelper;

    public ApplicationManager() {
        driver = new FirefoxDriver();
        baseUrl = "http://localhost/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    public void quit() {
        driver.quit();
    }

    public NavigationHelper getNavigationHelper() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper(this);
        }
        return navigationHelper;
    }

    public ControlInputHelper getControlInputHelper() {
        if (controlInputHelper == null) {
            controlInputHelper = new ControlInputHelper(this);
        }
        return controlInputHelper;
    }

    public AlertHelper getAlertHelper() {
        if (alertHelper == null) {
            alertHelper = new AlertHelper(this);
        }
        return alertHelper;
    }

    public ElementHelper getElementHelper() {
        if (elementHelper == null) {
            elementHelper = new ElementHelper(this);
        }
        return elementHelper;
    }
}
