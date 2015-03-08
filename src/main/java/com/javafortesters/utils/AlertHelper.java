package com.javafortesters.utils;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class AlertHelper extends HelperBase {
    private static Logger LOG = LoggerFactory.getLogger(AlertHelper.class.getName());

    private boolean acceptNextAlert = true;

    public AlertHelper(ApplicationManager manager) {
        super(manager);
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }

}
