package com.javafortesters.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.Properties;
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
    private GroupHelper groupHelper;
    private ContactsHelper contactsHelper;
    private Properties properties;

    public ApplicationManager(Properties properties) {
        this.properties = properties;
        String browser = properties.getProperty("browser");
        if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
        } else if (browser.equals("ie")) {
            driver = new InternetExplorerDriver();
        } else {
            throw new Error("Unsupported browser: " + browser);
        }
        baseUrl = properties.getProperty("baseUrl");
        driver.get(baseUrl);
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

    public GroupHelper getGroupHelper() {
        if (groupHelper == null) {
            groupHelper = new GroupHelper(this);
        }
        return groupHelper;
    }

    public ContactsHelper getContactsHelper() {
        if (contactsHelper == null) {
            contactsHelper = new ContactsHelper(this);
        }
        return contactsHelper;
    }
}
