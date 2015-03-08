package com.javafortesters.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by QA_Lady on 1/17/2015.
 */
public class ElementHelper extends HelperBase {
    private static Logger LOG = LoggerFactory.getLogger(ElementHelper.class.getName());

    public ElementHelper(ApplicationManager manager) {
        super(manager);
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
