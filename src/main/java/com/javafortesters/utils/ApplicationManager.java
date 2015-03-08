package com.javafortesters.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class ApplicationManager {

    private static Logger LOG = LoggerFactory.getLogger(ApplicationManager.class.getName());
    private static Logger BROWSER_LOG = LoggerFactory.getLogger("Browser");

    public WebDriver driver;
    protected String baseUrl;


    private NavigationHelper navigationHelper;
    private ControlInputHelper controlInputHelper;
    private AlertHelper alertHelper;
    private ElementHelper elementHelper;
    private GroupHelper groupHelper;
    private ContactsHelper contactsHelper;
    private Properties properties;
    private String browser;

    public ApplicationManager(Properties properties) {
        this.properties = properties;
        browser = properties.getProperty("browser");
        BROWSER_LOG.info(browser + " was requested");
        if (browser.equals("firefox")) {
            driver = new FirefoxDriver();
            BROWSER_LOG.info(browser + " was started");
        } else if (browser.equals("ie")) {
            driver = new InternetExplorerDriver();
            BROWSER_LOG.info(browser + " was started");
        } else {
            throw new Error("Unsupported browser: " + browser);
        }
        baseUrl = properties.getProperty("baseUrl");
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }


    public void quit() {
        driver.quit();
        BROWSER_LOG.info(browser + " has quit");
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
